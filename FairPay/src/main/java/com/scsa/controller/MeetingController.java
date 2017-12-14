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
	
	// ���� ����  : done
	@RequestMapping(value = "/meeting", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addMeeting(@RequestBody MeetingInfo meeting) {
		System.out.println("���� ���� ��û �߻� : "+meeting);
		meeting.setMeetingImage("DummyImage");
		boolean result = meetingService.createMeeting(meeting);
		if (result) {
			return "������ ��ϵǾ����ϴ�.";
		} else {
			return "���ӵ�Ͽ� �����Ͽ����ϴ�.";
		}
	}
	
	// ���� ����Ʈ ȣ��  : done
	@RequestMapping(value = "/meetingList/{userId}", method = RequestMethod.GET)
	public MeetingList getMeetingList(@PathVariable String userId) {
		System.out.println("���� ����Ʈ ��û �߻� userId : " + userId);
		MeetingList meetingList = new MeetingList();
		meetingList.setMeetingList(meetingService.searchMeetingList(userId));
		return meetingList;
	}
	
	// ���� ��� �ʴ�  : done
	@RequestMapping(value = "/meeting/member/post", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addGroupMembers(@RequestBody UserIdList userIdList2) {
		String groupId = userIdList2.getGroupId();
		List<String> userIdList = userIdList2.getUserIdList();
		System.out.println("���� ��� �ʴ� ��û �߻� groupId : " + groupId + " userIdList : " + userIdList.toString());
		boolean result = meetingService.createGroupMember(groupId, userIdList);
		if (result) {
			return "���� �ʴ� �޽����� ���½��ϴ�.";
		} else {
			return "���� �ʴ뿡 �����߽��ϴ�.";
		}
	}
	
	// ���ӿ� ����Ʈ ��������  : done
	@RequestMapping(value = "/meeting/member/get/{groupId}", method = RequestMethod.GET)
	public UserIdList getGroupMemebersList(@PathVariable String groupId) {
		System.out.println("���� ��� ����Ʈ ��û �߻� groupId : " + groupId);
		UserIdList userIdList = new UserIdList();
		userIdList.setUserIdList(meetingService.searchGroupMemebers(groupId));
		return userIdList;
	}
	
	// ���� ����  : ����
	@RequestMapping(value = "/meeting/delete/{meetingId}", method = RequestMethod.DELETE)
	public String deleteMeeting(@PathVariable String meetingId) {
		boolean result = meetingService.removeMeeting(meetingId);
		if (result) {
			return "���� ������ �����߽��ϴ�.";
		} else {
			return "���� ������ �����߽��ϴ�";
		}
	}
	
	// ���� Ű���� �˻�  : ����
	@RequestMapping(value = "/meetings/{keyword}", method = RequestMethod.GET)
	public List<MeetingInfo> getMeetingListWithKeyword(@PathVariable String keyword, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		return meetingService.searchMeetingListByKeyword(keyword, userId);
	}
	
	// ���� �߰�  : done
	@RequestMapping(value = "/event/post", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addEvent(@RequestBody EventInfo event) {
		System.out.println("�����߰� ��û �߻� event : " + event);
		boolean result = eventService.createEvent(event);
		if (result) {
			return "������ ��ϵǾ����ϴ�.";
		} else {
			return "������Ͽ� �����Ͽ����ϴ�.";
		}
	}
	
	// ���� ����  : ����
	@RequestMapping(value = "/event/delete/{eventId}", method = RequestMethod.DELETE)
	public String deleteEvent(@PathVariable String eventId) {
		boolean result = eventService.deleteEvent(eventId);
		if (result) {
			return "���� ������ �����߽��ϴ�.";
		} else {
			return "���� ������ �����߽��ϴ�";
		}
	}
	
	// ���� ����  : ����
	@RequestMapping(value = "/event/update", method = RequestMethod.PUT)
	public String updateEvent(@RequestBody EventInfo event) {
		boolean result = eventService.updateEvent(event);
		if (result) {
			return "������ ����Ǿ����ϴ�.";
		} else {
			return "���Ӻ��濡 �����Ͽ����ϴ�.";
		}
	}
	
	// �� ������ ���� ���� ��������  : done
	@RequestMapping(value = "/event/get/{meetingId}", method = RequestMethod.GET)
	public EventList getEventList(@PathVariable String meetingId) {
		System.out.println("���� ���� ���� ��û �߻�  meetingId : " + meetingId);
		EventList eventList = new EventList();
		eventList.setMeetingList(eventService.selectEventsByMeetingId(meetingId));
		return eventList;
	}

	// ���� ���̵�� ���� �ϳ��� ���� ��������  : done
	@RequestMapping(value = "/event/get/singleEvent/{eventId}", method = RequestMethod.GET)
	public EventInfo getEventByEventId(@PathVariable String eventId) {
		return eventService.selectEventByEventId(eventId);
	}
	
	
	// ȸ�� �����ϱ�  : done
	@RequestMapping(value = "/due/post", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addDue(@RequestBody DueInfo due) {
		boolean result = dueService.createDue(due);
		if (result) {
			return "ȸ�� �߰��Ǿ����ϴ�.";
		} else {
			return "ȸ�� �߰��� �����Ͽ����ϴ�.";
		}
	}

	// ȸ�� ����  : ����
	@RequestMapping(value = "/due/delete/{dueId}", method = RequestMethod.DELETE)
	public String deleteDue(@PathVariable String dueId) {
		boolean result = dueService.deleteDue(dueId);
		if (result) {
			return "ȸ�� ������ �����߽��ϴ�.";
		} else {
			return "ȸ�� ������ �����߽��ϴ�";
		}
	}
	
	// ȸ�� ����  : ����
	@RequestMapping(value = "/due/update", method = RequestMethod.PUT)
	public String updateDue(@RequestBody DueInfo due) {
		boolean result = dueService.updateDue(due);
		if (result) {
			return "ȸ�� ����Ǿ����ϴ�.";
		} else {
			return "ȸ�񺯰濡 �����Ͽ����ϴ�.";
		}
	}
	
	// ȸ�� ���̵�� ȸ�� �ϳ��� ���� ��������  : ����
	@RequestMapping(value = "/due/{dueId}", method = RequestMethod.GET)
	public DueInfo getDue(@PathVariable String dueId) {
		return dueService.searchDue(dueId);
	}
	
	// ���� ������ ÷���ϱ�  : done
	@RequestMapping(value = "/photo/post", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addPhoto(@RequestBody PhotoInfo photo) {
		boolean result = photoService.createPhoto(photo);
		if (result) {
			return "������ �߰��Ǿ����ϴ�.";
		} else {
			return "���� �߰��� �����Ͽ����ϴ�.";
		}
	}

	// ���� ����  : ����
	@RequestMapping(value = "/photo/delete/{photoId}", method = RequestMethod.DELETE)
	public String deletePhoto(@PathVariable String photoId) {
		boolean result = photoService.deletePhoto(photoId);
		if (result) {
			return "���� ������ �����߽��ϴ�.";
		} else {
			return "���� ������ �����߽��ϴ�";
		}
	}
	
	// ���� ����  : ����
	@RequestMapping(value = "/photo/update", method = RequestMethod.PUT)
	public String updatePhoto(@RequestBody PhotoInfo photo) {
		boolean result = photoService.updatePhoto(photo);
		if (result) {
			return "������ ����Ǿ����ϴ�.";
		} else {
			return "�������濡 �����Ͽ����ϴ�.";
		}
	}
	
	// ���� ���̵�� ���� ��������  : done
	@RequestMapping(value = "/photo/list/{eventId}", method = RequestMethod.GET)
	public PhotoList getPhotoList(@PathVariable String eventId) {
		PhotoList photoList = new PhotoList();
		photoList.setPhotoList(photoService.selectPhotoList(eventId));
		return photoList;
	}
	
	// ���� ���� �߰�  : done
	@RequestMapping(value = "/eventClaim/post", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addEventClaim(@RequestBody EventClaimInfo eventClaim) {
		System.out.println("���� ���� �߰� ��û �߻� : "+eventClaim.toString());
		boolean result = eventClaimService.createEventClaim(eventClaim);
		if (result) {
			return "���������� ��ϵǾ����ϴ�.";
		} else {
			return "�������� ��Ͽ� �����Ͽ����ϴ�.";
		}
	}
	
	// ���� ���� ����  : ����
	@RequestMapping(value = "/eventClaim/delete/{eventClaimId}", method = RequestMethod.DELETE)
	public String deleteEventClaim(@PathVariable String eventClaimId) {
		boolean result = eventClaimService.deleteEventClaim(eventClaimId);
		if (result) {
			return "�������� ������ �����߽��ϴ�.";
		} else {
			return "�������� ������ �����߽��ϴ�";
		}
	}
	
	// ���� ���� ����  : ����
	@RequestMapping(value = "/eventClaim/update", method = RequestMethod.PUT)
	public String updateEventClaim(@RequestBody EventClaimInfo eventClaim) {
		boolean result = eventClaimService.updateEventClaim(eventClaim);
		if (result) {
			return "�������� ����Ǿ����ϴ�.";
		} else {
			return "�������� ���濡 �����Ͽ����ϴ�.";
		}
	}
	
	// ���� ���̵�� ���� ���� ���� ����Ʈ ��������  : done
	@RequestMapping(value = "/eventClaim/list/{eventId}", method = RequestMethod.GET)
	public EventClaimList getEventClaimListByEventId(@PathVariable String eventId) {
		System.out.println("���� ���̵�� ���� ���� ���� ��û : eventId "+eventId);
		EventClaimList eventClaimList = new EventClaimList();
		eventClaimList.setEventClaimList(eventClaimService.searchEventClaimsByEventId(eventId));
		return eventClaimList;
	}

	// ���� ���� ���̵�� ���� ���� ���� ��������  : done
	@RequestMapping(value = "/eventClaim/get/{eventClaimId}", method = RequestMethod.GET)
	public EventClaimInfo getEventClaimByEventClaimId(@PathVariable String eventClaimId) {
		return eventClaimService.getEventClaim(eventClaimId);
	}
	
	
}
