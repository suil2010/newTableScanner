package com.noshow.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.ReviewDAO;
import com.noshow.vo.Review;

@Repository
public class ReviewDAOImpl implements ReviewDAO{
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id){
		return "com.noshow.config.mybatis.mapper.reviewMapper." + id;
	}

	@Override
	public int insertReview(Review review) {
		return session.insert(makeSqlId("insertReview"), review);
	}

	@Override
	public int updateReview(Review review) {
		return session.update(makeSqlId("updateReview"), review);
	}

	@Override
	public int deleteReview(int reviewNum) {
		return session.delete(makeSqlId("deleteReviewByReviewNum"), reviewNum);
	}

	@Override
	public List<Review> selectReviewByMemberId(String memberId) {
		return session.selectList(makeSqlId("selectReviewByMemberId"), memberId);
	}

	@Override
	public List<Review> selectReviewByBusinessId(String businessId) {
		return session.selectList(makeSqlId("selectReviewByBusinessId"), businessId);
	}

	@Override
	public List<Map<String, Object>> selectReviewForRank() {
		return session.selectList(makeSqlId("selectReviewForRank"));
	}

	@Override
	public int selectMaxResNum(Map<String, String> map) {
		return session.selectOne(makeSqlId("selectMaxResNum"), map);
	}
	
}
