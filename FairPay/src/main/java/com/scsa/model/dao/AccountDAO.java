package com.scsa.model.dao;

import java.util.List;

import com.scsa.model.vo.AccountInfo;

public interface AccountDAO {
	boolean insertAccount(AccountInfo account);
	List<AccountInfo> selectAccountsByUserId(String userId);
	boolean deleteAccount(String accountNumber,String userId );
}
