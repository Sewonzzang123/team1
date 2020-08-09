package com.my.maintest.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.my.maintest.board.svc.BoardSVC;
import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	BoardSVC boardSVC;

	/*
	 * //게시판 카테고리 조회
	 * 
	 * @ModelAttribute("bcategory") public List<BcategoryVO> getBcategory(){
	 * List<BcategoryVO> bcategoryVO =null; bcategoryVO =
	 * boardSVC.selectBcategory(); return bcategoryVO ; }
	 * 
	 * //게시판 말머리 조회
	 * 
	 * @ModelAttribute("hidcategory") public List<HeadIdCategoryVO>
	 * getHeadIdCategory(){ List<HeadIdCategoryVO> list =
	 * boardSVC.selectHeadIdCategory(); return list; }
	 */
	// 리스트 페이지
	@GetMapping("")
	public String toBoardMain(
			// @ModelAttribute("boardVO") BoardVO boardVO
			Model model) {
		model.addAttribute("listBoardVO", boardSVC.selectArticles());
		return "/board/boardListFrm";
	}

	// 게시글목록
	@GetMapping("/boardListFrm")
	public String toboardListFrm(Model model) {

		model.addAttribute("listBoardVO", boardSVC.selectArticles());
		return "/board/boardListFrm";
	}

	// 게시글 작성 화면
	@GetMapping("/boardWriteFrm")
	public String toboardwrite(@ModelAttribute("writeBoardVO") BoardVO boardVO) {

		return "/board/boardWriteFrm1";

	}

	// 게시글 등록

	@PostMapping("/write")
	public String toWrite(

			@ModelAttribute BoardVO boardVO, BindingResult result) {
		logger.info("컨트롤단 boardVO = " + boardVO.toString());

		if (result.hasErrors()) {
			return "/board/boardWriteFrm";
		}
		boardSVC.insertArticle(boardVO);
		return "redirect:/board/boardListFrm";
	}

	// 게시글열람

	// 게시글수정

	// 게시글삭제

}
