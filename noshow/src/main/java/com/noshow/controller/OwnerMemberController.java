package com.noshow.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.OwnerMemberService;
import com.noshow.vo.Member;
import com.noshow.vo.Restaurant;

@Controller
public class OwnerMemberController {
	
	@Autowired
	private OwnerMemberService service;
	
	@RequestMapping("/selectCode")
	public ModelAndView selectRtCode() {
		Map<String, Object> map = service.selectcode();
		return new ModelAndView("member/regist_rt_form.tiles","map",map);
		
	}

	@RequestMapping("/join_rt")
	@Transactional
	public ModelAndView joinRestaurant(Restaurant rt, HttpServletRequest request) throws IllegalStateException, IOException {
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

	@RequestMapping("/find_rt_byid")
	public ModelAndView restaurantSuccess(HttpServletRequest request) {
		String memberId = (String) request.getAttribute("businessId");
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String businessId = ((Member)authentication.getPrincipal()).getMemberId();
		
		Restaurant rt = service.selectRestaurantByBusinessId(businessId);
		return new ModelAndView("owner/owner_Info.tiles", "rt", rt);
	}
	
	@RequestMapping("/find_rt_update")
		public ModelAndView findOwnerInfoById(HttpServletRequest request){
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String businessId = ((Member)authentication.getPrincipal()).getMemberId();
		
		Restaurant rt = service.selectRestaurantByBusinessId(businessId);
		
		Map<String, Object> map = service.selectcode();
		map.put("rt",rt);
		
		return new ModelAndView("owner/owner_update_form.tiles", "map", map);
	}
	
	@RequestMapping("/update_rt")
	public ModelAndView updateRestaurant(Restaurant rt, HttpServletRequest request)  throws IllegalStateException, IOException  {
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		rt.setBusinessId(((Member)authentication.getPrincipal()).getMemberId());
		 
		MultipartFile rtImg = rt.getRtImg();
		if (rtImg != null && !rtImg.isEmpty()) {
			String dir = request.getServletContext().getRealPath("/rtPicture");
			String pictureName = UUID.randomUUID().toString();
			File upRtImg = new File(dir, pictureName);
			rtImg.transferTo(upRtImg);
			rt.setRtPicture(pictureName);
		}
		
		service.updateRestaurant(rt);
		return new ModelAndView("redirect:/find_rt_byid.do");
	}
	
	@RequestMapping("/find_rt")
	public ModelAndView findAllRestaurant() {
		List<Restaurant> rt = service.selectAllRestaurant();
		return new ModelAndView("admin/find_restaurant.tiles","rt",rt);
	}
}
