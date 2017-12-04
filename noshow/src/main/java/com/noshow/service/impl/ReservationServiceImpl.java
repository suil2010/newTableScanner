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
		int resNum = 0;
		int resPrice = calTotalPrice(businessId, resPeople);
		resStartTime = calStartTime(resDate, resStartTime);
		String resEndTime = calResEndTime(businessId, resStartTime);
		Date resPaidTime = new Date();
		for(int table : tableSeq ) {
			System.out.println("사용자가 고른 테이블 : "+table);
		}
	
		Reservation reservation = new Reservation(resNum, resDate, resPeople, resStartTime, resEndTime, resPaidTime,
				resPayStatement, resPrice, memberId, businessId);
		System.out.println("예약 insert 전 seq값 : "+reservation.getResNum());
		int result = dao.insertReservation(reservation);
		System.out.println("예약 insert 후 seq값 : " + reservation.getResNum());
		System.out.println(result);
		
//		 = selectReservationByReservationInfo(memberId, businessId, resStartTime);
		resNum = reservation.getResNum();
		
		/* 예약테이블 추가를 위한 부분 */
		result = result + addOrderTable(tableSeq, resNum);
		
		// 검증
		if (result ==2) {
			System.out.println("insert 성공");
			return reservation;
		} else {
			System.out.println("insert 실패!!");
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
		System.out.println("resServiceImp.calResEndTime - resStartTime : "+resStartTime);
		Restaurant restaurant = restaurantDao.selectRestaurantByBusinessId(businessId);
		int rt_term = restaurant.getRtTerm();

		try {
			formatDate = dateForm.parse(resStartTime);
			Calendar cal = new GregorianCalendar(Locale.KOREA);
			cal.setTime(formatDate);
			cal.add(Calendar.HOUR, rt_term);
			String resEndTime = dateForm.format(cal.getTime());
			System.out.println("resServiceImp.calResEndTime - resEndTime : "+resEndTime);
			return resEndTime;
		} catch (ParseException e) {
			System.out.println("Date 변환 실패");
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
	private int calTotalPrice(String businessId, int resPeople) {
		System.out.println(businessId);
		Restaurant restaurant = restaurantDao.selectRestaurantByBusinessId(businessId);
		return restaurant.getRtDeposit() * resPeople;
	}

	
	/* OrderTableService */
	@Override
	public int addOrderTable(List<Integer> tableSeq, int resNum) {
		int result = 0;
		
		System.out.println("ResServiceImpl-addOrderTable-resNum : " + resNum);
		for(int tableNum : tableSeq) {
			OrderTable orderTable = new OrderTable(tableNum, resNum);
			result = orderTableDao.insertOrderTable(orderTable);
			System.out.println("addOrderTable - result"+result);
			if (result == 1) {
				System.out.println("예약 번호 : "+resNum+" - 테이블 번호 : "+tableNum+"insert 성공");
			} else {
				System.out.println("예약 번호 : "+resNum+" - 테이블 번호 : "+tableNum+"insert 실패!!");
			}
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
		return	restaurantDao.selectRestaurantByBusinessId(businessId);
	}

	@Override
	public List<Reservation> selectJoinReservationByMemId(String memberId) {
		return dao.selectJoinReservationByMemId(memberId);
	}

	
	/* 사용가능한 테이블만 뽑아오기 위한 로직 */
	@Override
	public List<Table> selectUsableTable(String resDate, String resTime, String businessId) {

		String resStartTime = calStartTime(resDate, resTime);
		String resEndTime = calResEndTime(businessId, resStartTime);
		Map<String, String> tableMap = new HashMap<>();
		tableMap.put("resStartTime", resStartTime);
		tableMap.put("resEndTime", resEndTime);
		tableMap.put("businessId", businessId);
		List<Table> tableList = restaurantDao.selectUsableTable(tableMap);
		
		for(Table t : tableList) {
			System.out.println("ReservationServiceImpl.selectUsableTable - "+t);
		}
		return tableList;
	}

}
