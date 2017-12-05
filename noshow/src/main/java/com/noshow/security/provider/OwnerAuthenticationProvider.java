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
import com.noshow.dao.OwnerMemberDAO;
import com.noshow.vo.Authority;
import com.noshow.vo.Member;


/*
 * Spring Security 컨테이너가 사용자 인증 처리(로그인 처리) 할때 호출할 클래스
 * 			=> AuthenticationProvider
 * 구현 : AuthenticationProvider 를 implements. 메소드 오버라이딩(authenticate())
 */
@Component
public class OwnerAuthenticationProvider implements AuthenticationProvider{
	@Autowired
	private OwnerMemberDAO dao;
	
	@Autowired
	private AuthorityDao Authoritydao;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		
		
		
		return authentication;
		
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

	
}


