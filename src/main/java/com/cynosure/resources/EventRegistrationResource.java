package com.cynosure.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cynosure.pojo.EventRegisteration;
import com.cynosure.services.CommonService;
import com.cynosure.services.EventRegisterationService;
import com.cynosure.view.ErrorView;


@RequestMapping(value = "/v1/register")
@RestController
public class EventRegistrationResource {

	@Autowired
	private EventRegisterationService eventRegisterationService;
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> createEvent(@RequestParam long eventId,
			@RequestParam String personEmail,
			@RequestParam String personName,
			@RequestParam String contactNumber,
			@RequestParam int amountCharged,
			@RequestParam String registrationDate) {
		try {
			EventRegisteration eventRegisteration = new EventRegisteration();
			eventRegisteration.setEventId(eventId);
			eventRegisteration.setPersonEmail(personEmail);
			eventRegisteration.setPersonName(personName);
			eventRegisteration.setContactNumber(contactNumber);
			eventRegisteration.setAmountCharged(amountCharged);
			eventRegisteration.setRegistrationDate(CommonService
					.getTimeStampFromStr(registrationDate));
			eventRegisterationService.registerForEvent(eventRegisteration);
			return new ResponseEntity<Object>(eventRegisteration, HttpStatus.CREATED);
		} catch (Exception e) {
			ErrorView errorView = new ErrorView(
					"Error while registering for the  event");
			return new ResponseEntity<Object>(errorView, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{registrationId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getEventRegistration(@PathVariable long registrationId) {
		try {
			EventRegisteration eventRegisteration = eventRegisterationService.getEventRegistration(registrationId);
			return new ResponseEntity<Object>(eventRegisteration, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			ErrorView errorView = new ErrorView("Error while getting event registration");
			return new ResponseEntity<Object>(errorView, HttpStatus.BAD_REQUEST);
		}
	}
}
