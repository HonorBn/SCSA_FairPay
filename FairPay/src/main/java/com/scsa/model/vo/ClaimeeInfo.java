package com.scsa.model.vo;

public class ClaimeeInfo {

	private String paymentId;
	private String paymentDate;
	private int isPaid;
	private int tran_amt;
	private String claimId;
	private String claimeeId;
	private String claimee_accountNumber;
	
	private String userName;
	private String accessToken;
	private String claim_accountNumber;
	
	public ClaimeeInfo() {
		super();
	}

	// 피청구 등록할 때 필요한 변수로 생성자 정의  
	public ClaimeeInfo(String paymentId, int tran_amt, String claimId, String claimeeId) {
		super();
		this.paymentId = paymentId;
		this.isPaid = 0;
		this.tran_amt = tran_amt;
		this.claimId = claimId;
		this.claimeeId = claimeeId;
	}
	
	// 납부 성공 후 나머지 멤버변수 입력
	public ClaimeeInfo(String paymentId, String paymentDate, int isPaid, int tran_amt, String claimId,
			String claimeeId, String claimee_accountNumber) {
		this(paymentId, tran_amt, claimId, claimeeId);
		this.isPaid = 0;
		this.paymentDate = paymentDate;
		this.claimee_accountNumber = claimee_accountNumber;
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
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getClaim_accountNumber() {
		return claim_accountNumber;
	}

	public void setClaim_accountNumber(String claim_accountNumber) {
		this.claim_accountNumber = claim_accountNumber;
	}

	@Override
	public String toString() {
		return "ClaimeeInfo [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", isPaid=" + isPaid
				+ ", tran_amt=" + tran_amt + ", claimId=" + claimId + ", claimeeId=" + claimeeId
				+ ", claimee_accountNumber=" + claimee_accountNumber + "]";
	}
	
}
