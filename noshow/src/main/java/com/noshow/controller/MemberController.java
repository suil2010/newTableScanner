package com.noshow.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.MailService;
import com.noshow.service.MemberService;
import com.noshow.vo.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping("/join_member")
	public ModelAndView inserMember(@ModelAttribute Member member) {
		service.addMember(member, "ROLE_MEMBER");
		
		String subject = "Table Scanner 회원가입을 축하합니다."; 
		String Text = member.getMemberName() +"("+ member.getMemberId()+")"+" 님의 회원가입을 진심으로 축하드립니다." ;
		
		mailService.sendMail(subject, Text, member);
		
		return new ModelAndView("/join_success.do", "memberId", member.getMemberId());
	}

	@RequestMapping("/join_success")
	public ModelAndView joinSuccess(String memberId) {
		Member member = service.getUserByMemberId(memberId);
		return new ModelAndView("member/join_success.tiles", "member", member);
	}
	
	@RequestMapping("/update_Member")
	public String updateMember(@ModelAttribute Member member, @RequestParam String oldMemberPassword, HttpServletRequest request, ModelMap model) throws IllegalStateException, IOException {
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		if(!passwordEncoder.matches(oldMemberPassword, ((Member)authentication.getPrincipal()).getMemberPassword())) {
			model.addAttribute("errorMessage", "패스워드를 확인하세요.");
			return "member/update_member_form.tiles";
		}
		service.updateMemberProfile(member);
		
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
		System.out.println(authorities);
		UsernamePasswordAuthenticationToken newAutentication = 
				new UsernamePasswordAuthenticationToken(member, null, authorities);

		context.setAuthentication(newAutentication);
		
		return "member/mypage.tiles";
	}
	
	@RequestMapping("/remove_Member")
	public String removeMember() {
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String memberId = ((Member)authentication.getPrincipal()).getMemberId();
		service.removeMember(memberId);
		context.setAuthentication(null); 
		return "redirect:/remove.do";
	}
	
	
	@RequestMapping("/find_password")
	public ModelAndView findByPassword(String memberId, String memberEmail) throws Exception {
		
		String newPassword = getRandomPassword(8);
		Member member = service.getFindByMemberId(new Member(memberId, null, memberEmail), newPassword);
	
		String subject = "Table Scanner 비밀번호 찾기"; 
		String Text = member.getMemberName() +"("+ member.getMemberId()+")"+" 님의 비밀번호는" + newPassword + "입니다." ;
		mailService.sendMail(subject, Text, member);

		return new ModelAndView("/index.tiles");
	}
	
	private String getRandomPassword(int length) {
		char[] charaters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
		StringBuilder sb = new StringBuilder("");
		Random rm = new Random();
		for(int i = 0; i < length; i++) {
			sb.append(charaters[rm.nextInt(charaters.length )]);
		}
		return sb.toString();
	}
}
