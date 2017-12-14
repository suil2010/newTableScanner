package com.noshow.service;

import com.noshow.vo.Answer;

public interface AnswerService {

	int insertAnswer(Answer answer);
	
	int updateAnswer(Answer answer);
	
	int deleteAnswer(int answerNum);
}
