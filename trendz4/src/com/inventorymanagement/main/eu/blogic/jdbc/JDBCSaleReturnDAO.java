package com.inventorymanagement.main.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.main.eu.bi.SaleReturnDAO;
import com.inventorymanagement.main.eu.entity.Invoice;
import com.inventorymanagement.main.eu.entity.Purchase;

public class JDBCSaleReturnDAO extends JDBCBaseDAO implements SaleReturnDAO{
	
	
	public JDBCSaleReturnDAO(Connection connection){
		this.connection = connection;
	}

	public int saveCompany(Purchase purchase, String userId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into saler_company(name,mobileno,vat,lastupdated,userid) values(?,?,?,?,?)";
		int userid = 0;
		try{
			preparedStatement = connection.prepareStatement(sql,preparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, purchase.getCustomerName());
			preparedStatement.setString(2, purchase.getMobileNumber());
			preparedStatement.setInt(3, purchase.getVat());
			preparedStatement.setTimestamp(4,DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setString(5, userId);
			
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

	public int saveSize(Purchase purchase, String size, String actualSize) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into saler_size(productid,size,actualsize,color) values(?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, purchase.getProductId());
			preparedStatement.setString(2, size);
			preparedStatement.setString(3, actualSize);
			preparedStatement.setString(4, purchase.getColorSize());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int savePurchaseInvoice(Purchase purchase, String userId,
			int companyID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into saler_invoice(productid,companyid,quantity,mrp,total,size,categoryid,color,actualsize) values(?,?,?,?,?,?,?,?,?)";
		
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
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Invoice> getCompanyList(String userId) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<Invoice>list = new ArrayList<Invoice>();
		String sql = "select id, name,mobileno,vat,lastupdated from saler_company where userid=? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Invoice invoice = new Invoice();
				invoice.setInvoiceNumber(rs.getInt(1));
				invoice.setCustomerName(rs.getString(2));
				invoice.setMobileNumber(rs.getString(3));
				invoice.setVat(rs.getInt(4));
				invoice.setLastUpdated(DateTimeUtils.getDateinSimpleStringFormate(rs.getTimestamp(5)));
				
				
				list.add(invoice);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Purchase> getPurchaseInvoiceList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Purchase>list = new ArrayList<Purchase>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT productid,mrp,quantity,total,categoryid,size,color,actualsize,id FROM saler_invoice ");
		//sql.append("inner join saler_company on saler_company.id = saler_invoice.companyid and saler_company.id = "+invoiceNumber+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Purchase purchase = new Purchase();
				purchase.setProductName(getProductName(rs.getInt(1),rs.getString(7)));
				purchase.setMrp(rs.getDouble(2));
				purchase.setQuantity(rs.getInt(3));
				purchase.setTotal(rs.getDouble(4));
				purchase.setCategoryid(rs.getInt(5));
				
				String dbViewSize = "";
				String tempViewSize[] = rs.getString(6).split(",");
				String tempActualSize[] = rs.getString(8).split(",");
				purchase.setId(rs.getInt(9));
				
			/*	for(int i=0;i<tempViewSize.length;i++){
					dbViewSize = dbViewSize + "(" + tempActualSize[i] +  ")" + tempViewSize[i] + ",";
				}
				
				dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);*/
				for(int i=0;i<tempViewSize.length;i++){
					dbViewSize = dbViewSize + tempViewSize[i] + ",";
				}
				dbViewSize = dbViewSize.substring(0, dbViewSize.length()-1);
				
				purchase.setSize(dbViewSize);
				
				
				list.add(purchase);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	private String getProductName(int productID,String colorName) {
		PreparedStatement preparedStatement = null;
		String result = "";
		//String sql = "select productname from product where id = "+productID+" ";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT categoryName from category inner join ");
		sql.append("product on category.id = product.categoryid and product.id= "+productID+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
			
			
			result = result + "("+colorName + " "+getArticleNumber(productID)+")";
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public String getArticleNumber(int productID){
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select productname from product where id = "+productID+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public Purchase getSaleReturnDetails(String salereturnID) {
		
		PreparedStatement preparedStatement = null;
		Purchase purchase = new Purchase();
		StringBuffer sql = new StringBuffer();
	
		sql.append("SELECT productid,mrp,quantity,total,categoryid,size,color,actualsize,vat,name,mobileno,companyid ");
		sql.append("FROM saler_invoice ");
		sql.append("inner join saler_company on saler_company.id = saler_invoice.companyid ");
		sql.append("where saler_invoice.id = "+salereturnID+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				purchase.setProductId(rs.getInt(1));
				purchase.setMrp(rs.getDouble(2));
				purchase.setQuantity(rs.getInt(3));
				purchase.setTotal(rs.getDouble(4));
				purchase.setCategoryid(rs.getInt(5));
				purchase.setSize(rs.getString(6));
				purchase.setColor(rs.getString(7));
				purchase.setColorSize(purchase.getColor());
				purchase.setActualSize(rs.getString(8));
				purchase.setVat(rs.getInt(9));
				purchase.setCustomerName(rs.getString(10));
				purchase.setMobileNumber(rs.getString(11));
				purchase.setCompanyID(rs.getInt(12));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return purchase;
	}

	public int deleteSaleReturn(String salereturnID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from saler_invoice where id = "+salereturnID+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int countSaler_invoiceData(int companyID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from saler_invoice where companyid = "+companyID+" ";
		
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

	public int deleteSaler_company(int companyID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from saler_company where id = "+companyID+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return result;
	}

	
	

}
