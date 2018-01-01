package com.inventorymanagement.main.web.action;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.common.constants.Constants;
import com.inventorymanagement.main.eu.bi.AccessoriesDAO;
import com.inventorymanagement.main.eu.bi.PurchaseDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCAccessoriesDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCPurchaseDAO;
import com.inventorymanagement.main.eu.entity.Accessories;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.AccessoriesForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class AccessoriesAction extends BaseAction implements ModelDriven<AccessoriesForm>{

	AccessoriesForm accessoriesForm = new AccessoriesForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private Pagination pagination = new Pagination(5, 1);
	
	
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
		try{
			connection = Connection_provider.getconnection();
			AccessoriesDAO accessoriesDAO = new JDBCAccessoriesDAO(connection);
			
			//pagination
			int totalCount = accessoriesDAO.getAccessoriesCount(accessoriesForm.getSearchText(),loginInfo.getUserId());
			pagination.setPreperties(totalCount);
			ArrayList<Accessories>accessoriesList = accessoriesDAO.getAccessoriesList(pagination,accessoriesForm.getSearchText(),loginInfo.getUserId());
			accessoriesForm.setAccessoriesList(accessoriesList);
			pagination.setPage_records(accessoriesList.size());
			
			accessoriesForm.setTotalRecords(totalCount);
		
			accessoriesForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return SUCCESS;
	}
	
	
	public String save() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		String filePath = request.getRealPath("/acimage/");
	    Connection connection = null;  
		try{
			connection = Connection_provider.getconnection();
			AccessoriesDAO accessoriesDAO = new JDBCAccessoriesDAO(connection);
			Accessories accessories = new Accessories();
			
			accessories.setProductName(accessoriesForm.getProductName());
			accessories.setDiscription(accessoriesForm.getDiscription());
			accessories.setPrice(accessoriesForm.getPrice());
			if(accessoriesForm.getUserAccImage()!=null){
				 System.out.println("Server path:" + filePath);
				 File fileToCreate = new File(filePath, accessoriesForm.getProductName()+"_"+accessoriesForm.getUserAccImageFileName());
				 FileUtils.copyFile(accessoriesForm.getUserAccImage(), fileToCreate);
				 accessories.setUploadedimage(accessoriesForm.getProductName()+"_"+accessoriesForm.getUserAccImageFileName());
			}
			
			
			int result = accessoriesDAO.saveAccessories(accessories,loginInfo.getUserId());
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
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
			AccessoriesDAO accessoriesDAO = new JDBCAccessoriesDAO(connection);
			
			Accessories accessories = accessoriesDAO.getAccessories(selectedid);
			
			accessoriesForm.setProductName(accessories.getProductName());
			accessoriesForm.setDiscription(accessories.getDiscription());
			accessoriesForm.setPrice(accessories.getPrice());
			accessoriesForm.setUploadedimage(accessories.getUploadedimage());
			accessoriesForm.setId(accessories.getId());
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}
	
	
	@SkipValidation
	public String editsave() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		String filePath = request.getRealPath("/acimage/");
		try{
			
			int selectedid = accessoriesForm.getSelectedid();
			connection = Connection_provider.getconnection();
			AccessoriesDAO accessoriesDAO = new JDBCAccessoriesDAO(connection);
			Accessories accessories = new Accessories();
			
			accessories.setProductName(accessoriesForm.getProductName());
			accessories.setDiscription(accessoriesForm.getDiscription());
			accessories.setPrice(accessoriesForm.getPrice());
			String uploadedImage = accessoriesDAO.getUploadedImage(selectedid);
			accessories.setUploadedimage(uploadedImage);
			if(accessoriesForm.getUserAccImage()!=null){
				 System.out.println("Server path:" + filePath);
				 File fileToCreate = new File(filePath, accessoriesForm.getProductName()+"_"+accessoriesForm.getUserAccImageFileName());
				 FileUtils.copyFile(accessoriesForm.getUserAccImage(), fileToCreate);
				 accessories.setUploadedimage(accessoriesForm.getProductName()+"_"+accessoriesForm.getUserAccImageFileName());
			}
			
			int update = accessoriesDAO.updateAccessories(accessories,selectedid);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return "editsave";
	}
	
	
	@SkipValidation
	public String delete() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			
			int selectedid = Integer.parseInt(request.getParameter("selectedid"));
			connection = Connection_provider.getconnection();
			AccessoriesDAO accessoriesDAO = new JDBCAccessoriesDAO(connection);
			
			int delete = accessoriesDAO.deleteAccessories(selectedid);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		
		return "delete";
	}
	
	
	
	public void validate() {
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			AccessoriesDAO accessoriesDAO = new JDBCAccessoriesDAO(connection);
			boolean checkAccessoriesExist = accessoriesDAO.checkAccessoriesExist(accessoriesForm.getProductName());
			if(checkAccessoriesExist){
				addActionError("This product name allready exist!!");
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*----------------------------------Purchase Coding  */
	
	@SkipValidation
	public String set() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AccessoriesDAO accessoriesDAO = new JDBCAccessoriesDAO(connection);
			
			ArrayList<Accessories>accessoriesList = accessoriesDAO.getAccessoriesList(loginInfo.getUserId());
			
			StringBuffer str = new StringBuffer();
			
			str.append("<col width='10%'/>");
			str.append("<col width='2%'/>");
			str.append("<col width='20%'/>");
			str.append("<tr>");
			str.append("<td>Accessories<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<select name='accessories' id='accessories' onchange='stockAccessories(this.value);'> ");
			str.append("<option value='0'>Select Accessories</option>");
			for(Accessories accessories : accessoriesList){
				str.append("<option value='"+accessories.getId()+"'>"+accessories.getProductName()+"</option>");
			}
			str.append("</select>");
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
	
	
	public String stock() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AccessoriesDAO accessoriesDAO = new JDBCAccessoriesDAO(connection);
			
			int accessoriesID = Integer.parseInt(request.getParameter("accessoriesID"));
			Accessories accessories = accessoriesDAO.getAccessories(accessoriesID);
			
			StringBuffer str = new StringBuffer();
			
			str.append("<col width='20%'/>");
			str.append("<col width='3%'/>");
			str.append("<col width='20%'/>");
			
			str.append("<tr>");
			str.append("<td>Stock<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='stock' name='stock'  readonly='readonly' value='"+accessories.getQuantity()+"' ></textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Price<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='price' name='price' title='Enter Price'  size='5' style='text-align: center;' onchange='setvchkEnable()'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='quantity' name='quantity' size='5' style='text-align: center;' />");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Image<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='imgview'>");
			str.append("<img style='width: 150px;height: 100px;' src = '/inventorymanagement/acimage/"+accessories.getUploadedimage()+"'>");
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
	
	
	public String purchase() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AccessoriesDAO accessoriesDAO = new JDBCAccessoriesDAO(connection);
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			
			int accessoriesID = Integer.parseInt(request.getParameter("accessoriesID"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double price = Double.parseDouble(request.getParameter("price"));
			double total = quantity*price;
			
			Purchase purchase = new Purchase();
			purchase.setProductId(accessoriesID);
			purchase.setQuantity(quantity);
			purchase.setMrp(price);
			purchase.setTotal(total);
			
			int isAccessories = 1;
			
			Accessories accessories = accessoriesDAO.getAccessories(accessoriesID);
			
			boolean checkAccessoriesExist = accessoriesDAO.checkAccessoriesExist(accessoriesID,isAccessories);
			if(checkAccessoriesExist){
				int update = accessoriesDAO.updatePurchase(purchase,loginInfo.getUserId());
			}else{
				int result = accessoriesDAO.savePurchase(purchase,loginInfo.getUserId());
			}
			
			double payAmount = 0;
			//ArrayList<Sale>saleList = saleDAO.getSaleList();
			ArrayList<Purchase>purchaseList = purchaseDAO.getPurchaseList(loginInfo.getUserId());
			
			StringBuffer str = new StringBuffer();
			
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
				
				if(purchase2.getAccessories() == 0){
					String tempActualSize[] = purchase2.getActualSize().split(",");
					String tempViewSize[] = purchase2.getSize().split(",");
					
					for(int i=0;i<tempViewSize.length;i++){
						dbViewSize = dbViewSize + "(" + tempActualSize[i] +  ")" + " " + tempViewSize[i] + ",";
					}
					
					dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);
				}
				
				
				
				payAmount = payAmount+purchase2.getTotal();
				
				str.append("<tr>");
				str.append("<td><input type='checkbox' name='cksale'  value="+purchase2.getId()+"></td>");
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
			payAmount = payAmount+per;*/
			
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
	
	
	/*----------------------------------Sale Coding  */
	
	public String stocksale() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AccessoriesDAO accessoriesDAO = new JDBCAccessoriesDAO(connection);
			
			int accessoriesID = Integer.parseInt(request.getParameter("accessoriesID"));
			Accessories accessories = accessoriesDAO.getAccessories(accessoriesID);
			
			StringBuffer str = new StringBuffer();
			
			str.append("<col width='20%'/>");
			str.append("<col width='3%'/>");
			str.append("<col width='20%'/>");
			
			str.append("<tr>");
			str.append("<td>Stock<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='stock' name='stock'  readonly='readonly' value='"+accessories.getQuantity()+"' ></textarea>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Price<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='price' name='price' title='Enter Price' value="+accessories.getPrice()+"  size='5' style='text-align: center;' onchange='setvchkEnable()'/>");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Quantity<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td>");
			str.append("<input type='text' id='quantity' name='quantity' size='5' style='text-align: center;' onchange='setAccessoriesQuantity(this.value);' />");
			str.append("</td>");
			str.append("</tr>");
			
			str.append("<tr>");
			str.append("<td>Image<span class='reqd-info'>*</span></td>");
			str.append("<td align='center'>:</td>");
			str.append("<td id='imgview'>");
			str.append("<img style='width: 150px;height: 100px;' src = '/inventorymanagement/acimage/"+accessories.getUploadedimage()+"'>");
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
	
	
	
	public String sale() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			AccessoriesDAO accessoriesDAO = new JDBCAccessoriesDAO(connection);
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			
			int accessoriesID = Integer.parseInt(request.getParameter("accessoriesID"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double price = Double.parseDouble(request.getParameter("price"));
			double total = quantity*price;
			
			Purchase purchase = new Purchase();
			purchase.setProductId(accessoriesID);
			purchase.setQuantity(quantity);
			purchase.setMrp(price);
			purchase.setTotal(total);
			
			int isAccessories = 1;
			
			Accessories accessories = accessoriesDAO.getAccessories(accessoriesID);
			
			boolean checkAccessoriesExist = accessoriesDAO.checkAccessoriesExist(accessoriesID,isAccessories);
			if(checkAccessoriesExist){
				int update = accessoriesDAO.updatePurchase(purchase,loginInfo.getUserId());
			}else{
				int result = accessoriesDAO.savePurchase(purchase,loginInfo.getUserId());
			}
			
			double payAmount = 0;
			//ArrayList<Sale>saleList = saleDAO.getSaleList();
			ArrayList<Purchase>purchaseList = purchaseDAO.getPurchaseList(loginInfo.getUserId());
			
			StringBuffer str = new StringBuffer();
			
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
				
				if(purchase2.getAccessories() == 0){
					String tempActualSize[] = purchase2.getActualSize().split(",");
					String tempViewSize[] = purchase2.getSize().split(",");
					
					for(int i=0;i<tempViewSize.length;i++){
						dbViewSize = dbViewSize + "(" + tempActualSize[i] +  ")" + " " + tempViewSize[i] + ",";
					}
					
					dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);
				}
				
				
				
				payAmount = payAmount+purchase2.getTotal();
				
				str.append("<tr>");
				str.append("<td><input type='checkbox' name='cksale'  value="+purchase2.getId()+"></td>");
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
			payAmount = payAmount+per;*/
			
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
	
	public String vat() throws SQLException{
		Connection connection = null;
		int vat = Integer.parseInt(request.getParameter("vat"));
		int rupee = Integer.parseInt(request.getParameter("rupee"));
		try{
			connection = Connection_provider.getconnection();
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			AccessoriesDAO accessoriesDAO = new JDBCAccessoriesDAO(connection);
			
			double payAmount = 0;
			//ArrayList<Sale>saleList = saleDAO.getSaleList();
			ArrayList<Purchase>purchaseList = purchaseDAO.getPurchaseList(loginInfo.getUserId());
			
			
			/*for(Purchase purchase : purchaseList){
				double dtotal = 0;
				dtotal = purchase.getMrp()*purchase.getQuantity();
				double per  = (vat*dtotal)/100;
				double discountTotal = dtotal-per;
				
				int update  = accessoriesDAO.updatePurchaseTotal(purchase.getId(),discountTotal);
			}*/
			
			StringBuffer str = new StringBuffer();
			
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
				
				double dtotal = 0;
				double per = 0;
				double discountTotal = 0;
				if(vat != 0){
					
					dtotal = purchase2.getMrp()*purchase2.getQuantity();
					per  = (vat*dtotal)/100;
					discountTotal = dtotal-per;
					
					payAmount = payAmount+discountTotal;
				}
				
				if(rupee !=0){
					dtotal = purchase2.getMrp()*purchase2.getQuantity();
					discountTotal = dtotal-rupee;
					payAmount = payAmount+discountTotal;
				}
				
				
				str.append("<tr>");
				str.append("<td><input type='checkbox' name='cksale'  value="+purchase2.getId()+"></td>");
				str.append("<td style='padding-left: 10px;'>"+purchase2.getProductName()+"</td>");
				if(purchase2.getAccessories() == 0){
					str.append("<td style='padding-left: 0px;'>"+purchase2.getColorSize()+"</td>");
				}else{
					str.append("<td style='padding-left: 0px;'></td>");
				}
				str.append("<td style='padding-left: 0px;'>"+dbViewSize+"</td>");
				str.append("<td style='padding-left: 0px;'>"+purchase2.getMrp()+"</td>");
				str.append("<td style='padding-left: 20px;'>"+purchase2.getQuantity()+"</td>");
				
				str.append("<td>"+discountTotal+"</td>");
				str.append("</tr>");
				
			}
			
			str.append("<input type='hidden' name='hiddenpay' id='hiddenpay' value='"+payAmount+"'>");
			
		
			
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
			str.append("<td>Disc <input type='text' name='vat' id='vat' size='3' maxlength='3' style='text-align: center;' value='"+vat+"' onchange='addVat(this.value)'>%</td>");
			str.append("<td>Rs. <input type='text' name='rupee' id='rupee' size='3' maxlength='5' style='text-align: center;' value='"+rupee+"' onchange='addRate(this.value)'></td>");
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
	
	public AccessoriesForm getModel() {
		
		return accessoriesForm;
	}

}
