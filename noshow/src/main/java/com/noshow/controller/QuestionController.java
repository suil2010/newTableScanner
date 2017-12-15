package com.noshow.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
	@Transactional
	public ModelAndView registQuestion(@ModelAttribute Question question) {
		String businessId = question.getBusinessId();
		service.insertQusetion(question);
		return new ModelAndView("redirect:/reSearchQuestion.do", "businessId", businessId);
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
	
	/* 수정된 내용으로 문의글을 update 하는 Controller */
	@RequestMapping("/updateQuestion")
	@Transactional
	public ModelAndView updateQuestion(Question question) {
		service.updateQuestion(question);
		return new ModelAndView("redirect:/reSearchQuestion.do", "businessId", question.getBusinessId());
	}
	
	@RequestMapping("/deleteQuestion")
	@Transactional
	public ModelAndView deleteQuestion(int questionNum, String businessId) {
		service.deleteQuestion(questionNum);
		return new ModelAndView("redirect:/reSearchQuestion.do", "businessId", businessId);

	}
	
	/* Question, Answer에서 insert,update,delete 후 문의글목록 재조회하여 리턴 */
	@RequestMapping("/reSearchQuestion")
	@ResponseBody
	public List<Question> reSearchQuestion(String businessId) {
		List<Question> questionList = service.selectQuestionByBusinessId(businessId);
		return questionList;
	}
	
	@RequestMapping("/ownerMyQuestion")
	public ModelAndView ownerMyQuestion() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Member member = (Member)authentication.getPrincipal();
		String businessId = member.getMemberId();
		List<Question> questionList = service.selectQuestionByBusinessId(businessId);
		return new ModelAndView("owner/owner_question.tiles", "questionList",questionList);
	}
	
	@RequestMapping("/deleteMyQuestion")
	@Transactional
	public ModelAndView deleteMyQuestion(int questionNum) {
		service.deleteQuestion(questionNum);
		return new ModelAndView("redirect:/myQuestion.do");
	}
	
	@RequestMapping("/updateMyQuestion")
	@Transactional
	public ModelAndView updateMyQuestion(Question question) {
		service.updateQuestion(question);
		return new ModelAndView("redirect:/myQuestion.do");
	}
	
	
}
