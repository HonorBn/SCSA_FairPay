package com.scsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scsa.model.service.AccountService;
import com.scsa.model.service.ReceiptService;
import com.scsa.model.vo.AccountInfo;
import com.scsa.model.vo.ReceiptInfo;

@Controller
public class AccountController {

	/*
	 * boolean insertAccount(AccountInfo account); List<AccountInfo>
	 * selectAccountByUserId(String userId); boolean deleteAccount(String
	 * accountNumber,String userId ); private String accountNumber; private
	 * String bankCode; private String userId; private String accountAlias;
	 */

	private AccountService accountService;

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	// ∞Ë¡¬ µÓ∑œ
	@RequestMapping(value = "/add_account.do")
	public String add_account(Model model, AccountInfo account) {
		accountService.addAccount(account);
		model.addAttribute("accountNumber", account.getAccountNumber());
		return "account_list";
	}

	// ∞Ë¡¬ ∏Ò∑œ ¡∂»∏
	@RequestMapping(value = "/account_list.do")
	public String account_list_by_Id(Model model, String userId) {
		List<AccountInfo> list = accountService.getAccountsByUserId(userId);
		model.addAttribute("list", list);
		return "account_list";
	}

	// ∞Ë¡¬ ªË¡¶
	@RequestMapping(value = "/delete_account.do")
	public String delete_account(Model model, @RequestParam(name = "accountNumber") String accountNumber,
			@RequestParam(name = "userId") String userId) {
		accountService.removeAccount(accountNumber, userId);
		model.addAttribute("userId", userId);
		return "account_list_by_Id";
	}



}
