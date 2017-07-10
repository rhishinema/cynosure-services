package com.cynosure.view;

import java.util.List;

import com.cynosure.pojo.Event;


/**
 * @author 923986
 *
 */
public class EventView {
private List<Event> events;
	
	public EventView(List<Event> events){
		this.events = events;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	
}
