package com.scsa.model.vo;

public class ReceiptInfo {
	
	private String receiptId;
	private String receiptImg;
	private String claimId;
	private String isClaim;
	
	public ReceiptInfo() {
		super();
	}

	public ReceiptInfo(String claimId, String isClaim) {
		super();
		this.claimId = claimId;
		this.isClaim = isClaim;
	}

	public ReceiptInfo(String receiptImg, String claimId, String isClaim) {
		super();
		this.receiptImg = receiptImg;
		this.claimId = claimId;
		this.isClaim = isClaim;
	}

	public ReceiptInfo(String receiptId, String receiptImg, String claimId, String isClaim) {
		super();
		this.receiptId = receiptId;
		this.receiptImg = receiptImg;
		this.claimId = claimId;
		this.isClaim = isClaim;
	}

	public String getIsClaim() {
		return isClaim;
	}

	public void setIsClaim(String isClaim) {
		this.isClaim = isClaim;
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
		StringBuilder builder = new StringBuilder();
		builder.append("ReceiptInfo [getIsClaim()=");
		builder.append(getIsClaim());
		builder.append(", getReceiptId()=");
		builder.append(getReceiptId());
		builder.append(", getReceiptImg()=");
		builder.append(getReceiptImg());
		builder.append(", getClaimId()=");
		builder.append(getClaimId());
		builder.append("]");
		return builder.toString();
	}

}
