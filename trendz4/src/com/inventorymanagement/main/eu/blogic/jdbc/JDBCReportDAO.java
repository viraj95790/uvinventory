package com.inventorymanagement.main.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.main.eu.bi.ReportDAO;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.web.form.ReportForm;

public class JDBCReportDAO extends JDBCBaseDAO implements ReportDAO {
	
	public JDBCReportDAO(Connection connection){
		this.connection = connection;
	}

	public ArrayList<Product> getproductinfo(String fromdate, String todate, String productName) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ArrayList<Product> list = new ArrayList<Product>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT company.lastupdated,productname,quantity FROM company inner join p_invoice on ");
		sql.append("p_invoice.companyid = company.id inner join product  on product.id = p_invoice.productid ");
		sql.append("where lastupdated between '"+fromdate+"' and '"+todate+"' ");
		if(!productName.equals("")){
			sql.append("and product.id = '"+productName+"'");
		}
		
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setLastUpdated(rs.getString(1));
				product.setProductName(rs.getString(2));
				product.setQuantity(rs.getInt(3));
				
				String currentdate = DateTimeUtils.getLastModifiedDate(new Date());
				String temp[] = currentdate.split(" ");
				long days = DateTimeUtils.getDiffofTwoDates(product.getLastUpdated(), temp[0]);
				product.setDays(days);
				
				list.add(product);
				
				
			}
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Product> getproductlist() {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ArrayList<Product> list = new ArrayList<Product>();
		String sql = "select id, productname from product";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				list.add(product);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Product> getprofit(String productName) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ArrayList<Product> list = new ArrayList<Product>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT product.id,productname,sum(p_invoice.total) FROM company inner join p_invoice on ");
		sql.append("p_invoice.companyid = company.id inner join product  on product.id = p_invoice.productid ");
		if(!productName.equals("")){
			sql.append("where product.id = '"+productName+"'");
		}
		sql.append("group by product.id ");
		
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getDouble(3));
				
				double saleprice = getsaleprice(product.getId());
				product.setSaleprice(DateTimeUtils.changeFormat(saleprice));
				
				
				if(product.getPrice()> saleprice){
					double total = product.getPrice() - saleprice;
					product.setLoss(DateTimeUtils.changeFormat(total));
					product.setProfit("0");
				}else{
					double total = saleprice - product.getPrice();
					 product.setProfit(DateTimeUtils.changeFormat(total));
					 product.setLoss("0");
				}
				
				list.add(product);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}

	private double getsaleprice(int id) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		double saleprice = 0;
		String sql = "SELECT sum(total) FROM invoice where productid ='"+id+"' ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				saleprice = rs.getDouble(1);
			}
			
		} catch (Exception e) {
			// TODO:  exception
			e.printStackTrace();
		}
		
		return saleprice;
	}

}
