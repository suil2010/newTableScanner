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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.noshow.service.OwnerMemberService;
import com.noshow.vo.Member;
import com.noshow.vo.Restaurant;
import com.noshow.vo.Table;

@Controller
public class OwnerMemberController {
	
	
	@Autowired
	private OwnerMemberService service;
	
	@RequestMapping("/join_rt")
	   public ModelAndView joinRt(Restaurant rt, HttpServletRequest request) throws IllegalStateException, IOException {
	      //식당이미지 업로드
	      MultipartFile rtImage = rt.getRtImg();
	      if(rtImage != null && !rtImage.isEmpty()){
	         //이미지를 저장할 디렉토리
	         String dir = request.getServletContext().getRealPath("/rtPicture");
	         String pictureName = UUID.randomUUID().toString();
	         File upRtImg = new File(dir, pictureName);
	         rtImage.transferTo(upRtImg);
	         rt.setRtPicture(pictureName);
	      }
	      System.out.println("이지수");
	      service.insertRestaurant(rt, "ROLE_OWNER");
	      return new ModelAndView("member/mypage.tiles", "businessId", rt.getBusinessId());
/*	      return new ModelAndView("redirect:/regist_success.do", "businessId", rt.getBusinessId());*/
	   }
	   
	/*   
	   @RequestMapping("/regist_success")
	   public ModelAndView updateRt(@ModelAttribute Restaurant rt) {
	      System.out.println("aaa");
	      service.updateRestaurant(rt, "ROLE_OWNER");
	      return new ModelAndView("redirect:/regist_success.tiles", "businessId", rt.getBusinessId());
	   }*/

	@RequestMapping("/insertTable")
	public ModelAndView insertTable(@RequestParam String[] tableXY) {
		// 수정이 필요함 3번에 한번씩 service를 호출하지않고 List로 전달하여 insert할수 있도록.
		System.out.println("Table - delete 시작");
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		// 현재 사용자 정보를 받아와서 member 객체 생성
		Member member = (Member)authentication.getPrincipal();
		String id = member.getMemberId();
		service.deleteTable(id);
		System.out.println("Table - delete 완료");
		
		System.out.println("Table - insert 시작");
		System.out.println(tableXY.length);
		for(int i = 0; i < tableXY.length; i++) {
			if(i % 3 == 0) {
				String xLocation = tableXY[i];
				String yLocation = tableXY[i+1];
				int people = Integer.parseInt(tableXY[i+2]);
				Table table = new Table(3,1,people,xLocation,yLocation,id);
				service.insertTable(table);
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
		Member member = (Member)authentication.getPrincipal();
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
