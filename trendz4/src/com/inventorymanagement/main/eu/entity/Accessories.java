package com.inventorymanagement.main.eu.entity;

public class Accessories {
	
	private int id;
	
	private String productName;
	
	private int quantity;
	
	private double price;
	
	private String uploadedimage;
	
	private String discription;

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUploadedimage() {
		return uploadedimage;
	}

	public void setUploadedimage(String uploadedimage) {
		this.uploadedimage = uploadedimage;
	}

}
