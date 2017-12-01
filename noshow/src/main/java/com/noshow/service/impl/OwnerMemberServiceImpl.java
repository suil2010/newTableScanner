package com.noshow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noshow.dao.AuthorityDao;
import com.noshow.dao.OwnerMemberDAO;
import com.noshow.service.OwnerMemberService;
import com.noshow.vo.Authority;
import com.noshow.vo.Restaurant;
import com.noshow.vo.Table;

@Service
public class OwnerMemberServiceImpl implements OwnerMemberService{
  
   @Resource
   private OwnerMemberDAO dao;
   
   @Resource
   private AuthorityDao authoritydao;
   
	@Override
	@Transactional
	public void insertRestaurant(Restaurant rt, String role) {
		dao.insertRestaurant(rt);
		authoritydao.updateAuthority(new Authority(rt.getBusinessId(), role));
	}
	
	@Override
	public int updateRestaurant(Restaurant rt, String role) {
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
	public List<Restaurant> selectAllRestaurant() {
		return dao.selectAllRestaurant();
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