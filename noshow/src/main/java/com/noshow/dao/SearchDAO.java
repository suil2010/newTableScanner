package com.noshow.dao;

import java.util.List;
import java.util.Map;

import com.noshow.vo.Restaurant;
import com.noshow.vo.Table;

public interface SearchDAO {
	//Restaurant 조회
	Restaurant selectRestaurantByBusinessId(String businessId);
	
	//Restaurant 이름으로 조회
	int selectRestaurantByRtName(String rtName);
	
	/* 전체 테이블 조회 */
	List<Table> selectTable(String businessId);
	
	/* 2017.12.04 - 현준_검색 Test */
	List<Restaurant> selectRestaurantBySearch(Map<String, Object> searchInfo);
	
	/* 2017.12.05 - 현준_식당 이름으로 검색 */
	List<Restaurant> selectRestaurantByNameSearch(Map<String, String> searchInfo);
	
	/* 2017.12.04 - 현준_예약가능테이블 조회 TEST*/
	List<Table> selectUsableTable(Map<String, String> tableMap);

}
