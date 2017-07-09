package com.cynosure.services;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cynosure.dao.SubscriberRepository;
import com.cynosure.pojo.Subscriber;
import com.cynosure.resources.SubscriptionResource;
import com.cynosure.util.BaseException;
import com.cynosure.util.CommonConstants;

import net.sf.json.JSONObject;

@Component
public class SubscriptionService {

	@Autowired
	SubscriberRepository subscriberRepository;

	@Autowired
	SendMailService sendMailService;

	public static Logger LOGGER = Logger.getLogger(SubscriptionResource.class);

	@Transactional
	public void addSubscriber(JSONObject jsonObject) {

		if (subscriberRepository.findOne(jsonObject.getString(CommonConstants.SUBSCRIBER_EMAIL)) == null) {
			Subscriber subscriber = new Subscriber();
			subscriber.setEmail(jsonObject.getString(CommonConstants.SUBSCRIBER_EMAIL));
			subscriberRepository.save(subscriber);
			try {
				sendMailService.sendWelcomeMail(jsonObject.getString(CommonConstants.SUBSCRIBER_EMAIL));
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				throw new BaseException("Error Sending Email");
			}
		}
	}

	@Transactional
	public void removeSubscriber(String emailId) {

		Subscriber subscriber = new Subscriber();
		subscriber.setEmail(emailId);
		subscriberRepository.delete(subscriber);

	}

}
