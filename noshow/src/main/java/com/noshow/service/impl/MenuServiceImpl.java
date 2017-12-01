package com.noshow.service.impl;

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
	
}
