package com.milkyway.model;

public class User {

	private long userId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String userType;
    
   
    public User(String firstName, String lastName, String mobileNumber, String email, String userType ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.userType = userType; 
    }
   
    public User() {
    	
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
	public String getUserType() {
		return userType;
	}  
}
