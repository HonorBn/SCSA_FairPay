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

	// 청구 등록 후에 피청구와 영수증 등록
	@RequestMapping(value = "/claim", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String addClaimInfo(@RequestBody ClaimInfo claim) throws IOException, JSONException {
		
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
	private void addClaimeeInfo(ClaimInfo claim) throws IOException, JSONException {
		
		List<ClaimeeInfo> claimeeList = claim.getClaimeeList();
		
		for (ClaimeeInfo claimee : claimeeList) {	// Batch하면 좋을듯
			claimee.setClaimId(claim.getClaimId());
			claimeeService.createClaimee(claimee);
		}
		
		sendFCM(claim);
		
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
		
		System.out.println("내 아이디" + myId);
		List<ClaimInfo> claimList = claimService.getClaimListDetailByMyId(myId);
		
		System.out.println(claimList.get(0).toString());
		
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
		String claimer = claim.getClaimer().getUsername();
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
	    data.put("message", claimer + "님이 새로운 청구를 요청했습니다");
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
        
        System.out.println(successNum);
	}
	
}
