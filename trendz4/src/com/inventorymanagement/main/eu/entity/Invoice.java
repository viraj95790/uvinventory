package com.inventorymanagement.main.eu.entity;

import java.util.ArrayList;

public class Invoice {
	
	private int customerid;
	
	private int invoiceNumber;
	
	private String customerName;
	
	private String mobileNumber;
	
	private ArrayList<Sale>saleList;
	
	private ArrayList<Purchase>purchaseList;
	
	private int vat;
	
	private int rupee;
	
	private double saleAmount;
	
	private double payamount;
	
	private double balance;
	
	private String lastUpdated;
	
	private double somoftotal;
	
	private String gtotal;
	
	private String modelNumber;
	
	private String userid;
	
	private String totalcgst;
	private String totalsgst;
	private double gst;
	

	
	
	

	
	
	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getGtotal() {
		return gtotal;
	}

	public void setGtotal(String gtotal) {
		this.gtotal = gtotal;
	}

	public double getSomoftotal() {
		return somoftotal;
	}

	public void setSomoftotal(double somoftotal) {
		this.somoftotal = somoftotal;
	}

	public double getPayamount() {
		return payamount;
	}

	public void setPayamount(double payamount) {
		this.payamount = payamount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public ArrayList<Sale> getSaleList() {
		return saleList;
	}

	public void setSaleList(ArrayList<Sale> saleList) {
		this.saleList = saleList;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public int getVat() {
		return vat;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public double getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(double saleAmount) {
		this.saleAmount = saleAmount;
	}

	public ArrayList<Purchase> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(ArrayList<Purchase> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public int getRupee() {
		return rupee;
	}

	public void setRupee(int rupee) {
		this.rupee = rupee;
	}

	

}
