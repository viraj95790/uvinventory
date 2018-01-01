package com.inventorymanagement.main.web.form;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Product;

public class AdminBillForm {
	
	private int id;
	
	private String productName;
	
	private String description;
	
	private int categoryID;
	
	private String categoryName;
	
	private ArrayList<Category>categoryList;
	
	private int quantity;
	
	private int remainingStock;
	
	private String pagerecords;
	
	private int totalRecords;
	
	private ArrayList<Product>productList;
	
	private ArrayList<Branch> branchList;
	
	private int branchID;
	
	private String branchName;
	
	private double price;
	
	private double totalAmount;
	
	private ArrayList<Category>categoryWiseProductList;
	
	private String actionType = "";

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
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

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getRemainingStock() {
		return remainingStock;
	}

	public void setRemainingStock(int remainingStock) {
		this.remainingStock = remainingStock;
	}

	public String getPagerecords() {
		return pagerecords;
	}

	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	public ArrayList<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(ArrayList<Branch> branchList) {
		this.branchList = branchList;
	}

	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<Category> getCategoryWiseProductList() {
		return categoryWiseProductList;
	}

	public void setCategoryWiseProductList(
			ArrayList<Category> categoryWiseProductList) {
		this.categoryWiseProductList = categoryWiseProductList;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
