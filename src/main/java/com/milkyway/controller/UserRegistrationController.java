package com.milkyway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milkyway.dao.UserRegistrationDAO;
import com.milkyway.model.Response;
import com.milkyway.model.User;
import com.milkyway.validator.RequestValidator;

@RestController
public class UserRegistrationController {

	@Autowired
	private RequestValidator validator;
	@Autowired
	private Response response;
	@Autowired
	private UserRegistrationDAO registrationDAO;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Response> register(@RequestBody User userRequest) {
		System.out.println("Inside User Registration");
		if (userRequest == null)
			System.out.println("No User details");

		boolean validRequest = validator.validateRequest(userRequest);

		if (validRequest) {

			User user = new User(userRequest.getFirstName(),
					userRequest.getLastName(), userRequest.getMobileNumber(),
					userRequest.getEmail(), userRequest.getUserType());

			registrationDAO.addUser(user);

			response.setResponse_status("success");

			return new ResponseEntity<Response>(response, HttpStatus.OK);

		}

		else {

			response.setResponse_status("fail");
			return new ResponseEntity<Response>(response,
					HttpStatus.BAD_REQUEST);
		}

		// ResponseEntity.noContent().build();
	}
}
