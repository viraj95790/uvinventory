package com.inventorymanagement.main.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.main.common.constants.Constants;
import com.inventorymanagement.main.eu.bi.AccessoriesDAO;
import com.inventorymanagement.main.eu.bi.AdminSubcategoryDAO;
import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.bi.CategoryDAO;
import com.inventorymanagement.main.eu.bi.CustomerDAO;
import com.inventorymanagement.main.eu.bi.InvoiceDAO;
import com.inventorymanagement.main.eu.bi.ProductDAO;
import com.inventorymanagement.main.eu.bi.PurchaseDAO;
import com.inventorymanagement.main.eu.bi.SaleDAO;
import com.inventorymanagement.main.eu.bi.SalesManDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCAccessoriesDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCAdminSubcategoryDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCBranchDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCCategoryDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCCustomerDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCInvoiceDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCProductDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCPurchaseDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCSaleDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCSalesManDAO;
import com.inventorymanagement.main.eu.entity.Accessories;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Color;
import com.inventorymanagement.main.eu.entity.Customer;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.Sale;
import com.inventorymanagement.main.eu.entity.SalesMan;
import com.inventorymanagement.main.eu.entity.Size;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.ProductForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class SaleAction extends BaseAction implements  Preparable, ModelDriven<ProductForm>{
	
	
	ProductForm productForm = new ProductForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	public String execute() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Sale sale = new Sale();
		sale.setCustomerName(productForm.getCustomerName());
		sale.setMobileno(productForm.getMobileNo());
		sale.setVat(productForm.getVat());
		sale.setRupee(productForm.getRupee());
		
		 
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			AccessoriesDAO accessoriesDAO = new JDBCAccessoriesDAO(connection);
			SaleDAO saleDAO = new JDBCSaleDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ArrayList<Purchase>purchaseList = purchaseDAO.getPurchaseList(loginInfo.getUserId());
			
			Purchase purchase1 = new Purchase();
			purchase1.setCustomerName(productForm.getCustomerName());
			purchase1.setMobileNumber(productForm.getMobileNo());
			purchase1.setVat(productForm.getVat());
			purchase1.setRupee(productForm.getRupee());
		
			
			int companyId = saleDAO.saveCustomer(purchase1,loginInfo.getUserId(),productForm.getEmployeeid(),productForm.getBillType(),productForm.getCustomerid());
			
			
			
			for(Purchase purchase : purchaseList){
				if(purchase.getAccessories() == 0){
					Product product = purchaseDAO.getProdutctDetails(purchase.getCategoryid(), purchase.getProductId(), loginInfo.getUserId());
					int purchaseQuantity = saleDAO.getPurchaseQuantity(purchase.getProductId(),loginInfo.getUserId());
					int currentQuantity = product.getQuantity()-purchaseQuantity;
					String currentSize = "";
					currentSize = purchase.getSize();
					String productidseries = purchase.getProductidseries();
					
					int update = purchaseDAO.updatePurchase(currentQuantity,currentSize,purchase.getProductId());
					
					//update shop stock
					int purchaseShopQuantity = purchaseDAO.getShopStock(Integer.toString(purchase.getProductId()), loginInfo.getId());
					int currenShoptQuantity = purchaseShopQuantity-purchase.getQuantity();
					int upd = purchaseDAO.updateShopStock(currenShoptQuantity,loginInfo.getId(),purchase.getProductId());
						
						String temp[] = productidseries.split(",");
						for(int i=0;i<temp.length;i++){
							int deletepsize = saleDAO.deletePsizeByProductID(Integer.parseInt(temp[i]));
						}
					}else{
						Accessories accessories = accessoriesDAO.getAccessories(purchase.getProductId());
						int accessoriesStock = accessories.getQuantity() - purchase.getQuantity();
						int update = accessoriesDAO.updateAccessoriesStock(accessoriesStock,purchase.getProductId());
					}
				
					
					InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);
					
					double dtotal = 0;
					double per = 0;
					double discountTotal = 0;
					if(productForm.getVat() != 0){
						
						dtotal = purchase.getMrp()*purchase.getQuantity();
						per  = (productForm.getVat()*dtotal)/100;
						discountTotal = dtotal-per;
						
						purchase.setTotal(discountTotal);
						
					}
					if(productForm.getRupee()!=0){
						dtotal = purchase.getMrp()*purchase.getQuantity();
						discountTotal = dtotal-productForm.getRupee();
						purchase.setTotal(discountTotal);
					}
					
					//boolean checkCustomerIDExist = invoiceDAO.checkCustomerIDExist(companyId);
					int result = saleDAO.saveSalenvoice(purchase,loginInfo.getUserId(),companyId);
					
                    
					//save in payroll
					
					double balance = invoiceDAO.getpayrollbalance();
					
					double pbalance = Double.parseDouble(productForm
							.getPaymentrecieved()) + balance;

					

					int pr = invoiceDAO.insertdata(pbalance, "0",
							productForm.getHowpaid(), productForm.getPaymentrecieved());
					
					/*ArrayList<Purchase>saleList = invoiceDAO.getSaleInvoiceList(companyId);
					double payAmount = 0;*/
					/*for(Purchase purchas : purchaseList){
						payAmount = payAmount+purchas.getTotal();
						
					}*/
					
					
					
					
					/*double per  = (productForm.getVat()*payAmount)/100;
					payAmount = payAmount-per;*/
					
					;
					//productForm.setPayAmount(payAmount);
					
					
					
					int delete = purchaseDAO.deletePurchase(loginInfo.getUserId());
					int deletes = saleDAO.delteS_Size(loginInfo.getId());
					int deletebsize = purchaseDAO.deleBSize(loginInfo.getId());
			}
			
			int savesalepayment = saleDAO.saveSalePayment(companyId,productForm.getHowpaid(),productForm.getPaymentrecieved(),productForm.getPaymentnote());
			session.setAttribute("selectedinvoiceid", companyId);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return SUCCESS;
	}
	
	
	public String viewinvoice() throws SQLException{
		Connection connection = null;
		try{
			
			int selectedinvoiceid = 0;
			
			String invoiceid = request.getParameter("invoiceid");
			if(invoiceid==null){
				selectedinvoiceid = (Integer)session.getAttribute("selectedinvoiceid");
			}else{
				selectedinvoiceid = Integer.parseInt(invoiceid);
			}
			
		
			
			
			connection = Connection_provider.getconnection();
			InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);
			ArrayList<Purchase>saleList = invoiceDAO.getSaleInvoiceList(selectedinvoiceid);
			Purchase p = saleList.get(saleList.size()-1);
			productForm.setTotalcgst(DateTimeUtils.changeFormat(p.getTotalcgstvalue()));
			productForm.setTotalsgst(DateTimeUtils.changeFormat(p.getTotalsgstvalue()));
			
			double payAmount = 0;
			
			String clientid   = invoiceDAO.getclientid(selectedinvoiceid);
			CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
			Customer customer = invoiceDAO.getInvoiceCustomerDetails(Integer.toString(selectedinvoiceid));
			
			productForm.setCustomerName(customer.getName());
			productForm.setMobileNo(customer.getMob());
			
			double gtotal = 0;
			for(Purchase purchase : saleList){
				gtotal = gtotal + (purchase.getMrp() * purchase.getQuantity());
			}
			
			productForm.setGtotal(Double.toString(gtotal));
			
			double discount = invoiceDAO.getInvoiceDiscount(selectedinvoiceid);
			productForm.setVat((int) discount);
			
			double rupee = invoiceDAO.getInvoiceRupee(selectedinvoiceid);
			productForm.setRupee((int) rupee);
			
			payAmount = invoiceDAO.getSumOfSaleAmount(selectedinvoiceid);
			double invoicetotal = invoiceDAO.getSumOfSaleTotal(selectedinvoiceid);
			double balance = invoicetotal - payAmount;
			productForm.setBalance(Double.toString(balance));
			productForm.setInvoicetotal(Double.toString(invoicetotal));
			productForm.setPayAmount(payAmount);
			
			productForm.setSaleList(saleList);
			productForm.setInvoiceNo(selectedinvoiceid);
			
			String currentDate = DateTimeUtils.getDateinSimpleStringFormate(new Date());
			String date[] = currentDate.split(" ");					
			productForm.setCurrentDate(date[0]);
			
			//letter head
			String preparedby = invoiceDAO.getInvoicePreparedBy(selectedinvoiceid);
			BranchDAO branchDAO =new JDBCBranchDAO(connection);
			Branch branch=branchDAO.getSelectedBranchData(preparedby);
			
			productForm.setCompanyName(branch.getCompanyName());
			productForm.setState(branch.getState());
			productForm.setCity(branch.getCity());
			productForm.setShopmob(branch.getMobileNo());
			productForm.setAddress(branch.getAddress());
			productForm.setPincode(branch.getPincode());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "viewinvoice";
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
			
			
			SaleDAO saleDAO = new JDBCSaleDAO(connection);
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			//int delete = saleDAO.deleteSale();
			int delete = purchaseDAO.deletePurchase(loginInfo.getUserId());
			int deletes = saleDAO.delteS_Size(loginInfo.getId());
			int deletebsize = purchaseDAO.deleBSize(loginInfo.getId());
			int del = saleDAO.deleteTempSize();
			
			//set salesman
			SalesManDAO salesManDAO = new JDBCSalesManDAO(connection);
			ArrayList<SalesMan>salesManList = salesManDAO.getSalesManList(loginInfo.getUserId());
			ArrayList<SalesMan>populatedSalesManList = new ArrayList<SalesMan>();
			
			for(SalesMan salesMan : salesManList){
				populatedSalesManList.add(new SalesMan(salesMan.getId(),salesMan.getFirstName()+" "+salesMan.getLastName()));
			}
			
			productForm.setPopulatedSalesManList(populatedSalesManList);
			
			ArrayList<Color>populateColorList = new  ArrayList<Color>();
			ArrayList<Color>colorList = purchaseDAO.getColorList();
			for(Color color : colorList){
				populateColorList.add(new Color(color.getColorId(),color.getColorName()));
			}
			
			productForm.setColorList(populateColorList);
			
			
		
		}catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return INPUT;
	}
	
	
	public String subcategoryAjax(){
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			 int categoryID = Integer.parseInt(request.getParameter("id"));
			 
			 ProductDAO productDAO = new JDBCProductDAO(connection);
			 SaleDAO saleDAO = new JDBCSaleDAO(connection);
			 ArrayList<Product>ajaxProductList = productDAO.getAjaxProductList(categoryID);
			 
			 //int del = saleDAO.deleteTempSize();
			 
			 StringBuffer dropDownAjax = new StringBuffer();
				dropDownAjax.append("<select id='subCategoryID' name = 'productName' onchange='setStock(this.value)'>");
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
	
	public String subcategoryAjaxreturn(){
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			 int categoryID = Integer.parseInt(request.getParameter("id"));
			 
			 ProductDAO productDAO = new JDBCProductDAO(connection);
			 SaleDAO saleDAO = new JDBCSaleDAO(connection);
			 ArrayList<Product>ajaxProductList = productDAO.getAjaxProductList(categoryID);
			 
			 //int del = saleDAO.deleteTempSize();
			 
			 StringBuffer dropDownAjax = new StringBuffer();
				dropDownAjax.append("<select id='subCategoryID' name = 'productName' onchange='setStockSaleReturn(this.value)'>");
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
	
	
	public String rmovesize() throws SQLException{
		
		String productidseries  = request.getParameter("productidseries");
		String productSize = request.getParameter("productSize");
		String selectedColor = request.getParameter("selectedColor");
		
 		Size sizes = new Size();
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		
		String temp[] = productSize.split(",");
		String tempProductID[] = productidseries.split(",");
		int num = temp.length-1;
		/*if(temp.length > 1){
			productSize = productSize.substring(0,productSize.length()-3);
		}else{
			productSize = productSize.substring(0,productSize.length()-2);
		}*/
		
		ArrayList<String>sList = new ArrayList<String>();
		for(int i=0;i<temp.length;i++){
			sList.add(temp[i]);
		}
		sList.remove(temp[num]);
		productSize = "";
		for(String str : sList){
			productSize = productSize + str + ",";
		}
		if(productSize.length() > 1){
			productSize = productSize.substring(0,productSize.length()-1);
		}
		
		
		//remove idseries
		ArrayList<String>cList = new ArrayList<String>();
		String tempColor[] = productidseries.split(",");
		String tempColorID = "";
		for(int i=0;i<tempColor.length-1;i++){
			cList.add(tempColor[i]);
		}
		cList.remove(tempColor[num]);
		for(String str : cList){
			tempColorID = tempColorID + str  + ",";
		}
		
		System.out.println(temp[num]);
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			int categoryID = 0;
			if(Integer.parseInt(request.getParameter("categoryD"))!=0){
				 categoryID = Integer.parseInt(request.getParameter("categoryD"));
			}else{
				categoryID = (Integer)session.getAttribute("categoryID");
			}
			String click = request.getParameter("click");
			int subCategoryID = Integer.parseInt(request.getParameter("subcategoryID"));
			
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			SaleDAO saleDAO = new JDBCSaleDAO(connection);
			Purchase purchase = new Purchase();
			purchase.setProductId(subCategoryID);
			
			String colorName = saleDAO.getProductColorSize(tempProductID[num]);
			
			int deleteS_size = saleDAO.deleteLastRecordSsize(tempProductID[num]);
			
			int result = saleDAO.saveTempSize(tempProductID[num], temp[num],subCategoryID,colorName);
			
			double mrp = purchaseDAO.getPrice(categoryID,subCategoryID);
			purchase = purchaseDAO.getProductDetails(categoryID,subCategoryID);
			
			StringBuffer sizeBuffer = new StringBuffer();
			StringBuffer psizeBuffer = new StringBuffer();
			ArrayList<Color>tempColorList = saleDAO.getTempColorList(subCategoryID);
			ArrayList<Size>tempSizeList = new ArrayList<Size>();
			
			for(Color color : tempColorList){
				psizeBuffer = new StringBuffer(); 
				tempSizeList = saleDAO.getTempSizeListByColor(subCategoryID,color.getColorName());
				
				for(Size size : tempSizeList){
					psizeBuffer.append( size.getProductSize() +  ",");
				}
				sizeBuffer.append(color.getColorName() + ":->" + psizeBuffer.toString());
			}
			
			String size = "";
			String colorSize = "";
			if(sizeBuffer.length()!=0){
				size = sizeBuffer.subSequence(0, sizeBuffer.length()-1).toString();
				
			}
		
		
			StringBuffer str = new StringBuffer();
			
			str.append("<col width='20%'/>");
			str.append("<col width='3%'/>");
			str.append("<col width='20%'/>");
			
			str.append("<tr>");
			str.append("<td>Stock<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='stock' name='stock'  readonly='readonly' rows='4' cols='20' >"+size+"</textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			int totalQuantity = saleDAO.getTotalQuantityP_size(subCategoryID);
		
			str.append("<tr>");
			str.append("<td>Stock Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='stockQuantity' name='stockQuantity' value = "+totalQuantity+"  readonly='readonly' size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
		
			str.append("<tr>");
			str.append("<td>Model No.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='modelnumber' name='modelNumber' value = "+purchase.getModelNumber()+" title='Enter Model Number' readonly='readonly'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Article No.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='articlenumber' name='articleNumber' value="+purchase.getArticleNumber()+" title='Enter Article Number' readonly='readonly'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Add Color<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<select name='colorName' id='adcolor'>");
			ArrayList<Color>colorList = purchaseDAO.getColorList();
			str.append("<option value='0'>Select Color</option>");
			for(Color color : tempColorList){
				str.append("<option value='"+color.getColorId()+"'>"+color.getColorName()+"</option>");
			}
			str.append("</select>");
			str.append("<input type='button' value='Add' id='adcolorbtn' onclick='addColor()' />");
			str.append("<input type='button' value='Remove' id='rmovecolorbtn' onclick='removeColor()'/>");
			str.append("</td>");
			str.append("</tr>");
			
			
			str.append("<tr>");
			str.append("<td>Color.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='hidden' name='tempcolorid' id='tempcolorid' value='"+tempColorID+"'/>");
			str.append("<textarea id='color' name='color' readonly='readonly'/>"+selectedColor+"</textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Price<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='price' value="+mrp+" name='price' title='Enter Price'  size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Add Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='adsizeajax'>");
			str.append("<select name='adsize' id='adsize'>");
			str.append("<option value='0'>Select</option>");
			
			tempSizeList = saleDAO.getTempSizeListByColor(subCategoryID,selectedColor);
			for(Size size2 : tempSizeList){
				str.append("<option value='"+size2.getId()+"'>"+size2.getProductSize()+"</option>");
			}
			
			str.append("</select>");
			str.append("<input type='button' id='adsizebtn' value='Add' onclick='addSize();'/>");
			str.append("<input type='button' id='rmovesizebtn' value='Remove' onclick='RemoveSize();'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>View Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='viewsize' name='viewsize' readonly='readonly'>"+productSize+"</textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='quantity' name='quantity' value='"+click+"'  readonly='readonly' size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Image<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='imgview'>");
			str.append("<img style='width: 150px;height: 100px;' src = 'image/"+purchase.getUloadedImage()+"'>");
			str.append("</td>");
			str.append("</tr>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return null;
	}
	
	public String addsize() throws SQLException{
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		
		String priductid  = request.getParameter("priductid");
		String productSize = request.getParameter("productSize");
		String productidseries = request.getParameter("productidseries");
		String selectedColor = request.getParameter("selectedColor");
		
		Size sizes = new Size();
		sizes.setId(Integer.parseInt(priductid));
		 
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			SaleDAO saleDAO = new JDBCSaleDAO(connection);
			//String productColorSize = saleDAO.getProductColorSize(productidseries);
			
			//delete from tem_size
			int delete = purchaseDAO.deleteProductSize(sizes);
			
			int categoryID = 0;
			if(Integer.parseInt(request.getParameter("categoryD"))!=0){
				 categoryID = Integer.parseInt(request.getParameter("categoryD"));
			}else{
				categoryID = (Integer)session.getAttribute("categoryID");
			}
			int subCategoryID = Integer.parseInt(request.getParameter("subcategoryID"));
			
			String click = request.getParameter("click");
			int clickCount = Integer.parseInt(click);
			
			
			//productidseries = productidseries.substring(0,productidseries.length()-1);
			
			Purchase purchase = purchaseDAO.getProductDetails(categoryID,subCategoryID);
			
			double mrp = purchaseDAO.getPrice(categoryID,subCategoryID);
			
			//ArrayList<Size> sizeList = purchaseDAO.getProductSize(subCategoryID,loginInfo.getUserId());
			ArrayList<Color>tempColorList = saleDAO.getTempColorList(subCategoryID);
			
			StringBuffer sizeBuffer = new StringBuffer();
			StringBuffer psizeBuffer = new StringBuffer();
			
			ArrayList<Size>tempSizeList = new ArrayList<Size>();
			
			for(Color color : tempColorList){
				psizeBuffer = new StringBuffer();
				tempSizeList = saleDAO.getTempSizeListByColor(subCategoryID,color.getColorName());
				
				for(Size size : tempSizeList){
					psizeBuffer.append( size.getProductSize() +  ",");
				}
				sizeBuffer.append(color.getColorName() + ":->" + psizeBuffer.toString());
			}
			
			
			
			String size = "";
			String colorSize = "";
			if(sizeBuffer.length()!=0){
				size = sizeBuffer.subSequence(0, sizeBuffer.length()-1).toString();
				
			}
		
		
			StringBuffer str = new StringBuffer();
			//str.append("<input type='hidden' name='purchaseidseries' id='purchaseidseries' value='"+productidseries+"'/>");
			str.append("<col width='20%'/>");
			str.append("<col width='3%'/>");
			str.append("<col width='20%'/>");
			
			str.append("<tr>");
			str.append("<td>Stock<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='stock' name='stock'  readonly='readonly' rows='4' cols='20' >"+size+"</textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			int totalQuantity = saleDAO.getTotalQuantityP_size(subCategoryID);
			
			if(loginInfo.getId()>1){
				totalQuantity = purchaseDAO.getShopStock(Integer.toString(subCategoryID),loginInfo.getId());
				
			}
			
			str.append("<tr>");
			str.append("<td>Stock Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='stockQuantity' name='stockQuantity' value = "+totalQuantity+"  readonly='readonly' size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
		
			str.append("<tr>");
			str.append("<td>Model No.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='modelnumber' name='modelNumber' value = "+purchase.getModelNumber()+" title='Enter Model Number' readonly='readonly'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Article No.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='articlenumber' name='articleNumber' value="+purchase.getArticleNumber()+" title='Enter Article Number' readonly='readonly'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Add Color<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<select name='colorName' id='adcolor'>");
			//ArrayList<Color>colorList = purchaseDAO.getColorList();
			str.append("<option value='0'>Select Color</option>");
			for(Color color : tempColorList){
				str.append("<option value='"+color.getColorId()+"'>"+color.getColorName()+"</option>");
			}
			str.append("</select>");
			str.append("<input type='button' value='Add' id='adcolorbtn' onclick='addColor()' />");
			str.append("<input type='button' value='Remove' id='rmovecolorbtn' onclick='removeColor()'/>");
			str.append("</td>");
			str.append("</tr>");
			
			
			str.append("<tr>");
			str.append("<td>Color.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='color' name='color' readonly='readonly'/>"+selectedColor+"</textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Price<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='price' value="+mrp+" name='price' title='Enter Price'  size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Add Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='adsizeajax'>");
			str.append("<select name='adsize' id='adsize'>");
			str.append("<option value='0'>Select</option>");
			tempSizeList = saleDAO.getTempSizeListByColor(subCategoryID,selectedColor);
			for(Size size2 : tempSizeList){
				str.append("<option value='"+size2.getId()+"'>"+size2.getProductSize()+"</option>");
			}
			
			str.append("</select>");
			str.append("<input type='button' id='adsizebtn' value='Add' onclick='addSize();'/>");
			str.append("<input type='button' id='rmovesizebtn' value='Remove' onclick='RemoveSize();'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>View Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='viewsize' name='viewsize' readonly='readonly'>"+productSize+"</textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='quantity' name='quantity' value='"+clickCount+"'  readonly='readonly' size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Image<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='imgview'>");
			str.append("<img style='width: 150px;height: 100px;' src = 'image/"+purchase.getUloadedImage()+"'>");
			str.append("</td>");
			str.append("</tr>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return null;
	}
	
	
	
	public String stock() throws SQLException{
		
		int categoryID = 0;
		if(Integer.parseInt(request.getParameter("categoryD"))!=0){
			 categoryID = Integer.parseInt(request.getParameter("categoryD"));
		}else{
			categoryID = (Integer)session.getAttribute("categoryID");
		}
		
		int subCategoryID = Integer.parseInt(request.getParameter("subcategoryID"));
		Connection connection = null;
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		try{
			connection = Connection_provider.getconnection();
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			SaleDAO saleDAO = new JDBCSaleDAO(connection);
			Purchase purchase = purchaseDAO.getProductDetails(categoryID,subCategoryID);
			//int del = saleDAO.deleteTempSize();
			double mrp = purchaseDAO.getPrice(categoryID,subCategoryID);
			
			ArrayList<Size> sizeList = purchaseDAO.getProductSize(subCategoryID,loginInfo.getUserId());
			ArrayList<Size>sizeIDList = purchaseDAO.getProductIDList(subCategoryID);
			
			for(Size size2 : sizeList){
				if(sizeIDList.size() > 0){
					for(Size sizeID : sizeIDList){
						if(size2.getId() != Integer.parseInt(sizeID.getSizeID())){
							boolean checkTempProductID = purchaseDAO.checkTempProductID(size2.getId());
							boolean checS_sizeid = saleDAO.chechs_sizeProductID(size2.getId());
							if(!checkTempProductID && ! checS_sizeid){
								int result = purchaseDAO.saveTempSize(size2);
							}
							
						}
					}
				}else{
					int result = purchaseDAO.saveTempSize(size2);
				}
				
				
			}
			
			StringBuffer sizeBuffer = new StringBuffer();
			StringBuffer psizeBuffer = new StringBuffer();
			ArrayList<Color>tempColorList = saleDAO.getTempColorList(subCategoryID);
			ArrayList<Size>tempSizeList = new ArrayList<Size>();
			
			for(Color color : tempColorList){
				psizeBuffer = new StringBuffer(); 
				tempSizeList = saleDAO.getTempSizeListByColor(subCategoryID,color.getColorName());
				
				for(Size size : tempSizeList){
					psizeBuffer.append( size.getProductSize() +  ",");
				}
				sizeBuffer.append(color.getColorName() + ":->" + psizeBuffer.toString());
			}
			
			String size = "";
			String colorSize = "";
			if(sizeBuffer.length()!=0){
				size = sizeBuffer.subSequence(0, sizeBuffer.length()-1).toString();
			}
		
			int deletebsize = purchaseDAO.deleBSize(loginInfo.getId());
			
		
			StringBuffer str = new StringBuffer();
			
			str.append("<col width='20%'/>");
			str.append("<col width='3%'/>");
			str.append("<col width='20%'/>");
			
			str.append("<tr>");
			str.append("<td>Stock<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='stock' name='stock'  readonly='readonly' rows='4' cols='20' >"+size+"</textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			
			if(loginInfo.getId()>1){
				int tempQuantity = purchaseDAO.getShopStock(Integer.toString(subCategoryID),loginInfo.getId());
				purchase.setQuantity(tempQuantity);
			}
			
			str.append("<tr>");
			str.append("<td>Stock Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='stockQuantity' name='stockQuantity' value = "+purchase.getQuantity()+"  readonly='readonly' size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
		
			str.append("<tr>");
			str.append("<td>Model No.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='modelnumber' name='modelNumber' value = "+purchase.getModelNumber()+" title='Enter Model Number' readonly='readonly'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Article No.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='articlenumber' name='articleNumber' value="+purchase.getArticleNumber()+" title='Enter Article Number' readonly='readonly'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Add Color<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<select name='colorName' id='adcolor'>");
			//ArrayList<Color>colorList = purchaseDAO.getColorList();
			str.append("<option value='0'>Select Color</option>");
			for(Color color : tempColorList){
				str.append("<option value='"+color.getColorId()+"'>"+color.getColorName()+"</option>");
			}
			str.append("</select>");
			str.append("<input type='button' value='Add' id='adcolorbtn' onclick='addColor()' />");
			str.append("<input type='button' value='Remove' id='rmovecolorbtn' onclick='removeColor()'/>");
			str.append("</td>");
			str.append("</tr>");
			
			
			str.append("<tr>");
			str.append("<td>Color.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='color' name='color' readonly='readonly'/></textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Price<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='price' value="+mrp+" name='price' title='Enter Price'  size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Add Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='adsizeajax'>");
			str.append("<select name='adsize' id='adsize'>");
			str.append("<option value='0'>Select</option>");
			
			str.append("</select>");
			str.append("<input type='button' id='adsizebtn' value='Add' onclick='addSize();'/>");
			str.append("<input type='button' id='rmovesizebtn' value='Remove' onclick='RemoveSize();'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>View Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='viewsize' name='viewsize' readonly='readonly'></textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='quantity' name='quantity'  readonly='readonly' size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Image<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='imgview'>");
			str.append("<img style='width: 150px;height: 100px;' src = 'image/"+purchase.getUloadedImage()+"'>");
			str.append("</td>");
			str.append("</tr>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return null;
	}
	
	
	
	
	
	public String set() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			int categoryID = Integer.parseInt(request.getParameter("categoryD"));
			int subCategoryID = Integer.parseInt(request.getParameter("subcategoryID"));
			int gender = Integer.parseInt(request.getParameter("gender"));
			
			SaleDAO saleDAO = new JDBCSaleDAO(connection);
			AdminSubcategoryDAO adminSubcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			
			Purchase purchase = new Purchase();
			purchase.setProductId(subCategoryID);
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double mrp = Double.parseDouble(request.getParameter("price"));
			String viewsize = request.getParameter("viewsize");
			String viewsizeid = request.getParameter("viewsizeid");
			String colorSize = request.getParameter("colorsize");
			String productidseries = request.getParameter("productidseries");
			boolean chb = Boolean.parseBoolean(request.getParameter("chb"));
			
			purchase.setMrp(mrp);
			purchase.setSize(viewsize);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			purchase.setCategoryid(categoryID);
			purchase.setProductidseries(productidseries);
			if(chb == true){
				purchase.setProductidseries(viewsizeid);
			}
			
			
			String tempProductSize[] = purchase.getSize().split(",");
			String tempProductIDSeries[] = purchase.getProductidseries().split(",");
			for(int i=0;i<tempProductSize.length;i++){
				boolean chechs_size = saleDAO.chechs_size(tempProductIDSeries[i],colorSize);
				if(!chechs_size){
					int result = saleDAO.saveS_size(colorSize,subCategoryID,tempProductSize[i],tempProductIDSeries[i],loginInfo.getId());
				}
				
			}
			
			ArrayList<Size> s_sizeList = saleDAO.getS_SizeList(subCategoryID,colorSize,loginInfo.getId());
			StringBuffer resultProductSize = new StringBuffer();
			StringBuffer resultProductSizeID = new StringBuffer();
			
			for(Size size : s_sizeList){
				resultProductSize.append(size.getProductSize() + ",");
				resultProductSizeID.append(size.getSizeID() + ",");
			}
			if(resultProductSize.length() > 0){
				purchase.setSize(resultProductSize.substring(0,resultProductSize.length()-1));
				purchase.setProductidseries(resultProductSizeID.substring(0,resultProductSizeID.length()-1));
			}
			
			String actualSize = "";
			String temp[] = purchase.getSize().split(",");
			
			
			/*for(int i=0;i<temp.length;i++){
				actualSize = actualSize + purchaseDAO.getActualSize(temp[i], gender) + ",";
			}*/
			
			quantity = s_sizeList.size();
			double total = quantity*mrp;
			purchase.setQuantity(quantity);
			purchase.setTotal(total);
			purchase.setColorSize(colorSize);
			purchase.setActualSize(actualSize);
			
			boolean isProductIDExist = purchaseDAO.checkProductExist(categoryID,subCategoryID,loginInfo.getUserId(),colorSize);
			if(isProductIDExist){
				int result = purchaseDAO.updateSale(purchase,subCategoryID,loginInfo.getUserId());
				
			}else{
				int rsult = purchaseDAO.save(purchase,loginInfo.getUserId());
			}
			
			double payAmount = 0;
			//ArrayList<Sale>saleList = saleDAO.getSaleList();
			ArrayList<Purchase>purchaseList = purchaseDAO.getPurchaseList(loginInfo.getUserId());
			
			StringBuffer str = new StringBuffer();
			
			//str.append("<table width='100%' style='border: 1px solid #e69623; height: auto; padding: 1em; font-size: 12px; overflow: hidden; display: inline-block;white-space: nowrap; table-layout: fixed'>");
			str.append("<table id='saletable' width='100%' style='border: 1px solid #e69623;'>");
			str.append("<input type='hidden' name='itemsize' id='itemsize' value='"+purchaseList.size()+"'>");
			str.append("<col width='5%'/>"); 
			str.append("<col width='20%'/>");
			str.append("<col width='10%'/>");
			str.append("<col width='20%'/>");
			str.append("<col width='10%'/>");
			str.append("<col width='10%'/>");
			str.append("<col width='10%'/>");
			str.append("<tr>");
			str.append("<th></th>");
			str.append("<th align='left'>Product Name</th>");
			str.append("<th align='left'>Color</th>");
			str.append("<th align='left'>Size</th>");
			str.append("<th align='left'>M.R.P</th>");
			str.append("<th align='left'>Quantity</th>");
			str.append("<th align='left'>Total</th>");
			str.append("</tr>");
			for(Purchase purchase2 : purchaseList){
				
				String dbViewSize = "";
				if(purchase2.getAccessories() == 0){
					String tempActualSize[] = purchase2.getActualSize().split(",");
					String tempViewSize[] = purchase2.getSize().split(",");
					System.out.println(tempViewSize.length);
				
						/*for(int i=0;i<tempViewSize.length;i++){
							dbViewSize = dbViewSize + "(" + tempActualSize[i] +  ")" + tempViewSize[i] + ",";
						}
						
						dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);*/
					for(int i=0;i<tempViewSize.length;i++){
						dbViewSize = dbViewSize + tempViewSize[i] + ",";
					}
					dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);
				}
				
				
				payAmount = payAmount+purchase2.getTotal();
				
				str.append("<tr>");
				str.append("<td><input class='case' type='checkbox' name='cksale'  value="+purchase2.getId()+"></td>");
				str.append("<td style='padding-left: 10px;'>"+purchase2.getProductName()+"</td>");
				if(purchase2.getAccessories() == 0){
					str.append("<td style='padding-left: 0px;'>"+purchase2.getColorSize()+"</td>");
				}else{
					str.append("<td style='padding-left: 0px;'></td>");
				}
				str.append("<td style='padding-left: 0px;'>"+dbViewSize+"</td>");
				str.append("<td style='padding-left: 0px;'>"+purchase2.getMrp()+"</td>");
				str.append("<td style='padding-left: 20px;'>"+purchase2.getQuantity()+"</td>");
				str.append("<td>"+purchase2.getTotal()+"</td>");
				str.append("</tr>");
				
			}
			
			str.append("<input type='hidden' name='hiddenpay' id='hiddenpay' value='"+payAmount+"'>");
			
			/*double per  = (Constants.DEFAULT_VAT*payAmount)/100;
			payAmount = payAmount-per;*/
			
			str.append("<tr>");
			str.append("<td colspan='7'><hr></hr></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("<td colspan='7'>");
			str.append("<table width='100%'>");
			str.append("<col width='20%'/>");
			str.append("<col width='20%'/>");
			str.append("<col width='20%'/>");
			str.append("<col width='10%'/>");
			str.append("<tr>");
			str.append("<td><b>Amount to Pay</b></td>");
			str.append("<td>Disc <input type='text' name='vat' id='vat' size='3' maxlength='3' style='text-align: center;' value='"+Constants.DEFAULT_VAT+"' onchange='addVat(this.value)'>%</td>");
			str.append("<td>Rs. <input type='text' name='rupee' id='rupee' size='3' maxlength='5' style='text-align: center;' value='"+Constants.DEFAULT_VAT+"' onchange='addRate(this.value)'></td>");
			str.append("<td id='pay'>"+payAmount+"</td>");
			str.append("</tr>");
			str.append("</table>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("</table>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	
	public String delete() throws SQLException{
		
		String selectedItem = request.getParameter("selectedItem");
		
		String temp[] = selectedItem.split(",");
		
		
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			SaleDAO saleDAO = new JDBCSaleDAO(connection);
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			
			for(int i=0;i<temp.length;i++){
				int delete = purchaseDAO.deleteSelectedItem(temp[i]);
			}
			
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			//ArrayList<Sale>saleList = saleDAO.getSaleList();
			ArrayList<Purchase>purchaseList = purchaseDAO.getPurchaseList(loginInfo.getUserId());
			
			StringBuffer str = new StringBuffer();
			
			double payAmount = 0;
			
			str.append("<table id='saletable' width='100%' style='border: 1px solid #e69623;'>");
			str.append("<input type='hidden' name='itemsize' id='itemsize' value='"+purchaseList.size()+"'>");
			str.append("<col width='5%'/>");
			str.append("<col width='20%'/>");
			str.append("<col width='10%'/>");
			str.append("<col width='20%'/>");
			str.append("<col width='10%'/>");
			str.append("<col width='10%'/>");
			str.append("<col width='10%'/>");
			str.append("<tr>");
			str.append("<th></th>");
			str.append("<th align='left'>Product Name</th>");
			str.append("<th align='left'>Color</th>");
			str.append("<th align='left'>Size</th>");
			str.append("<th align='left'>M.R.P</th>");
			str.append("<th align='left'>Quantity</th>");
			str.append("<th align='left'>Total</th>");
			str.append("</tr>");
			for(Purchase purchase2 : purchaseList){
				
				String dbViewSize = "";
				
				if(purchase2.getAccessories() == 0){
					String tempActualSize[] = purchase2.getActualSize().split(",");
					String tempViewSize[] = purchase2.getSize().split(",");
					System.out.println(tempViewSize.length);
				
						/*for(int i=0;i<tempViewSize.length;i++){
							dbViewSize = dbViewSize + "(" + tempActualSize[i] +  ")" + tempViewSize[i] + ",";
						}
						
						dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);*/
					
					for(int i=0;i<tempViewSize.length;i++){
						dbViewSize = dbViewSize + tempViewSize[i] + ",";
					}
					dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);
				
				}
			
				
				payAmount = payAmount+purchase2.getTotal();
				
				str.append("<tr>");
				str.append("<td><input class='case' type='checkbox' name='cksale'  value="+purchase2.getId()+"></td>");
				str.append("<td style='padding-left: 10px;'>"+purchase2.getProductName()+"</td>");
				if(purchase2.getAccessories() == 0){
					str.append("<td style='padding-left: 0px;'>"+purchase2.getColorSize()+"</td>");
				}else{
					str.append("<td style='padding-left: 0px;'></td>");
				}
				str.append("<td style='padding-left: 0px;'>"+dbViewSize+"</td>");
				str.append("<td style='padding-left: 0px;'>"+purchase2.getMrp()+"</td>");
				str.append("<td style='padding-left: 20px;'>"+purchase2.getQuantity()+"</td>");
				str.append("<td>"+purchase2.getTotal()+"</td>");
				str.append("</tr>");
				
			}
			
			
			str.append("<input type='hidden' name='hiddenpay' id='hiddenpay' value='"+payAmount+"'>");
			
		/*	double per  = (Constants.DEFAULT_VAT*payAmount)/100;
			payAmount = payAmount-per;*/
			
			str.append("<tr>");
			str.append("<td colspan='7'><hr></hr></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("<td colspan='7'>");
			str.append("<table width='100%'>");
			str.append("<col width='20%'/>");
			str.append("<col width='20%'/>");
			str.append("<col width='20%'/>");
			str.append("<col width='10%'/>");
			str.append("<tr>");
			str.append("<td><b>Amount to Pay</b></td>");
			str.append("<td>Discount <input type='text' name='vat' id='vat' size='3' maxlength='3' style='text-align: center;' value='"+Constants.DEFAULT_VAT+"' onchange='addVat(this.value)'>%</td>");
			str.append("<td>Rs. <input type='text' name='rupee' id='rupee' size='3' maxlength='5' style='text-align: center;' value='"+Constants.DEFAULT_VAT+"' onchange='addRate(this.value)'></td>");
			str.append("<td id='pay'>"+payAmount+"</td>");
			str.append("</tr>");
			str.append("</table>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}

	
	
/*------------------------------------------barcode coding start------------------------------------   */
	
	
	
public String barcode() throws SQLException{
		
		String articleName = request.getParameter("articleName");
		String barcodesize = request.getParameter("barcodesize");
		String barcodeprice = request.getParameter("barcodeprice");
		String barcodecolor = request.getParameter("barcodecolor");
		String globelpsizeid = request.getParameter("globelpsizeid");
		
		Connection connection = null;
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		try{
			connection = Connection_provider.getconnection();
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			SaleDAO saleDAO = new JDBCSaleDAO(connection);
			
			
			String productid = purchaseDAO.getBarcodeProductId(globelpsizeid);
			
			Purchase purchase = purchaseDAO.getProductByName(productid);
			
			ArrayList<Size> list = purchaseDAO.getBarcodeProductSize(purchase.getProductId(),loginInfo.getUserId(),globelpsizeid);
			//int del = saleDAO.deleteTempSize();
			//int delete = purchaseDAO.deleBSize();
			//int pdelete = purchaseDAO.deletePurchase();

			ArrayList<Size>sizeIDList = purchaseDAO.getProductIDList(purchase.getProductId());
			boolean checkProductForTempsize = purchaseDAO.checkProductForTempsize(purchase.getProductId());
			if(!checkProductForTempsize){
				for(Size size : list){
					if(sizeIDList.size() > 0){
						for(Size sizeID : sizeIDList){
							if(size.getId() != Integer.parseInt(sizeID.getSizeID())){
								boolean checkTempProductID = purchaseDAO.checkTempProductID(size.getId());
								boolean checS_sizeid = saleDAO.chechs_sizeProductID(size.getId());
								if(!checkTempProductID && ! checS_sizeid){
									int result = purchaseDAO.saveTempSize(size);
								}
							}
							
						}
					}else{
						int result = purchaseDAO.saveTempSize(size);
					}
				
					
				}
			}
			
		
			/*ArrayList<Category>categoryList = purchaseDAO.getCategoryList(loginInfo.getUserId());
			ArrayList<Product>productList = purchaseDAO.getProductList(purchase.getCategoryid());*/
			
			StringBuffer str = new StringBuffer();
			str.append("<input type='hidden' name='hdncatname' id='hdncatid' value='"+purchase.getCategoryid()+"'/>");
			str.append("<input type='hidden' name='hdnprodname' id='hdnprodid' value='"+purchase.getProductId()+"'/>");
			str.append("<input type='hidden' name='gender' id='gender' value='"+purchase.getGender()+"'/>");
			
			String gender = "";
			if(purchase.getGender()==1){
				gender = "Male";
			}else{
				gender = "Female";
			}
			
			str.append("<tr>");
			str.append("<td>SubCategory<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='productajax'>");
			str.append("<input type='text' name='gendertext' id='gendertext' value='"+gender+"' readonly='readonly'>");
			str.append("</td>");
			str.append("</tr>");
			
			
			str.append("<col width='10%'/>");
			str.append("<col width='2%'/>");
			str.append("<col width='20%'/>");
			str.append("<tr>");
			str.append("<td>Category<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' name='categoryD' id='categoryID' value='"+purchase.getCategoryName()+"' readonly='readonly'>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>SubCategory<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='productajax'>");
			str.append("<input type='text' name='productName' id='subCategoryID' value='"+purchase.getProductName()+"' readonly='readonly'>");
			str.append("</td>");
			str.append("</tr>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	
	
		
		
		
	
/*public String bstock() throws SQLException{
		
	int categoryID = 0;
	if(Integer.parseInt(request.getParameter("categoryD"))!=0){
		 categoryID = Integer.parseInt(request.getParameter("categoryD"));
	}else{
		categoryID = (Integer)session.getAttribute("categoryID");
	}
	
	int subCategoryID = Integer.parseInt(request.getParameter("subcategoryID"));
	Connection connection = null;
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	try{
		connection = Connection_provider.getconnection();
		AdminSubcategoryDAO adminSubcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
		int stock = adminSubcategoryDAO.getAdminProductStock(categoryID,subCategoryID);
		
		PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
		SaleDAO saleDAO = new JDBCSaleDAO(connection);
		Purchase purchase = purchaseDAO.getProductDetails(categoryID,subCategoryID);
		double mrp = purchaseDAO.getPrice(categoryID,subCategoryID);
		
		
		StringBuffer sizeBuffer = new StringBuffer();
		ArrayList<Size>tempSizeList = saleDAO.getTempProductSize(subCategoryID);
		StringBuffer psizrBuffer = new StringBuffer();
		
		for(Size size : tempSizeList){
			sizeBuffer.append(size.getProductSize() + ",");
			
		}
		String size = "";
		String colorSize = "";
		if(sizeBuffer.length()!=0){
			size = sizeBuffer.subSequence(0, sizeBuffer.length()-1).toString();
		}
	
		
	
	
		StringBuffer str = new StringBuffer();
		
		str.append("<col width='20%'/>");
		str.append("<col width='3%'/>");
		str.append("<col width='20%'/>");
		
		str.append("<tr>");
		str.append("<td>Stock<span class='reqd-info'>*</span></td>");
		str.append("<td align='center'>:</td>");
		str.append("<td>");
		str.append("<textarea id='stock' name='stock'  readonly='readonly' rows='4' cols='20' >"+size+"</textarea>");
		str.append("</td>");
		str.append("</tr>");
		
		str.append("<tr>");
		str.append("<td>Stock Color<span class='reqd-info'>*</span></td>");
		str.append("<td align='center'>:</td>");
		str.append("<td>");
		str.append("<textarea id='stockcolor' name='stockcolor'  readonly='readonly'  >"+colorSize+"</textarea>");
		str.append("</td>");
		str.append("</tr>");
		
		str.append("<tr>");
		str.append("<td>Stock Quantity<span class='reqd-info'>*</span></td>");
		str.append("<td align='center'>:</td>");
		str.append("<td>");
		str.append("<input type='text' id='stockQuantity' name='stockQuantity' value = "+purchase.getQuantity()+"  readonly='readonly' size='5' style='text-align: center;'/>");
		str.append("</td>");
		str.append("</tr>");
	
		str.append("<tr>");
		str.append("<td>Model No.<span class='reqd-info'>*</span></td>");
		str.append("<td align='center'>:</td>");
		str.append("<td>");
		str.append("<input type='text' id='modelnumber' name='modelNumber' value = "+purchase.getModelNumber()+" title='Enter Model Number' readonly='readonly'/>");
		str.append("</td>");
		str.append("</tr>");
		
		str.append("<tr>");
		str.append("<td>Article No.<span class='reqd-info'>*</span></td>");
		str.append("<td align='center'>:</td>");
		str.append("<td>");
		str.append("<input type='text' id='articlenumber' name='articleNumber' value="+purchase.getArticleNumber()+" title='Enter Article Number' readonly='readonly'/>");
		str.append("</td>");
		str.append("</tr>");
		
		str.append("<tr>");
		str.append("<td>Add Color<span class='reqd-info'>*</span></td>");
		str.append("<td align='center'>:</td>");
		str.append("<td>");
		str.append("<select name='colorName' id='adcolor'>");
		ArrayList<Color>colorList = purchaseDAO.getColorList();
		str.append("<option value='0'>Select Color</option>");
		for(Color color : colorList){
			str.append("<option value='"+color.getColorId()+"'>"+color.getColorName()+"</option>");
		}
		str.append("</select>");
		str.append("<input type='button' value='Add' id='adcolorbtn' onclick='addColor()' disabled='disabled'/>");
		str.append("<input type='button' value='Remove' id='rmovecolorbtn' onclick='removeColor()' disabled='disabled' />");
		str.append("</td>");
		str.append("</tr>");
		
		
		str.append("<tr>");
		str.append("<td>Color.<span class='reqd-info'>*</span></td>");
		str.append("<td align='center'>:</td>");
		str.append("<td>");
		str.append("<textarea id='color' name='color' readonly='readonly'/></textarea>");
		str.append("</td>");
		str.append("</tr>");
		
		
		str.append("<tr>");
		str.append("<td>Price<span class='reqd-info'>*</span></td>");
		str.append("<td align='center'>:</td>");
		str.append("<td>");
		str.append("<input type='text' id='price' value="+mrp+" name='price' title='Enter Price'  size='5' style='text-align: center;'/>");
		str.append("</td>");
		str.append("</tr>");
		
		str.append("<tr>");
		str.append("<td>Add Size<span class='reqd-info'>*</span></td>");
		str.append("<td align='center'>:</td>");
		str.append("<td id='adsizeajax'>");
		str.append("<select name='adsize' id='adsize'>");
		str.append("<option value='0'>Select</option>");
		for(Size size2 : tempSizeList){
			str.append("<option value='"+size2.getId()+"'>"+size2.getProductSize()+"</option>");
		}
		
		str.append("</select>");
		str.append("<input type='button' id='adsizebtn' value='Add' onclick='addSize();'/>");
		str.append("<input type='button' id='rmovesizebtn' value='Remove' onclick='RemoveSize();'/>");
		str.append("</td>");
		str.append("</tr>");
		
		str.append("<tr>");
		str.append("<td>View Size<span class='reqd-info'>*</span></td>");
		str.append("<td align='center'>:</td>");
		str.append("<td>");
		str.append("<textarea id='viewsize' name='viewsize' readonly='readonly'></textarea>");
		str.append("</td>");
		str.append("</tr>");
		
		str.append("<tr>");
		str.append("<td>Quantity<span class='reqd-info'>*</span></td>");
		str.append("<td align='center'>:</td>");
		str.append("<td>");
		str.append("<input type='text' id='quantity' name='quantity'  readonly='readonly' size='5' style='text-align: center;'/>");
		str.append("</td>");
		str.append("</tr>");
		
		str.append("<tr>");
		str.append("<td>Image<span class='reqd-info'>*</span></td>");
		str.append("<td align='center'>:</td>");
		str.append("<td id='imgview'>");
		str.append("<img style='width: 150px;height: 100px;' src = '/inventorymanagement/image/"+purchase.getUloadedImage()+"'>");
		str.append("</td>");
		str.append("</tr>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+""); 
		
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
		
	}
*/	
	
	public String swap() throws SQLException{
		
		Connection connection = null;
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		try{
			connection = Connection_provider.getconnection();
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			SaleDAO saleDAO = new JDBCSaleDAO(connection);
			
			StringBuffer str = new StringBuffer();
			str.append("<col width='10%'/>");
			str.append("<col width='2%'/>");
			str.append("<col width='20%'/>");
			
			str.append("<tr>");
			str.append("<td>Select Gender<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<select name='gender' id='gender' onchange='setGenderAjax(this.value)' >");
			str.append("<option value='0'>Select Gender</option>");
			str.append("<option value='1'>Gents</option>");
			str.append("<option value='2'>Ladies</option>");
			str.append("<option value='3'>Accessories</option>");
			str.append("</select>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Category<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='gendercategory'>");
			str.append("<select name='category'>");
			str.append("<option value='0'>Select Category</option>");
			str.append("</select>");
			str.append("</td>");
			str.append("</tr>");
			
			
			str.append("<tr>");
			str.append("<td>Product Name<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='productajax'>");
			str.append("<select name='productName'>");
			str.append("<option value='0'>Select SubCategory</option>");
			str.append("</select>");
			str.append("</td>");
			str.append("</tr>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return null;
	}
	
	public String baddsize(){
		String sizeID = "";
		
		String productSize = request.getParameter("productSize");
		String articleName = request.getParameter("articleName");
		String barcodecolor = request.getParameter("barcodecolor");
		String globelpsizeid = request.getParameter("globelpsizeid");
		
		String price = request.getParameter("price");
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		
		double mrp = Double.parseDouble(price);
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			SaleDAO saleDAO = new JDBCSaleDAO(connection);
			
			String productid = purchaseDAO.getBarcodeProductId(globelpsizeid);
			
			Purchase purchase1 = purchaseDAO.getProductByName(productid);
			
			Purchase purchase = purchaseDAO.getProductDetails(purchase1.getCategoryid(),purchase1.getProductId());
			
			//ArrayList<Size>sizeList = purchaseDAO.getProductSize(purchase1.getProductId(), loginInfo.getUserId());
			
			ArrayList<Size>tempSizeList = saleDAO.getTempProductSize(purchase1.getProductId(),barcodecolor);
			
			for(Size size2 : tempSizeList){
				if(size2.getProductSize().equals(productSize) && size2.getColorSize().equals(barcodecolor)){
					sizeID = Integer.toString(size2.getId());
					break;
				}
			}
			
			
			
			int delete = purchaseDAO.deleteTempSize(sizeID);
			
			int result = purchaseDAO.saveB_size(purchase1.getProductId(), productSize,Integer.parseInt(sizeID),barcodecolor,loginInfo.getId());
			
			StringBuffer sizeBuffer = new StringBuffer();
			StringBuffer psizrBuffer = new StringBuffer(); 
			
			tempSizeList = saleDAO.getTempProductSize(purchase1.getProductId(),barcodecolor);
			ArrayList<Color>tempSizeColorList = saleDAO.getTemp_sizeColorList(purchase1.getProductId());
			ArrayList<Size>tempProductSizeList = new ArrayList<Size>();
			
			for(Color color : tempSizeColorList){
				psizrBuffer = new StringBuffer();
				tempProductSizeList = saleDAO.getTempProductSizeList(purchase1.getProductId(),loginInfo.getUserId(),color.getColorName());
				for(Size size : tempProductSizeList){
					psizrBuffer.append( size.getProductSize() +  ",");
					
				}
				
				sizeBuffer.append(color.getColorName() + ":->" + psizrBuffer.toString());
			}
			
			String size = "";
			
			if(sizeBuffer.length()!=0){
				size = sizeBuffer.subSequence(0, sizeBuffer.length()-1).toString();
				
			}
			
			StringBuffer b_sizeBuffer = new StringBuffer();
			StringBuffer b_sizeIDBuffer = new StringBuffer();
			StringBuffer b_sizeColorBuffer = new StringBuffer();
			ArrayList<Size>b_sizeList = purchaseDAO.getBsizeList(purchase1.getProductId(),barcodecolor);
			
			String b_size = "";
			String b_sizeID = "";
			String b_sizecolor = "";
			for(Size size2 : b_sizeList){
				 b_sizeBuffer.append(size2.getProductSize() + ",");
				 b_sizeIDBuffer.append(size2.getSizeID() + ",");
				 b_sizeColorBuffer.append(size2.getColorSize() + ",");
			}
			if(b_sizeBuffer.length()!=0){
				b_size = b_sizeBuffer.substring(0,b_sizeBuffer.length()-1);
				b_sizeID = b_sizeIDBuffer.substring(0, b_sizeIDBuffer.length()-1);
				b_sizecolor = b_sizeColorBuffer.substring(0, b_sizeColorBuffer.length()-1);
			}
			
		
			
		
		
			StringBuffer str = new StringBuffer();
			
			str.append("<col width='20%'/>");
			str.append("<col width='3%'/>");
			str.append("<col width='20%'/>");
			
			str.append("<tr>");
			str.append("<td>Stock<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='stock' name='stock'  readonly='readonly' rows='4' cols='20'>"+size+"</textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			/*str.append("<tr>");
			str.append("<td>Stock Color<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='stockcolor' name='stockcolor'  readonly='readonly' >"+colorSize+"</textarea>");
			str.append("</td>");
			str.append("</tr>");*/
			
			int tempQuantity = saleDAO.getTempQuantity(purchase1.getProductId());
			if(loginInfo.getId()>1){
				tempQuantity = purchaseDAO.getShopStock(productid,loginInfo.getId());
			}
			
			str.append("<tr>");
			str.append("<td>Stock Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='stockQuantity' name='stockQuantity' value = "+tempQuantity+"  readonly='readonly' size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
		
			str.append("<tr>");
			str.append("<td>Model No.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='modelnumber' name='modelNumber' value = "+purchase.getModelNumber()+" title='Enter Model Number' readonly='readonly'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Article No.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='articlenumber' name='articleNumber' value="+purchase.getArticleNumber()+" title='Enter Article Number' readonly='readonly'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Add Color<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<select name='colorName' id='adcolor'>");
			ArrayList<Color>colorList = purchaseDAO.getColorList();
			str.append("<option value='0'>Select Color</option>");
			for(Color color : colorList){
				str.append("<option value='"+color.getColorId()+"'>"+color.getColorName()+"</option>");
			}
			str.append("</select>");
			str.append("<input type='button' value='Add' id='adcolorbtn' onclick='addColor()' disabled='disabled' />");
			str.append("<input type='button' value='Remove' id='rmovecolorbtn' onclick='removeColor()' disabled='disabled' />");
			str.append("</td>");
			str.append("</tr>");
			
			
			str.append("<tr>");
			str.append("<td>Color.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='color' name='color' readonly='readonly'/>"+barcodecolor+"</textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Price<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='price' value="+mrp+" name='price' title='Enter Price'  size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Add Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='adsizeajax'>");
			str.append("<select name='adsize' id='adsize'>");
			str.append("<option value='0'>Select</option>");
			for(Size size2 : tempSizeList){
				str.append("<option value='"+size2.getId()+"'>"+size2.getProductSize()+"</option>");
			}
			
			str.append("</select>");
			str.append("<input type='button' id='adsizebtn' value='Add' onclick='addSize();' disabled='disabled'/>");
			str.append("<input type='button' id='rmovesizebtn' value='Remove' onclick='removeBarcodeSize();'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>View Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='viewsize' name='viewsize'  readonly='readonly' >"+b_size+"</textarea>");
			str.append("<input type='hidden' nam='viewsizeid' id='viewsizeid' value='"+b_sizeID+"'>");
			str.append("</td>");
			str.append("</tr>");
			
			String temp[] = b_size.split(",");
			
			str.append("<tr>");
			str.append("<td>Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='quantity' name='quantity' value='"+temp.length+"'  readonly='readonly' size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Image<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='imgview'>");
			str.append("<img style='width: 150px;height: 100px;' src = 'image/"+purchase.getUloadedImage()+"'>");
			str.append("</td>");
			str.append("</tr>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+"");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public String brmovesize() throws SQLException{
		
		int productID = Integer.parseInt(request.getParameter("productID"));
		int categoryID = Integer.parseInt(request.getParameter("categoryD"));
		double mrp = Double.parseDouble(request.getParameter("price"));
		String barcodecolor = request.getParameter("barcodecolor");
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			SaleDAO saleDAO = new JDBCSaleDAO(connection);
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			
			Size sizes = saleDAO.getLastRecordBsize(productID);
			
			int result = saleDAO.saveLastRecordtempSize(sizes);
			
			int delete = saleDAO.deleteLastRecordBsize(productID);
			
			int deletes = saleDAO.deleteLastRecordSsize(sizes.getSizeID());
			
			Purchase purchase = purchaseDAO.getProductDetails(categoryID,productID);
			
			ArrayList<Size>tempSizeList = saleDAO.getTempProductSize(productID,barcodecolor);
			
			
			//StringBuffer sizeBuffer = new StringBuffer();
			
			/*for(Size size : tempSizeList){
				sizeBuffer.append(size.getProductSize() + "("+size.getColorSize()+")"+ ",");
			}
			String size = "";
			if(sizeBuffer.length()!=0){
				size = sizeBuffer.subSequence(0, sizeBuffer.length()-1).toString();
			}*/
			
			StringBuffer sizeBuffer = new StringBuffer();
			StringBuffer psizeBuffer = new StringBuffer();
			ArrayList<Color>tempColorList = saleDAO.getTempColorList(productID);
			//ArrayList<Size>tempSizeList = new ArrayList<Size>();
			
			for(Color color : tempColorList){
				psizeBuffer = new StringBuffer(); 
				tempSizeList = saleDAO.getTempSizeListByColor(productID,color.getColorName());
				
				for(Size size : tempSizeList){
					psizeBuffer.append( size.getProductSize() +  ",");
				}
				sizeBuffer.append(color.getColorName() + ":->" + psizeBuffer.toString());
			}
			
			String size = "";
			String colorSize = "";
			if(sizeBuffer.length()!=0){
				size = sizeBuffer.subSequence(0, sizeBuffer.length()-1).toString();
				
			}
		
			
			StringBuffer b_sizeBuffer = new StringBuffer();
			ArrayList<Size>b_sizeList = purchaseDAO.getBsizeList(productID,barcodecolor);
			StringBuffer b_sizeIDBuffer = new StringBuffer();
			StringBuffer b_sizeColorBuffer = new StringBuffer();
			
			
			String b_size = "";
			String b_sizeID = "";
			String b_sizeColor = "";
			for(Size size2 : b_sizeList){
				 b_sizeBuffer.append(size2.getProductSize() + ",");
				 b_sizeIDBuffer.append(size2.getSizeID() + ",");
				 b_sizeColorBuffer.append(size2.getColorSize() + ",");
			}
			if(b_sizeBuffer.length()!=0){
				b_size = b_sizeBuffer.substring(0,b_sizeBuffer.length()-1);
				b_sizeID = b_sizeIDBuffer.substring(0, b_sizeIDBuffer.length()-1);
				b_sizeColor = b_sizeColorBuffer.substring(0, b_sizeColorBuffer.length()-1);
				
			}
		
			
			String temp[] = b_size.split(",");
			
		
		
			StringBuffer str = new StringBuffer();
			
			str.append("<col width='20%'/>");
			str.append("<col width='3%'/>");
			str.append("<col width='20%'/>");
			
			str.append("<tr>");
			str.append("<td>Stock<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='stock' name='stock'  readonly='readonly' rows='4' cols='20' >"+size+"</textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Stock Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='stockQuantity' name='stockQuantity' value = "+tempSizeList.size()+"  readonly='readonly' size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
		
			str.append("<tr>");
			str.append("<td>Model No.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='modelnumber' name='modelNumber' value = "+purchase.getModelNumber()+" title='Enter Model Number' readonly='readonly'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Article No.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='articlenumber' name='articleNumber' value="+purchase.getArticleNumber()+" title='Enter Article Number' readonly='readonly'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Add Color<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<select name='colorName' id='adcolor'>");
			ArrayList<Color>colorList = purchaseDAO.getColorList();
			str.append("<option value='0'>Select Color</option>");
			for(Color color : colorList){
				str.append("<option value='"+color.getColorId()+"'>"+color.getColorName()+"</option>");
			}
			str.append("</select>");
			str.append("<input type='button' value='Add' id='adcolorbtn' onclick='addColor()' disabled='disabled'/>");
			str.append("<input type='button' value='Remove' id='rmovecolorbtn' onclick='removeColor()' disabled='disabled' />");
			str.append("</td>");
			str.append("</tr>");
			
			
			str.append("<tr>");
			str.append("<td>Color.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='color' name='color' readonly='readonly'/>"+barcodecolor+"</textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Price<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='price' value="+mrp+" name='price' title='Enter Price'  size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Add Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='adsizeajax'>");
			str.append("<select name='adsize' id='adsize'>");
			str.append("<option value='0'>Select</option>");
			for(Size size2 : tempSizeList){
				str.append("<option value='"+size2.getId()+"'>"+size2.getProductSize()+"</option>");
			}
			
			str.append("</select>");
			str.append("<input type='button' id='adsizebtn' value='Add' onclick='addSize();' disabled='disabled'/>");
			if(temp.length == 1){
				str.append("<input type='button' id='rmovesizebtn' value='Remove' onclick='removeBarcodeSize();' disabled = 'disabled'/>");
			}else{
				str.append("<input type='button' id='rmovesizebtn' value='Remove' onclick='removeBarcodeSize();'/>");
			}
			
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>View Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='viewsize' name='viewsize'  readonly='readonly' >"+b_size+"</textarea>");
			str.append("<input type='hidden' nam='viewsizeid' id='viewsizeid' value='"+b_sizeID+"'>");
			str.append("</td>");
			str.append("</tr>");
			
			
			
			
			str.append("<tr>");
			str.append("<td>Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='quantity' name='quantity' value='"+temp.length+"'  readonly='readonly' size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Image<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='imgview'>");
			str.append("<img style='width: 150px;height: 100px;' src = 'image/"+purchase.getUloadedImage()+"'>");
			str.append("</td>");
			str.append("</tr>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+"");
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	
	public String contain(){
		
		String articleName = request.getParameter("articleName");
		String size = request.getParameter("barcodesize");
		String barcodecolor = request.getParameter("barcodecolor");
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			//boolean checkProductExist = purchaseDAO.isProductExistByName(articleName,size,barcodecolor);
			
			String globelpsizeid = request.getParameter("globelpsizeid");
					
			
			boolean checkProductExist = purchaseDAO.checkBarcodeProductExist(globelpsizeid);
			
			if(!checkProductExist){
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("false");
				
			}else{
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("true");
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String addcolor() throws SQLException{
		 int subcatecoryid = Integer.parseInt(request.getParameter("productID"));
		 int categoryid = Integer.parseInt(request.getParameter("categoryD"));
		 String selectedColor = request.getParameter("selectedColor");
		 System.out.println(selectedColor);
		 
		 Connection connection = null;
		 StringBuffer str = new StringBuffer();
		 try{
			 connection = Connection_provider.getconnection();
			 SaleDAO saleDAO = new JDBCSaleDAO(connection);
			 
			 ArrayList<Size>tempSizeList = saleDAO.getTempSizeListByColor(subcatecoryid,selectedColor);
				str.append("<select name='adsize' id='adsize'>");
				str.append("<option value='0'>Select</option>");
				
				for(Size size2 : tempSizeList){
					str.append("<option value='"+size2.getId()+"'>"+size2.getProductSize()+"</option>");
				}
				
				str.append("</select>");
				str.append("<input type='button' id='adsizebtn' value='Add' onclick='addSize();'/>");
				//str.append("<input type='button' id='rmovesizebtn' value='Remove' onclick='RemoveSize();'/>");
				str.append("</td>");
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(str.toString());
			 
			 
		 }catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		 
		
		
		return null;
	}
	
	
	public ProductForm getModel() {
		
	
		return productForm;
	}



	public void prepare() throws Exception {
		Connection connection = null;
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		try{
			
			connection = Connection_provider.getconnection();
			ProductDAO productDAO = new JDBCProductDAO(connection);
			
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
			
			SalesManDAO salesManDAO = new JDBCSalesManDAO(connection);
			ArrayList<SalesMan>salesManList = salesManDAO.getSalesManList(loginInfo.getUserId());
			ArrayList<SalesMan>populatedSalesManList = new ArrayList<SalesMan>();
			
			for(SalesMan salesMan : salesManList){
				populatedSalesManList.add(new SalesMan(salesMan.getId(),salesMan.getFirstName()+" "+salesMan.getLastName()));
			}
			
			productForm.setPopulatedSalesManList(populatedSalesManList);
			
			ArrayList<Customer>customerList = salesManDAO.getCustomerList();
			productForm.setCustomerList(customerList);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	}

}
