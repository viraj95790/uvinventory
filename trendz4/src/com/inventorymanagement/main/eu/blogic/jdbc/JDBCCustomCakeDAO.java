package com.inventorymanagement.main.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.bi.CustomCakeDAO;
import com.inventorymanagement.main.eu.entity.CustomCake;

public class JDBCCustomCakeDAO extends JDBCBaseDAO implements CustomCakeDAO{
	
	public JDBCCustomCakeDAO(Connection connection){
		this.connection = connection;
	}

	public int saveCustomCake(CustomCake customCake,String userID) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into customcake(weight,flavour,notes,msgoncake,amount,time,amorpm,userID,status,minute,deliverydate) values(?,?,?,?,?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, customCake.getWeight());
			preparedStatement.setString(2, customCake.getFlavour());
			preparedStatement.setString(3, customCake.getNotes());
			preparedStatement.setString(4, customCake.getMsgOnCake());
			preparedStatement.setInt(5, customCake.getAmount());
			preparedStatement.setString(6, customCake.getTime());
			preparedStatement.setString(7, customCake.getAmorpm());
			preparedStatement.setString(8, userID); 
			preparedStatement.setInt(9, 0);
			preparedStatement.setString(10, customCake.getMinute());
			preparedStatement.setString(11, customCake.getDeliveryDate());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getCustomCakeCount(String userId) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from customcake where userID = ? ";
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

	public ArrayList<CustomCake> getCustomCakeList(String userId,Pagination pagination)
			throws Exception {
		PreparedStatement preparedStatement = null;
		ArrayList<CustomCake>list = new ArrayList<CustomCake>();
		String sql = "select weight,flavour,notes,msgoncake,amount,time,amorpm,id,status,minute,deliverydate from customcake where userID = ? ";
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				CustomCake customCake = new CustomCake();
				customCake.setWeight(rs.getDouble(1));
				customCake.setFlavour(rs.getString(2));
				customCake.setNotes(rs.getString(3));
				customCake.setMsgOnCake(rs.getString(4));
				customCake.setAmount(rs.getInt(5));
				customCake.setTime(rs.getString(6));
				customCake.setAmorpm(rs.getString(7));
				customCake.setId(rs.getInt(8));
				customCake.setStatus(rs.getInt(9));
				customCake.setMinute(rs.getString(10));
				customCake.setDeliveryDate(rs.getString(11));
				
				
				list.add(customCake);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public CustomCake getCustomCake(int selectedid) throws Exception {
	
		PreparedStatement preparedStatement = null;
		CustomCake customCake = new CustomCake();
		String sql = "select weight,flavour,notes,msgoncake,amount,time,amorpm,id,status,minute,deliverydate from customcake where id = "+selectedid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				customCake.setWeight(rs.getDouble(1));
				customCake.setFlavour(rs.getString(2));
				customCake.setNotes(rs.getString(3));
				customCake.setMsgOnCake(rs.getString(4));
				customCake.setAmount(rs.getInt(5));
				customCake.setTime(rs.getString(6));
				customCake.setAmorpm(rs.getString(7));
				customCake.setId(rs.getInt(8));
				customCake.setStatus(rs.getInt(9));
				customCake.setMinute(rs.getString(10));
				customCake.setDeliveryDate(rs.getString(11));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return customCake;
	}

	public int updateCustomCake(CustomCake customCake, int selectedID)
			throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update customcake set weight=?,flavour=?,notes=?,msgoncake=?,amount=?,time=?,amorpm=?,minute=?,deliverydate=? where id = ? ";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, customCake.getWeight());
			preparedStatement.setString(2, customCake.getFlavour());
			preparedStatement.setString(3, customCake.getNotes());
			preparedStatement.setString(4, customCake.getMsgOnCake());
			preparedStatement.setInt(5, customCake.getAmount());
			preparedStatement.setString(6, customCake.getTime());
			preparedStatement.setString(7, customCake.getAmorpm());
			
			preparedStatement.setString(8, customCake.getMinute());
			preparedStatement.setString(9, customCake.getDeliveryDate());
			preparedStatement.setInt(10, selectedID);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteCustomCake(int selectedid) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from customcake where id = "+selectedid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getAdminCustomCakeCount(int branchID) throws Exception {
		String userID = getUserID(branchID); 
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from customcake where userID = ? ";
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
	
	
	public String getUserID(int branchID){
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select userid from branch where id = "+branchID+" ";
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

	public int updateStatus(int selectedID, int status) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update customcake set status = "+status+" where id = "+selectedID+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

}
