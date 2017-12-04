package com.noshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.MailService;
import com.noshow.vo.Member;

@Controller
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping("/sendMail")
	public ModelAndView sendMail(Member member) {
		System.out.println(member);
		System.out.println("요청옴");
		String subject = "회원가입을 축하합니다."; 
		String Text = member.getMemberName()+ "님의 회원가입을 진심으로 축하드립니다." + "하하";
		
		mailService.sendMail(subject, Text, member);
		
		return new ModelAndView("/join_success.do", "memberId", member.getMemberId());
	}
}
