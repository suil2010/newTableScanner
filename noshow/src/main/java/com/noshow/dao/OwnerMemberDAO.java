package com.noshow.dao;

import java.util.List;

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
	
}