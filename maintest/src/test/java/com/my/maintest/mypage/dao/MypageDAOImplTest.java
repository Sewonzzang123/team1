package com.my.maintest.mypage.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.member.vo.MemberVO;
import com.my.maintest.mypage.vo.IcategoryVO;
import com.my.maintest.mypage.vo.ListingVO;
import com.my.maintest.mypage.vo.MylistVO;

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
		memberVO.setId("admin");

		mypageDAO.changePW(memberVO);
		logger.info("실행됨");
	}

	@Test
	@DisplayName("회원탈퇴")
	@Disabled
	void withdraw() {

		String id = "id";

		mypageDAO.withdraw(id);
		logger.info("실행됨");
	}

	@Test
	@DisplayName("마이리스트")
	@Disabled
	void mylist() {
		String ucode = "34";
		int str_num = 80;
		int end_num = 0;

		List<MylistVO> list = (List<MylistVO>) mypageDAO.mylist(ucode, str_num, end_num);

		for (MylistVO vo : list) {
			logger.info(vo.toString());
		}

		logger.info(String.valueOf(list.size()));
		logger.info(ucode);
	}

	@Test
	@DisplayName("리스트 수")
	@Disabled
	void total_list() {

		String ucode = "34";

		mypageDAO.total_list(ucode);
		logger.info(String.valueOf(mypageDAO.total_list(ucode)));
	}

	@Test
	@DisplayName("리스트 삭제")
	@Disabled
	void del_list() {

		String l_num = "34";

		mypageDAO.del_list(l_num);
		logger.info("실행됨");
	}

	@Test
	@DisplayName("아이템 카테고리")
	@Disabled
	void get_category() {
		IcategoryVO categoryVO = new IcategoryVO();

		mypageDAO.get_category();
		logger.info(mypageDAO.get_category().toString());
	}

	@Test
	@DisplayName("아이템 카테고리")
	@Disabled
	void get_listing() {
		ListingVO listingVO = new ListingVO();

		String lnum = "56";
		mypageDAO.get_category();
		logger.info(mypageDAO.get_listing(lnum).toString());
	}

	@Test
	@DisplayName("포스트 수")
	@Disabled
	void total_post() {

		String ucode = "34";

		mypageDAO.total_list(ucode);
		logger.info(String.valueOf(mypageDAO.total_post(ucode)));
	}

	@Test
	@DisplayName("마이포스트")
//	@Disabled
	void mypost() {
		String ucode = "34";
		int str_num = 5;
		int end_num = 1;

		List<BoardVO> list = (List<BoardVO>) mypageDAO.mypost(ucode, str_num, end_num);

		for (BoardVO vo : list) {
			logger.info(vo.toString());
		}

		logger.info(String.valueOf(list.size()));
		logger.info(ucode);
	}
}
