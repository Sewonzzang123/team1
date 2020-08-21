package com.my.maintest.admin.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.maintest.admin.svc.AdminSVC;
import com.my.maintest.admin.vo.Board_dataVO;
import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

//	@Inject
//	AdminDAO adminDAO;

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

//		logger.info(adminSVC.getItem().toString());
		model.addAttribute("itemCategoryVO", adminSVC.getIcate());
		model.addAttribute("item", adminSVC.getItem());

		return "admin/admin_item";
	}

//아이템 설정 저장()
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/setItem", produces = "application/json")
	@ResponseBody
	public String setItem(@RequestBody Map<String, Object> param) {
		logger.info("JSON() 호출됨!!");

		logger.info(param.toString());
//		List<Object> text = (List<Object>) param.get("icate_data");
//		logger.info(text.get(0).toString());
		for (String del_icate : (List<String>) param.get("del_icate_list")) {
			adminSVC.delIcate(del_icate);
		}

		for (String item_del : (List<String>) param.get("item_del_list")) {//
			adminSVC.delItem(item_del);
		}

//		logger.info(String.valueOf(icate_dataVO.get(0).getItem_list()));

//		for (Icate_dataVO icate_dataVO : (List<Icate_dataVO>) param.get("icate_data")) {
//			logger.info(icate_dataVO.getCa_num());
//		}
		return "에이잭스 완료";
	}
//
//	// 게시판 생성
//	@RequestMapping(value = "/createBoard", method = RequestMethod.POST)
//	public String delBoard() {
//		adminDAO.createBoard();
//		return "redirect:/admin/board";
//	}
//
////게시판 저장
//	@RequestMapping(value = "/setBoard", method = RequestMethod.POST)
//	public String setBoard(@RequestParam String catnum, @RequestParam String catname, @RequestParam String btype) {
//		BcategoryVO bcategoryVO = new BcategoryVO();
//		bcategoryVO.setBtype(btype);
//		bcategoryVO.setCatname(catname);
//		bcategoryVO.setCatnum(catnum);
//		adminDAO.setBoard(bcategoryVO);
//		return "redirect:/admin/board";
//	}

}
