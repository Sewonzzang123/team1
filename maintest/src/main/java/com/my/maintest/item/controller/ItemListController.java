package com.my.maintest.item.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.maintest.item.dao.ItemListDAO;
import com.my.maintest.item.svc.ItemListSVC;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;

@Controller
@RequestMapping("/itemlist")
public class ItemListController {
	
	@Inject
	ItemListDAO itemListDAO;
	
	@Inject
	ItemListSVC itemListSVC;
	
//	@GetMapping("/category")
//	public String form(ItemCategoryVO itemCategoryVO, Model model) {
//		
//		List<ItemCategoryVO> categorylist =  itemCategoryDAO.selectAllCategory();
//		model.addAttribute("categoryList", categorylist);
//	
//		
//		return "/itemlist/category";
//	}
	
//	@GetMapping("/category2")
//	public String form2(ItemCategoryVO itemCategoryVO, ItemVO itemVO, Model model) {
//		
//		List<ItemCategoryVO> categorylist =  itemCategoryDAO.selectAllCategory();
//		model.addAttribute("categoryList", categorylist);
//		
//		String ca_num = "1";
//		List<ItemVO> itemList = itemCategoryDAO.selectAllItem(ca_num);
//		model.addAttribute("itemList", itemList);
//		
//		return "/itemlist/writeList";
//	}
	
	@GetMapping("category")
	public String itemForm(
			ItemVO itemVO,
			ItemCategoryVO itemCategoryVO,
			Model model) {
		
		List<ItemCategoryVO> categorylist =  itemListDAO.selectAllCategory();
		model.addAttribute("categoryList", categorylist);
		
		List<ItemVO> itemList = itemListDAO.selectAllItem();
		model.addAttribute("itemList", itemList);
		
		return "/packinglist/packingList";
	}
	
	@GetMapping("category2")
	public String itemForm2(
			ItemVO itemVO,
			ItemCategoryVO itemCategoryVO,
			Model model) {
		
		model.addAttribute("categoryList", itemListSVC.selectAllCategory());
		model.addAttribute("itemList", itemListSVC.selectAllItem());
		
		return "/packinglist/packingList2";
	}
	
//	@ResponseBody
//	@GetMapping(value ="/${ca_num}")
//	public String getItemList() {
//		return 
//	}
	
			
	
	

}
