package com.noshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.AnswerService;
import com.noshow.vo.Answer;

@Controller
public class AnswerController {

	@Autowired
	private AnswerService service;
	
	@RequestMapping("/registAnswer")
	@Transactional
	public ModelAndView registAnswer(@ModelAttribute Answer answer) {
		service.insertAnswer(answer);
		return new ModelAndView("redirect:/ownerMyQuestion.do");
	}
	
	@RequestMapping("/updateAnswer")
	@Transactional
	public ModelAndView updateAnswer(@ModelAttribute Answer answer) {
		service.updateAnswer(answer);
		return new ModelAndView("redirect:/ownerMyQuestion.do");
	}
	
	@RequestMapping("/deleteAnswer")
	@Transactional
	public ModelAndView deleteAnswer(int answerNum) {
		service.deleteAnswer(answerNum);
		return new ModelAndView("redirect:/ownerMyQuestion.do");
	}

}
