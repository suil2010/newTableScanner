package com.noshow.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.BookmarkDAO;
import com.noshow.vo.Bookmark;

@Repository
public class BookmarkDAOImpl implements BookmarkDAO{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.noshow.config.mybatis.mapper.bookmarkMapper." + id;
	}
	
	@Override
	public int insertBookmark(Bookmark bookmark) {
		System.out.println("BookmarkDAOImpl.insertBookmark - bookmark : "+bookmark);
		return session.insert(makeSqlId("insertBookmark"), bookmark);
	}

	@Override
	public int deleteBookmark(Bookmark bookmark) {
		return session.delete(makeSqlId("deleteBookmark"), bookmark);
	}

	@Override
	public List<Bookmark> selectBookmarkBymemberId(String memberId) {
		return session.selectList(makeSqlId("selectBookmarkBymemberId"), memberId);
	}

	@Override
	public int selectBookmarkByBusinessId(String businessId) {
		return session.selectOne(makeSqlId("selectBookmarkByBusinessId"), businessId);
	}

	@Override
	public int selectCheckBookmark(Bookmark bookmark) {
		System.out.println("BookmarkDAO.selectCheckBookmark - bookmark : " +bookmark);
		return session.selectOne(makeSqlId("selectCheckBookmark") , bookmark);
	}

}
