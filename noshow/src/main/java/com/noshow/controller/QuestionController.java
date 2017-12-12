package com.noshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.QuestionService;
import com.noshow.vo.Question;

@Controller
public class QuestionController {
	
	@Autowired
	private QuestionService service;
	
	@RequestMapping("/registQuestion")
	public ModelAndView registQuestion(@ModelAttribute Question question) {
		System.out.println("QuestionController.registQuestion - question : " + question);
		int result = service.insertQusetion(question);
		System.out.println("QuestionController.registQuestion - insertResult : "+ result);
		return new ModelAndView("/reSearchQuestion.do", "question", question);
	}
	
	@RequestMapping("/reSearchQuestion")
	@ResponseBody
	public List<Question> reSearchQuestion(Question question) {
		System.out.println("QuestionContoller.reSearchQuestion - review : " + question);
		String businessId = question.getBusinessId();
		List<Question> questionList = service.selectQuestionByBusinessId(businessId);
		//TEST
		for(Question q : questionList) {
			System.out.println("questionList : " + q);
		}
		return questionList;
	}
}
