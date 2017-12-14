package com.noshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.RankingService;
import com.noshow.vo.Ranking;

@Controller
public class RankingController {
	
	@Autowired
	private RankingService service;
	
	@RequestMapping("/rankByCountRes")
	public ModelAndView rankByCountRes() {
		List<Ranking> rankingList = service.rankByCountRes();
		return new ModelAndView("common/ranking_list.tiles", "rankingList", rankingList);
	}
	
	@RequestMapping("/rankByAvgGrade")
	public ModelAndView rankByAvgGrade() {
		List<Ranking> rankingList = service.rankByAvgGrade();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/ranking_list.tiles");
		mav.addObject("avgCnt", "Check");
		mav.addObject("rankingList", rankingList);
		return mav;
	}

}
