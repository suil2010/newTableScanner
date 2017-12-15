package com.noshow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noshow.dao.QuestionDAO;
import com.noshow.service.QuestionService;
import com.noshow.vo.Answer;
import com.noshow.vo.Question;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Resource
	private QuestionDAO dao;

	@Override
	public int insertQusetion(Question question) {
		return dao.insertQuestion(question);
	}

	@Override
	public int updateQuestion(Question question) {
		return dao.updqteQuestion(question);
	}

	@Override
	public int deleteQuestion(int questionNum) {
		return dao.deleteQuestion(questionNum);
	}

	@Override
	public List<Question> selectQuestionByMemberId(String memberId) {
		List<Question> questionList = dao.selectQuestionByMemberId(memberId);
		questionList = selectAnswerByQuestionNum(questionList);
		return questionList;
	}

	@Override
	public List<Question> selectQuestionByBusinessId(String businessId) {
		List<Question> questionList = dao.selectQuestionByBusinessId(businessId);
		questionList = selectAnswerByQuestionNum(questionList);
		return questionList;
	}

	@Override
	public Question selectQuestionByQuestionNum(int questionNum) {
		return dao.selectQuestionByQuestionNum(questionNum);
	}
	
	private List<Question> selectAnswerByQuestionNum(List<Question> questionList) {
		for(Question q : questionList) {
			Answer answer = dao.selectAnswerByQuestionNum(q.getQuestionNum());
			q.setAnswer(answer);
		}
		return questionList;
	}
	
}
