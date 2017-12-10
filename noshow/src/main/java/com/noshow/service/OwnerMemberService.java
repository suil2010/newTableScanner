package com.noshow.service;

import java.util.Map;

import com.noshow.vo.Restaurant;

/**
 * 사업자 관련 업무로직 처리
 */
public interface OwnerMemberService {

	Map<String, Object> selectcode();

	
	/**
	 * 사업자(식당) 등록
	 * rt : 등록할 식당정보
	 * role : 사용자 권한 / 관리자 - ROLE_ADMIN, 회원 - ROLE_MEMBER, 사업자회원 - ROLE_BUSINESS
	 */
	void insertRestaurant(Restaurant rt, String role);
}
