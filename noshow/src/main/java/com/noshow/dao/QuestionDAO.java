package com.noshow.dao;

import java.util.List;

import com.noshow.vo.Answer;
import com.noshow.vo.Question;

public interface QuestionDAO {
	
	int insertQuestion(Question question);
	
	int updqteQuestion(Question question);
	
	int deleteQuestion(int questionNum);
	
	List<Question> selectQuestionByMemberId(String memberId);
	
	List<Question> selectQuestionByBusinessId(String businessId);
	
	/**
	 * 문의번호로 답변 조회해오기
	 * @param questioNum
	 * @return
	 */
	Answer selectAnswerByQuestionNum(int questionNum);
}
