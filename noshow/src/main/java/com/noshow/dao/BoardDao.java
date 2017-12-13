package com.noshow.dao;

import java.util.List;

import com.noshow.vo.Board;
import com.noshow.vo.Commen;
import com.noshow.vo.Member;

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
	int deleteBoard(int boardNum);
	
	/**
	 * 글 전체 조회
	 * @param board
	 * @return
	 */
	List<Board>boardList();
	
	/**
	 * 글 번호로 글 상세 조회
	 * @return
	 */
	Board selectBoardByNum(int boardNum);
	
	/**
	 * 글 수정하기
	 * @param board
	 * @return
	 */
	int updateBoardByNum(Board board);
	
	/**
	 * 조회수 증가
	 * @param boardNum
	 * @return
	 */
	int increaseBoardViews(int boardNum);
	
	/**
	 * 댓글 추가
	 * @param commen
	 * @return
	 */
	int insertCommen(Commen commen);
	
	/**
	 * 댓글 조회
	 * @param boardNum
	 * @return
	 */
	List<Commen> selectCommenByBoardNum(int boardNum);
}
