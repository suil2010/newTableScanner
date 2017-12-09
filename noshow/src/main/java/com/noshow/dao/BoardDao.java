package com.noshow.dao;

import java.util.List;

import com.noshow.vo.Board;
import com.noshow.vo.Commen;

public interface BoardDao {
	
	int insertBoard(Board board);
	
	int updateBoard(Board board);
	
	int deleteBoard(Board memberId);
	
	//memberId로 게시글 조회
	List<Board>selectBoardByMemberId(String memberId);
	
	//제목으로 게시글 조회
	List<Board>selectBoardByBoardtitle(String boardTitle);
	
	//게시판 전체 글 조회
	List<Board>boardList(Board board);
	
	//조회수 count
	int getViewCount(Board boardViews);
	
	//게시판글상세보기
	Board getTextView(Board boardText);
	
	//댓글
	int boardCommen(Commen commen);
	
	//전체댓글조회?
	List<Commen> getCommenList(Commen commen);
	
}
