package com.my.maintest.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.maintest.member.svc.MemberSVC;
import com.my.maintest.member.vo.MemberVO;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	MemberSVC memberSVC;

	// 회원가입 화면
	@GetMapping("/signupForm")
	public String get_signupForm() {
		return "/member/signupForm";
	}

	// 회원가입
	@PostMapping("/signup")
	public String signup(@RequestParam String id, @RequestParam String name, @RequestParam String pw,
			@RequestParam String pwc, @RequestParam String nickname, @RequestParam String tel1, @RequestParam String tel2,
			@RequestParam String tel3) {

		MemberVO memberVO = new MemberVO();
		memberVO.setId(id);
		memberVO.setName(name);
		memberVO.setNickname(nickname);
		memberVO.setPw(pw);
		memberVO.setTel(tel1 + "-" + tel2 + "-" + tel3);

		memberSVC.singup(memberVO);

		return "redirect:/";
	}

	// 로그인 화면
	@GetMapping("/loginForm")
	public String loginForm() {
		logger.info("로그인 호출");
		return "/member/loginForm";
	}

//로그인 처리
	@PostMapping("/login")
	public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session, Model model) {
		logger.info("String login()호출됨");
		logger.info("id : " + id);
		logger.info("pw : " + pw);

		MemberVO memberVO = memberSVC.listOneMember(id);

		// 1)회원id가 없는경우
		if (memberVO == null) {
			model.addAttribute("svr_msg", "가입된 회원 정보가 없습니다.");
			return "/member/loginForm";
		} else {
			// 2)회원id가 존재할경우
			// 2-1) 비밀번호가 일치하는경우
			if (memberVO.getPw().equals(pw)) {
				session.setAttribute("member", memberVO);
				logger.info("등록완료");
			} else {
				// 2-2) 비밀번호가 틀린경우
				model.addAttribute("svr_msg", "비밀번호가 일치하지 않습니다.");
				return "/member/loginForm";
			}
		}
		return "redirect:/";
	}

//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {

		// 세션에 저장된 정보 제거
		session.invalidate();
		return "redirect:/";
	}
}
