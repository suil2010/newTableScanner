package com.noshow.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	
	/**
	 * 2017.12.08 윤동웅
	 * 음식점 정보를 등록하고 음식점 점주 환경으로 변환하기 위해 재 로그인
	 * @param rt
	 * @param request
	 * @param r
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
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
	
	@RequestMapping("/find_rt_update")
	public ModelAndView findRestaurantByBusinessIdUpdate() {
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String businessId = ((Member)authentication.getPrincipal()).getMemberId();
		
		Restaurant restaurant = service.selectRestaurantByBusinessId(businessId);
		
		return new ModelAndView("owner/owner_update_form.tiles","rt",restaurant);
	}
	
	/**
	 * 2017.12.08 윤동웅
	 * 음식점 정보 수정
	 * @param rt
	 * @return
	 */
	@RequestMapping("/update_rt")
	public String updateRestaurant(@ModelAttribute Restaurant rt) {
		System.out.println(rt);
		service.updateRestaurant(rt);
		return "find_rt_byid.do";
	}
	
	/**
	 * 2017.12.08 윤동웅
	 * 현재 접속된 사용자의 음식점 정보 보기!
	 * @return
	 */
	@RequestMapping("/find_rt_byid")
	public ModelAndView findRestaurantByBusinessId() {
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String businessId = ((Member)authentication.getPrincipal()).getMemberId();
		
		Restaurant restaurant = service.selectRestaurantByBusinessId(businessId);
		
		return new ModelAndView("owner/owner_Info.tiles","rt",restaurant);
	}
	

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
	
}
