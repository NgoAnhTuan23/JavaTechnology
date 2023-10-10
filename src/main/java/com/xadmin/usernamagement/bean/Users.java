package com.xadmin.usernamagement.bean;

public class Users {
	private int id;
	private String name;
	private String email;
	private String phoneNumber;
	private boolean isActive;
	private String dateCreated;
	
	public Users(String name, String email, String phoneNumber, boolean isActive, String dateCreated) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.isActive = isActive;
		this.dateCreated = dateCreated;
	}

	public Users(int id, String name, String email, String phoneNumber, boolean isActive, String dateCreated) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.isActive = isActive;
		this.dateCreated = dateCreated;
	}
	
	//ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//Name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	//PhoneNumber
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	//IsActive?
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	//Date Created
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
}
