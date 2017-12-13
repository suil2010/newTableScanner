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
	int addBoard(Board board);
	
	/**
	 * 게시판 글 수정
	 * @param memberId
	 * @return
	 */
	void updateBoard(Board board);
	
	/**
	 * boardNum를 받아 글 삭제
	 * @param boardNum
	 * @return
	 */
	void deleteBoard(int boardNum);
	
	/**
	 * 게시판 전체 조회
	 * @return
	 */
	List<Board> boardList();
	
	/**
	 * 글번호로 글 정보 조회
	 * @param boardNum
	 * @return
	 */
	Board boardListByNum(int boardNum);
	
	/**
	 * 조회수 증가
	 * @param boardNum
	 */
	void increaseBoardViews(int boardNum);
	
	/**
	 * 댓글 추가
	 * @param commen
	 */
	void insertCommen(Commen commen);
	
	/**
	 * 댓글 조회
	 * @param boardNum
	 * @return
	 */
	List<Commen> selectCommenByBoardNum(int boardNum);
}
