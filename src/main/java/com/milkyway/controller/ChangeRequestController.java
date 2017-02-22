package com.milkyway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.milkyway.dao.SubscriptionDAO;
import com.milkyway.model.Response;
import com.milkyway.model.Subscription;


@RestController
public class ChangeRequestController {
	
	@Autowired
	private SubscriptionDAO subscriptionDAO;

	@RequestMapping(value = "/changerequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> changeRequest(@RequestBody Subscription subscriptionChangeRequest) {	
		System.out.println("Inside Change Request");
		Response response = new Response();
		long changeRequestID = 0;
		try{
		changeRequestID = subscriptionDAO.insertChangeRecord(subscriptionChangeRequest);
		} catch (Exception ex){
			System.out.println("Exception while inserting change record ");
			ex.printStackTrace();
			response.setResponse_status("fail");
			response.setResponse_desc("Error while inserting change record");
			return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setResponse_status("success");
		response.setResponse_desc("Change Request ID is "+changeRequestID);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	
	}
	
}
