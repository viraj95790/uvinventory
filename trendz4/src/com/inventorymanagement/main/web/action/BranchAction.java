package com.inventorymanagement.main.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCBranchDAO;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.web.form.BranchForm;
import com.inventorymanagement.main.web.utils.PopulateData;
import com.inventorymanagement.main.web.utils.PopulateList;



import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


public class BranchAction extends BaseAction implements Preparable,  ModelDriven<BranchForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	BranchForm branchForm = new BranchForm();
	public String execute() throws Exception {
		try{
			Connection connection = Connection_provider.getconnection();
			
			
	
			BranchDAO branchDAO =new JDBCBranchDAO(connection);
		
			ArrayList<Branch> branchList = new ArrayList<Branch>();
			 branchList = branchDAO.getBranch();
			branchForm.setBranchList(branchList);
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		
		return "success";
	}
	
	public String save()
	{
		try
		{
			Connection connection = Connection_provider.getconnection();
			BranchDAO branchDAO =new JDBCBranchDAO(connection);
			
			
			
			Branch branch =new Branch();
			
			branch.setId(branchForm.getId());
			branch.setUserId(branchForm.getUserId());
			branch.setPassword(branchForm.getPassword());
			
			branch.setCompanyName(branchForm.getCompanyName());
			branch.setCountry(branchForm.getCountry());
			branch.setState(branchForm.getState());
			branch.setCity(branchForm.getCity());
			branch.setAddress(branchForm.getAddress());
			branch.setPincode(branchForm.getPinCode());
			branch.setMobileNo(branchForm.getMobileNo());
			branch.setLandLine(branchForm.getLandLine());
			branch.setEmail(branchForm.getEmail());
			branch.setUserType(branchForm.getUserType());
			
			int result=branchDAO.saveBranch(branch);
	
			if(branchForm.getUserType()==2) {
				return execute();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "save";
	} 
	public String input(){
		branchForm.setUserType(1);
		return "input";
	}
	public String inputShop(){
		branchForm.setUserType(2);
		return "inputshop";	
	}
	
	public String edit()
	{
		try{
		Connection connection = Connection_provider.getconnection();

		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
		int selectedid= Integer.parseInt(request.getParameter("selectedid"));
		
		BranchDAO branchDAO =new JDBCBranchDAO(connection);
		Branch branch=branchDAO.getBranch(selectedid);
		branchForm.setId(branch.getId());
		branchForm.setUserId(branch.getUserId());
		branchForm.setBranchName(branch.getBranchName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "edit";
		
	}
	
	public String update()
	{
		try{
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
			String userID = request.getParameter("userId");
			
			Connection connection = Connection_provider.getconnection();

			
			BranchDAO branchDAO =new JDBCBranchDAO(connection);
			
			Branch branch =new Branch();
		
			branch.setUserId(branchForm.getUserId());
			branch.setBranchName(branchForm.getBranchName());
			
			
			int result=branchDAO.updateBranch(branch,userID);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "update";
	}
	
public String delete(){
		
		try{
			Connection connection = Connection_provider.getconnection();
			
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
			int selectedid= Integer.parseInt(request.getParameter("selectedid"));
			BranchDAO branchDAO =new JDBCBranchDAO(connection);
			
			
			
			int result = branchDAO.deleteBranch(selectedid);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "delete";
	}

public String set(){
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession(true);
		Connection connection;
		try{
			connection = Connection_provider.getconnection();
			BranchDAO branchDAO = new JDBCBranchDAO(connection);
			if(id!=0){
				Branch branch = branchDAO.getBranch(id);
				session.setAttribute("selectedBranch", branch.getUserId());
				session.setAttribute("selectedBranchID", id);
			}else{
				session.setAttribute("selectedBranch", "0");
				session.setAttribute("selectedBranchID", id);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
	return null;
}
public String getedit() throws Exception{
	Connection connection = null;
	String selectedid = request.getParameter("selectedid");
	try {
		 connection = Connection_provider.getconnection();
		BranchDAO branchDAO =new JDBCBranchDAO(connection);
		Branch branch = branchDAO.editdata(selectedid);
		
		branchForm.setId(branch.getId());
		branchForm.setCompanyName(branch.getCompanyName());
		branchForm.setCountry(branch.getCountry());
		branchForm.setState(branch.getState());
		branchForm.setCity(branch.getCity());
		branchForm.setAddress(branch.getAddress());
		branchForm.setPinCode(branch.getPincode());
		branchForm.setMobileNo(branch.getMobileNo());
		branchForm.setLandLine(branch.getLandLine());
		branchForm.setEmail(branch.getEmail());
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally{
		connection.close();
	}
	
	return "getedit";
	
	}
 public String getupdate() throws Exception{
	 Connection connection = null;
	 try {
		  connection = Connection_provider.getconnection();
			BranchDAO branchDAO =new JDBCBranchDAO(connection);
			Branch branch = new Branch();
			
			branch.setId(branchForm.getId());
			branch.setCompanyName(branchForm.getCompanyName());
			branch.setCountry(branchForm.getCountry());
			branch.setState(branchForm.getState());
			branch.setCity(branchForm.getCity());
			branch.setAddress(branchForm.getAddress());
			branch.setPincode(branchForm.getPinCode());
			branch.setMobileNo(branchForm.getMobileNo());
			branch.setLandLine(branchForm.getLandLine());
			branch.setEmail(branchForm.getEmail());
			
			int result = branchDAO.updatedata(branch);
			
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return execute();
 }  
 
     public String getdelete() throws Exception{
    	 Connection connection = null;
    	 String selectedid = request.getParameter("selectedid");
    	 try {
    		 connection = Connection_provider.getconnection();
 			BranchDAO branchDAO =new JDBCBranchDAO(connection);
 			
 			int result = branchDAO.deletedata(selectedid);
    		 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return execute();
     }

	

	public BranchForm getModel() {
		// TODO Auto-generated method stub
		return branchForm;
	}

	public void prepare() throws Exception {
		
		branchForm.setCountryList(PopulateList.countryList());
		branchForm.setStateList(PopulateList.stateList());
		branchForm.setCityList(PopulateList.cityList());
		
	}

}
