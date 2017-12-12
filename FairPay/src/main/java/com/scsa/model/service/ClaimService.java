package com.scsa.model.service;

import java.util.HashMap;
import java.util.List;

import com.scsa.model.vo.ClaimInfo;

public interface ClaimService {
	
	boolean createClaim(ClaimInfo claim);
	boolean updateClaim(ClaimInfo claim);
	boolean removeClaim(String claimId);
	
	// Ư�� û�� �󼼳��� ��ȸ (��û��, ������ ��� ����)
	ClaimInfo getClaimDetail(String claimId);
	
	// û���� & ��û���� id�� ��ȸ�ؼ� ����ȭ�� û��/��û�� ��� ��ȸ					// ī��ó�� �� �޽���, ��� �޽��� ���·� �Ѹ��� ���� method ���� ����
	List<ClaimInfo> getClaimListDetailByClaimerId(String userId);
	List<ClaimInfo> getClaimListDetailByClaimeeId(String userId);
	
	// Ư�� ������ û�� ���� ���� ��ȸ 										// ī��ó�� �� �޽���, ��� �޽��� ���·� �Ѹ��� ����
	List<ClaimInfo> getClaimListDetailByOurId(HashMap<String, String> ourId);
	
	// ���� �� ȸ�� ��� ���� ��ȸ
	List<ClaimInfo> getClaimListDetailWithinMeeting(String meetingId);
	
}
