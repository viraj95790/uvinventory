package com.inventorymanagement.main.web.form;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Branch;

public class BranchForm {
	
	private int id;
	
	private String userId;
	
	private String password;
	
	private String branchName;
	
	private String description;
	
	private int userType;
	
	private ArrayList<Branch> branchList;
	
	/** Email Id of user */
	private String email;
	
	/** user's address */
	private String address;
           
	/** Country of user */
	private String country;
	
	/** Country list to be populated for drop down country list */
	private ArrayList<String> countryList;

	/** State of user (selected from drop down) */
	private String state;
	
	/** State of user (typed in text box) */
	private String stateText;
	
	/** State list to be populated for drop down state list */
	private ArrayList<String> stateList;

	/** City of user (selected from drop down) */
	private String city;
	
	/** City of user (typed in text box) */
	private String cityText;
	
	/** City list to be populated for drop down city list */
	private ArrayList<String> cityList;
	
	/** Mobile number of user */
	private String mobileNo;
	
	/** Landline number of user */
	private String landLine;
	
	private String pinCode;
	
	private String companyName;
	
	private String code;
	
	
	public int getUserType() {
		return userType;
	}



	public void setUserType(int userType) {
		this.userType = userType;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public ArrayList<String> getCountryList() {
		return countryList;
	}



	public void setCountryList(ArrayList<String> countryList) {
		this.countryList = countryList;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getStateText() {
		return stateText;
	}



	public void setStateText(String stateText) {
		this.stateText = stateText;
	}



	public ArrayList<String> getStateList() {
		return stateList;
	}



	public void setStateList(ArrayList<String> stateList) {
		this.stateList = stateList;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getCityText() {
		return cityText;
	}



	public void setCityText(String cityText) {
		this.cityText = cityText;
	}



	public ArrayList<String> getCityList() {
		return cityList;
	}



	public void setCityList(ArrayList<String> cityList) {
		this.cityList = cityList;
	}



	public String getMobileNo() {
		return mobileNo;
	}



	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}



	public String getLandLine() {
		return landLine;
	}



	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}





	public String getPinCode() {
		return pinCode;
	}



	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}



	public int getId() {
		return id;
	}

	

	public ArrayList<Branch> getBranchList() {
		return branchList;
	}



	public void setBranchList(ArrayList<Branch> branchList) {
		this.branchList = branchList;
	}



	public void setId(int id) {
		this.id = id;
	}

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getCompanyName() {
		return companyName;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
