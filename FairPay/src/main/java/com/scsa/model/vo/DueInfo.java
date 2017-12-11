package com.scsa.model.vo;

public class DueInfo {

	private String dueId;
	private int dueAmount;
	private String groupId;
	
	public DueInfo() {
		super();
	}

	public DueInfo(int dueAmount, String groupId) {
		super();
		this.dueAmount = dueAmount;
		this.groupId = groupId;
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DueInfo [getDueId()=");
		builder.append(getDueId());
		builder.append(", getDueAmount()=");
		builder.append(getDueAmount());
		builder.append(", getGroupId()=");
		builder.append(getGroupId());
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
