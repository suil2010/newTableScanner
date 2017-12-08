package com.noshow.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.MenuService;
import com.noshow.vo.Member;
import com.noshow.vo.Menu;

@Controller
public class MenuController {
	
	@Autowired
	private MenuService service;
	
	/**
	 * 2017.12.08 윤동웅
	 * 메뉴 등록하기
	 * @param menu
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/join_menu")
	public ModelAndView RegisterMenu(Menu menu, HttpServletRequest request) throws IllegalStateException, IOException{
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
		return new ModelAndView("redirect:/menu_businessId.do");
		
	}
	
	/**
	 * 2017. 윤동웅
	 * 메뉴 조회!
	 * @param businessId
	 * @return
	 */
	@RequestMapping("/menu_businessId")
	public ModelAndView getMenubybusinessId(String businessId) {
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		businessId = ((Member)authentication.getPrincipal()).getMemberId();
		List<Menu> menu = service.getMenuBybusinessId(businessId);
		return new ModelAndView("owner/menu_businessId.tiles","menu", menu);
	}
	
	/**
	 * 2017.12.08 윤동웅
	 * 메뉴 삭제하기!
	 * @param menuNum
	 * @return
	 */
	@RequestMapping(value="/remove_menu", produces="application/String;charset=utf8")
	public @ResponseBody String removeMenu(int menuNum) {
		service.deleteMenu(menuNum);
		return "삭제가 완료되었습니다.";
	}
	
} 
