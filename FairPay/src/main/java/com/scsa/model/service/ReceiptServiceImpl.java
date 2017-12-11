package com.scsa.model.service;

import java.util.List;

import com.scsa.model.dao.ReceiptDAO;
import com.scsa.model.vo.ReceiptInfo;

public class ReceiptServiceImpl implements ReceiptService {

	private ReceiptDAO receiptDao;

	public void setReceiptDao(ReceiptDAO receiptDao) {
		this.receiptDao = receiptDao;
	}

	public boolean createReceipt(ReceiptInfo receipt) {
		return receiptDao.insertReceipt(receipt);
	}

	public boolean removeReceipt(String receiptId) {
		return receiptDao.deleteReceipt(receiptId);
	}

	public ReceiptInfo searchReceipt(String receiptId) {
		return receiptDao.selectReceipt(receiptId);
	}

	public List<ReceiptInfo> searchReceiptList(String claimId) {
		return receiptDao.selectReceiptList(claimId);
	}

	public boolean recogReceipt(String receiptImg) {
		return receiptDao.recogReceipt(receiptImg);
	}

}
