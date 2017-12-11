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

	// 청구 등록 후에 피청구와 영수증 등록
	@RequestMapping(value = "/claim", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String addClaimInfo(@RequestBody Claim claim) {

		ClaimInfo claimInfo = new ClaimInfo("", claim.getClaimDate(), claim.getTotalPrice(), claim.getClaimerId(),
				claim.getClaimer_accountNumber(), claim.getMeetingId(), claim.getReceiptImg());

		// 청구 등록
		claimService.createClaim(claimInfo);
		claim.setClaimId(claimInfo.getClaimId());

		// 피청구 등록
		addClaimeeInfo(claim);

		// 영수증 등록
		if (claim.getReceiptImg() != null)
			addReceiptInfo(claim);

		return "등록에 성공하였습니다";
	}

	// 피청구 등록
	private void addClaimeeInfo(Claim claim) {
		ClaimeeInfo claimeeInfo = new ClaimeeInfo("", claim.getTran_amt(), claim.getClaimId(), claim.getClaimeeId());
		claimeeService.createClaimee(claimeeInfo);
	}

	// 영수증 등록
	public void addReceiptInfo(Claim claim) {
		ReceiptInfo receiptInfo = new ReceiptInfo("", claim.getReceiptImg(), claim.getClaimId());
		receiptService.createReceipt(receiptInfo);
	}

	// 청구 수정, 청구 삭제 추후 구현
	
	
	// 피청구 수정 (청구 납부 완료했을 때)
	@RequestMapping(value = "/claim", method = RequestMethod.PUT)
	public void updateClaimee(@RequestBody Pay pay) {

		// 입출금 정보 추출
		ClaimeeInfo claimee = claimeeService.selectClaimeeBefore(pay.getPaymentId());
		claimee.setClaimee_accountNumber(pay.getClaimee_accountNumber());

		/*
		 * 출금 API 호출
		 * Input : claimee (피청구인 accessToken, 입금계좌인자내역, 출금계좌 핀테크번호, 거래금액, 요청일시[현재])
		 * 입금 API 호출
		 * Input : claimee (이용기관 accessToken, 입금이체용암호문구, 출금계좌인자내역, 입금계좌 핀테크번호, 임금계좌인자내역, 거래금액, 요청일시)
		 */
		bankAPIService.transfer(claimee);

		claimee = claimeeService.selectClaimeeAfter(pay.getPaymentId());
		claimee.setClaimee_accountNumber(claimee.getClaimee_accountNumber());
		claimee.setIsPaid(1);
		claimee.setPaymentDate(new Date(System.currentTimeMillis()).toString());

		claimeeService.updateClaimee(claimee);
		
	}
	
	@RequestMapping(value = "/ours", method = RequestMethod.POST)
	public List<ClaimInfo> selectOurClaimList(@RequestBody Ours ours) {
		
		HashMap<String, String> ourId = new HashMap<String, String>();
		ourId.put("myId", ours.getMyId());
		ourId.put("yourId", ours.getYourId());
		System.out.println(ours.getMyId());
		return claimService.getClaimListByOurIdWithClaimeeList(ourId);		
	}
	
}
