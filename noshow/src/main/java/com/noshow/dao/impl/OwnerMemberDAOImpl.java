package com.noshow.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.OwnerMemberDAO;
import com.noshow.vo.Restaurant;

@Repository
public class OwnerMemberDAOImpl implements OwnerMemberDAO{
   
   @Autowired
   private SqlSessionTemplate session;
   
   private String makeSqlId(String id) {
      return "com.noshow.config.mybatis.mapper.ownerMemberMapper." + id;
   }

   @Override
   public int insertRestaurant(Restaurant rt) {
      return session.insert(makeSqlId("insertRestaurant"), rt);
   }

	   @Override
	public int updateRestaurant(Restaurant rt) {
		return session.update(makeSqlId("updateRestaurant"), rt);
	}
	
	@Override
	public int deleteRestaurant(String businessId) {
		return session.delete(makeSqlId("deleteRestaurant"), businessId);
	}

	@Override
	public Restaurant selectRestaurantByBusinessId(String businessId) {
		return session.selectOne(makeSqlId("selectRestaurantByBusinessId"), businessId);
	}	

}