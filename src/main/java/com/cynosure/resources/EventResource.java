package com.cynosure.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cynosure.pojo.Event;
import com.cynosure.services.CommonService;
import com.cynosure.services.EventService;
import com.cynosure.view.ErrorView;
import com.cynosure.view.EventView;

@RequestMapping(value = "/v1/events")
@RestController
public class EventResource {

	@Autowired
	private EventService eventService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllEvents() {
		try {
			List<Event> allEventsList = eventService.getAllEvents();
			return new ResponseEntity<Object>(new EventView(allEventsList), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			ErrorView errorView = new ErrorView("Error while getting all events");
			return new ResponseEntity<Object>(errorView, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/{eventId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getEvent(@PathVariable long eventId) {
		try {
			Event event = eventService.getEvent(eventId);
			return new ResponseEntity<Object>(event, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			ErrorView errorView = new ErrorView("Error while getting event");
			return new ResponseEntity<Object>(errorView, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> createEvent(@RequestBody Event event) {
		try {
			if (event != null) {
				event.setEventDate(CommonService.getTimeStampFromStr(event.getEventDateFormat()));
				event.setRegistrationStartDate(CommonService.getTimeStampFromStr(event.getRegistrationEndDateFormat()));
				event.setRegistrationEndDate(CommonService.getTimeStampFromStr(event.getRegistrationEndDateFormat()));
				eventService.createEvent(event);
			}
			return new ResponseEntity<Object>(event, HttpStatus.CREATED);
		} catch (Exception e) {
			ErrorView errorView = new ErrorView("Error while creating  event");
			return new ResponseEntity<Object>(errorView, HttpStatus.BAD_REQUEST);
		}
	}
}
