package com.inventorymanagement.main.web.form;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.AdminSubcategory;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.Stock;

public class AdminSubcategoryForm {
	
	private int id;
	
	private String productName;
	
	private String description;
	
	private int categoryID;
	
	private String categoryName;
	
	private ArrayList<Category>categoryList;
	
	private ArrayList<AdminSubcategory>subCategoryList;
	
	private int quantity;
	
	private int remainingStock;
	
	private String pagerecords;
	
	private int totalRecords;
	
	private ArrayList<Product>productList;
	
	private ArrayList<Branch> branchList;
	
	private int branchID;
	
	private Double price;
	
	private String branchName;
	
	private ArrayList<Category>categoryWiseProductList;
	
	private ArrayList<Product>subcategoryList;
	
	private int subCategoryID;
	
	private ArrayList<Category>branchWiseStockList;
	
	private ArrayList<Stock>branchStockList;
	
	private ArrayList<Product>navneetList;
	
	private ArrayList<Product>sapnaList;
	
	private String searchText = "";
	
	private int gender;
	
	private String profitmargin;
	
	private String cgst;
	
	private String sgst;
	
	
	

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

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
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

	

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public ArrayList<Category> getCategoryWiseProductList() {
		return categoryWiseProductList;
	}

	public void setCategoryWiseProductList(
			ArrayList<Category> categoryWiseProductList) {
		this.categoryWiseProductList = categoryWiseProductList;
	}

	public ArrayList<Product> getSubcategoryList() {
		return subcategoryList;
	}

	public void setSubcategoryList(ArrayList<Product> subcategoryList) {
		this.subcategoryList = subcategoryList;
	}

	public int getSubCategoryID() {
		return subCategoryID;
	}

	public void setSubCategoryID(int subCategoryID) {
		this.subCategoryID = subCategoryID;
	}

	public ArrayList<Category> getBranchWiseStockList() {
		return branchWiseStockList;
	}

	public void setBranchWiseStockList(ArrayList<Category> branchWiseStockList) {
		this.branchWiseStockList = branchWiseStockList;
	}

	public ArrayList<Stock> getBranchStockList() {
		return branchStockList;
	}

	public void setBranchStockList(ArrayList<Stock> branchStockList) {
		this.branchStockList = branchStockList;
	}

	public ArrayList<Product> getNavneetList() {
		return navneetList;
	}

	public void setNavneetList(ArrayList<Product> navneetList) {
		this.navneetList = navneetList;
	}

	public ArrayList<Product> getSapnaList() {
		return sapnaList;
	}

	public void setSapnaList(ArrayList<Product> sapnaList) {
		this.sapnaList = sapnaList;
	}

	public ArrayList<AdminSubcategory> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(ArrayList<AdminSubcategory> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}
	
}