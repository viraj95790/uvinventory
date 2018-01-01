package com.inventorymanagement.main.eu.bi;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Customer;

public interface CustomerDAO {

	int insertdata(Customer customer);

	ArrayList<Customer> getdata(String filterusertype,String searchtext);

	Customer editdata(String selectedid);

	int updatedata(Customer customer);

	int getdelet(String selectedid);

	

}
