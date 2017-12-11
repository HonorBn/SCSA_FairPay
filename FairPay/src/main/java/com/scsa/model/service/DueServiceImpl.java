package com.scsa.model.service;

import com.scsa.model.dao.DueDAO;
import com.scsa.model.vo.DueInfo;

public class DueServiceImpl implements DueService {
	
	private DueDAO dueDao;
	
	public void setDueDao(DueDAO dueDao) {
		this.dueDao = dueDao;
	}

	@Override
	public boolean createDue(DueInfo due) {
		return dueDao.insertDue(due);
	}

	@Override
	public boolean deleteDue(String dueId) {
		return dueDao.deleteDue(dueId);
	}

	@Override
	public boolean updateDue(DueInfo due) {
		return dueDao.updateDue(due);
	}

	@Override
	public DueInfo searchDue(String dueId) {
		return dueDao.selectDue(dueId);
	}

}
