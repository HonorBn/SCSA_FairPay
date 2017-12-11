package com.scsa.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EventClaimList {
	
	private List<EventClaimInfo> eventClaimList;

	public List<EventClaimInfo> getEventClaimList() {
		return eventClaimList;
	}

	@XmlElement(name="eventClaimInfo")
	public void setEventClaimList(List<EventClaimInfo> eventClaimList) {
		this.eventClaimList = eventClaimList;
	}
	
	
	
}
