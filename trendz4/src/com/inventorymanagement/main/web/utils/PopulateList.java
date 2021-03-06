package com.inventorymanagement.main.web.utils;





import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;



public class PopulateList {

	/** Resources of property file as a String */
	private static final String COUNTRY_FILE = "/com/inventorymanagement/main/common/resources/countryList.properties";
	private static final String STATE_FILE = "/com/inventorymanagement/main/common/resources/stateList.properties";
	private static final String CITY_FILE = "/com/inventorymanagement/main/common/resources/cityList.properties";
	private static final String TITLE_FILE = "/com/inventorymanagement/main/common/resources/title.properties";
	private static final String CUSTOMER_TYPE_FILE = "/com/inventorymanagement/main/common/resources/customerType.properties";
	private static final String EXPYEAR_FILE = "/com/hospitalmanagement/main/common/resources/expYearList.properties";
	private static final String EXPMONTH_FILE = "/com/hospitalmanagement/main/common/resources/expMonthList.properties";
	private static final String SALLACS_FILE = "/com/hospitalmanagement/main/common/resources/salLacsList.properties";
	private static final String SALTHOUSAND_FILE = "/com/hospitalmanagement/main/common/resources/salThousandList.properties";
	private static final String FUNCTIONAL_AREA_FILE = "/com/hospitalmanagement/main/common/resources/functionalAreaList.properties";
	private static final String CURRENT_INDUSTRY_FILE = "/com/hospitalmanagement/main/common/resources/currentIndustryList.properties";
	private static final String GRADUATION_FILE = "/com/hospitalmanagement/main/common/resources/graduationList.properties";
	private static final String POST_GRADUATION_FILE = "/com/hospitalmanagement/main/common/resources/postGraduationList.properties";
	private static final String OTHER_QUALIFICATION_FILE = "/com/hospitalmanagement/main/common/resources/otherQualificationList.properties";
	private static final String CURRENCY_FILE = "/com/hospitalmanagement/main/common/resources/currencyList.properties";
	private static final String YEAR_FILE = "/com/hospitalmanagement/main/common/resources/yearList.properties";
	private static final String DAY_FILE = "/com/hospitalmanagement/main/common/resources/dayList.properties";
	private static final String DATE_OF_BIRTH_YEAR_FILE = "/com/hospitalmanagement/main/common/resources/dateYearList.properties";
	private static final String MONTH_FILE = "/com/hospitalmanagement/main/common/resources/monthList.properties";
	private static final String SPECIALIZATION_FILE = "/com/hospitalmanagement/main/common/resources/specializationList.properties";
	private static final String UNIVERSITY_FILE = "/com/hospitalmanagement/main/common/resources/universityList.properties";
	private static final String MODE_FILE = "/com/hospitalmanagement/main/common/resources/modeList.properties";
	private static final String LANGUAG_FILE = "/com/hospitalmanagement/main/common/resources/languageList.properties";
	private static final String PROFICIENCY_FILE = "/com/hospitalmanagement/main/common/resources/proficiencyList.properties";
	private static final String PREFEREDJOBLOCATIONS_FILE = "/com/hospitalmanagement/main/common/resources/preferedJobLocation.properties";
	
	private static final String TIME_FILE = "/com/fantasybekary/main/common/resources/timeList.properties";
	
	private static final String AMORPM_FILE = "/com/fantasybekary/main/common/resources/amorpm.properties";
	
	private static final String MINUTE_FILE = "/com/fantasybekary/main/common/resources/minuteList.properties";
	
	/**
	 * countryList() method creates list of country using collection class
	 * 'ArrayList' by taking country name from countryList.properties file
	 * 
	 * @return ArrayList of country
	 */
	public static ArrayList<String> customertypeList() {

		/** arrayList of country */
		ArrayList<String> countryList = new ArrayList<String>();
		
		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(CUSTOMER_TYPE_FILE);
		
		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);
	
			for (int i = 1; i <= properties.size(); i++) {
				countryList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return countryList;
	}
	public static ArrayList<String> titleList() {

		/** arrayList of country */
		ArrayList<String> titleList = new ArrayList<String>();
		
		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(TITLE_FILE);
		
		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);
	
			for (int i = 1; i <= properties.size(); i++) {
				titleList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return titleList;
	}
	public static ArrayList<String> countryList() {

		/** arrayList of country */
		ArrayList<String> countryList = new ArrayList<String>();
		
		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(COUNTRY_FILE);
		
		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);
	
			for (int i = 1; i <= properties.size(); i++) {
				countryList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return countryList;
	}
	
	
	public static ArrayList<String> getTimeList() {

		/** arrayList of country */
		ArrayList<String> timeList = new ArrayList<String>();
		
		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(TIME_FILE);
		
		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);
	
			for (int i = 1; i <= properties.size(); i++) {
				timeList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return timeList;
	}
	
	
	public static ArrayList<String> getMinuteList() {

		/** arrayList of country */
		ArrayList<String> minuteList = new ArrayList<String>();
		
		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(MINUTE_FILE);
		
		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);
	
			for (int i = 1; i <= properties.size(); i++) {
				minuteList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return minuteList;
	}
	
	public static ArrayList<String> getomorpmList() {

		/** arrayList of country */
		ArrayList<String> amorpmList = new ArrayList<String>();
		
		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(AMORPM_FILE);
		
		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);
	
			for (int i = 1; i <= properties.size(); i++) {
				amorpmList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return amorpmList;
	}
	
	
	
	public static ArrayList<String>jobLocationList(){
		
		/** arrayList of state */
		ArrayList<String> jobLocationList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(PREFEREDJOBLOCATIONS_FILE);

		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {
				jobLocationList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jobLocationList;
	}

	/**
	 * stateList()
	 * 
	 * @return ArrayList of state
	 */
	public static ArrayList<String> stateList() {

		/** arrayList of state */
		ArrayList<String> stateList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(STATE_FILE);

		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {
				stateList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stateList;
	}

	/**
	 * cityList()
	 * 
	 * @return ArrayList of city
	 */
	public static ArrayList<String> cityList() {

		/** arrayList of city */
		ArrayList<String> cityList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(CITY_FILE);

		try {		
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {
				cityList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cityList;
	}
	/**
	 * expYearList() method creates list of experience using collection class
	 * 'ArrayList' by taking experience value from experienceList.properties
	 * file
	 * 
	 * @return ArrayList of years
	 */
	public static ArrayList<String> expYearList() {

		/** ArrayList of years of experience */
		ArrayList<String> expYearList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(EXPYEAR_FILE);

		try {		
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {
				expYearList.add(properties.getProperty(new Integer(i).toString()));

			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return expYearList;
	}

	/**
	 * expMonthList() method creates list of experience using collection class
	 * 'ArrayList' by taking experience value from experienceList.properties
	 * file
	 * 
	 * @return ArrayList of years
	 */
	public static ArrayList<String> expMonthList() {

		/** ArrayList of months for monthlist */
		ArrayList<String> expMonthList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(EXPMONTH_FILE);

		try {
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {
				
				expMonthList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return expMonthList;
	}
	
	/**
	 * salLacsList() method creates list of salary values using collection class
	 * 'ArrayList' by taking salary value from salary.properties file
	 * 
	 * @return ArrayList of salary values
	 */
	public static ArrayList<String> salLacsList() {

		/** arrayList of salary */
		ArrayList<String> salLacsList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(SALLACS_FILE);

		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {
				salLacsList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return salLacsList;
	}

	/**
	 * salThousandList() method creates list of salary values using collection class
	 * 'ArrayList' by taking salary value from salary.properties file
	 * 
	 * @return ArrayList of salary values
	 */
	public static ArrayList<String> salThousandList() {

		/** arrayList of salary */
		ArrayList<String> salThousandList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(SALTHOUSAND_FILE);

		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {

				salThousandList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return salThousandList;
	}

	/**
	 * functionalAreaList() method
	 * 
	 * @return ArrayList of functionalArea
	 */
	public static ArrayList<String> functionalAreaList() {

		/** arrayList of Functional Area */
		ArrayList<String> functionalAreaList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(FUNCTIONAL_AREA_FILE);

		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {
				
				functionalAreaList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return functionalAreaList;
	}

	/**
	 * industryList() method
	 * 
	 * @return List of industry
	 */
	public static ArrayList<String> currentIndustryList() {

		/** arrayList of industry */
		ArrayList<String> industryList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(CURRENT_INDUSTRY_FILE);

		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {

				industryList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return industryList;
	}

	/**
	 * graduationList() method
	 * 
	 * @return ArrayList of graduation list
	 */
	public static ArrayList<String> graduationList() {

		/** arrayList of graduation */
		ArrayList<String> graduationList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(GRADUATION_FILE);

		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {

				graduationList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return graduationList;
	}

	/**
	 * postGraduationList() method
	 * 
	 * @return ArrayList of postGraduatin list
	 */
	public static ArrayList<String> postGraduationList() {

		/** arrayList of Post graduation */
		ArrayList<String> postGraduationList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(POST_GRADUATION_FILE);

		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {
				postGraduationList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return postGraduationList;
	}

	/**
	 * otherQualificationList() method
	 * 
	 * @return ArrayList of OtherQuailification List
	 */
	public static ArrayList<String> otherQualificationList() {

		/** arrayList of other Qualification */
		ArrayList<String> otherQualificationList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(OTHER_QUALIFICATION_FILE);

		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {
				
				otherQualificationList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return otherQualificationList;

	}
	
	/**
	 * currencyList() method
	 * 
	 * @return ArrayList of currency List
	 */
	public static ArrayList<String> currencyList() {

		/** arrayList of other currency */
		ArrayList<String> currencyList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(CURRENCY_FILE);

		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {
				currencyList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return currencyList;
	}
	
	/** 
	 * universityList() method
	 * 
	 * @return ArrayList of university list
	 */
	public static ArrayList<String> universityList(){
		
		/** arrayList of university */
		ArrayList<String> universityList = new ArrayList<String>(); 
		
		/** get the properties file data in stream form as input */
		InputStream inputStream =  PopulateList.class.getResourceAsStream(UNIVERSITY_FILE);
		
		try{
			Properties properties = new Properties();
			properties.load(inputStream);
			for(int i = 1; i <= properties.size(); i++){
				universityList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return universityList;
	}
	
	
	/** 
	 * yearList() method
	 * 
	 * @return ArrayList of year list
	 */
	public static ArrayList<String> yearList(){
		
		/** arrayList of year */
		ArrayList<String> yearList = new ArrayList<String>(); 
		
		/** get the properties file data in stream form as input */
		InputStream inputStream =  PopulateList.class.getResourceAsStream(YEAR_FILE);
		
		try{
			Properties properties = new Properties();
			properties.load(inputStream);
			for(int i = 1; i <= properties.size(); i++){
				yearList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return yearList;
	}
	
	public static ArrayList<String>dateYearList(){
		
		/** arrayList of year */
		ArrayList<String> dateYearList = new ArrayList<String>(); 
		
		/** get the properties file data in stream form as input */
		InputStream inputStream =  PopulateList.class.getResourceAsStream(DATE_OF_BIRTH_YEAR_FILE);
		
		try{
			Properties properties = new Properties();
			properties.load(inputStream);
			for(int i = 1; i <= properties.size(); i++){
				dateYearList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return dateYearList;
	}
	
	
	/*monthList*/
	public static ArrayList<String>monthList(){
		
		/** arrayList of year */
		ArrayList<String> monthList = new ArrayList<String>(); 
		
		/** get the properties file data in stream form as input */
		InputStream inputStream =  PopulateList.class.getResourceAsStream(MONTH_FILE);
		
		try{
			Properties properties = new Properties();
			properties.load(inputStream);
			for(int i = 1; i <= properties.size(); i++){
				monthList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return monthList;
	}
	
	
	/*dayList*/
	
	public static ArrayList<String>dayList(){
		
		/** arrayList of year */
		ArrayList<String> dayList = new ArrayList<String>(); 
		
		/** get the properties file data in stream form as input */
		InputStream inputStream =  PopulateList.class.getResourceAsStream(DAY_FILE);
		
		try{
			Properties properties = new Properties();
			properties.load(inputStream);
			for(int i = 1; i <= properties.size(); i++){
				dayList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return dayList;
		
	}
	
	
	/** 
	 * specializationList() method
	 * 
	 * @return ArrayList of specialization list
	 */
	public static ArrayList<String> specializationList(){
		
		/** arrayList of specialization */
		ArrayList<String> specializationList = new ArrayList<String>(); 
		
		/** get the properties file data in stream form as input */
		InputStream inputStream =  PopulateList.class.getResourceAsStream(SPECIALIZATION_FILE);
		
		try{
			Properties properties = new Properties();
			properties.load(inputStream);
			for(int i = 1; i <= properties.size(); i++){
				specializationList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return specializationList;
	}
	
	/**
	 * modeList() method
	 * 
	 * @return ArrayList of mode List
	 */
	public static ArrayList<String> modeList() {

		/** arrayList of other mode */
		ArrayList<String> modeList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(MODE_FILE);

		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {
				modeList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return modeList;
	}
	
	/**
	 * languageList() method
	 * 
	 * @return ArrayList of language List
	 */
	public static ArrayList<String> languageList() {

		/** arrayList of  language */
		ArrayList<String> languageList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(LANGUAG_FILE);

		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {
				languageList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return languageList;
	}
	
	/**
	 * proficiencyList() method
	 * 
	 * @return ArrayList of proficiency List
	 */
	public static ArrayList<String> proficiencyList() {

		/** arrayList of  proficiency */
		ArrayList<String> proficiencyList = new ArrayList<String>();

		/** get the properties file data in stream form as input */
		InputStream inputStream = PopulateList.class.getResourceAsStream(PROFICIENCY_FILE);

		try {			
			Properties properties = new Properties();
			
			properties.load(inputStream);

			for (int i = 1; i <= properties.size(); i++) {
				proficiencyList.add(properties.getProperty(new Integer(i).toString()));
			}
			properties.clear();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return proficiencyList;
	}
}
