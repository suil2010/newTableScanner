package com.noshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.BoardService;
import com.noshow.vo.Board;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	//게시글 목록
	@RequestMapping("/boardList")
	public ModelAndView boardList(@RequestParam Board board) {
		List<Board> list = service.allList(board);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board_list.tiles");
		mav.addObject("list", list);
		return mav;
	}
	
	//게시글 등록처리
	@RequestMapping("/insertBoard")
	public String insertBoard(@ModelAttribute Board board)throws Exception {
		service.insertBoard(board);
		return "board_view";
	}
	
	/*//게시글 상세내용 조회, 조회수 count처리
	@RequestMapping("/viewBoard")
	public String viewBoard(@RequestParam Board boardViews, HttpSession session) throws Exception {
		service.getCountViews(boardViews);
		return "";
	}
	
	//게시글 수정
	@RequestMapping("/updateBoard")
	public String update(@ModelAttribute Board memberId) throws Exception{
		service.updateBoard(memberId);
		return "";
	}
	
	//게시글 삭제
	@RequestMapping("/deleteBoard")
	public String delete(@ModelAttribute Board memberId) throws Exception{
		service.deleteBoard(memberId);
		return "";
	}*/
}
