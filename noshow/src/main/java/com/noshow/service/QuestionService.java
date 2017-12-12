package com.noshow.service;

import java.util.List;

import com.noshow.vo.Question;

public interface QuestionService {
	
	int insertQusetion(Question question);
	
	int updateQuestion(Question question);
	
	int deleteQuestion(int questionNum);
	
	List<Question> selectQuestionByMemberId(String memberId);
	
	List<Question> selectQuestionByBusinessId(String businessId);
	
/*	List<Question> selectAnswerByQuestionNum(List<Question> questionList);*/

}
