package com.scsa.model.vo;

public class DueInfo {

	private String dueId;
	private int dueAmount;
	private String meetingId;
	public DueInfo() {
		super();
	}
	public DueInfo(int dueAmount, String meetingId) {
		super();
		this.dueAmount = dueAmount;
		this.meetingId = meetingId;
	}
	public DueInfo(String dueId, int dueAmount, String meetingId) {
		super();
		this.dueId = dueId;
		this.dueAmount = dueAmount;
		this.meetingId = meetingId;
	}
	public String getDueId() {
		return dueId;
	}
	public void setDueId(String dueId) {
		this.dueId = dueId;
	}
	public int getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(int dueAmount) {
		this.dueAmount = dueAmount;
	}
	public String getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DueInfo [getDueId()=");
		builder.append(getDueId());
		builder.append(", getDueAmount()=");
		builder.append(getDueAmount());
		builder.append(", getMeetingId()=");
		builder.append(getMeetingId());
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
