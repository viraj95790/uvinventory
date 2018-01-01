package com.inventorymanagement.main.web.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.main.eu.bi.ReportDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCReportDAO;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.web.action.BaseAction;
import com.inventorymanagement.main.web.form.ReportForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ReportAction extends BaseAction implements Preparable, ModelDriven<ReportForm> {
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	
	ReportForm reportForm = new ReportForm();
	
	public String execute() throws Exception{
         Connection connection =null;
         String fromdate = reportForm.getFromdate();
         String todate = reportForm.getTodate();
         
         if(fromdate.equals("")){
 			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
 			Calendar cal = Calendar.getInstance();
 			cal.add(Calendar.DATE, -7); 
 			fromdate = dateFormat.format(cal.getTime());
 			reportForm.setFromdate(fromdate);
 		}
 		if(todate.equals("")){
 			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
 			Calendar cal = Calendar.getInstance();
 			//cal.add(Calendar.DATE, -7); 
 			todate = dateFormat.format(cal.getTime());
 			reportForm.setTodate(todate);
 		}
 		
 		if(reportForm.getFromdate()!=null){
 			fromdate = DateTimeUtils.changeDateFormat(reportForm.getFromdate());
 			todate = DateTimeUtils.changeDateFormat(reportForm.getTodate());
 		}
 		
         try {
        	 connection = Connection_provider.getconnection();
        	 ReportDAO reportDAO = new JDBCReportDAO(connection);
        	 
        	 
        	 ArrayList<Product> list = reportDAO.getproductinfo(fromdate, todate, reportForm.getProductName());
        	 reportForm.setStockInfoList(list);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "success";
		
	}
	
	public String profitloss(){
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ReportDAO reportDAO = new JDBCReportDAO(connection);
			
			ArrayList<Product> list = reportDAO.getprofit(reportForm.getProductName());
			reportForm.setProfitlossList(list);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "profitloss";	
		
	}

	public ReportForm getModel() {
		// TODO Auto-generated method stub
		return reportForm;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ReportDAO reportDAO = new JDBCReportDAO(connection);
			
			ArrayList<Product> list = reportDAO.getproductlist();
			reportForm.setProductList(list);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	}

}
