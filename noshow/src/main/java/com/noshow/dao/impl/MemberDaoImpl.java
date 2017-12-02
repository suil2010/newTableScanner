package com.noshow.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.MemberDao;
import com.noshow.vo.Authority;
import com.noshow.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.noshow.config.mybatis.mapper.memberMapper." + id;
	}

	@Override
	public int insertMember(Member member) {
		return session.insert(makeSqlId("insertMember"), member);
	}

	@Override
	public Member selectMemberByMemberId(String memberId) {
		return session.selectOne(makeSqlId("selectMemberByMemberId"), memberId);
	}

	@Override
	public int updateMemberByMemberId(Member member) {
		return session.update(makeSqlId("updateMemberById"), member);
	}

	@Override
	public int selectDropCheckByMemberId(String memberId) {
		return session.selectOne(makeSqlId("selectDropCheckByMemberId"), memberId);
	}

	@Override
	public int updateDropCheckByMemberId(String memberId) {
		return session.update(makeSqlId("updateDropCheckByMemberId"), memberId);
	}
	
	
}
