package com.scsa.model.service;

import com.scsa.model.vo.UserInfo;

public interface UserService {
	
	boolean login(String userId, String password);
	boolean logout();
	boolean addUser(UserInfo user);
	boolean updateUser(UserInfo user);
	UserInfo getUserById(String userId);
	UserInfo getUserByUserSeqNo(String userSeqNo);
	
}
