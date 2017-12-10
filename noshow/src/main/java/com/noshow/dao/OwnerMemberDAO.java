package com.noshow.dao;

import java.util.List;
import java.util.Map;

import com.noshow.vo.Restaurant;
import com.noshow.vo.RtCode;
import com.noshow.vo.Table;

public interface OwnerMemberDAO {
	
	/**
	 * code table selectHolidatbyCode
	 * @return
	 */
	List<RtCode> selectHolidayByCode();
	
	/**
	 * code table selectTermByCode
	 * @return
	 */
	List<RtCode> selectTermByCode();
	
	/**
	 * code table selectFieldByCode
	 * @return
	 */
	List<RtCode> selectFieldByCode();
	
	/**
	 * insert Restaurant
	 * @param rt
	 * @return
	 */
	int insertRestaurant(Restaurant rt);
	
}