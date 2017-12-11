package com.scsa.model.service;

import java.util.List;

import com.scsa.model.dao.EventDAO;
import com.scsa.model.vo.EventInfo;

public class EventServiceImpl implements EventService {

	private EventDAO eventDao;
	
	public void setEventDao(EventDAO eventDao) {
		this.eventDao = eventDao;
	}

	@Override
	public boolean createEvent(EventInfo event) {
		return eventDao.insertEvent(event);
	}

	@Override
	public boolean deleteEvent(String eventId) {
		return eventDao.deleteEvent(eventId);
	}

	@Override
	public boolean updateEvent(EventInfo event) {
		return eventDao.updateEvent(event);
	}

	@Override
	public List<EventInfo> selectEventsByMeetingId(String meetingId) {
		return eventDao.selectEventsByMeetingId(meetingId);
	}

	@Override
	public EventInfo selectEventByEventId(String eventId) {
		return eventDao.selectEventByEventId(eventId);
	}

}
