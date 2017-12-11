package com.scsa.model.vo;

public class MeetingInfo {
	private String meetingId;
	private String managerId;
	private String memberCount;
	private String groupName;
	private String meetingImage;
	
	public MeetingInfo() {
		super();
	}

	public MeetingInfo(String meetingId, String managerId, String memberCount, String groupName, String meetingImage) {
		super();
		this.meetingId = meetingId;
		this.managerId = managerId;
		this.memberCount = memberCount;
		this.groupName = groupName;
		this.meetingImage = meetingImage;
	}

	public MeetingInfo(String managerId, String memberCount, String groupName, String meetingImage) {
		super();
		this.managerId = managerId;
		this.memberCount = memberCount;
		this.groupName = groupName;
		this.meetingImage = meetingImage;
	}

	public MeetingInfo(String managerId, String memberCount, String groupName) {
		super();
		this.managerId = managerId;
		this.memberCount = memberCount;
		this.groupName = groupName;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(String memberCount) {
		this.memberCount = memberCount;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getMeetingImage() {
		return meetingImage;
	}

	public void setMeetingImage(String meetingImage) {
		this.meetingImage = meetingImage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MeetingInfo [getGroupId()=");
		builder.append(getMeetingId());
		builder.append(", getManagerId()=");
		builder.append(getManagerId());
		builder.append(", getMemberCount()=");
		builder.append(getMemberCount());
		builder.append(", getGroupName()=");
		builder.append(getGroupName());
		builder.append(", getMeetingImage()=");
		builder.append(getMeetingImage());
		builder.append("]");
		return builder.toString();
	}
	
}
