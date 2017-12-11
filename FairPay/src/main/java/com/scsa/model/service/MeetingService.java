package com.scsa.model.service;

import java.util.List;

import com.scsa.model.vo.MeetingInfo;

public interface MeetingService {

	// 인자: 모임 만든 사람, 모임 정보
	boolean createMeeting(MeetingInfo meeting);

	boolean createGroupMember(String groupId, List<String> userIdList);
	
	List<String> searchGroupMemebers(String groupId);

	boolean removeMeeting(String meetingId);

	List<MeetingInfo> searchMeetingList(String userId);

	List<MeetingInfo> searchMeetingListByKeyword(String keyword, String userId);

}
