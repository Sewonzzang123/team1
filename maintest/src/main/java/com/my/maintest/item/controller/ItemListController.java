package com.my.maintest.item.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.maintest.item.dao.ItemListDAO;
import com.my.maintest.item.svc.ItemListSVC;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.item.vo.ListVO;
import com.my.maintest.member.vo.MemberVO;

@Controller
@RequestMapping("/itemlist")
public class ItemListController {
	
	@Inject
	ItemListDAO itemListDAO;
	
	@Inject
	ItemListSVC itemListSVC;
		
	@GetMapping("category2")
	public String itemForm2(
			ItemVO itemVO,
			ItemCategoryVO itemCategoryVO,
			Model model) {
		
		model.addAttribute("categoryList", itemListSVC.selectAllCategory());
		model.addAttribute("itemList", itemListSVC.selectAllItem());
		
		return "/packinglist/packingList2";
	}
	
	@GetMapping("saveListForm")
	public String saveListForm(
			Model model
			) {
		//저장되는거 보기
		//MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String ucode = "0";
		List<ListVO> listVO = null;
		listVO = itemListSVC.loadList(ucode);
		
		model.addAttribute("list", listVO);
		
		return "/packinglist/saveListForm";
	}

	@PostMapping(value="/lname", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map> lname(@RequestBody HashMap<String, String> map) {
		ResponseEntity<Map> res = null;
		int result = 0;
		int lnum= 0;
		result = itemListSVC.listNameInsert(map.get("ucode"), map.get("lname"));
		lnum = itemListSVC.getNum(map.get("ucode"), map.get("lname"));
		map.put("lnum", String.valueOf(lnum));
		Map<String, Object> resultMap = new HashMap<>();
		
		if(result ==1) {
			map.put("rtcode","00");
			res = new ResponseEntity<Map>(map, HttpStatus.OK);
			}else {
				map.put("rtcode","01");
				res = new ResponseEntity<Map>(map, HttpStatus.OK);
			}
		return res;		
	}
	
	//세션적용
	@GetMapping("saveListForm2")
	public String saveListForm2(
			Model model,
			HttpSession session) {
		//저장되는거 보기
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String ucode = memberVO.getUcode();
		List<ListVO> listVO = null;
		listVO = itemListSVC.loadList(ucode);
		
		model.addAttribute("list", listVO);
		
		return "/packinglist/saveListForm";
	}
	
			
	
	

}
