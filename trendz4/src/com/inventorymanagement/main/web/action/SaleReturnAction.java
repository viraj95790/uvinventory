package com.inventorymanagement.main.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.inventorymanagement.main.eu.bi.InvoiceDAO;
import com.inventorymanagement.main.eu.bi.ProductDAO;
import com.inventorymanagement.main.eu.bi.PurchaseDAO;
import com.inventorymanagement.main.eu.bi.SaleReturnDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCInvoiceDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCProductDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCPurchaseDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCSaleReturnDAO;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Color;
import com.inventorymanagement.main.eu.entity.Invoice;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.ProductForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class SaleReturnAction extends BaseAction implements Preparable, ModelDriven<ProductForm>{
	
	ProductForm productForm = new ProductForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	
	
	
	public String execute() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ArrayList<Purchase>purchaseList = purchaseDAO.getPurchaseList(loginInfo.getUserId());
			SaleReturnDAO saleReturnDAO = new JDBCSaleReturnDAO(connection);
			
			Purchase purchase1 = new Purchase();
			purchase1.setCustomerName(productForm.getCustomerName());
			purchase1.setMobileNumber(productForm.getMobileNo());
			purchase1.setVat(productForm.getVat());
			
			int companyId = saleReturnDAO.saveCompany(purchase1,loginInfo.getUserId());
			
			for(Purchase purchase : purchaseList){
				Product product = purchaseDAO.getProdutctDetails(purchase.getCategoryid(), purchase.getProductId(), loginInfo.getUserId());
				//int currentQuantity = product.getQuantity()+purchase.getQuantity();
				String currentSize = "";
				currentSize = purchase.getSize();
				/*if(!product.getSize().equals("")){
					currentSize = product.getSize() + "," + purchase.getSize();
				}*/
					//int update = purchaseDAO.updatePurchase(currentQuantity,currentSize,purchase.getProductId());
					
					String temp[] = currentSize.split(",");
					//String tempActualSize[] = purchase.getActualSize().split(",");
					
					for(int i=0;i<temp.length;i++){
						int result = saleReturnDAO.saveSize(purchase,temp[i],"");
					}
					
					int result = saleReturnDAO.savePurchaseInvoice(purchase,loginInfo.getUserId(),companyId);
					int delete = purchaseDAO.deletePurchase(loginInfo.getUserId());
					int deletebsize = purchaseDAO.deleBSize(loginInfo.getId());
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		

		return SUCCESS;
	}
	
	
	
	

	public String back() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);
			SaleReturnDAO saleReturnDAO = new JDBCSaleReturnDAO(connection);
			
			ArrayList<Purchase>purchaseList = saleReturnDAO.getPurchaseInvoiceList();
			productForm.setInvoiceList(purchaseList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		 
	
	
		
		return "selereturn";
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
			
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			
			ArrayList<Color>populateColorList = new  ArrayList<Color>();
			ArrayList<Color>colorList = purchaseDAO.getColorList();
			for(Color color : colorList){
				populateColorList.add(new Color(color.getColorId(),color.getColorName()));
			}
			
			productForm.setColorList(populateColorList);
			
			
			int delete = purchaseDAO.deletePurchase(loginInfo.getUserId());
			int deletebsize = purchaseDAO.deleBSize(loginInfo.getId());
			
		
		}catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return INPUT;
	}
	
	
	public String move() throws SQLException{
		Connection connection = null;
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		try{
			
			connection = Connection_provider.getconnection();
			SaleReturnDAO saleReturnDAO = new JDBCSaleReturnDAO(connection);
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			if(productForm.getSelectedItem().equals("") || productForm.getSelectedItem().equals("0")){
				addActionMessage("Please Select Product");
			}else{
				String temp[] = productForm.getSelectedItem().split(",");
				ArrayList<String>list = new ArrayList<String>();
				for(int i=0;i<temp.length;i++){
					list.add(temp[i]);
				}
				if(list.size() > 1){
					list.remove(0);
				}
				
				
					for(String salerID : list){
						Purchase purchase = saleReturnDAO.getSaleReturnDetails(salerID);
						Product product = purchaseDAO.getProdutctDetails(purchase.getCategoryid(), purchase.getProductId(), loginInfo.getUserId());
						int currentQuantity = product.getQuantity()+purchase.getQuantity();
						String currentSize = "";
						currentSize = purchase.getSize();
						int update = purchaseDAO.updatePurchase(currentQuantity,currentSize,purchase.getProductId());
						int companyId = purchaseDAO.saveCompany(purchase,loginInfo.getUserId());
						String temps[] = purchase.getSize().split(",");
						//String tempActualSize[] = purchase.getActualSize().split(",");
						
						for(int j=0;j<temps.length;j++){
							int result = purchaseDAO.saveSize(purchase,temps[j],"");
						}
						int result = purchaseDAO.savePurchaseInvoice(purchase,loginInfo.getUserId(),companyId);
						
						int count = saleReturnDAO.countSaler_invoiceData(purchase.getCompanyID());
						
						if(count == 1){
							int delete = saleReturnDAO.deleteSaler_company(purchase.getCompanyID());
						}
						
						int delete = saleReturnDAO.deleteSaleReturn(salerID);
						
						addActionMessage(" "+list.size()+" Product has been moved to purchase stock successfully!!");
						
					}
			}
				
			ArrayList<Purchase>purchaseList = saleReturnDAO.getPurchaseInvoiceList();
			productForm.setInvoiceList(purchaseList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return "move";
	}
	
	public String delete(){
		Connection connection = null;
			
		try{
			connection = Connection_provider.getconnection();
			SaleReturnDAO saleReturnDAO = new JDBCSaleReturnDAO(connection);
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			if(productForm.getSelectedItem().equals("") || productForm.getSelectedItem().equals("0")){
				addActionMessage("Please Select Product");
			}else{
				String temp[] = productForm.getSelectedItem().split(",");
				ArrayList<String>list = new ArrayList<String>();
				for(int i=0;i<temp.length;i++){
					list.add(temp[i]);
				}
				if(list.size() > 1){
					list.remove(0);
				}
				for(String salerID : list){
					Purchase purchase = saleReturnDAO.getSaleReturnDetails(salerID);
					int count = saleReturnDAO.countSaler_invoiceData(purchase.getCompanyID());
					
					if(count == 1){
						int delete = saleReturnDAO.deleteSaler_company(purchase.getCompanyID());
					}
					
					int delete = saleReturnDAO.deleteSaleReturn(salerID);
					
					addActionMessage(" "+list.size()+" Product has been deleted successfully!!");
				}
				
				
			}
			
			ArrayList<Purchase>purchaseList = saleReturnDAO.getPurchaseInvoiceList();
			productForm.setInvoiceList(purchaseList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "delete";
	}
	
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public ProductForm getModel() {
		// TODO Auto-generated method stub
		return productForm;
	}

}
