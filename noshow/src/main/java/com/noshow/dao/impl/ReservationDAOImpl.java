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
		return "com.noshow.config.mybatis.mapper.reservationMapper." + id;
	}
	
	@Override
	public int insertReservation(Reservation reservation) {
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
	public Reservation selectReservationByReservationInfo(Map<String, String> resInfoMap) {
		return session.selectOne(makeSqlId("selectReservationByReservationInfo"), resInfoMap);
	}

	@Override
	public List<Reservation> selectJoinReservationByMemId(String memberId) {
		return session.selectList(makeSqlId("selectJoinReservationByMemId"), memberId);
	}

	@Override
	public Reservation selectReservationByResNum(int resNum) {
		return session.selectOne(makeSqlId("selectReservationByResNum"), resNum);
	}
	
}
