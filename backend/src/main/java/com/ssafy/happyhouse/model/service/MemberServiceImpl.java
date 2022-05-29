package com.ssafy.happyhouse.model.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper memberMapper;

	@Override
	@Transactional
	public void registerMember(MemberDto memberDto) throws Exception {
		memberMapper.registMember(memberDto);
	}

	@Override
	public MemberDto getMember(MemberDto memberDto) throws Exception {

		return memberMapper.getMember(memberDto);
	}

	@Override
	public String findPassword(MemberDto memberDto) throws Exception {
		return memberMapper.findPassword(memberDto);
	}

	@Override
	@Transactional
	public void updateMember(MemberDto memberDto) throws Exception {
		memberMapper.updateMember(memberDto);
	}

	@Override
	@Transactional
	public void deleteMember(String id) throws Exception {
		memberMapper.deleteMember(id);

	}

	@Override
	public int idCheck(String id) throws Exception {
		return memberMapper.idCheck(id);
	}

	@Override
	public MemberDto getInfo(String id) throws Exception {
		return memberMapper.getInfo(id);
	}

	@Override
	public void sendEmail(String email, String password) throws Exception {

		String host = "smtp.naver.com";
		String user = "yourhouse2022@naver.com";
		String pass = "house1234!";

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(user, pass);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("HappyHouse 비밀번호 찾기 서비스입니다.");
			message.setText(
					"귀하의 비밀번호는 " + password + " 입니다. \n" + "즐거운 이용 되시길 바랍니다. \n" + "HappyHouse Project Team 드림");

			Transport.send(message);
			System.out.println("발송됨");
		} catch (AddressException e) {
			e.printStackTrace();
			System.out.println(email);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	

}
