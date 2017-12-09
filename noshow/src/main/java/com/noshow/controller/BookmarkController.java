package com.noshow.controller;

import java.util.List;


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

@Controller
public class BookmarkController {

/* 2017.12.07  즐겨찾기 TEST */
	
	@Autowired
	private BookmarkService service;
	
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
			return  new ModelAndView("member/myBookmark.tiles", "emptyBookmark", "등록된 즐겨찾기가 없습니다.");
		}
		
		return new ModelAndView("member/myBookmark.tiles", "myBookmarkList", myBookmarkList);
	}
	
}
