package com.noshow.service.impl;

import java.util.List;

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
	public int addBoard(Board board) {
		return dao.insertBoard(board);
	}

	@Override
	public void updateBoard(Board board) {
		dao.updateBoard(board);
	}

	@Override
	public void deleteBoard(int boardNum) {
		dao.deleteBoard(boardNum);
	}

	@Override
	public List<Board> boardList() {
		return dao.boardList();
	}

	@Override
	public Board boardListByNum(int boardNum) {
		return dao.selectBoardByNum(boardNum);
	}

	@Override
	public void increaseBoardViews(int boardNum) {
		dao.increaseBoardViews(boardNum);
	}

	@Override
	public void insertCommen(Commen commen) {
		dao.insertCommen(commen);
	}

	@Override
	public List<Commen> selectCommenByBoardNum(int boardNum) {
		return dao.selectCommenByBoardNum(boardNum);
	}


	
}
	
	
