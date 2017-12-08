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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReview(Review review) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReview(int reviewNum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Review> selectReviewByMemberId(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> selectReviewByBusinessId(String businessId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectReviewForRank() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
