package com.noshow.dao;

import java.util.List;
import java.util.Map;

import com.noshow.vo.Restaurant;
import com.noshow.vo.Table;

public interface OwnerMemberDAO {
	
	//Restaurant DB저장
	int insertRestaurant(Restaurant rt);
	
	//Restaurant DB수정
	int updateRestaurant(Restaurant rt);
	
	//Restaurant DB삭제
	int deleteRestaurant(String businessId);
	
	//Restaurant 조회
	Restaurant selectRestaurantByBusinessId(String businessId);
	
	//Restaurant 전체조회
	List<Restaurant> selectAllRestaurant();
	
	//Restaurant 이름으로 조회
	int selectRestaurantByRtName(String rtName);

	int insertTable(Table table);

	List<Table> selectTable(String id);
	
	int deleteTable(String id);
	
	/* 2017.12.04 - 현준_검색 Test */
	List<Restaurant> selectRestaurantBySearch(Map<String, Object> searchInfo);
	
	/* 2017.12.05 - 현준_식당 이름으로 검색 */
	List<Restaurant> selectRestaurantByNameSearch(Map<String, String> searchInfo);
	
	/* 2017.12.04 - 현준_예약가능테이블 조회 TEST*/
	List<Table> selectUsableTable(Map<String, String> tableMap);

	List<Map<Object,Object>> selectSales(String id);
	
}