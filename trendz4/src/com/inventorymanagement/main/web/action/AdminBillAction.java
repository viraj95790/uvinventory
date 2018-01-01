 package com.inventorymanagement.main.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.eu.bi.AdminBillDAO;
import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.bi.CategoryDAO;
import com.inventorymanagement.main.eu.bi.ProductDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCAdminBillDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCBranchDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCProductDAO;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Product;

import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.AdminBillForm;
import com.inventorymanagement.main.web.form.ProductForm;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class AdminBillAction extends BaseAction implements ModelDriven<AdminBillForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	
	ProductForm productForm = new ProductForm();
	
	AdminBillForm adminBillForm =new AdminBillForm();
	private Pagination pagination = new Pagination(10, 1);
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}


	public String execute() throws Exception {
		if(!verifyLogin(request)){
			return "login"; 
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			getadminDetails();
			System.out.println("Branch id: "+adminBillForm.getBranchID());
			AdminBillDAO adminBillDAO = new JDBCAdminBillDAO(connection);
			int branchId = adminBillForm.getBranchID();
			
			String branchname = adminBillDAO.getBranchName(branchId);
			
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ArrayList<Category>categoryWiseProductList = new ArrayList<Category>();
			ProductDAO productDAO  = new JDBCProductDAO(connection);
			
			ArrayList<Product>productList = new ArrayList<Product>();
			
			ArrayList<Category>categoryList = adminBillDAO.getCategoryList();
			adminBillForm.setCategoryList(categoryList);
			for(Category category : categoryList){
				productList = adminBillDAO.getProductList(category.getId(),branchname);
				category.setProductList(productList);
				categoryWiseProductList.add(category);
			}
			
			 for(Category category : categoryWiseProductList){
				 System.out.println(category.getCategoryName());
				 for(Product product : category.getProductList()){
					 System.out.println(product.getProductName());
				 }
			 }
			
			 adminBillForm.setCategoryWiseProductList(categoryWiseProductList);
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
		if(adminBillForm.getActionType().equals("go")){
			
			return "print";
		}
		
		return SUCCESS;
	}
	
	public void getadminDetails(){
		//set branchlist
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			//session.setAttribute("selectedBranch", "0");
			BranchDAO branchDAO = new JDBCBranchDAO(connection);
			ArrayList<Branch>BranchList = branchDAO.getBranch();
			ArrayList<Branch>populateBranchList = new ArrayList<Branch>();
			
			for(Branch branch : BranchList){
				populateBranchList.add(new Branch(branch.getId(),branch.getBranchName()));
				
			}
			adminBillForm.setBranchList(populateBranchList);
		}catch (Exception e) {
			e.printStackTrace();
		}
			

		
	}
	
	
	
	
	
	public String input() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ProductDAO productDAO = new JDBCProductDAO(connection);
			ArrayList<Category>categoryList = productDAO.getCategoryList(loginInfo.getUserId());
			ArrayList<Category>populateCategoryList = new ArrayList<Category>();
			for(Category category : categoryList){
				populateCategoryList.add(new Category(category.getId(),category.getCategoryName()));
			}
			
			productForm.setCategoryList(populateCategoryList);
		}catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			connection.close();
		}
	
		return INPUT;
	}
	
	
	

	
	public AdminBillForm getModel() {
		
		return adminBillForm;
	}


	


}
