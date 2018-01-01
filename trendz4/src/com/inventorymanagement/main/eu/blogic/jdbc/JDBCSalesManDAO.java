package com.inventorymanagement.main.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.main.eu.bi.CustomerDAO;
import com.inventorymanagement.main.eu.bi.SalesManDAO;
import com.inventorymanagement.main.eu.entity.Attendance;
import com.inventorymanagement.main.eu.entity.Customer;
import com.inventorymanagement.main.eu.entity.SalesMan;

public class JDBCSalesManDAO extends JDBCBaseDAO implements SalesManDAO {

	public JDBCSalesManDAO(Connection connection){
		this.connection = connection;
	}

	public ArrayList<SalesMan> getSalesManList(String userID) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<SalesMan>list = new ArrayList<SalesMan>();
		String sql = "select id, firstname,lastname,mobileno,address,employeeid,salaryperday,commission from salesman where userid = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				SalesMan salesMan = new SalesMan();
				salesMan.setId(rs.getInt(1));
				salesMan.setFirstName(rs.getString(2));
				salesMan.setLastName(rs.getString(3));
				salesMan.setMobileNo(rs.getString(4));
				salesMan.setAddress(rs.getString(5));
				salesMan.setUserId(rs.getString(6));
				salesMan.setSalaryperday(rs.getDouble(7));
				salesMan.setCommission(rs.getString(8));
				
				list.add(salesMan);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int saveSalesMan(SalesMan salesMan, String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into salesman(firstname,lastname,mobileno,address,employeeid,salaryperday,userid,commission) values(?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, salesMan.getFirstName());
			preparedStatement.setString(2, salesMan.getLastName());
			preparedStatement.setString(3, salesMan.getMobileNo());
			preparedStatement.setString(4, salesMan.getAddress());
			preparedStatement.setString(5, salesMan.getUserId());
			preparedStatement.setDouble(6, salesMan.getSalaryperday());
			preparedStatement.setString(7, userid);
			preparedStatement.setString(8, salesMan.getCommission());
			
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public SalesMan getEmployee(int employeeid) {
		
		PreparedStatement preparedStatement = null;
		SalesMan salesMan = new SalesMan();
		String sql = "select firstname,lastname,mobileno,address,employeeid,salaryperday from salesman where id = "+employeeid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				salesMan.setFirstName(rs.getString(1));
				salesMan.setLastName(rs.getString(2));
				salesMan.setMobileNo(rs.getString(3));
				salesMan.setAddress(rs.getString(4));
				salesMan.setUserId(rs.getString(5));
				salesMan.setSalaryperday(rs.getDouble(6));
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return salesMan;
	}

	public int saveAttendance(int employeeid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into attendance (employeeid,currentdatetime,lastupdated) values(?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, employeeid);
			preparedStatement.setString(2, DateTimeUtils.getDateinSimpleFormate(new Date()));
			preparedStatement.setTimestamp(3,DateTimeUtils.getCurrentDateInSQLCasting());
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean checkAttendanceExist(String currentdate, int employeeid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from attendance where lastupdated = ? and employeeid = ?";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, currentdate);
			preparedStatement.setInt(2, employeeid);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<SalesMan> getAttendanceList(int employeeid,String fromDate,String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<SalesMan>list = new ArrayList<SalesMan>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT firstname,lastname,currentdatetime  FROM attendance inner join salesman on ");
		sql.append("salesman.id = attendance.employeeid where attendance.employeeid = "+employeeid+" and lastupdated between ? and ? ");
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, fromDate);
			preparedStatement.setString(2, toDate);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				SalesMan salesMan = new SalesMan();
				salesMan.setFirstName(rs.getString(1));
				salesMan.setLastName(rs.getString(2));
				salesMan.setCurrentDateTime(rs.getString(3));
				
				list.add(salesMan);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		

		return list;
	}

	public SalesMan getSalesMan(int selectedId) {
		PreparedStatement preparedStatement = null;
		SalesMan salesMan = new SalesMan();
		String sql = "SELECT firstname,lastname,mobileno,salaryperday,employeeid,address,id,commission FROM salesman where id = "+selectedId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				salesMan.setFirstName(rs.getString(1));
				salesMan.setLastName(rs.getString(2));
				salesMan.setMobileNo(rs.getString(3));
				salesMan.setSalaryperday(rs.getDouble(4));
				salesMan.setUserId(rs.getString(5));
				salesMan.setAddress(rs.getString(6));
				salesMan.setId(rs.getInt(7));
				salesMan.setCommission(rs.getString(8));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return salesMan;
	}

	public int updateSaleMan(SalesMan salesMan) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update salesman set firstname=?,lastname=?,mobileno=?,salaryperday=?,employeeid=?,address=?,commission=? where id=?";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, salesMan.getFirstName());
			preparedStatement.setString(2, salesMan.getLastName());
			preparedStatement.setString(3, salesMan.getMobileNo());
			preparedStatement.setDouble(4, salesMan.getSalaryperday());
			preparedStatement.setString(5, salesMan.getUserId());
			preparedStatement.setString(6, salesMan.getAddress());
			preparedStatement.setString(7, salesMan.getCommission());
			preparedStatement.setInt(8, salesMan.getId());
			
			
			result = preparedStatement.executeUpdate();
		
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public int deleteEmployee(int selectedId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from salesman where id = "+selectedId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Customer> getCustomerList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Customer>list = new ArrayList<Customer>();
		String sql = "SELECT id,name,usertype,surname FROM customer_form  ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Customer customer = new Customer();
				customer.setId(rs.getInt(1));
				CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
				Customer customer1 = customerDAO.editdata(rs.getString(1));
				if(rs.getInt(3)==0){
					customer.setName(rs.getString(2) + " " + rs.getString(4) + " (Customer - "+customer1.getCustomer_type()+")");
				}else{
					customer.setName(rs.getString(2) + " (Vendor)");
				}
				
				list.add(customer);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<SalesMan> getSalesmanAttendanceList(String fromDate,
			String toDate,String userid) {
		PreparedStatement preparedStatement = null;
		ArrayList<SalesMan>list = new ArrayList<SalesMan>();
		String sql = "SELECT salesman.id,firstname,lastname FROM salesman inner join attendance on attendance.employeeid = salesman.id where"
				+ " lastupdated between '"+fromDate+"' and '"+toDate+"' and userid='"+userid+"' group by id ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				SalesMan salesMan = new SalesMan();
				salesMan.setId(rs.getInt(1));
				salesMan.setFirstName(rs.getString(1));
				salesMan.setLastName(rs.getString(2));
				
				ArrayList<Attendance>attandanceList = getAttaendanceList(salesMan.getId(),fromDate,toDate);
				salesMan.setAttandanceList(attandanceList);
				
				list.add(salesMan);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	private ArrayList<Attendance> getAttaendanceList(int id,String fromdate,String todate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Attendance>list = new ArrayList<Attendance>();
		String sql = "SELECT currentdatetime FROM attendance where employeeid = "+id+" and lastupdated between '"+fromdate+"' and '"+todate+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Attendance attendance = new Attendance();
				attendance.setDate(rs.getString(1));
				
				list.add(attendance);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
}
