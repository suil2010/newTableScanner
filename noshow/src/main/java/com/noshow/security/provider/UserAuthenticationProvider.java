package com.noshow.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.noshow.dao.AuthorityDao;
import com.noshow.dao.MemberDao;
import com.noshow.vo.Authority;
import com.noshow.vo.Member;


/*
 * Spring Security 컨테이너가 사용자 인증 처리(로그인 처리) 할때 호출할 클래스
 * 			=> AuthenticationProvider
 * 구현 : AuthenticationProvider 를 implements. 메소드 오버라이딩(authenticate())
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider{
	@Autowired
	private MemberDao dao;
	
	@Autowired
	private AuthorityDao Authoritydao;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//ID 체크
		String id = authentication.getName();//사용자가 입력한 ID
		Member member = dao.selectMemberByMemberId(id);
		if(member == null){ //없는 id => 로그인실패
			throw new UsernameNotFoundException("ID를 확인하세요");
		}
		//패스워드 체크
		String password = (String)authentication.getCredentials();//사용자가 입력한 패스워드.
		if(!encoder.matches(password, member.getMemberPassword())){//틀린 패스워드
			throw new BadCredentialsException("패스워드를 확인하세요");
		}
		//인증 성공
		//권한 조회
		List<Authority> list = Authoritydao.selectAuthorityByMemberId(id);
		
		//SimpleGrantedAuthority - 권한정보를 문자열로 저장.
		List<SimpleGrantedAuthority> authList = new ArrayList<>();
		for(Authority au : list){
			authList.add(new SimpleGrantedAuthority(au.getAuthority()));
		}
		
		//인증한 사용자 정보(Principal), 패스워드, 인증된사용자의 권한들 을 넣어 Authentication객체 생성해 리턴
		return new UsernamePasswordAuthenticationToken(member, null, authList);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

	
}


