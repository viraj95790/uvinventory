package com.inventorymanagement.main.web.common.helper;






import java.util.ArrayList;



public class LoginInfo {

	/** Id of user that corresponds to primary key of database table row */
	private int id;
	
	/** name of user */
	private String userName = null;

	/** id of user */
	private String userId = null;
	
	private String branchName;
	
	private int userType;
	
	private String code = "";
	
	
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	
    /**
     * @return the userId
     */
    public String getUserName() {
    	return userName;
    }
	
    /**
     * @param userId the userId to set
     */
    public void setUserName(String userName) {
    	this.userName = userName;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
    	return userId;
    }
	
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
    	this.userId = userId;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
  
}
