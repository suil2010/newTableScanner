package com.noshow.service;

import java.util.List;

import com.noshow.vo.Board;
import com.noshow.vo.Commen;

public interface BoardService {
	
	/**
	 * 게시판 글 등록
	 * @param board
	 * @return
	 */
	int insertBoard(Board board);
	
	/**
	 * 게시판 글 수정
	 * @param memberId
	 * @return
	 */
	int updateBoard(Board memberId);
	
	/**
	 * memberId를 받아 글 삭제
	 * @param memeberId
	 * @return
	 */
	int deleteBoard(Board memeberId);
	
	/**
	 * memberId로 게시판 글 조회
	 * @param memberId
	 * @return
	 */
	List<Board> selectBoardByMemberId(String memberId);
	
	/**
	 * 제목으로 게시판 글 조회
	 * @param boardTitle
	 * @return
	 */
	List<Board> selectBoardByBoardTitle(String boardTitle);
	
	/**
	 * 게시판 리스트
	 * @param paramMap
	 * @return
	 */
	List<Board> allList(Board board);
	
	/**
	 * 글 내용
	 * @param paramMap
	 * @return
	 */
	Board getCountViews(Board boardViews);
	
	/**
	 * 조회수count
	 * @param paramMap
	 * @return
	 */
	int getBoardCount();
	
	/**
	 * 댓글
	 * @param paramMap
	 * @return
	 */
	int boardCommen(Commen commen);
	
	/**
	 * 
	 * @param paramMap
	 * @return
	 */
	List<Commen> getCommenList(Commen commen);


}
