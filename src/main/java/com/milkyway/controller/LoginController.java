package com.milkyway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milkyway.dao.UserDAO;
import com.milkyway.model.Response;
import com.milkyway.model.User;
import com.milkyway.validator.RequestValidator;

@RestController
public class LoginController {

	@Autowired
	private RequestValidator validator;
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> login(@RequestBody User userRequest) {
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
				
				List<User> userSubscriptionList = userDAO.getUserSubscription(userRequest.getUserId());
				response.setResponse_status("success");
				if(userSubscriptionList!=null && userSubscriptionList.size()==1){
					response.setResponse_object(userSubscriptionList.get(0));					
				} else {
					response.setResponse_list(userSubscriptionList);
				}
				return new ResponseEntity<Response>(response, HttpStatus.OK);
				
			} else {          
			response.setResponse_status("fail");
			response.setResponse_desc("Your passcode is not valid");
			return new ResponseEntity<Response>(response, HttpStatus.NOT_ACCEPTABLE);
			}

	}
	}
