package com.milkyway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milkyway.dao.UserRegistrationDAO;
import com.milkyway.model.Response;
import com.milkyway.model.User;
import com.milkyway.utils.EmailSender;
import com.milkyway.validator.RequestValidator;

@RestController
public class UserRegistrationController {

	@Autowired
	private RequestValidator validator;
	@Autowired
	private UserRegistrationDAO registrationDAO;
	@Autowired
	private EmailSender emailSender;

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> register(@RequestBody User userRequest) {
		System.out.println("Inside User Registration");
		if (userRequest == null)
			System.out.println("No User details");

		Response response = new Response();
		
		boolean validRequest = validator.validateRequest(userRequest,response);
		
		if (validRequest) {
			boolean mobileNotYetRegistered = registrationDAO.findByMobileNo(userRequest.getMobileNumber());
            if(mobileNotYetRegistered) {
			User user = new User(userRequest.getFirstName(),
					userRequest.getLastName(), userRequest.getMobileNumber(),
					userRequest.getEmail());

			registrationDAO.addUser(user);
            
			response.setResponse_status("success");
			
			emailSender.send(userRequest.getEmail());

			return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
            else {
            	response.setResponse_status("fail");
    			response.setResponse_desc("Mobile number already registered with us!!");
    			return new ResponseEntity<Response>(response,
    					HttpStatus.BAD_REQUEST);
            }
		}

		else {
			response.setResponse_status("fail");
			return new ResponseEntity<Response>(response,
					HttpStatus.BAD_REQUEST);
		}
	}
}
