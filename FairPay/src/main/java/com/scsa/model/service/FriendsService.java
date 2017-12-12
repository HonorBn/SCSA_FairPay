package com.scsa.model.service;

import java.util.List;

import com.scsa.model.vo.FriendsInfo;
import com.scsa.model.vo.UserInfo;

public interface FriendsService {
	
	// 사용자의 친구목록 조회해오기 (아이디로 검색)
	List<UserInfo> getFriendsById(String userId);
	
	// 사용자의 친구목록 조회해오기 (이름으로 검색)
	List<UserInfo> getFriendsByName(String username);
	
	// 친구 등록
	boolean addFriend(FriendsInfo friend );
	
	// 친구 삭제
	boolean removeFriend(String userIdFrom, String userIdTo);
	
	// 즐겨찾는 친구 검색 ( 아이디로 검색 )
	List<UserInfo> getFavoriteFriendsById(String userId);
	
	// 즐겨 찾는 친구 등록
	boolean addFavoriteFriend(String userIdFrom, String userIdTo);
	
	// 즐겨 찾는 친구 해제
	boolean removeFavoriteFriend(String userIdFrom, String userIdTo);
	
}
