package com.noshow.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.QuestionService;
import com.noshow.vo.Member;
import com.noshow.vo.Question;

@Controller
public class QuestionController {
	
	@Autowired
	private QuestionService service;
	
	@RequestMapping("/registQuestion")
	@ResponseBody
	public List<Question> registQuestion(@ModelAttribute Question question) {
//		System.out.println("QuestionController.registQuestion - question : " + question);
		String businessId = question.getBusinessId();
		int result = service.insertQusetion(question);
		if (result == 0) {
			System.out.println("QuestionController - 문의글 insert 실패!!");
			return null;
		} else {
			System.out.println("QuestionController - 문의글 insert 성공 : "+ result);
			List<Question> questionList = service.selectQuestionByBusinessId(businessId);
			return questionList;
		}

	}
	
	@RequestMapping("/reSearchQuestion")
	@ResponseBody
	public List<Question> reSearchQuestion(Question question) {
		System.out.println("QuestionContoller.reSearchQuestion - review : " + question);
		String businessId = question.getBusinessId();
		List<Question> questionList = service.selectQuestionByBusinessId(businessId);
		return questionList;
	}
	
	
	/* 2017.12.13 - 현준 _ 내가 등록한 문의 Controller */
	@RequestMapping("/myQuestion")
	public ModelAndView myQuestion(ModelMap model) {
		// 현재 사용자 정보를 받아와서 member 객체 생성
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Member member = (Member)authentication.getPrincipal();
		
		String memberId = member.getMemberId();
		List<Question> questionList = service.selectQuestionByMemberId(memberId);
		Collection authorizes = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(Object auth : authorizes) {
			SimpleGrantedAuthority authority = (SimpleGrantedAuthority)auth;
			if(authority.getAuthority().equals("ROLE_MEMBER")) {
				model.addAttribute("tabMenu", "true");  
				break;
			}
		}
		return new ModelAndView("tabmenu/mypage/mypage_question.tiles", "questionList", questionList);
	}
}
