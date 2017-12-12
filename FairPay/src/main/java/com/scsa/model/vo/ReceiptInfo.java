package com.scsa.model.vo;

public class ReceiptInfo {
	private String receiptId;
	private String receiptImg;
	private String claimId;
	
	public ReceiptInfo() {
		super();
	}

	public ReceiptInfo(String receiptImg, String claimId) {
		super();
		this.receiptImg = receiptImg;
		this.claimId = claimId;
	}

	public ReceiptInfo(String receiptId, String receiptImg, String claimId) {
		super();
		this.receiptId = receiptId;
		this.receiptImg = receiptImg;
		this.claimId = claimId;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public String getReceiptImg() {
		return receiptImg;
	}

	public void setReceiptImg(String receiptImg) {
		this.receiptImg = receiptImg;
	}

	public ReceiptInfo(String receiptImg) {
		super();
		this.receiptImg = receiptImg;
	}
	
	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	@Override
	public String toString() {
		return "ReceiptInfo [receiptId=" + receiptId + ", receiptImg=" + receiptImg + ", claimId=" + claimId + "]";
	}
	
}
