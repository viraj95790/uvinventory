package com.inventorymanagement.main.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.main.eu.bi.PurchaseDAO;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Color;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.Size;

public class JDBCPurchaseDAO extends JDBCBaseDAO implements PurchaseDAO{
	
	public JDBCPurchaseDAO(Connection connection){
		this.connection = connection;
	}

	public Purchase getProductDetails(int categoryID, int subCategoryID) {
		PreparedStatement preparedStatement = null;
		Purchase purchase = new Purchase();
		String sql = "select modelnumber,articlenumber,size,color,quantity,imagename from subcategory where categoryid = "+categoryID+" and subcategoryname = "+subCategoryID+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				purchase.setModelNumber(rs.getString(1));
				purchase.setArticleNumber(rs.getString(2));
				purchase.setSize(rs.getString(3));
				purchase.setColor(rs.getString(4));
				purchase.setQuantity(rs.getInt(5));
				purchase.setUloadedImage(rs.getString(6));
				
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return purchase;
	}

	public int save(Purchase purchase, String userId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into purchase(productid,quantity,mrp,total,size,lastupdated,userid,categoryid,productidseries,color,actualsize,pcode,gst) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, purchase.getProductId());
			preparedStatement.setInt(2, purchase.getQuantity());
			preparedStatement.setDouble(3, purchase.getMrp());
			preparedStatement.setDouble(4, purchase.getTotal());
			preparedStatement.setString(5, purchase.getSize());
			preparedStatement.setTimestamp(6,DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setString(7, userId);
			preparedStatement.setInt(8, purchase.getCategoryid());
			preparedStatement.setString(9, purchase.getProductidseries());
			preparedStatement.setString(10, purchase.getColorSize());
			preparedStatement.setString(11, purchase.getActualSize());
			preparedStatement.setString(12, purchase.getPcode());
			preparedStatement.setDouble(13, purchase.getTotalgst());
			
			
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	
	public int updateSale(Purchase purchase, int subCategoryID,String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update purchase set quantity=?,mrp=?,total=?,size=?,lastupdated=?,productidseries=?,color=?,actualsize=? where categoryid=? and  productid=? and userid=? and color = ? and accessories=0";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, purchase.getQuantity());
			preparedStatement.setDouble(2, purchase.getMrp());
			preparedStatement.setDouble(3, purchase.getTotal());
			preparedStatement.setString(4, purchase.getSize());
			preparedStatement.setTimestamp(5, DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setString(6, purchase.getProductidseries());
			preparedStatement.setString(7, purchase.getColorSize());
			preparedStatement.setString(8, purchase.getActualSize());
			preparedStatement.setInt(9, purchase.getCategoryid());
			preparedStatement.setInt(10, subCategoryID);
			preparedStatement.setString(11, userid);
			preparedStatement.setString(12, purchase.getColorSize());
			
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Purchase> getPurchaseList(String userId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Purchase>list = new ArrayList<Purchase>();
		String sql = "select id,productid,quantity,mrp,total,size,categoryid,productidseries,color,actualsize,accessories,pcode,gst from purchase where userid = ? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
		
			while(rs.next()){
				Purchase purchase = new Purchase();
				purchase.setId(rs.getInt(1));
				purchase.setProductId(rs.getInt(2));
				purchase.setQuantity(rs.getInt(3));
				purchase.setMrp(rs.getDouble(4));
				purchase.setTotal(rs.getDouble(5));
				purchase.setSize(rs.getString(6));
				purchase.setCategoryid(rs.getInt(7));
				purchase.setProductidseries(rs.getString(8));
				purchase.setColorSize(rs.getString(9));
				purchase.setActualSize(rs.getString(10));
				purchase.setAccessories(rs.getInt(11));
				purchase.setProductName(getProductName(purchase.getProductId(),purchase.getAccessories()));
				purchase.setPcode(rs.getString(12));
				purchase.setTotalgst(rs.getDouble(13));
				
				list.add(purchase);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public String getProductName(int productID,int accessories) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "";
		if(accessories == 0){
			sql = "select productname from product where id = "+productID+" ";
		}else{
			sql = "select productname from accessories where id = "+productID+" ";
		}
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean checkProductExist(int categoryID, int subCategoryID,
		String userId,String colorName) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from purchase where categoryid = "+categoryID+" and productid = "+subCategoryID+" and userid = ? and color = ? and accessories = 0 ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, colorName);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public int deleteSelectedItem(String item) {
  		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from purchase where id in("+Integer.parseInt(item)+")";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deletePurchase(String userid) {
		PreparedStatement preparedStatement=null;
		int result = 0;
		String sql = "delete from purchase where userid='"+userid+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			
		}
		
		return result;
	}

	public Product getProdutctDetails(int categoryid, int productId,
			String userId) {
		PreparedStatement preparedStatement = null;
		Product product = new Product();
		String sql = "select quantity,size from subcategory where categoryid = "+categoryid+" and subcategoryname = "+productId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				product.setQuantity(rs.getInt(1));
				product.setSize(rs.getString(2));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	public int updatePurchase(int currentQuantity, String currentSize,int productID) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update subcategory set quantity=? where subcategoryname = ? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, currentQuantity);
			preparedStatement.setInt(2, productID);
			
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return result;
	}

	public int saveCompany(Purchase purchase,String userID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into company(name,mobileno,vat,lastupdated,userid) values(?,?,?,?,?)";
		int userid = 0;
		try{
			preparedStatement = connection.prepareStatement(sql,preparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, purchase.getCustomerName());
			preparedStatement.setString(2, purchase.getMobileNumber());
			preparedStatement.setInt(3, purchase.getVat());
			preparedStatement.setTimestamp(4,DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setString(5, userID);
			
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					userid = resultSet.getInt(1);  
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return userid;
	}

	public int savePurchaseInvoice(Purchase purchase, String userId,int companyID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into p_invoice(productid,companyid,quantity,mrp,total,size,categoryid,color,actualsize,accessories,pcode,gst) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, purchase.getProductId());
			preparedStatement.setInt(2, companyID);
			preparedStatement.setInt(3, purchase.getQuantity());
			preparedStatement.setDouble(4, purchase.getMrp());
			preparedStatement.setDouble(5, purchase.getTotal());
			preparedStatement.setString(6, purchase.getSize());
			preparedStatement.setInt(7, purchase.getCategoryid());
			preparedStatement.setString(8, purchase.getColorSize());
			preparedStatement.setString(9, purchase.getActualSize());
			preparedStatement.setInt(10, purchase.getAccessories());
			preparedStatement.setString(11, purchase.getPcode());
			preparedStatement.setDouble(12, purchase.getTotalgst());
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double getPrice(int categoryID, int subCategoryID) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select price from product where id = "+subCategoryID+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int saveSize(Purchase purchase, String size,String actualSize) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into p_size(productid,size,actualsize,color,lastmodified) values(?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, purchase.getProductId());
			preparedStatement.setString(2, size);
			preparedStatement.setString(3, actualSize);
			preparedStatement.setString(4, purchase.getColorSize());
			preparedStatement.setString(5, DateTimeUtils.getLastModifiedDate(new Date()));
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Size>  getProductSize(int subCategoryID, String userId,String colorName) {
		PreparedStatement preparedStatement = null;
		ArrayList<Size> list = new ArrayList<Size>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p_size.id, p_size.size, p_size.productid, p_size.color FROM p_size inner join  subcategory ");
		sql.append("on p_size.productid = subcategory.subcategoryname where subcategory.subcategoryname="+subCategoryID+"  and userid = '"+userId+"' and  p_size.color='"+colorName+"' order by size  ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			StringBuffer str = new StringBuffer();
			while(rs.next()){
				Size size = new Size();
				size.setId(rs.getInt(1));
				size.setProductSize(rs.getString(2));
				size.setProductID(rs.getInt(3));
				size.setColorName(rs.getString(4));
				size.setColorSize(rs.getString(4));
				list.add(size);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public ArrayList<Size>  getBarcodeProductSize(int subCategoryID, String userId,String psizeid) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<Size> list = new ArrayList<Size>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p_size.id, p_size.size, p_size.productid, p_size.color FROM p_size inner join  subcategory ");
		sql.append("on p_size.productid = subcategory.subcategoryname where subcategory.subcategoryname="+subCategoryID+" and p_size.id = "+psizeid+"  ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			StringBuffer str = new StringBuffer();
			while(rs.next()){
				Size size = new Size();
				size.setId(rs.getInt(1));
				size.setProductSize(rs.getString(2));
				size.setProductID(rs.getInt(3));
				size.setColorName(rs.getString(4));
				size.setColorSize(rs.getString(4));
				list.add(size);
			}
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public ArrayList<Size>  getProductSize(int subCategoryID, String userId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Size> list = new ArrayList<Size>();
		
		
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p_size.id, p_size.size, p_size.productid, p_size.color FROM p_size inner join  subcategory ");
		sql.append("on p_size.productid = subcategory.subcategoryname where subcategory.subcategoryname="+subCategoryID+"  ");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			StringBuffer str = new StringBuffer();
			while(rs.next()){
				Size size = new Size();
				size.setId(rs.getInt(1));
				size.setProductSize(rs.getString(2));
				size.setProductID(rs.getInt(3));
				size.setColorName(rs.getString(4));
				size.setColorSize(rs.getString(4));
				list.add(size);
			}
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public int deleteProductSize(Size size) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from temp_size where id = "+size.getId()+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int saveTempSize(Size size2) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into temp_size(id,productid,size,color,actualsize) values(?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, size2.getId());
			preparedStatement.setInt(2, size2.getProductID());
			preparedStatement.setString(3, size2.getProductSize());
			preparedStatement.setString(4, size2.getColorSize());
			preparedStatement.setString(5, size2.getActualsize());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Purchase getProductByName(String articleName) {
		PreparedStatement preparedStatement = null;
		Purchase purchase = new Purchase();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT category.id,product.id,categoryname,productname,gender FROM product ");
		sql.append("inner join category on category.id = product.categoryid ");
		sql.append("where  product.id = '"+articleName+"'; ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				purchase.setCategoryid(rs.getInt(1));
				purchase.setProductId(rs.getInt(2));
				purchase.setCategoryName(rs.getString(3));
				purchase.setProductName(rs.getString(4));
				purchase.setGender(rs.getInt(5));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return purchase;
	}

	public ArrayList<Category> getCategoryList(String userId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Category>list = new ArrayList<Category>();
		String sql = "select id,categoryname from category where userid=?";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Category category = new Category();
				category.setId(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				
				list.add(category);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Product> getProductList(int categoryID) {
		PreparedStatement preparedStatement = null;
		ArrayList<Product>list = new ArrayList<Product>();
		String sql = "select id,productname from product where categoryid = "+categoryID+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				list.add(product);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean checkBarcodeProductExist(int productId,String barcodeColor) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from b_size where productid = "+productId+" and color = '"+barcodeColor+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveB_size(int productId, String barcodesize,int sizeID,String barcodeColor,int loginid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into b_size(productid,size,sizeid,color,loginid) values(?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			preparedStatement.setString(2, barcodesize);
			preparedStatement.setInt(3, sizeID);
			preparedStatement.setString(4, barcodeColor);
			preparedStatement.setInt(5, loginid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public String getBarcodeSizeByProuct(int productId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select size from b_size where productid = "+productId+"  ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateBsize(int productId, String barcodeSizeByProduct,String barcodeColor) {
		PreparedStatement preparedStatement = null;
		int result=  0;
		String sql = "update b_size set size=?,color=? where productid = ? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, barcodeSizeByProduct);
			preparedStatement.setString(2, barcodeColor);
			preparedStatement.setInt(3, productId);
			
			
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleBSize(int loginid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from b_size where loginid="+loginid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Size> getSwapSize() {
		PreparedStatement preparedStatement = null;
		ArrayList<Size>list = new ArrayList<Size>();
		String sql = "select id,productid,size,color,actualsize from p_size ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Size size = new Size();
				size.setId(rs.getInt(1));
				size.setProductID(rs.getInt(2));
				size.setProductSize(rs.getString(3));
				size.setColorSize(rs.getString(4));
				size.setActualsize(rs.getString(5));
				
				list.add(size);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int deleteTempSize(String sizeID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from temp_size where id = "+sizeID+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Size> getBsizeList(int productId,String barcodeColor) {
		PreparedStatement preparedStatement = null;
		ArrayList<Size>list = new ArrayList<Size>();
		String sql = "select size,sizeid,color from b_size where productid = "+productId+" and color = '"+barcodeColor+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Size size = new Size();
				size.setProductSize(rs.getString(1));
				size.setSizeID(rs.getString(2));
				size.setColorSize(rs.getString(3));
				list.add(size);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public boolean isProductExistByName(String articleName,String size,String barcodecolor) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT category.id,product.id,categoryname,productname FROM product ");
		sql.append("inner join category on category.id = product.categoryid ");
		sql.append("where productname = '"+articleName+"'; ");
		
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				Purchase purchase = new Purchase();
				purchase.setCategoryid(rs.getInt(1));
				purchase.setProductId(rs.getInt(2));
				purchase.setCategoryName(rs.getString(3));
				purchase.setProductName(rs.getString(4));
				
				result = checkSizeExist(purchase.getProductId(),size,barcodecolor);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	private boolean checkSizeExist(int productId,String size,String barcodecolor) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from temp_size where productid=? and size=? and color=?";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			preparedStatement.setString(2, size);
			preparedStatement.setString(3, barcodecolor);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Color> getColorList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Color>list = new ArrayList<Color>();
		String sql = "select id,colorname from color";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Color color = new Color();
				color.setColorId(rs.getInt(1));
				color.setColorName(rs.getString(2));
				list.add(color);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getActualSize(String viewsize, int gender) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT actualsize FROM gents_ladies where commonsize in("+viewsize+") and gender = "+gender+" ";
		StringBuffer str = new StringBuffer();
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getBarcodeColorSizeByProduct(int subCategoryID) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select color from b_size where productid = "+subCategoryID+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Color> getP_sizeColorList(int subCategoryID) {
		PreparedStatement preparedStatement = null;
		ArrayList<Color>list = new ArrayList<Color>();
		String sql = "SELECT color FROM p_size where productid="+subCategoryID+" group by color";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Color color = new Color();
				color.setColorName(rs.getString(1));
				list.add(color);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean checkProductForTempsize(int productId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM temp_size where productid = "+productId+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Size> getProductIDList(int subCategoryID) {
		PreparedStatement preparedStatement = null;
		ArrayList<Size>list = new ArrayList<Size>();
		String sql = "select productidseries from purchase where productid = "+subCategoryID+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Size size = new Size();
				String temp[] = rs.getString(1).split(",");
				for(int i=0;i<temp.length;i++){
					size.setSizeID(temp[i]);
					list.add(size);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean checkTempProductID(int sizeID) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from temp_size where id = "+sizeID+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public String getProductName(int productId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "";
		
			sql = "select productname from product where id = "+productId+" ";
		
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int savePurchasePayment(int companyId, String howpaid,
			String paymentrecieved, String paymentnote) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into purchase_payment(companyid, paymode, payment, paynote, date) values(?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, companyId);
			preparedStatement.setString(2, howpaid);
			preparedStatement.setString(3, paymentrecieved);
			preparedStatement.setString(4, paymentnote);
			preparedStatement.setString(5, DateTimeUtils.getLastModifiedDate(new Date()));
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public String getPsizeId(int productId, String colorSize, String size,int count) {
		PreparedStatement preparedStatement = null;
		String result = "";
		
		/*if(count==0){
			count = 1;
		}*/
		
		count++;
		
		String sql = "SELECT id FROM p_size where productid = "+productId+" and color='"+colorSize+"' and size = '"+size+"' limit 0,"+count+" ";
		
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public Purchase getPsizeData(String selectedid) {
		PreparedStatement preparedStatement = null;
		Purchase purchase = new Purchase();
		String sql = "SELECT productid,size,color FROM p_size  where id = "+selectedid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				purchase.setProductId(rs.getInt(1));
				purchase.setSize(rs.getString(2));
				purchase.setColorSize(rs.getString(3));
				String productname = getProductName(purchase.getProductId(),0);
				purchase.setProductName(productname);
				String mrp = getProductMrp(purchase.getProductId());
				purchase.setMrp(Double.parseDouble(mrp));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return purchase;
	}

	private String getProductMrp(int productId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT price FROM product where id = "+productId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
					
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int getViewSize(String prodid,String color) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT size FROM p_size where productid = "+prodid+" and color in("+color+")  ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int getBarcodeQuantity(int subCategoryID, String color,String fromdate,String todate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		todate = todate + " 23:55:55";
		
		String sql = "SELECT count(id) FROM p_size where productid = "+subCategoryID+" and size='"+color+"' and lastmodified between '"+fromdate+"' and '"+todate+"'  ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public String getBarcodePSize(int subCategoryID, String color,String fromdate,String todate) {
		PreparedStatement preparedStatement = null;
		String result = "";
		
		todate = todate + " 23:55:55";
		
		String sql = "SELECT size  FROM p_size where productid = "+subCategoryID+" and size='"+color+"' and lastmodified between '"+fromdate+"' and '"+todate+"'   ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = result + rs.getString(1) + ",";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Color> getBarcodeColorList(int subCategoryID) {
		PreparedStatement preparedStatement = null;
		String sql = "SELECT color FROM p_size where productid = "+subCategoryID+" group by color";
		ArrayList<Color> list = new ArrayList<Color>();
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Color color = new Color();
				color.setColorName(rs.getString(1));
				list.add(color);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	public String getBarcodeProductId(String globelpsizeid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT productid FROM p_size where id = "+globelpsizeid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return result;
	}

	public boolean checkBarcodeProductExist(String globelpsizeid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM p_size where id = "+globelpsizeid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return result;
	}

	public boolean checkProductEntry(int subCategoryID) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM subcategory where subcategoryname = "+subCategoryID+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public ArrayList<Size>  getBarcodeProductSize(int subCategoryID, String userId,String fromdate,String todate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Size> list = new ArrayList<Size>();
		
		todate = todate + " 23:55:55";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p_size.id, p_size.size, p_size.productid, p_size.color FROM p_size inner join  subcategory ");
		sql.append("on p_size.productid = subcategory.subcategoryname where subcategory.subcategoryname="+subCategoryID+"  ");
		sql.append("and lastmodified between '"+fromdate+"' and '"+todate+"'    ");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			StringBuffer str = new StringBuffer();
			while(rs.next()){
				Size size = new Size();
				size.setId(rs.getInt(1));
				size.setProductSize(rs.getString(2));
				size.setProductID(rs.getInt(3));
				size.setColorName(rs.getString(4));
				size.setColorSize(rs.getString(4));
				list.add(size);
			}
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getShopStock(String productid,int loginid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT stock FROM shop_stock where subcateoryid = "+productid+" and shopid = "+loginid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateShopStock(int qty, int shopid, int subcateoryid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update shop_stock set stock="+qty+" where shopid="+shopid+" and subcateoryid="+subcateoryid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Product> getshopStockList(int loginid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Product>list = new ArrayList<Product>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT product.id,productname,stock FROM shop_stock  inner join product on ");
		sql.append("product.id = shop_stock.subcateoryid where shopid = "+loginid+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2) + " ("+rs.getString(3)+")");
				list.add(product);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public String getPcode(int subCategoryID, String fdate, String tdate,String size) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT pcode FROM company " +
				" inner join p_invoice on p_invoice.companyid = company.id " +
				" where lastupdated between '"+fdate+"' and '"+tdate+"' " +
				" and productid = "+subCategoryID+" and size='"+size+"' ";
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
				
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public ArrayList<Purchase> getBarcodeProdLength(int subCategoryID, String fromdate,
			String todate) {
		
		todate = todate + " 23:55:55";
		PreparedStatement preparedStatement = null;
		ArrayList<Purchase>list = new ArrayList<Purchase>();
		String sql = "SELECT size  FROM p_size where productid = "+subCategoryID+"  and lastmodified between '"+fromdate+"' and '"+todate+"' group by size ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Purchase purchase = new Purchase();
				purchase.setSize(rs.getString(1));
				list.add(purchase);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public double getproductgst(int subCategoryID) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		double gst = 0;
		String sql = "select cgst, sgst from product where id="+subCategoryID+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setCgst(rs.getString(1));
				product.setSgst(rs.getString(2));
				
				gst = Double.parseDouble(product.getCgst())+Double.parseDouble(product.getSgst());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return gst;
	}

	public double getpurchseamount(String subcategoryid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		double purchase = 0;
		String sql = "select purchase from product where id="+subcategoryid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				purchase = rs.getDouble(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return purchase;
	}
	

}
