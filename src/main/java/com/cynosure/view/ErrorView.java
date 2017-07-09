package com.cynosure.view;

public class ErrorView {

	private String message;
	
	public ErrorView(){
		
	}
	
	public ErrorView(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
