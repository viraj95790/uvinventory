package com.inventorymanagement.main.eu.blogic.jdbc;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.eu.bi.CategoryDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCBaseDAO;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Category;

public class JDBCCategoryDAO extends JDBCBaseDAO implements CategoryDAO{
	
	public JDBCCategoryDAO(Connection connection){
		this.connection = connection;
		
	}

	
	/*public ArrayList<Category> getCategoryList(String userId,Pagination pagination) {
	PreparedStatement preparedStatement = null;
	ArrayList<Category>list = new ArrayList<Category>();
	String sql = "select id,categoryname,description,lastupdated from category where userID = ? or userid = 'administrator' ";
	sql = pagination.getSQLQuery(sql);

	try{
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, userId);
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
*/


	public int saveCategory(Category category, String userId) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into category(categoryname,description,lastupdated,userID,gender) values(?,?,?,?,?) ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, category.getCategoryName());
			preparedStatement.setString(2, category.getDescription());
			preparedStatement.setTimestamp(3,DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setString(4, userId);
			preparedStatement.setString(5, category.getCategorytype());
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	
	/*public int getCategoryCount(String userId) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from category where userID = ? or userid = 'administrator' ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
*/

	public Category getCategory(int selectedid) {
		PreparedStatement preparedStatement = null;
		Category category = new Category();
		String sql = "select * from category where id=" +selectedid ;
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
			{
				category.setId(rs.getInt("id"));
				category.setCategoryName(rs.getString("categoryname"));
				category.setDescription(rs.getString("description"));
				category.setLastupdated(rs.getString("lastupdated"));
				category.setGender(rs.getInt("gender"));
				
				
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return category;
	}


	public int deleteCategory(int selectedid) {
		int result=0;
		try{
			String sql="delete from category where id= " +selectedid;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result= preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public int updateCategory(Category category, int selectedid) {
		int result=0;
		PreparedStatement preparedStatement = null;
		String sql = "update  category set categoryname=?,description=?,lastupdated=?,gender=? where id= "+selectedid;
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, category.getCategoryName());
			preparedStatement.setString(2, category.getDescription());
			preparedStatement.setTimestamp(3,DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setInt(4, category.getGender());
			
			preparedStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Category> getCategoryList(Pagination pagination,String searchText,String userID)
			throws Exception {
		PreparedStatement preparedStatement = null;
		ArrayList<Category>list = new ArrayList<Category>();
		String sql = "select id,categoryname,description,lastupdated,gender from category where categoryname like('%"+searchText+"%') and userid = ? ";
		sql = pagination.getSQLQuery(sql);

		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,userID);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				
				Category category = new Category();
				category.setId(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setDescription(rs.getString(3));
				category.setLastupdated(DateTimeUtils.getDateinSimpleFormate(rs.getTimestamp(4)));
				category.setGender(rs.getInt(5));
				int quantity = getpsizeQuantity(category.getId());
				category.setQuantity(quantity);
				
				list.add(category);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			return list;
	}


	public int getCategoryCount(String searchText,String userID) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from category  where categoryname like('%"+searchText+"%') and userid = ? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Category> getCategoryListByBranch(String selectedBranch,
			Pagination pagination) throws Exception {
		PreparedStatement preparedStatement = null;
		ArrayList<Category>list = new ArrayList<Category>();
		String sql = "select id,categoryname,description,lastupdated from category where userID = ? ";
		sql = pagination.getSQLQuery(sql);

		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, selectedBranch);
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


	public int getCategoryCountByBranch(String selectedBranch) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from category where userID = ? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, selectedBranch);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean isCategoryExist(String categoryName, String userId) {
		boolean result = false;
				
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				String query = "SELECT * FROM category where categoryname = ? AND userID = ?";
				try{
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, categoryName);
					preparedStatement.setString(2, userId);
					resultSet = preparedStatement.executeQuery();

					if( resultSet != null && resultSet.next() ) {
		    			int id = resultSet.getInt(1);
		    			if(id > 0) {
		    				result = true;
		    			}
		    		}
				 }catch(Exception ex){
				  ex.printStackTrace();
			   }finally{
					if(preparedStatement!=null)
						try {
							preparedStatement.close();
						} catch (Exception e) {
							e.printStackTrace();
					}
				}
				return result;
			}


	public int deleteProduct(int selectedid) throws Exception {
		int result=0;
		try{
			String sql="delete from product where categoryid= " +selectedid;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result= preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public int deleteSubCategory(int selectedid) throws Exception {
		int result=0;
		try{
			String sql="delete from subcategory where categoryid= " +selectedid;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result= preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public boolean isProductExist(int categoryID,String productName) throws Exception {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from product where categoryid = ? and productname = ? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, categoryID);
			preparedStatement.setString(2, productName);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				result = true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Category> getGendorCategoryList(String gender,String userid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Category>list = new ArrayList<Category>();
		String sql = "select id,categoryname,description,lastupdated,gender from category where gender="+gender+"  ";
		

		try{
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setString(1,userid);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				
				Category category = new Category();
				category.setId(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setDescription(rs.getString(3));
				category.setLastupdated(DateTimeUtils.getDateinSimpleFormate(rs.getTimestamp(4)));
				category.setGender(rs.getInt(5));
				
				
				list.add(category);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			return list;
	}


	private int getpsizeQuantity(int categoryid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) FROM subcategory inner join category ");
		sql.append("on subcategory.categoryid = category.id ");
		sql.append("inner join p_size on subcategory.subcategoryname = p_size.productid and category.id = "+categoryid+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int getTotalCategory() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT count(*) FROM category";
		
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


	public int getSubcategoryID(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT subcategoryname FROM subcategory where categoryid= "+selectedid+" ";
		
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


	public ArrayList<Category> getmainCategorylist() {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ArrayList<Category> list = new ArrayList<Category>();
		String sql = "select id, name from main_category";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Category  category = new Category();
				
				category.setId(rs.getInt(1));
				category.setCategorytype(rs.getString(2));
				
				list.add(category);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}



}
