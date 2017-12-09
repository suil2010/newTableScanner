package com.noshow.service;

import java.util.List;
import java.util.Map;

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
	void insertRestaurant(Restaurant rt, String role);
	
	/**
	 * 식당정보 수정
	 * @return 
	 */
	int updateRestaurant(Restaurant rt);
	
	/**
	 * 식당정보 삭제
	 */
	int deleteRestaurant(String businessId);
	
	/**
	 * 사업자 ID로 식당정보 조회처리 메소드
	 */
	Restaurant selectRestaurantByBusinessIdResInfo(String resDate, String resTime, String memberId, String businessId);
	
	
	Restaurant selectRestaurantByBusinessId(String memberId, String businessId);

	
	/**
	 * 모든 식당을 조회하는 메소드
	 */
	   
	List<Restaurant> selectAllRestaurant();
	
	/**
	 * 이름으로 식당 조회
	 */
	int selectRestaurantByRtName(String rtName);
	
	/**
	 * 검색(예약)조건으로 음식점 조회
	 * @param resPlace
	 * @param resDate
	 * @param resTime
	 * @param resPeople
	 * @return
	 */
	List<Restaurant> selectRestaurantBySearch(String memberId, String resPlace, String resDate, String resTime, int resPeople);
	
	/**
	 * 주소+식당이름으로 음식점 조회
	 * @param resPlace
	 * @param resName
	 * @return
	 */
	List<Restaurant> selectRestaurantByNameSearch(String memberId, String resPlace, String resName);
	
	/**
	 * 테이블정보 등록
	 */
	int insertTable(Table table);
	
	List<Table> selectTable(String id);
	
	int deleteTable(String id);

	List<Map<Object,Object>> selectSales(String id);
	
	/**
	 * 사용가능 테이블 - ajax 위함
	 * @param resDate
	 * @param resTime
	 * @param restaurant
	 * @return
	 */
	List<Table> selectUsableTable(String resDate, String resTime, String businessId);
	
}
