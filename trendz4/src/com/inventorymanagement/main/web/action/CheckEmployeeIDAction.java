package com.inventorymanagement.main.web.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCBranchDAO;

public class CheckEmployeeIDAction extends BaseAction 
			implements ServletRequestAware,ServletResponseAware{

	/** Http Servlet Request Object */
    private HttpServletRequest request;
    
    /** Http Servlet Response Object */
    private HttpServletResponse response;
    
   
	
   
	public String execute(){
		
		try{
		
			Connection connection = Connection_provider.getconnection();
			
		    BranchDAO branchDAO = new JDBCBranchDAO(connection);
			// Get user id entered by user from request parameters
			String userid = (String)request.getParameter("employeeid");
		
			// check if user with given user id already exist
			boolean userIdExist = branchDAO.isEmployeeExist(userid);
			
			// if user id already exist then set response 'false'
			if(userIdExist){
				response.getWriter().write("false");
			}else{	// else set response 'true'
				response.getWriter().write("true");
			}

		}catch (Exception be) {
			be.printStackTrace();
		}
		
		
		return null;
	}
	
    public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	    
    }

    public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	    
    }


}
