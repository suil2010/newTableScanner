package com.noshow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.ReservationService;
import com.noshow.vo.Member;
import com.noshow.vo.Reservation;

@Controller
public class ReservationController {

	
	@Autowired
	private ReservationService service;
	
	@RequestMapping("/addReservation")
	public ModelAndView addReservation(int resNum, String resDate, int resPeople, String resStartTime, String resPayStatement, String businessId, int tableSeq) {
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member)authentication.getPrincipal();
		
		String memberId = member.getMemberId();
		
		Reservation reservationInfo = service.addReservation(resNum, resDate, resPeople, resStartTime, resPayStatement, memberId, businessId, tableSeq);
		
		if (reservationInfo != null) {
			System.out.println("addReservation Controller - 예약 성공!");
			return new ModelAndView("/reservationSuccess.do", "reservationInfo",reservationInfo);
		} else {
			System.out.println("addReservation Controller - 예약 실패ㅠㅠ");
			return new ModelAndView("index.tiles");
		}
	} 
	
	@RequestMapping("/reservationSuccess")
	public ModelAndView reservationSuccess(HttpServletRequest request) {
		Reservation reservation = (Reservation)request.getAttribute("reservationInfo");
		String businessId = reservation.getBusinessId();
		System.out.println("reservationSuccess - businessId : " + businessId);
		String restaurantName = service.selectRestaurantNameByBusinessId(businessId);
		System.out.println("reservationSuccess - restaurantName : " + restaurantName);
		
		ModelAndView mov = new ModelAndView();
		mov.setViewName("reservation/reservation_success.tiles");
		mov.addObject("reservation", reservation);
		mov.addObject("restaurantName", restaurantName);
		return mov;
		
	}
	
}
