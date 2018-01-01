package com.inventorymanagement.main.web.action;



import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.bi.CategoryDAO;
import com.inventorymanagement.main.eu.bi.ProductDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCBranchDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCCategoryDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCProductDAO;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.ProductForm;
import com.opensymphony.xwork2.ModelDriven;


public class CheckProductNameAction extends BaseAction implements ServletRequestAware,ServletResponseAware{

	/** Http Servlet Request Object */
    private HttpServletRequest request;
    
    /** Http Servlet Response Object */
    private HttpServletResponse response;
    HttpSession session = request.getSession(true);
    
    ProductForm productForm = new ProductForm();
   
   
	public String execute(){
		
		try{
		
			Connection connection = Connection_provider.getconnection();
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ProductDAO productDAO = new JDBCProductDAO(connection);
			int selectedCategoryID=0;
		    
			if(session.getAttribute("selectedCategoryID")!=null){
				selectedCategoryID = (Integer)session.getAttribute("selectedCategoryID");
				System.out.println(selectedCategoryID);
			}
			int categoryId = Integer.parseInt(request.getParameter("categoryID"));
			
			String productName = (String)request.getParameter("productName");
			String userId= loginInfo.getUserId();
			// check if user with given user id already exist
			boolean userIdExist = productDAO.isProductExist(productName,selectedCategoryID);
			
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
