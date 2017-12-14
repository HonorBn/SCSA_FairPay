package com.scsa.model.dao;

import org.apache.ibatis.session.SqlSession;
import com.scsa.model.vo.UserInfo;

public class UserDAOImpl implements UserDAO {
	
	public SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public boolean login(UserInfo user) {
		Integer flag = sqlSession.selectOne("user.login", user);
		if(flag==null){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean updateToken(UserInfo user) {
		System.out.println("µµÂø");
		int a = sqlSession.update("user.updateToken", user);
		System.out.println(a);
		return a > 0;
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

}
