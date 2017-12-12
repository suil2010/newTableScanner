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

import com.noshow.dao.BookmarkDAO;
import com.noshow.dao.QuestionDAO;
import com.noshow.dao.ReviewDAO;
import com.noshow.dao.SearchDAO;
import com.noshow.service.SearchService;
import com.noshow.vo.Bookmark;
import com.noshow.vo.Menu;
import com.noshow.vo.Question;
import com.noshow.vo.Restaurant;
import com.noshow.vo.Review;
import com.noshow.vo.Table;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Resource
	private SearchDAO dao;
	
	/* 현준 추가 */
	@Resource
	private BookmarkDAO bookmarkDao;
	
	/* 2017.12.11 - 현준 _ 리뷰리스트 위해 추가 */
	@Resource
	private ReviewDAO reviewDao;
	
	/* 2017.12.12 - 현준 _ 문의글 리스트 추가 */
	@Resource
	private QuestionDAO questionDao;
	
	@Override
	public Restaurant selectRestaurantByBusinessId(String memberId, String businessId) {
		Restaurant restaurant = dao.selectRestaurantByBusinessId(businessId);
//		restaurant = selectAllTable(businessId, restaurant);
		List<Restaurant> restaurantList = new ArrayList<>();
		restaurantList.add(restaurant);
//		restaurantList = setFieldMethod(restaurantList);
		restaurantList = checkBookmark(memberId, restaurantList);
		restaurantList = timeFormatting(restaurantList);
		for(Restaurant r : restaurantList) {
			return r;
		}
		return restaurant;
		
	}
	
	// 현준 로직 구분&분리 
	/* 예약조건에서 나온 식당 리스트에서 식당을 선택했을 때 식당 정보 조회 */
	@Override
	public Restaurant selectRestaurantByBusinessIdResInfo(String resDate, String resTime, String memberId, String businessId) {
		System.out.println(businessId); 
		Restaurant restaurant = dao.selectRestaurantByBusinessId(businessId);
		System.out.println("SearchServiceImpl.selcRes - restaurant : " + restaurant); //TEST
		restaurant = selectCountCheckRervation(memberId, businessId, restaurant);
		restaurant = selectQuestionByBusinessId(restaurant);
		List<Review> reviewList = selectReviewByBusinesId(businessId);
		//TEST
		for(Review review : reviewList) {
			System.out.println("SearchServiceImpl-reviewList -> review : " +review);
		}
		restaurant.setReviewList(reviewList);
		List<Restaurant> restaurantList = new ArrayList<>();
		List<Table> tableList = selectUsableTable(resDate, resTime, businessId);
		restaurant.setUsableTable(tableList);
		restaurantList.add(restaurant);
		restaurantList = checkBookmark(memberId, restaurantList);
		restaurantList = timeFormatting(restaurantList);
		//TEST
		for(Restaurant r : restaurantList) {
			if (r.getMenuList() == null) {
				System.out.println("해당 식당에 등록된 menu가 없습니다.");
				return r;
			} else {
				for(Menu m : r.getMenuList()) {
					System.out.println("SearchServiceImpl-restaurant.menu : "+ m);
				}
			}
			return r;
		}
		return restaurant;
	}

	/* 검색조건으로 식당을 검색하여 식당 리스트를 리턴(To restaurant_list) */
	@Override
	public List<Restaurant> selectRestaurantBySearch(String memberId, String resPlace, String resDate, String resTime, int resPeople) {
	
			String rtHoliday = dayFormatting(resDate);
			
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
//			restaurantList = setFieldMethod(restaurantList);
//			restaurantList = setHoliDayMethod(restaurantList);
			restaurantList = timeFormatting(restaurantList);
			return checkBookmark(memberId, restaurantList);
	}


/*	@Override
	public int selectRestaurantByRtName(String rtName) {
		return dao.selectRestaurantByRtName(rtName);
	}*/
	
	// 현준_이름검색 
	@Override
	public List<Restaurant> selectRestaurantByNameSearch(String memberId, String resPlace, String resName) {
		Map<String, String> searchInfo = new HashMap<>();
		searchInfo.put("resPlace", resPlace);
		searchInfo.put("resName", resName);
		List<Restaurant> restaurantList = dao.selectRestaurantByNameSearch(searchInfo);
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

	// 사용가능한 테이블만 뽑아오기 위한 로직 
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
	
	// 사용자가 검색시 입력한 날짜를 요일로 바꿔주는 메소드 분리 
	public String dayFormatting(String resDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date formResDate;
		String holidayChar = "";
		try {
			formResDate = dateFormat.parse(resDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(formResDate);
			int holiday = cal.get(Calendar.DAY_OF_WEEK);
			switch (holiday) {
			case 0:
				holidayChar = "일";
				break;
			case 1:
				holidayChar = "월";
				break;
			case 2:
				holidayChar = "화";
				break;			
			case 3:
				holidayChar = "수";
				break;			
			case 4:
				holidayChar = "목";
				break;			
			case 5:
				holidayChar = "금";
				break;
			case 6:
				holidayChar = "토";
				break;
			default:
				break;
			}
			return holidayChar;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return holidayChar;

	}

	// 음식점 Open, Close 시간만 던져주기위한 메소드 분리 
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
	
	/**
	 * 사용자에게 시간만 받아서 처리하기 위한 메소드
	 * @param resDate
	 * @param resStartTime
	 * @return
	 */
	private String calStartTime(String resDate, String resStartTime) {
		resStartTime = resDate + " " +resStartTime + ":00";
		System.out.println("SearchServiceImpl.calStartTime - resStartTime : " + resStartTime);
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
		int rt_term = Integer.parseInt(restaurant.getTerm().getTermName());
		System.out.println("SearchServiceImpl.calResEndTime - businessId : "+businessId +"term : "+rt_term+" 시간");
		System.out.println("SearchServiceImpl-resStartTime : "+resStartTime);
		try {
			formatDate = dateForm.parse(resStartTime);
			Calendar cal = new GregorianCalendar(Locale.KOREA);
			cal.setTime(formatDate);
			cal.add(Calendar.HOUR, rt_term);
			String resEndTime = dateForm.format(cal.getTime());
			System.out.println("변환 후 예약 종료 시간 : "+resEndTime);
			return resEndTime;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resStartTime;
 
	}

	@Override
	public Restaurant selectCountCheckRervation(String memberId, String businessId, Restaurant restaurant) {
		Map<String, String> checkRes = new HashMap<>();
		checkRes.put("memberId", memberId);
		checkRes.put("businessId", businessId);
		System.out.println("SearchServiceImpl.selCntChckRes - memberId : " +memberId+", businessId : "+businessId);
		int resCheckResult = dao.selectCountCheckRervation(checkRes);
		System.out.println("COUNT 체크 결과 : " +resCheckResult);
		System.out.println(restaurant);
		restaurant.setReservationCheck(resCheckResult);
		return restaurant;
	}
	
	private List<Review> selectReviewByBusinesId(String businessId) {
		return reviewDao.selectReviewByBusinessId(businessId);
	}
	
	private Restaurant selectQuestionByBusinessId(Restaurant restaurant) {
		List<Question> questionList = questionDao.selectQuestionByBusinessId(restaurant.getBusinessId());
		System.out.println("SearchServiceImpl.selectQuestion - before restaurant : " + restaurant); // TEST
		restaurant.setQuestionList(questionList);
		System.out.println("SearchServiceImpl.selectQuestion - after restaurant : " + restaurant); // TEST

		return restaurant;
	}
}
