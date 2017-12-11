package com.scsa.model.dao;

import org.apache.ibatis.session.SqlSession;
import com.scsa.model.vo.UserInfo;

public class UserDAOImpl implements UserDAO {
	
	public SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public boolean login(String userId, String password) {
		Integer flag = sqlSession.selectOne("user.login", new UserInfo(userId,password));
		if(flag==null){
			return false;
		}else{
			return true;
		}
	}

	public boolean logout() {//����
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
