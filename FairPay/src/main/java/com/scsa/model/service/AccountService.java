package com.scsa.model.service;

import java.util.List;

import com.scsa.model.vo.AccountInfo;
import com.scsa.model.vo.ClaimInfo;
import com.scsa.model.vo.DueInfo;
import com.scsa.model.vo.MeetingInfo;
import com.scsa.model.vo.ReceiptInfo;
import com.scsa.model.vo.UserInfo;

public interface AccountService {
	boolean addAccount(AccountInfo account);
	List<AccountInfo> getAccountsByUserId(String userId);
	boolean removeAccount(String accountNumber,String userId );
}
