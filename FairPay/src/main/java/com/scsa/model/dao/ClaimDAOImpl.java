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
	public ClaimInfo selectClaimDetail(String claimId) {
		return sqlSession.selectOne("claim.selectClaimDetail", claimId);
	}

	@Override
	public List<ClaimInfo> selectClaimListDetailByMyId(String userId) {
		return sqlSession.selectList("claim.selectClaimListDetailByMyId", userId);
	}
	
	@Override
	public List<ClaimInfo> selectClaimListDetailByOurId(HashMap<String, String> ourId) {
		return sqlSession.selectList("claim.selectClaimListDetailByOurId", ourId);
	}

	@Override
	public List<ClaimInfo> selectClaimListDetailWithinMeeting(String meetingId) {
		return sqlSession.selectList("claim.selectClaimListDetailWithinMeeting", meetingId);
	}
}
