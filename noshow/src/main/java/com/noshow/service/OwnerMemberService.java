package com.noshow.service;

import java.util.List;
import java.util.Map;

import com.noshow.vo.Restaurant;

/**
 * 사업자 관련 업무로직 처리
 */
public interface OwnerMemberService {

	/**
	 * 휴무일, 이용시간 간격, 업종 map으로 저장
	 * @return
	 */
	Map<String, Object> selectcode();

	
	/**
	 * 사업자(식당) 등록
	 * rt : 등록할 식당정보
	 * role : 사용자 권한 / 관리자 - ROLE_ADMIN, 회원 - ROLE_MEMBER, 사업자회원 - ROLE_BUSINESS
	 */
	void insertRestaurant(Restaurant rt, String role);
	
	/**
	 * businessId로 사업자(식당) 정보 조회
	 * @param businessId
	 * @return
	 */
	Restaurant selectRestaurantByBusinessId(String businessId);
	
	/**
	 * 식당 정보 수정
	 * @param restaurant
	 */
	void updateRestaurant(Restaurant rt);
	
	/**
	 * 사업자 전체 조회
	 * @return
	 */
	List<Restaurant> selectAllRestaurant();
}
