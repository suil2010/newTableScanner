package com.noshow.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.OwnerMemberService;
import com.noshow.vo.Restaurant;

@Controller
public class OwnerMemberController {
	
	@Autowired
	private OwnerMemberService service;
	
	@RequestMapping("selectCode")
	public ModelAndView selectRtCode() {
		Map<String, Object> map = service.selectcode();
		return new ModelAndView("member/regist_rt_form.tiles","map",map);
	}

	@RequestMapping("/join_rt")
	@Transactional
	public ModelAndView joinRestaurant(Restaurant rt, HttpServletRequest request, BindingResult r) throws IllegalStateException, IOException {
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
	
		MultipartFile rtImg = rt.getRtImg();
		if (rtImg != null && !rtImg.isEmpty()) {
			String dir = request.getServletContext().getRealPath("/rtPicture");
			String pictureName = UUID.randomUUID().toString();
			File upRtImg = new File(dir, pictureName);
			rtImg.transferTo(upRtImg);
			rt.setRtPicture(pictureName);
		}
		
		service.insertRestaurant(rt, "ROLE_OWNER");
		context.setAuthentication(null);

		return new ModelAndView("redirect:/login_form.do");
	}
}
