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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAnswer(Answer answer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAnswer(int answer_num) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
