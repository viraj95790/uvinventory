package com.inventorymanagement.main.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;




import com.inventorymanagement.main.eu.bi.CustomerDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCCustomerDAO;
import com.inventorymanagement.main.eu.entity.Customer;
import com.inventorymanagement.main.eu.entity.Master;
import com.inventorymanagement.main.eu.entity.SendSms;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.CustomerForm;
import com.inventorymanagement.main.web.form.MasterForm;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class SendsmsAction extends BaseAction implements ModelDriven<MasterForm> ,Preparable{

	MasterForm masterForm=new MasterForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	
	LoginInfo loginInfo=LoginHelper.getLoginInfo(request);
	
	public MasterForm getModel() {
		// TODO Auto-generated method stub
		return masterForm;
	}

	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		Connection connection=null;
		
		try {
			/*connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			SMSTemplateDAO templateDAO=new JDBCSMSTemplateDao(connection);
			ArrayList<EmailTemplate> smsTemplateList=templateDAO.getAllSMSTemplates();
			masterForm.setSmsTemplateList(smsTemplateList);*/
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			
		}
		
		return super.execute();
	}
	
	public String importfile() throws Exception {
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			File fileUser=masterForm.getUserFile();
			String userFileName=masterForm.getUserFileFileName();
			ArrayList<Master> listContacts=new ArrayList<Master>();
			
			String filepath = request.getRealPath("/liveData/doc/");
			
			if(masterForm.getUserFileContentType()!=null){
			
						File fileToCreate = new File(filepath,userFileName);
						
						FileUtils.copyFile(fileUser, fileToCreate);		
						
						
					    FileInputStream file = new FileInputStream(fileToCreate);
			            XSSFWorkbook workbook = new XSSFWorkbook(file);
			            XSSFSheet sheet = workbook.getSheetAt(0);
			            Iterator<Row> rowIterator = sheet.iterator();
			            int i=0;
			            while (rowIterator.hasNext()) 
			            {
			                Row row = rowIterator.next();
			                //For each row, iterate through all the columns
			                Iterator<Cell> cellIterator = row.cellIterator();
			                 
			                Master master=new Master();
			                while (cellIterator.hasNext()) 
			                {
			                    
			                    Cell cell = cellIterator.next();
			                    //Check the cell type and format accordingly
			                    switch (cell.getCellType()) 
			                    {
			                          
			                    
			                        case Cell.CELL_TYPE_STRING:
			                             //System.out.print(cell.getStringCellValue() + "t");
			                        	 String name=cell.getStringCellValue();
			                        	 master.setName(name);
			                             break;
			                        case Cell.CELL_TYPE_NUMERIC:
			                            //System.out.print(cell.getNumericCellValue() + "t");
			                        	 Double mob=cell.getNumericCellValue();
			                        	 String result=String.valueOf(mob);
			                        	 String str=result.substring(0,11);
			                        	 String phone=str.replace(".","");
			                        	 master.setPhone(phone);
			                             break;
			                    }
			                }
			                
			                if(i!=0){
			                  listContacts.add(master);
			                }
			                i++;
			            }
			            
			            file.close();
            
			            masterForm.setListContacts(listContacts);
			            String smsTemplate=masterForm.getSmsTemplate();
			            masterForm.setSmsTemplate(smsTemplate);
			            String subject=masterForm.getSubject();
			            masterForm.setSubject(subject);
			            String smstxt=masterForm.getSmstxt();
			            masterForm.setSmstxt(smstxt);
			            
		   }  
			
			masterForm.setUserFile(fileUser);
			masterForm.setUserFileFileName(userFileName);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return execute();
	}

	
	public String sendmulti()throws Exception {
		
		try {
			
			String smstxt=request.getParameter("smstxt");
			String numbers=request.getParameter("numbers");
			
			if(numbers!=null){
				
				 SendSms s = new SendSms();
				 
				      for(String mobile:numbers.split(",")){
				    	  
					    	s.send(smstxt, mobile);
				    	  
				      }
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		 response.setContentType("text/html");
		 response.setHeader("Cache-Control", "no-cache");
		 response.getWriter().write("");
		
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	public void prepare() throws Exception {
		Connection connection = null;
		CustomerForm customerForm = new CustomerForm();
		try{
			connection = Connection_provider.getconnection();
			CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
			
			ArrayList<Master> listContacts=new ArrayList<Master>();
			masterForm.setListContacts(listContacts);
			
			
			
			ArrayList<Customer> custoArrayList = customerDAO.getdata(customerForm.getFilterusertype(),customerForm.getSearchText());
			masterForm.setCustomerList(custoArrayList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}
