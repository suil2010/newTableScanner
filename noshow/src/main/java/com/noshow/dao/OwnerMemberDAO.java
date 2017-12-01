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
	
	int insertTable(Table table);

	List<Table> selectTable(String id);
	
}