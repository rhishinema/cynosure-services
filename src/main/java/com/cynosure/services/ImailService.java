package com.cynosure.services;

public interface ImailService {

	void sendWelcomeMail(String emailId) throws Exception;
	
	void sendRegistrationMail(String emailId) throws Exception; 
	
	void sendDailyMail() throws Exception;

}
