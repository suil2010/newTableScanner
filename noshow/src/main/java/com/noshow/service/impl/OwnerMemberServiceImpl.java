package com.noshow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noshow.dao.OwnerMemberDAO;
import com.noshow.service.OwnerMemberService;
import com.noshow.vo.Restaurant;
import com.noshow.vo.Table;

@Service
public class OwnerMemberServiceImpl implements OwnerMemberService{
  
   @Resource
   private OwnerMemberDAO dao;
   
   
	@Override
	public int insertRestaurant(Restaurant rt, String role) {
		return dao.insertRestaurant(rt);
	}
	
	@Override
	public int updateRestaurant(Restaurant rt) {
		return dao.updateRestaurant(rt);
	}
	
	@Override
	public int deleteRestaurant(String businessId) {
		return dao.deleteRestaurant(businessId);
	}
	
	@Override
	public Restaurant selectRestaurantByBusinessId(String businessId) {
		return dao.selectRestaurantByBusinessId(businessId);
	}

	@Override
	public int insertTable(Table table) {
		return dao.insertTable(table);
	}

	@Override
	public List<Table> selectTable(String id) {
		return dao.selectTable(id);
	}

	@Override
	public int deleteTable(String id) {
		return dao.deleteTable(id);
	}

	


}