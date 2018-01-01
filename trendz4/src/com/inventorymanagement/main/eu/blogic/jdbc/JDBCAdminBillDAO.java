package com.inventorymanagement.main.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.eu.bi.AdminBillDAO;
import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.bi.ProductDAO;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Product;

public class JDBCAdminBillDAO extends JDBCBaseDAO implements AdminBillDAO{
	
	public JDBCAdminBillDAO(Connection connection){
		this.connection = connection;
	}
	
	private String getCategoryName(int categoryID) {
		PreparedStatement preparedStatement = null;
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
	
	
	public ArrayList<Category> getCategoryList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Category>list = new ArrayList<Category>();
		String sql = "select id,categoryname,description,lastupdated from category";
		

		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				Category category = new Category();
				category.setId(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setDescription(rs.getString(3));
				category.setLastupdated(DateTimeUtils.getDateinSimpleFormate(rs.getTimestamp(4)));
				
				list.add(category);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			return list;
	}

	

	public ArrayList<Product> getProductList(int categoryID,String branchname) {
		PreparedStatement preparedStatement = null;
		ArrayList<Product>list = new ArrayList<Product>();
		String sql = "select id,categoryid,subcategoryname,description,lastupdated,quantity,remainingstock,userid,price from subcategory where userid = '"+branchname+"' and categoryid = "+categoryID+" and quantity!=0 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setCategoryID(rs.getInt(2));
				String categoryName = getCategoryName(product.getCategoryID());
				product.setCategoryName(categoryName);
				int subcategoryID = rs.getInt(3);
				String productName = getProductName(subcategoryID);
				product.setProductName(productName);
				product.setDescription(rs.getString(4));
				product.setLastUpdated(DateTimeUtils.getDateinSimpleFormate(rs.getTimestamp(5)));
				product.setQuantity(rs.getInt(6));
				int quantity = rs.getInt(6);
				
				product.setRemaioningStock(rs.getInt(7));
				
				//String branchName = getBranchName(rs.getString(8));
				product.setBranchName(branchname);
				product.setPrice(rs.getInt(9));
				int rstock = product.getQuantity()-product.getRemaioningStock();
				product.setRemaioningStock(rstock);
				double price = rs.getDouble(9);
				
				double amountTotal = rstock*price;
				product.setTotalAmount(amountTotal);
				
				list.add(product);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	private String getProductName(int subCategoryID) {
		PreparedStatement preparedStatement = null;
		String subCategoryName = "";
		String sql = "select productname from product where id = "+subCategoryID+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				subCategoryName = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return subCategoryName;
	}


	public String getBranchName(int branchId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select userID from branch where id = ? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, branchId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}