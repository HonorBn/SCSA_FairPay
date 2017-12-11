package com.scsa.data;

public class Claim {

    private String claimId;
    private String claimDate;
    private String totalPrice;
    private String claimerId;
    private String claimer_accountNumber;
    private String meetingId;
    private String receiptImg;
    private String paymentId;
    private int isPaid;
    private int tran_amt;
    private String claimeeId;
    
    public Claim() {
		super();
	}

	public Claim(String claimId, String claimDate, String totalPrice,
				String claimerId, String claimer_accountNumber,
				String meetingId, String receiptImg,
				int tran_amt, String claimeeId) {
        this.claimId = claimId;
        this.claimDate = claimDate;
        this.totalPrice = totalPrice;
        this.claimerId = claimerId;
        this.claimer_accountNumber = claimer_accountNumber;
        this.meetingId = meetingId;
        this.receiptImg = receiptImg;
        this.paymentId = "";
        this.isPaid = 0;
        this.tran_amt = tran_amt;
        this.claimeeId = claimeeId;
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

	public String getReceiptImg() {
		return receiptImg;
	}

	public void setReceiptImg(String receiptImg) {
		this.receiptImg = receiptImg;
	}

	public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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

    public String getClaimeeId() {
        return claimeeId;
    }

    public void setClaimeeId(String claimeeId) {
        this.claimeeId = claimeeId;
    }
}
