package com.scsa.model.dao;

import java.util.HashMap;
import java.util.List;

import com.scsa.model.vo.ClaimInfo;

public interface ClaimDAO {
	
	boolean insertClaim(ClaimInfo claim);
	boolean updateClaim(ClaimInfo claim);
	boolean deleteClaim(String claimId);
	
	// 특정 청구 상세내역 조회 (피청구, 영수증 목록 포함)
	ClaimInfo selectClaimDetail(String claimId);
	
	// 청구인 & 피청구인 id로 조회해서 메인화면 청구/피청구 목록 조회
	List<ClaimInfo> selectClaimListDetailByMyId(String userId);
	
	// 특정 대상과의 청구 관계 내역 조회 
	List<ClaimInfo> selectClaimListDetailByOurId(HashMap<String, String> ourId);
	
	// 모임 내 회비 모금 내역 조회
	List<ClaimInfo> selectClaimListDetailWithinMeeting(String meetingId);
	
}
