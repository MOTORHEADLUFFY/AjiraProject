package com.ajira.Marsrover.demo.Entity;

public class Response {
	
	private Integer responseCode;
	// 0 for successful
	// 1 for failure
	
	private String responseMessage;

	
	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
}
