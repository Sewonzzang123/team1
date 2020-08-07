package com.my.maintest.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.maintest.board.svc.BoardSVC;
import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Inject
	BoardSVC boardSVC;

	
	//게시판 카테고리 조회	
	@ModelAttribute("bcategoryVO")
	public List<BcategoryVO> getBcategory(){
		 List<BcategoryVO> bcategoryVO =null;
		 bcategoryVO =  boardSVC.selectBcategory();		 
		return bcategoryVO ;
	}
	
	//게시판 말머리 조회
	@ModelAttribute("headIdCategoryVO")
	public List<HeadIdCategoryVO> getHeadIdCategory(){
		String catnum = "0" ; 
		 List<HeadIdCategoryVO>  list = boardSVC.selectHeadIdCategory(catnum);
		return list;
	}
	
	// 리스트 페이지 
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
	public String toboardwrite(@ModelAttribute("boardVO") BoardVO baordVO) {

		return "/board/boardWriteFrm";
	}

	// 게시글 등록
	@PostMapping("/write")
	public String toWrite(
			//@Valid 
			@ModelAttribute("boardVO") BoardVO boardVO,Model model
			//,BindingResult result
			) {
	/*	if (result.hasErrors()) {
			return "/board/boardWriteFrm";
		}*/
		boardSVC.insertArticle(boardVO);
		model.addAttribute("list",	boardSVC.selectArticles()); //중복 체크???
		return "redirect:/board/boardListFrm";
	}


	// 게시글열람

	// 게시글수정

	// 게시글삭제

}
