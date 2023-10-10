package com.xadmin.usermanagement.bean;

public class Users {
	private int UserID;
	private String UserName;
	private String Email;
	private String MobilePhone;
	private boolean IsActive;
	private String DateCreated;
	
	public Users(int userID, String userName, String email, String mobilePhone, boolean isActive, String dateCreated) {
		super();
		UserID = userID;
		UserName = userName;
		Email = email;
		MobilePhone = mobilePhone;
		IsActive = isActive;
		DateCreated = dateCreated;
	}
	
	
	public Users(String userName, String email, String mobilePhone, boolean isActive, String dateCreated) {
		super();
		UserName = userName;
		Email = email;
		MobilePhone = mobilePhone;
		IsActive = isActive;
		DateCreated = dateCreated;
	}


	public void setMobilePhone(String mobilePhone) {
		MobilePhone = mobilePhone;
	}
	public boolean isIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
	public String getDateCreated() {
		return DateCreated;
	}
	public void setDateCreated(String dateCreated) {
		DateCreated = dateCreated;
	}
	
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getMobilePhone() {
		return MobilePhone;
	}
}	
	