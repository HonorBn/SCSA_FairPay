package com.scsa.model.vo;

public class ClaimeeInfo {

	private String paymentId;
	private String paymentDate;
	private int isPaid;
	private int tran_amt;
	private String claimId;
	
	UserInfo claimee;
	AccountInfo account;
	
	public ClaimeeInfo() {
		super();
	}
	
	// 청구 등록할 때	(paymentId, paymentDate : 자동 입력)
	public ClaimeeInfo(int tran_amt, UserInfo claimee) {
		super();
		this.isPaid = 0;
		this.tran_amt = tran_amt;
		this.claimee = claimee;
	}
	
	// 청구 수락했을 때
	public ClaimeeInfo(String paymentId, String paymentDate, int isPaid, int tran_amt, String claimId,
			UserInfo claimee, AccountInfo account) {
		this(tran_amt, claimee);
		
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.isPaid = 0;
		this.tran_amt = tran_amt;
		this.claimId = claimId;
		this.claimee = claimee;
		this.account = account;
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

	public UserInfo getClaimee() {
		return claimee;
	}

	public void setClaimee(UserInfo claimee) {
		this.claimee = claimee;
	}

	public AccountInfo getAccount() {
		return account;
	}

	public void setAccount(AccountInfo account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "ClaimeeInfo [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", isPaid=" + isPaid
				+ ", tran_amt=" + tran_amt + ", claimId=" + claimId + ", claimee=" + claimee + ", account=" + account
				+ "]";
	}

}
