package com.noshow.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.QuestionDAO;
import com.noshow.vo.Answer;
import com.noshow.vo.Question;

@Repository
public class QuestionDAOImpl implements QuestionDAO{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.noshow.config.mybatis.mapper.QnAMapper." + id;
	}

	@Override
	public int insertQuestion(Question question) {
		return session.insert(makeSqlId("insertQuestion"), question);
	}

	@Override
	public int updqteQuestion(Question question) {
		return session.update(makeSqlId("updqteQuestion"), question);
	}

	@Override
	public int deleteQuestion(int questionNum) {
		return session.delete(makeSqlId("deleteQuestion"), questionNum);
	}

	@Override
	public List<Question> selectQuestionByMemberId(String memberId) {
		return session.selectList(makeSqlId("selectQuestionByMemberId"), memberId);
	}

	@Override
	public List<Question> selectQuestionByBusinessId(String businessId) {
		return session.selectList(makeSqlId("selectQuestionByBusinessId"), businessId);
	}

	@Override
	public Answer selectAnswerByQuestionNum(int questionNum) {
		return session.selectOne(makeSqlId("selectAnswerByQuestionNum"), questionNum);
	}

	@Override
	public Question selectQuestionByQuestionNum(int questionNum) {
		return session.selectOne(makeSqlId("selectQuestionByQuestionNum"), questionNum);
	}

}
