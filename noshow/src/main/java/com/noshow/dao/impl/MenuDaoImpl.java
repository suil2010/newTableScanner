package com.noshow.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.MenuDao;
import com.noshow.vo.Classification;
import com.noshow.vo.Menu;

@Repository
public class MenuDaoImpl implements MenuDao{
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id){
		return "com.noshow.config.mybatis.mapper.menuMapper." + id;
	}
	
	@Override
	public int insertClassification(Classification classification) {
		return session.insert(makeSqlId("insertClassification"), classification);
	}

	@Override
	public int updateClassification(Classification classification) {
		return session.update(makeSqlId("updateClassification"), classification);
	}

	@Override
	public int deleteClassification(int classificationNum) {
		return session.update(makeSqlId("deleteClassification"), classificationNum);
	}

	@Override
	public int insertMenu(Menu menu) {
		return session.insert(makeSqlId("insertMenu"), menu);
	}

	@Override
	public int updateMenu(Menu menu) {
		return session.update(makeSqlId("updateMenu"), menu);
	}

	@Override
	public int deleteMenu(int menuNum) {
		return session.delete(makeSqlId("deleteMenu"), menuNum);
	}

	@Override
	public List<Classification> selectClassificationBybusinessId(String businessId) {
		return session.selectList(makeSqlId("selectClassificationBybusinessId"), businessId);
	}

	@Override
	public List<Menu> selectMenuBybusinessId(String businessId) {
		return session.selectList(makeSqlId("selectMenuBybusinessId"), businessId);
	}

}
