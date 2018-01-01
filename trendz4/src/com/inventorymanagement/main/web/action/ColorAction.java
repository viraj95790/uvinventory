package com.inventorymanagement.main.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.inventorymanagement.main.eu.bi.ColorDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCColorDAO;
import com.inventorymanagement.main.eu.entity.Color;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.ColorForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class ColorAction extends BaseAction implements ModelDriven<ColorForm>{
	
	ColorForm colorForm = new ColorForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	@SkipValidation
	public String execute() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			ColorDAO colorDAO = new JDBCColorDAO(connection);
			ArrayList<Color>colorList = colorDAO.getColorList();
			
			colorForm.setColorList(colorList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return SUCCESS;
	}
	
	
	public String save(){
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ColorDAO colorDAO = new JDBCColorDAO(connection);
			Color color = new Color();
			
			color.setColorName(colorForm.getColorName());
			color.setDescription(colorForm.getDescription());
			
			int result = colorDAO.saveColor(color);
			
			addActionMessage("Colorname saved successfully!!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return "save";
	}
	
	@SkipValidation
	public String edit(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			int selectedid = Integer.parseInt(request.getParameter("selectedid"));
			connection = Connection_provider.getconnection();
			ColorDAO colorDAO = new JDBCColorDAO(connection);
			Color color = colorDAO.getColor(selectedid);
			
			colorForm.setId(color.getId());
			colorForm.setColorName(color.getColorName());
			colorForm.setDescription(color.getDescription());
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "edit";
	}
	
	@SkipValidation
	public String editsave(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			ColorDAO colorDAO = new JDBCColorDAO(connection);
			
			Color color = new Color();
			
			color.setId(colorForm.getId());
			color.setColorName(colorForm.getColorName());
			color.setDescription(color.getDescription());
			
			int update = colorDAO.updateColor(color);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "editsave";
	}
	
	@SkipValidation
	public String delete(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			int selectedid = Integer.parseInt(request.getParameter("selectedid"));
			connection = Connection_provider.getconnection();
			ColorDAO colorDAO = new JDBCColorDAO(connection);
			
			int delete = colorDAO.deleteColor(selectedid);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "delete";
	}
	

	public ColorForm getModel() {
		// TODO Auto-generated method stub
		return colorForm;
	}
	
	
	
	
	public void validate() {
		
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			ColorDAO colorDAO = new JDBCColorDAO(connection);
			boolean checkColorExist = colorDAO.checkColorExist(colorForm.getColorName());
			if(checkColorExist){
				addActionError("This color name allready exist!!");
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
		
		
		
	}
	

}
