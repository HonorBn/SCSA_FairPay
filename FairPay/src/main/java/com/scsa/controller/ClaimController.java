package com.scsa.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scsa.data.Claim;
import com.scsa.data.Ours;
import com.scsa.data.Pay;
import com.scsa.model.service.BankAPIService;
import com.scsa.model.service.ClaimService;
import com.scsa.model.service.ClaimeeService;
import com.scsa.model.service.ReceiptService;
import com.scsa.model.vo.ClaimInfo;
import com.scsa.model.vo.ClaimeeInfo;
import com.scsa.model.vo.ReceiptInfo;

@RestController
public class ClaimController {

	@Autowired
	private ClaimService claimService;
	@Autowired
	private ClaimeeService claimeeService;
	@Autowired
	private ReceiptService receiptService;
	@Autowired
	private BankAPIService bankAPIService;

	// û�� ��� �Ŀ� ��û���� ������ ���
	@RequestMapping(value = "/claim", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String addClaimInfo(@RequestBody Claim claim) {

		ClaimInfo claimInfo = new ClaimInfo("", claim.getClaimDate(), claim.getTotalPrice(), claim.getClaimerId(),
				claim.getClaimer_accountNumber(), claim.getMeetingId(), claim.getReceiptImg());

		// û�� ���
		claimService.createClaim(claimInfo);
		claim.setClaimId(claimInfo.getClaimId());

		// ��û�� ���
		addClaimeeInfo(claim);

		// ������ ���
		if (claim.getReceiptImg() != null)
			addReceiptInfo(claim);

		return "��Ͽ� �����Ͽ����ϴ�";
	}

	// ��û�� ���
	private void addClaimeeInfo(Claim claim) {
		ClaimeeInfo claimeeInfo = new ClaimeeInfo("", claim.getTran_amt(), claim.getClaimId(), claim.getClaimeeId());
		claimeeService.createClaimee(claimeeInfo);
	}

	// ������ ���
	public void addReceiptInfo(Claim claim) {
		ReceiptInfo receiptInfo = new ReceiptInfo("", claim.getReceiptImg(), claim.getClaimId());
		receiptService.createReceipt(receiptInfo);
	}

	// û�� ����, û�� ���� ���� ����
	
	
	// ��û�� ���� (û�� ���� �Ϸ����� ��)
	@RequestMapping(value = "/claim", method = RequestMethod.PUT)
	public void updateClaimee(@RequestBody Pay pay) {

		// ����� ���� ����
		ClaimeeInfo claimee = claimeeService.selectClaimeeBefore(pay.getPaymentId());
		claimee.setClaimee_accountNumber(pay.getClaimee_accountNumber());

		/*
		 * ��� API ȣ��
		 * Input : claimee (��û���� accessToken, �Աݰ������ڳ���, ��ݰ��� ����ũ��ȣ, �ŷ��ݾ�, ��û�Ͻ�[����])
		 * �Ա� API ȣ��
		 * Input : claimee (�̿��� accessToken, �Ա���ü���ȣ����, ��ݰ������ڳ���, �Աݰ��� ����ũ��ȣ, �ӱݰ������ڳ���, �ŷ��ݾ�, ��û�Ͻ�)
		 */
		bankAPIService.transfer(claimee);

		claimee = claimeeService.selectClaimeeAfter(pay.getPaymentId());
		claimee.setClaimee_accountNumber(claimee.getClaimee_accountNumber());
		claimee.setIsPaid(1);
		claimee.setPaymentDate(new Date(System.currentTimeMillis()).toString());

		claimeeService.updateClaimee(claimee);
		
	}
	
	@RequestMapping(value = "/ours", method = RequestMethod.POST)
	public List<ClaimInfo> selectClaimListDetailByOurId(@RequestBody Ours ours) {
		
		HashMap<String, String> ourId = new HashMap<String, String>();
		ourId.put("myId", ours.getMyId());
		ourId.put("yourId", ours.getYourId());
		
		return claimService.getClaimListDetailByOurId(ourId); 
	}
	
}
