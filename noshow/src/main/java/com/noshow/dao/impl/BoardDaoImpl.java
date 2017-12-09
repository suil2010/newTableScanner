package com.noshow.dao.impl;

import java.util.List;
import java.util.Map;

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

	@Override
	public int insertBoard(Board board) {
		return session.insert(makeSqlId("insertBoard"), board);
	}

	@Override
	public int updateBoard(Board board) {
		return session.update(makeSqlId("updateBoard"), board);
	}

	@Override
	public int deleteBoard(Board memberId) {
		return session.delete(makeSqlId("deleteBoardByMemberId"), memberId);
	}

	@Override
	public List<Board> selectBoardByMemberId(String memberId) {
		return session.selectList(makeSqlId("selectBoardByMemberId"), memberId);
	}

	@Override
	public List<Board> selectBoardByBoardtitle(String boardTitle) {
		return session.selectList(makeSqlId("selectBoardByBoardTitle"), boardTitle);
	}

	@Override
	public List<Board> boardList(Board board) {
		return session.selectList(makeSqlId("boardList"), board);
	}

	@Override
	public int getViewCount(Board boardViews) {
		return 0;
	}

	@Override
	public Board getTextView(Board board) {
		return board;
	}

	@Override
	public int boardCommen(Commen commen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Commen> getCommenList(Commen commen) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
