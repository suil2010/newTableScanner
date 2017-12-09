package com.noshow.dao;

import java.util.List;

import com.noshow.vo.Menu;

public interface MenuDao {


	
	// 여기서 부터 menu 관련
	
	/**
	 * 메뉴 추가하기
	 * @param menu
	 * @return
	 */
	int insertMenu(Menu menu);
	
	/**
	 * 메뉴 업데이트
	 * @param menu
	 * @return
	 */
	int updateMenu(Menu menu);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																				
	
	/**
	 * 메뉴 삭제
	 * @param menu
	 * @return
	 */
	int deleteMenu(int menuNum);
	
	/**
	 * business id로 가지고 있는 메뉴조회
	 * @param businessId
	 * @return
	 */
	List<Menu> selectMenuBybusinessId(String businessId);
	
	/**
	 * MenuNum으로 menu정보 가져오기
	 * @param menuNum
	 * @return
	 */
	Menu selectMenuByMenuNum(int menuNum);
}
