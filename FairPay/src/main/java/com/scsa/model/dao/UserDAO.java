package com.scsa.model.dao;

import com.scsa.model.vo.UserInfo;

public interface UserDAO {
	boolean login(String userId, String password);
	boolean logout();
	boolean insertUser(UserInfo user);
	boolean updateUser(UserInfo user);
	UserInfo selectUserById(String userId);
	UserInfo selectUserByUserSeqNo(String userSeqNo);
}
