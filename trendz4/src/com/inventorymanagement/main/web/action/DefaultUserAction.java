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
import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.bi.CustomerDAO;
import com.inventorymanagement.main.eu.bi.InvoiceDAO;
import com.inventorymanagement.main.eu.bi.PurchaseDAO;
import com.inventorymanagement.main.eu.bi.SalesManDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCBranchDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCCustomerDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCInvoiceDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCPurchaseDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCSalesManDAO;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Customer;
import com.inventorymanagement.main.eu.entity.Invoice;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.SalesMan;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.DefaultForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;



public class DefaultUserAction extends BaseAction implements Preparable, ModelDriven<DefaultForm>{
	
	DefaultForm defaultForm = new DefaultForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	public String execute() throws Exception {
		
		//yestrday purchase
		if(!verifyLogin(request)){
			return "login";
		}
		
		String fromDate = defaultForm.getFromDate();
		String toDate = defaultForm.getToDate();	
		String userid = defaultForm.getUserid();
		
		if(fromDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1); 
			fromDate = dateFormat.format(cal.getTime());
			defaultForm.setFromDate(fromDate);
		}
		if(toDate.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1); 
			toDate = dateFormat.format(cal.getTime());
			defaultForm.setToDate(toDate);
		}
		
		if(defaultForm.getFromDate()!=null){
			 fromDate = DateTimeUtils.changeDateFormat(defaultForm.getFromDate());
			 toDate = DateTimeUtils.changeDateFormat(defaultForm.getToDate());
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
					
					payAmount = invoiceDAO.getSumofPayAmount(invoice.getInvoiceNumber());
					double purchasetotral = invoice.getSomoftotal();
					double per  = (invoice.getVat()*purchasetotral)/100;
					
					invoice.setSaleAmount(payAmount);
					invoice.setPayamount(payAmount);
					
					
					invoice.setSomoftotal(purchasetotral+per);
					
					double balance = invoice.getSomoftotal() - payAmount;
					invoice.setBalance(balance);
					invoiceList.add(invoice);
				}
				
				
				
				defaultForm.setInvoiceList(invoiceList);
				defaultForm.setTotalQuantity(totalQuantity);
				defaultForm.setTotalAmount(totalAmount);
				
				
				
				//sale report
				
				int saletotalQuantity = 0;
				double saletotalAmount = 0;
				
				
			/*	if(loginInfo.getCode().equals(Constants.CODECONSTANT)){
					invoiceForm.setBillType(3);
				}*/
				
				ArrayList<Invoice>salecustomerList = invoiceDAO.getHomeCustomerList(fromDate,toDate,userid,defaultForm.getBillType(),loginInfo.getUserType());
				ArrayList<Invoice>saleinvoiceList = new ArrayList<Invoice>();
				
				for(Invoice invoice : salecustomerList){
					
					String clientid   = invoiceDAO.getclientid(invoice.getInvoiceNumber());
					CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
					Customer customer = customerDAO.editdata(clientid);
					
					invoice.setCustomerName(customer.getName() + " " + customer.getSurname() );
					invoice.setMobileNumber(customer.getMob());
					
					ArrayList<Purchase>salelist = invoiceDAO.getSaleInvoiceList(invoice.getInvoiceNumber());
					invoice.setPurchaseList(salelist);
					double gtotal = 0;
					double payAmount = 0;
					for(Purchase purchase : salelist){
						//payAmount = payAmount+purchase.getTotal();
						saletotalQuantity = saletotalQuantity + purchase.getQuantity();
						saletotalAmount = saletotalAmount + purchase.getTotal();
						gtotal = gtotal + (purchase.getMrp() * purchase.getQuantity());
					}
					invoice.setGtotal(Double.toString(gtotal));
					/*double per  = (invoice.getVat()*payAmount)/100;
					payAmount = payAmount-per;*/
					payAmount = invoiceDAO.getSumOfSaleAmount(invoice.getInvoiceNumber());
					double balance = invoice.getSomoftotal() - payAmount;
					invoice.setBalance(balance);
					invoice.setSaleAmount(payAmount);
					invoice.setPayamount(payAmount);
					saleinvoiceList.add(invoice);
				}
				
				
				
				defaultForm.setSaleinvoiceList(saleinvoiceList);
				defaultForm.setSelectedbillType(defaultForm.getBillType());
				defaultForm.setSaletotalQuantity(saletotalQuantity);
				defaultForm.setSaletotalAmount(saletotalAmount);
				
				
				//attendance
				SalesManDAO salesManDAO = new JDBCSalesManDAO(connection);
				ArrayList<SalesMan>salesmanlist = salesManDAO.getSalesmanAttendanceList(fromDate,toDate,userid);
				defaultForm.setSalesmanlist(salesmanlist);
				
				PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection) ;
				BranchDAO branchDAO = new JDBCBranchDAO(connection);
				Branch branch = branchDAO.getuser(defaultForm.getUserid());
				ArrayList<Product>shopStockList = purchaseDAO.getshopStockList(branch.getId());
				defaultForm.setShopStockList(shopStockList);
				
				/*SalesMan salesMan = salesManDAO.getSalesMan(salesManForm.getEmployeeid());
				
				double totalSalary = attendanceList.size() * salesMan.getSalaryperday();
				
				salesManForm.setTotalSalary(totalSalary);*/
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			 
		}
		
	
		return HOMEPAGE;
	}

	public DefaultForm getModel() {
		// TODO Auto-generated method stub
		return defaultForm;
	}

	public void prepare() throws Exception {
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			BranchDAO branchDAO = new JDBCBranchDAO(connection);
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);
			ArrayList<Branch>userList = branchDAO.getUserList();
			defaultForm.setUserList(userList);
			defaultForm.setUserid(loginInfo.getUserId());
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	}

}
