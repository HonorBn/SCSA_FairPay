package com.scsa.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MeetingList {

	private List<MeetingInfo> meetingList;

	public List<MeetingInfo> getMeetingList() {
		return meetingList;
	}

	@XmlElement(name="meetingInfo")
	public void setMeetingList(List<MeetingInfo> meetingList) {
		this.meetingList = meetingList;
	}
	
	

}
