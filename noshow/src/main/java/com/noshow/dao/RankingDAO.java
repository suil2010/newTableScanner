package com.noshow.dao;

import java.util.List;

import com.noshow.vo.Ranking;

public interface RankingDAO {
	
	List<Ranking> rankByCountRes();
	
	List<Ranking> rankByAvgGrade();
}
