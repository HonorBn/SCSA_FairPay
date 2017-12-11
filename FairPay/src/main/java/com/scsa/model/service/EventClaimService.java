package com.scsa.model.service;

import java.util.List;

import com.scsa.model.vo.EventClaimInfo;

public interface EventClaimService {
	
	boolean createEventClaim(EventClaimInfo eventClaim);
	boolean deleteEventClaim(String eventClaimId);
	boolean updateEventClaim(EventClaimInfo eventClaim);
	EventClaimInfo getEventClaim(String eventClaimId);
	List<EventClaimInfo> searchEventClaimsByEventId(String eventId);
	
	
}
