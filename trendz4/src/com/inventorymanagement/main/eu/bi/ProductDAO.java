package com.inventorymanagement.main.eu.bi;

import java.util.ArrayList;
import java.util.HashMap;

import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.RemainStock;
import com.inventorymanagement.main.eu.entity.Size;
import com.inventorymanagement.main.eu.entity.Stock;
import com.inventorymanagement.main.eu.entity.StockProduct;

public interface ProductDAO {

	ArrayList<Category> getCategoryList(String userId)throws Exception;

	int saveProduct(Product product)throws Exception;

	//int getProductCount(String userId)throws Exception;

	//ArrayList<Product> getProductList(String userId, Pagination pagination)throws Exception;

	int updateStock(String stockValue, String selectedID)throws Exception;

	int getRemainStock(String selectedID)throws Exception;
	
	int updateCategory(Product product, int selectedid);

	int deleteProduct(int selectedid);

	Product getProduct(int selectedid);

	int getProductCount(String searchText,String userid,String articleNumber)throws Exception;

	ArrayList<Product> getProductList(Pagination pagination,String searchText,String userid,String articleNumber)throws Exception;

	int getProductCountByBranch(String selectedBranch,String searchText)throws Exception;

	ArrayList<Product> getProductListByBranch(String selectedBranch,
			Pagination pagination,String searchText)throws Exception;

	ArrayList<Product> getProductList(String userId, int categoryID)throws Exception;

	ArrayList<Category> getAdminCategoryList()throws Exception;

	ArrayList<RemainStock> getAdminProductList(int categoryID,int branchid)throws Exception;

	ArrayList<Product> getSubCategoryList()throws Exception;

	int saveSubCategory(Product product)throws Exception;

	ArrayList<Product> getAjaxProductList(int categoryID)throws Exception;

	ArrayList<Stock> getBranchStockList(int id,
			int subCategoryID,int branchid);

	ArrayList<Product> getBranchWiseProductList(int branchid, int categoryid)throws Exception;

	ArrayList<StockProduct> getProductListByCategory(int id)throws Exception;

	boolean isProductExist(String productName, int selectedCategoryID);

	int updateQuantity(String selectedID, String quantity) throws Exception;

	String getUploadedImage(int selectedid);

	ArrayList<Size> getSizeList(int productid);

	Size getP_Size(int selectedid);

	int getGender(int parseInt);

	int getActualSize(int gender, int sizeValue);

	int updateP_size(Size size);

	int deleteProductSize(int selectedid);

	int updateCurrentQuantity(int selectedProductID,int currentQuantity);

	int getSubCategoryID(int selectedid);

	int deletePsize(int subcategoryID);

	int saveShopStock(String shopid, String qty,String prodid,String subcateoryid);

	boolean checkProductExist(String shopid, String prodid);

	int updateShopStock(String shopid, String prodid, String qty);

	int getShopQty(String shopid, String prodid);

	int getSumofShopQty(String prodid);

	int getAdminProductQty(String prodid);

	

	
	

	
}
