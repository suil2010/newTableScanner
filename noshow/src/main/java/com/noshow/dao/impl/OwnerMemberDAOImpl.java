package com.noshow.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.OwnerMemberDAO;
import com.noshow.vo.Restaurant;
import com.noshow.vo.RtCode;
import com.noshow.vo.Table;

@Repository
public class OwnerMemberDAOImpl implements OwnerMemberDAO {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.noshow.config.mybatis.mapper.ownerMemberMapper." + id;
	}

	@Override
	public List<RtCode> selectHolidayByCode() {
		return session.selectList(makeSqlId("selectHolidayByCode"));
	}

	@Override
	public List<RtCode> selectTermByCode() {
		return session.selectList(makeSqlId("selectTermByCode"));
	}

	@Override
	public List<RtCode> selectFieldByCode() {
		return session.selectList(makeSqlId("selectFieldByCode"));
	}

	@Override
	public int insertRestaurant(Restaurant rt) {
		return session.insert(makeSqlId("insertRestaurant"), rt);
	}
	

	
}