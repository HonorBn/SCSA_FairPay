package com.scsa.model.service;

import com.scsa.model.vo.ClaimeeInfo;

public interface ClaimeeService {
	
	boolean createClaimee(ClaimeeInfo claimee);
	boolean updateClaimee(ClaimeeInfo claimee);
	boolean deleteClaimee(String paymentId);
	ClaimeeInfo selectClaimeeBefore(String paymentId);
	ClaimeeInfo selectClaimeeAfter(String paymentId);
	
/*	// ���� û���� claimee ����Ʈ
	List<ClaimeeInfo> getClaimeeListByClaimerId(String claimerId);
	
	// ���� û������ claimee ����Ʈ
	List<ClaimeeInfo> getClaimeeListByClaimeeId(String claimeeId);
	
	// ���� ������ claimee ����Ʈ
	List<ClaimeeInfo> getClaimeeListById(String userId);
	
	// ���ҿϷ�� claimee ����Ʈ
	List<ClaimeeInfo> getPaidClaimeeList(String userId);
	
	// ���ҿϷ���� ���� claimee ����Ʈ
	List<ClaimeeInfo> getUnpaidClaimeeList(String userId);
	
	// ClaimId ��� -> Claim���� ����Ǳ� ����
	String getClaimByPaymentId(String paymentId);
	
	// Claimee ���
	ClaimeeInfo getClaimeeByPaymentId(String paymentId);
	*/
	
}
