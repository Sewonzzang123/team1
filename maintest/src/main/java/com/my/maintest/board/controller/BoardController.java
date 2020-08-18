package com.my.maintest.board.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.maintest.board.svc.BoardSVC;
import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardVO;
//import com.my.maintest.board.vo.TestVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;
import com.my.maintest.board.vo.TemporaryVO;
import com.my.maintest.common.paging.PagingComponent;

@Controller
@RequestMapping("/board")
public class BoardController {

	Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	BoardSVC boardSVC;

	// 게시판 카테고리 조회
	@ModelAttribute("bcategory")
	public List<BcategoryVO> getBcategory() {
		List<BcategoryVO> bcategoryVO = null;
		bcategoryVO = boardSVC.selectBcategory();
		return bcategoryVO;
	}

	// 게시판 말머리 조회

	@ModelAttribute("hidcategory")
	public List<HeadIdCategoryVO> getHeadIdCategory() {
		List<HeadIdCategoryVO> list = boardSVC.selectHeadIdCategory();
		return list;
	}

	// 임시 로그인 + 리스트 페이지
	@GetMapping("")
	String toLoginfrm() {
		return "/loginForm";
	}

	// 로그인 처리 및 세션
	@GetMapping("/login")
	String toLogin(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session, Model model) {

		System.out.println("String login()호출됨");
		TemporaryVO tmpVO = new TemporaryVO();

		tmpVO.setId(id);
		tmpVO.setPw(pw);
		session.setAttribute("member", tmpVO);

		System.out.println("getSession" + session.getAttribute("member").toString());

		return "redirect:/board/boardListFrm/1";
	}
//
//	// 게시글목록
//	@GetMapping({ "/boardListFrm", 
//																		"/boardListFrm/{reqPage}", 
//																		"/boardListFrm/{reqPage}/{recNumPerPage}" }) // 보여지는 게시글 수	조정을 위한 값
//																																																			
//	public String toboardListFrm(@PathVariable(value = "reqPage", required = false) Optional<Integer> reqPage,
//			@PathVariable(value = "recNumPerPage", required = false) String recNumPerPage, Model model) {
//
//		model.addAttribute("articles", boardSVC.selectArticles(reqPage.orElse(1)));
//		model.addAttribute("pagingComponent", boardSVC.getPagingComponent(reqPage.orElse(1)));
//
//		return "/board/boardListFrm";
//	}

	// 게시글 목록 (페이징 + 검색)
	@GetMapping({
		 "/boardListFrm", 
			"/boardListFrm/{reqPage}", 
			"/search/{reqPage}/{searchType}/{searchKeyword}"})
	public String toSearch(@PathVariable(value = "reqPage", required = false) Optional<Integer> reqPage,
			@PathVariable(value = "searchType", required = false) String searchType,
			@PathVariable(value = "searchKeyword", required = false) String searchKeyword,
			Model model) {

		model.addAttribute("articles", boardSVC.selectArticlesWithKey(reqPage.orElse(1), searchType, searchKeyword));
		model.addAttribute("pagingComponent", boardSVC.getPagingComponent(reqPage.orElse(1),searchType,searchKeyword ));

		return "/board/boardListFrm";
	}

	// 게시글 작성 화면
	@GetMapping("/boardWriteFrm/{returnPage}")
	public String toboardWriteFrm(@ModelAttribute BoardVO boardVO, @ModelAttribute("returnPage") String returnPage) {

		return "/board/boardWriteFrm";
	}

	// 게시글 등록
	@PostMapping("/write")
	public String toWrite(@ModelAttribute BoardVO boardVO

	// , BindingResult result
	) {

		// if (result.hasErrors()) {
		// return "/board/boardWriteFrm";
		// }

		System.out.println(boardVO.getFiles());
		System.out.println(boardVO.getBtitle());

		boardSVC.insertArticle(boardVO);
		return "redirect:/board/boardListFrm";
	}

	// 게시글열람
	@GetMapping("/read/{bnum}/{returnPage}") // returnPage : 열람후 리스트로 이동시 돌아갈 reqPage
	public String toRead(@PathVariable("bnum") Long bnum, @ModelAttribute("returnPage") String returnPage, Model model) {

		// svc는 map 타입을 반환값으로 가짐
		Map<String, Object> map = boardSVC.selectArticle(bnum);

		// 게시글 타입은 BoardVO
		model.addAttribute("boardVO", map.get("boardVO"));
		// 파일 타입은 List<BoardFileVO>
		model.addAttribute("files", map.get("files"));
		return "/board/boardReadFrm";
	}

	// 첨부파일 일부 삭제
	@GetMapping("/deleteFile/{fid}")
	@ResponseBody
	public ResponseEntity<String> toDeleteFile(@PathVariable("fid") long fid, Model model) {
		ResponseEntity<String> responseEntity = null;
		int result = boardSVC.deleteFile(fid);

		if (result == 1) {

			responseEntity = new ResponseEntity<>("success", HttpStatus.OK);
		} else {

			responseEntity = new ResponseEntity<>("fail", HttpStatus.OK);
		}
		return responseEntity;
	}

	// 게시글수정
	@PostMapping("/save")
	public String toSaveChanges(@ModelAttribute BoardVO boardVO, Model model) {
		boardSVC.updateArticle(boardVO);
		return "redirect:/board/read/" + boardVO.getBnum();
	}

	// 게시글삭제
	@GetMapping("/delete/{bnum}")
	public String toDeleteArticle(@PathVariable("bnum") int bnum, Model model) {

		boardSVC.deleteArticle(bnum);
		return "redirect:/board/boardListFrm";
	}

	// 답글 작성 화면
	@GetMapping("/boardReplyFrm/{bnum}/{returnPage}")
	public String toboardReplyFrm(@PathVariable("bnum") long bnum, @ModelAttribute("returnPage") String returnPage,
			Model model) {

		Map<String, Object> map = null;
		map = boardSVC.selectArticle(bnum);
		// 맵안에서 게시글데이터를 가져와 가공한다.
		BoardVO boardVO = (BoardVO) map.get("boardVO");
		boardVO.setBtitle("[답글]" + boardVO.getBtitle());
		boardVO.setBcontent("[원글]" + boardVO.getBcontent());

		model.addAttribute("boardVO", boardVO);

		return "/board/boardReplyFrm";
	}

	// 답글 등록
	@PostMapping("/reply")
	public String toReply(@ModelAttribute BoardVO boardVO, BindingResult result) {
		if (result.hasErrors()) {
			return "/board/boardWriteFrm";
		}
		boardSVC.insertRepliedArticle(boardVO);
		return "redirect:/board/boardListFrm";
	}

}
