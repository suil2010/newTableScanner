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
		System.out.println(list);
		return new ModelAndView("common/board_list.tiles","list",list);
	}
	
	//게시글 등록처리
	@RequestMapping("/insertBoard")
	public ModelAndView insertBoard(Board board){
		System.out.println(board);
		service.addBoard(board);
		System.out.println(board.getBoardSubject());
		return new ModelAndView();
	}

}
