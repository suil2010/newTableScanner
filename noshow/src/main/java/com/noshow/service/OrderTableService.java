package com.noshow.service;

import java.util.List;
import java.util.Map;

import com.noshow.vo.OrderTable;

public interface OrderTableService {
	
	int addOrderTable(OrderTable orderTable);
	
	int updateOrderTable(Map<String, OrderTable> orderTableMap);
	
	int deleteOrderTable(int resNum);
	
	List<OrderTable> selectOrderTableByResNum(int resNum);
}
