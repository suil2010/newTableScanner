package com.noshow.service;

import java.util.List;

import com.noshow.vo.Ranking;

public interface RankingService {
	
	List<Ranking> rankByCountRes();
	
	List<Ranking> rankByAvgGrade();
}
