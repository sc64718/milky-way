package com.milkyway.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.milkyway.model.Response;
import com.milkyway.model.User;
import org.apache.commons.lang3.math.NumberUtils;

public class RequestValidator {

	@Autowired
	Response response;

	public boolean validateRequest(User request) {

		if (request.getFirstName() == null) {
			response.setResponse_desc("Invalid First Name");
			return false;
		}

		if (request.getLastName() == null) {
			response.setResponse_desc("Invalid Last Name");
			return false;
		}

		if (request.getMobileNumber() == null) {
			response.setResponse_desc("Invalid Mobile Number");
			return false;
		}

		if (!(NumberUtils.isDigits(request.getMobileNumber()))) {
			response.setResponse_desc("Invalid Mobile Number");
			return false;
		}

		if (request.getUserType() == null) {
			response.setResponse_desc("Invalid User Type");
			return false;
		}

		return true;
	}

}
