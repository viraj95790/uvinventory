package com.inventorymanagement.main.web.form;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Invoice;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.SalesMan;

public class DefaultForm {
private String toDate = "";
	
	private String fromDate = "";
	
	private int totalQuantity = 0;
	
	private double totalAmount = 0;
	
	ArrayList<Purchase>purchaseaccList;
	
	private String paymentrecieved;
	
	private String howpaid;
	
	private String paymentnote;

	private String invoiceid;
	
	private String debitx;
	
	private String creditx;
	
	private String balancex;
	
	private ArrayList<Invoice>invoiceList;
	
	
	private int billType = 3;
	
	private int selectedbillType;
	
	private int saletotalQuantity;
	
	private double saletotalAmount;
	
	private ArrayList<Invoice>saleinvoiceList;
	
	private ArrayList<SalesMan>attendanceList;
	
	ArrayList<SalesMan>salesmanlist;
	
	ArrayList<Branch>userList;
	
	ArrayList<Product>shopStockList;
	
	
	
	public ArrayList<Product> getShopStockList() {
		return shopStockList;
	}

	public void setShopStockList(ArrayList<Product> shopStockList) {
		this.shopStockList = shopStockList;
	}

	public ArrayList<Branch> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<Branch> userList) {
		this.userList = userList;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	private String userid;
	
	
	
	

	public ArrayList<SalesMan> getSalesmanlist() {
		return salesmanlist;
	}

	public void setSalesmanlist(ArrayList<SalesMan> salesmanlist) {
		this.salesmanlist = salesmanlist;
	}

	public ArrayList<SalesMan> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(ArrayList<SalesMan> attendanceList) {
		this.attendanceList = attendanceList;
	}

	public int getBillType() {
		return billType;
	}

	public void setBillType(int billType) {
		this.billType = billType;
	}

	public int getSelectedbillType() {
		return selectedbillType;
	}

	public void setSelectedbillType(int selectedbillType) {
		this.selectedbillType = selectedbillType;
	}

	public int getSaletotalQuantity() {
		return saletotalQuantity;
	}

	public void setSaletotalQuantity(int saletotalQuantity) {
		this.saletotalQuantity = saletotalQuantity;
	}

	public double getSaletotalAmount() {
		return saletotalAmount;
	}

	public void setSaletotalAmount(double saletotalAmount) {
		this.saletotalAmount = saletotalAmount;
	}

	public ArrayList<Invoice> getSaleinvoiceList() {
		return saleinvoiceList;
	}

	public void setSaleinvoiceList(ArrayList<Invoice> saleinvoiceList) {
		this.saleinvoiceList = saleinvoiceList;
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

	public String getBalancex() {
		return balancex;
	}

	public void setBalancex(String balancex) {
		this.balancex = balancex;
	}

	public String getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
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

	public ArrayList<Purchase> getPurchaseaccList() {
		return purchaseaccList;
	}

	public void setPurchaseaccList(ArrayList<Purchase> purchaseaccList) {
		this.purchaseaccList = purchaseaccList;
	}

	public ArrayList<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(ArrayList<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
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
}
