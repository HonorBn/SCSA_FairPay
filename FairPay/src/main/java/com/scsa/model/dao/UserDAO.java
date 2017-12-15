package com.scsa.model.dao;

import com.scsa.model.vo.UserInfo;

public interface UserDAO {
	boolean login(UserInfo user);
	boolean updateToken(UserInfo user);
	boolean logout();
	boolean insertUser(UserInfo user);
	boolean updateUser(UserInfo user);
	UserInfo selectUserById(String userId);
	UserInfo selectUserByUserSeqNo(String userSeqNo);
	UserInfo selectUserWithAccountListById(String userId);
}