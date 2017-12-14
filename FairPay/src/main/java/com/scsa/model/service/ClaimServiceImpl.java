package com.scsa.model.service;

import java.util.HashMap;
import java.util.List;

import com.scsa.model.dao.ClaimDAO;
import com.scsa.model.vo.ClaimInfo;

public class ClaimServiceImpl implements ClaimService {
	
	ClaimDAO claimDao;
	
	public void setClaimDao(ClaimDAO claimDao) {
		this.claimDao = claimDao;
	}
	
	@Override
	public boolean createClaim(ClaimInfo claim) {
		return claimDao.insertClaim(claim);
	}

	@Override
	public boolean updateClaim(ClaimInfo claim) {
		return claimDao.updateClaim(claim);
	}

	@Override
	public boolean removeClaim(String claimId) {
		return claimDao.deleteClaim(claimId);
	}

	@Override
	public ClaimInfo getClaimDetail(String claimId) {
		return claimDao.selectClaimDetail(claimId);
	}

	@Override
	public List<ClaimInfo> getClaimListDetailByMyId(String userId) {
		return claimDao.selectClaimListDetailByMyId(userId);
	}
	
	@Override
	public List<ClaimInfo> getClaimListDetailByOurId(HashMap<String, String> ourId) {
		return claimDao.selectClaimListDetailByOurId(ourId);
	}

	@Override
	public List<ClaimInfo> getClaimListDetailWithinMeeting(String meetingId) {
		return claimDao.selectClaimListDetailWithinMeeting(meetingId);
	}
	
}
