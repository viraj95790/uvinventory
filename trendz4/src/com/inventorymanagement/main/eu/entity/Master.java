package com.inventorymanagement.main.eu.entity;

import java.util.ArrayList;
import java.util.Vector;



public class Master {
	private int id;
	private String occupation;
	private String jobTitle;
	private String reference;

	private String specialization;
	private String description;
	private String datetime;

	private String specimen;
	private String report_type;
	private String unit;
	private String normal_value;
	private boolean procedure;
	private int templateid;
	
	private String investigation_type_id;
	private String date;
	private String wardid;
	private String discipline_id;
	private String jobtitlegroup;
	private String jobtitlegroup_id;
	private String phone;
	private String other_template_text;
	private int days;
	private String template_nameid;
	private String ipd_template;
	private String text;
	private String department;
	private String title;
	private String template_text;	
	private String group;
	
	private String parentid;
	
	private String cbcid;
	
	
	private String stock;
	
	private String charge="0";
    private String payby;	
    private String nursingtype_id;
    private String taskname;
    private String notes;
    private String frequency;    
    private String nursingtype;
    private String clientid;
    private String ipdid;
    private String conditionid;
    private String practitionerid;
    
    
    
    private String fullname;
    private String ageandgender;
    
    //dosage variables
    private boolean dos1;
    private boolean dos2;
    private boolean dos3;
    private boolean dos4;
    private boolean dos5;
    private boolean dos6;
    private boolean dos7;
    private boolean dos8;
    private boolean dos9;
    private boolean dos10;
    
    //dosage variables
    private String dosch1;
    private String dosch2;
    private String dosch3;
    private String dosch4;
    private String dosch5;
    private String dosch6;
    private String dosch7;
    private String dosch8;
    private String dosch9;
    private String dosch10;
    private String dosage;
    
    private int dosesize;
    
    private String followupdate;
    
    private String clientname;
    
    
    private String dosevalue1;
    private String dosevalue2;
    private String dosevalue3;
    private String dosevalue4;
    private String dosevalue5;
    private String dosevalue6;
    private String dosevalue7;
    private String dosevalue8;
    private String dosevalue9;
    private String dosevalue10;
    
    
    
    
    
    

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAgeandgender() {
		return ageandgender;
	}

	public void setAgeandgender(String ageandgender) {
		this.ageandgender = ageandgender;
	}

	public boolean isDos1() {
		return dos1;
	}

	public void setDos1(boolean dos1) {
		this.dos1 = dos1;
	}

	public boolean isDos2() {
		return dos2;
	}

	public void setDos2(boolean dos2) {
		this.dos2 = dos2;
	}

	public boolean isDos3() {
		return dos3;
	}

	public void setDos3(boolean dos3) {
		this.dos3 = dos3;
	}

	public boolean isDos4() {
		return dos4;
	}

	public void setDos4(boolean dos4) {
		this.dos4 = dos4;
	}

	public boolean isDos5() {
		return dos5;
	}

	public void setDos5(boolean dos5) {
		this.dos5 = dos5;
	}

	public boolean isDos6() {
		return dos6;
	}

	public void setDos6(boolean dos6) {
		this.dos6 = dos6;
	}

	public boolean isDos7() {
		return dos7;
	}

	public void setDos7(boolean dos7) {
		this.dos7 = dos7;
	}

	public boolean isDos8() {
		return dos8;
	}

	public void setDos8(boolean dos8) {
		this.dos8 = dos8;
	}

	public boolean isDos9() {
		return dos9;
	}

	public void setDos9(boolean dos9) {
		this.dos9 = dos9;
	}

	public boolean isDos10() {
		return dos10;
	}

	public void setDos10(boolean dos10) {
		this.dos10 = dos10;
	}

	public String getDosch1() {
		return dosch1;
	}

	public void setDosch1(String dosch1) {
		this.dosch1 = dosch1;
	}

	public String getDosch2() {
		return dosch2;
	}

	public void setDosch2(String dosch2) {
		this.dosch2 = dosch2;
	}

	public String getDosch3() {
		return dosch3;
	}

	public void setDosch3(String dosch3) {
		this.dosch3 = dosch3;
	}

	public String getDosch4() {
		return dosch4;
	}

	public void setDosch4(String dosch4) {
		this.dosch4 = dosch4;
	}

	public String getDosch5() {
		return dosch5;
	}

	public void setDosch5(String dosch5) {
		this.dosch5 = dosch5;
	}

	public String getDosch6() {
		return dosch6;
	}

	public void setDosch6(String dosch6) {
		this.dosch6 = dosch6;
	}

	public String getDosch7() {
		return dosch7;
	}

	public void setDosch7(String dosch7) {
		this.dosch7 = dosch7;
	}

	public String getDosch8() {
		return dosch8;
	}

	public void setDosch8(String dosch8) {
		this.dosch8 = dosch8;
	}

	public String getDosch9() {
		return dosch9;
	}

	public void setDosch9(String dosch9) {
		this.dosch9 = dosch9;
	}

	public String getDosch10() {
		return dosch10;
	}

	public void setDosch10(String dosch10) {
		this.dosch10 = dosch10;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public int getDosesize() {
		return dosesize;
	}

	public void setDosesize(int dosesize) {
		this.dosesize = dosesize;
	}

	public String getFollowupdate() {
		return followupdate;
	}

	public void setFollowupdate(String followupdate) {
		this.followupdate = followupdate;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getDosevalue1() {
		return dosevalue1;
	}

	public void setDosevalue1(String dosevalue1) {
		this.dosevalue1 = dosevalue1;
	}

	public String getDosevalue2() {
		return dosevalue2;
	}

	public void setDosevalue2(String dosevalue2) {
		this.dosevalue2 = dosevalue2;
	}

	public String getDosevalue3() {
		return dosevalue3;
	}

	public void setDosevalue3(String dosevalue3) {
		this.dosevalue3 = dosevalue3;
	}

	public String getDosevalue4() {
		return dosevalue4;
	}

	public void setDosevalue4(String dosevalue4) {
		this.dosevalue4 = dosevalue4;
	}

	public String getDosevalue5() {
		return dosevalue5;
	}

	public void setDosevalue5(String dosevalue5) {
		this.dosevalue5 = dosevalue5;
	}

	public String getDosevalue6() {
		return dosevalue6;
	}

	public void setDosevalue6(String dosevalue6) {
		this.dosevalue6 = dosevalue6;
	}

	public String getDosevalue7() {
		return dosevalue7;
	}

	public void setDosevalue7(String dosevalue7) {
		this.dosevalue7 = dosevalue7;
	}

	public String getDosevalue8() {
		return dosevalue8;
	}

	public void setDosevalue8(String dosevalue8) {
		this.dosevalue8 = dosevalue8;
	}

	public String getDosevalue9() {
		return dosevalue9;
	}

	public void setDosevalue9(String dosevalue9) {
		this.dosevalue9 = dosevalue9;
	}

	public String getDosevalue10() {
		return dosevalue10;
	}

	public void setDosevalue10(String dosevalue10) {
		this.dosevalue10 = dosevalue10;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIpdid() {
		return ipdid;
	}

	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}

	public String getConditionid() {
		return conditionid;
	}

	public void setConditionid(String conditionid) {
		this.conditionid = conditionid;
	}

	public String getPractitionerid() {
		return practitionerid;
	}

	public void setPractitionerid(String practitionerid) {
		this.practitionerid = practitionerid;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getNursingtype() {
		return nursingtype;
	}

	public void setNursingtype(String nursingtype) {
		this.nursingtype = nursingtype;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getNursingtype_id() {
		return nursingtype_id;
	}

	public void setNursingtype_id(String nursingtype_id) {
		this.nursingtype_id = nursingtype_id;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public String getWardid() {
		return wardid;
	}

	public void setWardid(String wardid) {
		this.wardid = wardid;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	

	public String getCbcid() {
		return cbcid;
	}

	public void setCbcid(String cbcid) {
		this.cbcid = cbcid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getOther_template_text() {
		return other_template_text;
	}

	public void setOther_template_text(String other_template_text) {
		this.other_template_text = other_template_text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSpecimen() {
		return specimen;
	}

	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}

	public String getReport_type() {
		return report_type;
	}

	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNormal_value() {
		return normal_value;
	}

	public void setNormal_value(String normal_value) {
		this.normal_value = normal_value;
	}

	public String getInvestigation_type_id() {
		return investigation_type_id;
	}

	public void setInvestigation_type_id(String investigation_type_id) {
		this.investigation_type_id = investigation_type_id;
	}

	ArrayList<Master> specializationList;

	private String discipline;

	private String name;

	private String countryid;

	public String getCountryid() {
		return countryid;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	private String countryName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Master> getSpecializationList() {
		return specializationList;
	}

	public void setSpecializationList(ArrayList<Master> specializationList) {
		this.specializationList = specializationList;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDiscipline_id() {
		return discipline_id;
	}

	public void setDiscipline_id(String discipline_id) {
		this.discipline_id = discipline_id;
	}

	public String getJobtitlegroup() {
		return jobtitlegroup;
	}

	public void setJobtitlegroup(String jobtitlegroup) {
		this.jobtitlegroup = jobtitlegroup;
	}

	public String getJobtitlegroup_id() {
		return jobtitlegroup_id;
	}

	public void setJobtitlegroup_id(String jobtitlegroup_id) {
		this.jobtitlegroup_id = jobtitlegroup_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTemplate_nameid() {
		return template_nameid;
	}

	public void setTemplate_nameid(String template_nameid) {
		this.template_nameid = template_nameid;
	}

	public String getIpd_template() {
		return ipd_template;
	}

	public void setIpd_template(String ipd_template) {
		this.ipd_template = ipd_template;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTemplate_text() {
		return template_text;
	}

	public void setTemplate_text(String template_text) {
		this.template_text = template_text;
	}

	

	public int getTemplateid() {
		return templateid;
	}

	public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}

	public boolean isProcedure() {
		return procedure;
	}

	public void setProcedure(boolean procedure) {
		this.procedure = procedure;
	}

}
