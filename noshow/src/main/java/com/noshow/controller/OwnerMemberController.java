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
		// 수정이 필요함 3번에 한번씩 service를 호출하지않고 List로 전달하여 insert할수 있도록.
		System.out.println("Table - delete 시작");
		String id = "qwer";
		service.deleteTable(id);
		System.out.println("Table - delete 완료");
		
		System.out.println("Table - insert 시작");
		System.out.println(tableXY.length);
		for(int i = 0; i < tableXY.length; i++) {
			if(i % 3 == 0) {
				String xLocation = tableXY[i];
				String yLocation = tableXY[i+1];
				int people = Integer.parseInt(tableXY[i+2]);
				Table table = new Table(3,1,people,xLocation,yLocation,"qwer");
				service.insertTable(table);
			}
		}
		
		System.out.println("Tabel - insert 성공");
		return new ModelAndView("redirect:/index.do");
	}
	@RequestMapping("/selectTable")
	public ModelAndView selectTable() {
		System.out.println("Table - select 시작");
		String id = "qwer";
		List<Table> tableList = service.selectTable(id);
		System.out.println(tableList);   
		System.out.println("Table - select 성공");
		return new ModelAndView("owner/ownerTable.tiles", "Table", tableList);
	}
}
