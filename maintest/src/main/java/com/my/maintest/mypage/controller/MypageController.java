package com.my.maintest.mypage.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

		return "redirect:/mypage/modifyForm";
	}

	// 회원정보 수정 호출
	@RequestMapping("/modifyForm")
	public String get_modifyForm(@ModelAttribute MemberVO memberVO) {

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
	public String modify(HttpSession session, @ModelAttribute MemberVO info, BindingResult result, Model model) {

		if (info.getNickname().trim().length() == 0) {
			result.rejectValue("nickname", "required", "* 필수 정보입니다.");
			return "/mypage/modifyForm";
		}

		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		memberVO.setNickname(info.getNickname());
		memberVO.setTel1(info.getTel1());
		memberVO.setTel2(info.getTel2());
		memberVO.setTel3(info.getTel3());
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
	public String withdraw(HttpSession session, @RequestParam String pw, Model model) {
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		if (!memberVO.getPw().equals(pw)) {
			model.addAttribute("err_msg", "* 비밀번호가 일치하지 않습니다.");

			return "/mypage/withdrawForm";
		}

		mypageSVC.withdraw(memberVO.getId());
		session.invalidate();

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
