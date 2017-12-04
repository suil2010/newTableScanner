package com.noshow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.ReservationService;
import com.noshow.vo.Member;
import com.noshow.vo.OrderTable;
import com.noshow.vo.Reservation;
import com.noshow.vo.Table;


@Controller
public class ReservationController {
	
	@Autowired
	private ReservationService service;
	
	@RequestMapping("/addReservation")
	@Transactional
	public ModelAndView addReservation(String resDate, int resPeople, String resStartTime, String resPayStatement, String businessId, @RequestParam List<Integer> tableList) {
		
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
		System.out.println("reservationSuccess - businessId : " + businessId);	// log
		String restaurantName = service.selectRestaurantByBusinessId(businessId).getRtName();
		System.out.println("reservationSuccess - restaurantName : " + restaurantName); //log
		
		List<OrderTable> orderTableList = service.selectOrderTableByResNum(reservation.getResNum());
		// 검증
		for(OrderTable table : orderTableList) {
			System.out.println("reservationSuccessController- table: " + table);//log
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reservation/reservation_success.tiles");
		mav.addObject("reservation", reservation);
		mav.addObject("restaurantName", restaurantName);
		mav.addObject("orderTableList", orderTableList);

		return mav;
	}
	
	@RequestMapping("/tableSearchController")
	public ModelAndView tableSearchController(HttpServletRequest request, String resDate, String resTime, int resPeople, String restaurantName, String businessId) {
		
		List<Table> tableList = service.selectUsableTable(resDate, resTime, businessId);
		for(Table t : tableList) {
			System.out.println("ReservationController.tableSearchCtr - "+t);
		}
		
		List<Table> allTable = (List<Table>) request.getAttribute("allTable");
		for(Table tables : allTable) {
			System.out.println("ReservationController.tableSearchCtr - " + tables);
		}
		//log	
		System.out.println("tableSearchController - "+resDate);
		System.out.println("tableSearchController - " + businessId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reservation/reservation_form.tiles");
		mav.addObject("resDate", resDate);
		mav.addObject("resTime", resTime);
		mav.addObject("resPeople", resPeople);
		mav.addObject("allTable",allTable);
		mav.addObject("tableList", tableList);
		mav.addObject("restaurantName", restaurantName);
		mav.addObject("businessId", businessId);
		
		return mav;
	}

	
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
	
}
