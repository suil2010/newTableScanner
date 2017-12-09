package com.noshow.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.noshow.service.ReservationService;
import com.noshow.vo.Member;
import com.noshow.vo.Reservation;


@Controller
public class ReservationController {
	
	@Autowired
	private ReservationService service;
	
	@RequestMapping("/addReservation")
	@Transactional
	public String addReservation(String resDate, int resPeople, String resStartTime, String resPayStatement, String businessId, @RequestParam List<Integer> tableList, RedirectAttributes redirectAttributes) {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member)authentication.getPrincipal();
		String memberId = member.getMemberId();
		Reservation reservation = service.addReservation(resDate, resPeople, resStartTime, resPayStatement, memberId, businessId, tableList);
		redirectAttributes.addAttribute("resNum", reservation.getResNum());
		
		return "redirect:/reservationSuccess.do";

	} 
	
	@RequestMapping("/reservationSuccess")
	public ModelAndView reservationSuccess(int resNum) {
		Reservation reservation = service.selectReservationByResNum(resNum);
		return new ModelAndView("reservation/reservation_success.tiles","reservation", reservation );

	}
	
	/* 내 예약 조회 */
	@RequestMapping("/myReservation")
	public ModelAndView myReservation() {
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Member member = (Member)authentication.getPrincipal();
		
		String memberId = member.getMemberId();
		List<Reservation> reservationList = service.selectJoinReservationByMemId(memberId);
	
		return new ModelAndView("member/mypage_reservation.tiles", "reservationList", reservationList);
		
	}
	
	/* 결제 */
	@RequestMapping("/payment")
	public ModelAndView payment(String resDate, int resPeople, String resStartTime, String resPayStatement, String businessId, @RequestParam List<Integer> tableList) {
		System.out.println("payment호출");
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member)authentication.getPrincipal();
		String memberId = member.getMemberId();
		int resPrice = service.calTotalPrice(businessId, resPeople);
		Reservation reservation = new Reservation(resDate, resPeople, resStartTime, resPrice, resPayStatement ,memberId, businessId, tableList);
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("member", member);
		mav.addObject("reservation", reservation);
		mav.setViewName("reservation/payment.tiles");
		return mav; 
	}
	
}
