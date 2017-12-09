package com.noshow.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noshow.dao.BoardDao;
import com.noshow.service.BoardService;
import com.noshow.vo.Board;
import com.noshow.vo.Commen;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;

	@Override
	public int insertBoard(Board board) {
		return dao.insertBoard(board);
	}

	@Override
	public int updateBoard(Board memberId) {
		return dao.updateBoard(memberId);
	}

	@Override
	public int deleteBoard(Board memberId) {
		return dao.deleteBoard(memberId);
	}

	@Override
	public List<Board> selectBoardByMemberId(String memberId) {
		return dao.selectBoardByMemberId(memberId);
	}

	@Override
	public List<Board> selectBoardByBoardTitle(String boardTitle) {
		return dao.selectBoardByBoardtitle(boardTitle);
	}

	@Override
	public List<Board> allList(Board board) {
		return dao.boardList(board);
	}

	@Override
	public Board getCountViews(Board boardViews) {
		return dao.getTextView(boardViews);
	}

	@Override
	public int getBoardCount() {
		return 0;
	}

	@Override
	public int boardCommen(Commen commen) {
		return dao.boardCommen(commen);
	}

	@Override
	public List<Commen> getCommenList(Commen commen) {
		return dao.getCommenList(commen);
	}

	
}
	
	
