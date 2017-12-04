package com.noshow.service;

import java.io.Serializable;

import com.noshow.vo.Member;

public interface MailService extends Serializable{
	
	void sendMail(String subject, String Text, Member member);
	
}
