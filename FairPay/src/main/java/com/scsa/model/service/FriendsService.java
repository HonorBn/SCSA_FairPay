package com.scsa.model.service;

import java.util.List;

import com.scsa.model.vo.FriendsInfo;
import com.scsa.model.vo.UserInfo;

public interface FriendsService {
	
	// ������� ģ����� ��ȸ�ؿ��� (���̵�� �˻�)
	List<UserInfo> getFriendsById(String userId);
	
	// ������� ģ����� ��ȸ�ؿ��� (�̸����� �˻�)
	List<UserInfo> getFriendsByName(String username);
	
	// ģ�� ���
	boolean addFriend(FriendsInfo friend );
	
	// ģ�� ����
	boolean removeFriend(String userIdFrom, String userIdTo);
	
	// ���ã�� ģ�� �˻� ( ���̵�� �˻� )
	List<UserInfo> getFavoriteFriendsById(String userId);
	
	// ��� ã�� ģ�� ���
	boolean addFavoriteFriend(String userIdFrom, String userIdTo);
	
	// ��� ã�� ģ�� ����
	boolean removeFavoriteFriend(String userIdFrom, String userIdTo);
	
}
