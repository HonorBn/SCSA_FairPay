package com.scsa.model.service;

import java.util.List;
import com.scsa.model.vo.AccountInfo;

public interface AccountService {
	boolean addAccount(AccountInfo account);
	List<AccountInfo> getAccountsByUserId(String userId);
	boolean removeAccount(String accountNumber,String userId );
}
