package com.my.maintest.item.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.maintest.item.dao.ItemListDAO;
import com.my.maintest.item.svc.ItemListSVC;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.item.vo.ListVO;
import com.my.maintest.item.vo.ListingVO;
import com.my.maintest.member.vo.MemberVO;
import com.my.maintest.mypage.svc.MypageSVC;

@Controller
@RequestMapping("/itemlist")
public class ItemListController {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemListController.class);
	@Inject
	ItemListDAO itemListDAO;
	
	@Inject
	ItemListSVC itemListSVC;
	
	@Inject
	MypageSVC mypageSVC;

	@PostMapping(value="/lname", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map> lname(
			@RequestBody HashMap<String, String> map,
			HttpSession session) {
		
		ResponseEntity<Map> res = null;
		ListVO listVO = new ListVO();
		int result = 0;
		
		MemberVO memberVO =(MemberVO)session.getAttribute("member");
		String ucode = memberVO.getUcode();
		String lname = map.get("lname");
		
		listVO.setMemberVO(memberVO);
		listVO.setLname(lname);
		result = itemListSVC.listNameInsert(listVO);
		logger.info(""+listVO.getLnum());
		
		map.put("lnum", String.valueOf(listVO.getLnum()));
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

	//세션추가
	@RequestMapping(value="/saveListForm", method=RequestMethod.GET)
	public String saveListForm(
			@RequestParam(value="inum" , required = false) List<String> inum,
			@RequestParam(value="i_name", required = false) List<String> i_name,
			@RequestParam(value="icount", required = false) List<String> icount,	
			@RequestParam(value="icategory", required = false) List<String> icategory,
			@RequestParam(value="checked", required = false) List<String> checked,	
			HttpSession session,
			Model model) {

		List<Map<String, String>> listing = new ArrayList<Map<String, String>>();
		if(inum!=null) {
		for(int i=0; i<inum.size(); i++) {
			Map<String, String> itemMap = new HashMap<>();
			itemMap.put("inum", inum.get(i));
			itemMap.put("i_name", i_name.get(i));
			itemMap.put("icategory", icategory.get(i));
			itemMap.put("icount", icount.get(i));
			itemMap.put("checked", checked.get(i));
			listing.add(i,itemMap);
			}		
		}
		MemberVO memberVO =(MemberVO)session.getAttribute("member");
		String ucode = memberVO.getUcode();
		List<ListVO> listVO = null;
		listVO = itemListSVC.loadList(ucode);
	
		model.addAttribute("list", listVO);
		model.addAttribute("listing", listing);
		
		return "/packinglist/saveListForm";
	}
	
	@PostMapping(value="/saveList")
	public String inum(
			@RequestParam(value = "lnum", required = false) int lnum,
			@RequestParam(value = "inum", required = false) List<String> inum,
			@RequestParam(value = "i_name", required = false) List<String> i_name,
			@RequestParam(value = "icount", required = false) List<String> icount,
			@RequestParam(value = "icategory", required = false) List<String> icategory,
			@RequestParam(value = "checked", required = false) List<String> checked,
			HttpSession session,
			Model model) {
		
		
		List<Map<String, String>> listing = new ArrayList<Map<String, String>>();
		if (inum != null) {
			for (int i = 0; i < inum.size(); i++) {
				Map<String, String> itemMap = new HashMap<>();
				itemMap.put("i_num", inum.get(i));
				itemMap.put("i_name", i_name.get(i));
				itemMap.put("icategory", icategory.get(i));
				itemMap.put("icount", icount.get(i));
				itemMap.put("checked", checked.get(i));
				listing.add(i, itemMap);
			}
			ListVO listVO = new ListVO();

			listVO.setLnum(lnum);
			itemListSVC.insertListing(listVO, listing);
			
			MemberVO memberVO = (MemberVO) session.getAttribute("member");
			String ucode = memberVO.getUcode();
			
			model.addAttribute("mylist", mypageSVC.mylist(1, ucode));
			model.addAttribute("paging", mypageSVC.mylist_paging(1, ucode));
			session.removeAttribute("listing");			
		}else {
			return "/mypage/mylist";
		}
		
		
		//마이페이지에 리스트 화면으로 이동
		return "/mypage/mylist";

}


	@GetMapping("/loadListForm")
	public String loadListForm(
			HttpSession session,
			Model model) {
		
		MemberVO memberVO =(MemberVO)session.getAttribute("member");
		String ucode = memberVO.getUcode();
		List<ListVO> listVO = null;
		List<ListingVO> listing = null;
		listVO = itemListSVC.loadList(ucode);
		long lnum = listVO.get(0).getLnum();

		listing = itemListSVC.loadListing(lnum);
		List<ItemCategoryVO> icategory = itemListSVC.selectAllCategory();
		
		model.addAttribute("lnum", lnum);
		model.addAttribute("icategory", icategory);
		model.addAttribute("listVO", listVO);
		model.addAttribute("listingVO", listing);
		logger.info(listing.toString());
		
		return "/packinglist/loadListForm";
	}

	
	@GetMapping("/loadListForm/{lnum}")
	public String getListing(
			@PathVariable("lnum") long lnum,
			HttpSession session,
			Model model) {
		
		MemberVO memberVO =(MemberVO)session.getAttribute("member");
		String ucode = memberVO.getUcode();
		
		List<ListVO> listVO = null;
		List<ListingVO> listing = null;
		listVO = itemListSVC.loadList(ucode);
		listing = itemListSVC.loadListing(lnum);
		List<ItemCategoryVO> icategory = itemListSVC.selectAllCategory();

		model.addAttribute("lnum", lnum);
		model.addAttribute("icategory", icategory);
		model.addAttribute("listVO", listVO);
		model.addAttribute("listingVO", listing);
		
		return "/packinglist/loadListForm";
	}
	
//세션추가
	@GetMapping(value="/downloadListForm")
	public String downloadListForm(
			@RequestParam(value="inum" , required = false) List<String> inum,
			@RequestParam(value="i_name", required = false) List<String> i_name,
			@RequestParam(value="icount", required = false) List<String> icount,	
			@RequestParam(value="icategory", required = false) List<String> icategory,
			@RequestParam(value="checked", required = false) List<String> checked,	
			Model model) {
		
		List<ItemCategoryVO> category = itemListSVC.selectAllCategory();
		List<Map<String, String>> listing = new ArrayList<Map<String, String>>();
		if(inum!=null) {
		for(int i=0; i<inum.size(); i++) {
			Map<String, String> itemMap = new HashMap<>();
			itemMap.put("inum", inum.get(i));
			itemMap.put("i_name", i_name.get(i));
			itemMap.put("icategory", icategory.get(i));
			itemMap.put("icount", icount.get(i));
			itemMap.put("checked", checked.get(i));
			listing.add(i,itemMap);
			}		
		}
		
				
		model.addAttribute("listing", listing);
		model.addAttribute("category", category);
		
		return "/packinglist/downloadListForm";
	}
	
	//초기화 기능(session 제거)
	@GetMapping("/deleteAllItem")
	public String deleteAllItem(
			ItemVO itemVO,
			ItemCategoryVO itemCategoryVO,
			Model model,
			HttpSession session){
		
		//session정보가있는지 확인
		List<ListingVO> listingVO = (List<ListingVO>)session.getAttribute("listing");
		
		if(listingVO !=null) {
			//있으면s
			session.removeAttribute("listing");
		}else {
			//없으면
			logger.info("session에 listingVO가 없음 ");			
		}

		List<ItemVO> itemList = null;
		List<Map<String, String>> listing= null;
		itemList =	itemListSVC.selectAllItem();
		
		List<ItemCategoryVO> categoryList = itemListSVC.selectAllCategory();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("itemList", itemList);
		
		return "main";
	}
	
	
}
