package com.scsa.model.service;

import java.util.List;

import com.scsa.model.dao.FriendsDAO;
import com.scsa.model.vo.UserInfo;

public class FriendsServiceImpl implements FriendsService {

	private FriendsDAO friendsDao;

	public void setFriendsDao(FriendsDAO friendsDao) {
		this.friendsDao = friendsDao;
	}

	public List<UserInfo> getFriendsById(String userId) {
		return friendsDao.selectFriendsById(userId);
	}

	public List<UserInfo> getFriendsByName(String username) {
		return friendsDao.selectFriendsByName(username);
	}

	public boolean addFriend(String userIdFrom, String userIdTo) {
		return friendsDao.insertFriend(userIdFrom, userIdTo);
	}

	public boolean removeFriend(String userIdFrom, String userIdTo) {
		return friendsDao.deleteFriend(userIdFrom, userIdTo);
	}

	public List<UserInfo> getFavoriteFriendsById(String userId) {
		return friendsDao.selectFavoriteFriendsById(userId);
	}

	public boolean addFavoriteFriend(String userIdFrom, String userIdTo) {
		return friendsDao.insertFavoriteFriend(userIdFrom, userIdTo);
	}

	public boolean removeFavoriteFriend(String userIdFrom, String userIdTo) {
		return friendsDao.deleteFavoriteFriend(userIdFrom, userIdTo);
	}

}
