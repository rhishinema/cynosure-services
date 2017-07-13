package com.cynosure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cynosure.dao.EventRepository;
import com.cynosure.pojo.Event;
import com.cynosure.querybuilder.EventQueryBuilder;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private EventQueryBuilder eventQueryBuilder;

	public List<Event> getAllEvents() {
		return eventQueryBuilder.getAllEvents();
	}

	public Event getEvent(long eventId) {
		return eventQueryBuilder.getEvent(eventId);
	}

	public void createEvent(Event event) {
		eventRepository.save(event);
	}

	public List<Event> getUpcomingEvents() {
		return eventQueryBuilder.getUpcomingEvents();
	}

}
