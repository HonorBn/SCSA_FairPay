package com.scsa.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.scsa.model.vo.EventClaimInfo;

public class EventClaimDAOImpl implements EventClaimDAO {

	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public boolean insertEventClaim(EventClaimInfo eventClaim) {
		if(sqlSession.insert("eventClaim.insertEventClaim", eventClaim)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean deleteEventClaim(String eventClaimId) {
		if(sqlSession.delete("eventClaim.deleteEventClaim", eventClaimId)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updateEventClaim(EventClaimInfo eventClaim) {
		if(sqlSession.update("eventClaim.updateEventClaim", eventClaim)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public EventClaimInfo selectEventClaim(String eventClaimId) {
		return sqlSession.selectOne("eventClaim.selectEventClaim", eventClaimId);
	}

	@Override
	public List<EventClaimInfo> selectEventClaimsByEventId(String eventId) {
		return sqlSession.selectList("eventClaim.selectEventClaimsByEventId", eventId);
	}

}
