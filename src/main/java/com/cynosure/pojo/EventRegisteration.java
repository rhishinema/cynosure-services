package com.cynosure.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_event_registration", schema="[cynosure-schema]")
public class EventRegisteration {
	
	@Id
	@GeneratedValue
	@Column(name="registration_id")
	private long registerationId;
	
	@Column(name="EVENT_ID")
	private long eventId;
	
	@Column(name="person_email")
	private String personEmail;
	
	@Column(name="person_name")
	private String personName;
	
	@Column(name="contact_number")
	private String contactNumber;
	
	@Column(name="amount_charged")
	private int  amountCharged;
	
	@Column(name="regsitration_date")
	private Timestamp  registrationDate;

	public long getRegisterationId() {
		return registerationId;
	}

	public void setRegisterationId(long registerationId) {
		this.registerationId = registerationId;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getAmountCharged() {
		return amountCharged;
	}

	public void setAmountCharged(int amountCharged) {
		this.amountCharged = amountCharged;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

}
