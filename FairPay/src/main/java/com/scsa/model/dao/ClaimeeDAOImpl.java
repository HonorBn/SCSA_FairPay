package com.scsa.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.scsa.model.vo.ClaimInfo;
import com.scsa.model.vo.ClaimeeInfo;

public class ClaimeeDAOImpl implements ClaimeeDAO {
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public boolean insertClaimee(ClaimeeInfo claimee) {
		if (sqlSession.insert("claimee.insertClaimee", claimee) > 0) return true;
		else return false;
	}

	@Override
	public boolean updateClaimee(ClaimeeInfo claimee) {
		if (sqlSession.update("claimee.updateClaimee", claimee) > 0) return true;
		else return false;
	}

	@Override
	public boolean deleteClaimee(String paymentId) {
		if (sqlSession.delete("claimee.deleteClaimee", paymentId) > 0) return true;
		else return false;
	}

	@Override
	public ClaimInfo selectClaimeeByPaymentId(String paymentId) {
		return sqlSession.selectOne("claimee.selectClaimeeByPaymentId", paymentId);
	}

	@Override
	public List<ClaimInfo> selectClaimeeListByPaymentId(String[] paymentIdList) {
		return sqlSession.selectList("claimee.selectClaimeeByPaymentId", paymentIdList);
	}
	
}
