package com.scsa.model.vo;

public class UserInfo {
	private String userId;
	private String password;
	private String username;
	private String nickname;
	private String userSeqNo;
	private String authorizationCode;
	private String accessToken;
	private String fcmId;
	
	
	public UserInfo() {
		super();
	}

	public UserInfo(String userId, String password, String fcmId) {
		super();
		this.userId = userId;
		this.password = password;
		this.fcmId = fcmId;
	}


	public UserInfo(String userId, String username, String nickname, String fcmId) {
		super();
		this.userId = userId;
		this.username = username;
		this.nickname = nickname;
		this.fcmId = fcmId;
	}

	public UserInfo(String userId, String password, String username, String nickname, String fcmId) {
		super();
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.nickname = nickname;
		this.fcmId = fcmId;
	}
	public UserInfo(String userId, String password, String username, String nickname,String userSeqNo, String authorizationCode, String accessToken, String fcmId) {
		super();
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.nickname = nickname;
		this.userSeqNo = userSeqNo;
		this.authorizationCode = authorizationCode;
		this.accessToken = accessToken;
		this.fcmId = fcmId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserSeqNo() {
		return userSeqNo;
	}

	public void setUserSeqNo(String userSeqNo) {
		this.userSeqNo = userSeqNo;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getFcmId() {
		return fcmId;
	}

	public void setFcmId(String fcmId) {
		this.fcmId = fcmId;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", password=" + password + ", username=" + username + ", nickname="
				+ nickname + ", userSeqNo=" + userSeqNo + ", authorizationCode=" + authorizationCode + ", accessToken="
				+ accessToken + "]";
	}
	
	
}
