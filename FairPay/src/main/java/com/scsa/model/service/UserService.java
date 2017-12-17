package com.scsa.model.service;

import com.scsa.model.vo.UserInfo;

public interface UserService {
	
	boolean login(UserInfo user);
	boolean logout();
	boolean updateToken(UserInfo user);
	boolean addUser(UserInfo user);
	boolean updateUser(UserInfo user);
	UserInfo getUserById(String userId);
	UserInfo getUserByUserSeqNo(String userSeqNo);
	UserInfo getUserWithAccountListById(String userId);
	
}
