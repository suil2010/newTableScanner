package com.noshow.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.TableDAO;
import com.noshow.vo.Table;

@Repository
public class TableDAOImpl implements TableDAO{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.noshow.config.mybatis.mapper.tableMapper." + id;
	}
	
	@Override
	public int insertTable(Table table) {
		return session.insert(makeSqlId("insertTable"), table);
	}

	@Override
	public List<Table> selectTable(String businessId) {
		return session.selectList(makeSqlId("selectTable"), businessId);
	}

	@Override
	public int deleteTable(String businessId) {
		return session.delete(makeSqlId("deleteTable"), businessId);
	}

}
