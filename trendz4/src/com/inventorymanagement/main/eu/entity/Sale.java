package com.inventorymanagement.main.eu.entity;

public class Sale {
	
	private int productID;
	
	private double mrp;
	
	private int quantity;
	
	private double total;
	
	private String productName;
	
	private int id;
	
	private String customerName;
	
	private String mobileno;
	
	private int vat;
	
	private int rupee;
	
	private int categoryID;

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public int getVat() {
		return vat;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public int getRupee() {
		return rupee;
	}

	public void setRupee(int rupee) {
		this.rupee = rupee;
	}
	
	

}
