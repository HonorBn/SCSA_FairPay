package com.scsa.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.scsa.model.vo.ClaimInfo;
import com.scsa.model.vo.ClaimeeInfo;
import com.scsa.model.vo.UserInfo;

public class BankAPIService {
	
	final String NAME = "안영빈";		// 이용기관 대표자
	final String clientId = "l7xx1a592643436f43169b078251f7258c59";
	final String clientSecret = "66acb1f6756f4a348fac9c59412d6d11";
	final String AUTHORIZATION = "61d5e4f7-9e2c-40c1-8746-934c9825b06a";	// 이용기관 Access Token
	String url = "https://testapi.open-platform.or.kr";
	
	public void transfer(ClaimInfo claim) {
		
		withdraw(claim);
		deposit(claim);
		
	}
	
	
	private void withdraw(ClaimInfo claim) {
		
		ClaimeeInfo claimeeInfo = claim.getClaimeeList().get(0);
		UserInfo claimee = claimeeInfo.getClaimee();
		
		String Authorization = claimee.getAccessToken();
		
		String dps_print_content = 	claimee.getUsername();	// 입금계좌인자내역
		String fintech_use_num = claimeeInfo.getAccount().getAccountNumber();
		int tran_amt = claimeeInfo.getTran_amt();
		long tran_dtime = Long.parseLong(getTime());
		
		// 헤더 설정
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("Authorization", "Bearer " + Authorization);
		headers.setAll(map);
		
		// 파라미터 설정
		Map req_payload = new HashMap();
		req_payload.put("dps_print_content", dps_print_content);
		req_payload.put("fintech_use_num", fintech_use_num);
		req_payload.put("tran_amt", tran_amt);
		req_payload.put("tran_dtime", tran_dtime);
		
		// 헤더, 파라미터 입력
		String uri = url + "/v1.0/transfer/withdraw";
		HttpEntity<Map> request = new HttpEntity<Map>(req_payload, headers);
		
		new RestTemplate().postForObject(uri, request, Map.class);
	}
	
	private void deposit(ClaimInfo claim) {
		
		ClaimeeInfo claimeeInfo = claim.getClaimeeList().get(0);
		
		String Authorization = AUTHORIZATION;
		String wd_print_content = NAME;	// 이용기관 대표자 이름
		String wd_pass_phrase = "NONE"; // 입금 이체용 암호문구
		String req_cnt = "1";
		int tran_amt = claimeeInfo.getTran_amt();
		long tran_dtime = Long.parseLong(getTime());
		
		List<Map> req_list = new ArrayList<Map>();
		Map list = new HashMap();
		list.put("tran_no", "1");
		list.put("fintech_use_num", claim.getAccount().getAccountNumber());
		list.put("print_content", claimeeInfo.getClaimee().getUsername());
		list.put("tran_amt", tran_amt);
		req_list.add(list);
		
		// 헤더 설정
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("Authorization", "Bearer " + Authorization);
		headers.setAll(map);

		// 파라미터 설정
		Map req_payload = new HashMap();
		req_payload.put("wd_pass_phrase", wd_pass_phrase);
		req_payload.put("wd_print_content", wd_print_content);
		req_payload.put("req_cnt", req_cnt);
		req_payload.put("tran_dtime", tran_dtime);
		req_payload.put("req_list", req_list);
		
		// 데이터 설정
		String uri = url + "/v1.0/transfer/deposit";
		HttpEntity<Map> request = new HttpEntity<Map>(req_payload, headers);
		
		new RestTemplate().postForObject(uri, request, Map.class);
		
	}
	
	private String getTime() {
		return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();
	}
	
	public UserInfo getUserInfo(UserInfo user) {
		
		String code = user.getAuthorizationCode();
		String grant_type = "authorization_code";
		
		System.out.println(code);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		// 파라미터 설정
		Map req_payload = new HashMap();
		req_payload.put("code", code);
		req_payload.put("client_id", clientId);
		req_payload.put("client_secret", clientSecret);
		req_payload.put("redirect_uri", "http://70.12.109.163:9090/project/join.jsp");
		req_payload.put("grant_type", grant_type);
		
		// 데이터 설정
		String uri = url + "/oauth/2.0/token";
		HttpEntity<Map> request = new HttpEntity<Map>(req_payload, headers);
		
		System.out.println(new RestTemplate().postForObject(uri, request, Map.class).toString());
		
		return user;
	}
}