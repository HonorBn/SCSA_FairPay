package com.scsa.model.service;

import com.scsa.model.vo.ClaimeeInfo;

public interface ClaimeeService {
	
	boolean createClaimee(ClaimeeInfo claimee);
	boolean updateClaimee(ClaimeeInfo claimee);
	boolean deleteClaimee(String paymentId);
	ClaimeeInfo selectClaimeeBefore(String paymentId);
	ClaimeeInfo selectClaimeeAfter(String paymentId);
	
/*	// 내가 청구한 claimee 리스트
	List<ClaimeeInfo> getClaimeeListByClaimerId(String claimerId);
	
	// 내가 청구당한 claimee 리스트
	List<ClaimeeInfo> getClaimeeListByClaimeeId(String claimeeId);
	
	// 내가 관여된 claimee 리스트
	List<ClaimeeInfo> getClaimeeListById(String userId);
	
	// 지불완료된 claimee 리스트
	List<ClaimeeInfo> getPaidClaimeeList(String userId);
	
	// 지불완료되지 않은 claimee 리스트
	List<ClaimeeInfo> getUnpaidClaimeeList(String userId);
	
	// ClaimId 얻기 -> Claim으로 연결되기 위해
	String getClaimByPaymentId(String paymentId);
	
	// Claimee 얻기
	ClaimeeInfo getClaimeeByPaymentId(String paymentId);
	*/
	
}
