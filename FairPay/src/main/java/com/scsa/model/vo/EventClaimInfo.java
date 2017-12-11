package com.scsa.model.vo;

public class EventClaimInfo {
	private String eventClaimId;
	
	// 결제명
	private String eventClaimName;
	
	// 결제 금액
	private String eventClaimAmount;
	
	// 결제자명
	private String claimerId;
	
	// 일정
	private String eventId;

	public EventClaimInfo() {
		super();
	}

	public EventClaimInfo(String eventClaimId, String eventClaimName, String eventClaimAmount, String claimerId,
			String eventId) {
		super();
		this.eventClaimId = eventClaimId;
		this.eventClaimName = eventClaimName;
		this.eventClaimAmount = eventClaimAmount;
		this.claimerId = claimerId;
		this.eventId = eventId;
	}

	public EventClaimInfo(String eventClaimName, String eventClaimAmount, String claimerId, String eventId) {
		super();
		this.eventClaimName = eventClaimName;
		this.eventClaimAmount = eventClaimAmount;
		this.claimerId = claimerId;
		this.eventId = eventId;
	}

	public String getEventClaimId() {
		return eventClaimId;
	}

	public void setEventClaimId(String eventClaimId) {
		this.eventClaimId = eventClaimId;
	}

	public String getEventClaimName() {
		return eventClaimName;
	}

	public void setEventClaimName(String eventClaimName) {
		this.eventClaimName = eventClaimName;
	}

	public String getEventClaimAmount() {
		return eventClaimAmount;
	}

	public void setEventClaimAmount(String eventClaimAmount) {
		this.eventClaimAmount = eventClaimAmount;
	}

	public String getClaimerId() {
		return claimerId;
	}

	public void setClaimerId(String claimerId) {
		this.claimerId = claimerId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventClaimInfo [getEventClaimId()=");
		builder.append(getEventClaimId());
		builder.append(", getEventClaimName()=");
		builder.append(getEventClaimName());
		builder.append(", getEventClaimAmount()=");
		builder.append(getEventClaimAmount());
		builder.append(", getClaimerId()=");
		builder.append(getClaimerId());
		builder.append(", getEventId()=");
		builder.append(getEventId());
		builder.append("]");
		return builder.toString();
	}


	
}
