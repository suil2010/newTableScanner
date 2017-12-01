package com.noshow.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.MenuService;
import com.noshow.vo.Member;
import com.noshow.vo.Menu;

@Controller
public class MenuController {
	
	@Autowired
	private MenuService service;
	
	@RequestMapping("/join_menu")
	public ModelAndView joinMenu(Menu menu, HttpServletRequest request) throws IllegalStateException, IOException{
		// 메뉴 파일 업로드
		MultipartFile menuImage = menu.getMenuImage();
		if(menuImage != null && !menuImage.isEmpty()) {
			//사진을 저장할 디렉토리
			String dir = request.getServletContext().getRealPath("/menuPicture");
			String pictureName = UUID.randomUUID().toString();
			File upMenuImage = new File(dir, pictureName);
			menuImage.transferTo(upMenuImage);
			menu.setMenuPicture(pictureName);
		}
		service.addMenu(menu);
		return new ModelAndView("redirect:/join_menu_success.do", "menuNum", menu.getMenuNum());
		
	}
	
	@RequestMapping("/join_menu_success")
	public ModelAndView joinMenuSuccess(int menuNum) {
		Menu menu = service.getMenuByMenuNum(menuNum);
		return new ModelAndView("owner/menu_success.tiles", "menu", menu);
	}	
	
	@RequestMapping("/menu_businessId")
	public ModelAndView getMenubybusinessId(String businessId) {
		List<Menu> menu = service.getMenuBybusinessId(businessId);
		System.out.println(menu);
		return new ModelAndView("owner/menu_businessId.tiles","menu", menu);
	}
} 
