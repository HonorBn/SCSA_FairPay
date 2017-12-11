package com.scsa.model.service;

import java.util.List;

import com.scsa.model.vo.ReceiptInfo;

public interface ReceiptService {

	// 영수증 등록
	boolean createReceipt(ReceiptInfo receipt);

	// 영수증 삭제
	boolean removeReceipt(String receiptId);

	// 영수증 검색
	ReceiptInfo searchReceipt(String receiptId);

	// 영수증 목록 조회
	List<ReceiptInfo> searchReceiptList(String claimId);

	// 영수증 인식 ( 영수증 이미지 경로 )
	boolean recogReceipt(String receiptImg);

}
