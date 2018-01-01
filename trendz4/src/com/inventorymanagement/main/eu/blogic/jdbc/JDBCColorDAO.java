package com.inventorymanagement.main.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.inventorymanagement.main.eu.bi.ColorDAO;
import com.inventorymanagement.main.eu.entity.Color;

public class JDBCColorDAO extends JDBCBaseDAO implements ColorDAO{
	
	public JDBCColorDAO(Connection connection){
		this.connection = connection;
	}

	public boolean checkColorExist(String colorName) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from color where colorname = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, colorName);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int saveColor(Color color) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into color(colorname,description) values(?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, color.getColorName());
			preparedStatement.setString(2, color.getDescription());
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Color> getColorList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Color>list = new ArrayList<Color>();
		String sql = "SELECT id,colorname,description FROM color ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Color color = new Color();
				color.setId(rs.getInt(1));
				color.setColorName(rs.getString(2));
				color.setDescription(rs.getString(3));
				list.add(color);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public Color getColor(int selectedid) {
		PreparedStatement preparedStatement = null;
		Color color = new Color();
		String sql = "select id,colorname,description from color where id = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				color.setId(rs.getInt(1));
				color.setColorName(rs.getString(2));
				color.setDescription(rs.getString(3));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return color;
	}

	public int updateColor(Color color) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update color set colorname=?,description=? where id = ? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql) ;
			preparedStatement.setString(1, color.getColorName());
			preparedStatement.setString(2, color.getDescription());
			preparedStatement.setInt(3, color.getId());
			
			result = preparedStatement.executeUpdate(); 
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteColor(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from color where id = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
