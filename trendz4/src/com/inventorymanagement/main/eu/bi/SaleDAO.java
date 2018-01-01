package com.inventorymanagement.main.eu.bi;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Color;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.Sale;
import com.inventorymanagement.main.eu.entity.Size;

public interface SaleDAO {

	int save(Sale sale);

	boolean checkProductExist(int categoryID, int subCategoryID);

	int getQuantity(int subCategoryID);

	int updateSale(Sale sale,int productID);

	ArrayList<Sale> getSaleList();

	int deleteSale();

	int deleteSelectedItem(char c);

	int saveCustomer(Purchase purchase,String userid,int employeeid,int billType,String clientid);

	int updateStock(int currentStock, Sale sale);

	int saveInvoice(Sale sale2,int customerID);

	int deleteTempSize();

	ArrayList<Size> getTempProductSize(int subCategoryID,String barcodeColor);

	int saveTempSize(String id, String size, int subCategoryID,String colorName);

	int deletePsizeByProductID(int productId);

	int saveSalenvoice(Purchase purchase, String userId, int companyId);

	Size getLastRecordBsize(int productID);

	int saveLastRecordtempSize(Size size);

	int deleteLastRecordBsize(int productID);

	String getProductColorSize(String productidseries);

	ArrayList<Color> getTempColorList(int subCategoryID);

	ArrayList<Size> getTempSizeListByColor(int subCategoryID, String colorName);

	int getTotalQuantityP_size(int subCategoryID);

	int getTempTotalQuantity(int subCategoryID);

	int getPurchaseQuantity(int productId,String userid);

	ArrayList<Color> getTemp_sizeColorList(int subCategoryID);

	ArrayList<Size> getTempProductSizeList(int productId, String userId,
			String colorName);

	int getTempQuantity(int productId);

	Size getPurchaseSize(int subcategoryID, String selectedColor);

	int deletePurshase(int subCategoryID, String colorSize);

	int saveS_size(String colorSize, int subCategoryID, String sizeValue,
			String sizeID,int loginid);

	boolean chechs_size(String sizeid,String color);

	ArrayList<Size> getS_SizeList(int subCategoryID, String colorSize,int loginid);

	int delteS_Size(int loginid);

	int deleteLastRecordSsize(String sizeid);

	boolean chechs_sizeProductID(int id);

	int saveSalePayment(int companyId, String howpaid, String paymentrecieved,
			String paymentnote);

	ArrayList<Color> getBarcodeTempColorList(int subCategoryID,
			String fromdate, String todate);

	

	

	

	

}
