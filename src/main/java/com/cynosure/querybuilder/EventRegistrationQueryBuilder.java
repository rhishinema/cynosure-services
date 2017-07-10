package com.cynosure.querybuilder;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cynosure.pojo.EventRegisteration;

@Service
public class EventRegistrationQueryBuilder {

	@Autowired
	private EntityManager entityManager;
	
	public EventRegisteration getEventRegistration(long registrationId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<EventRegisteration> query = cb.createQuery(EventRegisteration.class);
		
		Root<EventRegisteration> eventRegisteration = query.from(EventRegisteration.class);
		return entityManager.createQuery(query.select(eventRegisteration).where(cb.equal(eventRegisteration.get("registrationId"),registrationId))).getSingleResult();
	}

}
