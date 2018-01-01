package com.inventorymanagement.main.eu.bi;

import java.util.ArrayList;

import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.eu.entity.Accessories;
import com.inventorymanagement.main.eu.entity.Purchase;

public interface AccessoriesDAO {

	int saveAccessories(Accessories accessories,String userID);

	int getAccessoriesCount(String searchText, String userId);

	ArrayList<Accessories> getAccessoriesList(Pagination pagination,
			String searchText, String userId);

	Accessories getAccessories(int selectedid);

	int updateAccessories(Accessories accessories, int selectedid);

	String getUploadedImage(int selectedid);

	int deleteAccessories(int selectedid);

	boolean checkAccessoriesExist(String productName);

	ArrayList<Accessories> getAccessoriesList(String userId);

	boolean checkAccessoriesExist(int accessoriesID, int isAccessories);

	int savePurchase(Purchase purchase,String userID);

	int updatePurchase(Purchase purchase,String userID);

	int updateAccessoriesStock(int accessoriesStock, int productId);

	int updatePurchaseTotal(int id, double discountTotal);

	

}
