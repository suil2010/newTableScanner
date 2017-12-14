package com.noshow.service;

import java.util.List;
import java.util.Map;

import com.noshow.vo.OrderTable;
import com.noshow.vo.Reservation;
import com.noshow.vo.Restaurant;

public interface ReservationService {

	/* ##### ReservationService ##### */
	Reservation addReservation(String resDate, int resPeople, String resStartTime, String resPayStatement, String memberId, String businessId, List<Integer> tableSeq);

	int updateReservation(Reservation reservation, Map<String, OrderTable> orderTableMap);

	int deleteReservation(int resNum);

	List<Reservation> selectReservationByMemberId(String memberId);

	List<Reservation> selectReservationByBusinessId(String businessId);

	int calTotalPrice(String businessId, int resPeople);

	/* ##### OrderTableService ##### */
	int addOrderTable(List<Integer> tableSeq, int resNum);

	int updateOrderTable(Map<String, OrderTable> orderTableMap);

	int deleteOrderTable(int resNum);

	List<OrderTable> selectOrderTableByResNum(int resNum);
	
	Restaurant selectRestaurantByBusinessId(String businessId);

	List<Reservation> selectJoinReservationByMemId(String memberId);
	
	Reservation selectReservationByResNum(int resNum);

	List<Reservation> selectJoinReservationByBusinessId(String businessId);
}
