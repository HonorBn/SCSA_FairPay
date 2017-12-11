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
	public ClaimInfo getClaimWithClaimeeList(String claimId) {
		return claimDao.selectClaimWithClaimeeList(claimId);
	}

	@Override
	public ClaimInfo getClaimWithReceiptList(String claimId) {
		return claimDao.selectClaimWithReceiptList(claimId);
	}

	@Override
	public List<ClaimInfo> getClaimListByClaimerIdWithClaimeeList(String userId) {
		return claimDao.selectClaimListByClaimerIdWithClaimeeList(userId);
	}

	@Override
	public List<ClaimInfo> getClaimListByClaimerIdWithReceiptList(String userId) {
		return claimDao.selectClaimListByClaimerIdWithReceiptList(userId);
	}

	@Override
	public List<ClaimInfo> getClaimListByClaimeeIdWithClaimeeList(String userId) {
		return claimDao.selectClaimListByClaimeeIdWithClaimeeList(userId);
	}

	@Override
	public List<ClaimInfo> getClaimListByClaimeeIdWithReceiptList(String userId) {
		return claimDao.selectClaimListByClaimeeIdWithReceiptList(userId);
	}

	@Override
	public List<ClaimInfo> getClaimListByOurIdWithReceiptList(HashMap<String, String> ourId) {
		return claimDao.selectClaimListByOurIdWithReceiptList(ourId);
	}

	@Override
	public List<ClaimInfo> getClaimListWithinMeetingWithClaimeeList(String meetingId) {
		return claimDao.selectClaimListWithinMeetingWithClaimeeList(meetingId);
	}

	@Override
	public List<ClaimInfo> getClaimListWithinMeetingWithReceiptList(String meetingId) {
		return claimDao.selectClaimListWithinMeetingWithReceiptList(meetingId);
	}
	
}
