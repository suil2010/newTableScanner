package com.noshow.service;

import java.util.List;

import com.noshow.vo.Restaurant;
import com.noshow.vo.Table;

/**
 * 사업자 관련 업무로직 처리
 */
public interface OwnerMemberService {
	
	/**
	 * 사업자(식당) 등록
	 * rt : 등록할 식당정보
	 * role : 사용자 권한 / 관리자 - ROLE_ADMIN, 회원 - ROLE_MEMBER, 사업자회원 - ROLE_BUSINESS
	 */
	int insertRestaurant(Restaurant rt, String role);
	
	/**
	 * 식당정보 수정
	 */
	int updateRestaurant(Restaurant rt);
	
	/**
	 * 식당정보 삭제
	 */
	int deleteRestaurant(String businessId);
	
	/**
	 * 사업자 ID로 식당정보 조회처리 메소드
	 */
	Restaurant selectRestaurantByBusinessId(String businessId);
	
	/**
	 * 테이블정보 등록
	 */
	int insertTable(Table table);
	
	List<Table> selectTable(String id);
	
	int deleteTable(String id);
}
