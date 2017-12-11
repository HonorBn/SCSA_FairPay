package com.scsa.model.dao;

import java.util.List;

import com.scsa.model.vo.MeetingInfo;

public interface MeetingDAO {

	boolean insertMeeting(MeetingInfo meeting);

	boolean insertGroupMember(String groupId, List<String> userIdList);
	
	List<String> selectGroupMemebers(String groupId);

	boolean deleteMeeting(String meetingId);

	List<MeetingInfo> selectMeetingList(String userId);

	List<MeetingInfo> selectMeetingListByKeyword(String keyword, String userId);

}
