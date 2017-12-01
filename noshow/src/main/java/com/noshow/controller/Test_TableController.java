package com.noshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.OwnerMemberService;
import com.noshow.vo.Table;

@Controller
public class Test_TableController {
	
	@Autowired
	private OwnerMemberService service;
	
	@Autowired
	private OwnerMemberController controller;
	
	@RequestMapping("/restaurantList")
	public ModelAndView restaurantList(String businessId) {
		
		List table = controller.selectTable(businessId);
		System.out.println(table);
		
		// 점주ID 로 해당 음식점의 테이블 얻어오기
		List<Table> list = service.selectTable(businessId);
		
		String restrauntName = service.selectRestaurantByBusinessId(businessId).getRtName();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reservation/reservation_form.tiles");
		mav.addObject("table",table);
		mav.addObject("restaurantName", restrauntName);
		mav.addObject("tableList", list);
		mav.addObject("businessId", businessId);
		
		return mav;
		
	}
}
