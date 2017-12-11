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

	/**
	 * 사용자 권한 업데이트
	 * @param authority
	 * @return
	 */
	int updateAuthority(Authority authority);
	
	/**
	 * 사용자 권한 해제
	 * @param memberId
	 * @return
	 */
	int deleteAuthority(String memberId);
	
	/**
	 * 관리자 권한 주기!
	 * @param memberId
	 * @return
	 */
	int updateAuthorityAdmin(String memberId);

}
