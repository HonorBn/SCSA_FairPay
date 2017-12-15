package com.scsa.model.vo;

public class AccountInfo {

	private String accountNumber;
	private String bankCode;
	private String userId;
	private String accountAlias;
	
	public AccountInfo() {
		super();
	}
	
	public AccountInfo(String accountNumber) {
		super();
		this.accountNumber = accountNumber;
	}

	public AccountInfo(String accountNumber, String userId) {
		super();
		this.accountNumber = accountNumber;
		this.userId = userId;
	}

	public AccountInfo(String accountNumber, String bankCode, String userId) {
		super();
		this.accountNumber = accountNumber;
		this.bankCode = bankCode;
		this.userId = userId;
	}
	
	public AccountInfo(String accountNumber, String bankCode, String userId,String accountAlias) {
		super();
		this.accountNumber = accountNumber;
		this.bankCode = bankCode;
		this.userId = userId;
		this.accountAlias = accountAlias;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountAlias() {
		return accountAlias;
	}

	public void setAccountAlias(String accountAlias) {
		this.accountAlias = accountAlias;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountInfo [accountNumber=");
		builder.append(accountNumber);
		builder.append(", bankCode=");
		builder.append(bankCode);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", accountAlias=");
		builder.append(accountAlias);
		builder.append("]");
		return builder.toString();
	}
}
