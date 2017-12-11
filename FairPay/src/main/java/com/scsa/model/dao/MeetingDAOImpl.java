package com.scsa.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.scsa.model.vo.MeetingInfo;

public class MeetingDAOImpl implements MeetingDAO {

	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public boolean insertMeeting(MeetingInfo meeting) {
		String meetingId = sqlSession.selectOne("selectMeetingIdBeforeInsertion")+"";
		meeting.setMeetingId(meetingId);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", meetingId);
		map.put("userId", meeting.getManagerId());
		if(sqlSession.insert("meeting.insertMeeting", meeting)>0 
				&& sqlSession.insert("meeting.insertGroupMember", map)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean insertGroupMember(String groupId, List<String> userIdList) {
		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("memberCount", userIdList.size());
//		sqlSession.update("meeting.updateMeetingMemberCount", map);
		map.put("groupId", groupId);
		for(String userId : userIdList){
			map.put("userId", userId);
			sqlSession.insert("meeting.insertGroupMember", map);
		}
		return true;
		
	}

	@Override
	public boolean deleteMeeting(String meetingId) {
		int count = sqlSession.selectOne("meeting.selectMeeting", meetingId);
		for(int i = 0 ; i < count ; i++){
			sqlSession.delete("meeting.preDeleteMeeting", meetingId);
		}
		if(sqlSession.delete("meeting.deleteMeeting", meetingId)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<MeetingInfo> selectMeetingList(String userId) {
		return sqlSession.selectList("meeting.selectMeetingList", userId);
	}

	@Override
	public List<MeetingInfo> selectMeetingListByKeyword(String keyword, String userId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("userId", userId);
		return sqlSession.selectList("meeting.selectMeetingListByKeyword", userId);
	}

	@Override
	public List<String> selectGroupMemebers(String groupId) {
		return sqlSession.selectList("meeting.selectGroupMemebers", groupId);
	}

}
