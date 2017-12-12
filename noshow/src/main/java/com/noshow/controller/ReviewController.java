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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.noshow.service.ReviewService;
import com.noshow.vo.Member;
import com.noshow.vo.Review;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewService service;

	/* 2017.12.11 - 현준 _리뷰등록 컨트롤러 */
	@RequestMapping("/registReview")
	@ResponseBody
	public List<Review> registerReview(String reviewText, @RequestBody @RequestParam MultipartFile reviewPicture, String reviewGrade, String businessId , HttpServletRequest request, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		System.out.println("ReviewController.registerReview - 멤버아이디도 넘어오는가? : "+ (String)request.getAttribute("memberId"));
		System.out.println("ReviewController.registerReview - 사업주아이디? : "+businessId);

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
			System.out.println("ReviewController - 리뷰 등록 실패!!");
//			return new ModelAndView("/researchReview.do", "review", review);
			return null;
		} else {
			System.out.println("ReviewController - 리뷰 등록 성공!!- review : "+review);
//			redirectAttributes.addAttribute("review", review);
			List<Review> reviewList = service.selectReviewByBusinessId(businessId);
			return reviewList;
		}
		
	}
	
	
	/* 2017.12.11 - 현준 _리뷰등록 후 재 조회 컨트롤러 */
	@RequestMapping("/researchReview")
	@ResponseBody
	public List<Review> researchReview(Review review) {
//		Review review = (Review) request.getAttribute("review");
		System.out.println("ReviewController.researchReview-review : "+ review);
		String businessId = review.getBusinessId();
		System.out.println("ReviewController.researchReview - businessId : "+businessId);
		List<Review> reviewList = service.selectReviewByBusinessId(businessId);
		
		return reviewList;
	}
}
