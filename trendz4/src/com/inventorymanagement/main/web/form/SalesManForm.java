package com.inventorymanagement.main.web.form;

import java.util.ArrayList;
import java.util.Date;

import com.inventorymanagement.main.eu.entity.SalesMan;

public class SalesManForm {
	
	private int id;
	
	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String mobileNo;
	
	private String address;
	
	private double salaryperday;
	
	private ArrayList<SalesMan>salesManList=null;
	
	private ArrayList<SalesMan>populatedSalesManList;
	
	private ArrayList<SalesMan>attendanceList;
	
	private int employeeid;
	
	private String name;
	
	private String currentdatetime;

	private String toDate;
	
	private String fromDate;

	private double totalSalary;
	
	private String commission;
	
	
	

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public double getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}

	public String getCurrentdatetime() {
		return currentdatetime;
	}

	public void setCurrentdatetime(String currentdatetime) {
		this.currentdatetime = currentdatetime;
	}

	public ArrayList<SalesMan> getPopulatedSalesManList() {
		return populatedSalesManList;
	}

	public void setPopulatedSalesManList(ArrayList<SalesMan> populatedSalesManList) {
		this.populatedSalesManList = populatedSalesManList;
	}

	public ArrayList<SalesMan> getSalesManList() {
		return salesManList;
	}

	public void setSalesManList(ArrayList<SalesMan> salesManList) {
		this.salesManList = salesManList;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getSalaryperday() {
		return salaryperday;
	}

	public void setSalaryperday(double salaryperday) {
		this.salaryperday = salaryperday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ArrayList<SalesMan> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(ArrayList<SalesMan> attendanceList) {
		this.attendanceList = attendanceList;
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

}
