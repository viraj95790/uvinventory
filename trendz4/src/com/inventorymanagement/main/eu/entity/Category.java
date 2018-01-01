package com.inventorymanagement.main.eu.entity;

import java.util.ArrayList;

public class Category {
	
	private int id;
	
	private String categoryName;
	
	private String description;
	
	private String lastupdated;
	
	private ArrayList<Product>productList;
	
	private ArrayList<Branch>branchList;
	
	private ArrayList<Stock>branchStockList;
	
	private ArrayList<RemainStock>navneetList;
	
	private ArrayList<RemainStock>sapnaList;
	
	private ArrayList<RemainStock>bcmList;
	
	private ArrayList<StockProduct>stockList;
	
	private String searchText = "";
	
	private String productName;
	
	private int gender;
	
	private int quantity;
	
	private String categorytype;
	
	
	
	
	

	public String getCategorytype() {
		return categorytype;
	}

	public void setCategorytype(String categorytype) {
		this.categorytype = categorytype;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public ArrayList<RemainStock> getNavneetList() {
		return navneetList;
	}

	public void setNavneetList(ArrayList<RemainStock> navneetList) {
		this.navneetList = navneetList;
	}

	public ArrayList<RemainStock> getSapnaList() {
		return sapnaList;
	}

	public void setSapnaList(ArrayList<RemainStock> sapnaList) {
		this.sapnaList = sapnaList;
	}

	public ArrayList<RemainStock> getBcmList() {
		return bcmList;
	}

	public void setBcmList(ArrayList<RemainStock> bcmList) {
		this.bcmList = bcmList;
	}

	public ArrayList<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(ArrayList<Branch> branchList) {
		this.branchList = branchList;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	public Category(){
		
	}
	
	public Category(int id,String categoryName){
		this.id = id;
		this.categoryName = categoryName;
	}

	public String getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Stock> getBranchStockList() {
		return branchStockList;
	}

	public void setBranchStockList(ArrayList<Stock> branchStockList) {
		this.branchStockList = branchStockList;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ArrayList<StockProduct> getStockList() {
		return stockList;
	}

	public void setStockList(ArrayList<StockProduct> stockList) {
		this.stockList = stockList;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	


}
