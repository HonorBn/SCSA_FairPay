package com.scsa.model.vo;

public class ClaimeeInfo {

	private String paymentId;
	private String paymentDate;
	private int isPaid;
	private int tran_amt;
	private String claimId;
	private String claimeeId;
	private String claimee_accountNumber;

	private UserInfo claimeeUserInfo;
	
	public ClaimeeInfo() {
		super();
	}
	
	// 청구 등록할 때
	public ClaimeeInfo(int tran_amt, String claimId, UserInfo claimeeUserInfo) {
		super();
		this.isPaid = 0;
		this.tran_amt = tran_amt;
		this.claimId = claimId;
		this.claimeeId = claimeeUserInfo.getUserId();
		this.claimeeUserInfo = claimeeUserInfo;
	}
	
	// 청구 수락했을 때
	public ClaimeeInfo(String paymentId, String paymentDate, int isPaid, int tran_amt, String claimId,
			String claimeeId, String claimee_accountNumber, UserInfo claimeeUserInfo) {
		this(tran_amt, claimId, claimeeUserInfo);
		this.claimeeId = claimeeId;
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.isPaid = 0;
		this.tran_amt = tran_amt;
		this.claimId = claimId;
		this.claimeeId = claimeeId;
		this.claimee_accountNumber = claimee_accountNumber;
		this.claimeeUserInfo = claimeeUserInfo;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(int isPaid) {
		this.isPaid = isPaid;
	}

	public int getTran_amt() {
		return tran_amt;
	}

	public void setTran_amt(int tran_amt) {
		this.tran_amt = tran_amt;
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	
	public String getClaimeeId() {
		return claimeeId;
	}

	public void setClaimeeId(String claimeeId) {
		this.claimeeId = claimeeId;
	}

	public String getClaimee_accountNumber() {
		return claimee_accountNumber;
	}

	public void setClaimee_accountNumber(String claimee_accountNumber) {
		this.claimee_accountNumber = claimee_accountNumber;
	}

	public UserInfo getClaimeeUserInfo() {
		return claimeeUserInfo;
	}

	public void setClaimeeUserInfo(UserInfo claimeeUserInfo) {
		this.claimeeUserInfo = claimeeUserInfo;
	}

	@Override
	public String toString() {
		return "ClaimeeInfo [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", isPaid=" + isPaid
				+ ", tran_amt=" + tran_amt + ", claimId=" + claimId + ", claimeeId=" + claimeeId
				+ ", claimee_accountNumber=" + claimee_accountNumber + ", claimeeUserInfo=" + claimeeUserInfo + "]";
	}

}
