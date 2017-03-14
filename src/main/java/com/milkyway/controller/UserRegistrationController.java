package com.milkyway.controller;

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
import com.milkyway.model.UserDetails;
import com.milkyway.utils.EmailSender;
import com.milkyway.validator.RequestValidator;

@RestController
public class UserRegistrationController {

	@Autowired
	private RequestValidator validator;
	@Autowired
	private UserDAO registrationDAO;
	@Autowired
	private EmailSender emailSender;

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> register(@RequestBody UserDetails userRequest) {
		System.out.println("Inside User Registration");
		Response response = new Response();
		
		if (userRequest == null){
			System.out.println("No User details");
			response.setResponse_status("fail");
			return new ResponseEntity<Response>(response,
					HttpStatus.BAD_REQUEST);
		}		
		
		boolean validRequest = validator.validateRequest(userRequest,response);
		
		if (validRequest) {
			boolean mobileNotYetRegistered = registrationDAO.findByMobileNo(userRequest.getMobileNumber());
            if(mobileNotYetRegistered) {
            	//generate a 4 digit integer 1000 <10000
    	    int randomPIN = (int)(Math.random()*9000)+1000;
    	    String PINString = String.valueOf(randomPIN);
			UserDetails userDetails = new UserDetails(userRequest.getFirstName(),
					userRequest.getLastName(), userRequest.getMobileNumber(),
					userRequest.getEmail(),PINString);
			System.out.println("Passcode is " +PINString);
			Long userId = registrationDAO.addUser(userDetails);
            System.out.println("User ID is " +userId);			
			try {
			//emailSender.send(userRequest.getEmail(),PINString);
			//send SMS
			} catch (Exception ex) {
				System.out.println("Exception while sending email to " +userRequest.getEmail());
				ex.printStackTrace();
			} 
			response.setResponse_status("success");	
			response.setResponse_desc("User ID is "+userId);	
			return new ResponseEntity<Response>(response, HttpStatus.OK);
	
            }
            else {
            	response.setResponse_status("fail");
    			response.setResponse_desc("Mobile number already registered with us");
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
