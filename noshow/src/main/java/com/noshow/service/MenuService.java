package com.noshow.service;

import java.util.List;

import com.noshow.vo.Menu;

public interface MenuService {

	void addMenu(Menu menu);
	
	void deleteMenu(int menuNum);
	
	void updateMenu(Menu menu);
	
	Menu getMenuByMenuNum(int menuNum);
	
	List<Menu> getMenuBybusinessId(String businessId);
	

}
