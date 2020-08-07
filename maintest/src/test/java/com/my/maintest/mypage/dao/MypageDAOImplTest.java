package com.my.maintest.mypage.dao;

import javax.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.maintest.member.vo.MemberVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
public class MypageDAOImplTest {
	private static final Logger logger = LoggerFactory.getLogger(MypageDAOImplTest.class);

	@Inject
	MypageDAO mypageDAO;

	@Test
	@DisplayName("회원 정보 수정")
	@Disabled
	void modify() {

		MemberVO memberVO = new MemberVO();
		memberVO.setNickname("12345");
		memberVO.setTel("010-9999-8888");
		memberVO.setId("min");

		mypageDAO.modify(memberVO);
		logger.info("실행됨");
	}

	@Test
	@DisplayName("비밀번호 변경")
	@Disabled
	void changePW() {

		MemberVO memberVO = new MemberVO();
		memberVO.setPw("1234567");
		memberVO.setId("min");

		mypageDAO.changePW(memberVO);
		logger.info("실행됨");
	}

	@Test
	@DisplayName("회원탈퇴")
//	@Disabled
	void withdraw() {

		String id = "id";

		mypageDAO.withdraw(id);
		logger.info("실행됨");
	}
}
