package com.my.maintest.member.svc;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.my.maintest.member.dao.MemberDAO;
import com.my.maintest.member.vo.MemberVO;

@Repository
public class MemberSVCImpl implements MemberSVC {

	@Inject
	MemberDAO memberDAO;

	@Inject
	private JavaMailSender mailSender;

	// 회원가입
	@Override
	public int singup(MemberVO memberVO) {

		return memberDAO.singup(memberVO);
	}

	// 로그인
	@Override
	public int login() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int logout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String findID(String name, String tel) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("tel", tel);

		return memberDAO.findID(map);
	}

	@Override
	public String findPW(String id, String tel) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("tel", tel);

		return memberDAO.findPW(map);
	}

	// 개별 조회
	@Override
	public MemberVO listOneMember(String id) {

		return memberDAO.listOneMember(id);
	}

	@Override
	@Async
	public void sendMail(String to, String subject, String body) {
		MimeMessage message = mailSender.createMimeMessage();
		String From = "mnj190@gmail.com";
		try {

			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setSubject(subject);
			messageHelper.setTo(to);
			messageHelper.setFrom(From);
			messageHelper.setText(body, true);
			mailSender.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int keepLogin(String id, String sessionId, Date age) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("sessionId", sessionId);
		map.put("age", age);
		return memberDAO.keepLogin(map);
	}

	@Override
	public MemberVO checkUserWithSessionKey(String sessionId) {
		// TODO Auto-generated method stub
		return memberDAO.checkUserWithSessionKey(sessionId);
	}

}
