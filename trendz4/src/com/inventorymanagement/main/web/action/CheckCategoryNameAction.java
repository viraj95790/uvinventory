package com.inventorymanagement.main.web.action;



import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.bi.CategoryDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCBranchDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCCategoryDAO;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;


public class CheckCategoryNameAction extends BaseAction 
			implements ServletRequestAware,ServletResponseAware{

	/** Http Servlet Request Object */
    private HttpServletRequest request;
    
    /** Http Servlet Response Object */
    private HttpServletResponse response;
    
   
	
   
	public String execute(){
		
		try{
		
			Connection connection = Connection_provider.getconnection();
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		 //   BranchDAO branchDAO = new JDBCBranchDAO(connection);
		    CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			// Get user id entered by user from request parameters
			String categoryName = (String)request.getParameter("categoryName");
			String userId= loginInfo.getUserId();
			// check if user with given user id already exist
			boolean userIdExist = categoryDAO.isCategoryExist(categoryName,userId);
			
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
