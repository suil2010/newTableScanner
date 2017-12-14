package com.noshow.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noshow.dao.OrderTableDao;
import com.noshow.dao.OwnerMemberDAO;
import com.noshow.dao.ReservationDAO;
import com.noshow.service.ReservationService;
import com.noshow.vo.OrderTable;
import com.noshow.vo.Reservation;
import com.noshow.vo.Restaurant;
import com.noshow.vo.Table;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Resource
	private ReservationDAO dao;
	
	@Resource 
	private OwnerMemberDAO restaurantDao;
	
	@Resource 
	private OrderTableDao orderTableDao;

	@Override
	@Transactional
	public Reservation addReservation(String resDate, int resPeople, String resStartTime, String resPayStatement, String memberId, String businessId, List<Integer> tableSeq) {

		// 사업주가 설정한 1인당 예약금을 예약 인원에 맞게 초기화
		int resPrice = calTotalPrice(businessId, resPeople);
		resStartTime = calStartTime(resDate, resStartTime);
		String resEndTime = calResEndTime(businessId, resStartTime);
		Date resPaidTime = new Date();
	
		Reservation reservation = new Reservation(resDate, resPeople, resStartTime, resEndTime, resPaidTime,
				resPayStatement, resPrice, memberId, businessId);
		int result = dao.insertReservation(reservation);
		
//		 = selectReservationByReservationInfo(memberId, businessId, resStartTime);
		int resNum = reservation.getResNum();
		
//		 예약테이블 추가를 위한 부분 
		result = result + addOrderTable(tableSeq, resNum);
		List<OrderTable> orderTable = orderTableDao.selectOrderTableByResNum(resNum);
		reservation.setOrderTable(orderTable);
		reservation.setRestaurant(selectRestaurantByBusinessId(businessId));
		// 검증
		if (result ==2) {
			return reservation;
		} else {
			return reservation;
		}
		
	}

	@Override
	@Transactional
	public int updateReservation(Reservation reservation, Map<String, OrderTable> orderTableMap) {
		return dao.updateReservationByResNum(reservation);
	}

	@Override
	@Transactional
	public int deleteReservation(int resNum) {
		return dao.deleteReservationByResNum(resNum);
	}

	@Override
	public List<Reservation> selectReservationByMemberId(String memberId) {
		return dao.selectReservationByMemberId(memberId);
	}

	@Override
	public List<Reservation> selectReservationByBusinessId(String businessId) {
		return dao.selectReservationByBusinessId(businessId);
	}
	
	
	/**
	 * 사용자에게 시간만 받아서 처리하기 위한 메소드
	 * @param resDate
	 * @param resStartTime
	 * @return
	 */
	private String calStartTime(String resDate, String resStartTime) {
		resStartTime = resDate + " " +resStartTime + ":00";
		return resStartTime;
	}
	
	
	/**
	 * 매개변수로 받은 사업주 ID로 rt_term (Table이용시간) 를 받고, resStartTime (예약시작시간) 에 re_term 을
	 * 더하여 resEndTime(예약종료시간)을 리턴
	 * 
	 * @param businessId
	 * @param resEndTime
	 * @return
	 */
	private String calResEndTime(String businessId, String resStartTime) {
		
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date formatDate;

		try {
			formatDate = dateForm.parse(resStartTime);
			Calendar cal = new GregorianCalendar(Locale.KOREA);
			cal.setTime(formatDate);
			//cal.add(Calendar.HOUR, rt_term);
			String resEndTime = dateForm.format(cal.getTime());
			return resEndTime;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resStartTime;
 
	}

	/**
	 * 사업주가 지정한 1인당 예약금을 예약인원에 맞추어 계산해서 리턴
	 * @param businessId
	 * @param resPeople
	 * @return
	 */
	@Override
	public int calTotalPrice(String businessId, int resPeople) {
		Restaurant restaurant = restaurantDao.selectRestaurantByBusinessId(businessId);
		return restaurant.getRtDeposit() * resPeople;
	}

	
	// OrderTableService 
	@Override
	@Transactional
	public int addOrderTable(List<Integer> tableSeq, int resNum) {
		int result = 0;
		
		for(int tableNum : tableSeq) {
			OrderTable orderTable = new OrderTable(tableNum, resNum);
			result = orderTableDao.insertOrderTable(orderTable);
		}
		return result;
	}

	@Override
	public int updateOrderTable(Map<String, OrderTable> orderTableMap) {
		return orderTableDao.updateOrderTableByResNum(orderTableMap);
	}

	@Override
	public int deleteOrderTable(int resNum) {
		return orderTableDao.deleteOrderTableByResNum(resNum);
	}

	@Override
	public List<OrderTable> selectOrderTableByResNum(int resNum) {
		return orderTableDao.selectOrderTableByResNum(resNum);
	}

	@Override
	public Restaurant selectRestaurantByBusinessId(String businessId) {
		return restaurantDao.selectRestaurantByBusinessId(businessId);
	}

	@Override
	public List<Reservation> selectJoinReservationByMemId(String memberId) {
		return dao.selectJoinReservationByMemId(memberId);
	}

	@Override
	public Reservation selectReservationByResNum(int resNum) {
		return dao.selectReservationByResNum(resNum);
	}

	@Override
	public List<Reservation> selectJoinReservationByBusinessId(String businessId) {
		return dao.selectJoinReservationByBusinessId(businessId);
	}

}
