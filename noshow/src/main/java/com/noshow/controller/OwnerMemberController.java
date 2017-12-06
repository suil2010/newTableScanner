package com.noshow.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noshow.service.OwnerMemberService;
import com.noshow.vo.Member;
import com.noshow.vo.Restaurant;
import com.noshow.vo.Table;

@Controller
public class OwnerMemberController {
	
	@Autowired
	private OwnerMemberService service;
	
	@RequestMapping("/join_rt")
	@Transactional
	public ModelAndView joinRt(Restaurant rt, HttpServletRequest request, BindingResult r) throws IllegalStateException, IOException {
		
		/*
		 * 2017.12.01 19.04 윤동웅 권한 업데이트후 바뀌어야하는데 안바뀜. 내일볼테니 주석 지우지 마셈..
		 * 주석없는 상태에서는 로그아웃한 후에 다시 로그인하면 권한 바뀌어서 실행됩니다
		 * 그거 번거로워서 로그아웃안하고 바꾸려고 설정하다가 상태가 너무 똥망이라 내일 수정함.
		 * SecurityContext context = SecurityContextHolder.getContext();
		 * Authentication authentication = context.getAuthentication();
		 */
		
		/* 2017.12.04 11:40 현준 Test */
		System.out.println("OwnerMemberController - rt : " + rt);
		System.out.println("rtOpen : " + rt.getRtOpen() + ", rtClose : " + rt.getRtClose());
		System.out.println("OwnerMemberController - r.count : " + r.getErrorCount());
		System.out.println("OwnerMemberController - r : " + r);
		// 식당이미지 업로드
		MultipartFile rtImg = rt.getRtImg();
		if (rtImg != null && !rtImg.isEmpty()) {
			// 이미지를 저장할 디렉토리
			String dir = request.getServletContext().getRealPath("/rtPicture");
			String pictureName = UUID.randomUUID().toString();
			File upRtImg = new File(dir, pictureName);
			rtImg.transferTo(upRtImg);
			rt.setRtPicture(pictureName);
		}
		service.insertRestaurant(rt, "ROLE_OWNER");
		
		/*
		 * List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
		 * System.out.println(authorities);
		 * UsernamePasswordAuthenticationToken newAutentication =
		 * new UsernamePasswordAuthenticationToken(rt, null, authorities);
		 * 
		 * context.setAuthentication(newAutentication);
		 */
		return new ModelAndView("redirect:/regist_success.do", "businessId", rt.getBusinessId());
	}
	
	@RequestMapping("/regist_success")
	public ModelAndView restaurantSuccess(HttpServletRequest request) {
		String memberId = (String) request.getAttribute("businessId");
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String businessId = ((Member)authentication.getPrincipal()).getMemberId();
		
		Restaurant rt = service.selectRestaurantByBusinessId(businessId);
		return new ModelAndView("owner/ownerInfo.tiles", "rt", rt);
	}
	
	@RequestMapping("/regist_update")
	public String updateRestaurant(@ModelAttribute Restaurant rt) {
		service.updateRestaurant(rt);
		return "regist_success.do";
	}
	public ModelAndView updateRestaurant(Restaurant restaurant, HttpServletRequest request) throws IllegalStateException, IOException {
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Restaurant rt = (Restaurant) authentication.getPrincipal();
		
		/*
		 * List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
		 * System.out.println(authorities);
		 * UsernamePasswordAuthenticationToken newAutentication =
		 * new UsernamePasswordAuthenticationToken((Restaurant)(authentication.getPrincipal()), null, authorities);
		 * 
		 * context.setAuthentication(newAutentication);
		 */
		
		service.updateRestaurant(rt);
		
		return new ModelAndView("owner/regist_success.tiles");

	}
	
	/*@RequestMapping("/regist_delete")
	public ModelAndView deleteRestaurant(@RequestParam String businessId) {
		service.deleteRestaurant(businessId);
		return new ModelAndView("redirect:/owner/delete_rt_success.tiles");
	}*/

	@RequestMapping("/all_restaurant")
	public ModelAndView selectAllRestaurant() {
		List<Restaurant> restaurantList = service.selectAllRestaurant();
		for (Restaurant r : restaurantList) {
			System.out.println(r);
		}
		return new ModelAndView("reservation/restaurant_list.tiles", "restaurantList", restaurantList);
	}
	
	@RequestMapping("/insertTable")
	@Transactional
	public ModelAndView insertTable(@RequestParam String[] tableXY) {
		// 수정이 필요함 3번에 한번씩 service를 호출하지않고 List로 전달하여 insert할수 있도록.
		System.out.println("Table - delete 시작");
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member) authentication.getPrincipal();
		String id = member.getMemberId();
		service.deleteTable(id);
		System.out.println("Table - delete 완료");
		System.out.println("Table - insert 시작");
		int n = 1;
		for (int i = 0; i < tableXY.length; i++) {
			if (i % 3 == 0) {
				
				String xLocation = tableXY[i];
				String yLocation = tableXY[i + 1];
				int people = Integer.parseInt(tableXY[i + 2]);
				Table table = new Table(3, n, people, xLocation, yLocation, id);
				service.insertTable(table);
				n++;
			}
		}
		
		System.out.println("Tabel - insert 성공");
		return new ModelAndView("redirect:/index.do");
	}
	
	@RequestMapping("/selectTable")
	public ModelAndView selectTable() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member) authentication.getPrincipal();
		String businessId = member.getMemberId();
		System.out.println("Table - select 시작");
		List<Table> tableList = service.selectTable(businessId);
		System.out.println("Table - select 성공");
		return new ModelAndView("owner/ownerTable.tiles", "Table", tableList);
	}
	
	public List<Table> selectTable(String businessId) {
		
		System.out.println("Table - select 시작");
		List<Table> tableList = service.selectTable(businessId);
		System.out.println("Table - select 성공");
		return tableList;
	}
	
	@RequestMapping("/restaurantList")
	public ModelAndView restaurantList(String businessId, int resPeople, String resDate, String resTime) {
		
		List<Table> allTable = selectTable(businessId);
		for (Table t : allTable) {
			System.out.println("restaurantList.allTable : " + t);
		}
		System.out.println("resListController - resTime : " + resTime);
		
		Restaurant restaurant = service.selectRestaurantByBusinessId(businessId);
		System.out.println("OwnerMemberController.restaurantList - restaurantName : " + restaurant);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/tableSearchController.do");
		mav.addObject("allTable", allTable);
		mav.addObject("resDate", resDate);
		mav.addObject("resTime", resTime);
		mav.addObject("resPeople", resPeople);
		mav.addObject("restaurant", restaurant);
		mav.addObject("businessId", businessId);
		
		return mav;
	}
	
	/* 이름검색으로 넘어오는 경우 식당상세 이동  처리 컨트롤러 */
	@RequestMapping("/restaurantListByName")
	public ModelAndView restaurantListByName(String businessId) {
		List<Table> allTable = selectTable(businessId);
		for (Table t : allTable) {
			System.out.println("restaurantListByName.allTable : " + t);
		}
		Restaurant restaurant = service.selectRestaurantByBusinessId(businessId);
		System.out.println("OwnerMemberController.restaurantListByName - restaurantName : " + restaurant);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reservation/reservation_form.tiles");
		mav.addObject("allTable", allTable);
		mav.addObject("restaurant", restaurant);
		mav.addObject("businessId", businessId);
		
		return mav;
	}
	
	/* 예약정보를 받아서 처리하는 controller */
	@RequestMapping("/searchRestaurant")
	public ModelAndView searchRestaurant(String resPlace, String resDate, String resTime, Integer resPeople, HttpSession session) {
		List<Restaurant> restaurantList = service.selectRestaurantBySearch(resPlace, resDate, resTime, resPeople);
		
		System.out.println("searchResController - resDate : " + resDate);
		System.out.println("searchResController - resTime : " + resTime);
		//검증
		if (restaurantList.isEmpty()) {
			System.out.println("해당 조건에 맞는 음식점이 없습니다.");
			return new ModelAndView("reservation/restaurant_list.tiles", "restaurantList", "null");
		} else {
			System.out.println("조건에 맞는 음식점 있다요~");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("reservation/restaurant_list.tiles");
			mav.addObject("restaurantList", restaurantList);
			mav.addObject("resPeople", resPeople);
			mav.addObject("resDate", resDate);
			mav.addObject("resTime", resTime);
			return mav;
		}
	}
	
	@RequestMapping("/selectSales")
	public ModelAndView selectSales() throws Exception{
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member) authentication.getPrincipal();
		String businessId = member.getMemberId();
		System.out.println("selectSales 호출");
		System.out.println(businessId);
		List<Map<Object, Object>> sales = service.selectSales(businessId);
		
		
		System.out.println(sales);
		
		String people = null;
		String date = null;
		int i = 0;
		List<Map<String, Object>> list = new ArrayList<>();
		for (Map<Object, Object> sale : sales) {
			HashMap<String, Object> m = new HashMap<>();
			for (Object mapkey : sale.keySet()) {
				if (mapkey.equals("SUM(RES_PEOPLE)")) {
					m.put("people",  sale.get(mapkey));
				} else if (mapkey.equals("RES_DATE")) {
					date = sale.get(mapkey).toString();
					date = date.substring(0,10); 
					m.put("period", date);
				}
			}
			list.add(m);
			
		}
		//MAP의 KEY값을 이용하여 VALUE값 가져오기
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(list);
		return new ModelAndView("owner/restaurant_Sales.tiles", "sales", str);
	}
	
	@RequestMapping("/searchRestaurantByName")
	public ModelAndView searchRestaurantByName(String resPlace, String resName) {
		System.out.println("OwnerMemberController.searchRestaurantByName - resPlace : "+resPlace );
		System.out.println("OwnerMemberController.searchRestaurantByName - resName : "+resName );
		List<Restaurant> restaurantList = service.selectRestaurantByNameSearch(resPlace, resName);
		if (restaurantList.isEmpty()) {
			System.out.println("해당하는 음식점이 없습니다.");
			return new ModelAndView("reservation/restaurant_list.tiles","notfountRestaurant", "검색 결과가 없습니다.");
		} else {
			System.out.println("검색조건에 맞는 음식점이 있다요오옹");
			return new ModelAndView("reservation/restaurant_list.tiles","restaurantList", restaurantList);
		}
		
	}
	
}
