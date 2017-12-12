package com.scsa.model.vo;

public class UserInfo {
	private String userId;
	private String password;
	private String username;
	private String nickname;
	private String userSegNo;
	private String authorizationCode;
	private String accessToken;
	
	
	public UserInfo() {
		super();
	}

	public UserInfo(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}


	public UserInfo(String userId, String username, String nickname) {
		super();
		this.userId = userId;
		this.username = username;
		this.nickname = nickname;
	}

	public UserInfo(String userId, String password, String username, String nickname) {
		super();
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.nickname = nickname;
	}
	public UserInfo(String userId, String password, String username, String nickname,String userSegNo, String authorizationCode,String accessToken) {
		super();
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.nickname = nickname;
		this.userSegNo = userSegNo;
		this.authorizationCode = authorizationCode;
		this.accessToken = accessToken;
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


	public String getUserSegNo() {
		return userSegNo;
	}
	public void setUserSegNo(String userSegNo) {
		this.userSegNo = userSegNo;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserInfo [userId=");
		builder.append(userId);
		builder.append(", password=");
		builder.append(password);
		builder.append(", username=");
		builder.append(username);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", userSegNo=");
		builder.append(userSegNo);
		builder.append(", authorizationCode=");
		builder.append(authorizationCode);
		builder.append(", accessToken=");
		builder.append(accessToken);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
