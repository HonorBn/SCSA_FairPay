package com.scsa.model.service;

import java.util.HashMap;
import java.util.List;

import com.scsa.model.vo.ClaimInfo;

public interface ClaimService {
	
	boolean createClaim(ClaimInfo claim);
	boolean updateClaim(ClaimInfo claim);
	boolean removeClaim(String claimId);
	
	// 특정 청구 상세내역 조회 (피청구, 영수증 목록 포함)
	ClaimInfo getClaimDetail(String claimId);
	
	// 청구인 & 피청구인 id로 조회해서 메인화면 청구/피청구 목록 조회
	List<ClaimInfo> getClaimListDetailByMyId(String userId);
	
	// 특정 대상과의 청구 관계 내역 조회
	List<ClaimInfo> getClaimListDetailByOurId(HashMap<String, String> ourId);
	
	// 모임 내 회비 모금 내역 조회
	List<ClaimInfo> getClaimListDetailWithinMeeting(String meetingId);
	
}
