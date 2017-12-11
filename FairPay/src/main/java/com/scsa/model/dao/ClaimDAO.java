package com.scsa.model.dao;

import java.util.HashMap;
import java.util.List;

import com.scsa.model.vo.ClaimInfo;

public interface ClaimDAO {
	
	boolean insertClaim(ClaimInfo claim);
	boolean updateClaim(ClaimInfo claim);
	boolean deleteClaim(String claimId);
	
	// Ư�� û�� �󼼳��� ��ȸ (��û��, ������ ��� ����)
	ClaimInfo selectClaimWithClaimeeList(String claimId);
	ClaimInfo selectClaimWithReceiptList(String claimId);
	
	// û���� & ��û���� id�� ��ȸ�ؼ� ����ȭ�� û��/��û�� ��� ��ȸ					// ī��ó�� �� �޽���, ��� �޽��� ���·� �Ѹ��� ���� method ���� ����
	List<ClaimInfo> selectClaimListByClaimerIdWithClaimeeList(String userId);
	List<ClaimInfo> selectClaimListByClaimerIdWithReceiptList(String userId);
	List<ClaimInfo> selectClaimListByClaimeeIdWithClaimeeList(String userId);
	List<ClaimInfo> selectClaimListByClaimeeIdWithReceiptList(String userId);
	
	// Ư�� ������ û�� ���� ���� ��ȸ 										// ī��ó�� �� �޽���, ��� �޽��� ���·� �Ѹ��� ����
	List<ClaimInfo> selectClaimListByOurIdWithReceiptList(HashMap<String, String> ourId);
	
	// ���� �� ȸ�� ��� ���� ��ȸ
	List<ClaimInfo> selectClaimListWithinMeetingWithClaimeeList(String meetingId);
	List<ClaimInfo> selectClaimListWithinMeetingWithReceiptList(String meetingId);
}
