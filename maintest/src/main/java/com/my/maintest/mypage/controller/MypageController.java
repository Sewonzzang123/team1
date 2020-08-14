package com.my.maintest.mypage.controller;

import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

		return "/mypage/modifyForm";
	}

	// 회원정보 수정 호출
	@RequestMapping("/modifyForm")
	public String get_modifyForm() {

		return "/mypage/modifyForm";
	}

	// 비밀번호 변경
	@RequestMapping("/changePW")
	public String changePW(HttpSession session, @ModelAttribute ChangePWVO info, Model model,
			HttpServletResponse response) {

		MemberVO memberVO = (MemberVO) session.getAttribute("member");

		if (info.getCulpw().trim().length() == 0) {
			model.addAttribute("err_msg_cpw", " * 필수 정보입니다.");
			return "/mypage/modifyForm";
		}

		if (!info.getCulpw().equals(memberVO.getPw())) {
			model.addAttribute("err_msg_cpw", " * 현재 비밀번호가 일치하지 않습니다.");
			return "/mypage/modifyForm";
		}

		if (info.getNewpw().trim().length() < 6) {
			model.addAttribute("err_msg_npw", " * 6~10자리로 입력해주세요.");
			logger.info(info.getNewpw() + info.getNewpwc());
			return "/mypage/modifyForm";
		}

		if (!info.getNewpw().equals(info.getNewpwc())) {
			model.addAttribute("err_msg_npw", " * 새 비밀번호가 일치하지 않습니다..");
			return "/mypage/modifyForm";
		}

		memberVO.setPw(info.getNewpw());
		mypageSVC.changePW(memberVO);
		model.addAttribute("suc_msg", " * 비밀번호가 변경되었습니다.");

		return "/mypage/modifyForm";
	}

	// 회원정보 수정
	@RequestMapping("/modify")
	public String modify(HttpSession session, @ModelAttribute MemberVO info, BindingResult result, Model model) {

		if (info.getNickname().trim().length() == 0) {
			model.addAttribute("err_msg", " * 필수 정보입니다.");
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
	@RequestMapping({ "/mylist", "/mylist/{reqPage}" })
	public String mylist(@PathVariable(value = "reqPage", required = false) Optional<Integer> reqPage,
			HttpSession session, Model model) {
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String ucode = memberVO.getUcode();

		model.addAttribute("mylist", mypageSVC.mylist(reqPage.orElse(1), ucode));
		model.addAttribute("paging", mypageSVC.paging(reqPage.orElse(1), ucode));
		logger.info(mypageSVC.mylist(reqPage.orElse(1), ucode).toString());
		logger.info(mypageSVC.paging(reqPage.orElse(1), ucode).toString());

		return "/mypage/mylist";

	}

	@RequestMapping("/del_list")
	public String del_list(@RequestBody String lnum, HttpSession session, Model model) {
		logger.info("삭제 호출됨");
		logger.info(lnum);
		mypageSVC.del_list(lnum);

		return "/mypage/mylist";

	}

	@RequestMapping("/listview/{lnum}")
	public String listview(@PathVariable String lnum, HttpSession session, Model model) {
		model.addAttribute("icategory", mypageSVC.get_category());
		model.addAttribute("listing", mypageSVC.get_listing(lnum));

		return "/mypage/listView";

	}
}
