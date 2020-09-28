package com.my.maintest.member.dao;

import java.util.HashMap;
import java.util.Map;

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
	//@Disabled
	void signup() {
		
		for(int i = 1 ; i <=  10; i++) {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("tester" + i + "@gmail.com");
		memberVO.setPw("qqaaqq");
		memberVO.setTel("010-1111-1111");
		memberVO.setName("홍길동"+i);
		memberVO.setNickname("테스터" + (i+1));
		memberDAO.singup(memberVO);
		
		}
		logger.info(memberDAO.listOneMember("admin").toString());
	}

	@Test
	@DisplayName("아이디 찾기")
	@Disabled
	void findID() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "관리자");
		map.put("tel", "010-1111-1111");

		logger.info(memberDAO.findID(map));
	}

}
