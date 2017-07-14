package com.cynosure.services;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cynosure.dao.SubscriberRepository;
import com.cynosure.pojo.Subscriber;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Email;

@Component("mailJetMailService")
public class MailJetMailService implements ImailService {

	@Autowired
	SubscriberRepository subscriberRepository;

	@Autowired
	CommonService commonService;

	public static final String API_KEY = "890e8d88db9476882284a2b1d86b027b";
	public static final String API_SECRET = "da3be1df40be8c3b46b6ebf51b24234b";
	public static final String WELCOME_MAIL_SUBJECT = "Welcome to Cynosure Fan Page";
	public static final String EVENT_MAIL_SUBJECT = "You have registered for Cynosure Event";
	public static final String DAILY_MAIL_SUBJECT = "Updates on Cynosure";
	public static final String FROM_NAME = "Cynosure Fan Page";
	public static final String FROM_EMAIL = "urman.ratneshwar@tcs.com";
	public static Logger LOGGER = Logger.getLogger(MailJetMailService.class);

	@Override
	public void sendWelcomeMail(String emailId) throws Exception {

		String htmlContent = "<HTML><BODY background=\"https://s-media-cache-ak0.pinimg.com/originals/9a/d5/81/9ad5814323154ca643918d58b72b99f5.jpg\">"
				+ "<font face=\"verdana\" color=\"#800080\"><H1>Welcome To Neha's Fan Page!</H1><br><b>Hi There!</b><br><p>We welcome you Neha's Fan Page. "
				+ "You will daily receive updates regarding upcoming events, latest and upcoming movies of Neha. You will also get updates regarding Neha's "
				+ "latest range of products.</p></font><div style=\"position:absolute; bottom: 0; left: 600; width: 100px; text-align:right;\">"
				+ "<a href=\"http://52.170.201.27:8080/v1/subscribers/remove?email=" + emailId
				+ "\">Unsubscribe</a></div></BODY></HTML>";

		MailjetClient client;
		MailjetRequest request;
		MailjetResponse response;

		client = new MailjetClient(API_KEY, API_SECRET);
		request = new MailjetRequest(Email.resource).property(Email.FROMEMAIL, FROM_EMAIL)
				.property(Email.FROMNAME, FROM_NAME).property(Email.SUBJECT, WELCOME_MAIL_SUBJECT)
				.property(Email.HTMLPART, htmlContent)
				.property(Email.RECIPIENTS, new JSONArray().put(new JSONObject().put("Email", emailId)));
		response = client.post(request);
		LOGGER.info(response.getData());
		LOGGER.info("Welcome mail sent");

	}

	@Override
	public void sendRegistrationMail(String emailId) throws Exception {
		String htmlContent = "<HTML><BODY background=\"https://s-media-cache-ak0.pinimg.com/originals/9a/d5/81/9ad5814323154ca643918d58b72b99f5.jpg\">"
				+ "<font face=\"verdana\" color=\"#800080\"><H1>You are succesfully registered for Neha's Event!</H1><br><b>Hi There!</b><br><p>We welcome to attend this event. "
				+ "You will daily receive updates regarding upcoming events, latest and upcoming movies of Neha. You will also get updates regarding Neha's "
				+ "latest range of products.</p></font><div style=\"position:absolute; bottom: 0; left: 600; width: 100px; text-align:right;\">"
				+ "<a href=\"http://52.170.201.27:8080/v1/subscribers/remove?email=" + emailId
				+ "\">Unsubscribe</a></div></BODY></HTML>";

		MailjetClient client;
		MailjetRequest request;
		MailjetResponse response;

		client = new MailjetClient(API_KEY, API_SECRET);
		request = new MailjetRequest(Email.resource).property(Email.FROMEMAIL, FROM_EMAIL)
				.property(Email.FROMNAME, FROM_NAME).property(Email.SUBJECT, EVENT_MAIL_SUBJECT)
				.property(Email.HTMLPART, htmlContent)
				.property(Email.RECIPIENTS, new JSONArray().put(new JSONObject().put("Email", emailId)));
		response = client.post(request);
		LOGGER.info(response.getData());
		LOGGER.info("Welcome mail sent");
	}

	@Override
	public void sendDailyMail() throws Exception {

		List<Subscriber> subscribers = subscriberRepository.findAll();
		if (CollectionUtils.isNotEmpty(subscribers)) {

			for (Subscriber subscriber : subscribers) {

				String emailId = subscriber.getEmail();

				MailjetClient client;
				MailjetRequest request;
				MailjetResponse response;

				client = new MailjetClient(API_KEY, API_SECRET);

				request = new MailjetRequest(Email.resource).property(Email.FROMEMAIL, FROM_EMAIL)
						.property(Email.FROMNAME, FROM_NAME).property(Email.SUBJECT, DAILY_MAIL_SUBJECT)
						.property(Email.HTMLPART, commonService.buildHtmlForDailyMail().replace("{emailId}", emailId))
						.property(Email.RECIPIENTS, new JSONArray().put(new JSONObject().put("Email", emailId)));
				response = client.post(request);
				LOGGER.info(response.getData());
			}
		}
		LOGGER.info("Daily mails sent");
	}

}
