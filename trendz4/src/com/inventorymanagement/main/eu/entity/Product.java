package com.inventorymanagement.main.eu.entity;

import java.util.ArrayList;

public class Product {

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
	
	private double price;
	
	private double totalAmount;
	
	ArrayList<Stock>branchStockList;
	
	private int n_stock;
	
	private int s_stock;
	
	private int b_stock;
	
	private int n_quantity;
	
	private int s_quantity;
	
	private int b_quantity;
	
	private int total;
	
	private int productionStock;
	
	private String status;
	
	private int deliveredStatus;
	
	private String modelNumber;
	
	private String articleNumber;
	
	private String size;
	
	private String color;
	
	private String imageName;
	
	private String uploadedimage;
	
	private int gender;
	
	private String barcodedate;
	
	private long days;
	
	private String profit;
	
	private String loss;
	
	private String saleprice;
	
    private String profitmargin;
	
	private String cgst;
	
	private String sgst;
	
	private boolean iegst;
	
	
	
	
	
	

	public boolean isIegst() {
		return iegst;
	}

	public void setIegst(boolean iegst) {
		this.iegst = iegst;
	}

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

	

	public String getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(String saleprice) {
		this.saleprice = saleprice;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getLoss() {
		return loss;
	}

	public void setLoss(String loss) {
		this.loss = loss;
	}

	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getBarcodedate() {
		return barcodedate;
	}

	public void setBarcodedate(String barcodedate) {
		this.barcodedate = barcodedate;
	}

	public String getUploadedimage() {
		return uploadedimage;
	}

	public void setUploadedimage(String uploadedimage) {
		this.uploadedimage = uploadedimage;
	}

	public int getDeliveredStatus() {
		return deliveredStatus;
	}

	public void setDeliveredStatus(int deliveredStatus) {
		this.deliveredStatus = deliveredStatus;
	}

	public int getProductionStock() {
		return productionStock;
	}

	public void setProductionStock(int productionStock) {
		this.productionStock = productionStock;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public ArrayList<Stock> getBranchStockList() {
		return branchStockList;
	}

	public void setBranchStockList(ArrayList<Stock> branchStockList) {
		this.branchStockList = branchStockList;
	}

	public Product(){
		
	}
	
	public Product(int id,String productName){
		this.id = id;
		this.productName = productName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

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

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
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

	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getN_quantity() {
		return n_quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setN_quantity(int n_quantity) {
		this.n_quantity = n_quantity;
	}

	public int getS_quantity() {
		return s_quantity;
	}

	public void setS_quantity(int s_quantity) {
		this.s_quantity = s_quantity;
	}

	public int getB_quantity() {
		return b_quantity;
	}

	public void setB_quantity(int b_quantity) {
		this.b_quantity = b_quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	

	

}
