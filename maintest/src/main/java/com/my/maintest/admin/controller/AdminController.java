package com.my.maintest.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.maintest.admin.svc.AdminSVC;
import com.my.maintest.admin.vo.Board_dataVO;
import com.my.maintest.admin.vo.Icate_dataVO;
import com.my.maintest.admin.vo.Item_dataVO;
import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;
import com.my.maintest.member.vo.MemberVO;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Inject
	AdminSVC adminSVC;

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	// 게시판 관리 호출
	@RequestMapping("/board")
	public String get_admin_board(Model model) {

		model.addAttribute("bcategoryVO", adminSVC.getCate());
		model.addAttribute("headIdCategoryVO", adminSVC.getHead());

		return "admin/admin_board";
	}

	// 게시판 설정 저장(Restfull 처리, 응답포맷:JSON)
	@PostMapping(value = "/setBoard", produces = "application/json")
	@ResponseBody
	public String setBoard(@RequestBody List<Board_dataVO> board_dataVO) {
		logger.info("JSON() 호출됨!!");

		for (Board_dataVO obj : board_dataVO) {
			// 게시판 설정
			BcategoryVO bcategoryVO = new BcategoryVO();
			bcategoryVO.setCatnum(obj.getCanum());
			bcategoryVO.setCatname(obj.getCaname());
			bcategoryVO.setBtype(obj.getBtype());
			bcategoryVO.setBmemo(obj.getBmemo());

			if (bcategoryVO.getCatnum().equals("new")) {
				adminSVC.createBoard(bcategoryVO);
			} else {
				adminSVC.setBoard(bcategoryVO);
			}

			// 말머리 설정
			for (String sub : obj.getSub_list()) {
				HeadIdCategoryVO headId = new HeadIdCategoryVO("new", obj.getCanum(), sub);
				adminSVC.setHead(headId);
			}

			// 말머리 삭제
			if (obj.getDel_sub_list().trim().length() != 0) {
				logger.info("말머리 삭제 요청");
				String[] del_sub = obj.getDel_sub_list().split("/");
				for (String hidnum : del_sub) {
					adminSVC.delHead(hidnum);
				}
			}
		}
		return "location.reload(true)";
	}

	// 게시판 삭제
	@RequestMapping(value = "/delBoard", method = RequestMethod.POST)
	public String delBoard(@RequestBody String del_board_list) {
		logger.info("삭제 호출");
		String[] del_board = del_board_list.split("/");
		logger.info(String.valueOf(del_board.length));
		for (String catnum : del_board) {
			adminSVC.delBoard(catnum);
		}

		return "admin/admin_board";
	}

	// 아이템 관리 호출
	@RequestMapping("/item")
	public String get_admin_item(Model model) {

		model.addAttribute("itemCategoryVO", adminSVC.getIcate());
		model.addAttribute("item", adminSVC.getItem());

		return "admin/admin_item";
	}

	// 아이템 설정 저장()
	@PostMapping(value = "/setItem", produces = "application/json")
	@ResponseBody
	public String setItem(@RequestBody Item_dataVO param) {
		logger.info("JSON() 호출됨!!");

		logger.info(param.getDel_icate_list().toString());

		logger.info(param.getIcate_data().toString());

		logger.info(param.getItem_del_list().toString());

		for (String ca_num : param.getDel_icate_list()) {
			adminSVC.delIcate(ca_num);
		}

		for (String i_num : param.getItem_del_list()) {
			adminSVC.delItem(i_num);
		}

		for (Icate_dataVO data : param.getIcate_data()) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("ca_name", data.getCa_name());

			if (data.getCa_num().equals("new")) {
				data.setCa_num(adminSVC.getCa_num());
				map.put("ca_num", data.getCa_num());
				adminSVC.setIcate(map);
			} else {
				map.put("ca_num", data.getCa_num());
				adminSVC.modifyIcate(map);
			}

			for (String i_name : data.getItem_list())
				if (i_name != null) {
					logger.info(i_name);
					logger.info(map.get("ca_num"));
					map.put("i_name", i_name);
					adminSVC.setItem(map);
				}
		}
		return "설정 완료";
	}

	// 회원 관리 호출
	@RequestMapping({ "/member", "/member/{reqPage}", "/member/{reqPage}/{searchType}/{keyword}" })
	public String get_admin_member(@PathVariable(value = "reqPage", required = false) Optional<Integer> reqPage,
			@PathVariable(value = "searchType", required = false) String searchType,
			@PathVariable(value = "keyword", required = false) String keyword, HttpSession session, Model model) {

		if (searchType != null) {
			logger.info("검색 호출");
			model.addAttribute("memberlist", adminSVC.memberlist(reqPage.orElse(1), searchType, keyword));
			model.addAttribute("paging", adminSVC.paging(reqPage.orElse(1), searchType, keyword));
			model.addAttribute("searchType", searchType);
			model.addAttribute("keyword", keyword);
		} else {
			model.addAttribute("memberlist", adminSVC.memberlist(reqPage.orElse(1)));
			model.addAttribute("paging", adminSVC.paging(reqPage.orElse(1)));

		}

		return "/admin/admin_member";
	}

	// 강제 탈퇴
	@PostMapping(value = "/exit", produces = "application/json")
	@ResponseBody
	public String exit_member(@RequestBody List<String> delete_list) {

		for (String ucode : delete_list) {
			adminSVC.exit_member(ucode);
		}

		return "강퇴완료";
	}

}
