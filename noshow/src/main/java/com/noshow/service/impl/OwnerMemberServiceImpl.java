package com.noshow.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noshow.dao.AuthorityDao;
import com.noshow.dao.OwnerMemberDAO;
import com.noshow.service.OwnerMemberService;
import com.noshow.vo.Authority;
import com.noshow.vo.Restaurant;
import com.noshow.vo.Table;

@Service
public class OwnerMemberServiceImpl implements OwnerMemberService{
  
   @Resource
   private OwnerMemberDAO dao;
   
   @Resource
   private AuthorityDao authoritydao;
   
	@Override
	@Transactional
	public void insertRestaurant(Restaurant rt, String role) {
		dao.insertRestaurant(rt);
		authoritydao.updateAuthority(new Authority(rt.getBusinessId(), role));
	}
	
	@Override
	public int updateRestaurant(Restaurant rt, String role) {
		return dao.updateRestaurant(rt);
	}
	
	@Override
	public int deleteRestaurant(String businessId) {
		return dao.deleteRestaurant(businessId);
	}
	
	@Override
	public Restaurant selectRestaurantByBusinessId(String businessId) {
		return dao.selectRestaurantByBusinessId(businessId);
	}
	
	@Override
	public List<Restaurant> selectAllRestaurant() {
		return dao.selectAllRestaurant();
	}
	
	@Override
	public int selectRestaurantByRtName(String rtName) {
		return dao.selectRestaurantByRtName(rtName);
	}

	@Override
	public int insertTable(Table table) {
		return dao.insertTable(table);
	}

	@Override
	public List<Table> selectTable(String id) {
		return dao.selectTable(id);
	}

	@Override
	public int deleteTable(String id) {
		return dao.deleteTable(id);
	}

	@Override
	public List<Restaurant> selectRestaurantBySearch(String resPlace, String resDate, String resTime, int resPeople) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			/* 사용자의 검색조건에서 날짜를 요일로 바꾸는 부분 */
			Date formResDate = dateFormat.parse(resDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(formResDate);
			
			int rtHoliday = cal.get(Calendar.DAY_OF_WEEK);
			Map<String, Object> searchInfo = new HashMap<>();
			searchInfo.put("resPlace", resPlace);
			searchInfo.put("rtHoliday", rtHoliday);
			searchInfo.put("resTime", resTime);
			searchInfo.put("resPeople", resPeople);
			return dao.selectRestaurantBySearch(searchInfo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
		
	}

}









