package com.inventorymanagement.main.eu.entity;

public class Size {

	private int id;
	
	private String productSize;
	
	private int productID;
	
	private String sizeID = "";
	
	private String colorName;
	
	private String actualsize;
	
	private String colorSize;
	
	private String sizeValue;
	

	
	

	public String getSizeValue() {
		return sizeValue;
	}

	public void setSizeValue(String sizeValue) {
		this.sizeValue = sizeValue;
	}

	public String getActualsize() {
		return actualsize;
	}

	public void setActualsize(String actualsize) {
		this.actualsize = actualsize;
	}

	public String getColorSize() {
		return colorSize;
	}

	public void setColorSize(String colorSize) {
		this.colorSize = colorSize;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getSizeID() {
		return sizeID;
	}

	public void setSizeID(String sizeID) {
		this.sizeID = sizeID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	
	
}
