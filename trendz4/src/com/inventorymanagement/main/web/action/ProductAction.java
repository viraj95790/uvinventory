package com.inventorymanagement.main.web.action;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.common.constants.Constants;
import com.inventorymanagement.main.eu.bi.AdminSubcategoryDAO;
import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.bi.CategoryDAO;
import com.inventorymanagement.main.eu.bi.ProductDAO;
import com.inventorymanagement.main.eu.bi.PurchaseDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCAdminSubcategoryDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCBranchDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCCategoryDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCProductDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCPurchaseDAO;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Color;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.RemainStock;
import com.inventorymanagement.main.eu.entity.Size;
import com.inventorymanagement.main.eu.entity.Stock;
import com.inventorymanagement.main.eu.entity.StockProduct;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.ProductForm;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ProductAction extends BaseAction implements Preparable, ModelDriven<ProductForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	ProductForm productForm = new ProductForm();
	private Pagination pagination = new Pagination(10, 1);
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@SkipValidation
	public String execute() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		String selectedBranch = "";
		
		try{
			connection = Connection_provider.getconnection();
			ProductDAO productDAO = new JDBCProductDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			
			if(session.getAttribute("selectedBranch")!=null){
				selectedBranch = (String)session.getAttribute("selectedBranch");
				System.out.println(selectedBranch);
			}
			
		
				int selectedBranchID = 0;
				if(session.getAttribute("selectedBranchID")!=null){
					selectedBranchID = (Integer)session.getAttribute("selectedBranchID");
				}
				
				
					//pagination
					int totalCount = productDAO.getProductCount(productForm.getSearchText(),loginInfo.getUserId(),productForm.getArticleNumber());
					pagination.setPreperties(totalCount);
					ArrayList<Product>productList = productDAO.getProductList(pagination,productForm.getSearchText(),loginInfo.getUserId(),productForm.getArticleNumber());
					pagination.setPage_records(productList.size());
					productForm.setTotalRecords(totalCount);
					productForm.setProductList(productList);
					
				
			
			
			
			productForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			
			
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return SUCCESS;
	}
	
	public String showshopqty(){
		
		String shopid = request.getParameter("shopid");
		String prodid = request.getParameter("prodid");
		
		if(!verifyLogin(request)){
			return "login";
		}
			
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ProductDAO productDAO  = new JDBCProductDAO(connection);
			
			int qty = productDAO.getShopQty(shopid,prodid);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+qty+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	//shopstockProduct?shopid="+shopid+"&qty="+qty+"&nq="+qty+"
	public String shopstock(){
		String shopid = request.getParameter("shopid");
		String qty = request.getParameter("qty");
		String prodid = request.getParameter("prodid");
		String subcateoryid = request.getParameter("subcateoryid");
		
		
		if(!verifyLogin(request)){
			return "login";
		}
			
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ProductDAO productDAO  = new JDBCProductDAO(connection);
			
			int sumshopqty = productDAO.getSumofShopQty(prodid);
			sumshopqty = sumshopqty + Integer.parseInt(qty);
			int adminqty = productDAO.getAdminProductQty(prodid);
			
			if(sumshopqty<=adminqty){
				boolean checkproductExist = productDAO.checkProductExist(shopid,prodid);
				if(!checkproductExist){
					int result = productDAO.saveShopStock(shopid,qty,prodid,subcateoryid);
				}else{
					int update = productDAO.updateShopStock(shopid,prodid,qty);
				}
			}else{
				int old = productDAO.getShopQty(shopid, prodid);
				qty = ""+old+"";
			}
		
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+qty+""); 
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	@SkipValidation
	public String adminStock(){
		if(!verifyLogin(request)){
			return "login";
		}
			
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			BranchDAO branchDAO = new JDBCBranchDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ArrayList<Category>categoryWiseProductList = new ArrayList<Category>();
			ProductDAO productDAO  = new JDBCProductDAO(connection);
			ArrayList<Branch>branchList = branchDAO.getBranch();
			
			ArrayList<RemainStock>productList2 = new ArrayList<RemainStock>();
			ArrayList<RemainStock>productList3 = new ArrayList<RemainStock>();
			ArrayList<RemainStock>productList4 = new ArrayList<RemainStock>();
			
			ArrayList<Category>categoryList = productDAO.getAdminCategoryList();
			productForm.setCategoryList(categoryList);
			
			ArrayList<StockProduct>productListBycategory = new ArrayList<StockProduct>();
			ArrayList<Product>adminProductList = new ArrayList<Product>();
		
			for(Category category : categoryList){
				
			
				
				
				adminProductList = branchDAO.getProductList(category.getId());
				
				category.setProductList(adminProductList);
				
				categoryWiseProductList.add(category);
				
		} 
			
		productForm.setCategoryWiseProductList(categoryWiseProductList);
			//productForm.setBranchWiseStockList(branchWiseStockList);
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
	
			return "adminStock";
	}
	
	@SkipValidation
	public String stock(){
		if(!verifyLogin(request)){
			return "login"; 
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ArrayList<Category>categoryWiseProductList = new ArrayList<Category>();
			ProductDAO productDAO  = new JDBCProductDAO(connection);
			
			ArrayList<Product>productList = new ArrayList<Product>();
			
			ArrayList<Category>categoryList = productDAO.getAdminCategoryList();
			productForm.setCategoryList(categoryList);
			for(Category category : categoryList){
				productList = productDAO.getProductList(loginInfo.getUserId(),category.getId());
				category.setProductList(productList);
				categoryWiseProductList.add(category);
			}
			
			 for(Category category : categoryWiseProductList){
				 System.out.println(category.getCategoryName());
				 for(Product product : category.getProductList()){
					 System.out.println(product.getProductName());
				 }
			 }
			
			productForm.setCategoryWiseProductList(categoryWiseProductList);
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
		
	
		
		return "stock";
	}
	
	public void getadminDetails(){
		//set branchlist
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			session.setAttribute("selectedBranch", "0");
			BranchDAO branchDAO = new JDBCBranchDAO(connection);
			ArrayList<Branch>BranchList = branchDAO.getBranch();
			Branch br = new Branch();
			br.setId(1);
			br.setBranchName("All");
			
			
			BranchList.add(br);
			ArrayList<Branch>populateBranchList = new ArrayList<Branch>();
			
			for(Branch branch : BranchList){
				populateBranchList.add(new Branch(branch.getId(),branch.getBranchName()));
				
			}
			productForm.setBranchList(populateBranchList);
			if(session.getAttribute("selectedBranchID")!=null){
				int selectedBranchID = (Integer)session.getAttribute("selectedBranchID");
				productForm.setBranchID(selectedBranchID);
			}else{
				productForm.setBranchID(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			

		
	}
	
	
	public String save(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			String filePath = request.getRealPath("/image/");
		      
			if(productForm.getUserImage()!=null){
			 System.out.println("Server path:" + filePath);
			 File fileToCreate = new File(filePath, productForm.getProductName()+"_"+productForm.getUserImageFileName());
			 FileUtils.copyFile(productForm.getUserImage(), fileToCreate);
			}
			
			
			int selectedBranch = 0;
			selectedBranch = productForm.getBranchID();
			
			connection = Connection_provider.getconnection();
			ProductDAO productDAO = new JDBCProductDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			Product product = new Product();
			
			product.setCategoryID(productForm.getCategoryID());
			product.setProductName(productForm.getProductName());
			product.setDescription(productForm.getDescription());
			//product.setQuantity(productForm.getQuantity());
			product.setPrice(productForm.getPrice());
			product.setBranchid(loginInfo.getId());
			product.setUserid(loginInfo.getUserId());
			
			product.setModelNumber(productForm.getArticleNumber());
			product.setArticleNumber(productForm.getArticleNumber());
			product.setSize(productForm.getSize());
			product.setColor(productForm.getColor());
			product.setUploadedimage(productForm.getProductName()+"_"+productForm.getUserImageFileName());
			
			int result = productDAO.saveProduct(product);
			
			
			
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
	public String input() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		
	/*if( session.getAttribute("selectedBranchID")!=null){
		int selectedBranchID  =(Integer)session.getAttribute("selectedBranchID");
		productForm.setBranchID(selectedBranchID);
		
	}
		*/
		
		
		
	
	
	
		return INPUT;
	}
	
	@SkipValidation
	public String saveSubcategory(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			/*Product product = new Product();
			product.setCategoryID(productForm.getCategoryID());
			product.setProductName(productForm.getProductName());
			product.setPrice(productForm.getPrice());*/
			
			
			ProductDAO productDAO = new JDBCProductDAO(connection);
			//int result = productDAO.saveSubCategory(product);
			
			for(Product product : productForm.getRowProductList()){
				int result = productDAO.saveSubCategory(product);
				
				String ch = request.getParameter("rowProductList[0].iegst");
				String ch1 = request.getParameter("rowProductList[1].iegst");
				
				product.setProductName(Integer.toString(result));
				product.setBranchid(loginInfo.getId());
				product.setUserid(loginInfo.getUserId());
				
				int result1 = productDAO.saveProduct(product);
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
		
		return "subCategoryList";
	}
	
	
	@SkipValidation
	public String subcategory(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ProductDAO productDAO = new JDBCProductDAO(connection);
			ArrayList<Category>categoryList = productDAO.getAdminCategoryList();
			ArrayList<Category>populateCategoryList = new ArrayList<Category>();
			for(Category category : categoryList){
				populateCategoryList.add(new Category(category.getId(),category.getCategoryName()));
			}
			
			productForm.setCategoryList(populateCategoryList);
			
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
		
		return "adminProduct";
	}
	
	
	
	@SkipValidation
	public String edit()
	{
		if(!verifyLogin(request)){
			return "login"; 
		}
		try{
			
		Connection connection = Connection_provider.getconnection();
		
		
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		ProductDAO productDAO = new JDBCProductDAO(connection);
		ArrayList<Category>categoryList = productDAO.getAdminCategoryList();
		ArrayList<Category>populateCategoryList = new ArrayList<Category>();
		for(Category category : categoryList){
			populateCategoryList.add(new Category(category.getId(),category.getCategoryName()));
		}
		
		productForm.setCategoryList(populateCategoryList);
		
		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
		int selectedid= Integer.parseInt(request.getParameter("selectedid"));
		
		
		Product product=productDAO.getProduct(selectedid);
		productForm.setId(product.getId());
		productForm.setCategoryID(product.getCategoryID());
		productForm.setDescription(product.getDescription());
		productForm.setProductName(product.getProductName());
		productForm.setQuantity(product.getQuantity());
		
		AdminSubcategoryDAO adminSubcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
		double price = adminSubcategoryDAO.getAdminProductPrice(product.getCategoryID(), Integer.parseInt(product.getProductName()));
		productForm.setPrice(price);
		productForm.setArticleNumber(product.getArticleNumber());
		productForm.setModelNumber(product.getModelNumber());
		
		PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
		ArrayList<Size> sizeList = new ArrayList<Size>();
		ArrayList<Color>psizeColorList = purchaseDAO.getP_sizeColorList(Integer.parseInt(product.getProductName()));
		
		StringBuffer sizeBuffer = new StringBuffer();
		StringBuffer psizrBuffer = new StringBuffer(); 
		
		for(Color color : psizeColorList){
			psizrBuffer = new StringBuffer(); 
			
			sizeList = purchaseDAO.getProductSize(Integer.parseInt(product.getProductName()),loginInfo.getUserId(),color.getColorName());
			for(Size size : sizeList){
				psizrBuffer.append( size.getProductSize() +  ",");
				
			}
			
			sizeBuffer.append(color.getColorName() + ":->" + psizrBuffer.toString());
		}
		
		String size = "";
		if(sizeBuffer.length()!=0){
			size = sizeBuffer.subSequence(0, sizeBuffer.length()-1).toString();
		}
		
		
		
		productForm.setSize(size);
		productForm.setColor(product.getColor());
		productForm.setUploadedimage(product.getUploadedimage());
		productForm.setGender(product.getGender());
	
		
		
		session.setAttribute("categoryID", product.getCategoryID());
		
		//set subcategory
		ArrayList<Product>subCategoryList = productDAO.getAjaxProductList(product.getCategoryID());
		ArrayList<Product>populateSubcategoryList = new ArrayList<Product>();
		for(Product products : subCategoryList){
			populateSubcategoryList.add(new Product(products.getId(),products.getProductName()));
		}
		productForm.setSubcategoryList(populateSubcategoryList);
		
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
			Product product =new Product();
			int selectedid = Integer.parseInt(request.getParameter("selectedid"));
			
			Connection connection = Connection_provider.getconnection();
			ProductDAO productDAO = new JDBCProductDAO(connection);
			
			product.setCategoryID(productForm.getCategoryID());
			product.setProductName(productForm.getProductName());
			product.setDescription(productForm.getDescription());
			//product.setQuantity(productForm.getQuantity());
			product.setPrice(productForm.getPrice());
			
			product.setModelNumber(productForm.getArticleNumber());
			product.setArticleNumber(productForm.getArticleNumber());
			product.setSize(productForm.getSize());
			product.setColor(productForm.getColor());
			String uploadedImage = productDAO.getUploadedImage(selectedid);
			product.setUploadedimage(uploadedImage);
			
			if(productForm.getUserImage()!=null){
				String filePath = request.getRealPath("/image/");
			       
				System.out.println("Server path:" + filePath);
				File fileToCreate = new File(filePath, productForm.getProductName()+"_"+productForm.getUserImageFileName());
				FileUtils.copyFile(productForm.getUserImage(), fileToCreate);
				
				product.setUploadedimage(productForm.getProductName()+"_"+productForm.getUserImageFileName());
			}
			
			int result=productDAO.updateCategory(product,selectedid);
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
			ProductDAO productDAO = new JDBCProductDAO(connection);
			int subcategoryID = productDAO.getSubCategoryID(selectedid);
			int deletepsize = productDAO.deletePsize(subcategoryID);
			
			int result = productDAO.deleteProduct(selectedid);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "delete";
	}
	
	@SkipValidation
	public String updatestock(){
		if(!verifyLogin(request)){
			return "login"; 
		}
		String stockValue = request.getParameter("stockValue");
		String selectedID = request.getParameter("selectedID");
		String detailid = request.getParameter("detailid");
		
		
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ProductDAO productDAO = new JDBCProductDAO(connection);
			
			Product product=productDAO.getProduct(Integer.parseInt(selectedID));
			BranchDAO branchDAO = new JDBCBranchDAO(connection);
			int updateStatus = branchDAO.updateDeliveredStatus(product.getCategoryID(),Integer.parseInt(product.getProductName()));
			
			int result = productDAO.updateStock(stockValue,selectedID);
			
			StringBuffer ajaxStock = new StringBuffer();
			int remainStock = productDAO.getRemainStock(selectedID);
			ajaxStock.append(remainStock);
			ajaxStock.append(" <a href='javascript:void(0)' onclick='showStock("+detailid+")'>Edit</a>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(ajaxStock.toString()); 
			
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
		
		
		
		System.out.println(stockValue);
	return null;	
	}

	@SkipValidation
	public String subcategoryAjax(){
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			 int categoryID = Integer.parseInt(request.getParameter("id"));
			 
			 ProductDAO productDAO = new JDBCProductDAO(connection);
			 ArrayList<Product>ajaxProductList = productDAO.getAjaxProductList(categoryID);
			 
			 StringBuffer dropDownAjax = new StringBuffer();
				dropDownAjax.append("<select id='subCategoryID' name = 'productName' onchange='setPrice(this.value)'>");
					dropDownAjax.append("<option value = '0'>Select Product</option>");
					if(ajaxProductList.size()!=0){
						for(Product product : ajaxProductList){
							dropDownAjax.append("<option value = '"+product.getId()+"'>"+product.getProductName()+"</option>");
						}
						
					}
				dropDownAjax.append("</select>");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				
				response.getWriter().write(""+dropDownAjax.toString()+""); 
			
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
		return null;
	}
	
	
	@SkipValidation
	public String delievered(){
 		if(!verifyLogin(request)){
			return "login"; 
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			int categoryID = Integer.parseInt(request.getParameter("categoryid"));
			int subcategoryid = Integer.parseInt(request.getParameter("subcategoryid"));
			int nq =  Integer.parseInt(request.getParameter("nq"));
			int sq = Integer.parseInt(request.getParameter("sq"));
			int bq = Integer.parseInt(request.getParameter("bq"));
			
			BranchDAO branchDAO = new JDBCBranchDAO(connection);
			ArrayList<Stock> quantityList = branchDAO.getquantityList(categoryID,subcategoryid);
			for(Stock stock : quantityList){
				if(stock.getBranchID() == 2){
					stock.setRemainingStock(nq);
					int result = branchDAO.delieveredStatus(categoryID,subcategoryid,stock);
				}
				if(stock.getBranchID() == 3){
					stock.setRemainingStock(sq);
					int result = branchDAO.delieveredStatus(categoryID,subcategoryid,stock);
				}
				if(stock.getBranchID() == 4){
					stock.setRemainingStock(bq);
					int result = branchDAO.delieveredStatus(categoryID,subcategoryid,stock);
				}
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
	
		
		return null;
	}
	
	
	@SkipValidation
	public String print(){
		if(!verifyLogin(request)){
			return "login"; 
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			BranchDAO branchDAO = new JDBCBranchDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ArrayList<Category>categoryWiseProductList = new ArrayList<Category>();
			ProductDAO productDAO  = new JDBCProductDAO(connection);
			ArrayList<Branch>branchList = branchDAO.getBranch();
			
			ArrayList<RemainStock>productList2 = new ArrayList<RemainStock>();
			ArrayList<RemainStock>productList3 = new ArrayList<RemainStock>();
			ArrayList<RemainStock>productList4 = new ArrayList<RemainStock>();
			
			ArrayList<Category>categoryList = productDAO.getAdminCategoryList();
			productForm.setCategoryList(categoryList);
			
			ArrayList<StockProduct>productListBycategory = new ArrayList<StockProduct>();
			ArrayList<Product>adminProductList = new ArrayList<Product>();
		
			for(Category category : categoryList){
				
				adminProductList = branchDAO.getProductList(category.getId());
				
				category.setProductList(adminProductList);
				
				categoryWiseProductList.add(category);
				
		} 
			
		productForm.setCategoryWiseProductList(categoryWiseProductList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return "print";
	}
	
	@SkipValidation
	public String updatequantity(){
		if(!verifyLogin(request)){
			return "login"; 
		}
		String stockValue = request.getParameter("stockValue");
		String selectedID = request.getParameter("selectedID");
		String quantity = request.getParameter("quantity");
		System.out.println(quantity);
		
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			ProductDAO productDAO = new JDBCProductDAO(connection);
			
			int result = productDAO.updateQuantity(selectedID,quantity);
			
			
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
		
		
		
		
		return null;
	}
	
	@SkipValidation
	public String size(){
		if(!verifyLogin(request)){
			return "login"; 
		}
		int selectedid= Integer.parseInt(request.getParameter("selectedid"));
		session.setAttribute("selectedProductID", selectedid);
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ProductDAO productDAO = new JDBCProductDAO(connection);
			Product product=productDAO.getProduct(selectedid);
			System.out.println(product.getProductName());
			int gender = productDAO.getGender(Integer.parseInt(product.getProductName()));
			ArrayList<Size>sizeList = productDAO.getSizeList(Integer.parseInt(product.getProductName()));
			
			session.setAttribute("gender", gender);
			productForm.setSelectedID(selectedid);
			productForm.setSizeList(sizeList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "productsize";
	}
	
	@SkipValidation
	public String editsize(){
		
		if(!verifyLogin(request)){
			return "login"; 
		}
		
		int selectedid= Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ProductDAO productDAO = new JDBCProductDAO(connection);
			Size size = productDAO.getP_Size(selectedid);
			
			productForm.setSizeValue(size.getSizeValue());
			productForm.setColorName(size.getColorName());
			int selectedProductID = (Integer)session.getAttribute("selectedProductID");
			productForm.setSelectedID(selectedProductID);
			productForm.setId(size.getId());
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "editproductsize";
	}
	
	
	@SkipValidation
	public String savesize() throws SQLException{
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ProductDAO productDAO = new JDBCProductDAO(connection);
			Size size = new Size();
			size.setColorName(productForm.getColorName());
			size.setSizeValue(productForm.getSizeValue());
			int gender = (Integer)session.getAttribute("gender");
			//int actualSize = productDAO.getActualSize(gender,size.getSizeValue());
			//size.setActualsize(Integer.toString(actualSize));
			size.setId(productForm.getId());
			
			int update = productDAO.updateP_size(size);
			
			//List Data
			int selectedProductID = (Integer)session.getAttribute("selectedProductID");
			Product product=productDAO.getProduct(selectedProductID);
			ArrayList<Size>sizeList = productDAO.getSizeList(Integer.parseInt(product.getProductName()));
			
			
			productForm.setSelectedID(selectedProductID);
			productForm.setSizeList(sizeList);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return "savesize";
	}
	
	
	public String deletesize(){
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ProductDAO productDAO = new JDBCProductDAO(connection);
			int selectedid= Integer.parseInt(request.getParameter("selectedid"));
			int delete = productDAO.deleteProductSize(selectedid); 
			
			
			
			//List Data
			int selectedProductID = (Integer)session.getAttribute("selectedProductID");
			Product product=productDAO.getProduct(selectedProductID);
			int currentQuantity = product.getQuantity() - 1;
			int update = productDAO.updateCurrentQuantity(selectedProductID,currentQuantity);
			ArrayList<Size>sizeList = productDAO.getSizeList(Integer.parseInt(product.getProductName()));
			
			
			productForm.setSelectedID(selectedProductID);
			productForm.setSizeList(sizeList);
			
		}catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		return "deletesize";
	}
	
	
	
	public void validate() {
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AdminSubcategoryDAO subcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
			boolean isQuantityExist = subcategoryDAO.checkQuantityExist(productForm.getCategoryID(),Integer.parseInt(productForm.getProductName()));
			if(isQuantityExist){
				addActionError("This product details already exist");
				
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
	
	
	public String more(){
		
		String count = request.getParameter("count");
		
		try{
			
			StringBuffer str = new StringBuffer();
			
			for(int i=1;i<=Integer.parseInt(count);i++){
				
				String gender = request.getParameter("gender"+i);
				
				str.append("<tr>");
				str.append("<td>");
				str.append("<select value='"+gender+"'  onchange='setGenderProductAjax(this.value,"+i+")' name='gender"+i+"' id='gender"+i+"'>");
				str.append("<option value='0'>Select Gender</option>");
				str.append("<option value='1'>Gents</option>");
				str.append("<option value='2'>Ladies</option>");
				str.append("</select>");
				str.append("</td>");
				
				
				str.append("<td id='gendercategory"+i+"'>");
				str.append("<select  name='category' id='category'>");
				str.append("<option value='0'>Select Category</option>");
				str.append("</select>");
				str.append("</td>");
				
				str.append("<td>");
				str.append("<input type='text' maxlength='15' placeholder='Valid only 15 charecter' name='productName"+i+"' id='productName"+i+"'>");
				str.append("</td>");
				
				str.append("<td>");
				str.append("<input type='text' maxlength='6' placeholder='Enter price' name='price"+i+"' id='price"+i+"'>");
				str.append("</td>");
				
				if(i==Integer.parseInt(count)){
					str.append("<input type='button' value='Add' onclick='setmoreproduct()'>");
				}
				
				
				str.append("</tr>");
			}
			
			int t = Integer.parseInt(count);
			t++;
			str.append("<tr id='trid"+t+"'></tr>");
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString()); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	

	
	public ProductForm getModel() {
	
		return productForm;
	}


	public void prepare() throws Exception {
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ProductDAO productDAO = new JDBCProductDAO(connection);
			BranchDAO branchDAO = new JDBCBranchDAO(connection);
			
			
            CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			
			ArrayList<Category> list = categoryDAO.getmainCategorylist();
			productForm.setMainCategory(list);
			
			ArrayList<Category>categoryList = productDAO.getAdminCategoryList();
			ArrayList<Category>populateCategoryList = new ArrayList<Category>();
			for(Category category : categoryList){
				populateCategoryList.add(new Category(category.getId(),category.getCategoryName()));
			}
			
			productForm.setCategoryList(populateCategoryList);
			
			
			//set subcategory
			ArrayList<Product>subCategoryList = productDAO.getSubCategoryList();
			ArrayList<Product>populateSubcategoryList = new ArrayList<Product>();
			for(Product product : subCategoryList){
				populateSubcategoryList.add(new Product(product.getId(),product.getProductName()));
			}
			productForm.setSubcategoryList(populateSubcategoryList);
			
			
			ArrayList<Branch>userList = branchDAO.getUserList();
			productForm.setUserList(userList);
			
			}catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	}


	


}
