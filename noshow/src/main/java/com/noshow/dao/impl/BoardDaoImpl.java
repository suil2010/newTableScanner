package com.noshow.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.BoardDao;
import com.noshow.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.noshow.config.mybatis.mapper.boardMapper." + id;
	}

	@Override
	public int insertBoard(Board board) {
		return session.insert(makeSqlId("insertBoard"), board);
	}

	@Override
	public int updateBoard(Board board) {
		return session.update(makeSqlId("updateBoard"), board);
	}

	@Override
	public int deleteBoard(Board boardNum) {
		return session.delete(makeSqlId("deleteBoard"), boardNum);
	}

	@Override
	public List<Board> boardList() {
		return session.selectList(makeSqlId("boardList"));
	}



}
