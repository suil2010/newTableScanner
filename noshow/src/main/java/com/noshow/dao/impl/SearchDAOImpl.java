package com.noshow.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.SearchDAO;
import com.noshow.vo.Restaurant;
import com.noshow.vo.Table;

@Repository
public class SearchDAOImpl implements SearchDAO{
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.noshow.config.mybatis.mapper.searchMapper." + id;
	}

	@Override
	public Restaurant selectRestaurantByBusinessId(String businessId) {
		return session.selectOne(makeSqlId("selectRestaurantByBusinessId"), businessId);
	}
	
	@Override
	public int selectRestaurantByRtName(String rtName) {
		return session.selectOne(makeSqlId("selectRestaurantByRtName"), rtName);
	}
	
	@Override
	public List<Restaurant> selectRestaurantBySearch(Map<String, Object> searchInfo) {
		return session.selectList(makeSqlId("selectRestaurantBySearch"), searchInfo);
	}
	
	@Override
	public List<Table> selectUsableTable(Map<String, String> tableMap) {
		return session.selectList(makeSqlId("selectUsableTable"), tableMap);
	}
	
	@Override
	public List<Restaurant> selectRestaurantByNameSearch(Map<String, String> searchInfo) {
		return session.selectList(makeSqlId("selectRestaurantByNameSearch"), searchInfo);
	}

	@Override
	public List<Table> selectTable(String businessId) {
		return session.selectList(makeSqlId("selectTable"), businessId);
	}
}
