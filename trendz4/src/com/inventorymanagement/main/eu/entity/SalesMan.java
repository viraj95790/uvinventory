package com.inventorymanagement.main.eu.entity;

import java.util.ArrayList;

public class SalesMan {
	
	private int id;
	
	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String mobileNo;
	
	private String address;
	
	private double salaryperday;
	
	private int employeeid;
	
	private String name;
	
	private String currentDateTime;
	
	private String commission;
	
	ArrayList<Attendance>attandanceList;
	
	
	
	
	
	public ArrayList<Attendance> getAttandanceList() {
		return attandanceList;
	}

	public void setAttandanceList(ArrayList<Attendance> attandanceList) {
		this.attandanceList = attandanceList;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getCurrentDateTime() {
		return currentDateTime;
	}

	public void setCurrentDateTime(String currentDateTime) {
		this.currentDateTime = currentDateTime;
	}

	public SalesMan(){
		
	}
	
	public SalesMan(int employeeid, String name){
		
		this.employeeid = employeeid;
		this.name = name;
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


	
	

}
