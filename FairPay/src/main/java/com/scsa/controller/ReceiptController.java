package com.scsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scsa.model.service.ReceiptService;
import com.scsa.model.vo.ReceiptInfo;

@RestController
public class ReceiptController {

	private ReceiptService receiptService;

	@Autowired
	public void setReceiptService(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}

	// 청구별 영수증 전체 목록 조회
	@RequestMapping(value = "/receipt_list.do")
	public String receipt_list_by_claimId(Model model, String claimId) {
		List<ReceiptInfo> list = receiptService.searchReceiptList(claimId);
		model.addAttribute("list", list);
		return "receipt_list";
	}

	// 영수증 등록  : done
	@RequestMapping(value = "/receipt/post", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addReceipt(@RequestBody ReceiptInfo receipt) {
		boolean result = receiptService.createReceipt(receipt);
		if (result) {
			return "영수증 등록에 성공했습니다.";
		} else {
			return "영수증 등록에 실패했습니다.";
		}
	}

	// 영수증 삭제
	@RequestMapping(value = "/delete_receipt.do")
	public String delete_receipt(Model model, String receiptId) {
		boolean result = receiptService.removeReceipt(receiptId);
		if (result) {
			model.addAttribute("msg", "영수증 삭제에 성공하였습니다");
		} else {
			model.addAttribute("msg", "영수증 삭제에 실패하였습니다");
		}
		return "receipt_list";
	}

	// 영수증 검색 by 영수증아이디
	@RequestMapping(value = "/select_receipt.do")
	public String select_receipt(Model model, String receiptId) {
		model.addAttribute("list", receiptService.searchReceipt(receiptId));
		return "receipt_list";
	}
	
	
	// 구현해야함 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// 영수증 인식 ( 영수증 이미지 경로 )
	@RequestMapping(value = "/recog_receipt.do")
	public String recog_receipt(Model model, String receiptId) {
		return "receipt_list";
	}
}
