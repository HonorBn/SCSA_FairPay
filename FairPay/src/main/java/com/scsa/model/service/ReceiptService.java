package com.scsa.model.service;

import java.util.List;

import com.scsa.model.vo.ReceiptInfo;

public interface ReceiptService {

	// ������ ���
	boolean createReceipt(ReceiptInfo receipt);

	// ������ ����
	boolean removeReceipt(String receiptId);

	// ������ �˻�
	ReceiptInfo searchReceipt(String receiptId);

	// ������ ��� ��ȸ
	List<ReceiptInfo> searchReceiptList(String claimId);

	// ������ �ν� ( ������ �̹��� ��� )
	boolean recogReceipt(String receiptImg);

}
