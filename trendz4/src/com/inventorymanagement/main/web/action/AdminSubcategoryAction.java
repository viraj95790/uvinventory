package com.inventorymanagement.main.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.eu.bi.AdminSubcategoryDAO;
import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.bi.CategoryDAO;
import com.inventorymanagement.main.eu.bi.ProductDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCAdminSubcategoryDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCBranchDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCCategoryDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCProductDAO;
import com.inventorymanagement.main.eu.entity.AdminSubcategory;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.AdminSubcategoryForm;
import com.inventorymanagement.main.web.form.CategoryForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class AdminSubcategoryAction extends BaseAction implements ModelDriven<AdminSubcategoryForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	//CategoryForm categoryForm = new CategoryForm();
	AdminSubcategoryForm adminSubcategoryForm = new AdminSubcategoryForm();
	Connection connection = null;
	private Pagination pagination = new Pagination(5, 1);

	public String execute() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		
		try{
			
			
			
			connection = Connection_provider.getconnection();
			
			AdminSubcategoryDAO adminSubcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			
			int totalCount = adminSubcategoryDAO.getSubCategoryCount(adminSubcategoryForm.getSearchText(),loginInfo.getUserId());
					pagination.setPreperties(totalCount);
					ArrayList<AdminSubcategory>subCategoryList = adminSubcategoryDAO.getSubCategoryList(pagination,adminSubcategoryForm.getSearchText(),loginInfo.getUserId());
					adminSubcategoryForm.setSubCategoryList(subCategoryList);
					pagination.setPage_records(subCategoryList.size());
					
					adminSubcategoryForm.setTotalRecords(totalCount);
				
					adminSubcategoryForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
	
	
		return SUCCESS;
	}
	
	
	
	
	
	
	/*public String save(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		try{
			String selectedBranch = "";
			if(session.getAttribute("selectedBranch")!=null){
				selectedBranch = (String)session.getAttribute("selectedBranch");
				System.out.println(selectedBranch);
			}
			
			connection = Connection_provider.getconnection();
			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			Category category = new Category();
			
			category.setCategoryName(categoryForm.getCategoryName());
			category.setDescription(categoryForm.getDescription());
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			if(loginInfo.getUserType()==1){
				if(selectedBranch.equals("0")){
					selectedBranch = "administrator";
				}
				System.out.println("admin");
				int result = categoryDAO.saveCategory(category,selectedBranch);
			}else{
				int result = categoryDAO.saveCategory(category,loginInfo.getUserId());
				System.out.println("admin not");
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		return "save";
	}*/
	
	public String edit()
	{
		try{
			
			Connection connection = Connection_provider.getconnection();
			
			
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ProductDAO productDAO = new JDBCProductDAO(connection);
			AdminSubcategoryDAO adminSubcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
			ArrayList<Category>categoryList = productDAO.getAdminCategoryList();
			ArrayList<Category>populateCategoryList = new ArrayList<Category>();
			for(Category category : categoryList){
				populateCategoryList.add(new Category(category.getId(),category.getCategoryName()));
			}
			
			adminSubcategoryForm.setCategoryList(populateCategoryList);
			
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
			int selectedid= Integer.parseInt(request.getParameter("selectedid"));
			
			AdminSubcategory adminSubcategory = adminSubcategoryDAO.getSubcategory(selectedid);
			adminSubcategoryForm.setId(adminSubcategory.getId());
			adminSubcategoryForm.setCategoryID(adminSubcategory.getCategoryID());
			adminSubcategoryForm.setDescription(adminSubcategory.getDescription());
			adminSubcategoryForm.setProductName(adminSubcategory.getProductName());
			adminSubcategoryForm.setPrice(adminSubcategory.getPrice());
			adminSubcategoryForm.setProfitmargin(adminSubcategory.getProfitmargin());
			adminSubcategoryForm.setCgst(adminSubcategory.getCgst());
			adminSubcategoryForm.setSgst(adminSubcategory.getSgst());
			adminSubcategoryForm.setGender(adminSubcategory.getGender());
			
			
			
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
			//Category category = new Category();
			AdminSubcategory adminSubcategory = new AdminSubcategory();
			int selectedid = adminSubcategoryForm.getId();
			
			Connection connection = Connection_provider.getconnection();
			AdminSubcategoryDAO adminSubcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
			
			
			adminSubcategory.setCategoryID(adminSubcategoryForm.getCategoryID());
			adminSubcategory.setCategoryName(adminSubcategoryForm.getCategoryName());
			adminSubcategory.setProductName(adminSubcategoryForm.getProductName());
			adminSubcategory.setDescription(adminSubcategoryForm.getDescription());
			adminSubcategory.setPrice(adminSubcategoryForm.getPrice());
			adminSubcategory.setProfitmargin(adminSubcategoryForm.getProfitmargin());
			adminSubcategory.setCgst(adminSubcategoryForm.getCgst());
			adminSubcategory.setSgst(adminSubcategoryForm.getSgst());
			adminSubcategory.setGender(adminSubcategoryForm.getGender());
			
			
			int result=adminSubcategoryDAO.updateAdminSubcategory(adminSubcategory,selectedid);
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
			AdminSubcategoryDAO adminSubcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
			ProductDAO productDAO = new JDBCProductDAO(connection);
			
			int deletepsize = productDAO.deletePsize(selectedid);
			int deleteSubcategory = adminSubcategoryDAO.deleteSubCategory(selectedid);
			int result = adminSubcategoryDAO.deleteProduct(selectedid);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "delete";
	}

	public String price(){
		
		int categoryID = 0;
		if(Integer.parseInt(request.getParameter("categoryD"))!=0){
			 categoryID = Integer.parseInt(request.getParameter("categoryD"));
		}else{
			categoryID = (Integer)session.getAttribute("categoryID");
		}
		
		int subCategoryID = Integer.parseInt(request.getParameter("subcategoryID"));
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AdminSubcategoryDAO adminSubcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
			double price = adminSubcategoryDAO.getAdminProductPrice(categoryID,subCategoryID);
			
			StringBuffer str = new StringBuffer();
			
			str.append("<input type = 'text' name = 'price' id = 'price' value = '"+price+"' readonly='readonly'>");
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		return null;
	}
	

	
	
		
	public AdminSubcategoryForm getModel() {
	
		return adminSubcategoryForm;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}
