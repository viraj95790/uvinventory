package com.inventorymanagement.main.eu.bi;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Customer;
import com.inventorymanagement.main.eu.entity.SalesMan;

public interface SalesManDAO {

	ArrayList<SalesMan> getSalesManList(String userID);

	int saveSalesMan(SalesMan salesMan, String userid);

	SalesMan getEmployee(int employeeid);

	int saveAttendance(int employeeid);

	boolean checkAttendanceExist(String currentdate, int employeeid);

	ArrayList<SalesMan> getAttendanceList(int employeeid,String fromDate,String toDate);

	SalesMan getSalesMan(int selectedId);

	int updateSaleMan(SalesMan salesMan);

	int deleteEmployee(int selectedId);

	ArrayList<Customer> getCustomerList();

	ArrayList<SalesMan> getSalesmanAttendanceList(String fromDate, String toDate,String userid);

}
