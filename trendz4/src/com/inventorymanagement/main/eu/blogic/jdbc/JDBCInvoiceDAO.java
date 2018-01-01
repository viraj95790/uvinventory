package com.inventorymanagement.main.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.main.common.constants.Constants;
import com.inventorymanagement.main.eu.bi.CustomerDAO;
import com.inventorymanagement.main.eu.bi.InvoiceDAO;
import com.inventorymanagement.main.eu.bi.SaleDAO;
import com.inventorymanagement.main.eu.entity.Customer;
import com.inventorymanagement.main.eu.entity.Invoice;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.Sale;
import com.inventorymanagement.main.eu.entity.Timber;

public class JDBCInvoiceDAO extends JDBCBaseDAO implements InvoiceDAO{
	
	public JDBCInvoiceDAO(Connection connection){
		
		this.connection = connection;
	}

	public ArrayList<Sale> getInvoiceList(int customerid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Sale>list = new ArrayList<Sale>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT productid,mrp,quantity,total,color FROM invoice ");
		sql.append("inner join customer on customer.id = invoice.customerid and customer.id = "+customerid+" ");
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Sale sale = new Sale();
				int productid = rs.getInt(1);
				String productName = getProductName(productid,rs.getString(5));
				sale.setProductName(productName);
				sale.setMrp(rs.getDouble(2));
				sale.setQuantity(rs.getInt(3));
				sale.setTotal(rs.getDouble(4));
				
				list.add(sale);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	public ArrayList<Invoice> getHomeCustomerList(String fromDate,String toDate,String userid,int billType, int userType) {
		PreparedStatement preparedStatement = null;
		ArrayList<Invoice>list = new ArrayList<Invoice>();
		String sql = "";
		
		sql = "select id, name, mobileno, lastupdated, vat,rupee,userid from customer where lastupdated between ? and ? and userid='"+userid+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, fromDate);
			preparedStatement.setString(2, toDate);
			//preparedStatement.setString(3, userid);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Invoice invoice = new Invoice();
				invoice.setInvoiceNumber(rs.getInt(1));
				invoice.setCustomerName(rs.getString(2));
				invoice.setMobileNumber(rs.getString(3));
				invoice.setLastUpdated(DateTimeUtils.getDateinSimpleStringFormate(rs.getDate(4)));
				invoice.setVat(rs.getInt(5));
				invoice.setRupee(rs.getInt(6));
				invoice.setUserid(rs.getString(7));
				
				double sumoftotal = getSumOfSaleTotal(rs.getInt(1));
				invoice.setSomoftotal(sumoftotal);
				
				list.add(invoice);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	

	public ArrayList<Invoice> getCustomerList(String fromDate,String toDate,String userid,int billType, int userType) {
		PreparedStatement preparedStatement = null;
		ArrayList<Invoice>list = new ArrayList<Invoice>();
		String sql = "";
		
		if(userType>0){
			if(billType == Constants.PRIMARY_BILL){
				sql = "select id, name, mobileno, lastupdated, vat,rupee,userid from customer where lastupdated between ? and ? and userid='"+userid+"' and billtype = 1 order by id desc ";
			}else if(billType == Constants.SECONDARY_BILL){
				sql = "select id, name, mobileno, lastupdated, vat,rupee,userid from customer where lastupdated between ? and ? and userid='"+userid+"'  and billtype = 2 order by id desc ";
			}else{
				sql = "select id, name, mobileno, lastupdated, vat,rupee,userid from customer where lastupdated between ? and ? and userid='"+userid+"' order by id desc ";
			}
			
		}else{
			if(billType == Constants.PRIMARY_BILL){
				sql = "select id, name, mobileno, lastupdated, vat,rupee,userid from customer where lastupdated between ? and ? and billtype = 1 order by id desc ";
			}else if(billType == Constants.SECONDARY_BILL){
				sql = "select id, name, mobileno, lastupdated, vat,rupee,userid from customer where lastupdated between ? and ?  and billtype = 2 order by id desc ";
			}else{
				sql = "select id, name, mobileno, lastupdated, vat,rupee,userid from customer where lastupdated between ? and ?  order by id desc ";
			}
		}
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, fromDate);
			preparedStatement.setString(2, toDate);
			//preparedStatement.setString(3, userid);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Invoice invoice = new Invoice();
				invoice.setInvoiceNumber(rs.getInt(1));
				invoice.setCustomerName(rs.getString(2));
				invoice.setMobileNumber(rs.getString(3));
				invoice.setLastUpdated(DateTimeUtils.getDateinSimpleStringFormate(rs.getDate(4)));
				invoice.setVat(rs.getInt(5));
				invoice.setRupee(rs.getInt(6));
				invoice.setUserid(rs.getString(7));
				
				double sumoftotal = getSumOfSaleTotal(rs.getInt(1));
				invoice.setSomoftotal(sumoftotal);
				
				list.add(invoice);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private String getProductName(int productID,String colorName) {
		PreparedStatement preparedStatement = null;
		String result = "";
		//String sql = "select productname from product where id = "+productID+" ";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT categoryName from category inner join ");
		sql.append("product on category.id = product.categoryid and product.id= "+productID+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
			
			
			result = result + "("+colorName + " "+getArticleNumber(productID)+")";
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String getArticleNumber(int productID){
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select productname from product where id = "+productID+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	
	public ArrayList<Invoice> getCompanyList(String fromDate, String toDate, String userid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Invoice>list = new ArrayList<Invoice>();
		String sql = "select id, name,mobileno,vat,lastupdated from company where lastupdated between ? and ? and userid=? order by id desc ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, fromDate);
			preparedStatement.setString(2, toDate);
			preparedStatement.setString(3, userid);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Invoice invoice = new Invoice();
				invoice.setInvoiceNumber(rs.getInt(1));
				invoice.setCustomerName(rs.getString(2));
				invoice.setMobileNumber(rs.getString(3));
				invoice.setVat(rs.getInt(4));
				invoice.setLastUpdated(DateTimeUtils.getDateinSimpleStringFormate(rs.getTimestamp(5)));
				
				double sumoftotal = getSumOfPurchaseTotal(rs.getInt(1));
				invoice.setSomoftotal(sumoftotal);
				
				
				list.add(invoice);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private double getSumOfPurchaseTotal(int companyid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql  = "SELECT sum(total) FROM p_invoice where companyid = "+companyid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Purchase> getPurchaseInvoiceList(int invoiceNumber) {
		PreparedStatement preparedStatement = null;
		ArrayList<Purchase>list = new ArrayList<Purchase>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT productid,mrp,quantity,total,categoryid,size,color,actualsize,accessories,p_invoice.companyid,gst FROM p_invoice ");
		sql.append("inner join company on company.id = p_invoice.companyid and company.id = "+invoiceNumber+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Purchase purchase = new Purchase();
				if(rs.getInt(9)==0){
					purchase.setProductName(getProductName(rs.getInt(1),rs.getString(7)));
				}else{
					purchase.setProductName(getAccessoriesName(rs.getInt(1)));
				}
				
				purchase.setMrp(rs.getDouble(2));
				purchase.setQuantity(rs.getInt(3));
				
				purchase.setTotal(rs.getDouble(4));
				purchase.setCompanyID(rs.getInt(10));
				purchase.setTotalgst(rs.getDouble(11));
				
				String dbViewSize = "";
				if(rs.getInt(9)==0){
					String tempViewSize[] = rs.getString(6).split(",");
					String tempActualSize[] = rs.getString(8).split(",");
					
					/*for(int i=0;i<tempViewSize.length;i++){
						dbViewSize = dbViewSize + "(" + tempActualSize[i] +  ")" + tempViewSize[i] + ",";
					}
					
					dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);*/
					
					for(int i=0;i<tempViewSize.length;i++){
						dbViewSize = dbViewSize + tempViewSize[i] + ",";
					}
					dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);
					
					String tmpsz[] = dbViewSize.split(",");
					purchase.setSize(tmpsz[0]);
				}
				
				
				
				list.add(purchase);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public double getSumofPayAmount(int companyid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select sum(payment) from purchase_payment where companyid = "+companyid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getAccessoriesName(int productid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT productname FROM accessories where id="+productid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	public ArrayList<Purchase> getSaleInvoiceList(int invoiceNumber) {
		PreparedStatement preparedStatement = null;
		ArrayList<Purchase>list = new ArrayList<Purchase>();
		StringBuffer sql = new StringBuffer();
		double totalcgstvalue = 0;
		double totalsgstvalue = 0;
		
		sql.append("SELECT productid,mrp,quantity,total,categoryid,size,color,actualsize,accessories,vat FROM invoice ");
		sql.append("inner join customer on customer.id = invoice.customerid and customer.id = "+invoiceNumber+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Purchase purchase = new Purchase();
				purchase.setMrp(rs.getDouble(2));
				purchase.setQuantity(rs.getInt(3));
				purchase.setTotal(rs.getDouble(2) * purchase.getQuantity());
				purchase.setCategoryid(rs.getInt(5));
				purchase.setVat(rs.getInt(10));
				
				
				double cgst = getcgstvalue(rs.getString(1));
				double sgst = getsgstvalue(rs.getString(1));
				
				double gst = cgst + sgst;
				
				double cgstvalue = purchase.getMrp() * cgst /100;
				double sgstvalue = purchase.getMrp() * sgst /100;
				
				
				totalcgstvalue = totalcgstvalue + cgstvalue;
				totalsgstvalue = totalsgstvalue + sgstvalue;
				
				purchase.setTotalgst(gst);
				purchase.setCgst(cgst);
				purchase.setSgst(sgst);
				purchase.setTotalcgstvalue(totalcgstvalue);
				purchase.setTotalsgstvalue(totalsgstvalue);
				
				
				String dbViewSize = "";
				if(rs.getInt(9)==0){
					String tempViewSize[] = rs.getString(6).split(",");
					String tempActualSize[] = rs.getString(8).split(",");
					
					/*for(int i=0;i<tempViewSize.length;i++){
						dbViewSize = dbViewSize + "(" + tempActualSize[i] +  ")" + tempViewSize[i] + ",";
					}
					
					dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);*/
					
					for(int i=0;i<tempViewSize.length;i++){
						dbViewSize = dbViewSize + tempViewSize[i] + ",";
					}
					dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);
					
					
					
					purchase.setProductName(getProductName(rs.getInt(1),rs.getString(7)));
				}else{
					purchase.setProductName(getAccessoriesName(rs.getInt(1)));
				}
				
				purchase.setSize(dbViewSize);
				
				
				
				list.add(purchase);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private double getcgstvalue(String productid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		double cgst = 0;
		String sql = "select cgst from product where id='"+productid+"' ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				cgst = rs.getDouble(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return cgst;
	}

	private double getsgstvalue(String productid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		double sgst = 0;
		String sql = "select sgst from product where id='"+productid+"' ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				sgst = rs.getDouble(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return sgst;
	}

	public double getSumOfSaleTotal(int invoiceNumber) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql  = "SELECT sum(total) FROM invoice where customerid = "+invoiceNumber+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkCustomerIDExist(int customerID) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from customer where id = "+customerID+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Invoice> getCustomerListBySalesMan(String fromDate,
			String toDate, String userId, int employeeid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Invoice>list = new ArrayList<Invoice>();
		String sql = "select id, name, mobileno, lastupdated, vat from customer where lastupdated between ? and ? and userid=? and employeeid = "+employeeid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, fromDate);
			preparedStatement.setString(2, toDate);
			preparedStatement.setString(3, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Invoice invoice = new Invoice();
				invoice.setInvoiceNumber(rs.getInt(1));
				invoice.setCustomerName(rs.getString(2));
				invoice.setMobileNumber(rs.getString(3));
				invoice.setLastUpdated(DateTimeUtils.getDateinSimpleStringFormate(rs.getDate(4)));
				invoice.setVat(rs.getInt(5));
				
				double sumoftotal = getSumOfSaleTotal(rs.getInt(1));
				invoice.setSomoftotal(sumoftotal);
				
				list.add(invoice);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public double getSumOfSaleAmount(int invoiceNumber) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select sum(payment) from sale_payment where companyid = "+invoiceNumber+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public double getInvoiceDiscount(int selectedinvoiceid) {
		PreparedStatement preparedStatement = null;
		String sql = "SELECT vat FROM customer where id = "+selectedinvoiceid+" ";
		double result = 0;
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				
				result = rs.getDouble(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return result;
	}

	public double getInvoiceRupee(int selectedinvoiceid) {
		PreparedStatement preparedStatement = null;
		String sql = "SELECT rupee FROM customer where id = "+selectedinvoiceid+" ";
		double result = 0;
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				
				result = rs.getDouble(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return result;
	}

	public ArrayList<Purchase> getPurchaseAccountList(String fromDate,
			String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Purchase>list = new ArrayList<Purchase>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT companyid,quantity,mrp,vat,lastupdated,total FROM p_invoice inner join company on ");
		sql.append("company.id = p_invoice.companyid ");
		sql.append("where lastupdated between '"+fromDate+"' and '"+toDate+"' ");
		sql.append("group by p_invoice.companyid order by p_invoice.companyid desc");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double gtotal = 0;
			double payAmount = 0;
			while(rs.next()){
				Purchase purchase = new Purchase();
				purchase.setId(rs.getInt(1));
				purchase.setQuantity(rs.getInt(2));
				purchase.setMrp(rs.getInt(3));
				purchase.setVat(rs.getInt(4));
				purchase.setDate(rs.getString(5));
				
				
				gtotal = getSumOfPurchaseTotal(rs.getInt(1));
				payAmount = getSumofPayAmount(purchase.getId());
				double purchasetotral = gtotal;
				double per  = (purchase.getVat()*purchasetotral)/100;
				purchasetotral = purchasetotral - per;
				double balance = purchasetotral - payAmount;
				
				purchase.setDabit(Double.toString(purchasetotral));
				purchase.setCredit(Double.toString(payAmount));
				purchase.setBalance(balance);
				
				
				list.add(purchase);
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Invoice> getRecordPaymentCompanyList(String invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Invoice>list = new ArrayList<Invoice>();
		String sql = "select id, name,mobileno,vat,lastupdated from company where id="+invoiceid+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
		
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Invoice invoice = new Invoice();
				invoice.setInvoiceNumber(rs.getInt(1));
				invoice.setCustomerName(rs.getString(2));
				invoice.setMobileNumber(rs.getString(3));
				invoice.setVat(rs.getInt(4));
				invoice.setLastUpdated(DateTimeUtils.getDateinSimpleStringFormate(rs.getTimestamp(5)));
				
				double sumoftotal = getSumOfPurchaseTotal(rs.getInt(1));
				invoice.setSomoftotal(sumoftotal);
				
				
				list.add(invoice);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Purchase> getSaleAccountList(String fromDate, String toDate,String clientid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Purchase>list = new ArrayList<Purchase>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT customerid,quantity,mrp,vat,lastupdated,rupee FROM invoice inner join customer on ");
		sql.append("customer.id = invoice.customerid ");
		sql.append("where lastupdated between '"+fromDate+"' and '"+toDate+"' ");
		sql.append("and clientid = "+clientid+" ");
		sql.append("group by invoice.customerid ");
		sql.append("order by invoice.customerid desc ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double gtotal = 0;
			double payAmount = 0;
			while(rs.next()){
				Purchase purchase = new Purchase();
				purchase.setId(rs.getInt(1));
				purchase.setQuantity(rs.getInt(2));
				purchase.setMrp(rs.getInt(3));
				purchase.setVat(rs.getInt(4));
				purchase.setDate(rs.getString(5));
				purchase.setRupee(rs.getInt(6));
				
				
				gtotal = getGrandSumOfSaleTotal(rs.getInt(1));
				payAmount = getSumOfSaleAmount(purchase.getId());
				double purchasetotral = gtotal;
				
				double per = 0;
				if(purchase.getVat()>0){
					per  = (purchase.getVat()*purchasetotral)/100;
				}
				
				if(purchase.getRupee()>0){
					per = purchase.getRupee();
				}
				
				purchase.setDabit(Double.toString(purchasetotral));
				
				purchasetotral = purchasetotral - per;
				double balance = purchasetotral - payAmount;
				
				
				purchase.setCredit(Double.toString(payAmount));
				purchase.setBalance(balance);
				
				
				list.add(purchase);
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	private double getGrandSumOfSaleTotal(int id) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT mrp,quantity FROM invoice where customerid = "+id+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				result = result + (rs.getDouble(1) * rs.getInt(2));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Invoice> getSaleCustomerList(String invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Invoice>list = new ArrayList<Invoice>();
		String sql = "select id, name, mobileno, lastupdated, vat,rupee from customer  where id="+invoiceid+"";
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Invoice invoice = new Invoice();
				invoice.setInvoiceNumber(rs.getInt(1));
				invoice.setCustomerName(rs.getString(2));
				invoice.setMobileNumber(rs.getString(3));
				invoice.setLastUpdated(DateTimeUtils.getDateinSimpleStringFormate(rs.getDate(4)));
				invoice.setVat(rs.getInt(5));
				invoice.setRupee(rs.getInt(6));
				
				double sumoftotal = getSumOfSaleTotal(rs.getInt(1));
				invoice.setSomoftotal(sumoftotal);
				
				list.add(invoice);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public String getclientid(int selectedinvoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT clientid FROM customer where id = "+selectedinvoiceid+" ";
		
		try{
			preparedStatement  = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public String getInvoicePreparedBy(int selectedinvoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT userid FROM customer where id = "+selectedinvoiceid+" ";
		
		try{
			preparedStatement  = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public Customer getInvoiceCustomerDetails(String invoiceid) {
		PreparedStatement preparedStatement = null;
		Customer customer = new Customer();
		String sql = "SELECT name,mobileno FROM customer where id = "+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				customer.setName(rs.getString(1));
				customer.setMob(rs.getString(2));
			}
		}catch(Exception e){
			
		}
		
		
		return customer;
	}

	public ArrayList<Timber> getpayroll() {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ArrayList<Timber> list = new ArrayList<Timber>();
		String sql = "select id, date, payee, paymode, credit, debit, balance,discription from payroll";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Timber timber = new Timber();
				CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
				Customer customer = customerDAO.editdata(rs.getString(3));
				
				timber.setId(rs.getInt(1));
				timber.setDate(rs.getString(2));
				timber.setCustomer(customer.getName()+" "+customer.getSurname());
				timber.setPaymode(rs.getString(4));
				timber.setCredit(rs.getString(5));
				timber.setDebit(rs.getString(6));
				timber.setBalance(rs.getString(7));
				timber.setDiscription(rs.getString(8));
					
				list.add(timber);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public double getpayrollbalance() {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		double result = 0;
		try {
			String sql = "select balance from payroll order by id desc limit 0,1";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = Double.parseDouble(rs.getString(1));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Timber> getvendorList() {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ArrayList<Timber>list = new ArrayList<Timber>();
		String sql = "SELECT id, name FROM customer_form where usertype = 1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Timber timber = new Timber();
				timber.setId(rs.getInt(1));
				timber.setVendor(rs.getString(2));
				
				list.add(timber);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public String getcustomerid(String invoiceid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		String customerid = " ";
		try {
			String sql = "select customerid from customer_sale where id="+invoiceid+"";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				customerid = rs.getString(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return customerid;
	}

	public int insertdata(double pbalance, String customerid, String howpaid,
			String paymentrecieved) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			String sql = "insert into payroll(date, payee, paymode, credit, debit, balance) values(?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, DateTimeUtils.getLastModifiedDate(new Date()));
			preparedStatement.setString(2, customerid);
			preparedStatement.setString(3, howpaid);
			preparedStatement.setString(4, paymentrecieved);
			preparedStatement.setString(5, "0");
			preparedStatement.setString(6, Double.toString(pbalance));
		    
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public int savedata(Timber timber, double balance, String customerid,
			String howpaid, String paymentrecieved, String date) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			String sql = "insert into payroll(date, discription, paymode, credit, debit, balance, payee) values(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			
			
			preparedStatement.setString(1, date);
			preparedStatement.setString(2, timber.getDiscription());
			preparedStatement.setString(3, howpaid);
			preparedStatement.setString(4, "0");
			preparedStatement.setString(5, timber.getDebitx());
			preparedStatement.setString(6, Double.toString(balance));
			preparedStatement.setString(7, "");
			
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Timber> getInvoiceList(String vnderid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ArrayList<Timber>list = new ArrayList<Timber>();
		String sql = "SELECT id FROM vendor_purchase where vendorid = "+vnderid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Timber timber = new Timber();
				timber.setId(rs.getInt(1));
			
				
				list.add(timber);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public Timber getVendorPurchaseDetails(String invoiceid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		Timber timber = new Timber();
		try {
			String sql = "select vendorid,quantity,purchase,discount,date from vendor_purchase where id ="+invoiceid+"";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				timber.setVendorid(rs.getString(1));
				timber.setQuantity(rs.getString(2));
				timber.setPurchase(rs.getString(3));
				timber.setDiscount(rs.getString(4));
				timber.setDate(rs.getString(5));
				
				double credit = getCredit(rs.getInt(1));
				timber.setCredit(DateTimeUtils.changeFormat(credit));
				
				double payamount = getSumofPurchasePayment(Integer.parseInt(invoiceid));
				timber.setPayamount(DateTimeUtils.changeFormat(payamount));
				
				double balance = Double.parseDouble(timber.getPurchase()) - payamount;
				timber.setBalance(DateTimeUtils.changeFormat(balance));
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return timber;
	}

	private double getSumofPurchasePayment(int int1) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		double payamount = 0;
		try {
			String sql = "select sum(payment)from purchase_payment where companyid="+int1+" ";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				payamount = rs.getDouble(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return payamount;
	}

	private double getCredit(int int1) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		double credit = 0;
		try {
			String sql = "select sum(payment) from purchase_payment where companyid="+int1+"";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				credit = rs.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return credit;
	}

	public Invoice getgstvalue(int invoiceNumber) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		Invoice invoice = new Invoice();
		double totalcgstvalue = 0, totalsgstvalue=0 ;
		String sql = "select total, gst from p_invoice where companyid="+invoiceNumber+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				invoice.setSomoftotal(rs.getDouble(1));
				invoice.setGst(rs.getDouble(2));
				
				double gst = invoice.getSomoftotal()*invoice.getGst()/100 ;
				
				double cgstvalue = gst/2;
				totalcgstvalue = totalcgstvalue + cgstvalue;
				
				double sgstvalue = gst/2;
				totalsgstvalue = totalsgstvalue + sgstvalue;
				
				invoice.setTotalcgst(DateTimeUtils.changeFormat(totalcgstvalue));
				invoice.setTotalsgst(DateTimeUtils.changeFormat(totalsgstvalue));		
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return invoice;
	}

	
	

}
