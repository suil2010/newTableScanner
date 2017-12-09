package com.noshow.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noshow.dao.AuthorityDao;
import com.noshow.dao.BookmarkDAO;
import com.noshow.dao.OwnerMemberDAO;
import com.noshow.service.OwnerMemberService;
import com.noshow.vo.Authority;
import com.noshow.vo.Bookmark;
import com.noshow.vo.Restaurant;
import com.noshow.vo.Table;

@Service
public class OwnerMemberServiceImpl implements OwnerMemberService {
	
	@Resource
	private OwnerMemberDAO dao;
	
	@Resource
	private AuthorityDao authoritydao;
	
	/* 현준 추가 */
	@Resource
	private BookmarkDAO bookmarkDao;
	
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
	public Restaurant selectRestaurantByBusinessId(String memberId, String businessId) {
		Restaurant restaurant = dao.selectRestaurantByBusinessId(businessId);
		List<Restaurant> restaurantList = new ArrayList<>();
		restaurantList.add(restaurant);
		restaurantList = setFieldMethod(restaurantList);
		restaurantList = checkBookmark(memberId, restaurantList);
		restaurantList = timeFormatting(restaurantList);
		for(Restaurant r : restaurantList) {
			return r;
		}
		return restaurant;
		
	}
	
	
	/* 현준 로직 구분&분리 */
	@Override
	public Restaurant selectRestaurantByBusinessIdResInfo(String resDate, String resTime, String memberId, String businessId) {
		Restaurant restaurant = dao.selectRestaurantByBusinessId(businessId);
		List<Restaurant> restaurantList = new ArrayList<>();
		List<Table> tableList = selectUsableTable(resDate, resTime, businessId);
		restaurant.setUsableTable(tableList);
		restaurantList.add(restaurant);
		restaurantList = setFieldMethod(restaurantList);
		restaurantList = checkBookmark(memberId, restaurantList);
		restaurantList = timeFormatting(restaurantList);
		for(Restaurant r : restaurantList) {
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
	public List<Restaurant> selectRestaurantBySearch(String memberId, String resPlace, String resDate, String resTime, int resPeople) {
	
			int rtHoliday = dayFormatting(resDate);
			
			System.out.println("selectBySearch-rtHoliday : "+rtHoliday);
			System.out.println("selectBySearch-resDate : "+resDate);
			System.out.println("selectBySearch-resTime : "+resTime);
			System.out.println("selectBySearch-resPeople : "+resPeople);

			Map<String, Object> searchInfo = new HashMap<>();
			searchInfo.put("resPlace", resPlace);
			searchInfo.put("rtHoliday", rtHoliday);
			searchInfo.put("resTime", resTime);
			searchInfo.put("resPeople", resPeople);
			List<Restaurant> restaurantList = dao.selectRestaurantBySearch(searchInfo);
			restaurantList = setFieldMethod(restaurantList);
			restaurantList = setHoliDayMethod(restaurantList);
			restaurantList = timeFormatting(restaurantList);
			return checkBookmark(memberId, restaurantList);
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
	public List<Restaurant> setFieldMethod(List<Restaurant> restaurantList) {
		for(Restaurant res : restaurantList) {
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
				return restaurantList;
			}
			
		} return restaurantList;
		
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
				//Test
				resOpen_Date = beforeFormat.parse(res.getRtOpen());
				resClose_Date = beforeFormat.parse(res.getRtClose());
				res.setRtOpen(afterFormat.format(resOpen_Date));
				res.setRtClose(afterFormat.format(resClose_Date));
			}
			return restaurantList;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return restaurantList;
	}
	

	@Override
	public List<Map<Object,Object>> selectSales(String id) {
		return dao.selectSales(id);
	}

	/* 현준_이름검색 */
	@Override
	public List<Restaurant> selectRestaurantByNameSearch(String memberId, String resPlace, String resName) {
		Map<String, String> searchInfo = new HashMap<>();
		searchInfo.put("resPlace", resPlace);
		searchInfo.put("resName", resName);
		List<Restaurant> restaurantList = setFieldMethod(dao.selectRestaurantByNameSearch(searchInfo));
		restaurantList = timeFormatting(restaurantList);
		return checkBookmark(memberId, restaurantList);
	}
	
	private List<Restaurant> checkBookmark(String memberId, List<Restaurant> restaurantList) {
		
		for(Restaurant res : restaurantList) {
			Bookmark bookmark = new Bookmark(memberId, res.getBusinessId());
			int bookmarkCheck = bookmarkDao.selectCheckBookmark(bookmark);
			res.setBookmarkCheck(bookmarkCheck);
		}
		return restaurantList;
	}
	
	/* 사용가능한 테이블만 뽑아오기 위한 로직 */
	@Override
	public List<Table> selectUsableTable(String resDate, String resTime, String businessId) {

		String resStartTime = calStartTime(resDate, resTime);
		String resEndTime = calResEndTime(businessId, resStartTime);
		Map<String, String> tableMap = new HashMap<>();
		tableMap.put("resStartTime", resStartTime);
		tableMap.put("resEndTime", resEndTime);
		tableMap.put("businessId", businessId);
		
		return dao.selectUsableTable(tableMap);
	}
	
	/**
	 * 사용자에게 시간만 받아서 처리하기 위한 메소드
	 * @param resDate
	 * @param resStartTime
	 * @return
	 */
	private String calStartTime(String resDate, String resStartTime) {
		resStartTime = resDate + " " +resStartTime + ":00";
		return resStartTime;
	}
	
	
	/**
	 * 매개변수로 받은 사업주 ID로 rt_term (Table이용시간) 를 받고, resStartTime (예약시작시간) 에 re_term 을
	 * 더하여 resEndTime(예약종료시간)을 리턴
	 * 
	 * @param businessId
	 * @param resEndTime
	 * @return
	 */
	private String calResEndTime(String businessId, String resStartTime) {
		
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date formatDate;
		Restaurant restaurant = dao.selectRestaurantByBusinessId(businessId);
		int rt_term = restaurant.getRtTerm();

		try {
			formatDate = dateForm.parse(resStartTime);
			Calendar cal = new GregorianCalendar(Locale.KOREA);
			cal.setTime(formatDate);
			cal.add(Calendar.HOUR, rt_term);
			String resEndTime = dateForm.format(cal.getTime());
			return resEndTime;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resStartTime;
 
	}

}
