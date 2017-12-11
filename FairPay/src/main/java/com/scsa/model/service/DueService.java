package com.scsa.model.service;

import com.scsa.model.vo.DueInfo;

public interface DueService {

	boolean createDue(DueInfo due);

	boolean deleteDue(String dueId);

	boolean updateDue(DueInfo due);

	DueInfo searchDue(String dueId);
}
