package com.my.maintest.mypage.controller;

import java.util.Optional;

import javax.inject.Inject;
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

	// �쉶�썝�젙蹂� �닔�젙 �샇異�
	@RequestMapping("/modifyForm")
	public String get_modifyForm() {

		return "/mypage/modifyForm";
	}

	// 鍮꾨�踰덊샇 蹂�寃�
	@RequestMapping("/changePW")
	public String changePW(HttpSession session, @RequestBody String nextpw) {

		logger.info(nextpw);

		MemberVO memberVO = (MemberVO) session.getAttribute("member");

		logger.info(memberVO.toString());
		memberVO.setPw(nextpw);
		mypageSVC.changePW(memberVO);

		return "/mypage/modifyForm";
	}

	// �쉶�썝�젙蹂� �닔�젙
	@RequestMapping("/modify")
	public String modify(HttpSession session, @ModelAttribute MemberVO info, BindingResult result, Model model) {

		if (info.getNickname().trim().length() == 0) {
			model.addAttribute("err_msg", " * �븘�닔 �젙蹂댁엯�땲�떎.");
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

	// �쉶�썝 �깉�눜 �샇異�
	@RequestMapping("/withdrawForm")
	public String get_withdrawForm() {

		return "/mypage/withdrawForm";
	}

	// �쉶�썝 �깉�눜 �샇異�
	@RequestMapping("/withdraw")
	public String withdraw(HttpSession session, @RequestParam String pw, Model model) {
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		if (!memberVO.getPw().equals(pw)) {
			model.addAttribute("err_msg", "* 鍮꾨�踰덊샇媛� �씪移섑븯吏� �븡�뒿�땲�떎.");

			return "/mypage/withdrawForm";
		}

		mypageSVC.withdraw(memberVO.getId());
		session.invalidate();

		return "/mypage/withdrawResult";
	}

	// �궡媛� �벖 湲�
	@RequestMapping({ "/mypost", "/mypost/{reqPage}" })
	public String mypost(@PathVariable(value = "reqPage", required = false) Optional<Integer> reqPage,
			HttpSession session, Model model) {
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String ucode = memberVO.getUcode();
		logger.info(memberVO.toString());
		model.addAttribute("mypost", mypageSVC.mypost(reqPage.orElse(1), ucode));
		model.addAttribute("paging", mypageSVC.mypost_paging(reqPage.orElse(1), ucode));

		return "/mypage/mypost";
	}

	// 湲� �궘�젣
	@RequestMapping("/del_post")
	public String del_post(@RequestBody String del_list, HttpSession session, Model model) {

		String[] del_list2 = del_list.split("/");

		for (int i = 0; i < del_list2.length - 1; i++) {
			mypageSVC.del_post(del_list2[i]);
		}

		return "/mypage/mypost";
	}

	// �궡 由ъ뒪�듃
	@RequestMapping({ "/mylist", "/mylist/{reqPage}" })
	public String mylist(@PathVariable(value = "reqPage", required = false) Optional<Integer> reqPage,
			HttpSession session, Model model) {
		
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String ucode = memberVO.getUcode();
		logger.info(memberVO.toString());

		model.addAttribute("mylist", mypageSVC.mylist(reqPage.orElse(1), ucode));
		model.addAttribute("paging", mypageSVC.mylist_paging(reqPage.orElse(1), ucode));
		logger.info(mypageSVC.mylist(reqPage.orElse(1), ucode).toString());
		logger.info(mypageSVC.mylist_paging(reqPage.orElse(1), ucode).toString());

		return "/mypage/mylist";
	}
	

	
	// 由ъ뒪�듃 �궘�젣
	@RequestMapping("/del_list")
	public String del_list(@RequestBody String lnum, HttpSession session, Model model) {
		logger.info("�궘�젣 �샇異쒕맖");
		logger.info(lnum);
		mypageSVC.del_list(lnum);
				
		return "/mypage/mylist";
	}

	// 由ъ뒪�듃 蹂닿린
	@RequestMapping("/listview/{lnum}")
	public String listview(@PathVariable String lnum, HttpSession session, Model model) {
		model.addAttribute("icategory", mypageSVC.get_category());

		model.addAttribute("listing", mypageSVC.get_listing(lnum));

		return "/mypage/listView";
	}

	// 由ъ뒪�듃 蹂닿린
	@RequestMapping("/check")
	public void item_check(@RequestBody String linum, Model model) {
		mypageSVC.item_check(linum);
		logger.info("泥댄겕�샇異�");

//		return "/mypage/listView";
	}

	// �븘�씠�뀥 泥댄겕
	@RequestMapping("/uncheck")
	public void item_uncheck(@RequestBody String linum, Model model) {
		mypageSVC.item_uncheck(linum);
		logger.info("�뼵泥댄겕�샇異�");

//		return "/mypage/listView";
	}
}
