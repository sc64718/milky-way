package com.milkyway.model;

public class User {

	private long userId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    
   
    public User(String firstName, String lastName, String mobileNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
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
}
