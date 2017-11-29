package com.noshow.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noshow.dao.OwnerMemberDAO;
import com.noshow.service.OwnerMemberService;
import com.noshow.vo.Restaurant;

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

}