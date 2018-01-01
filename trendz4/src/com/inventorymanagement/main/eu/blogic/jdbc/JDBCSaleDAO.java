package com.inventorymanagement.main.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.a.a.a.b.i;
import com.a.a.a.g.m.l;
import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.main.eu.bi.SaleDAO;
import com.inventorymanagement.main.eu.entity.Color;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.Sale;
import com.inventorymanagement.main.eu.entity.Size;

public class JDBCSaleDAO extends JDBCBaseDAO implements SaleDAO{
	
	public JDBCSaleDAO(Connection connection){
		this.connection = connection;
		
	}

	public int save(Sale sale) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into sale(productid,mrp,quantity,total,categoryid) values(?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, sale.getProductID());
			preparedStatement.setDouble(2, sale.getMrp());
			preparedStatement.setInt(3, sale.getQuantity());
			preparedStatement.setDouble(4, sale.getTotal());
			preparedStatement.setInt(5, sale.getCategoryID());
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkProductExist(int categoryId,int subCategoryID) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from sale where categoryid = "+categoryId+" and productid = "+subCategoryID+" ";
		
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

	public int getQuantity(int subCategoryID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select quantity from sale where productid = "+subCategoryID+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateSale(Sale sale,int productID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update sale set quantity = ?, total = ? where categoryid=? and productid = ?";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, sale.getQuantity());
			preparedStatement.setDouble(2, sale.getTotal());
			preparedStatement.setInt(3, sale.getCategoryID());
			preparedStatement.setInt(4, productID);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Sale> getSaleList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Sale>list = new ArrayList<Sale>();
		String sql = "select productid,mrp,quantity,total,id,categoryid from sale ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Sale sale = new Sale();
				int productID = rs.getInt(1);
				String productName = getProductName(productID);
				sale.setProductID(productID);
				sale.setProductName(productName);
				sale.setMrp(rs.getDouble(2));
				sale.setQuantity(rs.getInt(3));
				sale.setTotal(rs.getDouble(4));
				sale.setId(rs.getInt(5));
				sale.setCategoryID(rs.getInt(6));
				
				list.add(sale);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private String getProductName(int productID) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select productname from product where id = "+productID+" ";
		
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

	public int deleteSale() {
		PreparedStatement preparedStatement=null;
		int result = 0;
		String sql = "truncate sale";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			
		}
		
		return result;
	}

	public int deleteSelectedItem(char item) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from sale where id in("+item+")";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveCustomer(Purchase purchase,String str,int employeeID,int billType,String clientid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into customer(name,mobileno,vat,lastupdated,userid,employeeid,billtype,rupee,clientid) values(?,?,?,?,?,?,?,?,?)";
		int userid = 0;
		try{
			preparedStatement = connection.prepareStatement(sql,preparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, purchase.getCustomerName());
			preparedStatement.setString(2, purchase.getMobileNumber());
			preparedStatement.setInt(3, purchase.getVat());
			preparedStatement.setTimestamp(4,DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setString(5, str);
			preparedStatement.setInt(6, employeeID);
			preparedStatement.setInt(7, billType);
			preparedStatement.setInt(8, purchase.getRupee());
			preparedStatement.setString(9, clientid);
			
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

	public int updateStock(int currentStock, Sale sale) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update subcategory set quantity = "+currentStock+" where categoryid = "+sale.getCategoryID()+" and subcategoryname = "+sale.getProductID()+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveInvoice(Sale sale,int customerID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into invoice(customerid,productid,mrp,quantity,total,categoryid) values(?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerID);
			preparedStatement.setInt(2, sale.getProductID());
			preparedStatement.setDouble(3, sale.getMrp());
			preparedStatement.setInt(4, sale.getQuantity());
			preparedStatement.setDouble(5, sale.getTotal());
			preparedStatement.setInt(6, sale.getCategoryID());
			
			result = preparedStatement.executeUpdate();
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteTempSize() {
		PreparedStatement preparedStatement=null;
		int result = 0;
		String sql = "truncate temp_size";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			
		}
		
		return result;
	}

	public ArrayList<Size> getTempProductSize(int subCategoryID,String barcodeColor) {
		PreparedStatement preparedStatement = null;
		ArrayList<Size> list = new ArrayList<Size>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id, size, color, actualsize FROM temp_size ");
		sql.append("where productid ="+subCategoryID+" ");
		sql.append("and color='"+barcodeColor+"' ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			StringBuffer str = new StringBuffer();
			while(rs.next()){
				Size size = new Size();
				size.setId(rs.getInt(1));
				size.setProductSize(rs.getString(2));
				size.setColorSize(rs.getString(3));
				size.setActualsize(rs.getString(4));
				//size.setProductID(rs.getInt(2));
				list.add(size);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
		
		return list;
	}

	public int saveTempSize(String id, String size, int subCategoryID,String colorName) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into temp_size(id,productid,size,color) values(?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(id));
			preparedStatement.setInt(2, subCategoryID);
			preparedStatement.setString(3, size);
			preparedStatement.setString(4, colorName);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public int deletePsizeByProductID(int productId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from p_size where id = "+productId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveSalenvoice(Purchase purchase, String userId, int customerid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into invoice(productid,customerid,quantity,mrp,total,size,categoryid,color,actualsize,accessories) values(?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, purchase.getProductId());
			preparedStatement.setInt(2, customerid);
			preparedStatement.setInt(3, purchase.getQuantity());
			preparedStatement.setDouble(4, purchase.getMrp());
			preparedStatement.setDouble(5, purchase.getTotal());
			preparedStatement.setString(6, purchase.getSize());
			preparedStatement.setInt(7, purchase.getCategoryid());
			preparedStatement.setString(8, purchase.getColorSize());
			preparedStatement.setString(9, purchase.getActualSize());
			preparedStatement.setInt(10, purchase.getAccessories());
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
		
	}

	public Size getLastRecordBsize(int productID) {
		PreparedStatement preparedStatement = null;
		Size size = new Size();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,productid,size,color,sizeid FROM b_size WHERE id = (SELECT MAX(id) FROM b_size ");
		sql.append("where productid="+productID+") ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				size.setId(rs.getInt(1));
				size.setProductID(rs.getInt(2));
				size.setProductSize(rs.getString(3));
				size.setColorSize(rs.getString(4));
				size.setSizeID(rs.getString(5));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

	public int saveLastRecordtempSize(Size size) {
		PreparedStatement preparedStatement = null;
		int resulr = 0;
		String sql = "insert into temp_size(id,productid,size,color) values(?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, size.getId());
			preparedStatement.setInt(2, size.getProductID());
			preparedStatement.setString(3, size.getProductSize());
			preparedStatement.setString(4, size.getColorSize());
			
			resulr = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resulr;
	}

	public int deleteLastRecordBsize(int productID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "DELETE FROM b_size WHERE productid = "+productID+" ORDER BY id DESC LIMIT 1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getProductColorSize(String productidseries) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select color from p_size where id in("+productidseries+") group by color";
		StringBuffer str = new StringBuffer();
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				str.append(rs.getString(1) + ",");
			}
			
			if(str.length()!=0){
				result = str.substring(0,str.length()-1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	
	public ArrayList<Color> getTempColorList(int subCategoryID) {
		PreparedStatement preparedStatement = null;
		ArrayList<Color>list = new ArrayList<Color>();
		String sql = "SELECT color FROM temp_size where productid="+subCategoryID+" group by color";
		
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

	public ArrayList<Size> getTempSizeListByColor(int subCategoryID,
			String colorName) {
		PreparedStatement preparedStatement = null;
		ArrayList<Size> list = new ArrayList<Size>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id, size, color, actualsize FROM temp_size ");
		sql.append("where productid ="+subCategoryID+" and color = '"+colorName+"' ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			StringBuffer str = new StringBuffer();
			while(rs.next()){
				Size size = new Size();
				size.setId(rs.getInt(1));
				size.setProductSize(rs.getString(2));
				size.setColorSize(rs.getString(3));
				size.setActualsize(rs.getString(4));
				//size.setProductID(rs.getInt(2));
				list.add(size);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
		
		return list;
	}

	public int getTotalQuantityP_size(int subCategoryID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT count(*) FROM temp_size where productid = "+subCategoryID+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public int getTempTotalQuantity(int subCategoryID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT sum(quantity) FROM purchase where productid = "+subCategoryID+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public int getPurchaseQuantity(int productId,String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT sum(quantity) FROM purchase where productid = "+productId+" and userid='"+userid+"'";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public ArrayList<Color> getTemp_sizeColorList(int subCategoryID) {
		PreparedStatement preparedStatement = null;
		ArrayList<Color>list = new ArrayList<Color>();
		String sql = "SELECT color FROM temp_size where productid="+subCategoryID+" group by color";
		
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

	public ArrayList<Size> getTempProductSizeList(int productId, String userId,
			String colorName) {
		PreparedStatement preparedStatement = null;
		ArrayList<Size> list = new ArrayList<Size>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT temp_size.id, temp_size.size, temp_size.productid, temp_size.color FROM temp_size inner join  subcategory ");
		sql.append("on temp_size.productid = subcategory.subcategoryname where subcategory.subcategoryname="+productId+"   and  temp_size.color='"+colorName+"' ");
		
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

	public int getTempQuantity(int productId) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from temp_size where productid = "+productId+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Size getPurchaseSize(int subcategoryID, String selectedColor) {
		PreparedStatement preparedStatement = null;
		Size size = new Size();
		String sql = "SELECT size,productidseries FROM purchase where productid="+subcategoryID+" and color='"+selectedColor+"';";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				size.setProductSize(rs.getString(1));
				size.setSizeID(rs.getString(2));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

	public int deletePurshase(int subCategoryID, String colorSize) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from purchase where productid="+subCategoryID+" and color='"+colorSize+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveS_size(String colorSize, int subCategoryID,
			String sizeValue, String sizeID,int loginid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into s_size(productid,sizeid,size,color,loginid) values(?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, subCategoryID);
			preparedStatement.setInt(2,Integer.parseInt(sizeID));
			preparedStatement.setString(3, sizeValue);
			preparedStatement.setString(4, colorSize);
			preparedStatement.setInt(5, loginid);
			
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean chechs_size(String sizeid,String color) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from s_size where sizeid = "+sizeid+" and color = '"+color+"' ";
		
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

	public ArrayList<Size> getS_SizeList(int subCategoryID, String colorSize,int loginid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Size>list = new ArrayList<Size>();
		String sql = "select sizeid,size from s_size where productid="+subCategoryID+" and color='"+colorSize+"' and loginid="+loginid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Size size = new Size();
				size.setSizeID(rs.getString(1));
				size.setProductSize(rs.getString(2));
				
				
				list.add(size);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int delteS_Size(int loginid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from  s_size where loginid="+loginid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
		e.printStackTrace();
		}
		return result;
	}

	public int deleteLastRecordSsize(String sizeid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from s_size where sizeid = "+sizeid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean chechs_sizeProductID(int sizeid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from s_size where sizeid = "+sizeid+"  ";
		
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

	public int saveSalePayment(int companyId, String howpaid,
			String paymentrecieved, String paymentnote) {
		
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into sale_payment(companyid, paymode, payment, paynote, date) values(?,?,?,?,?)";
			
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


	
	public ArrayList<Color> getBarcodeTempColorList(int subCategoryID,String fromdate,String todate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Color>list = new ArrayList<Color>();
		//String sql = "SELECT color FROM temp_size where productid="+subCategoryID+" group by color";
		
		todate = todate + " 23:55:55";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT temp_size.color FROM temp_size  inner join p_size on ");
		sql.append("p_size.id = temp_size.id where temp_size.productid="+subCategoryID+" ");
		sql.append("and  lastmodified between '"+fromdate+"' and '"+todate+"' ");
		sql.append("group by color ");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
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



}
