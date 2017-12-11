package com.scsa.model.dao;

import org.apache.ibatis.session.SqlSession;

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
	public ClaimeeInfo selectClaimeeBefore(String paymentId) {
		return sqlSession.selectOne("claimee.selectClaimeeBefore", paymentId);
	}
	
	@Override
	public ClaimeeInfo selectClaimeeAfter(String paymentId) {
		return sqlSession.selectOne("claimee.selectClaimeeAfter", paymentId);
	}

/*	@Override
	public List<ClaimeeInfo> selectClaimeeListByClaimerId(String claimerId) {
		return session.selectList("claimee.selectClaimeeListByClaimerId", claimerId);
	}

	@Override
	public List<ClaimeeInfo> selectClaimeeListByClaimeeId(String claimeeId) {
		return session.selectList("claimee.selectClaimeeListByClaimeeId", claimeeId);
	}

	@Override
	public List<ClaimeeInfo> selectClaimeeListById(String userId) {
		return session.selectList("claimee.selectClaimeeListById", userId);
	}

	@Override
	public List<ClaimeeInfo> selectPaidClaimeeList(String userId) {
		return session.selectList("claimee.selectPaidClaimeeList", userId);
	}

	@Override
	public List<ClaimeeInfo> selectUnpaidClaimeeList(String userId) {
		return session.selectList("claimee.selectUnpaidClaimeeList", userId);
	}

	@Override
	public String selectClaimByPaymentId(String paymentId) {
		return session.selectOne("claimee.selectClaimByPaymentId", paymentId);
	}

	@Override
	public ClaimeeInfo selectClaimeeByPaymentId(String paymentId) {
		return session.selectOne("claimee.selectClaimeeByPaymentId", paymentId);
	}*/

}
