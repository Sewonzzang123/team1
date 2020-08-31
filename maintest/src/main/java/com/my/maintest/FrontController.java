package com.my.maintest;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.my.maintest.item.dao.ItemListDAO;
import com.my.maintest.item.svc.ItemListSVC;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;

@Controller
public class FrontController {

	private static final Logger logger = LoggerFactory.getLogger(FrontController.class);

	@Inject
	ItemListDAO itemListDAO;
	
	@Inject
	ItemListSVC itemListSVC;
	
	@GetMapping({"/","/{lnum}"})
	public String home(
			@PathVariable(value = "lnum", required=false) String lnum,
			ItemVO itemVO,
			ItemCategoryVO itemCategoryVO,
			Model model) {
		List<ItemVO> itemList = null;
		List<Map<String, String>> listing= null;
		Map<String, String> listingVO = null;
		if(lnum != null) {
			listing = itemListSVC.loadListing(Long.valueOf(lnum));			
			itemList = itemListSVC.selectListItem(Long.valueOf(lnum));
		}else {
			itemList =	itemListSVC.selectAllItem();
		}
		List<ItemCategoryVO> categoryList = itemListSVC.selectAllCategory();
		model.addAttribute("listing", listing);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("itemList", itemList);

		return "main";
	}

}
