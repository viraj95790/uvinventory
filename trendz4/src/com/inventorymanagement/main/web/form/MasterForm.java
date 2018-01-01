package com.inventorymanagement.main.web.form;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.inventorymanagement.main.eu.entity.Customer;
import com.inventorymanagement.main.eu.entity.Master;
import com.inventorymanagement.main.web.action.CustomerAction;



public class MasterForm {
	private int id;
	private String occupation;
	private String jobTitle;
	private String pagerecords;
	private int totalRecords;
	private String reference;
	ArrayList<Master> occupationList;
	ArrayList<Master> jobTitleList;
	ArrayList<Master> referenceList;
	private String message;

	private String specialization;
	private String description;
	private String datetime;
	private String name;
	private String masters;
	private String discipline_id;
	
	 private java.io.File fileName;
	 private String smsTemplate;
	 private String smstxt;
	 private String subject;
	 
	 ArrayList<Master> ipd_templateList;
	 ArrayList<Master> ipd_template_names;
	 private ArrayList<Master> listContacts;
	 private String numbers;
	 private ArrayList<Master> newChargeTypeList;
	
	private ArrayList<Master> godownList;
	private ArrayList<Customer> customerList;
	 
	 private File userFile;
	 private String userFileFileName;
	 private String userFileContentType;
	
	
	private String mastername;
	private String jobtitlegroup;
	private String jobtitlegroup_id;
	private ArrayList<Master> jobtitlegropulist;
	
	private boolean procedure;
	
	ArrayList<Master> specializationList;
	ArrayList<Master> notAvailableReasonList;
	ArrayList<Master> dischargeStatusList;

	private ArrayList<Master> masterlist;

	private String title;
	private String other_template_text;
    private String template_text;
	
	private String template_nameid;
	ArrayList<Master> expececategorylist;
	
	private String wardid;
	private String charge;
	
	private ArrayList<Master> standardChargesList;
	
	private String text;
	
	private Collection<Master> standardcharges;
	private ArrayList<Master> masterChageTypeList;
	private String payby;
	
	ArrayList<String> paybyList;
	
	
	
	
	
	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}

	public ArrayList<String> getPaybyList() {
		return paybyList;
	}

	public void setPaybyList(ArrayList<String> paybyList) {
		this.paybyList = paybyList;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public ArrayList<Master> getMasterChageTypeList() {
		return masterChageTypeList;
	}

	public void setMasterChageTypeList(ArrayList<Master> masterChageTypeList) {
		this.masterChageTypeList = masterChageTypeList;
	}

	public Collection<Master> getStandardcharges() {
		return standardcharges;
	}

	public void setStandardcharges(Collection<Master> standardcharges) {
		this.standardcharges = standardcharges;
	}

	

	public ArrayList<Master> getStandardChargesList() {
		return standardChargesList;
	}

	public void setStandardChargesList(ArrayList<Master> standardChargesList) {
		this.standardChargesList = standardChargesList;
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

	public ArrayList<Master> getExpececategorylist() {
		return expececategorylist;
	}

	public void setExpececategorylist(ArrayList<Master> expececategorylist) {
		this.expececategorylist = expececategorylist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOther_template_text() {
		return other_template_text;
	}

	public void setOther_template_text(String other_template_text) {
		this.other_template_text = other_template_text;
	}

	ArrayList<Master> new_chargetye_list;

	private ArrayList<Master> otherTemplateList;

	public ArrayList<Master> getOtherTemplateList() {
		return otherTemplateList;
	}

	public void setOtherTemplateList(ArrayList<Master> otherTemplateList) {
		this.otherTemplateList = otherTemplateList;
	}

	public ArrayList<Master> getNew_chargetye_list() {
		return new_chargetye_list;
	}

	public void setNew_chargetye_list(ArrayList<Master> new_chargetye_list) {
		this.new_chargetye_list = new_chargetye_list;
	}

	public String getMastername() {
		return mastername;
	}

	public void setMastername(String mastername) {
		this.mastername = mastername;
	}

	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}

	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}

	public String getMasters() {
		return masters;
	}

	public void setMasters(String masters) {
		this.masters = masters;
	}

	public ArrayList<Master> getDischargeStatusList() {
		return dischargeStatusList;
	}

	public void setDischargeStatusList(ArrayList<Master> dischargeStatusList) {
		this.dischargeStatusList = dischargeStatusList;
	}

	ArrayList<Master> dischargeOutcomeList;

	public ArrayList<Master> getDischargeOutcomeList() {
		return dischargeOutcomeList;
	}

	public void setDischargeOutcomeList(ArrayList<Master> dischargeOutcomeList) {
		this.dischargeOutcomeList = dischargeOutcomeList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String discipline;

	ArrayList<Master> disciplineList;

	ArrayList<Master> nursingcategorylist;
	Collection<Master> nursing_category;
	
	
	private String taskname;
    private String notes;
    private String frequency;

    Collection<Master> nursing_details;
    private String nursingtype_id;
    
    
    private String clientid;
    private String ipdid;
    private String conditionid;
    private String practitionerid;
    
    
    
   private  ArrayList<Master> nursingdetails;
   
   
   
   
	public ArrayList<Master> getNursingdetails() {
	return nursingdetails;
}

public void setNursingdetails(ArrayList<Master> nursingdetails) {
	this.nursingdetails = nursingdetails;
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

	public Collection<Master> getNursing_category() {
		return nursing_category;
	}

	public void setNursing_category(Collection<Master> nursing_category) {
		this.nursing_category = nursing_category;
	}

	public ArrayList<Master> getNursingcategorylist() {
		return nursingcategorylist;
	}

	public void setNursingcategorylist(ArrayList<Master> nursingcategorylist) {
		this.nursingcategorylist = nursingcategorylist;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public ArrayList<Master> getDisciplineList() {
		return disciplineList;
	}

	public void setDisciplineList(ArrayList<Master> disciplineList) {
		this.disciplineList = disciplineList;
	}

	public ArrayList<Master> getSpecializationList() {
		return specializationList;
	}

	public void setSpecializationList(ArrayList<Master> specializationList) {
		this.specializationList = specializationList;
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

	public ArrayList<Master> getNotAvailableReasonList() {
		return notAvailableReasonList;
	}

	public void setNotAvailableReasonList(
			ArrayList<Master> notAvailableReasonList) {
		this.notAvailableReasonList = notAvailableReasonList;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<Master> getReferenceList() {
		return referenceList;
	}

	public void setReferenceList(ArrayList<Master> referenceList) {
		this.referenceList = referenceList;
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

	public ArrayList<Master> getOccupationList() {
		return occupationList;
	}

	public void setOccupationList(ArrayList<Master> occupationList) {
		this.occupationList = occupationList;
	}

	public ArrayList<Master> getJobTitleList() {
		return jobTitleList;
	}

	public void setJobTitleList(ArrayList<Master> jobTitleList) {
		this.jobTitleList = jobTitleList;
	}

	public Collection<Master> getNursing_details() {
		return nursing_details;
	}

	public void setNursing_details(Collection<Master> nursing_details) {
		this.nursing_details = nursing_details;
	}

	public String getNursingtype_id() {
		return nursingtype_id;
	}

	public void setNursingtype_id(String nursingtype_id) {
		this.nursingtype_id = nursingtype_id;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
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

	public ArrayList<Master> getJobtitlegropulist() {
		return jobtitlegropulist;
	}

	public void setJobtitlegropulist(ArrayList<Master> jobtitlegropulist) {
		this.jobtitlegropulist = jobtitlegropulist;
	}

	public ArrayList<Master> getGodownList() {
		return godownList;
	}

	public void setGodownList(ArrayList<Master> godownList) {
		this.godownList = godownList;
	}

	public String getSmsTemplate() {
		return smsTemplate;
	}

	public void setSmsTemplate(String smsTemplate) {
		this.smsTemplate = smsTemplate;
	}

	

	public java.io.File getFileName() {
		return fileName;
	}

	public void setFileName(java.io.File fileName) {
		this.fileName = fileName;
	}

	public String getSmstxt() {
		return smstxt;
	}

	public void setSmstxt(String smstxt) {
		this.smstxt = smstxt;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public File getUserFile() {
		return userFile;
	}

	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}

	public String getUserFileFileName() {
		return userFileFileName;
	}

	public void setUserFileFileName(String userFileFileName) {
		this.userFileFileName = userFileFileName;
	}

	public String getUserFileContentType() {
		return userFileContentType;
	}

	public void setUserFileContentType(String userFileContentType) {
		this.userFileContentType = userFileContentType;
	}

	public ArrayList<Master> getListContacts() {
		return listContacts;
	}

	public void setListContacts(ArrayList<Master> listContacts) {
		this.listContacts = listContacts;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public ArrayList<Master> getIpd_templateList() {
		return ipd_templateList;
	}

	public void setIpd_templateList(ArrayList<Master> ipd_templateList) {
		this.ipd_templateList = ipd_templateList;
	}

	public ArrayList<Master> getIpd_template_names() {
		return ipd_template_names;
	}

	public void setIpd_template_names(ArrayList<Master> ipd_template_names) {
		this.ipd_template_names = ipd_template_names;
	}

	public String getTemplate_text() {
		return template_text;
	}

	public void setTemplate_text(String template_text) {
		this.template_text = template_text;
	}

	public String getTemplate_nameid() {
		return template_nameid;
	}

	public void setTemplate_nameid(String template_nameid) {
		this.template_nameid = template_nameid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<Master> getNewChargeTypeList() {
		return newChargeTypeList;
	}

	public void setNewChargeTypeList(ArrayList<Master> newChargeTypeList) {
		this.newChargeTypeList = newChargeTypeList;
	}

	

	public boolean isProcedure() {
		return procedure;
	}

	public void setProcedure(boolean procedure) {
		this.procedure = procedure;
	}
}
