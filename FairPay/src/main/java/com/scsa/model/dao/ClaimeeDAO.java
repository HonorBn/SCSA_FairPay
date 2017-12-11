package com.scsa.model.dao;

import com.scsa.model.vo.ClaimeeInfo;

public interface ClaimeeDAO {
	
	boolean insertClaimee(ClaimeeInfo claimee);
	boolean updateClaimee(ClaimeeInfo claimee);
	boolean deleteClaimee(String paymentId);
	ClaimeeInfo selectClaimeeBefore(String paymentId);
	ClaimeeInfo selectClaimeeAfter(String paymentId);
/*	
	// ���� û���� claimee ����Ʈ
	List<ClaimeeInfo> selectClaimeeListByClaimerId(String claimerId);
	
	// ���� û������ claimee ����Ʈ
	List<ClaimeeInfo> selectClaimeeListByClaimeeId(String claimeeId);
	
	// ���� ������ claimee ����Ʈ
	List<ClaimeeInfo> selectClaimeeListById(String userId);
	
	// ���ҿϷ�� claimee ����Ʈ
	List<ClaimeeInfo> selectPaidClaimeeList(String userId);
	
	// ���ҿϷ���� ���� claimee ����
	List<ClaimeeInfo> selectUnpaidClaimeeList(String userId);
	
	// ClaimId ��� -> Claim���� ����Ǳ� ����
	String selectClaimByPaymentId(String paymentId);
	
	// Claimee ���
	ClaimeeInfo selectClaimeeByPaymentId(String paymentId);*/
}
