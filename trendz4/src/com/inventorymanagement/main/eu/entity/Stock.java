package com.inventorymanagement.main.eu.entity;

import java.util.ArrayList;

public class Stock {
	
	private int remainingStock;
	
	private int quantity;
	
	private String branchName;
	
	private String userID;
	
	private ArrayList<Stock>branchStockList;
	
	private int branchID;
	
	private String productName;

	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ArrayList<Stock> getBranchStockList() {
		return branchStockList;
	}

	public void setBranchStockList(ArrayList<Stock> branchStockList) {
		this.branchStockList = branchStockList;
	}

	public int getRemainingStock() {
		return remainingStock;
	}

	public void setRemainingStock(int remainingStock) {
		this.remainingStock = remainingStock;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}

}
