package com.my.maintest.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public String get_signupForm(@ModelAttribute MemberVO memberVO) {

		return "/member/signupForm";
	}

	// 회원가입
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute @Valid MemberVO signup_info, BindingResult bindingResult, Model model,
			HttpSession session) {

		// 1) 필수 사항 입력확인
		if (bindingResult.hasErrors()) {
			return "/member/signupForm";
		}

		// 2) 아이디 중복확인
		MemberVO memberVO = memberSVC.listOneMember(signup_info.getId());
		if (memberVO != null) {
			bindingResult.rejectValue("id", "duplicate", "* 중복된 아이디입니다.");
			return "/member/signupForm";
		}

		// 3) 비밀번호 확인
		if (!signup_info.getPw().equals(signup_info.getPwc())) {
			logger.info(signup_info.getPw());
			logger.info(signup_info.getPwc());
			bindingResult.rejectValue("pwc", "nomatch", "* 비밀번호가 일치하지 않습니다.");
			return "/member/signupForm";
		}

		signup_info.setTel(signup_info.getTel1() + "-" + signup_info.getTel2() + "-" + signup_info.getTel3());

		memberSVC.singup(signup_info);
		session.setAttribute("member", signup_info);

		return "/member/signupResult";
	}

	// 로그인 화면
	@GetMapping("/loginForm")
	public String loginForm(Model model) {
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
			model.addAttribute("svr_msg", "존재하지 않는 아이디입니다.");
			return "/member/loginForm";
		} else {
			// 2)회원id가 존재할경우
			// 2-1) 비밀번호가 일치하는경우
			if (memberVO.getPw().equals(pw)) {
				String[] telSP = memberVO.getTel().split("-");
				memberVO.setTel1(telSP[0]);
				memberVO.setTel2(telSP[1]);
				memberVO.setTel3(telSP[2]);

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

	// 아이디 찾기 화면
	@GetMapping("/findIDForm")
	public String get_findIDForm() {

		return "member/findIDForm";
	}

	// 아이디 찾기
	@PostMapping("/findID")
	public String findID(@RequestParam String name, @RequestParam String tel, Model model) {
		String id = memberSVC.findID(name, tel);

		// 1)회원id가 없는경우
		if (id == null) {
			model.addAttribute("err_msg", "일치하는 정보가 없습니다.");
			return "/member/findIDForm";
		}

		model.addAttribute("name", name);
		model.addAttribute("id", id);

		return "member/findIDResult";
	}

	// 비밀번호 찾기 화면
	@GetMapping("/findPWForm")
	public String get_findPWForm() {

		return "member/findPWForm";
	}

	// 비밀번호 찾기
	@PostMapping("/findPW")
	public String findPW(@RequestParam String id, @RequestParam String tel, Model model) {
		String pw = memberSVC.findPW(id, tel);

		// 1)회원id가 없는경우
		if (pw == null) {
			model.addAttribute("err_msg", "일치하는 정보가 없습니다.");
			return "/member/findPWForm";
		}

		model.addAttribute("id", id);

		return "member/findPWResult";
	}
}
