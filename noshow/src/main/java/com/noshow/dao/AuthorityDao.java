package com.noshow.dao;

import java.util.List;

import com.noshow.vo.Authority;

public interface AuthorityDao {
	/**
	 * 사용자의 권한 등록
	 * @param authority
	 * @return
	 */
	int insertAuthority(Authority authority);
	
	/**
	 * userId의 사용자의 권한들 조회
	 * @param userId
	 * @return
	 */
	List<Authority> selectAuthorityByMemberId(String memberId);

	int updateAuthority(Authority authority);
	
	int deleteAuthority(String memberId);

}
