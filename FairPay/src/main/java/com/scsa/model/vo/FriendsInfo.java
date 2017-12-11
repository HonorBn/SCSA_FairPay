package com.scsa.model.vo;

public class FriendsInfo {
	private String userId;
	private String friendId;
	private int isFavorite;
	
	public FriendsInfo() {
		super();
	}
	public FriendsInfo(String userId, String friendId) {
		super();
		this.userId = userId;
		this.friendId = friendId;
	}
	public FriendsInfo(String userId, String friendId, int isFavorite) {
		super();
		this.userId = userId;
		this.friendId = friendId;
		this.isFavorite = isFavorite;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	
	
	public int getIsFavorite() {
		return isFavorite;
	}
	public void setIsFavorite(int isFavorite) {
		this.isFavorite = isFavorite;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FriendsInfo [getUserId()=");
		builder.append(getUserId());
		builder.append(", getFriendId()=");
		builder.append(getFriendId());
		builder.append(", getIsFavorite()=");
		builder.append(getIsFavorite());
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
