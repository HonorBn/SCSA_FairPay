package com.scsa.model.dao;

import org.apache.ibatis.session.SqlSession;
import com.scsa.model.vo.UserInfo;

public class UserDAOImpl implements UserDAO {
	
	public SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public UserInfo login(UserInfo user) {
		System.out.println(user.getUserId());
		System.out.println(user.getPassword());
		UserInfo u = sqlSession.selectOne("user.login", user);
		System.out.println(u == null);
		return u;
	}
	
	public boolean updateToken(UserInfo user) {
		return sqlSession.update("user.updateToken", user) > 0;
	}
	
	public boolean logout() {//º¸·ù
		return false;
	}

	@Override
	public boolean insertUser(UserInfo user) {
		
		if(sqlSession.insert("user.insertUser", user)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updateUser(UserInfo user) {
		if( sqlSession.update("user.updateUser",user)>0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public UserInfo selectUserById(String userId) {
		return sqlSession.selectOne("user.selectUserById", userId);
	}

	@Override
	public UserInfo selectUserByUserSeqNo(String userSeqNo) {
		return sqlSession.selectOne("user.selectUserBySeqNo", userSeqNo);
	}

	@Override
	public UserInfo selectUserWithAccountListById(String userId) {
		return sqlSession.selectOne("user.selectUserWithAccountListById", userId);
	}
}
