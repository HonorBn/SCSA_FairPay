package com.scsa.model.vo;

import java.util.List;

public class ClaimInfo {
	private String claimId;
	private String claimDate;
	private String totalPrice;
	private String claimerId;
	private UserInfo claimer;
	private String claimer_accountNumber;
    private String meetingId;
	private List<ClaimeeInfo> claimeeList;
	private List<ReceiptInfo> receiptList;
	
	public ClaimInfo() {
		super();
	}
	
	public ClaimInfo(String claimId, String claimDate, String totalPrice, String claimerId,
			String claimer_accountNumber, String meetingId) {
		this.claimId = claimId;
		this.claimDate = claimDate;
		this.totalPrice = totalPrice;
		this.claimerId = claimerId;
		this.claimer_accountNumber = claimer_accountNumber;
		this.meetingId = meetingId;
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	
	public UserInfo getClaimer() {
		return claimer;
	}

	public void setClaimer(UserInfo claimer) {
		this.claimer = claimer;
	}

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getClaimerId() {
		return claimerId;
	}

	public void setClaimerId(String claimerId) {
		this.claimerId = claimerId;
	}

	public String getClaimer_accountNumber() {
		return claimer_accountNumber;
	}

	public void setClaimer_accountNumber(String claimer_accountNumber) {
		this.claimer_accountNumber = claimer_accountNumber;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public List<ClaimeeInfo> getClaimeeList() {
		return claimeeList;
	}

	public void setClaimeeList(List<ClaimeeInfo> claimeeList) {
		this.claimeeList = claimeeList;
	}

	public List<ReceiptInfo> getReceiptList() {
		return receiptList;
	}

	public void setReceiptList(List<ReceiptInfo> receiptList) {
		this.receiptList = receiptList;
	}
	
}
