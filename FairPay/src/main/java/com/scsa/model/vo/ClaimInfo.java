package com.scsa.model.vo;

import java.util.List;

public class ClaimInfo {
	
	private String claimId;
	private String claimDate;
	private String totalPrice;
	
	private UserInfo claimer;
	private MeetingInfo meeting;
	private AccountInfo account;
	
	private List<ClaimeeInfo> claimeeList;
	private List<ReceiptInfo> receiptList;
	
	public ClaimInfo() {
		super();
	}

	public ClaimInfo(String totalPrice, UserInfo claimer, MeetingInfo meeting, AccountInfo account,
			List<ClaimeeInfo> claimeeList, List<ReceiptInfo> receiptList) {
		super();
		this.totalPrice = totalPrice;
		this.claimer = claimer;
		this.meeting = meeting;
		this.account = account;
		this.claimeeList = claimeeList;
		this.receiptList = receiptList;
	}

	public ClaimInfo(String claimId, String claimDate, String totalPrice, UserInfo claimer, MeetingInfo meeting,
			AccountInfo account, List<ClaimeeInfo> claimeeList, List<ReceiptInfo> receiptList) {
		super();
		this.claimId = claimId;
		this.claimDate = claimDate;
		this.totalPrice = totalPrice;
		this.claimer = claimer;
		this.meeting = meeting;
		this.account = account;
		this.claimeeList = claimeeList;
		this.receiptList = receiptList;
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
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

	public UserInfo getClaimer() {
		return claimer;
	}

	public void setClaimer(UserInfo claimer) {
		this.claimer = claimer;
	}

	public MeetingInfo getMeeting() {
		return meeting;
	}

	public void setMeeting(MeetingInfo meeting) {
		this.meeting = meeting;
	}

	public AccountInfo getAccount() {
		return account;
	}

	public void setAccount(AccountInfo account) {
		this.account = account;
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

	@Override
	public String toString() {
		return "ClaimInfo [claimId=" + claimId + ", claimDate=" + claimDate + ", totalPrice=" + totalPrice
				+ ", claimer=" + claimer + ", meeting=" + meeting + ", account=" + account + ", claimeeList="
				+ claimeeList + ", receiptList=" + receiptList + "]";
	}
	
}	
