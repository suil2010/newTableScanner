package com.noshow.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.noshow.service.ReviewService;
import com.noshow.vo.Member;
import com.noshow.vo.Question;
import com.noshow.vo.Review;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewService service;

	/* 2017.12.11 - 현준 _리뷰등록 컨트롤러 */
	@RequestMapping("/registReview")
	@ResponseBody
	public List<Review> registerReview(String reviewText, @RequestBody @RequestParam MultipartFile reviewPicture, String reviewGrade, String businessId , HttpServletRequest request, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {

		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		float reviewGradeFloat = Float.parseFloat(reviewGrade);

		Member member = (Member) authentication.getPrincipal();
		String memberId = member.getMemberId();
		Review review = new Review(reviewText, reviewPicture, reviewGradeFloat, memberId, businessId);
		
		MultipartFile reviewImage = review.getReviewPicture();
		if (reviewImage != null && !reviewImage.isEmpty()) {
			String dir = request.getServletContext().getRealPath("/reviewPicture");
			String reviewImg = UUID.randomUUID().toString();
			File upReviewImg = new File(dir, reviewImg);
			reviewImage.transferTo(upReviewImg);
			review.setReviewImg(reviewImg);
		}
		int result = service.addReview(review);
		if (result == 0) {
			return null;
		} else {
			List<Review> reviewList = service.selectReviewByBusinessId(businessId);
			return reviewList;
		}
		
	}
	
	/* 2017.12.13 - 현준 _ 내가 등록한 리뷰 Controller */
	@RequestMapping("/myReview")
	public ModelAndView myReview(ModelMap model) {
		// 현재 사용자 정보를 받아와서 member 객체 생성
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Member member = (Member)authentication.getPrincipal();
		String memberId = member.getMemberId();
		List<Review> reviewList = service.selectReviewByMemberId(memberId);
		Collection authorizes = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(Object auth : authorizes) {
			SimpleGrantedAuthority authority = (SimpleGrantedAuthority)auth;
			if(authority.getAuthority().equals("ROLE_MEMBER")) {
				model.addAttribute("tabMenu", "true");  
				break;
			}
		}
		return new ModelAndView("tabmenu/mypage/mypage_review.tiles", "reviewList", reviewList);
	}
	
	/* 2017.12.15 현준_사업주 리뷰조회 */
	@RequestMapping("/ownerMyReview")
	public ModelAndView ownerMyReview() {
		// 현재 사용자 정보를 받아와서 member 객체 생성
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Member member = (Member)authentication.getPrincipal();
		String businessId = member.getMemberId();
		List<Review> reviewList = service.selectReviewByBusinessId(businessId);
		return new ModelAndView("owner/owner_review.tiles", "reviewList", reviewList);
	}
	
}
