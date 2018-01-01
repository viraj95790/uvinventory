package com.inventorymanagement.main.eu.bi;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Product;

public interface AdminBillDAO {

	ArrayList<Category> getCategoryList();

	ArrayList<Product> getProductList(int categoryID, String branchname );

	String getBranchName(int branchId);

}
