package com.noshow.service;

import com.noshow.vo.Member;

public interface MemberService {
		
		/**
		 * 회원 등록처리
		 * MEMBER 테이블에 사용자 정보 등록(패스워드암호화), 권한 테이블에 회원권한(ROLE_MEMBER) 등록
		 * @param member
		 * @param role : 사용자 권한 - ROLE_ADMIN, 회원 - ROLE_MEMBER
		 */
		void addMember(Member member, String role);
		
		/**
		 * 사용자 ID로 사용자 정보 조회 처리 하는 메소드
		 * @param MemberId
		 * @return
		 */
		Member getUserByMemberId(String MemberId);
		
		/**
		 * 사용자 정보 수정 처리
		 * @param member
		 */

		void updateMemberProfile(Member member);
		

}
