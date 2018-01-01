package com.inventorymanagement.main.web.form;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Category;

public class CategoryForm {
	
	private int id;
	
	private String categoryName;
	
	private String description;
	
	private int branchID;
	
	private String branchName;
	
	private ArrayList<Category>categoryList;
	
	private ArrayList<Branch> branchList;
	
	private String pagerecords;
	
	private int totalRecords;
	
	private String searchText = "";
	
	private int gender;
	
	private String categorytype;
	
	
	private ArrayList<Category> mainCategory;
	
	
	

	

	public String getCategorytype() {
		return categorytype;
	}

	public void setCategorytype(String categorytype) {
		this.categorytype = categorytype;
	}

	public ArrayList<Category> getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(ArrayList<Category> mainCategory) {
		this.mainCategory = mainCategory;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getPagerecords() {
		return pagerecords;
	}

	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}

	public ArrayList<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(ArrayList<Branch> branchList) {
		this.branchList = branchList;
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

	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	

	

}
