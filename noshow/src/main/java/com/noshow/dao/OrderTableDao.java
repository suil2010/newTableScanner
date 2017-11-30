package com.noshow.dao;

import java.util.List;
import java.util.Map;

import com.noshow.vo.OrderTable;

public interface OrderTableDao {
	
	/**
	 * 예약 테이블 추가
	 * @param orderTable
	 * @return
	 */
	int insertOrderTable(OrderTable orderTable);
	
	/**
	 * 예약번호로 예약 테이블 변경(수정)
	 * @param orderTable
	 * @return
	 */
	int updateOrderTableByResNum(Map<String, OrderTable> orderTableMap);
	
	/**
	 * 예약번호로 예약테이블 삭제
	 * @param orderTable
	 * @return
	 */
	int deleteOrderTableByResNum(int resNum);
	
	/**
	 * 예약번호로 예약테이블 조회
	 * @param resNum
	 * @return
	 */
	List<OrderTable> selectOrderTableByResNum(int resNum);
}
