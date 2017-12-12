package com.scsa.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FriendsList {
	
	List<UserInfo> friendsList;

	public List<UserInfo> getFriendsList() {
		return friendsList;
	}

	@XmlElement(name="userInfo")
	public void setFriendsList(List<UserInfo> friendsList) {
		this.friendsList = friendsList;
	}
	
	
}
