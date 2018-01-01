package com.inventorymanagement.main.eu.bi;

import java.util.ArrayList;

import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.eu.entity.AdminSubcategory;
import com.inventorymanagement.main.eu.entity.Category;

public interface AdminSubcategoryDAO {

	int getSubCategoryCount(String searchText,String userid);

	ArrayList<AdminSubcategory> getSubCategoryList(Pagination pagination,String searchText,String userid);

	AdminSubcategory getSubcategory(int selectedid);

	int updateAdminSubcategory(AdminSubcategory adminSubcategory, int selectedid);

	int deleteProduct(int selectedid);

	int deleteSubCategory(int selectedid);

	double getAdminProductPrice(int categoryID, int subCategoryID)throws Exception;

	int getAdminProductStock(int categoryID, int subCategoryID);

	boolean checkQuantityExist(int categoryID, int subCategoryID);

}
