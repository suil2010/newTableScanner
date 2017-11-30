package com.noshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.noshow.service.ReservationService;
import com.noshow.vo.Member;
import com.noshow.vo.Reservation;

@Controller
public class ReservationController {
	
	@Autowired
	private ReservationService service;
	
	@RequestMapping("addReservation")
	public String addReservation(int resNum, String resDate, int resPeople, String resStartTime, String resPayStatement, String businessId, int tableSeq) {
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Member member = (Member)authentication.getPrincipal();
		
		String memberId = member.getMemberId();
		
		int result = service.addReservation(resNum, resDate, resPeople, resStartTime, resPayStatement, memberId, businessId, tableSeq);
		if (result == 2) {
			System.out.println("예약 성공");
			return "/view/content/reservation/reservationSuccess.jsp";
		} else if (result == 1) {
			System.out.println("무언가 하나는 안들어감....?!");
			return "/index.jsp";
		}{
			System.out.println("예약 실패");
			return "/index.jsp";
		}
	}
	
}
