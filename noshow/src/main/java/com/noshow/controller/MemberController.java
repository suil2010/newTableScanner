package com.noshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.MemberService;
import com.noshow.vo.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	@RequestMapping("/join_member")
	public ModelAndView inserMember(@ModelAttribute Member member) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		member.setMemberPassword(encoder.encode(member.getMemberPassword()));
		service.addMember(member, "ROLE_MEMBER");
		return new ModelAndView("redirect:/join_success.do", "memberId", member.getMemberId());
	}
	
	@RequestMapping("/join_success")
	public ModelAndView joinSuccess(@RequestParam String memberId) {
		Member member = service.getUserByMemberId(memberId);
		return new ModelAndView("member/join_success.tiles", "member", member);
	}
}
