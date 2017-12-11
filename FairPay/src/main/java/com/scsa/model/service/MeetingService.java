package com.scsa.model.service;

import java.util.List;

import com.scsa.model.vo.MeetingInfo;

public interface MeetingService {

	// ����: ���� ���� ���, ���� ����
	boolean createMeeting(MeetingInfo meeting);

	boolean createGroupMember(String groupId, List<String> userIdList);
	
	List<String> searchGroupMemebers(String groupId);

	boolean removeMeeting(String meetingId);

	List<MeetingInfo> searchMeetingList(String userId);

	List<MeetingInfo> searchMeetingListByKeyword(String keyword, String userId);

}
