package com.scsa.model.service;

import java.util.List;

import com.scsa.model.dao.AccountDAO;
import com.scsa.model.vo.AccountInfo;

public class AccountServiceImpl implements AccountService {
	
	AccountDAO accountDao;
	
	

	public void setAccountDao(AccountDAO accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public boolean addAccount(AccountInfo account) {
		
		return accountDao.insertAccount(account);
	}

	@Override
	public List<AccountInfo> getAccountsByUserId(String userId) {
		return accountDao.selectAccountsByUserId(userId);
	}

	@Override
	public boolean removeAccount(String accountNumber,String userId ) {
		return accountDao.deleteAccount(accountNumber,userId );
	}

}
