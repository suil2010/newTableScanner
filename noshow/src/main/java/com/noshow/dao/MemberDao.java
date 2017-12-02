package com.noshow.dao;

import java.util.List;

import com.noshow.vo.Authority;
import com.noshow.vo.Member;

public interface MemberDao {
		
		/**
		 * 사용자 등록
		 * @param user
		 * @return
		 */
	
		int insertMember(Member member);
		
		/**
		 * 인증가능 사용자 userId로 조회
		 * @param userId
		 * @return
		 */
		Member selectMemberByMemberId(String memberId);
		
		
		/**
		 * 사용자 정보 수정처리
		 * @param user
		 * @return
		 */
		int updateMemberByMemberId(Member member);
		
		int selectDropCheckByMemberId(String memberId);
		
		int updateDropCheckByMemberId(String memberId);
}
