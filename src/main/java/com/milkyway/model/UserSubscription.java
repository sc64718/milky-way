package com.milkyway.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
//@JsonFilter("userSubsFilter")
public class UserSubscription {

	private UserDetails userDetails;
    private Subscription subscription;
    
    public UserSubscription() {   	
    }
    
	public Subscription getSubscription() {
		return subscription;
	}


	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}


	public UserDetails getUserDetails() {
		return userDetails;
	}


	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

}
