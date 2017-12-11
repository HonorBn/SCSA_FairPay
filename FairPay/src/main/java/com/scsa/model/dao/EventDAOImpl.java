package com.scsa.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.scsa.model.vo.EventInfo;

public class EventDAOImpl implements EventDAO {
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public boolean insertEvent(EventInfo event) {
		if(sqlSession.insert("event.insertEvent", event)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean deleteEvent(String eventId) {
		if(sqlSession.delete("event.deleteEvent", eventId)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updateEvent(EventInfo event) {
		if(sqlSession.delete("event.updateEvent", event)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<EventInfo> selectEventsByMeetingId(String meetingId) {
		return sqlSession.selectList("event.selectEventsByMeetingId", meetingId);
	}

	@Override
	public EventInfo selectEventByEventId(String eventId) {
		return sqlSession.selectOne("event.selectEventByEventId", eventId);
	}

}
