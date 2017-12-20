package com.scsa.model.dao;

import java.util.List;

import com.scsa.model.vo.ClaimInfo;
import com.scsa.model.vo.ClaimeeInfo;

public interface ClaimeeDAO {
	
	boolean insertClaimee(ClaimeeInfo claimee);
	boolean updateClaimee(ClaimeeInfo claimee);
	boolean deleteClaimee(String paymentId);
	ClaimInfo selectClaimeeByPaymentId(String paymentId);
	List<ClaimInfo> selectClaimeeListByPaymentId(String[] paymentIdList); 
}
