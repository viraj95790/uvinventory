package com.inventorymanagement.main.eu.bi;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.Stock;

public interface BranchDAO {

	int saveBranch(Branch branch);

	ArrayList<Branch> getBranch();

	Branch getBranch(int selectedid);

	int updateBranch(Branch branch, String userID);

	int deleteBranch(int selectedid);

	boolean isUserExist(String userid);
	Branch getuser(String userID)throws Exception;

	boolean checkProductExist(Product product, String userId);

	int updateExistBranchProduct(Product product)throws Exception;

	ArrayList<Product> getProductList(int categoryID)throws Exception;

	int delieveredStatus(int categoryID, int subcategoryid, Stock stock)throws Exception;

	ArrayList<Stock> getquantityList(int categoryID, int subcategoryid) throws Exception;

	int updateDeliveredStatus(int categoryID, int subcategoryID)throws Exception;

	boolean isEmployeeExist(String userid);

	Branch editdata(String selectedid);

	int updatedata(Branch branch);

	int deletedata(String selectedid);

	Branch getSelectedBranchData(String userid);

	String getdate();

	ArrayList<Branch> getUserList();


	

}
