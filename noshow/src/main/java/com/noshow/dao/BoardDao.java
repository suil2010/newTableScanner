package com.noshow.dao;

import java.util.List;

import com.noshow.vo.Board;

public interface BoardDao {
	
	/**
	 * 글 작성하기
	 * @param board
	 * @return
	 */
	int insertBoard(Board board);
	
	/**
	 * 글 수정하기
	 * @param board
	 * @return
	 */
	int updateBoard(Board board);
	
	/**
	 * 글 삭제하기
	 * @param memberId
	 * @return
	 */
	int deleteBoard(Board memberId);
	
	/**
	 * 글 전체 조회
	 * @param board
	 * @return
	 */
	List<Board>boardList();
	
	
/*	//memberId로 게시글 조회
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
	*/
}
