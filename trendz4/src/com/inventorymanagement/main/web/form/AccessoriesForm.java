package com.inventorymanagement.main.web.form;

import java.io.File;
import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Accessories;

public class AccessoriesForm {
	
	private int id;
	
	private String productName;
	
	private Double price;
	
	private String discription;
	
	private File userAccImage;
	
	private String userAccImageContentType;
	
	private String userAccImageFileName;
	
	private String uploadedimage;
	
	private String pagerecords;
	
	private int totalRecords;
	
	private String searchText = "";
	
	private int selectedid;
	
	private ArrayList<Accessories>accessoriesList;

	public ArrayList<Accessories> getAccessoriesList() {
		return accessoriesList;
	}

	public void setAccessoriesList(ArrayList<Accessories> accessoriesList) {
		this.accessoriesList = accessoriesList;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}



	public File getUserAccImage() {
		return userAccImage;
	}

	public void setUserAccImage(File userAccImage) {
		this.userAccImage = userAccImage;
	}

	public String getUserAccImageContentType() {
		return userAccImageContentType;
	}

	public void setUserAccImageContentType(String userAccImageContentType) {
		this.userAccImageContentType = userAccImageContentType;
	}

	public String getUserAccImageFileName() {
		return userAccImageFileName;
	}

	public void setUserAccImageFileName(String userAccImageFileName) {
		this.userAccImageFileName = userAccImageFileName;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUploadedimage() {
		return uploadedimage;
	}

	public void setUploadedimage(String uploadedimage) {
		this.uploadedimage = uploadedimage;
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

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getSelectedid() {
		return selectedid;
	}

	public void setSelectedid(int selectedid) {
		this.selectedid = selectedid;
	}

}
