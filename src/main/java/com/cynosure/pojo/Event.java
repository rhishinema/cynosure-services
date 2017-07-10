package com.cynosure.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_event", schema="[cynosure-schema]")
public class Event {

	@Id
	@GeneratedValue
	@Column(name="event_id")
	private long EventId;
	
	@Column(name="event_name")
	private String eventName;
	
	@Column(name="event_description")
	private String eventDescription;
	
	@Column(name="event_date")
	private Timestamp  eventDate;
	
	@Column(name="event_venue")
	private String eventVenue;
	
	@Column(name="registration_start_dt")
	private Timestamp registrationStartDate;
	
	@Column(name="registration_end_dt")
	private Timestamp registrationEndDate;
	
	@Column(name="entry_fee")
	private int  entryFee;

	public long getEventId() {
		return EventId;
	}

	public void setEventId(long eventId) {
		EventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Timestamp getEventDate() {
		return eventDate;
	}

	public void setEventDate(Timestamp eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventVenue() {
		return eventVenue;
	}

	public void setEventVenue(String eventVenue) {
		this.eventVenue = eventVenue;
	}

	public Timestamp getRegistrationStartDate() {
		return registrationStartDate;
	}

	public void setRegistrationStartDate(Timestamp registrationStartDate) {
		this.registrationStartDate = registrationStartDate;
	}

	public Timestamp getRegistrationEndDate() {
		return registrationEndDate;
	}

	public void setRegistrationEndDate(Timestamp registrationEndDate) {
		this.registrationEndDate = registrationEndDate;
	}

	public int getEntryFee() {
		return entryFee;
	}

	public void setEntryFee(int entryFee) {
		this.entryFee = entryFee;
	}
	
}
