package com.my.maintest.member.dao;

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
public class MemberDAOImplTest {

	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImplTest.class);

	@Inject
	MemberDAO memberDAO;

	@Test
	@DisplayName("개별 조회")
	@Disabled
	void listOneMember() {
		memberDAO.listOneMember("admin");
		logger.info(memberDAO.listOneMember("admin").toString());
	}

	@Test
	@DisplayName("회원가입")
	@Disabled
	void signup() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("tel");
		memberVO.setPw("tel");
		memberVO.setTel("010-1111-1111");
		memberVO.setName("tel");
		memberVO.setNickname("tel");

		memberDAO.singup(memberVO);
		logger.info(memberDAO.listOneMember("admin").toString());
	}

}
