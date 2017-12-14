package com.noshow.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.RankingDAO;
import com.noshow.vo.Ranking;

@Repository
public class RankingDAOImpl implements RankingDAO{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.noshow.config.mybatis.mapper.rankingMapper." + id;
	}
	
	@Override
	public List<Ranking> rankByCountRes() {
		return session.selectList(makeSqlId("rankByCountRes"));
	}

	@Override
	public List<Ranking> rankByAvgGrade() {
		return session.selectList(makeSqlId("rankByAvgGrade"));
	}
	

}
