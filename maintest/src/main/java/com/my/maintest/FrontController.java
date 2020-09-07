package com.my.maintest;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.my.maintest.board.svc.BoardSVC;

@Controller
public class FrontController {

	@Inject
	BoardSVC boardSVC;
	
	private static final Logger logger = LoggerFactory.getLogger(FrontController.class);

	@GetMapping(value = "/")
	public String home(
			Model model)  {
		//보드 타입
		
		//표현할 게시글 수 
		long recNumPerPage = 6;
		//게시판 카테고리 번호
		int tipCatnum = 1;
		int galCatnum = 2;
		int qaCatnum = 3;		
		
		model.addAttribute("tipBoardVO",boardSVC.selectArticlesWithKey("blog",tipCatnum, 1, recNumPerPage, null, null).get("list"));
		model.addAttribute("galBoardVO",boardSVC.selectArticlesWithKey("album",galCatnum,  1, (recNumPerPage+2), null, null).get("list"));
		model.addAttribute("qaBoardVO",boardSVC.selectArticlesWithKey("blog", qaCatnum, 1, recNumPerPage, null, null).get("list"));
		

		
		return "main";
	}
	
	

}
