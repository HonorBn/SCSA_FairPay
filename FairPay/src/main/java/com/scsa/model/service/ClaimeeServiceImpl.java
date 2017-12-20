package com.scsa.model.service;

import java.util.List;

import com.scsa.model.dao.ClaimeeDAO;
import com.scsa.model.vo.ClaimInfo;
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
	public ClaimInfo getClaimeeByPaymentId(String paymentId) {
		return claimeeDao.selectClaimeeByPaymentId(paymentId);
	}

	@Override
	public List<ClaimInfo> getClaimeeListByPaymentId(String[] paymentIdList) {
		return claimeeDao.selectClaimeeListByPaymentId(paymentIdList);
	}
	
}
