package com.inventorymanagement.main.web.form;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.CustomCake;

public class CustomCakeForm {
	
	private int id;
	
	private Double weight;
	
	private String flavour;
	
	private String notes;
	
	private String msgOnCake;
	
	private Integer amount;
	
	private int timeID;
	
	private int amorpmID;
	
	private String time;
	
	private String minute;
	
	private String amorpm;
	
	private String deliveryDate;
	
	private ArrayList<String>timeList;
	
	private ArrayList<String>minuteList;
	
	private ArrayList<String>amorpmList;
	
	private String pagerecords;
	
	private int totalRecords;
	
	private ArrayList<CustomCake>customCakeList;
	
	private ArrayList<Branch>branchList;
	
	private int branchID;
	
	private String branchName;

	
	public ArrayList<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(ArrayList<Branch> branchList) {
		this.branchList = branchList;
	}

	public ArrayList<CustomCake> getCustomCakeList() {
		return customCakeList;
	}

	public void setCustomCakeList(ArrayList<CustomCake> customCakeList) {
		this.customCakeList = customCakeList;
	}

	public int getTimeID() {
		return timeID;
	}

	public void setTimeID(int timeID) {
		this.timeID = timeID;
	}

	public int getAmorpmID() {
		return amorpmID;
	}

	public void setAmorpmID(int amorpmID) {
		this.amorpmID = amorpmID;
	}

	public ArrayList<String> getTimeList() {
		return timeList;
	}

	public void setTimeList(ArrayList<String> timeList) {
		this.timeList = timeList;
	}

	public ArrayList<String> getAmorpmList() {
		return amorpmList;
	}

	public void setAmorpmList(ArrayList<String> amorpmList) {
		this.amorpmList = amorpmList;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getFlavour() {
		return flavour;
	}

	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getMsgOnCake() {
		return msgOnCake;
	}

	public void setMsgOnCake(String msgOnCake) {
		this.msgOnCake = msgOnCake;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAmorpm() {
		return amorpm;
	}

	public void setAmorpm(String amorpm) {
		this.amorpm = amorpm;
	}

	public String getPagerecords() {
		return pagerecords;
	}

	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public ArrayList<String> getMinuteList() {
		return minuteList;
	}

	public void setMinuteList(ArrayList<String> minuteList) {
		this.minuteList = minuteList;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	
	
}
