package com.inventorymanagement.main.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.bi.CustomCakeDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCBranchDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCCustomCakeDAO;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.CustomCake;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.CustomCakeForm;
import com.inventorymanagement.main.web.utils.PopulateList;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class CustomCakeAction extends BaseAction implements Preparable, ModelDriven<CustomCakeForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	CustomCakeForm customCakeForm = new CustomCakeForm();
	private Pagination pagination = new Pagination(5, 1);



	public String execute() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection  = Connection_provider.getconnection();
			CustomCakeDAO customCakeDAO = new JDBCCustomCakeDAO(connection);
			
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			
			if(loginInfo.getUserType()==1){
				getadminDetails();
				
				int selectedBranch = (Integer)session.getAttribute("selectedBranchID");
				System.out.println(selectedBranch);
				
				
				//pagination
				int totalCount = customCakeDAO.getAdminCustomCakeCount(selectedBranch);
				pagination.setPreperties(totalCount);
				String userId = customCakeDAO.getUserID(selectedBranch);
				ArrayList<CustomCake>customCakeList = customCakeDAO.getCustomCakeList(userId,pagination);
				customCakeForm.setCustomCakeList(customCakeList);
				pagination.setPage_records(customCakeList.size());
				customCakeForm.setTotalRecords(totalCount);
			}
			else{
				//pagination
				int totalCount = customCakeDAO.getCustomCakeCount(loginInfo.getUserId());
				pagination.setPreperties(totalCount);
				ArrayList<CustomCake>customCakeList = customCakeDAO.getCustomCakeList(loginInfo.getUserId(),pagination);
				customCakeForm.setCustomCakeList(customCakeList);
				pagination.setPage_records(customCakeList.size());
				customCakeForm.setTotalRecords(totalCount);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return SUCCESS;
	}
	
	
	public String set(){
		int branchID = Integer.parseInt(request.getParameter("id"));
		session.setAttribute("selectedBranchID", branchID);
		System.out.println(branchID);
		
		return null;
	}
	
	public String changestatus(){
		
		int selectedID = Integer.parseInt(request.getParameter("selectedid"));
		int status = Integer.parseInt(request.getParameter("status"));
		int selectedBranch = (Integer)session.getAttribute("selectedBranchID");
		System.out.println(selectedBranch);
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			CustomCakeDAO customCakeDAO = new JDBCCustomCakeDAO(connection);
			if(status == 0){
				int result = customCakeDAO.updateStatus(selectedID,1);
			}
			if(status == 1){
				int result = customCakeDAO.updateStatus(selectedID,0);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return "changestatus";
	}
	
	
	public void getadminDetails(){
		//set branchlist
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			session.setAttribute("selectedBranch", "0");
			BranchDAO branchDAO = new JDBCBranchDAO(connection);
			ArrayList<Branch>BranchList = branchDAO.getBranch();
			ArrayList<Branch>populateBranchList = new ArrayList<Branch>();
			
			for(Branch branch : BranchList){
				populateBranchList.add(new Branch(branch.getId(),branch.getBranchName()));
				
			}
			customCakeForm.setBranchList(populateBranchList);
			if(session.getAttribute("selectedBranchID")!=null){
				int selectedBranch = (Integer)session.getAttribute("selectedBranchID");
				customCakeForm.setBranchID(selectedBranch);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			

		
	}
	
	
	
	public String save(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			CustomCake customCake = new CustomCake();
			customCake.setWeight(customCakeForm.getWeight());
			customCake.setFlavour(customCakeForm.getFlavour());
			customCake.setNotes(customCakeForm.getNotes());
			customCake.setMsgOnCake(customCakeForm.getMsgOnCake());
			customCake.setAmount(customCakeForm.getAmount());
			customCake.setTime(customCakeForm.getTime());
			customCake.setAmorpm(customCakeForm.getAmorpm());
			customCake.setMinute(customCakeForm.getMinute());
			customCake.setDeliveryDate(customCakeForm.getDeliveryDate());
			
			CustomCakeDAO customCakeDAO = new JDBCCustomCakeDAO(connection);
			int result = customCakeDAO.saveCustomCake(customCake,loginInfo.getUserId());
			
	
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "save";
	}
	
	
	public String edit(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			int selectedid = Integer.parseInt(request.getParameter("selectedid"));
			CustomCakeDAO  customCakeDAO  = new JDBCCustomCakeDAO(connection);
			CustomCake customCake = customCakeDAO.getCustomCake(selectedid);
			setFormData(customCake);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return "edit";
	}
	
	public void setFormData(CustomCake customCake){
		customCakeForm.setId(customCake.getId());
		customCakeForm.setWeight(customCake.getWeight());
		customCakeForm.setFlavour(customCake.getFlavour());
		customCakeForm.setNotes(customCake.getNotes());
		customCakeForm.setMsgOnCake(customCake.getMsgOnCake());
		customCakeForm.setAmount(customCake.getAmount());	
		customCakeForm.setTime(customCake.getTime());
		customCakeForm.setAmorpm(customCake.getAmorpm());
		customCakeForm.setMinute(customCake.getMinute());
		customCakeForm.setDeliveryDate(customCake.getDeliveryDate());
	}
	
	
	public String editsave(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			int selectedID = customCakeForm.getId();
			CustomCake customCake = new CustomCake();
			customCake.setWeight(customCakeForm.getWeight());
			customCake.setFlavour(customCakeForm.getFlavour());
			customCake.setNotes(customCakeForm.getNotes());
			customCake.setMsgOnCake(customCakeForm.getMsgOnCake());
			customCake.setAmount(customCakeForm.getAmount());
			customCake.setTime(customCakeForm.getTime());
			customCake.setAmorpm(customCakeForm.getAmorpm());
			customCake.setMinute(customCakeForm.getMinute());
			customCake.setDeliveryDate(customCakeForm.getDeliveryDate());
			
			CustomCakeDAO customCakeDAO = new JDBCCustomCakeDAO(connection);
			
			int result = customCakeDAO.updateCustomCake(customCake,selectedID);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return "editsave";
	}
	
	 public String delete(){
		 int selectedid = Integer.parseInt(request.getParameter("selectedid"));
		 if(!verifyLogin(request)){
				return "login";
			}
			Connection connection = null;
			try{
				
				connection = Connection_provider.getconnection();
				CustomCakeDAO customCakeDAO = new JDBCCustomCakeDAO(connection);
				int result = customCakeDAO.deleteCustomCake(selectedid);
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 
		 return "delete";
	 }
	
	

	public CustomCakeForm getModel() {
		
		return customCakeForm;
	}

	public void prepare() throws Exception {
		customCakeForm.setTimeList(PopulateList.getTimeList());
		customCakeForm.setAmorpmList(PopulateList.getomorpmList());
		customCakeForm.setMinuteList(PopulateList.getMinuteList());
		
	}
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}



}
