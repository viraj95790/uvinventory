package com.inventorymanagement.main.web.form;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Customer;

public class CustomerForm {

	private int id;
	private String vendorid;
	private String title;
	private String name;
	private String middlename;
	private String surname;
	private String company;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipcode;
	private String homephone;
	private String mob;
	private String office;
	private String email;
	private String fax;
	private String webpage;
	private String birthday;
	private String passby;
	private String newspaper;
	private String internet;
	private String flyer;
	private String friends_reference;
	private String posters;
	private String yellow_page;
	private String other;
	private String customer_type;
	private String usertype;
	private String filterusertype = "0";
	
	private String searchText = "";
	
	
	
	
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getFilterusertype() {
		return filterusertype;
	}
	public void setFilterusertype(String filterusertype) {
		this.filterusertype = filterusertype;
	}
	private ArrayList<Customer> customerList;
    private ArrayList<String> cityList;
	private ArrayList<String> stateList;
	private ArrayList<String> titleList;
	private ArrayList<String> customertypeList;
	private String citytext;
	private String statetext;
	private String titletext;
	private String customertext;
	
	
	
	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}
	public ArrayList<String> getCityList() {
		return cityList;
	}
	public void setCityList(ArrayList<String> cityList) {
		this.cityList = cityList;
	}
	public ArrayList<String> getStateList() {
		return stateList;
	}
	public void setStateList(ArrayList<String> stateList) {
		this.stateList = stateList;
	}
	public ArrayList<String> getTitleList() {
		return titleList;
	}
	public void setTitleList(ArrayList<String> titleList) {
		this.titleList = titleList;
	}
	public ArrayList<String> getCustomertypeList() {
		return customertypeList;
	}
	public void setCustomertypeList(ArrayList<String> customertypeList) {
		this.customertypeList = customertypeList;
	}
	public String getCitytext() {
		return citytext;
	}
	public void setCitytext(String citytext) {
		this.citytext = citytext;
	}
	public String getStatetext() {
		return statetext;
	}
	public void setStatetext(String statetext) {
		this.statetext = statetext;
	}
	public String getTitletext() {
		return titletext;
	}
	public void setTitletext(String titletext) {
		this.titletext = titletext;
	}
	public String getCustomertext() {
		return customertext;
	}
	public void setCustomertext(String customertext) {
		this.customertext = customertext;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVendorid() {
		return vendorid;
	}
	public void setVendorid(String vendorid) {
		this.vendorid = vendorid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getHomephone() {
		return homephone;
	}
	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getWebpage() {
		return webpage;
	}
	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPassby() {
		return passby;
	}
	public void setPassby(String passby) {
		this.passby = passby;
	}
	public String getNewspaper() {
		return newspaper;
	}
	public void setNewspaper(String newspaper) {
		this.newspaper = newspaper;
	}
	public String getInternet() {
		return internet;
	}
	public void setInternet(String internet) {
		this.internet = internet;
	}
	public String getFlyer() {
		return flyer;
	}
	public void setFlyer(String flyer) {
		this.flyer = flyer;
	}
	public String getFriends_reference() {
		return friends_reference;
	}
	public void setFriends_reference(String friends_reference) {
		this.friends_reference = friends_reference;
	}
	public String getPosters() {
		return posters;
	}
	public void setPosters(String posters) {
		this.posters = posters;
	}
	public String getYellow_page() {
		return yellow_page;
	}
	public void setYellow_page(String yellow_page) {
		this.yellow_page = yellow_page;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getCustomer_type() {
		return customer_type;
	}
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	
}
