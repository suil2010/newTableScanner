package com.noshow.service;

import com.noshow.vo.Menu;

public interface MenuService {

	void addMenu(Menu menu);
	
	Menu getMenuByMenuNum(int menuNum);
}
