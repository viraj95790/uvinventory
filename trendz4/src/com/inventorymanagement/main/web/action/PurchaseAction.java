package com.inventorymanagement.main.web.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.main.common.constants.Constants;
import com.inventorymanagement.main.eu.bi.AccessoriesDAO;
import com.inventorymanagement.main.eu.bi.AdminSubcategoryDAO;
import com.inventorymanagement.main.eu.bi.CategoryDAO;
import com.inventorymanagement.main.eu.bi.ProductDAO;
import com.inventorymanagement.main.eu.bi.PurchaseDAO;
import com.inventorymanagement.main.eu.bi.SaleDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCAccessoriesDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCAdminSubcategoryDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCCategoryDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCProductDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCPurchaseDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCSaleDAO;
import com.inventorymanagement.main.eu.entity.Accessories;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Color;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.Sale;
import com.inventorymanagement.main.eu.entity.Size;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.ProductForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PurchaseAction extends BaseAction implements Preparable, ModelDriven<ProductForm>{

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
			AccessoriesDAO accessoriesDAO = new JDBCAccessoriesDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ArrayList<Purchase>purchaseList = purchaseDAO.getPurchaseList(loginInfo.getUserId());
			
			Purchase purchase1 = new Purchase();
			purchase1.setCustomerName(productForm.getCustomerName());
			purchase1.setMobileNumber(productForm.getMobileNo());
			purchase1.setVat(productForm.getVat());
			
			int companyId = purchaseDAO.saveCompany(purchase1,loginInfo.getUserId());
			
			for(Purchase purchase : purchaseList){
				
					if(purchase.getAccessories() == 0){
						Product product = purchaseDAO.getProdutctDetails(purchase.getCategoryid(), purchase.getProductId(), loginInfo.getUserId());
						int currentQuantity = product.getQuantity()+purchase.getQuantity();
						String currentSize = "";
						currentSize = purchase.getSize();
						
						int update = purchaseDAO.updatePurchase(currentQuantity,currentSize,purchase.getProductId());
						String temp[] = currentSize.split(",");
						//String tempActualSize[] = purchase.getActualSize().split(",");
						
						for(int i=0;i<temp.length;i++){
							int result = purchaseDAO.saveSize(purchase,temp[i],"");
						}
					}else{
						Accessories accessories = accessoriesDAO.getAccessories(purchase.getProductId());
						int accessoriesStock = accessories.getQuantity() + purchase.getQuantity();
						int update = accessoriesDAO.updateAccessoriesStock(accessoriesStock,purchase.getProductId());
					}
				
					/*purchase.setVat(productForm.getVat());
					double dis = purchase.getTotal()*purchase.getVat()/100 ;
					double total = purchase.getTotal() - dis;
					purchase.setTotal(total);*/
					int result = purchaseDAO.savePurchaseInvoice(purchase,loginInfo.getUserId(),companyId);
					
					int delete = purchaseDAO.deletePurchase(loginInfo.getUserId());
					int deletebsize = purchaseDAO.deleBSize(loginInfo.getId());
			}
			
			int savepayment = purchaseDAO.savePurchasePayment(companyId,productForm.getHowpaid(),productForm.getPaymentrecieved(),productForm.getPaymentnote());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	/*public String getpurchaseprice(){
		Connection connection = null;
		String subcategoryid = request.getParameter("subcategoryID");
		try{
			connection = Connection_provider.getconnection();
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			double purchaseprice = purchaseDAO.getpurchseamount(subcategoryid);
			
			StringBuffer str = new StringBuffer();
			
			str.append("<td>");
			str.append("<input type='text' id='price' name='price' title='Enter Price'  size='5' style='text-align: center;' onchange='setvchkEnable()'/>");
			str.append("</td>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+"");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}*/
	
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
			
			productForm.setColorName("1");
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
	
	public void prepare() throws Exception {
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			
			ArrayList<Category> list = categoryDAO.getmainCategorylist();
			productForm.setMainCategory(list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public String sreturn() throws SQLException{
		
		
			
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
			/*	AdminSubcategoryDAO adminSubcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
				int stock = adminSubcategoryDAO.getAdminProductStock(categoryID,subCategoryID);*/
				
				PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
				Purchase purchase = purchaseDAO.getProductDetails(categoryID,subCategoryID);
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
				
				ArrayList<Size> sizeList = new ArrayList<Size>();
				ArrayList<Color>psizeColorList = purchaseDAO.getP_sizeColorList(subCategoryID);
				
				StringBuffer sizeBuffer = new StringBuffer();
				StringBuffer psizrBuffer = new StringBuffer(); 
				
				for(Color color : psizeColorList){
					psizrBuffer = new StringBuffer(); 
					
					sizeList = purchaseDAO.getProductSize(subCategoryID,loginInfo.getUserId(),color.getColorName());
					for(Size size : sizeList){
						psizrBuffer.append( size.getProductSize() +  ",");
						
					}
					
					sizeBuffer.append(color.getColorName() + ":->" + psizrBuffer.toString());
				}
				
				
				String size = "";
				
				if(sizeBuffer.length()!=0){
					size = sizeBuffer.substring(0, sizeBuffer.length()-1);
					
				}
				int deletebsize = purchaseDAO.deleBSize(loginInfo.getId());
				
				StringBuffer str = new StringBuffer();
				
				/*str.append("<col width='20%'/>");
				str.append("<col width='3%'/>");
				str.append("<col width='20%'/>");*/
				
				
				str.append("<tr>");
				str.append("<td>Price<span class='reqd-info'>*</span></td>");
				str.append("<td align='center'>:</td>");
				str.append("<td>");
				str.append("<input type='text' id='price' name='price' title='Enter Price'  size='5' style='text-align: center;' onchange='setvchkEnable()'/>");
				str.append("</td>");

				str.append("<td>Size<span class='reqd-info'>*</span></td>");
				str.append("<td align='center'>:</td>");
				str.append("<td>");
				str.append("<input size='5' type='text' id='viewsizetxt' name='viewsizetxt' style='text-align: center;' onchange='setpurchaseqsze()' />");
				str.append("<input type='hidden' id='viewsize' name='viewsize' />");
				str.append("</td>");
				str.append("</tr>");
				
				str.append("<tr>");
				str.append("<td>Quantity<span class='reqd-info'>*</span></td>");
				str.append("<td align='center'>:</td>");
				str.append("<td>");
				str.append("<input type='text' id='quantity' name='quantity'  onchange='setpurchase(this.value)' size='5'  style='text-align: center;' />");
				str.append("</td>");
			
				
				str.append("<td>Color<span class='reqd-info'>*</span></td>");
				str.append("<td align='center'>:</td>");
				str.append("<td>");
				str.append("<select onchange='addColor()' name='colorName' id='adcolor'>");
				ArrayList<Color>colorList = purchaseDAO.getColorList();
				str.append("<option value='0'>Select Color</option>");
				for(Color color : colorList){
					str.append("<option value='"+color.getColorId()+"'>"+color.getColorName()+"</option>");
				}
				str.append("</select>");
				/*str.append("<input type='button' value='Add' id='adcolorbtn' onclick='addColor()'/>");*/
				str.append("</td>");
				str.append("</tr>");
				
				
				/*str.append("<tr>");
				str.append("<td>Stock Color<span class='reqd-info'>*</span></td>");
				str.append("<td align='center'>:</td>");
				str.append("<td>");
				str.append("<textarea id='stockcolor' name='stockcolor'  readonly='readonly' >"+colorSize+"</textarea>");
				str.append("</td>");
				str.append("</tr>");*/
			
				str.append("<tr style='display:none'>");
				str.append("<td>Model No.<span class='reqd-info'>*</span></td>");
				str.append("<td align='center'>:</td>");
				str.append("<td>");
				str.append("<input type='text' id='modelnumber' name='modelNumber' value = '"+purchase.getModelNumber()+"' title='Enter Model Number' readonly='readonly'/>");
				str.append("</td>");
				str.append("</tr>");
				
				str.append("<tr style='display:none'>");
				str.append("<td>Article No.<span class='reqd-info'>*</span></td>");
				str.append("<td align='center'>:</td>");
				str.append("<td>");
				str.append("<input type='text' id='articlenumber' name='articleNumber' value='"+purchase.getArticleNumber()+"' title='Enter Article Number' readonly='readonly'/>");
				str.append("</td>");
				str.append("</tr>");
				
				
				
			
				
				
				str.append("<tr style='display:none'>");
				str.append("<td>Color.<span class='reqd-info'>*</span></td>");
				str.append("<td align='center'>:</td>");
				str.append("<td>");
				str.append("<textarea id='color' name='color' readonly='readonly'/></textarea>");
				str.append("</td>");
				
				str.append("<td >Stock<span class='reqd-info'>*</span></td>");
				str.append("<td align='center'>:</td>");
				str.append("<td>");
				str.append("<textarea id='stock' name='stock'  readonly='readonly' rows='4' cols='20' >"+size+"</textarea>");
				str.append("</td>");
				
				str.append("<td>Stock Quantity<span class='reqd-info'>*</span></td>");
				str.append("<td align='center'>:</td>");
				str.append("<td>");
				str.append("<input type='text' id='stockQuantity' name='stockQuantity' value = "+purchase.getQuantity()+"  readonly='readonly' size='5' style='text-align: center;'/>");
				str.append("</td>");
			
				str.append("</tr>");
			
				
				
				/*str.append("<tr>");
				str.append("<td>Add Size<span class='reqd-info'>*</span></td>");
				str.append("<td align='center'>:</td>");
				str.append("<td>");
				str.append("<select name='adsize' id='adsize' >");
				str.append("<option value='0'>Select</option>");
				str.append("<option value='05'>05</option>");
				str.append("<option value='06'>06</option>");
				str.append("<option value='07'>07</option>");
				str.append("<option value='08'>08</option>");
				str.append("<option value='09'>09</option>");
				str.append("<option value='10'>10</option>");
				str.append("<option value='11'>11</option>");
				str.append("<option value='12'>12</option>");
				str.append("</select>");
				str.append("<input type='button' id='adsizebtn' value='Add' onclick='addSize();'/>");
				str.append("<input type='button' id='rmovesizebtn' value='Remove' onclick='RemoveSize();'/>");
				str.append("</td>");
				str.append("</tr>");*/
				
				str.append("<tr>");
				
				
				str.append("<td>Image<span class='reqd-info'>*</span></td>");
				str.append("<td align='center'>:</td>");
				str.append("<td id='imgview'>");
				str.append("<img style='width: 150px;height: 100px;' src = 'image/"+purchase.getUloadedImage()+"'>");
				str.append("</td>");
				str.append("</tr>");
				
				
				boolean ckeckproductentry = purchaseDAO.checkProductEntry(subCategoryID);
				
				if(ckeckproductentry){
					response.setContentType("text/html");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(""+str.toString()+""); 
				}else{
					str = new StringBuffer();
					
					str.append("<tr style='color:red'>");
					str.append("<td>Error<span class='reqd-info'>*</span></td>");
					str.append("<td align='center'>:</td>");
					str.append("<td style='color:red'>");
					str.append("No entry in product!!");
					str.append("</td>");
					str.append("</tr>");
					
					response.setContentType("text/html");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(""+str.toString()+""); 
				}
				
				
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
		try{
			connection = Connection_provider.getconnection();
		/*	AdminSubcategoryDAO adminSubcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
			int stock = adminSubcategoryDAO.getAdminProductStock(categoryID,subCategoryID);*/
			
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			Purchase purchase = purchaseDAO.getProductDetails(categoryID,subCategoryID);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			
			ArrayList<Size> sizeList = new ArrayList<Size>();
			ArrayList<Color>psizeColorList = purchaseDAO.getP_sizeColorList(subCategoryID);
			
			StringBuffer sizeBuffer = new StringBuffer();
			StringBuffer psizrBuffer = new StringBuffer(); 
			
			for(Color color : psizeColorList){
				psizrBuffer = new StringBuffer(); 
				
				sizeList = purchaseDAO.getProductSize(subCategoryID,loginInfo.getUserId(),color.getColorName());
				for(Size size : sizeList){
					psizrBuffer.append( size.getProductSize() +  ",");
					
				}
				
				sizeBuffer.append(color.getColorName() + ":->" + psizrBuffer.toString());
			}
			
			
			String size = "";
			
			if(sizeBuffer.length()!=0){
				size = sizeBuffer.substring(0, sizeBuffer.length()-1);
				
			}
			int deletebsize = purchaseDAO.deleBSize(loginInfo.getId());
			
			StringBuffer str = new StringBuffer();
			
			/*str.append("<col width='20%'/>");
			str.append("<col width='3%'/>");
			str.append("<col width='20%'/>");*/
			
			str.append("<tr>");
			str.append("<td>Stock<span class='reqd-info'></span></td>");
			str.append("<td>Color.<span class='reqd-info'></span></td>");
			str.append("<td>Image.<span class='reqd-info'></span></td>");
			str.append("</tr>");
			
			str.append("<tr>");
			
			
			str.append("<td>");
			str.append("<textarea id='stock' name='stock'  readonly='readonly' rows='4' cols='20' >"+size+"</textarea>");
			str.append("</td>");
			
			str.append("<td>");
			str.append("<textarea id='color' name='color' readonly='readonly'/></textarea>");
			str.append("</td>");
			
			str.append("<td id='imgview'>");
			str.append("<img style='width: 150px;height: 100px;' src = 'image/"+purchase.getUloadedImage()+"'>");
			str.append("</td>");
			
			/*str.append("<td>Stock Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='stockQuantity' name='stockQuantity' value = "+purchase.getQuantity()+"  readonly='readonly' size='5' style='text-align: center;'/>");
			str.append("</td>");*/
			
			/*str.append("<td>Add Color<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<select name='colorName' id='adcolor'>");
			ArrayList<Color>colorList = purchaseDAO.getColorList();
			str.append("<option value='0'>Select Color</option>");
			for(Color color : colorList){
				str.append("<option value='"+color.getColorId()+"'>"+color.getColorName()+"</option>");
			}
			str.append("</select>");
			str.append("<input type='button' value='Add' id='adcolorbtn' onclick='addColor()'/>");
			str.append("</td>");*/
			str.append("</tr>");
			
			
			/*str.append("<tr>");
			str.append("<td>Stock Color<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='stockcolor' name='stockcolor'  readonly='readonly' >"+colorSize+"</textarea>");
			str.append("</td>");
			str.append("</tr>");*/
		
			str.append("<tr style='display:none'>");
			str.append("<td>Model No.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='modelnumber' name='modelNumber' value = '"+purchase.getModelNumber()+"' title='Enter Model Number' readonly='readonly'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr style='display:none'>");
			str.append("<td>Article No.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='articlenumber' name='articleNumber' value='"+purchase.getArticleNumber()+"' title='Enter Article Number' readonly='readonly'/>");
			str.append("</td>");
			str.append("</tr>");
			
			
			
		
			
			
		/*	str.append("<tr style=''>");
			
			
			
			
			str.append("<td>Price<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='price' name='price' title='Enter Price'  size='5' style='text-align: center;' onchange='setvchkEnable()'/>");
			str.append("</td>");
			
			
			str.append("<tr>");
			str.append("<td>Add Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<select name='adsize' id='adsize' >");
			str.append("<option value='0'>Select</option>");
			str.append("<option value='05'>05</option>");
			str.append("<option value='06'>06</option>");
			str.append("<option value='07'>07</option>");
			str.append("<option value='08'>08</option>");
			str.append("<option value='09'>09</option>");
			str.append("<option value='10'>10</option>");
			str.append("<option value='11'>11</option>");
			str.append("<option value='12'>12</option>");
			str.append("</select>");
			str.append("<input type='button' id='adsizebtn' value='Add' onclick='addSize();'/>");
			str.append("<input type='button' id='rmovesizebtn' value='Remove' onclick='RemoveSize();'/>");
			str.append("</td>");
			str.append("</tr>");
			
			
			str.append("<td>Enter Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input size='5' type='text' id='viewsizetxt' name='viewsizetxt' style='text-align: center;' onchange='setpurchaseqsze()' />");
			
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='quantity' name='quantity'  onchange='setpurchase(this.value)' size='5'  style='text-align: center;' />");
			str.append("</td>");
			
			str.append("<td>Image<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			
			str.append("</tr>");
			*/
			
			str.append("<input type='hidden' id='viewsize' name='viewsize' />");
			
			boolean ckeckproductentry = purchaseDAO.checkProductEntry(subCategoryID);
			
			if(ckeckproductentry){
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+str.toString()+""); 
			}else{
				str = new StringBuffer();
				
				str.append("<tr style='color:red'>");
				str.append("<td>Error<span class='reqd-info'>*</span></td>");
				str.append("<td align='center'>:</td>");
				str.append("<td style='color:red'>");
				str.append("No entry in product!!");
				str.append("</td>");
				str.append("</tr>");
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+str.toString()+""); 
			}
			
			
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
			String pcode = request.getParameter("pcode");
			
			SaleDAO saleDAO = new JDBCSaleDAO(connection);
			AdminSubcategoryDAO adminSubcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			
			double gst = purchaseDAO.getproductgst(subCategoryID);
			
			Purchase purchase = new Purchase();
			purchase.setProductId(subCategoryID);
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double mrp = Double.parseDouble(request.getParameter("price"));
			String viewsize = request.getParameter("viewsize");
			String colorSize = request.getParameter("colorsize");
			double total = quantity*mrp;
			
			double gstvalue = total*gst/100 ;
			double gstprice = total - gstvalue;
			
			
			String actualSize = "";
			String temp[] = viewsize.split(",");
			
			/*for(int i=0;i<temp.length;i++){
				actualSize = actualSize + purchaseDAO.getActualSize(temp[i], gender) + ",";
			}*/
			
			purchase.setQuantity(quantity);
			purchase.setMrp(mrp);
			purchase.setTotalgst(gst);
			purchase.setSize(viewsize);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			purchase.setCategoryid(categoryID);
			purchase.setTotal(total);
			purchase.setColorSize(colorSize);
			purchase.setActualSize(actualSize);
			purchase.setPcode(pcode);
			
			
			boolean isProductIDExist = purchaseDAO.checkProductExist(categoryID,subCategoryID,loginInfo.getUserId(),viewsize);
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
			str.append("<table width='100%' style='border: 1px solid #e69623; '>");
			str.append("<input type='hidden' name='itemsize' id='itemsize' value='"+purchaseList.size()+"'>");
			str.append("<col width='5%'/>");
			str.append("<col width='40%'/>");
			str.append("<col width='10%'/>");
			str.append("<col width='10%'/>");
			str.append("<col width='10%'/>");
			str.append("<col width='10%'/>");
			str.append("<col width='10%'/>");
			str.append("<tr>");
			str.append("<th></th>");
			str.append("<th align='left'>Product Name</th>");
			/*str.append("<th align='left'>Color</th>");*/
			str.append("<th align='left'>Size</th>");
			str.append("<th align='left'>M.R.P</th>");
			str.append("<th align='left'>GST</th>");
			str.append("<th align='left'>Quantity</th>");
			str.append("<th align='left'>Total</th>");
			str.append("</tr>");
			
			for(Purchase purchase2 : purchaseList){
				
				String dbViewSize = "";
				if(purchase2.getAccessories() == 0){
					String tempActualSize[] = purchase2.getActualSize().split(",");
					String tempViewSize[] = purchase2.getSize().split(",");
					
					/*for(int i=0;i<tempViewSize.length;i++){
						dbViewSize = dbViewSize + "(" + tempActualSize[i] +  ")" + " " + tempViewSize[i] + ",";
					}
					
					dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);*/
					for(int i=0;i<tempViewSize.length;i++){
						dbViewSize = dbViewSize + tempViewSize[i] + ",";
					}
					dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);
					
				}
				
				
				
				payAmount = payAmount+purchase2.getTotal();
				
				str.append("<tr>");
				str.append("<td><input type='checkbox' name='cksale'  value="+purchase2.getId()+"></td>");
				str.append("<td style='padding-left: 10px;'>"+purchase2.getProductName()+"</td>");
				/*if(purchase2.getAccessories() == 0){
					str.append("<td style='padding-left: 0px;'>"+purchase2.getColorSize()+"</td>");
				}else{
					str.append("<td style='padding-left: 0px;'></td>");
				}*/
				
				
				String tmpsze[] = dbViewSize.split(",");
				
				str.append("<td style='padding-left: 0px;'>"+tmpsze[0]+"</td>");
				str.append("<td style='padding-left: 0px;'>"+purchase2.getMrp()+"</td>");
				str.append("<td style='padding-left: 0px;'>"+purchase2.getTotalgst()+"%</td>");
				str.append("<td style='padding-left: 20px;'>"+purchase2.getQuantity()+"</td>");
				str.append("<td>"+purchase2.getTotal()+"</td>");
				str.append("</tr>");
				
			}
			
			str.append("<input type='hidden' name='hiddenpay' id='hiddenpay' value='"+payAmount+"'>");
			
			double per  = (Constants.DEFAULT_VAT*payAmount)/100;
			payAmount = payAmount+per;
			
			str.append("<tr>");
			str.append("<td colspan='7'><hr></hr></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("<td colspan='7'>");
			str.append("<table width='100%'>");
			str.append("<col width='40%'/>");
			str.append("<col width='20%'/>");
			str.append("<col width='30%'/>");
			str.append("<col width='10%'/>");
			str.append("<tr>");
			str.append("<td><b>Amount to Pay</b></td>");
			str.append("<td>");
			str.append("<select id='dtype' name='dtype' class='from-control'>");
			str.append("<option value='1'>Rs.</option>");
			str.append("<option value='2'>%</option>");
			str.append("</select>");
			str.append("</td>");
			str.append("<td>Discount <input type='text' name='vat' id='vat' size='3' maxlength='3' style='text-align: center;' value='"+Constants.DEFAULT_VAT+"' onchange='addVat(this.value)'>%</td>");
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
			
			//str.append("<table width='100%' style='border: 1px solid #e69623; height: auto; padding: 1em; font-size: 12px; overflow: hidden; display: inline-block;white-space: nowrap; table-layout: fixed'>");
			str.append("<table width='100%' style='border: 1px solid #e69623; '>");
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
				
				
				String tempActualSize[] = purchase2.getActualSize().split(",");
				String tempViewSize[] = purchase2.getSize().split(",");
				
				/*for(int i=0;i<tempViewSize.length;i++){
					dbViewSize = dbViewSize + "(" + tempActualSize[i] +  ")" + " " + tempViewSize[i] + ",";
				}*/
				
				for(int i=0;i<tempViewSize.length;i++){
					dbViewSize = dbViewSize + tempViewSize[i] + ",";
				}
				dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);
				
				payAmount = payAmount+purchase2.getTotal();
				
				str.append("<tr>");
				str.append("<td><input type='checkbox' name='cksale'  value="+purchase2.getId()+"></td>");
				str.append("<td style='padding-left: 10px;'>"+purchase2.getProductName()+"</td>");
				str.append("<td style='padding-left: 0px;'>"+purchase2.getColorSize()+"</td>");
				str.append("<td style='padding-left: 0px;'>"+dbViewSize+"</td>");
				str.append("<td style='padding-left: 0px;'>"+purchase2.getMrp()+"</td>");
				str.append("<td style='padding-left: 20px;'>"+purchase2.getQuantity()+"</td>");
				str.append("<td>"+purchase2.getTotal()+"</td>");
				str.append("</tr>");
				
			}
			
			
			str.append("<input type='hidden' name='hiddenpay' id='hiddenpay' value='"+payAmount+"'>");
			
			double per  = (Constants.DEFAULT_VAT*payAmount)/100;
			payAmount = payAmount+per;
			
			str.append("<tr>");
			str.append("<td colspan='7'><hr></hr></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("<td colspan='7'>");
			str.append("<table width='100%'>");
			str.append("<col width='40%'/>");
			str.append("<col width='30%'/>");
			str.append("<col width='10%'/>");
			str.append("<tr>");
			str.append("<td><b>Amount to Pay</b></td>");
			str.append("<td>vat <input type='text' name='vat' id='vat' size='3' maxlength='3' style='text-align: center;' value='"+Constants.DEFAULT_VAT+"' onchange='addVat(this.value)'>%</td>");
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


/*------------------- barcode coding start ---------------------------------------------------  */	
	
	
	
	public String barcode() throws SQLException{
		
		String articleName = request.getParameter("articleName");
		String barcodesize = request.getParameter("barcodesize");
		String barcodeprice = request.getParameter("barcodeprice");
		String barcodecolor = request.getParameter("barcodecolor");
		
		Connection connection = null;
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		try{
			connection = Connection_provider.getconnection();
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			
			
			Purchase purchase = purchaseDAO.getProductByName(articleName);
			
			boolean chechProductIDExist = purchaseDAO.checkBarcodeProductExist(purchase.getProductId(),barcodecolor);
			if(!chechProductIDExist){
				int result = purchaseDAO.saveB_size(purchase.getProductId(),barcodesize,0,barcodecolor,loginInfo.getId());
			}else{
				String barcodeSizeByProduct = purchaseDAO.getBarcodeSizeByProuct(purchase.getProductId());
				//String barcodeColorByProduct = purchaseDAO.getBarcodeColorSizeByProduct(purchase.getProductId());
				barcodeSizeByProduct = barcodeSizeByProduct + "," + barcodesize;
				//barcodeColorByProduct = barcodeColorByProduct + "," + barcodecolor;
				int update = purchaseDAO.updateBsize(purchase.getProductId(),barcodeSizeByProduct,barcodecolor);
			}
			
		/*	ArrayList<Category>categoryList = purchaseDAO.getCategoryList(loginInfo.getUserId());
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
	
	
	
	public String rbarcode() throws SQLException{
		
		String articleName = request.getParameter("articleName");
		String barcodesize = request.getParameter("barcodesize");
		String barcodeprice = request.getParameter("barcodeprice");
		String barcodecolor = request.getParameter("barcodecolor");
		
		String viewsize = "";
		String barcodecolorsize = "";
		if(request.getParameter("viewsize")!=null){
			viewsize = request.getParameter("viewsize");
			barcodecolorsize = request.getParameter("barcodecolorsize");
		}
		
	
		
		Connection connection = null;
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		try{
			connection = Connection_provider.getconnection();
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			
			
			Purchase purchase = purchaseDAO.getProductByName(articleName);
			
			boolean chechProductIDExist = purchaseDAO.checkBarcodeProductExist(purchase.getProductId(),barcodecolor);
			if(!chechProductIDExist){
				int result = purchaseDAO.saveB_size(purchase.getProductId(),barcodesize,0,barcodecolor,loginInfo.getId());
			}else{
				String barcodeSizeByProduct = viewsize;
				int update = purchaseDAO.updateBsize(purchase.getProductId(),barcodeSizeByProduct,barcodecolorsize);
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
			/*str.append("<select name='categoryID' id='categoryID' onchange='setSubCategory(this.value)' class='error'>"
	    +"<option value='0' selected='selected'>Select Category</option>");
			for(Category category : categoryList){
				str.append("<option value='"+category.getId()+"'>"+category.getCategoryName()+"</option>");
			}
			str.append("</select>");*/
			str.append("<input type='text' name='categoryD' id='categoryID' value='"+purchase.getCategoryName()+"' readonly='readonly'>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>SubCategory<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='productajax'>");
			/*str.append("<select name='productName' id='subCategoryID' onchange='setStock(this.value)' class='error'>"
	    +"<option value='0' selected='selected'>Select Category</option>");
			for(Product product : productList){
				str.append("<option value='"+product.getId()+"'>"+product.getProductName()+"</option>");
			}
			str.append("</select>");*/
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
	
	
	public String bstock() throws SQLException{
		
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
		/*	AdminSubcategoryDAO adminSubcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
			int stock = adminSubcategoryDAO.getAdminProductStock(categoryID,subCategoryID);*/
			
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			Purchase purchase = purchaseDAO.getProductDetails(categoryID,subCategoryID);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			
			ArrayList<Size> sizeList = new ArrayList<Size>();
			
			ArrayList<Color>psizeColorList = purchaseDAO.getP_sizeColorList(subCategoryID);
			
			StringBuffer sizeBuffer = new StringBuffer();
			StringBuffer psizrBuffer = new StringBuffer(); 
			
			for(Color color : psizeColorList){
				psizrBuffer = new StringBuffer(); 
				
				sizeList = purchaseDAO.getProductSize(subCategoryID,loginInfo.getUserId(),color.getColorName());
				for(Size size : sizeList){
					psizrBuffer.append( size.getProductSize() +  ",");
					
				}
				
				sizeBuffer.append(color.getColorName() + ":->" + psizrBuffer.toString());
			}
			
			String size = "";
			if(sizeBuffer.length()!=0){
				size = sizeBuffer.subSequence(0, sizeBuffer.length()-1).toString();
			}
			
			String barcodeSizeByproduct = purchaseDAO.getBarcodeSizeByProuct(subCategoryID);
			String barcodeColorByProduct = purchaseDAO.getBarcodeColorSizeByProduct(subCategoryID);
			
		
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
			
			/*str.append("<tr>");
			str.append("<td>Stock Color<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='stockcolor' name='stockcolor'  readonly='readonly' >"+colorSize+"</textarea>");
			str.append("</td>");
			str.append("</tr>");*/
		
		
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
			str.append("<input type='button' value='Add' id='adcolorbtn' onclick='addColor()'/>");
			str.append("<input type='button' value='Remove' id='rmovecolorbtn' onclick='removeColor()'/>");
			str.append("</td>");
			str.append("</tr>");
			
			
			str.append("<tr>");
			str.append("<td>Color.<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='color' name='color' readonly='readonly'/>"+barcodeColorByProduct+"</textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Price<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='price' name='price' title='Enter Price'  size='5' style='text-align: center;'/>");
			str.append("</td>");
			str.append("</tr>");
			
		/*	str.append("<tr>");
			str.append("<td>Add Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<select name='adsize' id='adsize'>");
			str.append("<option value='0'>Select</option>");
			str.append("<option value='05'>05</option>");
			str.append("<option value='06'>06</option>");
			str.append("<option value='07'>07</option>");
			str.append("<option value='08'>08</option>");
			str.append("<option value='09'>09</option>");
			str.append("<option value='10'>10</option>");
			str.append("<option value='11'>11</option>");
			str.append("<option value='12'>12</option>");
			str.append("</select>");
			str.append("<input type='button' id='adsizebtn' value='Add' onclick='addSize();'/>");
			str.append("<input type='button' id='rmovesizebtn' value='Remove' onclick='barcodeQuantity();'/>");
			str.append("</td>");
			str.append("</tr>");*/
			
			str.append("<tr>");
			str.append("<td>View Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='viewsize' name='viewsize' onchange='addSize();'>"+barcodeSizeByproduct+"</textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			String temp[] = barcodeSizeByproduct.split(",");
			
			str.append("<tr>");
			str.append("<td>Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='quantity' name='quantity' value='"+temp.length+"'  readonly='readonly' size='5' style='text-align: center;' />");
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


	public ProductForm getModel()  {
		
		return productForm;
	}
	
	

}
