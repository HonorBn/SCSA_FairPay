package com.scsa.model.service;

import java.util.List;

import com.scsa.model.dao.EventClaimDAO;
import com.scsa.model.vo.EventClaimInfo;

public class EventClaimServiceImpl implements EventClaimService {
	
	private EventClaimDAO eventClaimDao;

	public void setEventClaimDao(EventClaimDAO eventClaimDao) {
		this.eventClaimDao = eventClaimDao;
	}

	@Override
	public boolean createEventClaim(EventClaimInfo eventClaim) {
		return eventClaimDao.insertEventClaim(eventClaim);
	}

	@Override
	public boolean deleteEventClaim(String eventClaimId) {
		return eventClaimDao.deleteEventClaim(eventClaimId);
	}

	@Override
	public boolean updateEventClaim(EventClaimInfo eventClaim) {
		return eventClaimDao.updateEventClaim(eventClaim);
	}

	@Override
	public EventClaimInfo getEventClaim(String eventClaimId) {
		return eventClaimDao.selectEventClaim(eventClaimId);
	}

	@Override
	public List<EventClaimInfo> searchEventClaimsByEventId(String eventId) {
		return eventClaimDao.selectEventClaimsByEventId(eventId);
	}

}
