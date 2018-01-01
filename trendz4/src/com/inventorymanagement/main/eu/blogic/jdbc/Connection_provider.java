package com.inventorymanagement.main.eu.blogic.jdbc;







import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connection_provider {
public static Connection getconnection() throws SQLException
{
	Connection con=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/garments","root","mydb");
		
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/trendz","root","mydb");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	return con;
}
}
