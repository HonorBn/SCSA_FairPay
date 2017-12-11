package com.scsa.model.dao;

import java.util.List;

import com.scsa.model.vo.EventClaimInfo;

public interface EventClaimDAO {
	boolean insertEventClaim(EventClaimInfo eventClaim);
	boolean deleteEventClaim(String eventClaimId);
	boolean updateEventClaim(EventClaimInfo eventClaim);
	EventClaimInfo selectEventClaim(String eventClaimId);
	List<EventClaimInfo> selectEventClaimsByEventId(String eventId);
}
