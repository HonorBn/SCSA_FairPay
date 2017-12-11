package com.scsa.model.vo;

public class EventInfo {
	
	private String eventId;
	private String eventDate;
	private String description;
	private String groupId;
	public EventInfo() {
		super();
	}
	public EventInfo(String eventDate, String description, String groupId) {
		super();
		this.eventDate = eventDate;
		this.description = description;
		this.groupId = groupId;
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
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
		builder.append(", getGroupId()=");
		builder.append(getGroupId());
		builder.append("]");
		return builder.toString();
	}
	
	

}
