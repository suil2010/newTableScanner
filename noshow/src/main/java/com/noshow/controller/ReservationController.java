package com.noshow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.ReservationService;
import com.noshow.vo.Member;
import com.noshow.vo.Reservation;

@Controller
public class ReservationController {

	
	@Autowired
	private ReservationService service;
	
	@RequestMapping("/addReservation")
	public ModelAndView addReservation(String resDate, int resPeople, String resStartTime, String resPayStatement, String businessId,@RequestParam List<Integer> tableList) {
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member)authentication.getPrincipal();
		
		String memberId = member.getMemberId();
		
		//test
		for(int i : tableList) {
			System.out.println("tableNum : "+i);
		}
		Reservation reservation = service.addReservation(resDate, resPeople, resStartTime, resPayStatement, memberId, businessId, tableList);
		
		if (reservation != null) {
			System.out.println("addReservation Controller - 예약 성공!");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/reservationSuccess.do");
			mav.addObject("reservation", reservation);
			mav.addObject("tableList", tableList);
			return mav;
		} else {
			System.out.println("addReservation Controller - 예약 실패ㅠㅠ");
			return new ModelAndView("index.tiles");
		}
	} 
	
	@RequestMapping("/reservationSuccess")
	public ModelAndView reservationSuccess(HttpServletRequest request) {
		Reservation reservation = (Reservation)request.getAttribute("reservation");
		String businessId = reservation.getBusinessId();
		System.out.println("reservationSuccess - businessId : " + businessId);
		String restaurantName = service.selectRestaurantNameByBusinessId(businessId);
		System.out.println("reservationSuccess - restaurantName : " + restaurantName);
		List<Integer> tableList = (List<Integer>) request.getAttribute("tableList");
		// 검증
		for(int table : tableList) {
			System.out.println("reservationSuccessController- table: " + table);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reservation/reservation_success.tiles");
		mav.addObject("reservation", reservation);
		mav.addObject("restaurantName", restaurantName);
		mav.addObject("tableList", tableList);

		return mav;
		
	}
	
}
