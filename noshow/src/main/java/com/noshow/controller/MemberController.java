package com.noshow.controller;

import java.io.IOException;
import java.util.List;

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

import com.noshow.service.MemberService;
import com.noshow.vo.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping("/join_member")
	public ModelAndView inserMember(@ModelAttribute Member member) {
/*		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		member.setMemberPassword(encoder.encode(member.getMemberPassword()));*/
		service.addMember(member, "ROLE_MEMBER");
		
		return new ModelAndView("/sendMail.do", "member", member);
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
}
