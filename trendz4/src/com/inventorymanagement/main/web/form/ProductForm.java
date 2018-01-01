package com.inventorymanagement.main.web.form;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Color;
import com.inventorymanagement.main.eu.entity.Customer;
import com.inventorymanagement.main.eu.entity.Invoice;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.Sale;
import com.inventorymanagement.main.eu.entity.SalesMan;
import com.inventorymanagement.main.eu.entity.Size;
import com.inventorymanagement.main.eu.entity.Stock;

public class ProductForm {
	
	private int id;
	
	private File userImage;
	
	private String userImageContentType;
	
	private String userImageFileName;
	
	private String uploadedimage;
	
	private String productName;
	
	private String description;
	
	private int categoryID;
	
	private String categoryName;
	
	private ArrayList<Category>categoryList;
	
	private int quantity;
	
	private int remainingStock;
	
	private String pagerecords;
	
	private int totalRecords;
	
	private ArrayList<Product>productList;
	
	private ArrayList<Branch> branchList;
	
	private int branchID;
	
	private Double price;
	
	private String branchName;
	
	private ArrayList<Category>categoryWiseProductList;
	
	private ArrayList<Product>subcategoryList;
	
	private int subCategoryID;
	
	private ArrayList<Category>branchWiseStockList;
	
	private ArrayList<Stock>branchStockList;
	
	private ArrayList<Product>navneetList;
	
	private ArrayList<Product>sapnaList;
	
	ArrayList<Purchase>saleList;
	
	private String searchText = "";
	
	private String customerName;
	
	private String mobileNo;
	
	private double payAmount;
	
	private int vat;
	
	private int rupee;
	
	private int invoiceNo;
	
	private String currentDate;
	
	private String modelNumber;
	
	private String articleNumber = "";
	
	private String size;
	
	private String color;
	
	private ArrayList<SalesMan>populatedSalesManList;
	
	private int employeeid;
	
	private String name;
	
	private int billType;
	
	private int gender;
	
	private int colorId;
	
	private String colorName;
	
	private ArrayList<Color>colorList;
	
	private ArrayList<Size>sizeList;
	
	private int selectedID;
	
	private String sizeValue;
	
	private ArrayList<Purchase>invoiceList;
	
	private String selectedItem;

	private String paymentrecieved;
	
	private String howpaid;
	
	private String paymentnote;

	private String invoicetotal;
	
	private String balance;
	
	private String discount;
	
	private String gtotal;
	
	ArrayList<Customer>customerList;
	
	private String customerid;
	private String shopmob;
	
	
	private Collection<Product>rowProductList;
	private String toDate = "";
	
	private String fromDate = "";
	
	private ArrayList<Branch>userList;
	
	private String userId;
	
	private ArrayList<Category> mainCategory;
	
    private String profitmargin;
	
	private String cgst;
	
	private String sgst;
	
	private String totalcgst;
	
	private String totalsgst;
	
	private Double saleprice;
	
	private boolean iegst ;
	
	private String dtype;
	
	
	
	
	
	
	
	
	
	
	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public boolean isIegst() {
		return iegst;
	}

	public void setIegst(boolean iegst) {
		this.iegst = iegst;
	}

	public String getTotalcgst() {
		return totalcgst;
	}

	public void setTotalcgst(String totalcgst) {
		this.totalcgst = totalcgst;
	}

	public String getTotalsgst() {
		return totalsgst;
	}

	public void setTotalsgst(String totalsgst) {
		this.totalsgst = totalsgst;
	}

	public ArrayList<Category> getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(ArrayList<Category> mainCategory) {
		this.mainCategory = mainCategory;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ArrayList<Branch> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<Branch> userList) {
		this.userList = userList;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public Collection<Product> getRowProductList() {
		return rowProductList;
	}

	public void setRowProductList(Collection<Product> rowProductList) {
		this.rowProductList = rowProductList;
	}

	public String getShopmob() {
		return shopmob;
	}

	public void setShopmob(String shopmob) {
		this.shopmob = shopmob;
	}

	/** Email Id of user */
	private String email;
	
	/** user's address */
	private String address;
           
	/** Country of user */
	private String country;
	
	/** State of user (selected from drop down) */
	private String state;
	
	/** State of user (typed in text box) */
	private String stateText;
	
	/** City of user (selected from drop down) */
	private String city;
	
	/** City of user (typed in text box) */
	private String cityText;
	
	/** Landline number of user */
	private String landLine;
	
	private String pincode;
	
	private String companyName;
	
	
	
	
	
	

	public Double getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(Double saleprice) {
		this.saleprice = saleprice;
	}

	public String getProfitmargin() {
		return profitmargin;
	}

	public void setProfitmargin(String profitmargin) {
		this.profitmargin = profitmargin;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getSgst() {
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateText() {
		return stateText;
	}

	public void setStateText(String stateText) {
		this.stateText = stateText;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityText() {
		return cityText;
	}

	public void setCityText(String cityText) {
		this.cityText = cityText;
	}

	public String getLandLine() {
		return landLine;
	}

	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getGtotal() {
		return gtotal;
	}

	public void setGtotal(String gtotal) {
		this.gtotal = gtotal;
	}

	public String getInvoicetotal() {
		return invoicetotal;
	}

	public void setInvoicetotal(String invoicetotal) {
		this.invoicetotal = invoicetotal;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getPaymentnote() {
		return paymentnote;
	}

	public void setPaymentnote(String paymentnote) {
		this.paymentnote = paymentnote;
	}

	public String getPaymentrecieved() {
		return paymentrecieved;
	}

	public void setPaymentrecieved(String paymentrecieved) {
		this.paymentrecieved = paymentrecieved;
	}

	public String getHowpaid() {
		return howpaid;
	}

	public void setHowpaid(String howpaid) {
		this.howpaid = howpaid;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	

	public String getSizeValue() {
		return sizeValue;
	}

	public void setSizeValue(String sizeValue) {
		this.sizeValue = sizeValue;
	}

	public int getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(int selectedID) {
		this.selectedID = selectedID;
	}

	public ArrayList<Size> getSizeList() {
		return sizeList;
	}

	public void setSizeList(ArrayList<Size> sizeList) {
		this.sizeList = sizeList;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public ArrayList<Stock> getBranchStockList() {
		return branchStockList;
	}

	public void setBranchStockList(ArrayList<Stock> branchStockList) {
		this.branchStockList = branchStockList;
	}

	public ArrayList<Product> getNavneetList() {
		return navneetList;
	}

	public void setNavneetList(ArrayList<Product> navneetList) {
		this.navneetList = navneetList;
	}

	public ArrayList<Product> getSapnaList() {
		return sapnaList;
	}

	public void setSapnaList(ArrayList<Product> sapnaList) {
		this.sapnaList = sapnaList;
	}

	public ArrayList<Category> getBranchWiseStockList() {
		return branchWiseStockList;
	}

	public void setBranchWiseStockList(ArrayList<Category> branchWiseStockList) {
		this.branchWiseStockList = branchWiseStockList;
	}

	public int getSubCategoryID() {
		return subCategoryID;
	}

	public void setSubCategoryID(int subCategoryID) {
		this.subCategoryID = subCategoryID;
	}

	public ArrayList<Product> getSubcategoryList() {
		return subcategoryList;
	}

	public void setSubcategoryList(ArrayList<Product> subcategoryList) {
		this.subcategoryList = subcategoryList;
	}

	public ArrayList<Category> getCategoryWiseProductList() {
		return categoryWiseProductList;
	}

	public void setCategoryWiseProductList(
			ArrayList<Category> categoryWiseProductList) {
		this.categoryWiseProductList = categoryWiseProductList;
	}

	public ArrayList<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(ArrayList<Branch> branchList) {
		this.branchList = branchList;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getRemainingStock() {
		return remainingStock;
	}

	public void setRemainingStock(int remainingStock) {
		this.remainingStock = remainingStock;
	}

	public String getPagerecords() {
		return pagerecords;
	}

	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getVat() {
		return vat;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	

	public ArrayList<Purchase> getSaleList() {
		return saleList;
	}

	public void setSaleList(ArrayList<Purchase> saleList) {
		this.saleList = saleList;
	}

	public double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	public String getUploadedimage() {
		return uploadedimage;
	}

	public void setUploadedimage(String uploadedimage) {
		this.uploadedimage = uploadedimage;
	}

	public ArrayList<SalesMan> getPopulatedSalesManList() {
		return populatedSalesManList;
	}

	public void setPopulatedSalesManList(ArrayList<SalesMan> populatedSalesManList) {
		this.populatedSalesManList = populatedSalesManList;
	}

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBillType() {
		return billType;
	}

	public void setBillType(int billType) {
		this.billType = billType;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public ArrayList<Purchase> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(ArrayList<Purchase> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public ArrayList<Color> getColorList() {
		return colorList;
	}

	public void setColorList(ArrayList<Color> colorList) {
		this.colorList = colorList;
	}

	public int getRupee() {
		return rupee;
	}

	public void setRupee(int rupee) {
		this.rupee = rupee;
	}

	
	

	


}
