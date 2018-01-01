package com.inventorymanagement.main.eu.bi;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Color;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.Size;

public interface PurchaseDAO {

	Purchase getProductDetails(int categoryID, int subCategoryID);

	int save(Purchase purchase, String userId);

	int updateSale(Purchase purchase, int subCategoryID,String userid);

	ArrayList<Purchase> getPurchaseList(String userId);

	boolean checkProductExist(int categoryID, int subCategoryID, String userId,String colorName);

	int deleteSelectedItem(String item);

	int deletePurchase(String userid);

	Product getProdutctDetails(int categoryid, int productId, String userId);

	int updatePurchase(int currentQuantity, String currentSize,int productID);

	int saveCompany(Purchase purchase,String userID);

	int savePurchaseInvoice(Purchase purchase, String userId,int companyID);

	double getPrice(int categoryID, int subCategoryID);

	int saveSize(Purchase purchase, String string, String actualSize);

	ArrayList<Size>  getProductSize(int subCategoryID, String userId,String colorName);

	int deleteProductSize(Size size);

	int saveTempSize(Size size2);

	Purchase getProductByName(String articleName);

	ArrayList<Category> getCategoryList(String userId);

	ArrayList<Product> getProductList(int categoryID);

	boolean checkBarcodeProductExist(int productId,String barcodeColor);

	int saveB_size(int productId, String barcodesize,int sizeID,String bacodeColor,int loginid);

	String getBarcodeSizeByProuct(int productId);

	int updateBsize(int productId, String barcodeSizeByProduct,String barcodeColor);

	int deleBSize(int loginid);

	ArrayList<Size> getSwapSize();

	int deleteTempSize(String sizeID);

	ArrayList<Size> getBsizeList(int productId,String barcodeColor);

	boolean isProductExistByName(String articleName,String size,String barcodecolor);

	ArrayList<Color> getColorList();

	String getActualSize(String viewsize, int gender);

	String getBarcodeColorSizeByProduct(int subCategoryID);

	ArrayList<Color> getP_sizeColorList(int subCategoryID);

	ArrayList<Size> getProductSize(int subCategoryID, String userId);

	String getProductName(int productId);
	
	String getProductName(int productID,int accessories);

	boolean checkProductForTempsize(int productId);

	ArrayList<Size> getProductIDList(int subCategoryID);

	boolean checkTempProductID(int id);

	int savePurchasePayment(int companyId, String howpaid,
			String paymentrecieved, String paymentnote);

	String getPsizeId(int productId, String colorSize, String string,int count);

	Purchase getPsizeData(String selectedid);

	int getViewSize(String prodid,String color);

	int getBarcodeQuantity(int subCategoryID, String string,String fromdate,String todate);

	String getBarcodePSize(int subCategoryID, String string,String fromdate,String todate);

	ArrayList<Color> getBarcodeColorList(int subCategoryID);

	String getBarcodeProductId(String globelpsizeid);

	ArrayList<Size> getBarcodeProductSize(int productId, String userId,
			String globelpsizeid);

	boolean checkBarcodeProductExist(String globelpsizeid);

	boolean checkProductEntry(int subCategoryID);

	ArrayList<Size> getBarcodeProductSize(int subCategoryID, String userId,
			String fromdate, String todate);

	int getShopStock(String productid,int loginid);

	int updateShopStock(int currenShoptQuantity, int id, int productId);

	ArrayList<Product> getshopStockList(int id);

	String getPcode(int subCategoryID,String fdate,String tdate,String size);

	ArrayList<Purchase> getBarcodeProdLength(int subCategoryID, String fromdate, String todate);

	double getproductgst(int subCategoryID);

	double getpurchseamount(String subcategoryid);

	

	

	
	

	

}
