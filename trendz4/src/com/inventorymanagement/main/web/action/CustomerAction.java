package com.inventorymanagement.main.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import com.inventorymanagement.main.eu.bi.CustomerDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCCustomerDAO;
import com.inventorymanagement.main.eu.entity.Customer;
import com.inventorymanagement.main.web.form.CustomerForm;
import com.inventorymanagement.main.web.utils.PopulateList;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class CustomerAction extends BaseAction implements Preparable, ModelDriven<CustomerForm> {
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	CustomerForm customerForm = new CustomerForm();
	
	public String execute() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
			
			ArrayList<Customer> custoArrayList = customerDAO.getdata(customerForm.getFilterusertype(),customerForm.getSearchText());
			customerForm.setCustomerList(custoArrayList);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "success";
	}
	public String vendor(){
		
		return "vendor";
		
	}
	public String registerdata() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
			Customer customer = new Customer();
			
			customer.setVendorid(customerForm.getVendorid());
			customer.setTitle(customerForm.getTitle());
			customer.setName(customerForm.getName());
			customer.setMiddlename(customerForm.getMiddlename());
			customer.setSurname(customerForm.getSurname());
			customer.setCompany(customerForm.getCompany());
			customer.setAddress1(customerForm.getAddress1());
			customer.setAddress2(customerForm.getAddress2());
			customer.setCity(customerForm.getCity());
			customer.setState(customerForm.getState());
			customer.setZipcode(customerForm.getZipcode());
			customer.setHomephone(customerForm.getHomephone());
			customer.setMob(customerForm.getMob());
			customer.setOffice(customerForm.getOffice());
			customer.setEmail(customerForm.getEmail());
			customer.setFax(customerForm.getFax());
			customer.setWebpage(customerForm.getWebpage());
			customer.setBirthday(customerForm.getBirthday());
			customer.setPassby(customerForm.getPassby());
			customer.setNewspaper(customerForm.getNewspaper());
			customer.setInternet(customerForm.getInternet());
			customer.setFlyer(customerForm.getFlyer());
			customer.setFriends_reference(customerForm.getFriends_reference());
			customer.setPosters(customerForm.getPosters());
			customer.setYellow_page(customerForm.getYellow_page());
			customer.setOther(customerForm.getOther());
			customer.setCustomer_type(customerForm.getCustomer_type());
			customer.setUsertype(customerForm.getUsertype());
			
			int result = customerDAO.insertdata(customer);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return execute();
		
	}
	public String edit() throws Exception{
		Connection connection = null;
		String selectedid = request.getParameter("selectedid");
		try {
			connection = Connection_provider.getconnection();
			CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
			Customer customer = customerDAO.editdata(selectedid);
			
			customerForm.setId(customer.getId());
			customerForm.setVendorid(customer.getVendorid());
			customerForm.setTitle(customer.getTitle());
			customerForm.setName(customer.getName());
			customerForm.setMiddlename(customer.getMiddlename());
			customerForm.setSurname(customer.getSurname());
			customerForm.setCompany(customer.getCompany());
			customerForm.setAddress1(customer.getAddress1());
			customerForm.setAddress2(customer.getAddress2());
			customerForm.setCity(customer.getCity());
			customerForm.setState(customer.getState());
			customerForm.setZipcode(customer.getZipcode());
			customerForm.setHomephone(customer.getHomephone());
			customerForm.setMob(customer.getMob());
			customerForm.setOffice(customer.getOffice());
			customerForm.setEmail(customer.getEmail());
			customerForm.setFax(customer.getFax());
			customerForm.setWebpage(customer.getWebpage());
			customerForm.setBirthday(customer.getBirthday());
			customerForm.setPassby(customer.getPassby());
			customerForm.setNewspaper(customer.getNewspaper());
			customerForm.setInternet(customer.getInternet());
			customerForm.setFlyer(customer.getFlyer());
			customerForm.setFriends_reference(customer.getFriends_reference());
			customerForm.setPosters(customer.getPosters());
			customerForm.setYellow_page(customer.getYellow_page());
			customerForm.setOther(customer.getOther());
			customerForm.setCustomer_type(customer.getCustomer_type());
			customerForm.setUsertype(customer.getUsertype());
			
			if(customer.getUsertype().equals("0")){
				return "edit";
			}else{
				return "vendor1";
			}
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "edit";
		
	}
	public String update() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
            Customer customer = new Customer();
			
			customer.setId(customerForm.getId());
            customer.setVendorid(customerForm.getVendorid());
			customer.setTitle(customerForm.getTitle());
			customer.setName(customerForm.getName());
			customer.setMiddlename(customerForm.getMiddlename());
			customer.setSurname(customerForm.getSurname());
			customer.setCompany(customerForm.getCompany());
			customer.setAddress1(customerForm.getAddress1());
			customer.setAddress2(customerForm.getAddress2());
			customer.setCity(customerForm.getCity());
			customer.setState(customerForm.getState());
			customer.setZipcode(customerForm.getZipcode());
			customer.setHomephone(customerForm.getHomephone());
			customer.setMob(customerForm.getMob());
			customer.setOffice(customerForm.getOffice());
			customer.setEmail(customerForm.getEmail());
			customer.setFax(customerForm.getFax());
			customer.setWebpage(customerForm.getWebpage());
			customer.setBirthday(customerForm.getBirthday());
			customer.setPassby(customerForm.getPassby());
			customer.setNewspaper(customerForm.getNewspaper());
			customer.setInternet(customerForm.getInternet());
			customer.setFlyer(customerForm.getFlyer());
			customer.setFriends_reference(customerForm.getFriends_reference());
			customer.setPosters(customerForm.getPosters());
			customer.setYellow_page(customerForm.getYellow_page());
			customer.setOther(customerForm.getOther());
			customer.setCustomer_type(customerForm.getCustomer_type());
			
			int result = customerDAO.updatedata(customer);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return execute();
		
	}
	
	public String delete() throws Exception{
		Connection connection = null;
		String selectedid = request.getParameter("selectedid");
		try {
			connection = Connection_provider.getconnection();
			CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
			
			int result = customerDAO.getdelet(selectedid);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return execute();
		
	}
	
	public String info(){
		
		String selectedid = request.getParameter("selectedid");
		
		Connection connection = null;
		
		try {
			connection = Connection_provider.getconnection();
			CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
			Customer customer = customerDAO.editdata(selectedid);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+customer.getMob() + "~" + customer.getName() + " " + customer.getSurname()+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return null;
		
	}

	public CustomerForm getModel() {
		// TODO Auto-generated method stub
		return customerForm;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		customerForm.setTitleList(PopulateList.titleList());
		customerForm.setCityList(PopulateList.cityList());
		customerForm.setStateList(PopulateList.stateList());
		customerForm.setCustomertypeList(PopulateList.customertypeList());
		
		
	}

}
