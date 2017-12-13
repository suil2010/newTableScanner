package com.noshow.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.BoardDao;
import com.noshow.vo.Board;
import com.noshow.vo.Commen;

@Repository
public class BoardDaoImpl implements BoardDao{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.noshow.config.mybatis.mapper.boardMapper." + id;
	}
	
	private String CommenmakeSqlId(String id) {
		return "com.noshow.config.mybatis.mapper.commenMapper." + id;
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
	public int deleteBoard(int boardNum) {
		return session.delete(makeSqlId("deleteBoard"), boardNum);
	}

	@Override
	public List<Board> boardList() {
		return session.selectList(makeSqlId("boardList"));
	}

	@Override
	public Board selectBoardByNum(int boardNum) {
		return session.selectOne(makeSqlId("selectBoardByNum"), boardNum);
	}

	@Override
	public int updateBoardByNum(Board board) {
		return session.update(makeSqlId("updateBoard"), board);
	}

	@Override
	public int increaseBoardViews(int boardNum) {
		return session.update(makeSqlId("increaseBoardViews"), boardNum);
	}

	@Override
	public int insertCommen(Commen commen) {
		return session.insert(CommenmakeSqlId("insertCommen"), commen);
	}

	@Override
	public List<Commen> selectCommenByBoardNum(int boardNum) {
		return session.selectList(CommenmakeSqlId("selectCommenByBoardNum"), boardNum);
	}



}
