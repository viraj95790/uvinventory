package com.inventorymanagement.main.eu.entity;

import java.util.ArrayList;

public class AdminSubcategory {

	private int id;
	
	private String productName;
	
	private String description;
	
	private int quantity;
	
	private int remaioningStock;
	
	private int categoryID;
	
	private String lastUpdated;
	
	private String categoryName;
	
	private String branchName;
	
	private int subCategoryID;
	
	private String userid;
	
	private int branchid;
	
	private Double price;
	
	private int totalAmount;
	
	private String profitmargin;
	
	private String cgst;
	
	private String sgst;
	
	ArrayList<Stock>branchStockList;
	
	private int n_stock;
	
	private int s_stock;
	
	private int b_stock;
	
	private int total;
	
	private int gender;
	
	
	
	

	public String getProfitmargin() {
		return profitmargin;
	}

	public void setProfitmargin(String profitmargin) {
		this.profitmargin = profitmargin;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getSgst() {
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getRemaioningStock() {
		return remaioningStock;
	}

	public void setRemaioningStock(int remaioningStock) {
		this.remaioningStock = remaioningStock;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getSubCategoryID() {
		return subCategoryID;
	}

	public void setSubCategoryID(int subCategoryID) {
		this.subCategoryID = subCategoryID;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getBranchid() {
		return branchid;
	}

	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}

	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public ArrayList<Stock> getBranchStockList() {
		return branchStockList;
	}

	public void setBranchStockList(ArrayList<Stock> branchStockList) {
		this.branchStockList = branchStockList;
	}

	public int getN_stock() {
		return n_stock;
	}

	public void setN_stock(int n_stock) {
		this.n_stock = n_stock;
	}

	public int getS_stock() {
		return s_stock;
	}

	public void setS_stock(int s_stock) {
		this.s_stock = s_stock;
	}

	public int getB_stock() {
		return b_stock;
	}

	public void setB_stock(int b_stock) {
		this.b_stock = b_stock;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}