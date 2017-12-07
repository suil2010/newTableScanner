package com.noshow.dao;

import java.util.List;
import java.util.Map;

import com.noshow.vo.Bookmark;

public interface BookmarkDAO {
	
	/**
	 * 즐겨찾기 insert
	 * @param bookmark
	 * @return
	 */
	int insertBookmark(Bookmark bookmark);
	
	/**
	 * 회원,사업주ID 로 즐겨찾기 삭제
	 * @param map
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
	 * 사업주ID로 즐겨찾기 조회
	 * @param businessId
	 * @return
	 */
	int selectBookmarkByBusinessId(String businessId);
	
	/** 
	 * TEST : 회원&사업주 ID로 등록된 것이 있는지 조회 
	 * -> COUNT를 통해 있으면 1, 없으면 0 리턴될 듯?
	 * @param bookmark
	 * @return
	 */
	int selectCheckBookmark(Bookmark bookmark);
}
