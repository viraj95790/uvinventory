package com.inventorymanagement.main.web.action;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.main.eu.bi.InvoiceDAO;
import com.inventorymanagement.main.eu.bi.PurchaseDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCInvoiceDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCPurchaseDAO;
import com.inventorymanagement.main.eu.entity.Invoice;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.Sale;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.PurchaseInvoiceForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class PurchaseInvoiceAction extends BaseAction implements ModelDriven<PurchaseInvoiceForm>{

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	
	PurchaseInvoiceForm purchaseInvoiceForm = new PurchaseInvoiceForm();
	
	
	public String execute() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		String fromDate = purchaseInvoiceForm.getFromDate();
		String toDate = purchaseInvoiceForm.getToDate();	
		
		
		if(fromDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7); 
			fromDate = dateFormat.format(cal.getTime());
			purchaseInvoiceForm.setFromDate(fromDate);
		}
		if(toDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			toDate = dateFormat.format(cal.getTime());
			purchaseInvoiceForm.setToDate(toDate);
		}
		
		if(purchaseInvoiceForm.getFromDate()!=null){
			 fromDate = DateTimeUtils.changeDateFormat(purchaseInvoiceForm.getFromDate());
			 toDate = DateTimeUtils.changeDateFormat(purchaseInvoiceForm.getToDate());
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			Connection connection = null;
			int totalQuantity = 0;
			double totalAmount = 0;
			try{
				connection = Connection_provider.getconnection();
				InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);
				
				ArrayList<Invoice>customerList = invoiceDAO.getCompanyList(fromDate,toDate,loginInfo.getUserId());
				ArrayList<Invoice>invoiceList = new ArrayList<Invoice>();
				for(Invoice invoice : customerList){
					ArrayList<Purchase>purchaseList = invoiceDAO.getPurchaseInvoiceList(invoice.getInvoiceNumber());
					invoice.setPurchaseList(purchaseList);
					double payAmount = 0;
					for(Purchase purchase : purchaseList){
						//payAmount = payAmount+purchase.getTotal();
						totalQuantity = totalQuantity + purchase.getQuantity();
						totalAmount = totalAmount + purchase.getTotal();
					}
					
					Invoice invoice2 = invoiceDAO.getgstvalue(invoice.getInvoiceNumber());
					invoice.setTotalcgst(invoice2.getTotalcgst());
					invoice.setTotalsgst(invoice2.getTotalsgst());
					
					payAmount = invoiceDAO.getSumofPayAmount(invoice.getInvoiceNumber());
					double purchasetotral = invoice.getSomoftotal();
					double per  = (invoice.getVat()*purchasetotral)/100;
					
					invoice.setSaleAmount(payAmount);
					invoice.setPayamount(payAmount);
					
					
					invoice.setSomoftotal(purchasetotral-per);
					
					double balance = invoice.getSomoftotal() - payAmount;
					invoice.setBalance(balance);
					invoiceList.add(invoice);
				}
				
				
				
				purchaseInvoiceForm.setInvoiceList(invoiceList);
				purchaseInvoiceForm.setTotalQuantity(totalQuantity);
				purchaseInvoiceForm.setTotalAmount(totalAmount);
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 
		}
		
		
		return SUCCESS;
	}
	
	public String account(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		String fromDate = purchaseInvoiceForm.getFromDate();
		String toDate = purchaseInvoiceForm.getToDate();	
		
		
		if(fromDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7); 
			fromDate = dateFormat.format(cal.getTime());
			purchaseInvoiceForm.setFromDate(fromDate);
		}
		if(toDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DATE, -7); 
			toDate = dateFormat.format(cal.getTime());
			purchaseInvoiceForm.setToDate(toDate);
		}
		
		
		if(!fromDate.equals("")){
			String temp[]= fromDate.split("/");
			fromDate = temp[2]+"-"+temp[1]+"-"+temp[0];
		}
		if(!toDate.equals("")){
			String temp1[]= toDate.split("/");
			toDate = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
		}
		
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);
			
			ArrayList<Purchase>purchaseaccList = invoiceDAO.getPurchaseAccountList(fromDate,toDate);
			purchaseInvoiceForm.setPurchaseaccList(purchaseaccList);
			
			double d=0, c=0, b = 0;
			for(Purchase purchase : purchaseaccList){
				
				d = d + Double.parseDouble(purchase.getDabit());
				c = c + Double.parseDouble(purchase.getCredit());
				b = b + purchase.getBalance();
				
			}
			purchaseInvoiceForm.setDebitx(Double.toString(d));
			purchaseInvoiceForm.setCreditx(Double.toString(c));
			purchaseInvoiceForm.setBalancex(Double.toString(b));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "purchaseaccount";
	}
	
	public String record(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);
			
			String invoiceid = request.getParameter("invoiceid");
			
			ArrayList<Invoice>customerList = invoiceDAO.getRecordPaymentCompanyList(invoiceid);
			ArrayList<Invoice>invoiceList = new ArrayList<Invoice>();
			for(Invoice invoice : customerList){
				ArrayList<Purchase>purchaseList = invoiceDAO.getPurchaseInvoiceList(invoice.getInvoiceNumber());
				invoice.setPurchaseList(purchaseList);
				double payAmount = 0;
				
				Invoice invoice2 = invoiceDAO.getgstvalue(invoice.getInvoiceNumber());
				invoice.setTotalcgst(invoice2.getTotalcgst());
				invoice.setTotalsgst(invoice2.getTotalsgst());
				
				payAmount = invoiceDAO.getSumofPayAmount(invoice.getInvoiceNumber());
				double purchasetotral = invoice.getSomoftotal();
				double per  = (invoice.getVat()*purchasetotral)/100;
				
				invoice.setSaleAmount(payAmount);
				invoice.setPayamount(payAmount);
				
				
				invoice.setSomoftotal(purchasetotral-per);
				
				double balance = invoice.getSomoftotal() - payAmount;
				invoice.setBalance(balance);
				invoiceList.add(invoice);
			}
			
			
			
			purchaseInvoiceForm.setInvoiceList(invoiceList);
			purchaseInvoiceForm.setInvoiceid(invoiceid);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "purchasepayment";
	}
	
	
	
	public String payment(){
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			
			int savepayment = purchaseDAO.savePurchasePayment(Integer.parseInt(purchaseInvoiceForm.getInvoiceid()),purchaseInvoiceForm.getHowpaid(),purchaseInvoiceForm.getPaymentrecieved(),purchaseInvoiceForm.getPaymentnote());
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return "payment";
	}
	
	
	
	
	public PurchaseInvoiceForm getModel() {
		
		return purchaseInvoiceForm;
	}

}
