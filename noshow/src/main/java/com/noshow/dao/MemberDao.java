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
		
		/**
		 * 사용자의 DropCheck 회원 탈퇴 여부 확인
		 * @param memberId
		 * @return
		 */
		int selectDropCheckByMemberId(String memberId);
		
		/**
		 * 사용자가 회원탈퇴시 DropCheck 값을 1로 변경
		 * @param memberId
		 * @return
		 */
		int updateDropCheckByMemberId(String memberId);
		
		/**
		 * 사용자가 넘겨준 ID, Email로 일치하는 회원이 있는지 조회
		 * @param member
		 * @return
		 */
		int selectFindPasswordByMemberId(Member member);
		
		/**
		 * 임시비밀번호로 변경처리
		 * @param member
		 * @return
		 */
		int updatePasswordByMemberId(Member member);
}
