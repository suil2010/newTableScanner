package com.noshow.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noshow.dao.BookmarkDAO;
import com.noshow.service.BookmarkService;
import com.noshow.vo.Bookmark;
import com.noshow.vo.Restaurant;

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
		return timeFormatting(dao.selectBookmarkBymemberId(memberId));
	}

	@Override
	public int selectBookmarkByBusinessId(String businessId) {
		return dao.selectBookmarkByBusinessId(businessId);
	}

	@Override
	public int selectCheckBookmark(String memberId, String businessId) {
		Bookmark bookmark = new Bookmark(memberId, businessId);
		return dao.selectCheckBookmark(bookmark);
	}
	
	/* 시간 변환 */
	public List<Bookmark> timeFormatting(List<Bookmark> bookmarkList) {
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat afterFormat = new SimpleDateFormat("HH:mm");
		Date resOpen_Date, resClose_Date;
		try {
			for(Bookmark bm : bookmarkList) {
				
				resOpen_Date = beforeFormat.parse(bm.getRestaurant().getRtOpen());
				resClose_Date = beforeFormat.parse(bm.getRestaurant().getRtClose());
				bm.getRestaurant().setRtOpen(afterFormat.format(resOpen_Date));
				bm.getRestaurant().setRtClose(afterFormat.format(resClose_Date));
			}
			return bookmarkList;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bookmarkList;
	}

}
