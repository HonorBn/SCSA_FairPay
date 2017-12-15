package com.scsa.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
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
	public String addClaimInfo(@RequestBody ClaimInfo claim) throws IOException {
		
		// 청구 등록
		claimService.createClaim(claim);
		claim.setClaimId(claim.getClaimId());

		// 피청구 등록
		addClaimeeInfo(claim);

		// 영수증 등록
		if (claim.getReceiptList() != null)
			addReceiptInfo(claim);
		
		return "등록에 성공하였습니다";
	}

	// 피청구 등록, fcm push
	private void addClaimeeInfo(ClaimInfo claim) throws IOException {
		
		List<ClaimeeInfo> claimeeList = claim.getClaimeeList();
		
		for (ClaimeeInfo claimee : claimeeList) {	// Batch하면 좋을듯
			claimee.setClaimId(claim.getClaimId());
			claimeeService.createClaimee(claimee);
		}
		
		//sendFCM(claimeeList);
		
	}

	// 영수증 등록
	public void addReceiptInfo(ClaimInfo claim) {
		
		List<ReceiptInfo> receiptList = claim.getReceiptList();
		
		for (ReceiptInfo receipt : receiptList) {
			receipt.setClaimId(claim.getClaimId());
			receiptService.createReceipt(receipt);
		}
		
	}
	
	
	// 청구 수정, 청구 삭제 추후 구현
	
	
	// 피청구 수정 (청구 납부 완료했을 때)
	@RequestMapping(value = "/claim", method = RequestMethod.PUT)
	public void updateClaimee(@RequestBody Pay pay) {

		// 입출금 정보 추출 (싹 다 가져오는 메소드)
		ClaimInfo claim = claimeeService.getClaimeeByPaymentId(pay.getPaymentId());
		
		ClaimeeInfo claimee = claim.getClaimeeList().get(0);
		claimee.getAccount().setAccountNumber(pay.getClaimee_accountNumber());
		
		/*
		 * 출금 API 호출
		 * Input : claimee (피청구인 accessToken, 입금계좌인자내역, 출금계좌 핀테크번호, 거래금액, 요청일시[현재])
		 * 입금 API 호출
		 * Input : claimee (이용기관 accessToken, 입금이체용암호문구, 출금계좌인자내역, 입금계좌 핀테크번호, 임금계좌인자내역, 거래금액, 요청일시)
		 */
		//bankAPIService.transfer(claim);
		
		claimee.setIsPaid(1);
		claimee.setPaymentDate(new Date(System.currentTimeMillis()).toString());

		claimeeService.updateClaimee(claimee);
		
	}
	
	@RequestMapping(value = "/mine", method = RequestMethod.POST)
	public List<ClaimInfo> selectClaimListDetailByMyId(@RequestBody String myId) {
		return claimService.getClaimListDetailByMyId(myId); 
	}
	
	@RequestMapping(value = "/ours", method = RequestMethod.POST)
	public List<ClaimInfo> selectClaimListDetailByOurId(@RequestBody Ours ours) {
		
		HashMap<String, String> ourId = new HashMap<String, String>();
		ourId.put("myId", ours.getMyId());
		ourId.put("yourId", ours.getYourId());
		
		return claimService.getClaimListDetailByOurId(ourId); 
	}
	
	public void sendFCM(List<ClaimeeInfo> claimeeList) throws IOException {
		
		String claimId = claimeeList.get(0).getClaimId();
		int tokenSize = claimeeList.size();
		String[] tokenList = new String[tokenSize];
		for (int i = 0; i < tokenSize; i++) {
			tokenList[i] = claimeeList.get(i).getClaimee().getFcmId();
		}
		
		String url = "https://android.googleapis.com/gcm/notification";
		String contentType = "application/json";
		String authorization = "key=AAAAhnbpQzw:APA91bEH0T4G9JF1vgMS2vQw695tQ7J_KwZHE6aUyvjQNCcs_fLn2Z2Xp9cMsnrmApIbRhHeSncuuLjuRgTVuI4gb8x5-sV11IdGPSvveDmwSZlGpgp-omI6_fiDpGhbWeu1zEQuy9H_";
		String projectId = "577520616252";
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("Authorization", authorization);
		map.put("Content-Type", contentType);
		map.put("project_id", projectId);
		headers.setAll(map);
		System.out.println(tokenSize);
		Map req_payload = new HashMap();
		req_payload.put("operation", "create");
		req_payload.put("notification_key_name", claimId);
		req_payload.put("registration_ids", tokenList);
		
		HttpEntity<Map> request = new HttpEntity<Map>(req_payload, headers);
		
		String notificationKey = (String) (new RestTemplate().postForObject(url, request, Map.class).get("notification_key"));
		
		
		url = "https://fcm.googleapis.com/fcm/send";
		
		headers = new LinkedMultiValueMap<String, String>();
		map = new HashMap<String, String>();
		map.put("Authorization", authorization);
		map.put("Content-Type", contentType);
		headers.setAll(map);
		
		Map data = new HashMap();
		data.put("message", "신규 청구가 있습니다");
		
		req_payload = new HashMap();
		req_payload.put("to", notificationKey);
		req_payload.put("data", data);
		
		request = new HttpEntity<Map>(req_payload, headers);
		
		System.out.println((new RestTemplate().postForObject(url, request, Map.class).get("success")));
	}
}
