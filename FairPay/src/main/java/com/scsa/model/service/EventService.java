package com.scsa.model.service;

import java.util.List;
import com.scsa.model.vo.EventInfo;

public interface EventService {
	boolean createEvent(EventInfo event);
	boolean deleteEvent(String eventId);
	boolean updateEvent(EventInfo event);
	List<EventInfo> selectEventsByMeetingId(String meetingId);
	EventInfo selectEventByEventId(String eventId);
	
}
