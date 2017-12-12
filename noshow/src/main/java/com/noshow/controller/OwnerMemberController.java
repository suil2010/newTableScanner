package com.noshow.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
// github.com/yoondongung/newTableScanner.git
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.OwnerMemberService;
import com.noshow.service.SearchService;
import com.noshow.vo.Member;
import com.noshow.vo.Restaurant;

@Controller
public class OwnerMemberController {
	
	@Autowired
	private OwnerMemberService service;
	
	@Autowired
	private SearchService SearchService;
	
	@RequestMapping("/selectCode")
	public ModelAndView selectRtCode() {
		Map<String, Object> map = service.selectcode();
		return new ModelAndView("member/regist_rt_form.tiles", "map", map);
		
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
		String businessId = ((Member) authentication.getPrincipal()).getMemberId();
		
		Restaurant rt = service.selectRestaurantByBusinessId(businessId);
		return new ModelAndView("owner/owner_Info.tiles", "rt", rt);
	}
	
	@RequestMapping("/find_rt_update")
	public ModelAndView findOwnerInfoById(HttpServletRequest request) {
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String businessId = ((Member) authentication.getPrincipal()).getMemberId();
		
		Restaurant rt = service.selectRestaurantByBusinessId(businessId);
		
		Map<String, Object> map = service.selectcode();
		map.put("rt", rt);
		
		return new ModelAndView("owner/owner_update_form.tiles", "map", map);
	}
	
	@RequestMapping("/update_rt")
	public ModelAndView updateRestaurant(Restaurant rt, HttpServletRequest request) throws IllegalStateException, IOException {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		rt.setBusinessId(((Member) authentication.getPrincipal()).getMemberId());
		
		MultipartFile rtImg = rt.getRtImg();
		if (rtImg != null && !rtImg.isEmpty()) {
			
			String pictureName = UUID.randomUUID().toString();
			String dir = request.getServletContext().getRealPath("/rtPicture");
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
		return new ModelAndView("admin/find_restaurant.tiles", "rt", rt);
	}
	/*
	 * @RequestMapping("/selectSales")
	 * public ModelAndView selectSales() throws Exception {
	 * SecurityContext context = SecurityContextHolder.getContext();
	 * Authentication authentication = context.getAuthentication();
	 * 
	 * // 현재 사용자 정보를 받아와서 member 객체 생성
	 * Member member = (Member) authentication.getPrincipal();
	 * String businessId = member.getMemberId();
	 * System.out.println("selectSales 호출");
	 * System.out.println(businessId);
	 * List<Map<Object, Object>> sales = service.selectSales(businessId);
	 * 
	 * System.out.println(sales);
	 * 
	 * String date = null;
	 * List<Map<String, Object>> list = new ArrayList<>();
	 * for (Map<Object, Object> sale : sales) {
	 * HashMap<String, Object> m = new HashMap<>();
	 * for (Object mapkey : sale.keySet()) {
	 * if (mapkey.equals("SUM(RES_PEOPLE)")) {
	 * m.put("people", sale.get(mapkey));
	 * } else if (mapkey.equals("RES_DATE")) {
	 * date = sale.get(mapkey).toString();
	 * date = date.substring(0, 10);
	 * m.put("period", date);
	 * }
	 * }
	 * list.add(m);
	 * 
	 * }
	 * //MAP의 KEY값을 이용하여 VALUE값 가져오기
	 * ObjectMapper mapper = new ObjectMapper();
	 * String str = mapper.writeValueAsString(list);
	 * return new ModelAndView("owner/restaurant_Sales.tiles", "sales", str);
	 * }
	 */
	
	@RequestMapping("/ownerRestaurantInfo")
	public ModelAndView ownerRestaurantInfo() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member) authentication.getPrincipal();
		String memberId = member.getMemberId();
		
		Date ownerDate2 = new Date();
		SimpleDateFormat formatType = new SimpleDateFormat("yyyy-MM-dd");
		String ownerDate = formatType.format(ownerDate2);
		Restaurant restaurant = SearchService.selectRestaurantByBusinessIdResInfo(ownerDate, "12:00", memberId, memberId);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("owner/reservation_join.tiles");
		mav.addObject("restaurant", restaurant);
		
		return mav;
	}
	
}
