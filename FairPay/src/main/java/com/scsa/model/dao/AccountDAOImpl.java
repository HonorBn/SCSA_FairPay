package com.scsa.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.scsa.model.vo.AccountInfo;

public class AccountDAOImpl implements AccountDAO {
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public boolean insertAccount(AccountInfo account) {
		if (sqlSession.insert("account.insertAccount", account) > 0) return true;
		else return false;
	}

	@Override
	public List<AccountInfo> selectAccountsByUserId(String userId) {
		return sqlSession.selectList("account.selectAccountByUserId", userId);

	}

	@Override
	public boolean deleteAccount(String accountNumber,String userId ) {
		if (sqlSession.delete("account.deleteAccount", new AccountInfo(accountNumber, userId)) > 0) return true;
		else return false;
	}

}
