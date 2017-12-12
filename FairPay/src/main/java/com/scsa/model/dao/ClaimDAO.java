package com.scsa.model.dao;

import java.util.HashMap;
import java.util.List;

import com.scsa.model.vo.ClaimInfo;

public interface ClaimDAO {
	
	boolean insertClaim(ClaimInfo claim);
	boolean updateClaim(ClaimInfo claim);
	boolean deleteClaim(String claimId);
	
	// Ư�� û�� �󼼳��� ��ȸ (��û��, ������ ��� ����)
	ClaimInfo selectClaimDetail(String claimId);
	
	// û���� & ��û���� id�� ��ȸ�ؼ� ����ȭ�� û��/��û�� ��� ��ȸ					// ī��ó�� �� �޽���, ��� �޽��� ���·� �Ѹ��� ���� method ���� ����
	List<ClaimInfo> selectClaimListDetailByClaimerId(String userId);
	List<ClaimInfo> selectClaimListDetailByClaimeeId(String userId);
	
	// Ư�� ������ û�� ���� ���� ��ȸ 										// ī��ó�� �� �޽���, ��� �޽��� ���·� �Ѹ��� ����
	List<ClaimInfo> selectClaimListDetailByOurId(HashMap<String, String> ourId);
	
	// ���� �� ȸ�� ��� ���� ��ȸ
	List<ClaimInfo> selectClaimListDetailWithinMeeting(String meetingId);
	
}
