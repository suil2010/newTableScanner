package com.noshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.BoardService;
import com.noshow.vo.Board;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	
	@RequestMapping("/board_list")
	public ModelAndView boardList() {
		List<Board> list = service.boardList();
		return new ModelAndView("common/board_list.tiles","list",list);
	}
	
	//게시글 등록처리
	@RequestMapping("/insertBoard")
	public ModelAndView insertBoard(Board board){
		service.addBoard(board);
		return new ModelAndView("redirect:/readBoardByNum.do", "boardNum", board.getBoardNum());
	}
	
	//게시글 상세페이지
	@RequestMapping("/readBoardByNum")
	public ModelAndView readBoardByBoardNum(int boardNum) {
		service.increaseBoardViews(boardNum);
		Board board = service.boardListByNum(boardNum);
		return new ModelAndView("common/board_read.tiles", "board", board);
	}
	
	//게시글 수정 폼
	@RequestMapping("/updateBoard_form")
	public ModelAndView updateBoardForm(int boardNum) {
		Board board = service.boardListByNum(boardNum);
		return new ModelAndView("common/updateBoard_form.tiles", "board", board);
	}
	
	//게시글 수정
	@RequestMapping("/updateBoard")
	public ModelAndView updateBoard(Board board) {
		service.updateBoard(board);
		return new ModelAndView("redirect:/readBoardByNum.do", "boardNum", board.getBoardNum());
	}
	
	//글 삭제처리
	@RequestMapping("/deleteBoardByNum")
	public ModelAndView deleteBoardByBoardNum(int boardNum) {
		service.deleteBoard(boardNum);
		return new ModelAndView("redirect:/board_list.do");
	}
	
	

}
