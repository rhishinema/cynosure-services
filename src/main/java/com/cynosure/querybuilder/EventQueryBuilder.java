package com.cynosure.querybuilder;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cynosure.pojo.Event;


@Service
public class EventQueryBuilder {

	@Autowired
	private EntityManager entityManager;
	
	public List<Event> getAllEvents(){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Event> query = cb.createQuery(Event.class);
		
		Root<Event> event = query.from(Event.class);
		return entityManager.createQuery(query.select(event).orderBy(cb.desc(event.get("eventDate")))).getResultList();
	}

	public Event getEvent(long eventId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Event> query = cb.createQuery(Event.class);
		
		Root<Event> event = query.from(Event.class);
		return entityManager.createQuery(query.select(event).where(cb.equal(event.get("eventId"),eventId))).getSingleResult();
	}
}
