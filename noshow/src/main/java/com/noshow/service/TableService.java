package com.noshow.service;

import java.util.List;

import com.noshow.vo.Table;

public interface TableService {
	
	int insertTable(Table table);
	
	List<Table> selectTable(String businessId);
	
	int deleteTable(String businessId);
}
