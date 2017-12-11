package com.scsa.model.service;

import com.scsa.model.dao.UserDAO;
import com.scsa.model.vo.UserInfo;

public class UserServiceImpl implements UserService {
	
	private UserDAO userDao;
	
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public boolean login(String userId, String password) {
		
		return userDao.login(userId, password);

	}

	public boolean logout() {
		return userDao.logout();

	}

	public boolean addUser(UserInfo user) {
		return userDao.insertUser(user);
	}

	public boolean updateUser(UserInfo user) {

		return userDao.updateUser(user);
	}

	public UserInfo getUserById(String userId) {
		return userDao.selectUserById(userId);
	}

	@Override
	public UserInfo getUserByUserSeqNo(String userSeqNo) {
		return userDao.selectUserByUserSeqNo(userSeqNo);
	}

}
