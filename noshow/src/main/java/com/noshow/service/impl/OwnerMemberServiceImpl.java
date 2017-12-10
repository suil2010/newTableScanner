package com.noshow.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noshow.dao.AuthorityDao;
import com.noshow.dao.OwnerMemberDAO;
import com.noshow.service.OwnerMemberService;
import com.noshow.vo.Authority;
import com.noshow.vo.Restaurant;

@Service
public class OwnerMemberServiceImpl implements OwnerMemberService {
	
	@Resource
	private OwnerMemberDAO dao;
	
	@Resource
	private AuthorityDao authoritydao;

	@Override
	public Map<String, Object> selectcode() {
		
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("field", dao.selectField());
		map.put("holiday", dao.selectHoliday());
		map.put("term", dao.selectTerm());
		return map;
	}

	@Override
	public void insertRestaurant(Restaurant rt, String role) {
		dao.insertRestaurant(rt);
		authoritydao.updateAuthority(new Authority(rt.getBusinessId(), role));
	}

	@Override
	public Restaurant selectRestaurantByBusinessId(String businessId) {
		return dao.selectRestaurantByBusinessId(businessId);
	}
	

}
