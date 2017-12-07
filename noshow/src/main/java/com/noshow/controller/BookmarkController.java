package com.noshow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
		String resTime = (String)request.getAttribute("resTime");
		int resPeople = (Integer)request.getAttribute("resPeople");
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
		System.out.println("TestController.addBookmark - ");
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
}
