package com.scsa.model.dao;

import java.util.List;

import com.scsa.model.vo.FriendsInfo;
import com.scsa.model.vo.UserInfo;

public interface FriendsDAO {
	
	List<UserInfo> selectFriendsById(String userId);
	
	List<UserInfo> selectFriendsByName(String username);
	
	boolean insertFriend(FriendsInfo friend);
	
	boolean deleteFriend(String userIdFrom, String userIdTo);
	
	List<UserInfo> selectFavoriteFriendsById(String userId);
	
	boolean insertFavoriteFriend(String userIdFrom, String userIdTo);
	
	boolean deleteFavoriteFriend(String userIdFrom, String userIdTo);
}
