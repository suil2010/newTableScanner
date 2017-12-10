package com.noshow.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.OwnerMemberDAO;
import com.noshow.vo.Field;
import com.noshow.vo.Holiday;
import com.noshow.vo.Restaurant;
import com.noshow.vo.Term;

@Repository
public class OwnerMemberDAOImpl implements OwnerMemberDAO {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.noshow.config.mybatis.mapper.ownerMemberMapper." + id;
	}

	@Override
	public List<Holiday> selectHoliday() {
		return session.selectList(makeSqlId("selectHoliday"));
	}

	@Override
	public List<Term> selectTerm() {
		return session.selectList(makeSqlId("selectTerm"));
	}

	@Override
	public List<Field> selectField() {
		return session.selectList(makeSqlId("selectField"));
	}

	@Override
	public int insertRestaurant(Restaurant rt) {
		return session.insert(makeSqlId("insertRestaurant"), rt);
	}

	@Override
	public Restaurant selectRestaurantByBusinessId(String businessId) {
		return session.selectOne(makeSqlId("selectRestaurantByBusinessId"), businessId);
	}
	
}