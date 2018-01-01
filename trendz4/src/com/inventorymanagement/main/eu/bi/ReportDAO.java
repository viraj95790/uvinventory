package com.inventorymanagement.main.eu.bi;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Product;

public interface ReportDAO {

	ArrayList<Product> getproductinfo(String fromdate, String todate, String productName);

	ArrayList<Product> getproductlist();

	ArrayList<Product> getprofit(String productName);

}
