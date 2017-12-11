package com.noshow.dao;

import java.util.List;

import com.noshow.vo.Table;

public interface TableDAO {
	
	int insertTable(Table table);
	
	List<Table> selectTable(String businessId);
	
	int deleteTable(String businessId);
}
