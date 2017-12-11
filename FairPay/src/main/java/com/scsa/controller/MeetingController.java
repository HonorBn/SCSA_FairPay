package com.scsa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scsa.model.service.DueService;
import com.scsa.model.service.EventClaimService;
import com.scsa.model.service.EventService;
import com.scsa.model.service.MeetingService;
import com.scsa.model.service.PhotoService;
import com.scsa.model.vo.DueInfo;
import com.scsa.model.vo.EventClaimInfo;
import com.scsa.model.vo.EventClaimList;
import com.scsa.model.vo.EventInfo;
import com.scsa.model.vo.EventList;
import com.scsa.model.vo.MeetingInfo;
import com.scsa.model.vo.MeetingList;
import com.scsa.model.vo.PhotoInfo;
import com.scsa.model.vo.PhotoList;
import com.scsa.model.vo.UserIdList;

@RestController
public class MeetingController {
	
	@Autowired
	private MeetingService meetingService;
	@Autowired
	private EventService eventService;
	@Autowired
	private EventClaimService eventClaimService;
	@Autowired
	private PhotoService photoService;
	@Autowired
	private DueService dueService;
	
	// 모임 생성  : done
	@RequestMapping(value = "/meeting", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addMeeting(@RequestBody MeetingInfo meeting) {
		System.out.println("모임 생성 요청 발생 : "+meeting);
		meeting.setMeetingImage("DummyImage");
		boolean result = meetingService.createMeeting(meeting);
		if (result) {
			return "모임이 등록되었습니다.";
		} else {
			return "모임등록에 실패하였습니다.";
		}
	}
	
	// 모임 리스트 호출  : done
	@RequestMapping(value = "/meetingList/{userId}", method = RequestMethod.GET)
	public MeetingList getMeetingList(@PathVariable String userId) {
		System.out.println("모임 리스트 요청 발생 userId : " + userId);
		MeetingList meetingList = new MeetingList();
		meetingList.setMeetingList(meetingService.searchMeetingList(userId));
		return meetingList;
	}
	
	// 모임 멤버 초대  : done
	@RequestMapping(value = "/meeting/member/post", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addGroupMembers(@RequestBody UserIdList userIdList2) {
		String groupId = userIdList2.getGroupId();
		List<String> userIdList = userIdList2.getUserIdList();
		System.out.println("모임 멤버 초대 요청 발생 groupId : " + groupId + " userIdList : " + userIdList.toString());
		boolean result = meetingService.createGroupMember(groupId, userIdList);
		if (result) {
			return "모임 초대 메시지를 보냈습니다.";
		} else {
			return "모임 초대에 실패했습니다.";
		}
	}
	
	// 모임원 리스트 가져오기  : done
	@RequestMapping(value = "/meeting/member/get/{groupId}", method = RequestMethod.GET)
	public UserIdList getGroupMemebersList(@PathVariable String groupId) {
		System.out.println("모임 멤버 리스트 요청 발생 groupId : " + groupId);
		UserIdList userIdList = new UserIdList();
		userIdList.setUserIdList(meetingService.searchGroupMemebers(groupId));
		return userIdList;
	}
	
	// 모임 삭제  : 보류
	@RequestMapping(value = "/meeting/delete/{meetingId}", method = RequestMethod.DELETE)
	public String deleteMeeting(@PathVariable String meetingId) {
		boolean result = meetingService.removeMeeting(meetingId);
		if (result) {
			return "모임 삭제에 성공했습니다.";
		} else {
			return "모임 삭제에 실패했습니다";
		}
	}
	
	// 모임 키워드 검색  : 보류
	@RequestMapping(value = "/meetings/{keyword}", method = RequestMethod.GET)
	public List<MeetingInfo> getMeetingListWithKeyword(@PathVariable String keyword, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		return meetingService.searchMeetingListByKeyword(keyword, userId);
	}
	
	// 일정 추가  : done
	@RequestMapping(value = "/event/post", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addEvent(@RequestBody EventInfo event) {
		System.out.println("일정추가 요청 발생 event : " + event);
		boolean result = eventService.createEvent(event);
		if (result) {
			return "일정이 등록되었습니다.";
		} else {
			return "일정등록에 실패하였습니다.";
		}
	}
	
	// 일정 삭제  : 보류
	@RequestMapping(value = "/event/delete/{eventId}", method = RequestMethod.DELETE)
	public String deleteEvent(@PathVariable String eventId) {
		boolean result = eventService.deleteEvent(eventId);
		if (result) {
			return "모임 삭제에 성공했습니다.";
		} else {
			return "모임 삭제에 실패했습니다";
		}
	}
	
	// 일정 변경  : 보류
	@RequestMapping(value = "/event/update", method = RequestMethod.PUT)
	public String updateEvent(@RequestBody EventInfo event) {
		boolean result = eventService.updateEvent(event);
		if (result) {
			return "모임이 변경되었습니다.";
		} else {
			return "모임변경에 실패하였습니다.";
		}
	}
	
	// 한 모임의 일정 정보 가져오기  : done
	@RequestMapping(value = "/event/get/{meetingId}", method = RequestMethod.GET)
	public EventList getEventList(@PathVariable String meetingId) {
		System.out.println("모임 일정 정보 요청 발생  meetingId : " + meetingId);
		EventList eventList = new EventList();
		eventList.setMeetingList(eventService.selectEventsByMeetingId(meetingId));
		return eventList;
	}

	// 일정 아이디로 일정 하나의 정보 가져오기  : done
	@RequestMapping(value = "/event/get/singleEvent/{eventId}", method = RequestMethod.GET)
	public EventInfo getEventByEventId(@PathVariable String eventId) {
		return eventService.selectEventByEventId(eventId);
	}
	
	
	// 회비 생성하기  : done
	@RequestMapping(value = "/due/post", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addDue(@RequestBody DueInfo due) {
		boolean result = dueService.createDue(due);
		if (result) {
			return "회비가 추가되었습니다.";
		} else {
			return "회비 추가에 실패하였습니다.";
		}
	}

	// 회비 삭제  : 보류
	@RequestMapping(value = "/due/delete/{dueId}", method = RequestMethod.DELETE)
	public String deleteDue(@PathVariable String dueId) {
		boolean result = dueService.deleteDue(dueId);
		if (result) {
			return "회비 삭제에 성공했습니다.";
		} else {
			return "회비 삭제에 실패했습니다";
		}
	}
	
	// 회비 변경  : 보류
	@RequestMapping(value = "/due/update", method = RequestMethod.PUT)
	public String updateDue(@RequestBody DueInfo due) {
		boolean result = dueService.updateDue(due);
		if (result) {
			return "회비가 변경되었습니다.";
		} else {
			return "회비변경에 실패하였습니다.";
		}
	}
	
	// 회비 아이디로 회비 하나의 정보 가져오기  : 보류
	@RequestMapping(value = "/due/{dueId}", method = RequestMethod.GET)
	public DueInfo getDue(@PathVariable String dueId) {
		return dueService.searchDue(dueId);
	}
	
	// 사진 일정에 첨부하기  : done
	@RequestMapping(value = "/photo/post", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addPhoto(@RequestBody PhotoInfo photo) {
		boolean result = photoService.createPhoto(photo);
		if (result) {
			return "사진이 추가되었습니다.";
		} else {
			return "사진 추가에 실패하였습니다.";
		}
	}

	// 사진 삭제  : 보류
	@RequestMapping(value = "/photo/delete/{photoId}", method = RequestMethod.DELETE)
	public String deletePhoto(@PathVariable String photoId) {
		boolean result = photoService.deletePhoto(photoId);
		if (result) {
			return "사진 삭제에 성공했습니다.";
		} else {
			return "사진 삭제에 실패했습니다";
		}
	}
	
	// 사진 변경  : 보류
	@RequestMapping(value = "/photo/update", method = RequestMethod.PUT)
	public String updatePhoto(@RequestBody PhotoInfo photo) {
		boolean result = photoService.updatePhoto(photo);
		if (result) {
			return "사진이 변경되었습니다.";
		} else {
			return "사진변경에 실패하였습니다.";
		}
	}
	
	// 일정 아이디로 사진 가져오기  : done
	@RequestMapping(value = "/photo/list/{eventId}", method = RequestMethod.GET)
	public PhotoList getPhotoList(@PathVariable String eventId) {
		PhotoList photoList = new PhotoList();
		photoList.setPhotoList(photoService.selectPhotoList(eventId));
		return photoList;
	}
	
	// 일정 결제 추가  : done
	@RequestMapping(value = "/eventClaim/post", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addEventClaim(@RequestBody EventClaimInfo eventClaim) {
		System.out.println("일정 결제 추가 요청 발생 : "+eventClaim.toString());
		boolean result = eventClaimService.createEventClaim(eventClaim);
		if (result) {
			return "일정결제가 등록되었습니다.";
		} else {
			return "일정결제 등록에 실패하였습니다.";
		}
	}
	
	// 일정 결제 삭제  : 보류
	@RequestMapping(value = "/eventClaim/delete/{eventClaimId}", method = RequestMethod.DELETE)
	public String deleteEventClaim(@PathVariable String eventClaimId) {
		boolean result = eventClaimService.deleteEventClaim(eventClaimId);
		if (result) {
			return "일정결제 삭제에 성공했습니다.";
		} else {
			return "일정결제 삭제에 실패했습니다";
		}
	}
	
	// 일정 결제 변경  : 보류
	@RequestMapping(value = "/eventClaim/update", method = RequestMethod.PUT)
	public String updateEventClaim(@RequestBody EventClaimInfo eventClaim) {
		boolean result = eventClaimService.updateEventClaim(eventClaim);
		if (result) {
			return "일정결제 변경되었습니다.";
		} else {
			return "일정결제 변경에 실패하였습니다.";
		}
	}
	
	// 일정 아이디로 일정 결제 정보 리스트 가져오기  : done
	@RequestMapping(value = "/eventClaim/list/{eventId}", method = RequestMethod.GET)
	public EventClaimList getEventClaimListByEventId(@PathVariable String eventId) {
		EventClaimList eventClaimList = new EventClaimList();
		eventClaimList.setEventClaimList(eventClaimService.searchEventClaimsByEventId(eventId));
		return eventClaimList;
	}

	// 일정 결제 아이디로 일정 결제 정보 가져오기
	@RequestMapping(value = "/eventClaim/get/{eventClaimId}", method = RequestMethod.GET)
	public EventClaimInfo getEventClaimByEventClaimId(@PathVariable String eventClaimId) {
		return eventClaimService.getEventClaim(eventClaimId);
	}
	
	
}
