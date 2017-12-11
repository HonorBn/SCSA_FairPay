package com.scsa.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.scsa.model.vo.DueInfo;

public class DueDAOImpl implements DueDAO {
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public boolean insertDue(DueInfo due) {
		if(sqlSession.insert("due.insertDue", due)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean deleteDue(String dueId) {
		if(sqlSession.delete("due.deleteDue", dueId)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updateDue(DueInfo due) {
		if(sqlSession.update("due.updateDue", due)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public DueInfo selectDue(String dueId) {
		return sqlSession.selectOne("due.selectDue", dueId);
	}

}
