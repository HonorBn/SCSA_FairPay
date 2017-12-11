package com.scsa.data;

public class Balance {
	
	private String userId;
	private String accountNumber;
	
	public Balance(String userId, String accountNumber) {
		super();
		this.userId = userId;
		this.accountNumber = accountNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "Balance [userId=" + userId + ", accountNumber=" + accountNumber + "]";
	}
	
}	
