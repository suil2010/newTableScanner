package com.noshow.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noshow.dao.MemberDao;
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

	@Override
	public int selectFindPasswordByMemberId(Member member) {
		return session.selectOne(makeSqlId("selectFindPasswordByMemberId"), member);
	}

	@Override
	public int updatePasswordByMemberId(Member member) {
		return session.update(makeSqlId("updatePasswordByMemberId"), member);
		
	}

	@Override
	public String selectMemberIdByMemberNameAndMemberEmail(String memberName, String memberEmail){
		HashMap<String, String> map = new HashMap<>();
		map.put("memberName", memberName);
		map.put("memberEmail", memberEmail);
		return session.selectOne(makeSqlId("selectMemberIdByMemberNameAndMemberEmail"), map);
	}

	@Override
	public List<Member> selectMemberAuthorityAdmin() {
		return session.selectList(makeSqlId("selectMemberAuthorityAdmin"));
	}

	@Override
	public List<Member> selectMemberAuthorityMember() {
		return session.selectList(makeSqlId("selectMemberAuthorityMember"));
	}

	@Override
	public List<Member> selectMemberAuthorityOwner() {
		return session.selectList(makeSqlId("selectMemberAuthorityOwner"));
	}

	@Override
	public List<Member> selectWithdrawMember() {
		return session.selectList(makeSqlId("selectWithdrawMember"));
	}
	
	
	
}
