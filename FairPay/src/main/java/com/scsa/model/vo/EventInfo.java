package com.scsa.model.vo;

public class EventInfo {
	
	private String eventId;
	private String eventDate;
	private String description;
	private String meetingId;
	
	public EventInfo() {
		super();
	}

	public EventInfo(String eventDate, String description, String meetingId) {
		super();
		this.eventDate = eventDate;
		this.description = description;
		this.meetingId = meetingId;
	}

	public EventInfo(String eventId, String eventDate, String description, String meetingId) {
		super();
		this.eventId = eventId;
		this.eventDate = eventDate;
		this.description = description;
		this.meetingId = meetingId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventInfo [getEventId()=");
		builder.append(getEventId());
		builder.append(", getEventDate()=");
		builder.append(getEventDate());
		builder.append(", getDescription()=");
		builder.append(getDescription());
		builder.append(", getMeetingId()=");
		builder.append(getMeetingId());
		builder.append("]");
		return builder.toString();
	}
	
	

}
