package com.scsa.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.scsa.model.vo.FriendsInfo;
import com.scsa.model.vo.UserInfo;

public class FriendsDAOImpl implements FriendsDAO {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<UserInfo> selectFriendsById(String userId) {
		return sqlSession.selectList("friends.selectFriendsListById", userId);
	}

	public List<UserInfo> selectFriendsByName(String username) {
		return sqlSession.selectList("friends.", username);
	}

	public boolean insertFriend(FriendsInfo friend) {
		return sqlSession.insert("friends.insertFriend", friend) == 1 ? true : false;
	}

	public boolean deleteFriend(String userIdFrom, String userIdTo) {
		return sqlSession.delete("friends.deleteFriend", new FriendsInfo(userIdFrom, userIdTo)) == 1 ? true : false;
	}

	public List<UserInfo> selectFavoriteFriendsById(String userId) {
		return sqlSession.selectList("friends.selectFavoriteFriendsById", userId);
	}

	public boolean insertFavoriteFriend(String userIdFrom, String userIdTo) {
		return sqlSession.update("friends.insertFavoriteFriend", new FriendsInfo(userIdFrom, userIdTo)) == 1 ? true
				: false;
	}

	public boolean deleteFavoriteFriend(String userIdFrom, String userIdTo) {
		return sqlSession.update("friends.deleteFavoriteFriend", new FriendsInfo(userIdFrom, userIdTo)) == 1 ? true
				: false;
	}

}
