package com.scsa.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
	public String addClaimInfo(@RequestBody ClaimInfo claim) throws IOException, JSONException {
		
		// û�� ���
		claim.setClaimDate(new Date(System.currentTimeMillis()).toString());
		claimService.createClaim(claim);
		claim.setClaimId(claim.getClaimId());

		// ��û�� ���
		addClaimeeInfo(claim);

		// ������ ���
		if (claim.getReceiptList() != null)
			addReceiptInfo(claim);
		
		return "û�� ��û�� �߼��߽��ϴ�";
	}

	// ��û�� ���, fcm push
	private void addClaimeeInfo(ClaimInfo claim) throws IOException, JSONException {
		
		List<ClaimeeInfo> claimeeList = claim.getClaimeeList();
		
		for (ClaimeeInfo claimee : claimeeList) {	// Batch�ϸ� ������
			claimee.setClaimId(claim.getClaimId());
			claimeeService.createClaimee(claimee);
		}
		
		sendFCM(claim);
	}

	// ������ ���
	public void addReceiptInfo(ClaimInfo claim) {
		
		List<ReceiptInfo> receiptList = claim.getReceiptList();
		
		for (ReceiptInfo receipt : receiptList) {
			receipt.setClaimId(claim.getClaimId());
			receiptService.createReceipt(receipt);
		}
	}
	
	
	// û�� ����, û�� ���� ���� ����
	
	
	// ��û�� ���� (û�� ���� �Ϸ����� ��)
	@RequestMapping(value = "/claim", method = RequestMethod.PUT)
	public void updateClaimee(@RequestBody String[] paymentIdList) {
		
		// ��û�� ���̵�� ������ ��������
		List<ClaimInfo> claimList = claimeeService.getClaimeeListByPaymentId(paymentIdList);
		
		/*
		 * ��� API ȣ��
		 * Input : claimee (��û���� accessToken, �Աݰ������ڳ���, ��ݰ��� ����ũ��ȣ, �ŷ��ݾ�, ��û�Ͻ�[����])
		 * �Ա� API ȣ��
		 * Input : claimee (�̿��� accessToken, �Ա���ü���ȣ����, ��ݰ������ڳ���, �Աݰ��� ����ũ��ȣ, �ӱݰ������ڳ���, �ŷ��ݾ�, ��û�Ͻ�)
		 */
		bankAPIService.transfer(claimList);
		
		for (ClaimInfo claim : claimList) {
			ClaimeeInfo claimee = claim.getClaimeeList().get(0);
			claimee.setIsPaid(1);
			claimee.setPaymentDate(new Date(System.currentTimeMillis()).toString());
			claimeeService.updateClaimee(claimee);
		}
	}
	
	@RequestMapping(value = "/mine", method = RequestMethod.POST)
	public List<ClaimInfo> selectClaimListDetailByMyId(@RequestBody String myId) {
		
		System.out.println("�� ���̵�� û�� ��û�� ����Ʈ ��ȸ : " + myId);
		List<ClaimInfo> claimList = claimService.getClaimListDetailByMyId(myId);
		
		for (ClaimInfo claim : claimList) {
			System.out.println("--------------------û�� Ȯ�� ----------------");
			System.out.println(claim.toString());
		}
		
		return claimList;
	}
	
	@RequestMapping(value = "/ours", method = RequestMethod.POST)
	public List<ClaimInfo> selectClaimListDetailByOurId(@RequestBody Ours ours) {
		
		HashMap<String, String> ourId = new HashMap<String, String>();
		ourId.put("myId", ours.getMyId());
		ourId.put("yourId", ours.getYourId());
		
		return claimService.getClaimListDetailByOurId(ourId); 
	}
	
	
	public void sendFCM(ClaimInfo claim) throws IOException, JSONException {
		
		List<ClaimeeInfo> claimeeList = claim.getClaimeeList();
		
		// prepare data
		String claimId = claim.getClaimId();
		String claimer = "����";
		int tokenSize = claimeeList.size();
		String[] tokenList = new String[tokenSize];
		for (int i = 0; i < tokenSize; i++) 
			tokenList[i] = claimeeList.get(i).getClaimee().getFcmId();
		
		String contentType = "application/json";
		String authorization = "key=AAAAhnbpQzw:APA91bEH0T4G9JF1vgMS2vQw695tQ7J_KwZHE6aUyvjQNCcs_fLn2Z2Xp9cMsnrmApIbRhHeSncuuLjuRgTVuI4gb8x5-sV11IdGPSvveDmwSZlGpgp-omI6_fiDpGhbWeu1zEQuy9H_";
		String projectId = "577520616252";
		
		// HTTP URL
		URL url = new URL("https://android.googleapis.com/gcm/notification");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		
	    // HTTP request header
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", contentType);
	    con.setRequestProperty("Authorization", authorization);
	    con.setRequestProperty("project_id", projectId);
	    con.setDoOutput(true);
	    con.connect();
	    
	    // HTTP request
	    JSONObject payload = new JSONObject();
	    payload.put("operation", "create");
	    payload.put("notification_key_name", claimId);
	    payload.put("registration_ids", new JSONArray(tokenList));
	    
	    OutputStream os = con.getOutputStream();
	    os.write(payload.toString().getBytes("UTF-8"));
	    os.flush();
	    os.close();
	    
        // Read the response into a string
        InputStream is = con.getInputStream();
        String responseString = new Scanner(is, "UTF-8").useDelimiter("\\A").next();
        is.close();

        // Parse the JSON string and return the notification key
        String notificationKey = new JSONObject(responseString).getString("notification_key");
        
		// HTTP URL
		url = new URL("https://fcm.googleapis.com/fcm/send");
		con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		
	    // HTTP request header
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", contentType);
	    con.setRequestProperty("Authorization", authorization);
	    con.setDoOutput(true);
	    con.connect();
	    
	    // HTTP request
	    JSONObject data = new JSONObject();
	    data.put("message","������� ���ο� û���� ��û�߽��ϴ�");
	    payload = new JSONObject();
	    payload.put("to", notificationKey);
	    payload.put("data", data);
	    
	    os = con.getOutputStream();
	    os.write(payload.toString().getBytes("UTF-8"));
	    os.flush();
	    os.close();
	    
	    // Read the response into a string
        is = con.getInputStream();
        responseString = new Scanner(is, "UTF-8").useDelimiter("\\A").next();
        is.close();

        // Parse the JSON string and return the notification key
        String successNum = new JSONObject(responseString).getString("success");
        
        System.out.println("�� " + tokenSize + "�� Push ��û �� " + successNum + "�� ����");
	}
	
}
