package com.scsa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value="/image/springrest/register", method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile image, 
			@RequestParam("claimId") String claimId,
			@RequestParam("isClaim") String isClaim) throws IllegalStateException, IOException{
		System.out.println("이미지 업로드 요청 발생 : "+image.getOriginalFilename());
		System.out.println(claimId);
		System.out.println(isClaim);
		ReceiptInfo receipt = new ReceiptInfo(claimId, isClaim);
		String saveDir = servletContext.getRealPath("/images");
		File file = new File(saveDir+"/"+image.getOriginalFilename());
		
		if(!file.exists()){
			image.transferTo(file);
			receipt.setReceiptImg(image.getOriginalFilename());
			receiptService.createReceipt(receipt);
		} else {
			System.out.println("같은 이름의 파일이 이미 존재함!");
		}
		
		return "confirm";
	}
	
	@RequestMapping(value = "/image/download/{claimId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@PathVariable String claimId) throws Exception {
		System.out.println("사진 다운로드 요청 발생 : "+claimId);
		List<ReceiptInfo> list = new ArrayList<ReceiptInfo>();
		list = receiptService.searchReceiptList(claimId);
		String saveDir = servletContext.getRealPath("/images");
		File file;
		if(list.size()==0 || list==null){
			file = new File(saveDir+"/"+"basicImage.jpg");
		} else {
			file = new File(saveDir+"/"+list.get(list.size()-1).getReceiptImg());
			System.out.println("사진 경로 : "+saveDir+"/"+list.get(list.size()-1).getReceiptImg());
		}
		InputStream inputStream = new FileInputStream(file); 
        byte[] out=org.apache.commons.io.IOUtils.toByteArray(inputStream);

	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("Content-disposition", "attachment; filename="+file);
	    return new ResponseEntity<byte[]>(out, responseHeaders, HttpStatus.OK);
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
	
	
	
}
