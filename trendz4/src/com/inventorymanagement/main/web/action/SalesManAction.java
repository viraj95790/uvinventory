package com.inventorymanagement.main.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

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

public class SalesManAction extends BaseAction implements Preparable, ModelDriven<SalesManForm>{
	
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
			ArrayList<SalesMan>salesManList = salesManDAO.getSalesManList(loginInfo.getUserId());
			salesManForm.setSalesManList(salesManList);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
	
		
		return SUCCESS;
	}
	
	public String save() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			SalesMan salesMan = new SalesMan();
			salesMan.setUserId(salesManForm.getUserId());
			salesMan.setFirstName(salesManForm.getFirstName());
			salesMan.setLastName(salesManForm.getLastName());
			salesMan.setMobileNo(salesManForm.getMobileNo());
			salesMan.setAddress(salesManForm.getAddress());
			salesMan.setSalaryperday(salesManForm.getSalaryperday());
			salesMan.setCommission(salesManForm.getCommission());
			
			SalesManDAO salesManDAO = new JDBCSalesManDAO(connection);
			
			int result = salesManDAO.saveSalesMan(salesMan,loginInfo.getUserId());
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "save";
	}
	
	
	public String edit() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		int selectedId = Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		
		
		try{
			connection = Connection_provider.getconnection();
			SalesManDAO salesManDAO = new JDBCSalesManDAO(connection);
			SalesMan salesMan = salesManDAO.getSalesMan(selectedId);
			
			salesManForm.setFirstName(salesMan.getFirstName());
			salesManForm.setLastName(salesMan.getLastName());
			salesManForm.setMobileNo(salesMan.getMobileNo());
			salesManForm.setUserId(salesMan.getUserId());
			salesManForm.setAddress(salesMan.getAddress());
			salesManForm.setSalaryperday(salesMan.getSalaryperday());
			salesManForm.setCommission(salesMan.getCommission());
			salesManForm.setId(salesMan.getId());
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "edit";
	}
	
	
	public String editsave() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			SalesManDAO salesManDAO = new JDBCSalesManDAO(connection);
			SalesMan salesMan = new SalesMan();
			
			salesMan.setFirstName(salesManForm.getFirstName());
			salesMan.setLastName(salesManForm.getLastName());
			salesMan.setMobileNo(salesManForm.getMobileNo());
			salesMan.setUserId(salesManForm.getUserId());
			salesMan.setAddress(salesManForm.getAddress());
			salesMan.setSalaryperday(salesManForm.getSalaryperday());
			salesMan.setCommission(salesManForm.getCommission());
			salesMan.setId(salesManForm.getId());
			
			int update  = salesManDAO.updateSaleMan(salesMan);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
			
		
		return "editsave";
	}
	
	public String delete(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			SalesManDAO salesManDAO = new JDBCSalesManDAO(connection);
			int selectedId = Integer.parseInt(request.getParameter("selectedid"));
			
			int delete  = salesManDAO.deleteEmployee(selectedId);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}

	

	public SalesManForm getModel() {
		// TODO Auto-generated method stub
		return salesManForm;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
