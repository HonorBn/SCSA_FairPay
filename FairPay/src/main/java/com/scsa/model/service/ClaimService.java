package com.scsa.model.service;

import java.util.HashMap;
import java.util.List;

import com.scsa.model.vo.ClaimInfo;

public interface ClaimService {
	
	boolean createClaim(ClaimInfo claim);
	boolean updateClaim(ClaimInfo claim);
	boolean removeClaim(String claimId);
	
	// 특정 청구 상세내역 조회 (피청구, 영수증 목록 포함)
	ClaimInfo getClaimWithClaimeeList(String claimId);
	ClaimInfo getClaimWithReceiptList(String claimId);
	
	// 청구인 & 피청구인 id로 조회해서 메인화면 청구/피청구 목록 조회					// 카톡처럼 내 메시지, 상대 메시지 형태로 뿌리기 위해 method 따로 정의
	List<ClaimInfo> getClaimListByClaimerIdWithClaimeeList(String userId);
	List<ClaimInfo> getClaimListByClaimerIdWithReceiptList(String userId);
	List<ClaimInfo> getClaimListByClaimeeIdWithClaimeeList(String userId);
	List<ClaimInfo> getClaimListByClaimeeIdWithReceiptList(String userId);
	
	// 특정 대상과의 청구 관계 내역 조회 										// 카톡처럼 내 메시지, 상대 메시지 형태로 뿌리기 위해
	List<ClaimInfo> getClaimListByOurIdWithReceiptList(HashMap<String, String> ourId);
	
	// 모임 내 회비 모금 내역 조회
	List<ClaimInfo> getClaimListWithinMeetingWithClaimeeList(String meetingId);
	List<ClaimInfo> getClaimListWithinMeetingWithReceiptList(String meetingId);
	
}
