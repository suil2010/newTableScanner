package com.noshow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noshow.dao.BookmarkDAO;
import com.noshow.service.BookmarkService;
import com.noshow.vo.Bookmark;

@Service
public class BookmarkServiceImpl implements BookmarkService{

	@Autowired
	private BookmarkDAO dao;
	
	@Override
	public int addBookmark(Bookmark bookmark) {
		return dao.insertBookmark(bookmark);
	}

	@Override
	public int deleteBookmark(Bookmark bookmark) {
		return dao.deleteBookmark(bookmark);
	}

	@Override
	public List<Bookmark> selectBookmarkBymemberId(String memberId) {
		return dao.selectBookmarkBymemberId(memberId);
	}

	@Override
	public int selectBookmarkByBusinessId(String businessId) {
		return dao.selectBookmarkByBusinessId(businessId);
	}

	@Override
	public int selectCheckBookmark(String memberId, String businessId) {
		System.out.println("bookmarkServiceImpl - memberId : "+memberId+", businessId : "+businessId);
		Bookmark bookmark = new Bookmark(memberId, businessId);
		return dao.selectCheckBookmark(bookmark);
	}

}
