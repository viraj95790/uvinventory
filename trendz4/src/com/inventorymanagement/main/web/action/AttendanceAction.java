package com.inventorymanagement.main.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.main.eu.bi.SalesManDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCSalesManDAO;
import com.inventorymanagement.main.eu.entity.SalesMan;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.SalesManForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class AttendanceAction extends BaseAction implements Preparable, ModelDriven<SalesManForm>{

	SalesManForm salesManForm = new SalesManForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	
	
	public String execute() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			SalesManDAO salesManDAO = new JDBCSalesManDAO(connection);
			if(salesManForm.getFromDate()!=null){
				String fromDate = DateTimeUtils.changeDateFormat(salesManForm.getFromDate());
				String toDate = DateTimeUtils.changeDateFormat(salesManForm.getToDate());
				ArrayList<SalesMan>attendanceList = salesManDAO.getAttendanceList(salesManForm.getEmployeeid(),fromDate,toDate);
				salesManForm.setAttendanceList(attendanceList);
				
				SalesMan salesMan = salesManDAO.getSalesMan(salesManForm.getEmployeeid());
				
				double totalSalary = attendanceList.size() * salesMan.getSalaryperday();
				
				salesManForm.setTotalSalary(totalSalary);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return SUCCESS;
	}
	
	
	
	public String input() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		
	
		
		return super.input();
	}
	
	public String save() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		
		
		try{
			
			connection = Connection_provider.getconnection();
			SalesManDAO salesManDAO = new JDBCSalesManDAO(connection);
			ArrayList<SalesMan>salesManList = salesManDAO.getSalesManList(loginInfo.getUserId());
			ArrayList<SalesMan>populatedSalesManList = new ArrayList<SalesMan>();
			
			for(SalesMan salesMan : salesManList){
				populatedSalesManList.add(new SalesMan(salesMan.getId(),salesMan.getFirstName()+" "+salesMan.getLastName()));
			}
			
			salesManForm.setPopulatedSalesManList(populatedSalesManList);
			salesManForm.setCurrentdatetime(DateTimeUtils.getDateinSimpleStringFormate(new Date()));
			
			SalesMan salesMan = salesManDAO.getEmployee(salesManForm.getEmployeeid());
			
			System.out.println(DateTimeUtils.getDateinSimpleStringFormate(new Date()));
			
			
			String currentdate = DateTimeUtils.getCurrentDateInMySqlDateCasting(DateTimeUtils.getDateinSimpleStringFormate(new Date()));
			
			boolean checkAttendanceExist = salesManDAO.checkAttendanceExist(currentdate,salesManForm.getEmployeeid());
			
			if(!checkAttendanceExist){
				int result = salesManDAO.saveAttendance(salesManForm.getEmployeeid());
				if(result!=0){
					addActionMessage(" "+salesMan.getFirstName()+ " "+ salesMan.getLastName()+" Your Attendance Done Successfully");
				}
			}else{
				addActionMessage(""+salesMan.getFirstName()+ " "+ salesMan.getLastName()+" Your Today's Attendance Allready Done");
			}
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return "save";
	}
	
	
	
	
	
	
	
	public SalesManForm getModel() {
		// TODO Auto-generated method stub
		return salesManForm;
	}
	
	
	public void prepare() throws Exception {
		
		Connection connection = null;
		
		
		try{
			
			connection = Connection_provider.getconnection();
			SalesManDAO salesManDAO = new JDBCSalesManDAO(connection);
			ArrayList<SalesMan>salesManList = salesManDAO.getSalesManList(loginInfo.getUserId());
			ArrayList<SalesMan>populatedSalesManList = new ArrayList<SalesMan>();
			
			for(SalesMan salesMan : salesManList){
				populatedSalesManList.add(new SalesMan(salesMan.getId(),salesMan.getFirstName()+" "+salesMan.getLastName()));
			}
			
			salesManForm.setPopulatedSalesManList(populatedSalesManList);
			salesManForm.setCurrentdatetime(DateTimeUtils.getDateinSimpleStringFormate(new Date()));
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	}
	

}
