package com.my.maintest.mypage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.maintest.member.vo.MemberVO;

@Controller
@RequestMapping(value = "/mypage", method = RequestMethod.GET)
public class MypageController {
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);

	@RequestMapping(value = "")
	public String getMypage() {

		return "/mypage/modifyForm";
	}

	// 관리자 페이지

	// 회원관리 수정 호출
	@RequestMapping("/modifyForm")
	public String get_modifyForm() {

		return "/mypage/modifyForm";
	}

	// 비밀번호 변경
	@RequestMapping("/changePW")
	public String changePW() {

		return "redirect:/mypage/modifyForm";
	}

	// 회원정보 수정
	@RequestMapping("/modify")
	public String modify(@RequestParam String tel1, @RequestParam String tel2, @RequestParam String tel3,
			@ModelAttribute("info") MemberVO memberVO) {
		logger.info(memberVO.getNickname());
		logger.info(tel1);
		logger.info(tel2);
		logger.info(tel3);
		memberVO.setTel(tel1 + "-" + tel2 + "-" + tel3);
		logger.info(memberVO.getTel());
		return "/mypage/modifyResult";
	}

	// 회원 탈퇴 호출
	@RequestMapping("/withdrawForm")
	public String get_withdrawForm() {

		return "/mypage/withdrawForm";
	}

	// 회원 탈퇴 호출
	@RequestMapping("/withdraw")
	public String withdraw() {

		return "/mypage/withdrawResult";
	}

	// 내가 쓴 글
	@RequestMapping("/mypost")
	public String mypost() {

		return "/mypage/mypost";
	}

	// 내 리스트
	@RequestMapping("/mylist")
	public String mylist() {

		return "/mypage/mylist";
	}
}
