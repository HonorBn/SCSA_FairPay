package com.scsa.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EventList {

	private List<EventInfo> eventList;

	public List<EventInfo> getMeetingList() {
		return eventList;
	}

	@XmlElement(name="eventInfo")
	public void setMeetingList(List<EventInfo> eventList) {
		this.eventList = eventList;
	}
	
	

}
