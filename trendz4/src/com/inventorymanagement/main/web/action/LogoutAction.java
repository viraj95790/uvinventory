package com.inventorymanagement.main.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.opensymphony.xwork2.ActionContext;

public class LogoutAction extends BaseAction{
	
	
	public String execute() throws Exception {
		

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		LoginHelper.removeLoginInfo(request);
		
		
		return SUCCESS;
	}

}
