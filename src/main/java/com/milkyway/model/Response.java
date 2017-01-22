package com.milkyway.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	
	private String response_status;
    private String response_object;
    private String response_desc;
	private List<?> response_list;
    
	public Response(){
	}
	
	public String getResponse_status() {
		return response_status;
	}
	public String getResponse_object() {
		return response_object;
	}
	public List<?> getResponse_list() {
		return response_list;
	}
	public void setResponse_status(String response_status) {
		this.response_status = response_status;
	}
	public void setResponse_object(String response_object) {
		this.response_object = response_object;
	}
	public void setResponse_list(List<?> response_list) {
		this.response_list = response_list;
	}
    public String getResponse_desc() {
		return response_desc;
	}
	public void setResponse_desc(String response_desc) {
		this.response_desc = response_desc;
	}

}
