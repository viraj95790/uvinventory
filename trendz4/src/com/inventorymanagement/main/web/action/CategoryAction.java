package com.inventorymanagement.main.web.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.objectweb.asm.xwork.tree.TryCatchBlockNode;

import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.bi.CategoryDAO;
import com.inventorymanagement.main.eu.bi.ProductDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCBranchDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCCategoryDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCProductDAO;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.CategoryForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class CategoryAction extends BaseAction implements Preparable, ModelDriven<CategoryForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	CategoryForm categoryForm = new CategoryForm();
	Connection connection = null;
	private Pagination pagination = new Pagination(5, 1);

	@SkipValidation
	public String execute() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		String selectedBranch = "";
		try{
			
			
			
			connection = Connection_provider.getconnection();
			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			
			if(session.getAttribute("selectedBranch")!=null){
				selectedBranch = (String)session.getAttribute("selectedBranch");
				System.out.println(selectedBranch);
			}
			
			
					//pagination
					int totalCount = categoryDAO.getCategoryCount(categoryForm.getSearchText(),loginInfo.getUserId());
					pagination.setPreperties(totalCount);
					ArrayList<Category>categoryList = categoryDAO.getCategoryList(pagination,categoryForm.getSearchText(),loginInfo.getUserId());
					categoryForm.setCategoryList(categoryList);
					pagination.setPage_records(categoryList.size());
					
					categoryForm.setTotalRecords(totalCount);
				
			categoryForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
	
	
		return SUCCESS;
	}
	
	
	
	public void getadminDetails(){
		
		//set branchlist
		
			session.setAttribute("selectedBranch", "0");
			BranchDAO branchDAO = new JDBCBranchDAO(connection);
			ArrayList<Branch>BranchList = branchDAO.getBranch();
			ArrayList<Branch>populateBranchList = new ArrayList<Branch>();
			
			for(Branch branch : BranchList){
				populateBranchList.add(new Branch(branch.getId(),branch.getBranchName()));
				
			}
			categoryForm.setBranchList(populateBranchList);

		
	}
	
	
	public String save(){
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
			category.setCategorytype(categoryForm.getCategorytype());
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
	}
	
	@SkipValidation
	public String edit()
	{
		if(!verifyLogin(request)){
			return "login";
		}
		
		try{
		Connection connection = Connection_provider.getconnection();

		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
		int selectedid= Integer.parseInt(request.getParameter("selectedid"));
		
		CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
		
		Category category=categoryDAO.getCategory(selectedid);
		
		categoryForm.setId(category.getId());
		categoryForm.setCategoryName(category.getCategoryName());
		categoryForm.setDescription(category.getDescription());
		categoryForm.setGender(category.getGender());
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "edit";
		
	}

	@SkipValidation
	public String update()
	{
		if(!verifyLogin(request)){
			return "login";
		}
		try{
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
			Category category = new Category();
			int selectedid = categoryForm.getId();
			
			Connection connection = Connection_provider.getconnection();

			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			
			
			category.setCategoryName(categoryForm.getCategoryName());
			category.setDescription(categoryForm.getDescription());
			category.setGender(categoryForm.getGender());
			
			
			int result=categoryDAO.updateCategory(category,selectedid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "update";
	}
	
@SkipValidation
public String delete(){
	if(!verifyLogin(request)){
		return "login";
	}
		
		try{
			Connection connection = Connection_provider.getconnection();
			
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
			int selectedid= Integer.parseInt(request.getParameter("selectedid"));
			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			ProductDAO productDAO = new JDBCProductDAO(connection);
			
			int subcategoryID = categoryDAO.getSubcategoryID(selectedid);
			int deletepsize = productDAO.deletePsize(subcategoryID);
			
			int deleteProduct = categoryDAO.deleteProduct(selectedid);
			int deleteSubCategory = categoryDAO.deleteSubCategory(selectedid);
			int result = categoryDAO.deleteCategory(selectedid);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "delete";
	}
	
	
	@SkipValidation
	public String product(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection(); 
			int categoryID = Integer.parseInt(request.getParameter("categoryid"));
			String productName = request.getParameter("productName");
			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			
			boolean isProductExist = categoryDAO.isProductExist(categoryID,productName);
			
			if(isProductExist){
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("<font color = 'red'>This product is allready exist for this category!!</font>"); 
				
			}else{
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("go"); 
			}
			
			System.out.println(categoryID);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@SkipValidation
	public String gender(){
		String gender = request.getParameter("gender");
		System.out.println(gender);
		Connection connection = null;
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		try{
			connection = Connection_provider.getconnection();
			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			ArrayList<Category> categoryList = categoryDAO.getGendorCategoryList(gender,loginInfo.getUserId());
			
			StringBuffer str = new StringBuffer();
			str.append("<select name='categoryID' id='categoryID' onchange='setCategoryID(this.value)'>");
			str.append("<option value='0'>Select Category</option>");
			for(Category category : categoryList){
				
				str.append("<option value='"+category.getId()+"'>"+category.getCategoryName()+"</option>");
			}
			
			str.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString()); 
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SkipValidation
	public String gendereturn(){
		String gender = request.getParameter("gender");
		System.out.println(gender);
		Connection connection = null;
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		try{
			connection = Connection_provider.getconnection();
			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			ArrayList<Category> categoryList = categoryDAO.getGendorCategoryList(gender,loginInfo.getUserId());
			
			StringBuffer str = new StringBuffer();
			str.append("<select name='categoryID' id='categoryID' onchange='setCategoryIDSalaReturn(this.value)'>");
			str.append("<option value='0'>Select Category</option>");
			for(Category category : categoryList){
				
				str.append("<option value='"+category.getId()+"'>"+category.getCategoryName()+"</option>");
			}
			
			str.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString()); 
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SkipValidation
	public String gprod(){
		
		String gender = request.getParameter("gender");
		String index = request.getParameter("index");
		System.out.println(gender);
		Connection connection = null;
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		try{
			connection = Connection_provider.getconnection();
			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			ArrayList<Category> categoryList = categoryDAO.getGendorCategoryList(gender,loginInfo.getUserId());
			
			StringBuffer str = new StringBuffer();
			str.append("<select name='rowProductList["+index+"].categoryID' id='rowProductList["+index+"].categoryID' onchange='setCategoryID(this.value)'>");
			str.append("<option value='0'>Select Category</option>");
			for(Category category : categoryList){
				
				str.append("<option value='"+category.getId()+"'>"+category.getCategoryName()+"</option>");
			}
			
			str.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString()); 
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
public void validate() {
		
	/*	Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			int totalCategory = categoryDAO.getTotalCategory();
			
			
			if(totalCategory==3){
				addActionError("You can not add more than 3 category. Please purchase full version!!");
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
		*/
		
		
	}
	

	public CategoryForm getModel() {
	
		return categoryForm;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}



	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			
			ArrayList<Category> list = categoryDAO.getmainCategorylist();
			categoryForm.setMainCategory(list);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
