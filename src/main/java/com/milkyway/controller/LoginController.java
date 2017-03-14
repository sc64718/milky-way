package com.milkyway.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milkyway.dao.UserDAO;
import com.milkyway.model.Response;
import com.milkyway.model.Subscription;
import com.milkyway.model.UserDetails;
import com.milkyway.model.UserSubscription;

@RestController
public class LoginController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Response> login(@RequestBody UserDetails userRequest) {
		System.out.println("Inside User Login");
	    Response response = new Response();
	    
		if (userRequest == null) {
			response.setResponse_status("fail");
			response.setResponse_desc("No Passcode submitted");
			return new ResponseEntity<Response>(response,
					HttpStatus.BAD_REQUEST);
		} 

			boolean validateUserPasscode = userDAO.validateUserPasscode(userRequest.getUserId(),userRequest.getPassCode());
			
			if(validateUserPasscode) {
				
				List<UserSubscription> userSubscriptionList = userDAO.getUserSubscription(userRequest.getUserId());
				
				if(userSubscriptionList!=null && userSubscriptionList.size()>0){				
					response.setResponse_status("success");
					response.setResponse_object(userSubscriptionList.get(0).getUserDetails());
					
					List<Subscription> subsListForResponse = new ArrayList<Subscription>();
					
					for (UserSubscription subs:userSubscriptionList){
						subsListForResponse.add(subs.getSubscription());
					}
					response.setResponse_list(subsListForResponse);
					return new ResponseEntity<Response>(response, HttpStatus.OK);
					} else {
				response.setResponse_status("fail");
				response.setResponse_desc("No user subscriptions");
				return new ResponseEntity<Response>(response, HttpStatus.OK);
				}		
			} else {          
			response.setResponse_status("fail");
			response.setResponse_desc("Your passcode is not valid");
			return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);
			}
	}
	}
