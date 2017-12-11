package com.scsa.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserIdList {
	private List<String> userIdList;
	private String groupId;

	public List<String> getUserIdList() {
		return userIdList;
	}

	@XmlElement(name="string")
	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

	public String getGroupId() {
		return groupId;
	}

	@XmlElement(name="groupId")
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public UserIdList() {
		super();
	}

	public UserIdList(List<String> userIdList, String groupId) {
		super();
		this.userIdList = userIdList;
		this.groupId = groupId;
	}

}
