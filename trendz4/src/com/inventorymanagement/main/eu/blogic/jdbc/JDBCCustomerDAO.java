package com.inventorymanagement.main.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.inventorymanagement.main.eu.bi.CustomerDAO;
import com.inventorymanagement.main.eu.entity.Customer;

public class JDBCCustomerDAO extends JDBCBaseDAO implements CustomerDAO {

	public JDBCCustomerDAO(Connection connection) {
		this.connection = connection;
	}

	public int insertdata(Customer customer) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		try {
			String sql = "insert into customer_form(vendorid, title, name, middlename, surname, company, address1, address2, city, state, zipcode, homephone, mob, office, email, fax, webpage, birthday, passby, newspaper, internet, flyer, friends_reference, posters, yellow_page, other, customer_type, usertype) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, customer.getVendorid());
			preparedStatement.setString(2, customer.getTitle());
			preparedStatement.setString(3, customer.getName());
			preparedStatement.setString(4, customer.getMiddlename());
			preparedStatement.setString(5, customer.getSurname());
			preparedStatement.setString(6, customer.getCompany());
			preparedStatement.setString(7, customer.getAddress1());
			preparedStatement.setString(8, customer.getAddress2());
			preparedStatement.setString(9, customer.getCity());
			preparedStatement.setString(10, customer.getState());
			preparedStatement.setString(11, customer.getZipcode());
			preparedStatement.setString(12, customer.getHomephone());
			preparedStatement.setString(13, customer.getMob());
			preparedStatement.setString(14, customer.getOffice());
			preparedStatement.setString(15, customer.getEmail());
			preparedStatement.setString(16, customer.getFax());
			preparedStatement.setString(17, customer.getWebpage());
			preparedStatement.setString(18, customer.getBirthday());
			preparedStatement.setString(19, customer.getPassby());
			preparedStatement.setString(20, customer.getNewspaper());
			preparedStatement.setString(21, customer.getInternet());
			preparedStatement.setString(22, customer.getFlyer());
			preparedStatement.setString(23, customer.getFriends_reference());
			preparedStatement.setString(24, customer.getPosters());
			preparedStatement.setString(25, customer.getYellow_page());
			preparedStatement.setString(26, customer.getOther());
			preparedStatement.setString(27, customer.getCustomer_type());
			preparedStatement.setString(28, customer.getUsertype());

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	
	
	 public  boolean areAllNumericWayThree(String id){
	        String regex = "[0-9]+";
	        boolean b = id.matches(regex);
	        return b;
	    }
	 
	 
	public ArrayList<Customer> getdata(String filtercustomertype,String searchtext) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ArrayList<Customer> cList = new ArrayList<Customer>();
		 String sql = "";
		 
		 
		 if(filtercustomertype.equals("0")){
			 sql = "select id,vendorid,title,name,middlename,surname,company,address1,address2,city,state,zipcode,homephone,mob,office,email,fax,webpage,birthday,passby,newspaper,internet,flyer,friends_reference,posters,yellow_page,other,customer_type,usertype from customer_form order by id desc limit 0,20 ";
		 }else if(filtercustomertype.equals("1")){
			 sql = "select id,vendorid,title,name,middlename,surname,company,address1,address2,city,state,zipcode,homephone,mob,office,email,fax,webpage,birthday,passby,newspaper,internet,flyer,friends_reference,posters,yellow_page,other,customer_type,usertype from customer_form where usertype='0' order by id desc limit 0,20 ";
		 }else{
			 sql = "select id,vendorid,title,name,middlename,surname,company,address1,address2,city,state,zipcode,homephone,mob,office,email,fax,webpage,birthday,passby,newspaper,internet,flyer,friends_reference,posters,yellow_page,other,customer_type,usertype from customer_form where usertype='1' order by id desc limit 0,20 ";
		 }
		 
		
		 if(!searchtext.equals("")){
			 if(areAllNumericWayThree(searchtext)){
				 sql = "select id,vendorid,title,name,middlename,surname,company,address1,address2,city,state,zipcode,homephone,mob,office,email,fax,webpage,birthday,passby,newspaper,internet,flyer,friends_reference,posters,yellow_page,other,customer_type,usertype from customer_form where mob like('%"+searchtext+"%') "; 
			 }else{
				 sql = "select id,vendorid,title,name,middlename,surname,company,address1,address2,city,state,zipcode,homephone,mob,office,email,fax,webpage,birthday,passby,newspaper,internet,flyer,friends_reference,posters,yellow_page,other,customer_type,usertype from customer_form where name like('%"+searchtext+"%')  ";
			 }
			 
			 
		 }
		
		try {
		    preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();

				customer.setId(rs.getInt(1));
				customer.setVendorid(rs.getString(2));
				customer.setTitle(rs.getString(3));
				customer.setName(rs.getString(4));
				customer.setMiddlename(rs.getString(5));
				customer.setSurname(rs.getString(6));
				customer.setCompany(rs.getString(7));
				customer.setAddress1(rs.getString(8));
				customer.setAddress2(rs.getString(9));
				customer.setCity(rs.getString(10));
				customer.setState(rs.getString(11));
				customer.setZipcode(rs.getString(12));
				customer.setHomephone(rs.getString(13));
				customer.setMob(rs.getString(14));
				customer.setOffice(rs.getString(15));
				customer.setEmail(rs.getString(16));
				customer.setFax(rs.getString(17));
				customer.setWebpage(rs.getString(18));
				customer.setBirthday(rs.getString(19));
				customer.setPassby(rs.getString(20));
				customer.setNewspaper(rs.getString(21));
				customer.setInternet(rs.getString(22));
				customer.setFlyer(rs.getString(23));
				customer.setFriends_reference(rs.getString(24));
				customer.setPosters(rs.getString(25));
				customer.setYellow_page(rs.getString(26));
				customer.setOther(rs.getString(27));
				customer.setCustomer_type(rs.getString(28));
				customer.setUsertype(rs.getString(29));

				cList.add(customer);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return cList;
	}

	public Customer editdata(String selectedid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		Customer customer = new Customer();
		try {
			String sql = "select vendorid,title,name,middlename,surname,company,address1,address2,city,state,zipcode,homephone,mob,office,email,fax,webpage,birthday,passby,newspaper,internet,flyer,friends_reference,posters,yellow_page,other,customer_type,usertype from customer_form where id='"+selectedid+"'";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				customer.setId(Integer.parseInt(selectedid));
				customer.setVendorid(rs.getString(1));
				customer.setTitle(rs.getString(2));
				customer.setName(rs.getString(3));
				customer.setMiddlename(rs.getString(4));
				
				if(rs.getString(5)!=null){
					customer.setSurname(rs.getString(5));
				}else{
					customer.setSurname("");
				}
				
				customer.setCompany(rs.getString(6));
				customer.setAddress1(rs.getString(7));
				customer.setAddress2(rs.getString(8));
				customer.setCity(rs.getString(9));
				customer.setState(rs.getString(10));
				customer.setZipcode(rs.getString(11));
				customer.setHomephone(rs.getString(12));
				customer.setMob(rs.getString(13));
				customer.setOffice(rs.getString(14));
				customer.setEmail(rs.getString(15));
				customer.setFax(rs.getString(16));
				customer.setWebpage(rs.getString(17));
				customer.setBirthday(rs.getString(18));
				customer.setPassby(rs.getString(19));
				customer.setNewspaper(rs.getString(20));
				customer.setInternet(rs.getString(21));
				customer.setFlyer(rs.getString(22));
				customer.setFriends_reference(rs.getString(23));
				customer.setPosters(rs.getString(24));
				customer.setYellow_page(rs.getString(25));
				customer.setOther(rs.getString(26));
				customer.setCustomer_type(rs.getString(27));
				customer.setUsertype(rs.getString(28));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return customer;
	}

	public int updatedata(Customer customer) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		try {
			String sql ="update customer_form set vendorid=?, title=?, name=?, middlename=?, surname=?, company=?, address1=?, address2=?, city=?, state=?, zipcode=?, homephone=?, mob=?, office=?, email=?, fax=?, webpage=?, birthday=?, passby=?, newspaper=?, internet=?, flyer=?, friends_reference=?, posters=?, yellow_page=?, other=?, customer_type=? where id=? ";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, customer.getVendorid());
			preparedStatement.setString(2, customer.getTitle());
			preparedStatement.setString(3, customer.getName());
			preparedStatement.setString(4, customer.getMiddlename());
			preparedStatement.setString(5, customer.getSurname());
			preparedStatement.setString(6, customer.getCompany()); 
			preparedStatement.setString(7, customer.getAddress1());
			preparedStatement.setString(8, customer.getAddress2());
			preparedStatement.setString(9, customer.getCity());
			preparedStatement.setString(10, customer.getState());
			preparedStatement.setString(11, customer.getZipcode());
			preparedStatement.setString(12, customer.getHomephone());
			preparedStatement.setString(13, customer.getMob());
			preparedStatement.setString(14, customer.getOffice());
			preparedStatement.setString(15, customer.getEmail());
			preparedStatement.setString(16, customer.getFax());
			preparedStatement.setString(17, customer.getWebpage());
			preparedStatement.setString(18, customer.getBirthday());
			preparedStatement.setString(19, customer.getPassby());
			preparedStatement.setString(20, customer.getNewspaper());
			preparedStatement.setString(21, customer.getInternet());
			preparedStatement.setString(22, customer.getFlyer());
			preparedStatement.setString(23, customer.getFriends_reference());
			preparedStatement.setString(24, customer.getPosters());
			preparedStatement.setString(25, customer.getYellow_page());
			preparedStatement.setString(26, customer.getOther());
			preparedStatement.setString(27, customer.getCustomer_type());
			preparedStatement.setInt(28, customer.getId());
			
			preparedStatement.executeUpdate();

			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	public int getdelet(String selectedid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		try {
			String sql = "delete from customer_form where id='"+selectedid+"'";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
}
