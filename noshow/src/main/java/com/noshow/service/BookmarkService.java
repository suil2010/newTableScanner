package com.noshow.service;

import java.util.List;

import com.noshow.vo.Bookmark;

public interface BookmarkService {
	
	/**
	 * 즐겨찾기 등록
	 * @param bookmark
	 * @return
	 */
	int addBookmark(Bookmark bookmark);
	
	/**
	 * 회원,사업주ID받아서 즐겨찾기 삭제
	 * @param memberId
	 * @param businessId
	 * @return
	 */
	int deleteBookmark(Bookmark bookmark);

	/**
	 * 회원ID로 즐겨찾기 조회
	 * @param memberId
	 * @return
	 */
	List<Bookmark> selectBookmarkBymemberId(String memberId);
	
	/**
	 * 사업주ID로 즐겨찾기 등록된 수 조회
	 * @param businessId
	 * @return
	 */
	int selectBookmarkByBusinessId(String businessId);
	
	/**
	 * TEST : 즐겨찾기 체크 위함
	 * @param bookmark
	 * @return
	 */
	int selectCheckBookmark(String memberId, String businessId);
}
