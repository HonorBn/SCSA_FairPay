package com.scsa.model.dao;

import java.util.List;

import com.scsa.model.vo.EventInfo;

public interface EventDAO {
	boolean insertEvent(EventInfo event);
	boolean deleteEvent(String eventId);
	boolean updateEvent(EventInfo event);
	List<EventInfo> selectEventsByMeetingId(String meetingId);
	EventInfo selectEventByEventId(String eventId);
}
