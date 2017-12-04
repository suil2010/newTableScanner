package com.noshow.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.dao.AuthorityDao;
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

/* 2017.12.01 19.04 윤동웅 권한 업데이트후 바뀌어야하는데 안바뀜. 내일볼테니 주석 지우지 마셈.. 
 * 주석없는 상태에서는 로그아웃한 후에 다시 로그인하면 권한 바뀌어서 실행됩니다
 * 그거 번거로워서 로그아웃안하고 바꾸려고 설정하다가 상태가 너무 똥망이라 내일 수정함.
 * 		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
*/
		
		/* 2017.12.04 11:40 현준 Test*/
		System.out.println("OwnerMemberController - rt : "+rt);
		System.out.println("rtOpen : " + rt.getRtOpen() +", rtClose : " +rt.getRtClose());
		System.out.println("OwnerMemberController - r.count : "+r.getErrorCount());
		System.out.println("OwnerMemberController - r : "+r);
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
		
/*		List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
		System.out.println(authorities);
		UsernamePasswordAuthenticationToken newAutentication = 
				new UsernamePasswordAuthenticationToken(rt, null, authorities);

		context.setAuthentication(newAutentication);
		*/
		return new ModelAndView("redirect:/regist_success.do", "businessId", rt.getBusinessId());
	}

	@RequestMapping("/regist_success")
	public ModelAndView restaurantSuccess(@RequestParam String businessId) {
		Restaurant rt = service.selectRestaurantByBusinessId(businessId);
		return new ModelAndView("owner/regist_success.tiles", "rt", rt);
	}
	
	/*@RequestMapping("/regist_update")
	public ModelAndView updateRestaurant(@RequestParam String role) {
		
		
	}*/
	
	@RequestMapping("/all_restaurant")
	public ModelAndView selectAllRestaurant() {
		List<Restaurant> restaurantList = service.selectAllRestaurant();
		for(Restaurant r : restaurantList) {
			System.out.println(r);
		}
		return new ModelAndView("reservation/restaurant_list.tiles", "restaurantList", restaurantList);
	}
	
	

	@RequestMapping("/insertTable")
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
		for(int i = 0; i < tableXY.length; i++) {
			if(i % 3 == 0) {
	
				String xLocation = tableXY[i];
				String yLocation = tableXY[i+1];
				int people = Integer.parseInt(tableXY[i+2]);
				Table table = new Table(3,n,people,xLocation,yLocation,id);
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
}
