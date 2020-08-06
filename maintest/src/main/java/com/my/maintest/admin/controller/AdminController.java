package com.my.maintest.admin.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.maintest.admin.dao.AdminDAO;
import com.my.maintest.board.vo.BcategoryVO;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Inject
	AdminDAO adminDAO;

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	// 게시판 관리 호출
	@RequestMapping("/board")
	public String get_admin_board(Model model) {

		List<BcategoryVO> bcategoryVO = adminDAO.getCate();

		model.addAttribute("bcategoryVO", bcategoryVO);

		return "admin/admin_board";
	}

	// 게시판 삭제
	@RequestMapping(value = "/delBoard", method = RequestMethod.POST)
	public String delBoard(@RequestParam String catnum) {
		logger.info(catnum);
		adminDAO.delBoard(catnum);
		return "redirect:/admin/board";
	}

	// 게시판 생성
	@RequestMapping(value = "/createBoard", method = RequestMethod.POST)
	public String delBoard() {
		adminDAO.createBoard();
		return "redirect:/admin/board";
	}

//게시판 저장
	@RequestMapping(value = "/setBoard", method = RequestMethod.POST)
	public String setBoard(@RequestParam String catnum, @RequestParam String catname, @RequestParam String btype) {
		BcategoryVO bcategoryVO = new BcategoryVO();
		bcategoryVO.setBtype(btype);
		bcategoryVO.setCatname(catname);
		bcategoryVO.setCatnum(catnum);
		adminDAO.setBoard(bcategoryVO);
		return "redirect:/admin/board";
	}
}
