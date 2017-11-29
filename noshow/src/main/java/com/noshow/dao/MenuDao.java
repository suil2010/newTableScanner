package com.noshow.dao;

import java.util.List;

import com.noshow.vo.Classification;
import com.noshow.vo.Menu;

public interface MenuDao {

	/**
	 * 대분류 테이블에 매개변수로 받은 대분류 메뉴 insert하는 메소드
	 * @param classification
	 * @return 
	 */
	int insertClassification(Classification classification);
	
	/**
	 * 매개변수로 받은 classification의  classificationNum 의 나머지 정보 update 처리하는 메소드
	 * @param classification
	 * @return
	 */
	int updateClassification(Classification classification);
	
	/**
	 * 매개변수로 받은 classificationNum과 일치하는 classification 데이터를 삭제
	 * @param classificationNum
	 * @return
	 */
	int deleteClassification(int classificationNum);
	
	/**
	 * business id로 가지고 있는 대분류 조회
	 * @param businessId
	 * @return
	 */
	List<Classification> selectClassificationBybusinessId(String businessId);
	
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
	int deleteMenu(int MenuNum);
	
	/**
	 * business id로 가지고 있는 메류조회
	 * @param businessId
	 * @return
	 */
	List<Menu> selectMenuBybusinessId(String businessId);
}
