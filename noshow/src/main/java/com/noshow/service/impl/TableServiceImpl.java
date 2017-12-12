package com.noshow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noshow.dao.TableDAO;
import com.noshow.service.TableService;
import com.noshow.vo.Table;

@Service
public class TableServiceImpl implements TableService{
	
	@Resource
	private TableDAO dao;

	@Override
	public int insertTable(Table table) {
		System.out.println("TableServiceImpl-table : "+ table);
		return dao.insertTable(table);
	}

	@Override
	public List<Table> selectTable(String businessId) {
		return dao.selectTable(businessId);
	}

	@Override
	public int deleteTable(String businessId) {
		return dao.deleteTable(businessId);
	}

}
