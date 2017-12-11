package com.scsa.model.service;

import com.scsa.model.dao.ClaimeeDAO;
import com.scsa.model.vo.ClaimeeInfo;

public class ClaimeeServiceImpl implements ClaimeeService {
	
	ClaimeeDAO claimeeDao;
	
	public void setClaimeeDao(ClaimeeDAO claimeeDao) {
		this.claimeeDao = claimeeDao;
	}
	
	@Override
	public boolean createClaimee(ClaimeeInfo claimee) {
		return claimeeDao.insertClaimee(claimee);
	}

	@Override
	public boolean updateClaimee(ClaimeeInfo claimee) {
		return claimeeDao.updateClaimee(claimee);
	}

	@Override
	public boolean deleteClaimee(String paymentId) {
		return claimeeDao.deleteClaimee(paymentId);
	}

	@Override
	public ClaimeeInfo selectClaimeeBefore(String paymentId) {
		return claimeeDao.selectClaimeeBefore(paymentId);
	}
	
	@Override
	public ClaimeeInfo selectClaimeeAfter(String paymentId) {
		return claimeeDao.selectClaimeeAfter(paymentId);
	}

/*	@Override
	public List<ClaimeeInfo> getClaimeeListByClaimerId(String claimerId) {
		return claimeeDao.selectClaimeeListByClaimerId(claimerId);
	}

	@Override
	public List<ClaimeeInfo> getClaimeeListByClaimeeId(String claimeeId) {
		return claimeeDao.selectClaimeeListByClaimeeId(claimeeId);
	}

	@Override
	public List<ClaimeeInfo> getClaimeeListById(String userId) {
		return claimeeDao.selectClaimeeListById(userId);
	}

	@Override
	public List<ClaimeeInfo> getPaidClaimeeList(String userId) {
		return claimeeDao.selectPaidClaimeeList(userId);
	}

	@Override
	public List<ClaimeeInfo> getUnpaidClaimeeList(String userId) {
		return claimeeDao.selectUnpaidClaimeeList(userId);
	}

	@Override
	public String getClaimByPaymentId(String paymentId) {
		return claimeeDao.selectClaimByPaymentId(paymentId);
	}

	@Override
	public ClaimeeInfo getClaimeeByPaymentId(String paymentId) {
		return claimeeDao.selectClaimeeByPaymentId(paymentId);
	} */

}
