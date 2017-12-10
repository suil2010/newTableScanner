package com.noshow.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.MemberService;
import com.noshow.vo.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	// memberInfo 손대지마세요.
	@RequestMapping("/mypage/member_info")
	public String memberInfo(ModelMap model) {
		String url = null;
		Collection authorizes = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(Object auth : authorizes) {
			SimpleGrantedAuthority authority = (SimpleGrantedAuthority)auth;
			if(authority.getAuthority().equals("ROLE_MEMBER")) {
				model.addAttribute("tabMenu", "true");
				url = "tabmenu/mypage/member_info.tiles";
				break;
			}
		}
		if(url == null) url = "mypage/member_info.tiles";
		return url;
	}
	@RequestMapping("/mypage/update_member_form")
	public String updateMemberForm(ModelMap model) {
		String url = null;
		Collection authorizes = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(Object auth : authorizes) {
			SimpleGrantedAuthority authority = (SimpleGrantedAuthority)auth;
			if(authority.getAuthority().equals("ROLE_MEMBER")) {
				model.addAttribute("tabMenu", "true");
				url = "tabmenu/mypage/update_member_form.tiles";
				break;
			}
		}
		System.out.println(model.get("tabMenu"));
		if(url == null) url = "mypage/member_info.tiles";
		return url;
	}
	/**
	 * 2017.
	 * @param member
	 * @return
	 */
	@RequestMapping("/join_member")
	public ModelAndView inserMember(@ModelAttribute Member member) {
		service.addMember(member, "ROLE_MEMBER");
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
		model.addAttribute("tabMenu", "true"); 
		return "tabmenu/mypage/update_member_form.tiles";   
	}
	
	/**
	 * 윤동웅
	 * 회원 탈퇴를 처리하는 controller
	 * member테이블에서 사라지지않고 로그인할수 있는 권한을 업데이트 시킴
	 * @return
	 */
	@RequestMapping("/remove_Member")
	public String removeMember() {
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String memberId = ((Member)authentication.getPrincipal()).getMemberId();
		service.removeMember(memberId);
		context.setAuthentication(null); 
		return "redirect:/remove.do";
	}
	
	
	/**
	 * 2017.12.06 윤동웅
	 * ID와 Email로 임시비밀번호 메일로 전송
	 * @param memberId
	 * @param memberEmail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/find_password")
	public ModelAndView findByPassword(String memberId, String memberEmail) throws Exception {
		service.getFindByPassword(new Member(memberId, null, memberEmail));
		return new ModelAndView("/index.tiles");
	}
	
	/**
	 * 2017.12.08 윤동웅 ajax로 수정
	 * 
	 * 2017.12.07 윤동웅 
	 * Name와 Email로 ID찾기
	 * @param memberName
	 * @param memberEmail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/find_id", produces="application/String;charset=utf8")
	public @ResponseBody String findById(String memberName, String memberEmail) throws Exception {
		String memberId = service.getFindById(memberName, memberEmail);
		if(memberId == null) {
			return "Email, Name을 다시 확인하세요.";
		}
		return memberId;
		
	}
	
	/**
	 * 2017.12.10 윤동웅
	 * 관리자 권한 member 전체 조회
	 * @return
	 */
	@RequestMapping("/find_authority_admin")
	public ModelAndView findAuthorityAdminMember() {
		List<Member> member = service.selectMemberAuthorityAdmin(); 
		return new ModelAndView("admin/authority_admin.tiles","member",member);
	}
	
	/**
	 * 2017.12.10 윤동웅
	 * 일반 회원 권한 member 전체 조회
	 * @return
	 */
	@RequestMapping("/find_authority_member")
	public ModelAndView findAuthorityMember() {
		List<Member> member = service.selectMemberAuthorityMember();
		return new ModelAndView("admin/authority_member.tiles","member",member);
	}
	
	/**
	 * 2017.12.10 윤동웅
	 * 사업자 권한 member 전체 조회
	 * @return
	 */
	@RequestMapping("/find_authority_owner")
	public ModelAndView findAuthorityOwnerMember() {
		List<Member> member = service.selectMemberAuthorityOwner(); 
		return new ModelAndView("admin/authority_owner.tiles","member",member);
	}
	
	/**
	 * 탈퇴한 member 전체 조회
	 * @return
	 */
	@RequestMapping("/find_drop_member")
	public ModelAndView findDropMember() {
		List<Member> member = service.selectWithdrawMember();
		return new ModelAndView("admin/drop_member.tiles","member",member);
	}
}
