package com.noshow.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noshow.dao.ReviewDAO;
import com.noshow.service.ReviewService;
import com.noshow.vo.Review;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDAO dao;

	@Override
	public int addReview(Review review) {
		return dao.insertReview(review);
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
	
}
