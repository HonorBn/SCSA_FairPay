package com.scsa.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.scsa.model.vo.ReceiptInfo;

public class ReceiptDAOImpl implements ReceiptDAO {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public boolean insertReceipt(ReceiptInfo receipt) {
		return sqlSession.insert("receipt.insertReceipt", receipt) == 1 ? true : false;
	}

	public boolean deleteReceipt(String receiptId) {
		return sqlSession.delete("receipt.deleteReceipt", receiptId) == 1  ? true: false;
	}

	public ReceiptInfo selectReceipt(String receiptId) {
		return sqlSession.selectOne("receipt.selectReceiptById", receiptId);
	}

	public List<ReceiptInfo> selectReceiptList(String claimId) {
		return sqlSession.selectList("receipt.selectReceiptList", claimId);
	}
	
	// 구현 해야함 !
	public boolean recogReceipt(String receiptImg) {
		return false;
	}

}
