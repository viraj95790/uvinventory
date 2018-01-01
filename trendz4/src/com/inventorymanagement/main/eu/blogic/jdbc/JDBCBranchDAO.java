package com.inventorymanagement.main.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.inventorymanagement.main.common.constants.Constants;
import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.bi.ProductDAO;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.Stock;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.common.utils.Encryption;



public class JDBCBranchDAO extends JDBCBaseDAO implements BranchDAO {
	
	public JDBCBranchDAO(Connection connection){
		this.connection = connection;
		
	}

	public int saveBranch(Branch branch) {
		int result=0;
		PreparedStatement preparedStatement = null;
		String sql="insert into branch(companyname,userID,password,country,state,city,address,pincode,mobile,landline,email,userType) values(?,?,?,?,?,?,?,?,?,?,?,?) ";
		try{
			String encPassword = Encryption.encryptSHA(branch.getPassword());		// encrypt password before saving to database
			branch.setPassword(encPassword);
			
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, branch.getCompanyName());
			preparedStatement.setString(2, branch.getUserId());
			preparedStatement.setString(3, branch.getPassword());
			preparedStatement.setString(4, branch.getCountry());
			preparedStatement.setString(5, branch.getState());
			preparedStatement.setString(6, branch.getCity());
			preparedStatement.setString(7, branch.getAddress());
			preparedStatement.setString(8, branch.getPincode());
			preparedStatement.setString(9, branch.getMobileNo());
			preparedStatement.setString(10, branch.getLandLine());
			preparedStatement.setString(11, branch.getEmail());
			preparedStatement.setInt(12, branch.getUserType());
			
			preparedStatement.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Branch> getBranch() {
//ArrayList<EmployeeDetails> previousEmpList= new ArrayList<EmployeeDetails>();
		ArrayList<Branch> branchList = new ArrayList<Branch>();
		String sql = "select id,companyname,country,state,city,address,pincode,mobile,landline,email,userType from branch";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				Branch branch = new Branch();
				
				branch.setId(rs.getInt(1));
				branch.setCompanyName(rs.getString(2));
				branch.setCountry(rs.getString(3));
				branch.setState(rs.getString(4));
				branch.setCity(rs.getString(5));
				branch.setAddress(rs.getString(6));
				branch.setPincode(rs.getString(7));
				branch.setMobileNo(rs.getString(8));
				branch.setLandLine(rs.getString(9));
				branch.setEmail(rs.getString(10));
				branch.setUserType(rs.getInt(11));
				
				branchList.add(branch);
			
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		return branchList;
	}

	public Branch getBranch(int selectedid) {
		PreparedStatement preparedStatement = null;
		Branch branch=new Branch();
		String sql = "select * from branch where id=" +selectedid ;
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
			{
				branch.setId(rs.getInt("id"));
				branch.setUserId(rs.getString("userID"));
				branch.setBranchName(rs.getString("branchname"));
				
				
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return branch;
	}

	public int updateBranch(Branch branch, String userID) {
		int result=0;
		PreparedStatement preparedStatement = null;
		String sql = "update  branch set branchname=? where userID= '"+userID+"'";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, branch.getBranchName());
			
			
			preparedStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public int deleteBranch(int selectedid) {
		int result=0;
		try{
			String sql="delete from branch where id= " +selectedid;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result= preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public boolean isUserExist(String userid) {
		boolean result = false;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM branch where userID = ?";
		try{
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();

			if( resultSet != null && resultSet.next() ) {
    			int id = resultSet.getInt(1);
    			if(id > 0) {
    				result = true;
    			}
    		}
		 }catch(Exception ex){
		  ex.printStackTrace();
	   }finally{
			if(preparedStatement!=null)
				try {
					preparedStatement.close();
				} catch (Exception e) {
					e.printStackTrace();
			}
		}
		return result;
	}
	
	public Branch getuser(String userID) throws Exception {
		PreparedStatement preparedStatement = null;
		Branch branch = new Branch();
		String sql = "select id,userID,companyname,password,userType from branch where userID = ? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				branch.setId(rs.getInt(1));
				branch.setUserId(rs.getString(2));
				branch.setCompanyName(rs.getString(3));
				branch.setPassword(rs.getString(4));
				branch.setUserType(rs.getInt(5));
				
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
			
			return branch;
		}

	public boolean checkProductExist(Product product, String userId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM fantasy.subcategory where categoryid = "+product.getCategoryID()+" and subcategoryname = "+product.getProductName()+" and userid = ? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}

	public int updateExistBranchProduct(Product product) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update subcategory set quantity = ?,description = ?, remainingstock = ? where categoryid = ? and subcategoryname = ? and userid = ? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, product.getQuantity());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setInt(3, product.getQuantity());
			preparedStatement.setInt(4, product.getCategoryID());
			preparedStatement.setString(5, product.getProductName());
			preparedStatement.setString(6, product.getUserid());
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Product> getProductList(int categoryID) {
		PreparedStatement preparedStatement = null;
		ArrayList<Product>list = new ArrayList<Product>();
		StringBuffer sql = new StringBuffer();
		
		
		sql.append("select subcategoryname,status, ");
		sql.append("sum(case when branchid = '2' then remainingstock else 0 end) Navneet, ");
		sql.append("sum(case when branchid = '3' then remainingstock else 0 end) Sapna, ");
		sql.append("sum(case when branchid = '4' then remainingstock else 0 end) Bcm, ");
		sql.append("sum(case when branchid = '2' then quantity else 0 end) qNavneet, ");
		sql.append("sum(case when branchid = '3' then quantity else 0 end) qSapna, ");
		sql.append("sum(case when branchid = '4' then quantity else 0 end) qBcm ");
		
		sql.append(" from subcategory where categoryid = "+categoryID+" ");
		sql.append("group by subcategoryname ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			//ResultSet rs1 = preparedStatement.executeQuery();
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				Product product = new Product();
				int subcategoryID = rs.getInt(1);
				int status = rs.getInt(2);
				product.setDeliveredStatus(status);
				if(status == 0){
					product.setStatus(Constants.PENDING);
				}else{
					product.setStatus(Constants.DELIEVERED);
				}
				String prouctName = getProductName(subcategoryID);
				product.setSubCategoryID(subcategoryID);
				product.setProductName(prouctName);
				product.setN_stock(rs.getInt(6) - rs.getInt(3));
				product.setS_stock(rs.getInt(7)-rs.getInt(4));
				product.setB_stock(rs.getInt(8)-rs.getInt(5));
				
				product.setN_quantity(rs.getInt(6));
				product.setS_quantity(rs.getInt(7));
				product.setB_quantity(rs.getInt(8));
				
				
				int total = product.getN_stock()+product.getS_stock()+product.getB_stock();
				product.setTotal(total);
				
				list.add(product);
				
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return list;
	}
		
	private String getProductName(int subCategoryID) {
		PreparedStatement preparedStatement = null;
		String subCategoryName = "";
		String sql = "select productname from product where id = "+subCategoryID+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				subCategoryName = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return subCategoryName;
	}

	public int delieveredStatus(int categoryID, int subcategoryid, Stock stock)
			throws Exception {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update subcategory set remainingstock = ?, status = ? where categoryid = "+categoryID+" and subcategoryname = "+subcategoryid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, stock.getQuantity());
			preparedStatement.setInt(2, 1);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

	public ArrayList<Stock> getquantityList(int categoryID, int subcategoryid)
			throws Exception {
		PreparedStatement preparedStatement = null;
		ArrayList<Stock>list = new ArrayList<Stock>();
		int result = 0;
		String sql = "SELECT quantity, branchid  FROM subcategory  where categoryid = "+categoryID+" and subcategoryname = "+subcategoryid+" ";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Stock stock = new Stock();
				stock.setQuantity(rs.getInt(1));
				stock.setBranchID(rs.getInt(2));
				list.add(stock);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateDeliveredStatus(int categoryID, int subcategoryID)
			throws Exception {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update subcategory set status = 0 where categoryid = "+categoryID+" and subcategoryname = "+subcategoryID+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			 result = preparedStatement.executeUpdate();
			 
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean isEmployeeExist(String userid) {
		boolean result = false;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM salesman where userID = ?";
		try{
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();

			if( resultSet != null && resultSet.next() ) {
    			int id = resultSet.getInt(1);
    			if(id > 0) {
    				result = true;
    			}
    		}
		 }catch(Exception ex){
		  ex.printStackTrace();
	   }finally{
			if(preparedStatement!=null)
				try {
					preparedStatement.close();
				} catch (Exception e) {
					e.printStackTrace();
			}
		}
		return result;
	}

	public Branch editdata(String selectedid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		Branch branch = new Branch();
		try {
			String sql ="select companyname,country,state,city,address,pincode,mobile,landline,email from branch where id='"+selectedid+"'";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				branch.setId(Integer.parseInt(selectedid));
				branch.setCompanyName(rs.getString(1));
				branch.setCountry(rs.getString(2));
				branch.setState(rs.getString(3));
				branch.setCity(rs.getString(4));
				branch.setAddress(rs.getString(5));
				branch.setPincode(rs.getString(6));
				branch.setMobileNo(rs.getString(7));
				branch.setLandLine(rs.getString(8));
				branch.setEmail(rs.getString(9));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return branch;
	}

	public int updatedata(Branch branch) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		try {
			String sql ="update branch set companyname=?, country=?, state=?, city=?, address=?, pincode=?, mobile=?, landline=?, email=? where id=? ";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, branch.getCompanyName());
			preparedStatement.setString(2, branch.getCountry());
			preparedStatement.setString(3, branch.getState());
			preparedStatement.setString(4, branch.getCity());
			preparedStatement.setString(5, branch.getAddress());
			preparedStatement.setString(6, branch.getPincode());
			preparedStatement.setString(7, branch.getMobileNo());
			preparedStatement.setString(8, branch.getLandLine());
			preparedStatement.setString(9, branch.getEmail());
			preparedStatement.setInt(10, branch.getId());
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	public int deletedata(String selectedid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement=null;
		try {
			String sql ="delete from branch where id='"+selectedid+"' ";
			preparedStatement=connection.prepareStatement(sql);
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	public Branch getSelectedBranchData(String userid) {
		String sql = "select id,companyname,country,state,city,address,pincode,mobile,landline,email from branch where userid='"+userid+"' ";
		Branch branch = new Branch();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				
				
				branch.setId(rs.getInt(1));
				branch.setCompanyName(rs.getString(2));
				branch.setCountry(rs.getString(3));
				branch.setState(rs.getString(4));
				branch.setCity(rs.getString(5));
				branch.setAddress(rs.getString(6));
				branch.setPincode(rs.getString(7));
				branch.setMobileNo(rs.getString(8));
				branch.setLandLine(rs.getString(9));
				branch.setEmail(rs.getString(10));
				
				
			
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return branch;
	}

	public String getdate() {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		String result = null;
		try {
			String sql = "select afterdate from initiate";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
				
			}
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Branch> getUserList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Branch>list = new ArrayList<Branch>();
		String sql = "SELECT userid,companyname,id FROM branch ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Branch branch = new Branch();
				branch.setUserId(rs.getString(1));
				branch.setCompanyName(rs.getString(2));
				branch.setId(rs.getInt(3));
				
				list.add(branch);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	

}
