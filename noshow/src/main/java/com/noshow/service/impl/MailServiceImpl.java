package com.noshow.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.noshow.service.MailService;
import com.noshow.vo.Member;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
	private JavaMailSender mailSender;
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Autowired
	private MessageSource messageSource;
	
	@Override
	public void sendMail(String subject, String Text, Member member) {
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			System.out.println("serviceimpl로 넘어옴");
			System.out.println(subject);
			System.out.println(Text);
			System.out.println(member.getMemberEmail());
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
			messageHelper.setSubject(subject);
			messageHelper.setText(Text);
			messageHelper.setFrom("jisumongo@gmail.com");
			messageHelper.setTo(new InternetAddress(member.getMemberEmail(),member.getMemberName()));
			mailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
