package com.scsa.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
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
	public String addClaimInfo(@RequestBody ClaimInfo claim) {
		
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
	private void addClaimeeInfo(ClaimInfo claim) {
		
		List<ClaimeeInfo> claimeeList = claim.getClaimeeList();
		
		for (ClaimeeInfo claimee : claimeeList) {
			claimee.setClaimId(claim.getClaimId());
			claimeeService.createClaimee(claimee);
			// 토큰 리스트 추가
		}
		// 추가된 리스트로 토큰 푸시
		
		
	}

	// 영수증 등록
	public void addReceiptInfo(ClaimInfo claim) {
		
		List<ReceiptInfo> receiptList = claim.getReceiptList();
		
		for (ReceiptInfo receipt : receiptList) {
			receipt.setClaimId(claim.getClaimId());
			receiptService.createReceipt(receipt);
		}
		
	}
	
/*	public void sendFCM(ClaimInfo claim) {
		
		
        String token = tokenList.get(count).getDEVICE_ID();
        
        final String apiKey = "파이어 베이스의 서버 API키를 여기에 넣는다";
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + apiKey);

        conn.setDoOutput(true);
        
        String userId =(String) request.getSession().getAttribute("ssUserId");

        // 이렇게 보내면 주제를 ALL로 지정해놓은 모든 사람들한테 알림을 날려준다.
        String input = "{\"notification\" : {\"title\" : \"여기다 제목 넣기 \", \"body\" : \"여기다 내용 넣기\"}, \"to\":\"/topics/ALL\"}";
        
        // 이걸로 보내면 특정 토큰을 가지고있는 어플에만 알림을 날려준다  위에 둘중에 한개 골라서 날려주자
        String input = "{\"notification\" : {\"title\" : \" 여기다 제목넣기 \", \"body\" : \"여기다 내용 넣기\"}, \"to\":\" 여기가 받을 사람 토큰  \"}";

        OutputStream os = conn.getOutputStream();
        
        // 서버에서 날려서 한글 깨지는 사람은 아래처럼  UTF-8로 인코딩해서 날려주자
        os.write(input.getBytes("UTF-8"));
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + input);
        System.out.println("Response Code : " + responseCode);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        // print result
        System.out.println(response.toString());

	}*/
	
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
	
	@RequestMapping(value = "/mine", method = RequestMethod.POST)
	public List<ClaimInfo> selectClaimListDetailByMyId(@RequestBody String myId) {
		System.out.println("컨트롤러 도착");
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
