package com.cynosure.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cynosure.services.CommonService;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_event_detail", schema = "[cynosure-schema]")
public class Event {

	@Id
	@GeneratedValue
	@Column(name = "event_id")
	private long eventId;

	@Column(name = "event_name")
	private String eventName;

	@Column(name = "event_description")
	private String eventDescription;

	@JsonIgnore
	@Column(name = "event_date")
	private Timestamp eventDate;

	@Column(name = "event_venue")
	private String eventVenue;

	@JsonIgnore
	@Column(name = "registration_start_dt")
	private Timestamp registrationStartDate;

	@JsonIgnore
	@Column(name = "registration_end_dt")
	private Timestamp registrationEndDate;

	@Column(name = "entry_fee")
	private int entryFee;

	@Transient
	private String eventUrl;

	@Transient
	private String eventDateFormat;

	@Transient
	private String registrationStartDateFormat;

	@Transient
	private String registrationEndDateFormat;

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
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

	public String getRegistrationStartDateFormat() {
		if (registrationStartDate != null) {
			registrationStartDateFormat = CommonService.getTimeStampStr(registrationStartDate);
		}

		return registrationStartDateFormat;
	}

	public void setRegistrationStartDateFormat(String registrationStartDateFormat) {
		this.registrationStartDateFormat = registrationStartDateFormat;
	}

	public String getRegistrationEndDateFormat() {
		if (registrationEndDate != null) {
			registrationEndDateFormat = CommonService.getTimeStampStr(registrationEndDate);
		}
		return registrationEndDateFormat;
	}

	public void setRegistrationEndDateFormat(String registrationEndDateFormat) {
		this.registrationEndDateFormat = registrationEndDateFormat;
	}

	public void setEventDateFormat(String eventDateFormat) {
		this.eventDateFormat = eventDateFormat;
	}

	public String getEventDateFormat() {
		if (eventDate != null) {
			eventDateFormat = CommonService.getTimeStampStr(eventDate);
		}
		return eventDateFormat;
	}

	public void setEventUrl(String eventUrl) {
		this.eventUrl = eventUrl;
	}

	public String getEventUrl() {
		return eventUrl;
	}
}
