package com.inventorymanagement.main.eu.bi;

import java.util.ArrayList;

import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.eu.entity.CustomCake;

public interface CustomCakeDAO {

	int saveCustomCake(CustomCake customCake,String userID)throws Exception;

	int getCustomCakeCount(String userId)throws Exception;

	ArrayList<CustomCake> getCustomCakeList(String userId,Pagination pagination)throws Exception;

	CustomCake getCustomCake(int selectedid)throws Exception;

	int updateCustomCake(CustomCake customCake, int selectedID)throws Exception;

	int deleteCustomCake(int selectedid)throws Exception;

	int getAdminCustomCakeCount(int branchID) throws Exception;

	String getUserID(int branchID)throws Exception;

	int updateStatus(int selectedID, int i)throws Exception;

}
