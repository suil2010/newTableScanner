package com.noshow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.MailService;
import com.noshow.vo.Member;

@Controller
public class MailController {
	
	@Autowired
	private MailService mailService;
	
/*	@RequestMapping("/joinMemberSendMail")
	public ModelAndView joinMemberSendMail(Member member) {
		String subject = "Table Scanner 회원가입을 축하합니다."; 
		String Text = member.getMemberName() +"("+ member.getMemberId()+")"+" 님의 회원가입을 진심으로 축하드립니다." ;
		
		mailService.sendMail(subject, Text, member);
		
		return new ModelAndView("/join_success.do", "memberId", member.getMemberId());
	}
	*/
/*	@RequestMapping("/findByPasswordSendMail")
	public ModelAndView findByPasswordSendMail(HttpServletRequest request) {

		Member member = (Member)request.getAttribute("member");
		
		System.out.println(member);

		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		member.setMemberPassword(encoder.encode(member.getMemberPassword()));
		
		String pas = (encoder.encode(member.getMemberPassword()));
		System.out.println(pas);
		
		String subject = "Table Scanner 비밀번호 찾기"; 
		String Text = member.getMemberName() +"("+ member.getMemberId()+")"+" 님의 비밀번호는 /n" + member.getMemberPassword() + "입니다." ;
		
		mailService.sendMail(subject, Text, member);

		return new ModelAndView("/index.tiles");
	}*/
}
