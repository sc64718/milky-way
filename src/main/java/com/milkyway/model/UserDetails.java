package com.milkyway.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserDetails {

	private long userId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String passCode;
    
    public UserDetails(String firstName, String lastName, String mobileNumber, String email,String passCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.passCode = passCode;
    }
   
    
    public UserDetails() {
    	
    }
    
	public long getUserId() {
		return userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public String getEmail() {
		return email;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}


}
