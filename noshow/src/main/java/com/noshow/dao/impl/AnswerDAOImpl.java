package com.noshow.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.AnswerDAO;
import com.noshow.vo.Answer;

@Repository
public class AnswerDAOImpl implements AnswerDAO{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.noshow.config.mybatis.mapper.QnAMapper." + id;
	}

	@Override
	public int insertAnswer(Answer answer) {
		return session.insert(makeSqlId("insertAnswer"), answer);
	}

	@Override
	public int updateAnswer(Answer answer) {
		return session.update(makeSqlId("updqteAnswer"), answer);
	}

	@Override
	public int deleteAnswer(int answerNum) {
		return session.delete(makeSqlId("deleteAnswer"), answerNum);
	}

}
