package com.scsa.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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
import com.scsa.model.service.UserService;
import com.scsa.model.vo.ClaimInfo;
import com.scsa.model.vo.ClaimeeInfo;
import com.scsa.model.vo.ReceiptInfo;

@RestController
public class ClaimController {

	@Autowired
	private UserService userService;
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
	public String addClaimInfo(@RequestBody ClaimInfo claim) throws IOException {
		
		// û�� ���
		claimService.createClaim(claim);
		claim.setClaimId(claim.getClaimId());

		// ��û�� ���
		addClaimeeInfo(claim);

		// ������ ���
		if (claim.getReceiptList() != null)
			addReceiptInfo(claim);
		
		
		
		return "��Ͽ� �����Ͽ����ϴ�";
	}

	// ��û�� ���, fcm push
	private void addClaimeeInfo(ClaimInfo claim) throws IOException {
		
		List<ClaimeeInfo> claimeeList = claim.getClaimeeList();
		
		for (ClaimeeInfo claimee : claimeeList) {	// Batch�ϸ� ������
			claimee.setClaimId(claim.getClaimId());
			claimeeService.createClaimee(claimee);
			
		}
		
		sendFCM(claimeeList);
		
	}

	// ������ ���
	public void addReceiptInfo(ClaimInfo claim) {
		
		List<ReceiptInfo> receiptList = claim.getReceiptList();
		
		
		for (ReceiptInfo receipt : receiptList) {
			receipt.setClaimId(claim.getClaimId());
			receiptService.createReceipt(receipt);
		}
		
		
	}
	
	public void sendFCM(List<ClaimeeInfo> claimeeList) throws IOException {
		
		String claimId = claimeeList.get(0).getClaimId();
		int tokenSize = claimeeList.size();
		String[] tokenList = new String[tokenSize];
		for (int i = 0; i < tokenSize; i++) {
			tokenList[i] = claimeeList.get(i).getClaimeeUserInfo().getFcmId();
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
		data.put("message", "�ű� û���� �ֽ��ϴ�");
		
		req_payload = new HashMap();
		req_payload.put("to", notificationKey);
		req_payload.put("data", data);
		
		request = new HttpEntity<Map>(req_payload, headers);
		
		System.out.println((new RestTemplate().postForObject(url, request, Map.class).get("success")));
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
		//bankAPIService.transfer(claimee);

		claimee = claimeeService.selectClaimeeAfter(pay.getPaymentId());
		claimee.setClaimee_accountNumber(claimee.getClaimee_accountNumber());
		claimee.setIsPaid(1);
		claimee.setPaymentDate(new Date(System.currentTimeMillis()).toString());

		claimeeService.updateClaimee(claimee);
		
	}
	
	@RequestMapping(value = "/mine", method = RequestMethod.POST)
	public List<ClaimInfo> selectClaimListDetailByMyId(@RequestBody String myId) {
		System.out.println("��Ʈ�ѷ� ����");
		return claimService.getClaimListDetailByMyId(myId); 
	}
	
	@RequestMapping(value = "/ours", method = RequestMethod.POST)
	public List<ClaimInfo> selectClaimListDetailByOurId(@RequestBody Ours ours) {
		
		HashMap<String, String> ourId = new HashMap<String, String>();
		ourId.put("myId", ours.getMyId());
		ourId.put("yourId", ours.getYourId());
		
		return claimService.getClaimListDetailByOurId(ourId); 
	}
	
}
