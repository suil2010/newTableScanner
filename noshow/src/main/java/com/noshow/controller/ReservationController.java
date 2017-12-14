package com.noshow.controller;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.noshow.service.ReservationService;
import com.noshow.vo.Member;
import com.noshow.vo.Reservation;
import com.noshow.vo.Restaurant;


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
	
	@RequestMapping("/ownerAddReservation")
	public String ownerAddReservation(String resDate, int resPeople, String resStartTime, String resPayStatement, @RequestParam List<Integer> tableList, RedirectAttributes redirectAttributes) {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member)authentication.getPrincipal();
		String memberId = member.getMemberId();
		Reservation reservation = service.addReservation(resDate, resPeople, resStartTime, resPayStatement, memberId, memberId, tableList);
		redirectAttributes.addAttribute("resNum", reservation.getResNum());
		
		return "redirect:/reservationSuccess.do";

	} 
	
	@RequestMapping("/reservationSuccess")
	public ModelAndView reservationSuccess(int resNum) {
		Reservation reservation = service.selectReservationByResNum(resNum);
		return new ModelAndView("reservation/reservation_success.tiles","reservation", reservation );

	}
	
//	 내 예약 조회 
	@RequestMapping("/myReservation")
	public ModelAndView myReservation(ModelMap model) {
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Member member = (Member)authentication.getPrincipal();
		
		String memberId = member.getMemberId();
		List<Reservation> reservationList = service.selectJoinReservationByMemId(memberId);
		Collection authorizes = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(Object auth : authorizes) {
			SimpleGrantedAuthority authority = (SimpleGrantedAuthority)auth;
			if(authority.getAuthority().equals("ROLE_MEMBER")) {
				model.addAttribute("tabMenu", "true");  
				break;
			}
		}
		return new ModelAndView("tabmenu/mypage/mypage_reservation.tiles", "reservationList", reservationList);
	}
	
	@RequestMapping("/cancelReservation")
	@Transactional
	public ModelAndView cancelReservation(int resNum, ModelMap model) {
		String memberAuth = "member";
		service.deleteReservation(resNum);
		Collection authorizes = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(Object auth : authorizes) {
			SimpleGrantedAuthority authority = (SimpleGrantedAuthority)auth;
			if(authority.getAuthority().equals("ROLE_MEMBER")) {
				model.addAttribute("tabMenu", "true"); 
				break;
			} else if (authority.getAuthority().equals("ROLE_OWNER")) {
				memberAuth = "owner";
				break;
			}
		}
		if (memberAuth.equals("owner")) {
			return new ModelAndView("redirect:/ownerMyReservation.do");
		} 
		return new ModelAndView("redirect:/myReservation.do");
	}
	
//	 결제 
	@RequestMapping("/payment")
	public ModelAndView payment(String resDate, int resPeople, String resStartTime, String resPayStatement, String businessId, @RequestParam List<Integer> tableList) {
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
	
	/* 2017.12.11 - 현준_예약폼에서 인원 변경에 따른 총 금액 ajax 처리 Controller */
	@RequestMapping(value="/totalResPrice",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String totalResPrice(int resPeople, String businessId) {
		int totalPrice = service.calTotalPrice(businessId, resPeople);
		
		return new DecimalFormat("#,### 원").format(totalPrice);
	}
	
	/* 2017.12.15 - 현준_사업주 예약조회 */
	@RequestMapping("/ownerMyReservation")
	public ModelAndView ownerMyReservation() {
		// 현재 사용자(사업주) 정보를 받아와서 member 객체 생성
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Member member = (Member)authentication.getPrincipal();
		String businessId = member.getMemberId();
		List<Reservation> reservationList = service.selectJoinReservationByBusinessId(businessId);
		
		return new ModelAndView("owner/reservation_info.tiles","reservationList", reservationList);

	}
	
}
