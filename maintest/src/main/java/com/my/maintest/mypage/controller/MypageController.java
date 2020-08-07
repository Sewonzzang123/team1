package com.my.maintest.mypage.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.maintest.member.vo.MemberVO;
import com.my.maintest.mypage.svc.MypageSVC;
import com.my.maintest.mypage.vo.ChangePWVO;

@Controller
@RequestMapping(value = "/mypage")
public class MypageController {
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);

	@Inject
	MypageSVC mypageSVC;

	@RequestMapping(value = "")
	public String getMypage() {

		return "/mypage/modifyForm";
	}

	// 회원정보 수정 호출
	@RequestMapping("/modifyForm")
	public String get_modifyForm() {

		return "/mypage/modifyForm";
	}

	// 비밀번호 변경
	@RequestMapping("/changePW")
	public String changePW(HttpSession session, @ModelAttribute("info") ChangePWVO info) {

		MemberVO memberVO = (MemberVO) session.getAttribute("member");

		memberVO.setPw(info.getNextpw());

		logger.info(memberVO.toString());
		logger.info(info.toString());
		mypageSVC.changePW(memberVO);

		return "redirect:/mypage/modifyForm";
	}

	// 회원정보 수정
	@RequestMapping("/modify")
	public String modify(HttpSession session, @ModelAttribute("info") MemberVO info

	) {

		MemberVO memberVO = (MemberVO) session.getAttribute("member");

		memberVO.setNickname(info.getNickname());
		memberVO.setTel(info.getTel1() + "-" + info.getTel2() + "-" + info.getTel3());

		mypageSVC.modify(memberVO);

		return "/mypage/modifyResult";
	}

	// 회원 탈퇴 호출
	@RequestMapping("/withdrawForm")
	public String get_withdrawForm() {

		return "/mypage/withdrawForm";
	}

	// 회원 탈퇴 호출
	@RequestMapping("/withdraw")
	public String withdraw(HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		mypageSVC.withdraw(memberVO.getId());

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
