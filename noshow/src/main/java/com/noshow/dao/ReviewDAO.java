package com.noshow.dao;

import java.util.List;
import java.util.Map;

import com.noshow.vo.Review;

public interface ReviewDAO {
	
	/**
	 * 리뷰 등록
	 * @param review
	 * @return
	 */
	int insertReview(Review review);
	
	/**
	 * 리뷰 수정
	 * @param review
	 * @return
	 */
	int updateReview(Review review);
	
	/**
	 * 리뷰 삭제
	 * @param review
	 * @return
	 */
	int deleteReview(int reviewNum);
	
	/**
	 * 회원 ID 로 리뷰 조회 
	 * @param memberId
	 * @return
	 */
	List<Review> selectReviewByMemberId(String memberId);
	
	/**
	 * 사업주 ID 로 리뷰 조회
	 * @param businessId
	 * @return
	 */
	List<Review> selectReviewByBusinessId(String businessId);
	
	/**
	 * TEST_ 등록된 리뷰들을 businessId 별로 평균 낸 것을 조회하여 리턴하는 메소드
	 * @return
	 */
	List<Map<String, Object>> selectReviewForRank();
}
