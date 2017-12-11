package com.scsa.model.dao;

import com.scsa.model.vo.DueInfo;

public interface DueDAO {
	
	boolean insertDue(DueInfo due);

	boolean deleteDue(String dueId);

	boolean updateDue(DueInfo due);

	DueInfo selectDue(String dueId);
}
