package com.inventorymanagement.main.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.eu.bi.AdminSubcategoryDAO;
import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.bi.ProductDAO;
import com.inventorymanagement.main.eu.entity.AdminSubcategory;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.RemainStock;
import com.inventorymanagement.main.eu.entity.Stock;
import com.inventorymanagement.main.eu.entity.StockProduct;

public class JDBCAdminSubcategoryDAO extends JDBCBaseDAO implements AdminSubcategoryDAO{
	
	public JDBCAdminSubcategoryDAO(Connection connection){
		this.connection = connection;
	}

	public int getSubCategoryCount(String searchText,String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		//String sql = "select count(*) from product where productname like('%"+searchText+"%')";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) FROM product inner join category on category.id = product.categoryid ");
		sql.append("where productname like('%"+searchText+"%') and category.userid = ? ");

		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, userid);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;	}

	public ArrayList<AdminSubcategory> getSubCategoryList(Pagination pagination,String searchText,String userid) {
		PreparedStatement preparedStatement = null;
		//ArrayList<Category>list = new ArrayList<Category>();
		ArrayList<AdminSubcategory>subCategoryList=new ArrayList<AdminSubcategory>() ;
		//String sql = "select id,categoryid,productname,price from product where productname like('%"+searchText+"%') ";
		//sql = pagination.getSQLQuery(sql);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT product.id,product.categoryid,productname,price FROM product inner join category on category.id = product.categoryid ");
		sql.append("where productname like('%"+searchText+"%') and category.userid = ? ");
		String query = pagination.getSQLQuery(sql.toString());

		try{
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userid);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				//Category category = new Category();
				AdminSubcategory adminSubcategory =new AdminSubcategory();
				adminSubcategory.setId(rs.getInt(1));
				int categoryID = rs.getInt(2);
				String categoryName = getCategoryName(categoryID);
				adminSubcategory.setCategoryName(categoryName);
				adminSubcategory.setProductName(rs.getString(3));
				adminSubcategory.setPrice(rs.getDouble(4));
				
				subCategoryList.add(adminSubcategory);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			return subCategoryList;
			
	}

	private String getCategoryName(int categoryID) {
		PreparedStatement  preparedStatement = null;
		String result = "";
		String sql = "select categoryname from category where id = "+categoryID+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public AdminSubcategory getSubcategory(int selectedid) {
		PreparedStatement preparedStatement = null;
		Product product =new Product();
		AdminSubcategory adminSubcategory =new AdminSubcategory();
		//String sql = "select * from product where id=" +selectedid ;
		StringBuffer sql = new StringBuffer();
		sql.append("select product.id, categoryid,productname,price,profitmargin,cgst,sgst,description,gender from product ");
		sql.append("inner join category on product.categoryid = category.id ");
		sql.append("where product.id= "+selectedid+" ");
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
			{
				adminSubcategory.setId(rs.getInt(1));
				adminSubcategory.setCategoryID(rs.getInt(2));
				adminSubcategory.setProductName(rs.getString(3));
				//adminSubcategory.setDescription(rs.getString("productname"));
				adminSubcategory.setPrice(rs.getDouble(4));
				adminSubcategory.setProfitmargin(rs.getString(5));
				adminSubcategory.setCgst(rs.getString(6));
				adminSubcategory.setSgst(rs.getString(7));
				adminSubcategory.setDescription(rs.getString(8));
				adminSubcategory.setGender(rs.getInt(9));
				
				
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return adminSubcategory;
	}

	public int updateAdminSubcategory(AdminSubcategory adminSubcategory,
			int selectedid) {
		int result=0;
		PreparedStatement preparedStatement = null;
		String sql = "update  product set categoryid=?,productname=?,price=?,profitmargin=?,cgst=?,sgst=? where id= "+selectedid;
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, adminSubcategory.getCategoryID());
			preparedStatement.setString(2, adminSubcategory.getProductName());
			preparedStatement.setDouble(3, adminSubcategory.getPrice());
			preparedStatement.setString(4, adminSubcategory.getProfitmargin());
			preparedStatement.setString(5, adminSubcategory.getCgst());
			preparedStatement.setString(6, adminSubcategory.getSgst());
			
			preparedStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public int deleteProduct(int selectedid) {
		int result=0;
		try{
			String sql="delete from product where id= " +selectedid;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result= preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int deleteSubCategory(int selectedid) {
		int result=0;
		try{
			String sql="delete from subcategory where subcategoryname= " +selectedid;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result= preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public double getAdminProductPrice(int categoryID, int subCategoryID)
			throws Exception {
	
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT price FROM product where categoryid = "+categoryID+" and id = "+subCategoryID+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int getAdminProductStock(int categoryID, int subCategoryID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT quantity FROM subcategory where categoryid = "+categoryID+" and subcategoryname = "+subCategoryID+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean checkQuantityExist(int categoryID, int subCategoryID) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from subcategory where categoryid = "+categoryID+" and subcategoryname = "+subCategoryID+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}