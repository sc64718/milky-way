package com.milkyway.validator;

import org.apache.commons.lang3.math.NumberUtils;

import com.milkyway.model.Response;
import com.milkyway.model.User;
import org.apache.commons.validator.routines.EmailValidator;

public class RequestValidator {


	public boolean validateRequest(User request, Response response) {

		if (request.getFirstName() == null && "".equalsIgnoreCase(request.getFirstName())) {
			response.setResponse_desc("Invalid First Name");
			return false;
		}

		if (request.getLastName() == null && "".equalsIgnoreCase(request.getLastName())) {
			response.setResponse_desc("Invalid Last Name");
			return false;
		}

		if (request.getMobileNumber() != null && "".equalsIgnoreCase(request.getMobileNumber())) {
			response.setResponse_desc("Invalid Mobile Number");
			return false;
		}

		if (!(NumberUtils.isDigits(request.getMobileNumber()))) {
			response.setResponse_desc("Invalid Mobile Number");
			return false;
		}
		
		if(!(EmailValidator.getInstance().isValid(request.getEmail()))) {
			response.setResponse_desc("Invalid Email Address");
			return false;	
		}

		return true;
	}

}
