package com.noshow.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
public class OwnerMemberServiceImpl implements OwnerMemberService {
	
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
	public int updateRestaurant(Restaurant rt) {
		return dao.updateRestaurant(rt);
	}

	
	@Override
	public int deleteRestaurant(String businessId) {
		return dao.deleteRestaurant(businessId);
	}
	
	@Override
	public Restaurant selectRestaurantByBusinessId(String businessId) {
		Restaurant restaurant = dao.selectRestaurantByBusinessId(businessId);
		List<Restaurant> list = new ArrayList<>();
		list.add(restaurant);
		list = timeFormatting(list);
		for(Restaurant r : list) {
			return r;
		}
		return restaurant;
		
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
	
			int rtHoliday = dayFormatting(resDate);
			
			Map<String, Object> searchInfo = new HashMap<>();
			searchInfo.put("resPlace", resPlace);
			searchInfo.put("rtHoliday", rtHoliday);
			searchInfo.put("resTime", resTime);
			searchInfo.put("resPeople", resPeople);
			List<Restaurant> restaurantList = setFieldMethod(dao.selectRestaurantBySearch(searchInfo));
			restaurantList = setHoliDayMethod(restaurantList);
			return timeFormatting(restaurantList);
		
	}

	
	/* 사용자가 검색시 입력한 날짜를 요일로 바꿔주는 메소드 분리 */
	public int dayFormatting(String resDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date formResDate;
		try {
			formResDate = dateFormat.parse(resDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(formResDate);
		
			return cal.get(Calendar.DAY_OF_WEEK);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}
	
	
	/* 검색 결과 - 음식점리스트에 int로 들어오는 Field 값을 변환 */
	public List<Restaurant> setFieldMethod(List<Restaurant> restaurant) {
		for(Restaurant res : restaurant) {
			switch (res.getRtField()) {
			case "1":
				res.setRtField("한식");
				break;
			case "2":
				res.setRtField("중식");
				break;
			case "3":
				res.setRtField("일식");
				break;
			case "4":
				res.setRtField("분식");
				break;
			case "5":
				res.setRtField("치킨");
				break;
			case "6":
				res.setRtField("피자");
				break;
			case "7":
				res.setRtField("족발");
				break;
			default:
				return restaurant;
			}
		} return restaurant;
		
	}
	

	/* 검색 결과 - 음식점리스트에 int로 들어오는 holiday 값을 변환 */
	public List<Restaurant> setHoliDayMethod(List<Restaurant> restaurantList) {
		for(Restaurant restaurant : restaurantList) {
			switch (restaurant.getRtHoliday()) {
			case "1":
				restaurant.setRtHoliday("일");
				break;
			case "2":
				restaurant.setRtHoliday("월");
				break;
			case "3":
				restaurant.setRtHoliday("화");
				break;			
				case "4":
				restaurant.setRtHoliday("수");
				break;			
			case "5":
				restaurant.setRtHoliday("목");
				break;			
			case "6":
				restaurant.setRtHoliday("금");
				break;
			case "7":
				restaurant.setRtHoliday("토");
				break;
			default:
				break;
			}
		} return restaurantList;
		
	}
	
	/* 음식점 Open, Close 시간만 던져주기위한 메소드 분리 */
	public List<Restaurant> timeFormatting(List<Restaurant> restaurantList) {
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat afterFormat = new SimpleDateFormat("HH:mm");
		Date resOpen_Date, resClose_Date;
		try {
			for(Restaurant res : restaurantList) {
				resOpen_Date = beforeFormat.parse(res.getRtOpen());
				resClose_Date = beforeFormat.parse(res.getRtClose());
				res.setRtOpen(afterFormat.format(resOpen_Date));
				res.setRtClose(afterFormat.format(resClose_Date));
			}
			return restaurantList;
		} catch (ParseException e) {
			System.out.println("OwnerMemberServiceImpl.timeFormatting - 데이터 변환 실패ㅠㅠ");
			e.printStackTrace();
		}
		return restaurantList;
	}
	

	@Override
	public List<Map<Object,Object>> selectSales(String id) {
		return dao.selectSales(id);
	}

	@Override
	public List<Restaurant> selectRestaurantByNameSearch(String resPlace, String resName) {
		Map<String, String> searchInfo = new HashMap<>();
		searchInfo.put("resPlace", resPlace);
		searchInfo.put("resName", resName);
		List<Restaurant> restaurantList = setFieldMethod(dao.selectRestaurantByNameSearch(searchInfo));
		restaurantList = setHoliDayMethod(restaurantList);
		return timeFormatting(restaurantList);
	
	}






	
}
