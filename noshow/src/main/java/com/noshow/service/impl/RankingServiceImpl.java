package com.noshow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noshow.dao.OwnerMemberDAO;
import com.noshow.dao.RankingDAO;
import com.noshow.service.RankingService;
import com.noshow.vo.Ranking;
import com.noshow.vo.Restaurant;

@Service
public class RankingServiceImpl implements RankingService{

	@Resource
	private RankingDAO dao;
	
	@Resource
	private OwnerMemberDAO restaurantDao;
	
	@Override
	public List<Ranking> rankByCountRes() {
		List<Ranking> rankingList = dao.rankByCountRes();
		rankingList = selectRestaurant(rankingList);
		return rankingList;
	}

	@Override
	public List<Ranking> rankByAvgGrade() {
		List<Ranking> rankingList = dao.rankByAvgGrade();
		rankingList = selectRestaurant(rankingList);
		return rankingList;
	}
	
	private List<Ranking> selectRestaurant(List<Ranking> rankingList) {
		for(Ranking r : rankingList) {
			Restaurant restaurant = restaurantDao.selectRestaurantByBusinessId(r.getBusinessId());
			r.setRestaurant(restaurant);
		}
		return rankingList;
	}

	
}
