package com.cynosure.services;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.cynosure.resources.SubscriptionResource;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Email;

@Component("mailJetMailService")
public class MailJetMailService implements ImailService{
	
	public static final String API_KEY = "890e8d88db9476882284a2b1d86b027b";
	public static final String API_SECRET = "da3be1df40be8c3b46b6ebf51b24234b";
	public static Logger LOGGER = Logger.getLogger(SubscriptionResource.class);

	@Override
	public void sendWelcomeMail(String emailId) throws Exception {

		MailjetClient client;
		MailjetRequest request;
		MailjetResponse response;
		
		client = new MailjetClient(API_KEY, API_SECRET);
		request = new MailjetRequest(Email.resource).property(Email.FROMEMAIL, "urman.ratneshwar@tcs.com")
				.property(Email.FROMNAME, "Cynosure Fan Page").property(Email.SUBJECT,"Welcome to Cynosure Fan Page")
				.property(Email.HTMLPART, 
						"<HTML><BODY background=\"https://s-media-cache-ak0.pinimg.com/originals/9a/d5/81/9ad5814323154ca643918d58b72b99f5.jpg\">"
								+ "<font face=\"verdana\" color=\"#800080\"><H1>Welcome To Rhishi's Fan Page!</H1><br><b>Hi There!</b><br><p>We welcome you Rhishi's Fan Page. "
								+ "You will daily receive updates regarding upcoming events, latest and upcoming movies of Rhishi. You will also get updates regarding Rhishi's "
								+ "latest range of products.</p></font><div style=\"position:absolute; bottom: 0; left: 600; width: 100px; text-align:right;\">"
								+ "<a href=\"http://52.170.201.27:8080/v1/subscribers/remove?email=" + emailId
								+ "\">Unsubscribe</a></div></BODY></HTML>").property(Email.RECIPIENTS, new JSONArray()
						                .put(new JSONObject()
						                        .put("Email", emailId)));
		response = client.post(request);
		LOGGER.info(response.getData());
		
	}

	@Override
	public void sendDailyMail() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
