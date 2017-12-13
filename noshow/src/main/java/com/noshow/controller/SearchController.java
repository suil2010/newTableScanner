package com.noshow.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.OwnerMemberService;
import com.noshow.service.SearchService;
import com.noshow.vo.Member;
import com.noshow.vo.Restaurant;
import com.noshow.vo.Table;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService service;
	
//	 2017.12.09 현준_OwnerMemberController에서 분리 
	@RequestMapping("/restaurantList")
	public ModelAndView restaurantList(String businessId, int resPeople, String resDate, String resTime) {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member) authentication.getPrincipal();
		String memberId = member.getMemberId();
		
		Restaurant restaurant = service.selectRestaurantByBusinessIdResInfo(resDate,resTime,memberId,businessId);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reservation/reservation_form.tiles");
		mav.addObject("resDate", resDate);
		mav.addObject("resTime", resTime);
		mav.addObject("resPeople", resPeople);
		mav.addObject("restaurant", restaurant);
		mav.addObject("businessId", businessId);
		System.out.println(restaurant); 
		return mav;
	}
	
	
//	 이름검색으로 넘어오는 경우 식당상세 이동  처리 컨트롤러 
	@RequestMapping("/restaurantListByName")
	public ModelAndView restaurantListByName(String businessId) {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member) authentication.getPrincipal();
		String memberId = member.getMemberId();
		
		Restaurant restaurant = service.selectRestaurantByBusinessId(memberId, businessId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reservation/reservation_form.tiles");
		mav.addObject("restaurant", restaurant);
		
		return mav;
	}
	
	// 예약정보를 받아서 처리하는 controller 
	@RequestMapping("/searchRestaurant")
	public ModelAndView searchRestaurant(String resPlace, String resDate, String resTime, Integer resPeople, HttpSession session) {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member) authentication.getPrincipal();
		String memberId = member.getMemberId();
		List<Restaurant> restaurantList = service.selectRestaurantBySearch(memberId, resPlace, resDate, resTime, resPeople);
		
		//검증
		if (restaurantList.isEmpty()) {
			return new ModelAndView("reservation/restaurant_list.tiles", "notfountRestaurant", "해당 조건에 맞는 음식점이 없습니다.");
		} else {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("reservation/restaurant_list.tiles");
			mav.addObject("restaurantList", restaurantList);
			mav.addObject("resPeople", resPeople);
			mav.addObject("resDate", resDate);
			mav.addObject("resTime", resTime);
			return mav;
		}
	}
	
	
	@RequestMapping("/searchRestaurantByName")
	public ModelAndView searchRestaurantByName(String resPlace, String resName) {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member) authentication.getPrincipal();
		String memberId = member.getMemberId();
		
		List<Restaurant> restaurantList = service.selectRestaurantByNameSearch(memberId, resPlace, resName);
		if (restaurantList.isEmpty()) {
			return new ModelAndView("reservation/restaurant_list.tiles", "notfountRestaurant", "해당 조건에 맞는 음식점이 없습니다.");
		} else {
			return new ModelAndView("reservation/restaurant_list.tiles","restaurantList", restaurantList);
		}
		
	}
	
//	 2017.12.05 23:18-현준_ajax Controller 
//	 2017.12.09 02:38 정리 완료 
	@RequestMapping("/reSearchTable")
	@ResponseBody
	public List<Table> reSearchTable(String resDate, String resStartTime, String businessId) throws IOException {
		System.out.println("SearchController-------------------------");
		return service.selectUsableTable(resDate, resStartTime, businessId);
	}
	
}
