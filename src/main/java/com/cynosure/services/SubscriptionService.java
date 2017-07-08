package com.cynosure.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cynosure.dao.SubscriberRepository;
import com.cynosure.pojo.Subscriber;
import com.cynosure.util.CommonConstants;

import net.sf.json.JSONObject;

@Component
public class SubscriptionService {
	
	@Autowired
	SubscriberRepository subscriberRepository;
	
	@Transactional
	public void addSubscriber(JSONObject jsonObject) {

		Subscriber subscriber = new Subscriber();
		subscriber.setEmail(jsonObject.getString(CommonConstants.SUBSCRIBER_EMAIL));
		subscriberRepository.save(subscriber);
		
	}

}
