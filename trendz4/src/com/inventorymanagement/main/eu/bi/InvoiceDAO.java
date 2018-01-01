package com.inventorymanagement.main.eu.bi;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Customer;
import com.inventorymanagement.main.eu.entity.Invoice;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.Sale;
import com.inventorymanagement.main.eu.entity.Timber;

public interface InvoiceDAO {

	ArrayList<Sale> getInvoiceList(int customerid);

	ArrayList<Invoice> getCustomerList(String fromDate,String toDate,String usreid,int billType, int userType);

	ArrayList<Invoice> getCompanyList(String fromDate, String toDate,String userid);

	ArrayList<Purchase> getPurchaseInvoiceList(int invoiceNumber);

	ArrayList<Purchase> getSaleInvoiceList(int invoiceNumber);

	boolean checkCustomerIDExist(int companyId);

	ArrayList<Invoice> getCustomerListBySalesMan(String fromDate,
			String toDate, String userId, int employeeid);

	double getSumofPayAmount(int invoiceNumber);

	double getSumOfSaleAmount(int invoiceNumber);

	double getSumOfSaleTotal(int companyId);

	double getInvoiceDiscount(int selectedinvoiceid);

	double getInvoiceRupee(int selectedinvoiceid);

	ArrayList<Purchase> getPurchaseAccountList(String fromDate, String toDate);

	ArrayList<Invoice> getRecordPaymentCompanyList(String invoiceid);

	ArrayList<Purchase> getSaleAccountList(String fromDate, String toDate,String selectedid);

	ArrayList<Invoice> getSaleCustomerList(String invoiceid);

	String getclientid(int selectedinvoiceid);

	String getInvoicePreparedBy(int selectedinvoiceid);

	ArrayList<Invoice> getHomeCustomerList(String fromDate, String toDate,
			String userid, int billType, int userType);

	Customer getInvoiceCustomerDetails(String invoiceid);

	ArrayList<Timber> getpayroll();

	double getpayrollbalance();

	ArrayList<Timber> getvendorList();

	String getcustomerid(String invoiceid);

	int insertdata(double pbalance, String customerid, String howpaid,
			String paymentrecieved);

	int savedata(Timber timber, double balance, String customerid, String howpaid,
			String paymentrecieved, String date);

	Timber getVendorPurchaseDetails(String invoiceid);

	ArrayList<Timber> getInvoiceList(String vnderid);

	Invoice getgstvalue(int invoiceNumber);

	
	

}
