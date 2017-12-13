package com.noshow.dao;

import com.noshow.vo.Answer;

public interface AnswerDAO {

	int insertAnswer(Answer answer);
	
	int updateAnswer(Answer answer);
	
	int deleteAnswer(Answer answer);
	
}
