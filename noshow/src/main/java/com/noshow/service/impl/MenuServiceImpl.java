package com.noshow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noshow.dao.MenuDao;
import com.noshow.service.MenuService;
import com.noshow.vo.Menu;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuDao dao;

	@Override
	public void addMenu(Menu menu) {
		dao.insertMenu(menu);
	}

	@Override
	public Menu getMenuByMenuNum(int menuNum) {
		return dao.selectMenuByMenuNum(menuNum);
	}

	@Override
	public List<Menu> getMenuBybusinessId(String businessId) {
		return dao.selectMenuBybusinessId(businessId);
	}

	@Override
	public void deleteMenu(int menuNum) {
		dao.deleteMenu(menuNum);
	}
	
}
