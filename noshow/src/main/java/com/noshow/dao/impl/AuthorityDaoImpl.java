package com.noshow.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.AuthorityDao;
import com.noshow.vo.Authority;

@Repository
public class AuthorityDaoImpl implements AuthorityDao{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.noshow.config.mybatis.mapper.authorityMapper." + id;
	}
	
	@Override
	public int insertAuthority(Authority authority) {
		return session.insert(makeSqlId("insertAuthority"), authority);
	}

	@Override
	public List<Authority> selectAuthorityByMemberId(String memberId) {
		return session.selectList(makeSqlId("selectAuthorityByMemberId"), memberId);
	}

	@Override
	public int updateAuthority(Authority authority) {
		return session.update(makeSqlId("updateAuthority"), authority);
	}

	@Override
	public int deleteAuthority(String memberId) {
		return session.delete(makeSqlId("deleteAuthority"), memberId);
	}

	@Override
	public int updateAuthorityAdmin(String memberId) {
		return session.update(makeSqlId("updateAuthorityAdmin"), memberId);
	}

}
