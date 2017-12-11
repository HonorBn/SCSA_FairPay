package com.scsa.model.dao;

import com.scsa.model.vo.ClaimeeInfo;

public interface ClaimeeDAO {
	
	boolean insertClaimee(ClaimeeInfo claimee);
	boolean updateClaimee(ClaimeeInfo claimee);
	boolean deleteClaimee(String paymentId);
	ClaimeeInfo selectClaimeeBefore(String paymentId);
	ClaimeeInfo selectClaimeeAfter(String paymentId);
/*	
	// 내가 청구한 claimee 리스트
	List<ClaimeeInfo> selectClaimeeListByClaimerId(String claimerId);
	
	// 내가 청구당한 claimee 리스트
	List<ClaimeeInfo> selectClaimeeListByClaimeeId(String claimeeId);
	
	// 내가 관여된 claimee 리스트
	List<ClaimeeInfo> selectClaimeeListById(String userId);
	
	// 지불완료된 claimee 리스트
	List<ClaimeeInfo> selectPaidClaimeeList(String userId);
	
	// 지불완료되지 않은 claimee 리스
	List<ClaimeeInfo> selectUnpaidClaimeeList(String userId);
	
	// ClaimId 얻기 -> Claim으로 연결되기 위해
	String selectClaimByPaymentId(String paymentId);
	
	// Claimee 얻기
	ClaimeeInfo selectClaimeeByPaymentId(String paymentId);*/
}
