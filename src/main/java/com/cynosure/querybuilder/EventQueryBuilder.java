package com.cynosure.querybuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cynosure.pojo.Event;

@Service
public class EventQueryBuilder {

	@Autowired
	private EntityManager entityManager;

	public List<Event> getAllEvents() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Event> query = cb.createQuery(Event.class);

		Root<Event> event = query.from(Event.class);
		return entityManager.createQuery(query.select(event).orderBy(cb.desc(event.get("eventDate")))).getResultList();
	}

	public Event getEvent(long eventId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Event> query = cb.createQuery(Event.class);

		Root<Event> event = query.from(Event.class);
		return entityManager.createQuery(query.select(event).where(cb.equal(event.get("eventId"), eventId)))
				.getSingleResult();
	}

	public List<Event> getUpcomingEvents() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Event> query = cb.createQuery(Event.class);
		List<Event> upcomingEvents = new ArrayList<Event>();
		Root<Event> event = query.from(Event.class);

		upcomingEvents = entityManager.createQuery(query.select(event)
				.where(cb.greaterThan(event.get("eventDate"), new Timestamp(System.currentTimeMillis())))
				.orderBy(cb.desc(event.get("eventDate")))).setMaxResults(4).getResultList();
		if (CollectionUtils.isNotEmpty(upcomingEvents)) {
			return upcomingEvents;
		}

		return new ArrayList<Event>();
	}
}
