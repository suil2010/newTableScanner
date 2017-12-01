package com.noshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.OwnerMemberService;
import com.noshow.vo.Table;

@Controller
public class OwnerMemberController {
	
	
	@Autowired
	private OwnerMemberService service;
	

	@RequestMapping("/insertTable")
	public ModelAndView insertTable(@RequestParam String[] tableXY) {
		System.out.println("insertTable 시작");
		System.out.println("x : " + tableXY[0] + "y : " + tableXY[1] + "인원수 : " + tableXY[2]);
		String xLocation = tableXY[0];
		String yLocation = tableXY[1];
		int people = Integer.parseInt(tableXY[2]);
		Table table = new Table(3,1,people,xLocation,yLocation,"qwer");
		int i = service.insertTable(table);
		System.out.println(i); 
		return new ModelAndView("redirect:/index.do", "member", "aa");
	}
	@RequestMapping("/selectTable")
	public ModelAndView selectTable() {
		String id = "qwer";
		List<Table> tableList = service.selectTable(id);
		System.out.println(tableList);   
		return new ModelAndView("owner/ownerTable.tiles", "Table", tableList);
	}
}
