package com.milkyway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milkyway.dao.UserDAO;
import com.milkyway.model.Response;
import com.milkyway.model.UserVacations;

@RestController
public class PlanVacationController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value = "/planvacations", method = RequestMethod.POST)
	public ResponseEntity<Response> planVacations(@RequestBody UserVacations userVacations) {
		long usrId = userVacations.getUserId();
		System.out.println("Planning vacations for userId" +usrId);
		Response response = new Response();		
		try{
        userDAO.addUserVacations(userVacations);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setResponse_status("fail");
			response.setResponse_desc("Error creating vacation record for userId --> " +usrId);
			return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setResponse_status("success");
		response.setResponse_desc("Successfully created vacation record for userId -->" +usrId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
