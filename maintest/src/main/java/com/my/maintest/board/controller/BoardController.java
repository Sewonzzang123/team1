package com.my.maintest.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.maintest.board.svc.BoardSVC;
import com.my.maintest.board.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Inject
	BoardSVC boardSVC;

	// 보드 메인
	@GetMapping("")
	public String toBoardMain(
			//@ModelAttribute("boardVO") BoardVO boardVO			
			Model model) {
		model.addAttribute("list",	boardSVC.selectArticles());
		return "/board/boardListFrm";
	}
	
	
	// 게시글목록
	@GetMapping("/boardListFrm")
	public String toboardListFrm(
			//@ModelAttribute("list") BoardVO boardVO
			Model model
			) {
		model.addAttribute("list",	boardSVC.selectArticles());
			return "/board/boardListFrm";
}
	
	
	
	

	// 각 보드 메인
	@GetMapping("/boardtext")
	public String toBoardText() {

		return "/board/boardTextFrm";
	}

	// 게시글 작성 화면
	@GetMapping("/boardWriteFrm")
	public String toboardwrite() {

		return "/board/boardWriteFrm";
	}

	// 게시글 등록

	@PostMapping("/write")
	public String toWrite(
			//@Valid 
			@ModelAttribute("boardVO") BoardVO boardVO
			//,BindingResult result
			) {
	/*	if (result.hasErrors()) {
			return "/board/boardWriteFrm";
		}*/
		boardSVC.insertArticle(boardVO);
		return "redirect:/board/boardListFrm";
	}


	// 게시글열람

	// 게시글수정

	// 게시글삭제

}
