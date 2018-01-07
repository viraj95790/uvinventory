package com.inventorymanagement.main.web.action;





import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;






import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCBranchDAO;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.common.utils.Encryption;
import com.inventorymanagement.main.web.form.BranchForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;





public class LoginAction extends BaseAction implements ModelDriven<BranchForm> {
	
	
	BranchForm branchForm = new BranchForm();
	
	
	
	public String execute(){
		
		try{
			Connection connection = Connection_provider.getconnection();
		
		BranchDAO branchDAO = new JDBCBranchDAO(connection);
		
		Branch branch = branchDAO.getuser(branchForm.getUserId());
		if(!branchForm.getUserId().equals(branch.getUserId()) ){
			addActionError(getText("error.userid.notexist"));
			return INPUT;
		}
		String encPassword = Encryption.encryptSHA(branchForm.getPassword());
		if(!encPassword.equals(branch.getPassword())){
			addActionError(getText("error.user.authfailed"));
			return INPUT;
		}
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession(true);
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setId(branch.getId());
		loginInfo.setUserId(branch.getUserId());
		loginInfo.setBranchName(branch.getBranchName());
		loginInfo.setUserType(branch.getUserType());
		loginInfo.setCode(branchForm.getCode());
		
		
		
		
		session.setAttribute("logininfo", loginInfo);
		LoginHelper.saveLoginInfo(request, loginInfo);
		
		}catch (Exception e) {
 			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	

	public void validate() {
		Connection connection = null;
	    	
    	 /* Do not use 'else if' since it will cause to show only one error at a time */
    	 // If user is null or empty add error in field errors
		 if ( branchForm.getUserId() == null || branchForm.getUserId().length() == 0) {
	            addFieldError("userId", getText("error.userid.required") );	// set error message form property file
		 }
		 // If password is null or empty add error to field errors
	     if ( branchForm.getPassword() == null ||  branchForm.getPassword().length() == 0) {
	            addFieldError("password", getText("error.password.required")); 	// set error message form property file
	     }
	     
	     try {
	    	  connection = Connection_provider.getconnection();
	    	 BranchDAO branchDAO = new JDBCBranchDAO(connection);
	    	 
	    	 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	 		Calendar cal = Calendar.getInstance();
	 		
	 		String date= dateFormat.format(cal.getTime());
	    	 
	    	 String afterdate = branchDAO.getdate();
	    	 
	    	 if(date.equals(afterdate)){
	    		 
	    		 addActionError("Your software is expired! please upgrade!");
	    	 }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
		   try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	     
    }

	public BranchForm getModel() {
		// TODO Auto-generated method stub
		return branchForm;
	}

	

	
	
	

}
