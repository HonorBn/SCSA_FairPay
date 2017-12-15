package com.scsa.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scsa.model.service.ReceiptService;
import com.scsa.model.vo.ReceiptInfo;

@RestController
public class ReceiptController {

	private ReceiptService receiptService;

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	public void setReceiptService(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}

	@RequestMapping(value="/image/register", method=RequestMethod.POST)
	public String upload(Model model, @RequestParam String message, 
			@RequestParam("file") MultipartFile image) throws IllegalStateException, IOException{
		System.out.println("�̹��� ���ε� ��û �߻� : "+image.getOriginalFilename());
		String saveDir = servletContext.getRealPath("/images");
		File file = new File(saveDir+"/"+image.getOriginalFilename());
		
		if(!file.exists()){
			image.transferTo(file);
			model.addAttribute("fileName",image.getOriginalFilename());
			model.addAttribute("message", message);
		} else {
			// file rename �˰��� ������ ��, �� �� transfer..
			System.out.println("���� �̸��� ������ �̹� ������!");
		}
		
		return "confirm";
	}
	

	// û���� ������ ��ü ��� ��ȸ
	@RequestMapping(value = "/receipt_list.do")
	public String receipt_list_by_claimId(Model model, String claimId) {
		List<ReceiptInfo> list = receiptService.searchReceiptList(claimId);
		model.addAttribute("list", list);
		return "receipt_list";
	}

	// ������ ���  : done
	@RequestMapping(value = "/receipt/post", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addReceipt(@RequestBody ReceiptInfo receipt) {
		boolean result = receiptService.createReceipt(receipt);
		if (result) {
			return "������ ��Ͽ� �����߽��ϴ�.";
		} else {
			return "������ ��Ͽ� �����߽��ϴ�.";
		}
	}

	// ������ ����
	@RequestMapping(value = "/delete_receipt.do")
	public String delete_receipt(Model model, String receiptId) {
		boolean result = receiptService.removeReceipt(receiptId);
		if (result) {
			model.addAttribute("msg", "������ ������ �����Ͽ����ϴ�");
		} else {
			model.addAttribute("msg", "������ ������ �����Ͽ����ϴ�");
		}
		return "receipt_list";
	}

	// ������ �˻� by ���������̵�
	@RequestMapping(value = "/select_receipt.do")
	public String select_receipt(Model model, String receiptId) {
		model.addAttribute("list", receiptService.searchReceipt(receiptId));
		return "receipt_list";
	}
	
	
	// �����ؾ��� !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// ������ �ν� ( ������ �̹��� ��� )
	@RequestMapping(value = "/recog_receipt.do")
	public String recog_receipt(Model model, String receiptId) {
		return "receipt_list";
	}
}
