package com.inventorymanagement.main.web.form;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Invoice;
import com.inventorymanagement.main.eu.entity.Purchase;

public class PurchaseInvoiceForm {
	
private ArrayList<Invoice>invoiceList;
	
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
