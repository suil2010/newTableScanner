package com.noshow.service;

import java.util.List;
import java.util.Map;

import com.noshow.vo.Review;

public interface ReviewService {
	
	int addReview(Review review);
	
	int updateReview(Review review);
	
	int deleteReview(int reviewNum);
	
	List<Review> selectReviewByMemberId(String memberId);
	
	List<Review> selectReviewByBusinessId(String businessId);
	
	List<Map<String, Object>> selectReviewForRank();
	
	int selectMaxResNum(String memberId, String businessId);
}
