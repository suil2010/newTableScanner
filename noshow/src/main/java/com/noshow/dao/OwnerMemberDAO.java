package com.noshow.dao;

import java.util.List;

import com.noshow.vo.Field;
import com.noshow.vo.Holiday;
import com.noshow.vo.Restaurant;
import com.noshow.vo.Term;

public interface OwnerMemberDAO {
	
	/**
	 * code table selectHolidatbyCode
	 * @return
	 */
	List<Holiday> selectHoliday();
	
	/**
	 * code table selectTermByCode
	 * @return
	 */
	List<Term> selectTerm();
	
	/**
	 * code table selectFieldByCode
	 * @return
	 */
	List<Field> selectField();
	
	/**
	 * insert Restaurant
	 * @param rt
	 * @return
	 */
	int insertRestaurant(Restaurant rt);
	
 	//Restaurant 조회
  	Restaurant selectRestaurantByBusinessId(String businessId);
	
}