package com.inventorymanagement.common.utils;




import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class DateTimeUtils {
	
	static {	
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Calcutta"));		// set time zone for Indian standard time
	}
	
	/** Time unit in years */
	public static final int UNIT_YEARS = 1;	
	/** Time unit in months */
	public static final int UNIT_MONTHS = 2;
	/** Time unit in weeks */
	public static final int UNIT_WEEKS = 3;
	/** Time unit in days */
	public static final int UNIT_DAYS = 4;
	/** Time unit in hours */
	public static final int UNIT_HOURS = 5;
	/** Time unit in minutes */
	public static final int UNIT_MINUTES = 6;
	/** Time unit in seconds */
	public static final int UNIT_SECONDS = 7;
	
	/** milliseconds in one average year */
	private static final long MS_IN_YEAR = 31556952000l;
	/** milliseconds in one average month */
	private static final long MS_IN_MONTH = 2592000000l;
	/** milliseconds in one week */
	private static final long MS_IN_WEEK= 604800000l;
	/** milliseconds in one day */
	private static final long MS_IN_DAY = 86400000l;
	/** milliseconds in one hour */
	private static final long MS_IN_HOUR = 3600000l;
	/** milliseconds in one minute */
	private static final long MS_IN_MINUTE= 60000l;
	/** milliseconds in one second */
	private static final long MS_IN_SECOND = 1000l;
	
	/**
	 * Get current date
	 * 
	 * @return Date current date 
	 */
	public static Date getCurrentDateTime(){
		
		Date currentDate = null;
			
		// get calender instance for
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
		
		// get current date
		currentDate = calendar.getTime();
		
		return currentDate;
		
	}
	
	/**
	 * Return the difference between two dates in milliseconds
	 * @param olderDate older date
	 * @param newerDate newer date
	 * @return difference in milliseconds
	 */
	public static long getDateDifferenceInMillis(Date olderDate, Date newerDate){
		
		long difference = 0;

		long oldMillis = olderDate.getTime();	// get milliseconds in older date
		long newMillis = newerDate.getTime();	// get milliseconds in newer date
		
		difference = newMillis - oldMillis;		// get difference in milliseconds
		
		return difference;
		
	}
	
	/** *
	 * 
	 * Checks if given date is expired against given expiration period
	 * 
	 * @param startDate	when expiration period started
	 * @param expPeriod	expiration period
	 * @param expPeriodUnit unit of expiration period. Values should be one of the following:
	 * <ul>
	 * <li>DateTimeUtils.UNIT_YEARS</li>
	 * <li>DateTimeUtils.UNIT_MONTHS</li>
	 * <li>DateTimeUtils.UNIT_WEEKS</li>
	 * <li>DateTimeUtils.UNIT_DAYS</li>
	 * <li>DateTimeUtils.UNIT_HOURS</li>
	 * <li>DateTimeUtils.UNIT_MINUTES</li>
	 * <li>DateTimeUtils.UNIT_SECONDS</li>
	 * </ul>
	 * @return true if date is expired, else false
	 * @throws Exception may throw exception if expPeriodUnit value is other than above mentioned values
	 */
	public static boolean isExpired (Date startDate, int expPeriod, int expPeriodUnit) throws Exception{
		
		boolean isExpired = false;
		
		long converter = 1;				// initialize converter
		
		switch (expPeriodUnit) {		// switch to given expiration unit and set appropriate converter
			case UNIT_YEARS:{			
				converter = MS_IN_YEAR; 
				break;
			}
			case UNIT_MONTHS:{
				converter = MS_IN_MONTH; 
				break;
			}
			case UNIT_WEEKS:{
				converter = MS_IN_WEEK; 
				break;
			}
			case UNIT_DAYS:{
				converter = MS_IN_DAY; 
				break;
			}
			case UNIT_HOURS:{
				converter = MS_IN_HOUR; 
				break;
			}
			case UNIT_MINUTES:{
				converter = MS_IN_MINUTE;
				break;
			}
			case UNIT_SECONDS:{
				converter = MS_IN_SECOND;
				break;
			}
			default: {		// if unit value is out of range then throw exception
				throw new Exception("Invalid Unit of Expiration Period");
			}
		}
		
		long expPeriodInMilis = expPeriod * converter;			// convert expiration time to milliseconds
		long timeDiffInMillis = getDateDifferenceInMillis(startDate, getCurrentDateTime());	// get difference in milliseconds
																							// between start date and current date
		// if time difference is greater than expiration time, then date expired 
		if(timeDiffInMillis >= expPeriodInMilis){
			isExpired = true;
		}
		
		return isExpired;
		
	}
	
	
	/**
	 * get date in "dd-MM-yyyy' at 'hh:mm a"
	 * @param date
	 * @return
	 */
	public static String getDateinSimpleStringFormate(Date date){
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy' at 'hh:mm a");  
		 String s = df.format(date); 
		 return s;
	}
	
	public static String getDateinSimpleFormate(Date date){
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy' at 'hh-mm a");  
		 String s = df.format(date); 
		 return s;
	}
	
	public static String getLastModifiedDate(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		 String s = df.format(date); 
		 return s;
	}
	
	/**
	 * this method is used to parse current date into sql timestamp
	 * @return currentTimestamp 
	 */
	public static Timestamp getCurrentDateInSQLCasting(){
		
		Date currentDate = getCurrentDateTime();
		
		Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
		
		return currentTimestamp;
	}
	
	/*
	 * toDateFromUKFormat() method converts string in the UK format "dd/mm/YYYY" to date
	 */
	public static Calendar toDateFromUKFormat(String strDate) 
		{
			Calendar calendar = Calendar.getInstance();
			String[] dateStr = strDate.split("/");
			calendar.set(Integer.parseInt(dateStr[2]), Integer
					.parseInt(dateStr[1])-1, Integer.parseInt(dateStr[0]), 0,0,0);
			return calendar;

		}
	
	/** 
	 * get provided date 'dd/MM/yyyy' in 'dd-MM-yyyy HH:mm:ss'
	 * @param dateInDDMMYYYY
	 * @return
	 */
	public static Date getConverDateToStandardPattern(String dateInDDMMYYYY){
		String currentDate="";
		
		Calendar cToDate = toDateFromUKFormat(dateInDDMMYYYY);
		
		Date dToDate = cToDate.getTime();
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
		
		currentDate = df.format(dToDate);
		
		try {
			dToDate = df.parse(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dToDate;
	}
	/**
	 * Conver to date picker formate i.e dd/MM/YYYY
	 * @param date
	 * @return
	 */
	public static String convertToDatePickerFormate(Date date){
		String datePickerFromer = "";
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
		
		datePickerFromer = df.format(date); 
		
		return datePickerFromer;
	}
	
	public static String changeDateFormat(String date){
		
		String temp[] = date.split("/");
		date = temp[2]+"-"+temp[1]+"-"+temp[0];
		
		return date;
	}
	
	public static String getCurrentDateInMySqlDateCasting(String str){
		
		String temp[] = str.split(" ");
		
		String temp1[] = temp[0].split("-");
		
		String date = temp1[2]+"-"+temp1[1]+"-"+temp1[0];
		
		return date;
	}
	
	public static void main(String arr[]) {
		
	
		//getConverDateToStandardPattern("18/07/2012");
		//Calendar cToDate = toDateFromUKFormat("18/07/2012");
		
		/*Date dToDate = new Date();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
		String s = df.format(dToDate); 
		
		System.out.println(s);
		System.out.println(dToDate.toString());
		System.out.println(dToDate);
		System.out.println(dToDate.toGMTString());
		System.out.println(dToDate.toLocaleString());*/
		
		int[] s = new int[5];
		
		for(int i=0 ; i <5 ; i++){
			s[i] = i;
		}
		System.out.println();
	}

	public static String getCommencingDate(String fromdate) {
		String temp[] = fromdate.split("/");
		String date = temp[2] + "-" + temp[1] + "-" + temp[0];
		return date;
	}
	
	public static String getCommencingDate1(String fromdate) {
		String temp[] = fromdate.split("-");
		String date = temp[2] + "/" + temp[1] + "/" + temp[0];
		return date;
	}
	
	public static String changeFormat(double x){

		NumberFormat formatter = new DecimalFormat("#0.00");     
		//System.out.println(formatter.format(z));
		return formatter.format(x);

}
	
	public static long getDiffofTwoDates(String date1,String date2){
		long ddays = 0;
		
		String temp[] = date1.split("-");
		String semp[] = date2.split("-");
		
		int y1 = Integer.parseInt(temp[0]);
		int m1 = Integer.parseInt(temp[1]);
		int d1 = Integer.parseInt(temp[2]);
		
		int y2 = Integer.parseInt(semp[0]);
		int m2 = Integer.parseInt(semp[1]);
		int d2 = Integer.parseInt(semp[2]);
		
		try{
			  Calendar calendar1 = Calendar.getInstance();
		      Calendar calendar2 = Calendar.getInstance();
		      calendar1.set(y1, m1, d1);
		      calendar2.set(y2, m2, d2);
		      long milsecs1= calendar1.getTimeInMillis();
		      long milsecs2 = calendar2.getTimeInMillis();
		      long diff = milsecs2 - milsecs1;
		      long dsecs = diff / 1000;
		      long dminutes = diff / (60 * 1000);
		      long dhours = diff / (60 * 60 * 1000);
		      ddays = diff / (24 * 60 * 60 * 1000);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ddays;
	}
}
