package com.noshow.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.OrderTableDao;
import com.noshow.vo.OrderTable;

@Repository
public class OrderTableDaoImpl implements OrderTableDao{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "noshow.config.mybatis.mapper.orderTableMapper." + id;
	}
	
	@Override
	public int insertOrderTable(OrderTable orderTable) {
		return session.insert(makeSqlId("insertOrderTable"), orderTable);
	}

	@Override
	public int updateOrderTableByResNum(Map<String, OrderTable> orderTableMap) {
		return session.update(makeSqlId("updateOrderTableByResNum"), orderTableMap);
	}

	@Override
	public int deleteOrderTableByResNum(int resNum) {
		return session.delete(makeSqlId("deleteOrderTableByResNum"), resNum);
	}

	@Override
	public List<OrderTable> selectOrderTableByResNum(int resNum) {
		return session.selectList(makeSqlId("selectOrderTableByResNum"), resNum);
	}

}
