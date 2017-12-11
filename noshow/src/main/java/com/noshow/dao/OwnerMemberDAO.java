package com.noshow.dao;

import java.util.List;

import com.noshow.vo.Field;
import com.noshow.vo.Holiday;
import com.noshow.vo.Member;
import com.noshow.vo.Restaurant;
import com.noshow.vo.Term;

public interface OwnerMemberDAO {
	
	/**
	 * 휴무일 목록 조회
	 * @return
	 */
	List<Holiday> selectHoliday();
	
	/**
	 * 식당 이용 시간 목록 조회
	 * @return
	 */
	List<Term> selectTerm();
	
	/**
	 * 업종 목록 조회
	 * @return
	 */
	List<Field> selectField();
	
	/**
	 * 사업자 등록
	 * @param rt
	 * @return
	 */
	int insertRestaurant(Restaurant rt);
	
 	/**
 	 * 회원 아이디로 식당 정보 조회
 	 * @param businessId
 	 * @return
 	 */
  	Restaurant selectRestaurantByBusinessId(String businessId);
  	
  	/*
  	 * 사업자 정보 수정
  	 */
  	int updateRestaurant(Restaurant rt);
  	
  	
  	/**
  	 * 모든 사업자 정보 조회
  	 * @return
  	 */
  	List<Restaurant> selectAllRestaurant();
	
}