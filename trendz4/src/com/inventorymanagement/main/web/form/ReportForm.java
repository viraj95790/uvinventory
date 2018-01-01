package com.inventorymanagement.main.web.form;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Product;

public class ReportForm {
	
	private int id;
	private String productName = "";
	private int quantity;
	private String lastUpdated;
	private String purchasedate;
	private String currentdate;
	private String fromdate = "";
	private String todate = "";
	private String profit;
	private String loss;
	
	
	private ArrayList<Product> stockInfoList;
	private ArrayList<Product> productList;
	private ArrayList<Product> profitlossList;
	


	
	
	
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


	public ArrayList<Product> getProfitlossList() {
		return profitlossList;
	}


	public void setProfitlossList(ArrayList<Product> profitlossList) {
		this.profitlossList = profitlossList;
	}


	public ArrayList<Product> getProductList() {
		return productList;
	}


	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}


	public String getFromdate() {
		return fromdate;
	}


	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}


	public String getTodate() {
		return todate;
	}


	public void setTodate(String todate) {
		this.todate = todate;
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


	public String getLastUpdated() {
		return lastUpdated;
	}


	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}


	public String getPurchasedate() {
		return purchasedate;
	}


	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}


	public String getCurrentdate() {
		return currentdate;
	}


	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}


	public ArrayList<Product> getStockInfoList() {
		return stockInfoList;
	}


	public void setStockInfoList(ArrayList<Product> stockInfoList) {
		this.stockInfoList = stockInfoList;
	}
	
	
	

}
