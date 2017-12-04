package com.noshow.service.impl;

import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noshow.dao.AuthorityDao;
import com.noshow.dao.MemberDao;
import com.noshow.service.MemberService;
import com.noshow.vo.Authority;
import com.noshow.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao dao;

	@Autowired
	private AuthorityDao Authoritydao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Autowired
	private MessageSource messageSource;

	@Override
	@Transactional
	public void addMember(Member member, String role) {
		member.setMemberPassword(passwordEncoder.encode(member.getMemberPassword()));
		dao.insertMember(member);
		Authoritydao.insertAuthority(new Authority(member.getMemberId(), role));

		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
			messageHelper.setSubject("Table Scanner 회원가입을 축하합니다.");
			messageHelper.setText(member.getMemberName() + "(" + member.getMemberId() + ")" + " 님의 회원가입을 진심으로 축하드립니다.");
			messageHelper.setFrom("jisumongo@gmail.com");
			messageHelper.setTo(new InternetAddress(member.getMemberEmail(), member.getMemberName()));
			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Member getUserByMemberId(String MemberId) {
		return dao.selectMemberByMemberId(MemberId);
	}

	@Override
	public void updateMemberProfile(Member member) {
		member.setMemberPassword(passwordEncoder.encode(member.getMemberPassword()));
		dao.updateMemberByMemberId(member);
	}

	@Override
	@Transactional
	public void removeMember(String memberId) {
		dao.updateDropCheckByMemberId(memberId);
		Authoritydao.deleteAuthority(memberId);
	}

	@Override
	@Transactional
	public void getFindByPassword(Member member) throws Exception {
		if (dao.selectFindPasswordByMemberId(member) == 1) {
			String newPassword = getRandomPassword(10);
			member.setMemberPassword(passwordEncoder.encode(newPassword));
			dao.updatePasswordByMemberId(member);
			member = dao.selectMemberByMemberId(member.getMemberId());

			MimeMessage message = mailSender.createMimeMessage();
			try {
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
				messageHelper.setSubject("Table Scanner 비밀번호 찾기");
				messageHelper.setText(
						member.getMemberName() + "(" + member.getMemberId() + ")" + " 님의 비밀번호는" + newPassword + "입니다.");
				messageHelper.setFrom("jisumongo@gmail.com");
				messageHelper.setTo(new InternetAddress(member.getMemberEmail(), member.getMemberName()));
				mailSender.send(message);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new Exception("오류입니다.");
		}

	}

	private String getRandomPassword(int length) {
		char[] charaters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
				's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuilder sb = new StringBuilder("");
		Random rm = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(charaters[rm.nextInt(charaters.length)]);
		}
		return sb.toString();
	}
}
