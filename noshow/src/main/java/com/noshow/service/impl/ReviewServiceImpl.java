package com.noshow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.noshow.dao.ReviewDAO;
import com.noshow.service.ReviewService;
import com.noshow.vo.Review;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDAO dao;

	@Override
	public int addReview(Review review) {
		System.out.println("ReviewServiceImpl.addReview - review : " + review);
		int resNum = selectMaxResNum(review.getMemberId(), review.getBusinessId());
		review.setResNum(resNum);
		System.out.println("ReviewServiceImpl.addReview - resNum : " + resNum);
		int result = dao.insertReview(review);
		System.out.println("ReviewServiceImpl.addReview - insertResult : "+result);
		return result;
	}

	@Override
	public int updateReview(Review review) {
		return dao.updateReview(review);
	}

	@Override
	public int deleteReview(int reviewNum) {
		return dao.deleteReview(reviewNum);
	}

	@Override
	public List<Review> selectReviewByMemberId(String memberId) {
		return dao.selectReviewByMemberId(memberId);
	}

	@Override
	public List<Review> selectReviewByBusinessId(String businessId) {
		return dao.selectReviewByBusinessId(businessId);
	}

	@Override
	public List<Map<String, Object>> selectReviewForRank() {
		return dao.selectReviewForRank();
	}

	@Override
	public int selectMaxResNum(String memberId, String businessId) {
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("businessId", businessId);
		return dao.selectMaxResNum(map);
	}
	
	
	
	
}
