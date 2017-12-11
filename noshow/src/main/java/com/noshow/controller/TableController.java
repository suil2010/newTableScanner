package com.noshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.TableService;
import com.noshow.vo.Member;
import com.noshow.vo.Table;

@Controller
public class TableController {
	
	@Autowired
	private TableService service;
	
	@RequestMapping("/insertTable")
	@Transactional
	public ModelAndView insertTable(@RequestParam String[] tableXY) {
		// 수정이 필요함 3번에 한번씩 service를 호출하지않고 List로 전달하여 insert할수 있도록.
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member) authentication.getPrincipal();
		String id = member.getMemberId();
		service.deleteTable(id);
		int n = 1;
		for (int i = 0; i < tableXY.length; i++) {
			if (i % 3 == 0) {
				
				String xLocation = tableXY[i];
				String yLocation = tableXY[i + 1];
				int people = Integer.parseInt(tableXY[i + 2]);
				Table table = new Table(3, n, people, xLocation, yLocation, id);
				System.out.println(table); 
				service.insertTable(table);
				n++;
			}
		}
		
		return new ModelAndView("redirect:/index.do");
	}

	@RequestMapping("/selectTable")
	public ModelAndView selectTable() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member) authentication.getPrincipal();
		String businessId = member.getMemberId();
		List<Table> tableList = service.selectTable(businessId);
		System.out.println(tableList);
		return new ModelAndView("owner/ownerTable.tiles", "Table", tableList);
	}
}
