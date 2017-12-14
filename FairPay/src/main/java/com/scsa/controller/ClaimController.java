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

	// û�� ��� �Ŀ� ��û���� ������ ���
	@RequestMapping(value = "/claim", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String addClaimInfo(@RequestBody ClaimInfo claim) {
		
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
	private void addClaimeeInfo(ClaimInfo claim) {
		
		List<ClaimeeInfo> claimeeList = claim.getClaimeeList();
		
		for (ClaimeeInfo claimee : claimeeList) {
			claimee.setClaimId(claim.getClaimId());
			claimeeService.createClaimee(claimee);
			// ��ū ����Ʈ �߰�
		}
		// �߰��� ����Ʈ�� ��ū Ǫ��
		
		
	}

	// ������ ���
	public void addReceiptInfo(ClaimInfo claim) {
		
		List<ReceiptInfo> receiptList = claim.getReceiptList();
		
		for (ReceiptInfo receipt : receiptList) {
			receipt.setClaimId(claim.getClaimId());
			receiptService.createReceipt(receipt);
		}
		
	}
	
/*	public void sendFCM(ClaimInfo claim) {
		
		
        String token = tokenList.get(count).getDEVICE_ID();
        
        final String apiKey = "���̾� ���̽��� ���� APIŰ�� ���⿡ �ִ´�";
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + apiKey);

        conn.setDoOutput(true);
        
        String userId =(String) request.getSession().getAttribute("ssUserId");

        // �̷��� ������ ������ ALL�� �����س��� ��� ��������� �˸��� �����ش�.
        String input = "{\"notification\" : {\"title\" : \"����� ���� �ֱ� \", \"body\" : \"����� ���� �ֱ�\"}, \"to\":\"/topics/ALL\"}";
        
        // �̰ɷ� ������ Ư�� ��ū�� �������ִ� ���ÿ��� �˸��� �����ش�  ���� ���߿� �Ѱ� ��� ��������
        String input = "{\"notification\" : {\"title\" : \" ����� ����ֱ� \", \"body\" : \"����� ���� �ֱ�\"}, \"to\":\" ���Ⱑ ���� ��� ��ū  \"}";

        OutputStream os = conn.getOutputStream();
        
        // �������� ������ �ѱ� ������ ����� �Ʒ�ó��  UTF-8�� ���ڵ��ؼ� ��������
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
