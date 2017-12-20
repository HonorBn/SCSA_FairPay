package com.scsa.model.service;

import java.util.List;

import com.scsa.model.vo.ClaimInfo;
import com.scsa.model.vo.ClaimeeInfo;

public interface ClaimeeService {
	
	boolean createClaimee(ClaimeeInfo claimee);
	boolean updateClaimee(ClaimeeInfo claimee);
	boolean deleteClaimee(String paymentId);
	ClaimInfo getClaimeeByPaymentId(String paymentId);
	List<ClaimInfo> getClaimeeListByPaymentId(String[] paymentIdList);
}
