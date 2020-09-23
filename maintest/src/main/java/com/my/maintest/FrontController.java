package com.my.maintest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.my.maintest.board.dao.BoardDAO;
import com.my.maintest.board.svc.BoardSVC;
import com.my.maintest.item.dao.ItemListDAO;
import com.my.maintest.item.svc.ItemListSVC;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.item.vo.ListingVO;

@Controller
public class FrontController {

	private static final Logger logger = LoggerFactory.getLogger(FrontController.class);

	@Inject
	ItemListDAO itemListDAO;

	@Inject
	ItemListSVC itemListSVC;

	@Inject
	BoardSVC boardSVC;
	@Inject
	BoardDAO boardDAO;

	@GetMapping({ "/", "/{lnum}" })
	public String home(@PathVariable(value = "lnum", required = false) String lnum, ItemVO itemVO,
			ItemCategoryVO itemCategoryVO, Model model, HttpSession session) {

		// 게시판
		// 표현할 게시글 수
		long recNumPerPage = 6;
		// 게시판 카테고리 번호
		int tipCatnum = 1;
		int galCatnum = 2;
		int qaCatnum = 3;

		model.addAttribute("tipBoardVO", boardSVC.selectArticles("blog", tipCatnum, 1, recNumPerPage, null, null));
		model.addAttribute("galBoardVO", boardSVC.selectArticles("album", galCatnum, 1, (recNumPerPage + 2), null, null));
		model.addAttribute("qaBoardVO", boardSVC.selectArticles("album", qaCatnum, 1, recNumPerPage, null, null));

		List<ItemVO> itemList = null;
		List<ListingVO> listing = null;
		Map<String, String> listingVO = null;

		if (lnum != null) {
			listing = itemListSVC.loadListing(Long.valueOf(lnum));
			itemList = itemListSVC.selectListItem(Long.valueOf(lnum));
			// 기존 session을 지우고
			session.removeAttribute("listing");

		} else {
			itemList = itemListSVC.selectAllItem();
		}

		List<ItemCategoryVO> categoryList = itemListSVC.selectAllCategory();
		model.addAttribute("listing", listing);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("itemList", itemList);

		return "main";
	}

}
