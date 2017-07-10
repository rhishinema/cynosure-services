package com.cynosure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cynosure.dao.EventRegistrationRepository;
import com.cynosure.pojo.EventRegisteration;
import com.cynosure.querybuilder.EventRegistrationQueryBuilder;

@Service
public class EventRegisterationService {

	@Autowired
	private EventRegistrationRepository eventRegistrationRepository;

	@Autowired
	private EventRegistrationQueryBuilder eventRegistrationQueryBuilder;
	
	public void registerForEvent(EventRegisteration eventRegisteration) {
		eventRegistrationRepository.save(eventRegisteration);
	}

	public EventRegisteration getEventRegistration(long registrationId) {
		return eventRegistrationQueryBuilder.getEventRegistration(registrationId);
	}

}
