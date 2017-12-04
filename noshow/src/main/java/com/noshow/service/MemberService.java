package com.noshow.service;

import java.io.IOException;

import com.noshow.vo.Member;

public interface MemberService {

	/**
	 * 회원 등록처리 MEMBER 테이블에 사용자 정보 등록(패스워드암호화), 권한 테이블에 회원권한(ROLE_MEMBER) 등록
	 * 회원 가입 메세지 이메일로 전송
	 * 
	 * @param member
	 * @param role
	 *            : 사용자 권한 - ROLE_ADMIN, 회원 - ROLE_MEMBER
	 */
	void addMember(Member member, String role);

	/**
	 * 사용자 ID로 사용자 정보 조회 처리 하는 메소드
	 * 
	 * @param MemberId
	 * @return
	 */
	Member getUserByMemberId(String MemberId);

	/**
	 * 사용자 정보 수정 처리
	 * 
	 * @param member
	 */
	void updateMemberProfile(Member member);

	/**
	 * 사용자의 탈퇴 // update해서 dropCheck값을 변경해서 로그인 불가능하게 설정
	 * @param memberId
	 */
	void removeMember(String memberId);

	/**
	 * 비밀번호 찾기 (이메일로 임시비밀번호 전송)
	 * @param member
	 * @throws Exception
	 */
	void getFindByPassword(Member member) throws Exception;

}
