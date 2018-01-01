package com.inventorymanagement.main.web.action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;







import org.apache.commons.io.FileDeleteStrategy;
import org.apache.struts2.ServletActionContext;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.main.common.constants.Constants;
import com.inventorymanagement.main.eu.bi.AdminSubcategoryDAO;
import com.inventorymanagement.main.eu.bi.ProductDAO;
import com.inventorymanagement.main.eu.bi.PurchaseDAO;
import com.inventorymanagement.main.eu.bi.SaleDAO;
import com.inventorymanagement.main.eu.bi.SalesManDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCAdminSubcategoryDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCProductDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCPurchaseDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCSaleDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCSalesManDAO;
import com.inventorymanagement.main.eu.entity.Barcode;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Color;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.SalesMan;
import com.inventorymanagement.main.eu.entity.Size;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.BarcodeForm;
import com.inventorymanagement.main.web.form.ProductForm;
import com.onbarcode.barcode.Code128;
import com.onbarcode.barcode.Code39;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class BarcodeGeneratorAction extends BaseAction implements ModelDriven<ProductForm>{
	
	ProductForm productForm = new ProductForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	public String execute() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		deleteSubImage();
		/*Code128 code128 = new Code128();
		 //code128.setShowText(false);
		String viewSize = barcodeForm.getViewsize();
		String temp[] = viewSize.split(",");
		ArrayList<Barcode>totalBarcodeList = new ArrayList<Barcode>();
		for(int i=0;i<temp.length;i++){
			String barcodeData = barcodeForm.getArticleName() +" "+temp[i]+ " "+ barcodeForm.getPrice();
			code128.setData(barcodeData);
			 String filePath = request.getRealPath("/subimage/"+barcodeData+i+".jpg");
			 code128.drawBarcode(filePath);
			 
			 Barcode barcode = new Barcode();
			 barcode.setCompanyName(barcodeForm.getCompanyName());
			 barcode.setImageName(barcodeData+i+".jpg");
			 totalBarcodeList.add(barcode);
		}
		
		session.setAttribute("totalBarcodeList", totalBarcodeList);*/
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			ArrayList<Purchase>purchaseList = purchaseDAO.getPurchaseList(loginInfo.getUserId());
			Code39 code128 = new Code39();
			code128.setBarcodeWidth(3);
			
			ArrayList<Barcode>totalBarcodeList = new ArrayList<Barcode>();
			for(Purchase purchase : purchaseList){
				String productName = purchaseDAO.getProductName(purchase.getProductId());
				String temp[] = purchase.getSize().split(",");
				//String actualSize[] = purchase.getActualSize().split(",");
				for(int i=0;i<temp.length;i++){
					 String barcodeData = productName +"~"+temp[i]+ "~"+ purchase.getMrp() + "~" + purchase.getColorSize();
					 String psizeid = purchaseDAO.getPsizeId(purchase.getProductId(),purchase.getColorSize(),temp[i],i);
					 code128.setData(psizeid);
					 String filePath = request.getRealPath("/subimage/"+barcodeData+i+".gif");
					 code128.drawBarcode(filePath);
					 
					 
					 Barcode barcode = new Barcode();
					 barcode.setCompanyName("Trendz");
					 barcode.setArticleNumber(productName);
					 barcode.setSize(temp[i]);
					 barcode.setColor(purchase.getColorSize());
					 barcode.setMrp(purchase.getMrp());
					 barcode.setPcode(purchase.getPcode());
					 barcode.setImageName(barcodeData+i+".gif");
					 totalBarcodeList.add(barcode);
					 
				}
				
			}
			session.setAttribute("totalBarcodeList", totalBarcodeList);
			session.setAttribute("mdbarcodecount", totalBarcodeList.size());
			//int delete = purchaseDAO.deletePurchase();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
			 return SUCCESS;
	}
	
	public String or() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		session.removeAttribute("barcodesessionproduct");
		return input();
	}
	
	
	public String move() throws Exception{
		String selectedid = request.getParameter("selectedid");
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ProductDAO productDAO = new JDBCProductDAO(connection);
			Product product = productDAO.getProduct(Integer.parseInt(selectedid));
			
			int gender = productDAO.getGender(product.getCategoryID());
			product.setGender(gender);
			
			session.setAttribute("barcodesessionproduct", product);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return input();
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
			int deletebsize = purchaseDAO.deleBSize(loginInfo.getId());
			
			
			
			ArrayList<Color>populateColorList = new  ArrayList<Color>();
			ArrayList<Color>colorList = purchaseDAO.getColorList();
			for(Color color : colorList){
				populateColorList.add(new Color(color.getColorId(),color.getColorName()));
			}
			
			productForm.setColorList(populateColorList);
			
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			String fromdate = dateFormat.format(cal.getTime());
			
			productForm.setFromDate(fromdate);
			productForm.setToDate(fromdate);
			
			if(session.getAttribute("barcodesessionproduct")!=null){
				return "popupbarcode";
			}
			
		
		}catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		
		return INPUT;
	}
	
	
public String stock() throws SQLException{
		
		int categoryID = 0;
		if(Integer.parseInt(request.getParameter("categoryD"))!=0){
			 categoryID = Integer.parseInt(request.getParameter("categoryD"));
		}else{
			categoryID = (Integer)session.getAttribute("categoryID");
		}
		
		int subCategoryID = Integer.parseInt(request.getParameter("subcategoryID"));
		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		Connection connection = null;
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		try{
			connection = Connection_provider.getconnection();
		/*	AdminSubcategoryDAO adminSubcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
			int stock = adminSubcategoryDAO.getAdminProductStock(categoryID,subCategoryID);*/
			
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			SaleDAO saleDAO = new JDBCSaleDAO(connection);
			Purchase purchase = purchaseDAO.getProductDetails(categoryID,subCategoryID);
			int del = saleDAO.deleteTempSize();
			double mrp = purchaseDAO.getPrice(categoryID,subCategoryID);
			
			ArrayList<Size> sizeList = purchaseDAO.getProductSize(subCategoryID,loginInfo.getUserId());
			
			for(Size size2 : sizeList){
				int result = purchaseDAO.saveTempSize(size2);
			}
			
			fromdate = DateTimeUtils.getCommencingDate(fromdate);
			todate = DateTimeUtils.getCommencingDate(todate);
			
			StringBuffer sizeBuffer = new StringBuffer();
			StringBuffer psizeBuffer = new StringBuffer();
			ArrayList<Color>tempColorList = saleDAO.getTempColorList(subCategoryID);
			int productCount = saleDAO.getQuantity(subCategoryID);
			ArrayList<Size>tempSizeList = new ArrayList<Size>();
			
			for(Color color : tempColorList){
				psizeBuffer = new StringBuffer(); 
				tempSizeList = saleDAO.getTempSizeListByColor(subCategoryID,color.getColorName());
				
				for(Size size : tempSizeList){
					psizeBuffer.append( size.getProductSize() +  ",");
				}
				sizeBuffer.append(color.getColorName() + ":->" + psizeBuffer.toString() + " ");
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
			
			
			str.append("<tr style=''>");
			str.append("<td>Add Color<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<select name='colorName' id='adcolor'>");
			ArrayList<Color>colorList = saleDAO.getBarcodeTempColorList(subCategoryID,fromdate,todate);
			str.append("<option value='0'>Select Color</option>");
			for(Color color : colorList){
				str.append("<option value='"+color.getColorId()+"'>"+color.getColorName()+"</option>");
			}
			str.append("</select>");
			/*str.append("<input type='text' style='text-align:center' name='bqty' id='bqty' maxlength='1' size='3'  onchange='addbqty(this.value)'/>");*/
			str.append("<input type='button' value='Add' id='adcolorbtn' onclick='addColor()'/>");
			str.append("</td>");
			str.append("</tr>");
			
			
			str.append("<tr style=''>");
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
			str.append("<td>View Size<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<textarea id='viewsize' name='viewsize' rows='4' cols='20' onchange='setQuantity()'>"+size+"</textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='quantity' name='quantity' value="+purchase.getQuantity()+"  readonly='readonly' size='5' style='text-align: center;'/>");
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
	
	
public String psize(){
	if(!verifyLogin(request)){
		return "login";
	}
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Connection connection = null;
	try{
		
		String selectedid = request.getParameter("selectedid");
		
		connection = Connection_provider.getconnection();
		PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
		
		Purchase purchase = purchaseDAO.getPsizeData(selectedid);
		
		String str = purchase.getProductName() + "~" + purchase.getSize() + "~" + purchase.getMrp() + "~" + purchase.getColorSize() ;
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+""); 
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return null;
}
	
	
	public String set() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			int categoryID = Integer.parseInt(request.getParameter("categoryD"));
			int subCategoryID = Integer.parseInt(request.getParameter("subcategoryID"));
			int gender = Integer.parseInt(request.getParameter("gender"));
			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");
			
			fromdate = DateTimeUtils.getCommencingDate(fromdate);
			todate  = DateTimeUtils.getCommencingDate(todate);
			
			SaleDAO saleDAO = new JDBCSaleDAO(connection);
			AdminSubcategoryDAO adminSubcategoryDAO = new JDBCAdminSubcategoryDAO(connection);
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			
			Purchase purchase = new Purchase();
			purchase.setProductId(subCategoryID);
			int quantity = 0;
			double mrp = Double.parseDouble(request.getParameter("price"));
			String viewsize = request.getParameter("viewsize");
			//String viewsizeid = request.getParameter("viewsizeid");
			String colorSize = "";
			//String productidseries = request.getParameter("productidseries");
		
			double total = quantity*mrp;
			
			
			purchase.setMrp(mrp);
			String temps[] = viewsize.split(" ");
			
			String bcodecplorsize = request.getParameter("bcodecplorsize");
			String bcolor = bcodecplorsize.substring(0,bcodecplorsize.length()-1);
			String tmcolor[] = bcodecplorsize.split(",");
			
			ArrayList<Purchase>bpList = purchaseDAO.getBarcodeProdLength(subCategoryID,fromdate,todate);
			
			for(Purchase p : bpList){
				//String color[] = tmcolor[i];
				//System.out.println(color[0]);
				//String size[] = temps[i].split(">");
				//System.out.println(size[1]);
				//purchase.setSize(size[1]);
				colorSize = "Red";
				
				//String actualSize = "";
				//String temp[] = size[1].split(",");
				//quantity = temp.length;
				//purchase.setQuantity(quantity);
				/*for(int j=0;j<temp.length;j++){
					actualSize = actualSize + purchaseDAO.getActualSize(temp[j], gender) + ",";
				}*/
				
				
				purchase.setColorSize("Red");
				int pquantity = purchaseDAO.getBarcodeQuantity(subCategoryID,p.getSize(),fromdate,todate);
				String size = purchaseDAO.getBarcodePSize(subCategoryID,p.getSize(),fromdate,todate);
				purchase.setQuantity(pquantity);
				purchase.setSize(size);
				//purchase.setActualSize(actualSize);
				
				String pcode = purchaseDAO.getPcode(subCategoryID,fromdate,todate,size);
				purchase.setPcode(pcode);
				
				purchase.setCategoryid(categoryID);
				boolean isProductIDExist = purchaseDAO.checkProductExist(categoryID,subCategoryID,loginInfo.getUserId(),size);
				if(isProductIDExist){
					
					int result = purchaseDAO.updateSale(purchase,subCategoryID,loginInfo.getUserId());
				}else{
					int rsult = purchaseDAO.save(purchase,loginInfo.getUserId());
				}
			}
			
			
		
			
			double payAmount = 0;
			int barcodeQuantity = 0;
			//ArrayList<Sale>saleList = saleDAO.getSaleList();
			ArrayList<Purchase>purchaseList = purchaseDAO.getPurchaseList(loginInfo.getUserId());
			
			
			
			StringBuffer str = new StringBuffer();
			
			//str.append("<table width='100%' style='border: 1px solid #e69623; height: auto; padding: 1em; font-size: 12px; overflow: hidden; display: inline-block;white-space: nowrap; table-layout: fixed'>");
			str.append("<table width='100%' style='border: 1px solid #e69623;'>");
			str.append("<input type='hidden' name='itemsize' id='itemsize' value='"+purchaseList.size()+"'>");
			str.append("<col width='5%'/>");
			str.append("<col width='30%'/>");
			str.append("<col width='10%'/>");
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
			str.append("</tr>");
			
			for(Purchase purchase2 : purchaseList){
				
				String dbViewSize = "";
				
				
				//String tempActualSize[] = purchase2.getActualSize().split(",");
				String tempViewSize[] = purchase2.getSize().split(",");
				
				/*for(int i=0;i<tempViewSize.length;i++){
					dbViewSize = dbViewSize + "(" + tempActualSize[i] +  ")" + " " + tempViewSize[i] + ",";
				}
				
				dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);*/
				
				for(int i=0;i<tempViewSize.length;i++){
					dbViewSize = dbViewSize + tempViewSize[i] + ",";
				}
				dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);
				
				payAmount = payAmount+purchase2.getTotal();
				barcodeQuantity = barcodeQuantity + purchase2.getQuantity();
				
				String tmpsze[] = dbViewSize.split(",");
				
				str.append("<tr>");
				str.append("<td><input type='checkbox' name='cksale'  value="+purchase2.getId()+"></td>");
				str.append("<td style='padding-left: 10px;'>"+purchase2.getProductName()+"</td>");
				str.append("<td style='padding-left: 0px;'>"+purchase2.getColorSize()+"</td>");
				str.append("<td style='padding-left: 0px;'>"+tmpsze[0]+"</td>");
				str.append("<td style='padding-left: 0px;'>"+purchase2.getMrp()+"</td>");
				str.append("<td style='padding-left: 20px;'>"+purchase2.getQuantity()+"</td>");
				//str.append("<td>"+purchase2.getTotal()+"</td>");
				str.append("</tr>");
				
			}
			
			str.append("<input type='hidden' name='hiddenpay' id='hiddenpay' value='"+payAmount+"'>");
			
			double per  = (Constants.DEFAULT_VAT*payAmount)/100;
			payAmount = payAmount+per;
			
			str.append("<tr>");
			str.append("<td colspan='6'><hr></hr></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("<td colspan='6'>");
			str.append("<table width='100%'>");
			str.append("<col width='20%'/>");
			str.append("<col width='20%'/>");
			str.append("<col width='5%'/>");
			str.append("<col width='10%'/>");
			str.append("<tr>");
			str.append("<td><b>Total Barcoce</b></td>");
			str.append("<td></td>");
			str.append("<td>"+barcodeQuantity+"</td>");
			//str.append("<td id='pay'>"+payAmount+"</td>");
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
			int barcodeQuantity = 0;
			
			str.append("<table width='100%' style='border: 1px solid #e69623;'>");
			str.append("<input type='hidden' name='itemsize' id='itemsize' value='"+purchaseList.size()+"'>");
			str.append("<col width='5%'/>");
			str.append("<col width='20%'/>");
			str.append("<col width='10%'/>");
			str.append("<col width='20%'/>");
			str.append("<col width='10%'/>");
			str.append("<col width='10%'/>");
			
			str.append("<tr>");
			str.append("<th></th>");
			str.append("<th align='left'>Product Name</th>");
			str.append("<th align='left'>Color</th>");
			str.append("<th align='left'>Size</th>");
			str.append("<th align='left'>M.R.P</th>");
			str.append("<th align='left'>Quantity</th>");
			str.append("</tr>");
			
			for(Purchase purchase2 : purchaseList){
				
				String dbViewSize = "";
				
				
				String tempActualSize[] = purchase2.getActualSize().split(",");
				String tempViewSize[] = purchase2.getSize().split(",");
				
				for(int i=0;i<tempViewSize.length;i++){
					dbViewSize = dbViewSize + "(" + tempActualSize[i] +  ")" + " " + tempViewSize[i] + ",";
				}
				
				dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);
				
				payAmount = payAmount+purchase2.getTotal();
				barcodeQuantity = barcodeQuantity + purchase2.getQuantity();
				
				str.append("<tr>");
				str.append("<td><input type='checkbox' name='cksale'  value="+purchase2.getId()+"></td>");
				str.append("<td style='padding-left: 10px;'>"+purchase2.getProductName()+"</td>");
				str.append("<td style='padding-left: 0px;'>"+purchase2.getColorSize()+"</td>");
				str.append("<td style='padding-left: 0px;'>"+dbViewSize+"</td>");
				str.append("<td style='padding-left: 0px;'>"+purchase2.getMrp()+"</td>");
				str.append("<td style='padding-left: 20px;'>"+purchase2.getQuantity()+"</td>");
				//str.append("<td>"+purchase2.getTotal()+"</td>");
				str.append("</tr>");
				
			}
			
			str.append("<input type='hidden' name='hiddenpay' id='hiddenpay' value='"+payAmount+"'>");
			
			double per  = (Constants.DEFAULT_VAT*payAmount)/100;
			payAmount = payAmount+per;
			
			str.append("<tr>");
			str.append("<td colspan='6'><hr></hr></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("<td colspan='6'>");
			str.append("<table width='100%'>");
			str.append("<col width='20%'/>");
			str.append("<col width='20%'/>");
			str.append("<col width='5%'/>");
			str.append("<col width='10%'/>");
			str.append("<tr>");
			str.append("<td><b>Total Barcoce</b></td>");
			str.append("<td></td>");
			str.append("<td>"+barcodeQuantity+"</td>");
			//str.append("<td id='pay'>"+payAmount+"</td>");
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

	
	public String quantity(){
		
		Connection connection = null;
		
		
		try{
			
			connection = Connection_provider.getconnection();
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			
			String viewsize = request.getParameter("viewsize");
			String bcodecplorsize = request.getParameter("bcodecplorsize");
			
			String prodid = request.getParameter("prodid");
			String tempclor[] = bcodecplorsize.split(",");
			
			String bcolor = "";
			
			for(int c=0;c<tempclor.length;c++){
				bcolor = bcolor +  "'"+tempclor[c]+"'" + ",";
			}
			
			bcolor = bcolor.substring(0,bcolor.length()-1);
			int count  = purchaseDAO.getViewSize(prodid,bcolor);
			
			
			/*String temp[] = viewsize.split(" ");
			int count = 0;
			
			
			
			bcodecplorsize = bcodecplorsize.substring(0,bcodecplorsize.length()-1);
			
			for(int i=0;i<temp.length;i++){
				String color[] = temp[i].split(":");
				String size[] = temp[i].split(">");
				String quantity[] = size[1].split(",");
				for(int j=0;j<quantity.length;j++){
					count++;
				}
			}*/
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+count+""); 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	
	


	
	
	
	public void deleteSubImage(){
		File fin = new File(request.getRealPath("/subimage/"));

		for (File file : fin.listFiles()) {
		    try {
				FileDeleteStrategy.FORCE.delete(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
	}

	public ProductForm getModel() {
		// TODO Auto-generated method stub
		return productForm;
	}

}
