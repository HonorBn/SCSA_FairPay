package com.scsa.model.dao;

import java.util.List;

import com.scsa.model.vo.ReceiptInfo;

public interface ReceiptDAO {

	boolean insertReceipt(ReceiptInfo receipt);

	boolean deleteReceipt(String receiptId);

	ReceiptInfo selectReceipt(String receiptId);

	List<ReceiptInfo> selectReceiptList(String claimId);

	boolean recogReceipt(String receiptImg);

}
