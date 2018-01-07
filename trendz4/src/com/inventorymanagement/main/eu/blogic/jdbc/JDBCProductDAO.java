package com.inventorymanagement.main.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.common.utils.Pagination;
import com.inventorymanagement.main.eu.bi.BranchDAO;
import com.inventorymanagement.main.eu.bi.ProductDAO;
import com.inventorymanagement.main.eu.entity.Branch;
import com.inventorymanagement.main.eu.entity.Category;
import com.inventorymanagement.main.eu.entity.Product;
import com.inventorymanagement.main.eu.entity.RemainStock;
import com.inventorymanagement.main.eu.entity.Size;
import com.inventorymanagement.main.eu.entity.Stock;
import com.inventorymanagement.main.eu.entity.StockProduct;

public class JDBCProductDAO extends JDBCBaseDAO implements ProductDAO{
	
	public JDBCProductDAO(Connection connection){
		this.connection = connection;
	}

	
	public ArrayList<Category> getCategoryList(String userId) throws Exception {
		PreparedStatement preparedStatement = null;
		ArrayList<Category>list = new ArrayList<Category>();
		String sql = "select id,categoryname,description,lastupdated from category where userID = ? or userID = 'administrator'";
		

		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				Category category = new Category();
				category.setId(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setDescription(rs.getString(3));
				category.setLastupdated(DateTimeUtils.getDateinSimpleFormate(rs.getTimestamp(4)));
				
				list.add(category);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			return list;
	}


	
	public int saveProduct(Product product) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into subcategory(categoryid,subcategoryname,description,modelnumber,articlenumber,size,color,lastupdated,quantity,userid,imagename) values(?,?,?,?,?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, product.getCategoryID());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setString(4, product.getModelNumber());
			preparedStatement.setString(5, product.getArticleNumber());
			preparedStatement.setString(6, product.getSize());
			preparedStatement.setString(7, product.getColor());
			preparedStatement.setTimestamp(8, DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setInt(9, 0);
			preparedStatement.setString(10, product.getUserid());
			preparedStatement.setString(11, product.getUploadedimage());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	
	/*public int getProductCount(String userId) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from subcategory where userid = ? and quantity!=0 ";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				
				result = rs.getInt(1);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	
	public ArrayList<Product> getProductList(String userId,
		Pagination pagination) throws Exception {
		PreparedStatement preparedStatement = null;
		ArrayList<Product>list = new ArrayList<Product>();
		String sql = "select id,categoryid,subcategoryname,description,lastupdated,quantity,remainingstock,userid,price from subcategory where userid = ? and quantity!=0";
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setCategoryID(rs.getInt(2));
				String categoryName = getCategoryName(product.getCategoryID());
				product.setCategoryName(categoryName);
				int suCategoryID = Integer.parseInt(rs.getString(3));
				String productName = getProductName(suCategoryID);
				product.setProductName(productName);
				product.setDescription(rs.getString(4));
				product.setLastUpdated(DateTimeUtils.getDateinSimpleFormate(rs.getTimestamp(5)));
				product.setQuantity(rs.getInt(6));
				product.setRemaioningStock(rs.getInt(7));
				String branchName = getBranchName(rs.getString(8));
				product.setBranchName(branchName);
				product.setPrice(rs.getDouble(9));
				list.add(product);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}*/


	private String getCategoryName(int categoryID) {
	PreparedStatement preparedStatement = null;
	String result = "";
	String sql = "select categoryname from category where id = "+categoryID+" ";
	try{
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			result = rs.getString(1);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
		return result;
	}


	
	public int updateStock(String stockValue, String selectedID)
			throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update subcategory set remainingstock = ? where id  = ? ";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(stockValue));
			preparedStatement.setInt(2, Integer.parseInt(selectedID));
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	
	public int getRemainStock(String selectedID) throws Exception {
	PreparedStatement preparedStatement = null;
	int result = 0;
	String sql = "select remainingstock from subcategory where id = ?";
	try{
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, Integer.parseInt(selectedID));
		
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			result = rs.getInt(1);
		}
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
		return result;
	}


	public Product getProduct(int selectedid) {
		PreparedStatement preparedStatement = null;
		Product product =new Product();
		//String sql = "select * from subcategory where id=" +selectedid ;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT subcategory.id, subcategory.categoryid,subcategoryname,subcategory.description, ");
		sql.append("modelnumber,articlenumber,size,color,subcategory.lastupdated,quantity,imagename,category.gender,subcategory.lastupdated ");
		sql.append("FROM subcategory inner join product on product.id = subcategory.subcategoryname ");
		sql.append("inner join category on category.id = product.categoryid ");
		sql.append("and subcategory.id = "+selectedid+" ");
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
			{
				product.setId(rs.getInt(1));
				product.setCategoryID(rs.getInt(2));
				product.setProductName(rs.getString(3));
				product.setDescription(rs.getString(4));
				product.setModelNumber(rs.getString(5));
				product.setArticleNumber(rs.getString(6));
				product.setSize(rs.getString(7));
				product.setColor(rs.getString(8));
				product.setLastUpdated(rs.getString(9));
				product.setQuantity(rs.getInt(10));
				product.setUploadedimage(rs.getString(11));
				product.setGender(rs.getInt(12));
				product.setBarcodedate(DateTimeUtils.getCommencingDate1(rs.getString(13)));
				
				
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return product;
	}

	public int getProductCount(String searchText,String userid,String articleNumber) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		if(!searchText.equals("") ){
			sql.append("select count(*) from product ");
			sql.append("inner join category on product.categoryid = category.id ");
			sql.append("inner join subcategory on subcategory.subcategoryname = product.id ");
			sql.append("where product.productname like('%"+searchText+"%') and category.userid = ? ");
		}
		else if(!articleNumber.equals("")){
			sql.append("select count(*) from product ");
			sql.append("inner join category on product.categoryid = category.id ");
			sql.append("inner join subcategory on subcategory.subcategoryname = product.id ");
			sql.append("where subcategory.articlenumber like('%"+articleNumber+"%') and category.userid = ? ");
		}
		
		else if(!searchText.equals("") && !articleNumber.equals("")){
			sql.append("select count(*) from product ");
			sql.append("inner join category on product.categoryid = category.id ");
			sql.append("inner join subcategory on subcategory.subcategoryname = product.id ");
			sql.append("where product.productname like('%"+searchText+"%') and subcategory.articlenumber like('%"+articleNumber+"%') and category.userid = ? ");
		}
		
		else{
			sql.append("select count(*) from product ");
			sql.append("inner join category on product.categoryid = category.id ");
			sql.append("inner join subcategory on subcategory.subcategoryname = product.id ");
			sql.append("where category.userid = ? ");
		}
		
		//String sql = "select count(*) from subcategory where quantity!=0";
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, userid);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				
				result = rs.getInt(1);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Product> getProductList(Pagination pagination,String searchText,String userid,String articleNumber)
			throws Exception {
		PreparedStatement preparedStatement = null;
		ArrayList<Product>list = new ArrayList<Product>();
		StringBuffer sql = new StringBuffer();
		//String sql = "select id,categoryid,subcategoryname,description,lastupdated,quantity,remainingstock,userid,price from subcategory  where quantity!=0";
		if(!searchText.equals("")){
			sql.append("select subcategory.id,subcategory.categoryid,subcategoryname,subcategory.description,subcategory.lastupdated,quantity,size,color,subcategory.userid from product ");
			sql.append("inner join category on product.categoryid = category.id ");
			sql.append("inner join subcategory on subcategory.subcategoryname = product.id ");
			sql.append("where product.productname like('%"+searchText+"%') and category.userid = ? ");
		}
		else if(!articleNumber.equals("")){
			sql.append("select subcategory.id,subcategory.categoryid,subcategoryname,subcategory.description,subcategory.lastupdated,quantity,size,color,subcategory.userid from product ");
			sql.append("inner join category on product.categoryid = category.id ");
			sql.append("inner join subcategory on subcategory.subcategoryname = product.id ");
			sql.append("where subcategory.articlenumber like('%"+articleNumber+"%') and category.userid = ? ");
		}
		else if(!searchText.equals("") && !articleNumber.equals("")){
			sql.append("select subcategory.id,subcategory.categoryid,subcategoryname,subcategory.description,subcategory.lastupdated,quantity,size,color,subcategory.userid from product ");
			sql.append("inner join category on product.categoryid = category.id ");
			sql.append("inner join subcategory on subcategory.subcategoryname = product.id ");
			sql.append("where product.productname like('%"+searchText+"%') and subcategory.articlenumber like('%"+articleNumber+"%')  and category.userid = ? ");
		}
		else{
			sql.append("select subcategory.id,subcategory.categoryid,subcategoryname,subcategory.description,subcategory.lastupdated,quantity,size,color,subcategory.userid from product ");
			sql.append("inner join category on product.categoryid = category.id ");
			sql.append("inner join subcategory on subcategory.subcategoryname = product.id ");
			sql.append("where category.userid = ? ");
		}
		
		
		String str = pagination.getSQLQuery(sql.toString());
		try{
			preparedStatement = connection.prepareStatement(str);
			preparedStatement.setString(1, userid);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setCategoryID(rs.getInt(2));
				String categoryName = getCategoryName(product.getCategoryID());
				product.setCategoryName(categoryName);
				int suCategoryID = Integer.parseInt(rs.getString(3));
				product.setSubCategoryID(suCategoryID);
				String productName = getProductName(suCategoryID);
				product.setProductName(productName);
				product.setDescription(rs.getString(4));
				product.setLastUpdated(DateTimeUtils.getDateinSimpleFormate(rs.getTimestamp(5)));
				product.setQuantity(rs.getInt(6));
				product.setSize(rs.getString(7));
				product.setColor(rs.getString(8));
				
				/*String branchName = getBranchName(rs.getString(8));
				product.setBranchName(branchName);*/
				//product.setPrice(rs.getDouble(9));
				
				list.add(product);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}


	private String getBranchName(String userID) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select branchname from branch where userid = ? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public int getProductCountByBranch(String selectedBranch,String searchText) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		//String sql = "select count(*) from subcategory where branchid = ? and quantity != 0 ";
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) ");
		sql.append("inner join category on product.categoryid = category.id ");
		sql.append("inner join subcategory on subcategory.subcategoryname = product.id ");
		sql.append("inner join branch on subcategory.branchid = branch.id ");
		sql.append("where product.productname like('%"+searchText+"%') and subcategory.branchid = ? ");
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, selectedBranch);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				
				result = rs.getInt(1);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Product> getProductListByBranch(String selectedBranch,
			Pagination pagination,String searchText) throws Exception {
		PreparedStatement preparedStatement = null;
		ArrayList<Product>list = new ArrayList<Product>();
		//String sql = "select id,categoryid,subcategoryname,description,lastupdated,quantity,remainingstock,userid,price from subcategory where branchid = ? and quantity!=0 ";
		StringBuffer sql = new StringBuffer();
		sql.append("select subcategory.id,subcategory.categoryid,subcategoryname,subcategory.description,subcategory.lastupdated,quantity,remainingstock,subcategory.userid,subcategory.price from product ");
		sql.append("inner join category on product.categoryid = category.id ");
		sql.append("inner join subcategory on subcategory.subcategoryname = product.id ");
		sql.append("inner join branch on subcategory.branchid = branch.id ");
		sql.append("where product.productname like('%"+searchText+"%') and subcategory.branchid = ? ");
		
		String str = pagination.getSQLQuery(sql.toString());
		try{
			preparedStatement = connection.prepareStatement(str);
			preparedStatement.setString(1, selectedBranch);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setCategoryID(rs.getInt(2));
				String categoryName = getCategoryName(product.getCategoryID());
				product.setCategoryName(categoryName);
				int suCategoryID = Integer.parseInt(rs.getString(3));
				String productName = getProductName(suCategoryID);
				product.setProductName(productName);
				product.setDescription(rs.getString(4));
				product.setLastUpdated(DateTimeUtils.getDateinSimpleFormate(rs.getTimestamp(5)));
				product.setQuantity(rs.getInt(6));
				int remainstock = product.getQuantity() - rs.getInt(7);
				product.setRemaioningStock(remainstock);
				String branchName = getBranchName(rs.getString(8));
				product.setBranchName(branchName);
				product.setPrice(rs.getDouble(9));
				list.add(product);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;

	}
	
	public int updateCategory(Product product, int selectedid) {
		int result=0;
		PreparedStatement preparedStatement = null;
		String sql = "update  subcategory set categoryid=?,subcategoryname=?,description=?,modelnumber=?,articlenumber=?,color=?,lastupdated=?,imagename=? where id= "+selectedid;
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, product.getCategoryID());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setString(4, product.getModelNumber());
			preparedStatement.setString(5, product.getArticleNumber());
			preparedStatement.setString(6, product.getColor());
			preparedStatement.setTimestamp(7,DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setString(8, product.getUploadedimage());
			
			
			preparedStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}


	public int deleteProduct(int selectedid) {
		int result=0;
		try{
			String sql="delete from subcategory where id= " +selectedid;
			//String sql = "update subcategory set quantity = ?, price = ?,remainingstock = ? where id = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			result= preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public ArrayList<Product> getProductList(String userId,
			int categoryID) throws Exception {
			PreparedStatement preparedStatement = null;
			ArrayList<Product>list = new ArrayList<Product>();
			String sql = "select id,categoryid,subcategoryname,description,lastupdated,quantity,remainingstock,userid,price from subcategory where userid = ?  and categoryid = "+categoryID+"  and quantity!=0";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, userId);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt(1));
					product.setCategoryID(rs.getInt(2));
					String categoryName = getCategoryName(product.getCategoryID());
					product.setCategoryName(categoryName);
					int subCategoryID = Integer.parseInt(rs.getString(3));
					String productName = getProductName(subCategoryID);
					product.setProductName(productName);
					product.setDescription(rs.getString(4));
					product.setLastUpdated(DateTimeUtils.getDateinSimpleFormate(rs.getTimestamp(5)));
					product.setQuantity(rs.getInt(6));
					product.setRemaioningStock(rs.getInt(7));
					String branchName = getBranchName(rs.getString(8));
					product.setBranchName(branchName);
					product.setPrice(rs.getDouble(9));
					
					int productionStock = product.getQuantity() - product.getRemaioningStock();
					
					product.setProductionStock(productionStock);
					
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


	public ArrayList<Category> getAdminCategoryList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Category>list = new ArrayList<Category>();
		String sql = "select id,categoryname,description,lastupdated from category ";
		

		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				Category category = new Category();
				category.setId(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setDescription(rs.getString(3));
				category.setLastupdated(DateTimeUtils.getDateinSimpleFormate(rs.getTimestamp(4)));
				
				list.add(category);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			return list;
	}


	public ArrayList<RemainStock> getAdminProductList(int categoryID,int branchid)
			throws Exception {
		PreparedStatement preparedStatement = null;
		ArrayList<RemainStock>list = new ArrayList<RemainStock>();
	
		//String sql = "select id,categoryid,subcategoryname,description,lastupdated,quantity,remainingstock,userid from subcategory where  categoryid = "+categoryID+" and branchid = "+branchid+" ";
		
		String sql = "select remainingstock from subcategory where  categoryid = "+categoryID+" and branchid = "+branchid+" ";
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				RemainStock remainStock = new RemainStock();
				
				remainStock.setRemainStock(rs.getInt(1));
				
				
				list.add(remainStock);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}


	public ArrayList<Product> getSubCategoryList() throws Exception {
		PreparedStatement preparedStatement = null;
		ArrayList<Product>list = new ArrayList<Product>();
		String sql = "select id,productname from product";;
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				
				list.add(product);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public int saveSubCategory(Product product) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into product(categoryid,productname,price,profitmargin,cgst,sgst,isgst,purchase) values(?,?,?,?,?,?,?,?)";
		int prodprice = 0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, product.getCategoryID());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setString(3, product.getSaleprice());
			preparedStatement.setString(4, product.getProfitmargin());
			preparedStatement.setString(5, product.getCgst());
			preparedStatement.setString(6, product.getSgst());
			preparedStatement.setBoolean(7, product.isIegst());
			preparedStatement.setDouble(8, product.getPrice() );
			
			
			
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					prodprice = resultSet.getInt(1);  
				}
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return prodprice;
	}


	public ArrayList<Product> getAjaxProductList(int categoryID)
			throws Exception {
		PreparedStatement preparedStatement = null;
		ArrayList<Product>list = new ArrayList<Product>();
		String sql = "select id,productname from product where categoryid = "+categoryID+" ";;
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				
				list.add(product);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Stock> getBranchStockList(int categoryID,int subCategoryID,int branchid) {
		PreparedStatement preparedStatement = null;
		
		ArrayList<Stock>list = new ArrayList<Stock>();
		String sql = "SELECT userid,remainingstock,subcategoryname FROM  subcategory where categoryid = ?  and subcategoryname = ? and branchid = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, categoryID);
			preparedStatement.setInt(2, subCategoryID);
			preparedStatement.setInt(3, branchid);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Stock stock = new Stock();
				stock.setUserID(rs.getString(1));
				stock.setRemainingStock(rs.getInt(2));
				int suCategoryID = Integer.parseInt(rs.getString(3));
				String productName = getProductName(suCategoryID);
				stock.setProductName(productName);
				
				
				list.add(stock);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Product> getBranchWiseProductList(int branchid,
			int categoryid) throws Exception {
		PreparedStatement preparedStatement = null;
		ArrayList<Product>list = new ArrayList<Product>();
		String sql = "select subcategoryname,userid from subcategory where categoryid = "+categoryid+" and branchid = "+branchid+"  ";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setSubCategoryID(rs.getInt(1));
				product.setUserid(rs.getString(2));
				list.add(product);			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<StockProduct> getProductListByCategory(int categoryid) throws Exception {
		PreparedStatement preparedStatement = null;
		ArrayList<StockProduct>list = new ArrayList<StockProduct>();
		/*StringBuffer sql = new StringBuffer();
		sql.append("select subcategoryname from subcategory ");
		sql.append("inner join category on category.id = subcategory.categoryid ");
		sql.append("where subcategory.categoryid = "+categoryid+" group by  subcategory.subcategoryname ");*/
		String sql = "select distinct subcategoryname from subcategory where categoryid = "+categoryid+" ";
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				StockProduct stock = new StockProduct();
				int suCategoryID = Integer.parseInt(rs.getString(1));
				String productName = getProductName(suCategoryID);
				stock.setProductName(productName);
				list.add(stock);			
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public boolean isProductExist(String productName, int selectedCategoryID) {
		boolean result = false;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT productname FROM product where categoryid = "+selectedCategoryID+" AND productname = '"+productName+"'";
		try{
			preparedStatement = connection.prepareStatement(query);
			
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


	public int updateQuantity(String selectedID, String quantity)
			throws Exception {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update subcategory set quantity = ? where id = "+selectedID+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(quantity));
			
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public String getUploadedImage(int selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select imagename from subcategory where id = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}


	public ArrayList<Size> getSizeList(int productid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Size>list = new ArrayList<Size>();
		String sql = "SELECT id,size,color FROM p_size where productid="+productid+" order by size ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Size size = new Size();
				size.setId(rs.getInt(1));
				size.setSizeValue(rs.getString(2));
				size.setColorName(rs.getString(3));
				
				list.add(size);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


	public Size getP_Size(int selectedid) {
		PreparedStatement preparedStatement = null;
		Size size = new Size();
		String sql = "SELECT productid,size,color,id FROM p_size where id = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				size.setProductID(rs.getInt(1));
				size.setSizeValue(rs.getString(2));
				size.setColorName(rs.getString(3));
				size.setId(rs.getInt(4));
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return size;
	}


	public int getGender(int productid) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT gender from category inner join subcategory ");
		sql.append("on category.id = subcategory.categoryid and subcategoryname= "+productid+" ");
				
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int getActualSize(int gender, int sizeValue) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT actualsize FROM gents_ladies where commonsize="+sizeValue+" and gender = "+gender+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public int updateP_size(Size size) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update p_size set size=?,color=?,actualsize=? where id = ?";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, size.getSizeValue());
			preparedStatement.setString(2, size.getColorName());
			preparedStatement.setString(3, size.getActualsize());
			preparedStatement.setInt(4, size.getId());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public int deleteProductSize(int selectedid) {
		PreparedStatement preparedStatement  = null;
		int result=  0;
		String sql = "delete from p_size where id = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql) ;
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int updateCurrentQuantity(int selectedProductID,int currentQuantity) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update subcategory set quantity = "+currentQuantity+" where id = "+selectedProductID+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	public int getSubCategoryID(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT subcategoryname FROM subcategory where id = "+selectedid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int deletePsize(int subcategoryID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from p_size where productid="+subcategoryID+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int saveShopStock(String shopid, String qty,String prodid,String subcateoryid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into shop_stock(shopid, stock, prodid, subcateoryid) values(?,?,?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, shopid);
			preparedStatement.setString(2, qty);
			preparedStatement.setString(3, prodid);
			preparedStatement.setString(4, subcateoryid);
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	public boolean checkProductExist(String shopid, String prodid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM shop_stock where shopid="+shopid+" and prodid="+prodid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public int updateShopStock(String shopid, String prodid, String qty) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update shop_stock set stock="+qty+" where shopid="+shopid+" and prodid="+prodid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	public int getShopQty(String shopid, String prodid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT stock FROM shop_stock where shopid="+shopid+" and prodid="+prodid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public int getSumofShopQty(String prodid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT sum(stock) FROM shop_stock where prodid="+prodid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result  = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
				
	}


	public int getAdminProductQty(String prodid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT quantity FROM subcategory where id="+prodid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result  = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	
	



}
