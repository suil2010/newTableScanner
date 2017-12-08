package com.noshow.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.BookmarkService;
import com.noshow.vo.Bookmark;
import com.noshow.vo.Member;
import com.noshow.vo.Restaurant;
import com.noshow.vo.Table;

@Controller
public class BookmarkController {

/* 2017.12.07  즐겨찾기 TEST */
	
	@Autowired
	private BookmarkService service;
	
	
	@RequestMapping("/finalResInfo")
	public ModelAndView finalResInfo(HttpServletRequest request) {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member)authentication.getPrincipal();
		String memberId = member.getMemberId();
		
		Restaurant restaurant = (Restaurant)request.getAttribute("restaurant");
		List<Table> allTable = (List<Table>) request.getAttribute("allTable");

		
		String resDate = (String)request.getAttribute("resDate");
		String businessId = restaurant.getBusinessId();
		int result = service.selectCheckBookmark(memberId, businessId);
		System.out.println("TestController.finalResInfo - bookmarkCheck - result : " + result);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reservation/reservation_form.tiles");
		mav.addObject("restaurant", restaurant);
		mav.addObject("allTable",allTable);
		mav.addObject("bookmarkCheck", result);
		if (resDate == null) {
			return mav;
		} else {
			String resTime = (String)request.getAttribute("resTime");
			int resPeople = (Integer)request.getAttribute("resPeople");
			List<Table> tableList = (List<Table>)request.getAttribute("tableList");
			mav.addObject("resDate", resDate);
			mav.addObject("resTime", resTime);
			mav.addObject("resPeople", resPeople);
		}
		return mav;
	}
	
	/* reservation_form - 즐겨찾기 추가 ajax 처리 Controller */
	@RequestMapping("/addBookmark")
	@ResponseBody
	public int addBookmark(String businessId) {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member)authentication.getPrincipal();
		String memberId = member.getMemberId();
		Bookmark bookmark = new Bookmark(memberId, businessId);
		System.out.println("TestController.addBookmark - " + bookmark);
		int result = service.addBookmark(bookmark);
		
		return result;
	}
	
	/* reservation_form - 즐겨찾기 삭제 ajax 처리 Controller */
	@RequestMapping("/deleteBookmark")
	@ResponseBody
	public int deleteBookmark(String businessId) {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member)authentication.getPrincipal();
		String memberId = member.getMemberId();
		Bookmark bookmark = new Bookmark(memberId, businessId);
		int result = service.deleteBookmark(bookmark);
		return result;
	}
	
	/* 사용자 ID 로 즐겨찾기 조회 Controller */
	@RequestMapping("/myBookmarkList")
	public ModelAndView myBookmark() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member)authentication.getPrincipal();
		String memberId = member.getMemberId();
		List<Bookmark> myBookmarkList = service.selectBookmarkBymemberId(memberId);
		if (myBookmarkList.isEmpty()) {
			System.out.println("BookmarkController.myBookmark - myBookmarkList = null! 등록한 즐겨찾기가 없다!!");
			return  new ModelAndView("member/myBookmark.tiles", "emptyBookmark", "등록된 즐겨찾기가 없습니다.");
		}
		return new ModelAndView("member/myBookmark.tiles", "myBookmarkList", myBookmarkList);
	}
	
	@RequestMapping("/checkBookmark")
	public ModelAndView checkBookmark(HttpServletRequest request) {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member)authentication.getPrincipal();
		String memberId = member.getMemberId();
		
		List<Restaurant> restaurantList = (List<Restaurant>) request.getAttribute("restaurantList");
		String resDate = (String)request.getAttribute("reDate");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reservation/restaurant_list.tiles");
		mav.addObject("restaurantList", restaurantList);
		if (restaurantList.isEmpty()) {
			System.out.println("BookmarkController.checkBookmark- 조건에 맞는 음식점이 없습니다~");
			return new ModelAndView("reservation/restaurant_list.tiles", "notfountRestaurant", "해당 조건에 맞는 음식점이 없습니다.");
		} else {
			List<Integer> list = new ArrayList<>();
			for(Restaurant r : restaurantList) {
				String businessId = r.getBusinessId();
				System.out.println("######## businessId : " + businessId);
				int result = service.selectCheckBookmark(memberId, businessId);
				list.add(result);
			}
			mav.addObject("result", list);
		}
		if (resDate == null) {
			return mav;
		} else {
			String resTime = (String)request.getAttribute("resTime");
			int resPeople = (Integer)request.getAttribute("resPeople");
			List<Table> tableList = (List<Table>)request.getAttribute("tableList");
			mav.addObject("resDate", resDate);
			mav.addObject("resTime", resTime);
			mav.addObject("resPeople", resPeople);
		}
		return mav;
	}
}
