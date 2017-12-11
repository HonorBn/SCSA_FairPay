package com.scsa.model.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.scsa.model.vo.ClaimInfo;

public class ClaimDAOImpl implements ClaimDAO {
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public boolean insertClaim(ClaimInfo claim) {
		if (sqlSession.insert("claim.insertClaim", claim) > 0) return true;
		else return false;
	}

	@Override
	public boolean updateClaim(ClaimInfo claim) {
		if (sqlSession.update("claim.updateClaim", claim) > 0) return true;
		else return false;
	}

	@Override
	public boolean deleteClaim(String claimId) {
		if (sqlSession.delete("claim.deleteClaim", claimId) > 0) return true;
		else return false;
	}

	@Override
	public ClaimInfo selectClaimWithClaimeeList(String claimId) {
		return sqlSession.selectOne("claim.selectClaimWithClaimeeList", claimId);
	}

	@Override
	public ClaimInfo selectClaimWithReceiptList(String claimId) {
		return sqlSession.selectOne("claim.selectClaimWithReceiptList", claimId);
	}

	@Override
	public List<ClaimInfo> selectClaimListByClaimerIdWithClaimeeList(String userId) {
		return sqlSession.selectList("claim.selectClaimListByClaimerIdWithClaimeeList", userId);
	}

	@Override
	public List<ClaimInfo> selectClaimListByClaimerIdWithReceiptList(String userId) {
		return sqlSession.selectList("claim.selectClaimListByClaimerIdWithReceiptList", userId);
	}

	@Override
	public List<ClaimInfo> selectClaimListByClaimeeIdWithClaimeeList(String userId) {
		return sqlSession.selectList("claim.selectClaimListByClaimeeIdWithClaimeeList", userId);
	}

	@Override
	public List<ClaimInfo> selectClaimListByClaimeeIdWithReceiptList(String userId) {
		return sqlSession.selectList("claim.selectClaimListByClaimeeIdWithReceiptList", userId);
	}

	@Override
	public List<ClaimInfo> selectClaimListByOurIdWithReceiptList(HashMap<String, String> ourId) {
		return sqlSession.selectList("claim.selectClaimListByOurIdWithReceiptList", ourId);
	}

	@Override
	public List<ClaimInfo> selectClaimListWithinMeetingWithClaimeeList(String meetingId) {
		return sqlSession.selectList("claim.selectClaimListWithinMeetingWithClaimeeList", meetingId);
	}

	@Override
	public List<ClaimInfo> selectClaimListWithinMeetingWithReceiptList(String meetingId) {
		return sqlSession.selectList("claim.selectClaimListWithinMeetingWithReceiptList", meetingId);
	}
}
