package com.noshow.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.ReservationDAO;
import com.noshow.vo.Reservation;

@Repository
public class ReservationDAOImpl implements ReservationDAO{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "noshow.config.mybatis.mapper.reservationMapper." + id;
	}
	
	@Override
	public int insertReservation(Reservation reservation) {
		System.out.println(reservation);
		return session.insert(makeSqlId("insertReservation"), reservation);
	}

	@Override
	public int updateReservationByResNum(Reservation reservation) {
		return session.update(makeSqlId("updateReservationByResNum"), reservation);
	}

	@Override
	public int deleteReservationByResNum(int resNum) {
		return session.delete(makeSqlId("deleteReservationByResNum"), resNum);
	}

	@Override
	public List<Reservation> selectReservationByMemberId(String memberId) {
		return session.selectList(makeSqlId("selectReservationByMemberId"), memberId);
	}

	@Override
	public List<Reservation> selectReservationByBusinessId(String businessId) {
		return session.selectList(makeSqlId("selectReservationByBusinessId"), businessId);
	}

	@Override
	public int selectResNumByReservationInfo(Map<String, String> resInfoMap) {
		return session.selectOne(makeSqlId("selectResNumByReservationInfo"), resInfoMap);
	}
	
}
