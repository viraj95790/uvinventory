package com.inventorymanagement.main.web.form;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import com.inventorymanagement.main.eu.entity.Invoice;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.SalesMan;
import com.inventorymanagement.main.eu.entity.Timber;

public class InvoiceForm {

	private ArrayList<Invoice> invoiceList;

	private String toDate = "";

	private String date;

	private String fromDate = "";

	private int billType;

	private int selectedbillType;

	private ArrayList<SalesMan> populatedSalesManList;

	private int employeeid;

	private String name;

	private int totalQuantity;

	private double totalAmount;

	ArrayList<Purchase> saleaccList;

	private String paymentrecieved;

	private String howpaid;

	private String paymentnote;

	private String invoiceid;

	private String modelNumber;

	private String customerName;

	private String mobileNumber;
	
	private String totalcgst;
	private String totalsgst;

	private String debitx;
	private String creditx;
	private String balance;

	private String commusion;

	ArrayList<Timber> payrolList;
	ArrayList<Timber> vendorList;
	private Collection<Timber> paylist;
	
	
	
	

	
	public ArrayList<Timber> getVendorList() {
		return vendorList;
	}

	public void setVendorList(ArrayList<Timber> vendorList) {
		this.vendorList = vendorList;
	}

	public Collection<Timber> getPaylist() {
		return paylist;
	}

	public void setPaylist(Collection<Timber> paylist) {
		this.paylist = paylist;
	}

	
	
	

	public String getTotalcgst() {
		return totalcgst;
	}

	public void setTotalcgst(String totalcgst) {
		this.totalcgst = totalcgst;
	}

	public String getTotalsgst() {
		return totalsgst;
	}

	public void setTotalsgst(String totalsgst) {
		this.totalsgst = totalsgst;
	}

	public ArrayList<Timber> getPayrolList() {
		return payrolList;
	}

	public void setPayrolList(ArrayList<Timber> payrolList) {
		this.payrolList = payrolList;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCommusion() {
		return commusion;
	}

	public void setCommusion(String commusion) {
		this.commusion = commusion;
	}

	public String getDebitx() {
		return debitx;
	}

	public void setDebitx(String debitx) {
		this.debitx = debitx;
	}

	public String getCreditx() {
		return creditx;
	}

	public void setCreditx(String creditx) {
		this.creditx = creditx;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPaymentrecieved() {
		return paymentrecieved;
	}

	public void setPaymentrecieved(String paymentrecieved) {
		this.paymentrecieved = paymentrecieved;
	}

	public String getHowpaid() {
		return howpaid;
	}

	public void setHowpaid(String howpaid) {
		this.howpaid = howpaid;
	}

	public String getPaymentnote() {
		return paymentnote;
	}

	public void setPaymentnote(String paymentnote) {
		this.paymentnote = paymentnote;
	}

	public String getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}

	public ArrayList<Purchase> getSaleaccList() {
		return saleaccList;
	}

	public void setSaleaccList(ArrayList<Purchase> saleaccList) {
		this.saleaccList = saleaccList;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getSelectedbillType() {
		return selectedbillType;
	}

	public void setSelectedbillType(int selectedbillType) {
		this.selectedbillType = selectedbillType;
	}

	public int getBillType() {
		return billType;
	}

	public void setBillType(int billType) {
		this.billType = billType;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public ArrayList<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(ArrayList<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public ArrayList<SalesMan> getPopulatedSalesManList() {
		return populatedSalesManList;
	}

	public void setPopulatedSalesManList(
			ArrayList<SalesMan> populatedSalesManList) {
		this.populatedSalesManList = populatedSalesManList;
	}

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
