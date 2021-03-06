package com.noshow.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noshow.dao.AnswerDAO;
import com.noshow.service.AnswerService;
import com.noshow.vo.Answer;

@Service
public class AnswerServiceImpl implements AnswerService{

	@Resource
	private AnswerDAO dao;
	
	@Override
	public int insertAnswer(Answer answer) {
		return dao.insertAnswer(answer);
	}

	@Override
	public int updateAnswer(Answer answer) {
		return dao.updateAnswer(answer);
	}

	@Override
	public int deleteAnswer(int answerNum) {
		return dao.deleteAnswer(answerNum);
	}

	
}
