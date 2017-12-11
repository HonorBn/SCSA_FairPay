package com.scsa.model.service;

import java.util.HashMap;
import java.util.List;

import com.scsa.model.vo.ClaimInfo;

public interface ClaimService {
	
	boolean createClaim(ClaimInfo claim);
	boolean updateClaim(ClaimInfo claim);
	boolean removeClaim(String claimId);
	
	// Ư�� û�� �󼼳��� ��ȸ (��û��, ������ ��� ����)
	ClaimInfo getClaimWithClaimeeList(String claimId);
	ClaimInfo getClaimWithReceiptList(String claimId);
	
	// û���� & ��û���� id�� ��ȸ�ؼ� ����ȭ�� û��/��û�� ��� ��ȸ					// ī��ó�� �� �޽���, ��� �޽��� ���·� �Ѹ��� ���� method ���� ����
	List<ClaimInfo> getClaimListByClaimerIdWithClaimeeList(String userId);
	List<ClaimInfo> getClaimListByClaimerIdWithReceiptList(String userId);
	List<ClaimInfo> getClaimListByClaimeeIdWithClaimeeList(String userId);
	List<ClaimInfo> getClaimListByClaimeeIdWithReceiptList(String userId);
	
	// Ư�� ������ û�� ���� ���� ��ȸ 										// ī��ó�� �� �޽���, ��� �޽��� ���·� �Ѹ��� ����
	List<ClaimInfo> getClaimListByOurIdWithReceiptList(HashMap<String, String> ourId);
	
	// ���� �� ȸ�� ��� ���� ��ȸ
	List<ClaimInfo> getClaimListWithinMeetingWithClaimeeList(String meetingId);
	List<ClaimInfo> getClaimListWithinMeetingWithReceiptList(String meetingId);
	
}
