package com.inventorymanagement.main.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.eu.bi.AccessoriesDAO;
import com.inventorymanagement.main.eu.entity.Accessories;
import com.inventorymanagement.main.eu.entity.Purchase;

public class JDBCAccessoriesDAO extends JDBCBaseDAO implements AccessoriesDAO{
	
	public JDBCAccessoriesDAO(Connection connection){
		this.connection = connection;
	}

	public int saveAccessories(Accessories accessories,String userID) {
		
		PreparedStatement preparedStatement = null;
		int resulr = 0;
		String sql = "insert into accessories(productname,discription,price,imagename,userid) values(?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, accessories.getProductName());
			preparedStatement.setString(2, accessories.getDiscription());
			preparedStatement.setDouble(3, accessories.getPrice());
			preparedStatement.setString(4, accessories.getUploadedimage());
			preparedStatement.setString(5, userID);
			
			
			resulr = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resulr;
	}

	public int getAccessoriesCount(String searchText, String userId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from accessories  where productname like('%"+searchText+"%') and userid = ? ";
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

	public ArrayList<Accessories> getAccessoriesList(Pagination pagination,
			String searchText, String userId) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<Accessories>list = new ArrayList<Accessories>();
		String sql = "select id,productname,discription,price,imagename,stock from accessories  where productname like('%"+searchText+"%') and userid = ?";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accessories accessories = new Accessories();
				accessories.setId(rs.getInt(1));
				accessories.setProductName(rs.getString(2));
				accessories.setDiscription(rs.getString(3));
				accessories.setPrice(rs.getDouble(4));
				accessories.setUploadedimage(rs.getString(5));
				accessories.setQuantity(rs.getInt(6));
				
				list.add(accessories);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public Accessories getAccessories(int selectedid) {
		PreparedStatement preparedStatement = null;
		Accessories accessories = new Accessories();
		String sql = "select id,productname,discription,price,imagename,stock from accessories where id = "+selectedid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				accessories.setId(rs.getInt(1));
				accessories.setProductName(rs.getString(2));
				accessories.setDiscription(rs.getString(3));
				accessories.setPrice(rs.getDouble(4));
				accessories.setUploadedimage(rs.getString(5));
				accessories.setQuantity(rs.getInt(6));
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return accessories;
	}

	public int updateAccessories(Accessories accessories, int selectedid) {
		PreparedStatement preparedStatement = null;
		int resulr = 0;
		String sql = "update accessories set productname=?,discription=?,price=?,imagename=? where id = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, accessories.getProductName());
			preparedStatement.setString(2, accessories.getDiscription());
			preparedStatement.setDouble(3, accessories.getPrice());
			preparedStatement.setString(4, accessories.getUploadedimage());
			
			resulr = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		
		return resulr;
	}

	public String getUploadedImage(int selectedid) {
		
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select imagename from accessories where id = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int deleteAccessories(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from accessories where id = "+selectedid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean checkAccessoriesExist(String productName) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from accessories where productname = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productName);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Accessories> getAccessoriesList(String userId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accessories>list = new ArrayList<Accessories>();
		String sql = "SELECT id,productname FROM accessories where userid = ?";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accessories accessories = new Accessories();
				accessories.setId(rs.getInt(1));
				accessories.setProductName(rs.getString(2));
				list.add(accessories);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean checkAccessoriesExist(int accessoriesID, int isAccessories) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from purchase where productid = "+accessoriesID+" and accessories = "+isAccessories+" ";
		
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

	public int savePurchase(Purchase purchase,String userID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into purchase(productid,quantity,mrp,total,accessories,userid) values(?,?,?,?,?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, purchase.getProductId());
			preparedStatement.setInt(2, purchase.getQuantity());
			preparedStatement.setDouble(3, purchase.getMrp());
			preparedStatement.setDouble(4, purchase.getTotal());
			preparedStatement.setInt(5, 1);
			preparedStatement.setString(6, userID);
			
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updatePurchase(Purchase purchase,String userID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update purchase set quantity=?,mrp=?,total=? where productid="+purchase.getProductId()+" and accessories=1 and userid=?";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, purchase.getQuantity());
			preparedStatement.setDouble(2, purchase.getMrp());
			preparedStatement.setDouble(3, purchase.getTotal());
			preparedStatement.setString(4, userID);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateAccessoriesStock(int accessoriesStock, int productId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update accessories set stock = ? where id = ? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accessoriesStock);
			preparedStatement.setInt(2, productId);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updatePurchaseTotal(int id, double discountTotal) {
		PreparedStatement preparedStatement = null;
		int restlr = 0;
		String sql = "update purchase set total = ? where id = ?";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, discountTotal);
			preparedStatement.setInt(2, id);
			
			restlr = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return restlr;
	}
	
	
	
	

}
