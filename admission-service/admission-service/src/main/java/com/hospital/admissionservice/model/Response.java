package com.hospital.admissionservice.model;

public class Response {
	
	String message;
	
	public Response(){
		
	}

	public Response(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
