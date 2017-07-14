package com.cynosure.services;

import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cynosure.dao.SubscriberRepository;
import com.cynosure.pojo.Subscriber;

@Component("sendGridMailService")
public class SendGridMailService implements ImailService{

	@Autowired
	SubscriberRepository subscriberRepository;
	
	@Autowired
	CommonService commonService;

	private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";
	private static final String SMTP_AUTH_USER = "azure_cbcdecab9df232f188cca68f652b4188@azure.com";
	private static final String SMTP_AUTH_PWD = "Cyno@sure123";
	
	public static Logger LOGGER = Logger.getLogger(SendGridMailService.class);

	@Override
	public void sendWelcomeMail(String emailId) throws Exception {
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.host", SMTP_HOST_NAME);
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");

		Authenticator auth = new SMTPAuthenticator();
		Session mailSession = Session.getDefaultInstance(properties, auth);

		MimeMessage message = new MimeMessage(mailSession);
		Multipart multipart = new MimeMultipart("alternative");
		BodyPart part2 = new MimeBodyPart();
		part2.setContent(
				"<HTML><BODY background=\"https://s-media-cache-ak0.pinimg.com/originals/9a/d5/81/9ad5814323154ca643918d58b72b99f5.jpg\">"
						+ "<font face=\"verdana\" color=\"#800080\"><H1>Welcome To Neha's Fan Page!</H1><br><b>Hi There!</b><br><p>We welcome you Neha's Fan Page. "
						+ "You will daily receive updates regarding upcoming events, latest and upcoming movies of Neha. You will also get updates regarding Neha's "
						+ "latest range of products.</p></font><div style=\"position:absolute; bottom: 0; left: 600; width: 100px; text-align:right;\">"
						+ "<a href=\"http://52.170.201.27:8080/v1/subscribers/remove?email=" + emailId
						+ "\">Unsubscribe</a></div></BODY></HTML>",
				"text/html");
		multipart.addBodyPart(part2);
		message.setFrom(new InternetAddress("urman.ratneshwar@tcs.com"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
		message.setSubject("Welcome to Neha's Fan Page");
		message.setContent(multipart);

		Transport transport = mailSession.getTransport();
		// Connect the transport object.
		transport.connect();
		// Send the message.
		transport.sendMessage(message, message.getAllRecipients());
		// Close the connection.
		transport.close();
		LOGGER.info("Welcome mail sent");
	}

	@Override
	public void sendDailyMail() throws Exception {

		List<Subscriber> subscribers = subscriberRepository.findAll();
		if (CollectionUtils.isNotEmpty(subscribers)) {
			
			for (Subscriber subscriber : subscribers) {

				String emailId = subscriber.getEmail();
				Properties properties = new Properties();
				properties.put("mail.transport.protocol", "smtp");
				properties.put("mail.smtp.host", SMTP_HOST_NAME);
				properties.put("mail.smtp.port", 587);
				properties.put("mail.smtp.auth", "true");

				Authenticator auth = new SMTPAuthenticator();
				Session mailSession = Session.getDefaultInstance(properties, auth);

				MimeMessage message = new MimeMessage(mailSession);
				Multipart multipart = new MimeMultipart("alternative");
				BodyPart part2 = new MimeBodyPart();
				part2.setContent(commonService.buildHtmlForDailyMail().replace("{emailId}", emailId),
						"text/html");
				multipart.addBodyPart(part2);
				message.setFrom(new InternetAddress("urman.ratneshwar@tcs.com"));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
				message.setSubject("Welcome to Neha's Fan Page");
				message.setContent(multipart);

				Transport transport = mailSession.getTransport();
				// Connect the transport object.
				transport.connect();
				// Send the message.
				transport.sendMessage(message, message.getAllRecipients());
				// Close the connection.
				transport.close();
			}
		}
		LOGGER.info("Daily mails sent");
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}
}
