package com.scsa.model.service;

import java.util.List;

import com.scsa.model.dao.MeetingDAO;
import com.scsa.model.vo.MeetingInfo;

public class MeetingServiceImpl implements MeetingService {
	
	private MeetingDAO meetingDao;
	
	public void setMeetingDao(MeetingDAO meetingDao) {
		this.meetingDao = meetingDao;
	}

	@Override
	public boolean createMeeting(MeetingInfo meeting) {
		return meetingDao.insertMeeting(meeting);
	}

	@Override
	public boolean createGroupMember(String groupId, List<String> userIdList) {
		return meetingDao.insertGroupMember(groupId, userIdList);
	}

	@Override
	public boolean removeMeeting(String meetingId) {
		return meetingDao.deleteMeeting(meetingId);
	}

	@Override
	public List<MeetingInfo> searchMeetingList(String userId) {
		return meetingDao.selectMeetingList(userId);
	}

	@Override
	public List<MeetingInfo> searchMeetingListByKeyword(String keyword, String userId) {
		return meetingDao.selectMeetingListByKeyword(keyword, userId);
	}

	@Override
	public List<String> searchGroupMemebers(String groupId) {
		return meetingDao.selectGroupMemebers(groupId);
	}

}
