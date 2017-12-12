package com.noshow.service;

import java.util.List;

import com.noshow.vo.Restaurant;
import com.noshow.vo.Table;

public interface SearchService {

	Restaurant selectRestaurantByBusinessId(String memberId, String businessId);

	
	/**
	 * 사업자 ID로 식당정보 조회처리 메소드
	 */
	Restaurant selectRestaurantByBusinessIdResInfo(String resDate, String resTime, String memberId, String businessId);
	
	/**
	 * 검색(예약)조건으로 음식점 조회
	 * @param resPlace
	 * @param resDate
	 * @param resTime
	 * @param resPeople
	 * @return
	 */
	List<Restaurant> selectRestaurantBySearch(String memberId, String resPlace, String resDate, String resTime, int resPeople);
	
	/* int selectRestaurantByRtName(String rtName); */
	
	/**
	 * 주소+식당이름으로 음식점 조회
	 * @param resPlace
	 * @param resName
	 * @return
	 */
	List<Restaurant> selectRestaurantByNameSearch(String memberId, String resPlace, String resName);

	
	/**
	 * 사용가능 테이블 - ajax 위함
	 * @param resDate
	 * @param resTime
	 * @param restaurant
	 * @return
	 */
	List<Table> selectUsableTable(String resDate, String resTime, String businessId);
	
	/** 2017.12.11
	 * 예약내역 있는지 여부 Check - 리뷰 작성권한 check 위함_TEST
	 * @param memberId
	 * @param businessId
	 * @return
	 */
	Restaurant selectCountCheckRervation(String memberId, String businessId, Restaurant restaurant);
}
