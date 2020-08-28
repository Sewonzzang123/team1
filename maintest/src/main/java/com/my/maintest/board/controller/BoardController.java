package com.my.maintest.board.controller;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.maintest.board.svc.BoardSVC;
import com.my.maintest.board.svc.PagingSVC;
import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardFileVO;
import com.my.maintest.board.vo.BoardVO;
//import com.my.maintest.board.vo.TestVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;
import com.my.maintest.board.vo.TemporaryVO;
import com.my.maintest.common.paging.SearchCriteria;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	BoardSVC boardSVC;

	@Inject
	PagingSVC pagingSVC;


	// 게시판 카테고리 조회
	@ModelAttribute("bcategoryList")
	public List<BcategoryVO> getBcategory() {
		List<BcategoryVO> bcategoryVO = null;
		bcategoryVO = boardSVC.selectBcategory();
		return bcategoryVO;
	}
	
	// 게시판 말머리 조회
	@PostMapping(value = "/headid", produces = "application/json")
	 @ResponseBody
	public  ResponseEntity<Map<String, Object>> getHeadIdCategory(
		@RequestBody HeadIdCategoryVO headIdCategoryVO) {
		System.out.println("/headid 호출");

		System.out.println("@RequestParam(\"catnum\")" + headIdCategoryVO.getCatnum());
		ResponseEntity<Map<String, Object>> res = null;

		List<HeadIdCategoryVO> list = boardSVC.selectHeadIdCategory(headIdCategoryVO.getCatnum());
		Map<String, Object> map = new HashMap<>();

		if (list != null & list.size() > 0) {

			map.put("rtcode", "00");
			map.put("hidcategory", list);
			res = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} else {
			map.put("rtcode", "01");
			res = new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}

		return res;
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
		tmpVO.setNickname("홍길동");
		tmpVO.setUcode("5");

		session.setAttribute("member", tmpVO);

		System.out.println("getSession" + session.getAttribute("member").toString());

		return "redirect:/board/0";
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

	// 게시글 전체보기  (페이징 + 검색)
	@GetMapping({ 
		"/{catnum}" // 각 게시판으로 이동
		,"/boardListFrm"
		, "/boardListFrm/{reqPage}"
		, "/boardListFrm/{reqPage}/{searchType}/{searchKeyword}" })
	public String toSearch(
			
			@PathVariable(value="catnum", required = false) Optional<Integer> catnum,
			@PathVariable(value = "reqPage", required = false) Optional<Integer> reqPage,
			@PathVariable(value = "searchType", required = false) String searchType,
			@PathVariable(value = "searchKeyword", required = false) String searchKeyword,
			@ModelAttribute SearchCriteria searchCriteria, Model model) {		
		
		// 게시판 타입 읽어오기 		
		String btype = boardSVC.selectBtype(catnum.orElse(0));
			Map<String, Object> map = boardSVC.selectArticlesWithKey( catnum.orElse(0), reqPage.orElse(1), searchType, searchKeyword);	
			


			model.addAttribute("pagingComponent",	pagingSVC.getPagingComponent(reqPage.orElse(1), searchType, searchKeyword));
			model.addAttribute("articles", map.get("articles"));
			model.addAttribute("files", map.get("files"));
			
			
			
			if(btype.equals("album") ){
				return "/board/boardGalleryListFrm";
			}			
			return "/board/boardListFrm";
	}


	
	// 게시글 작성 화면
	@GetMapping("/boardWriteFrm/{returnPage}")
	public String toboardWriteFrm(@ModelAttribute BoardVO boardVO, @ModelAttribute("returnPage") String returnPage) {

		return "/board/boardWriteFrm";
	}

	// 게시글 등록
	@PostMapping("/write")
	public String toWrite(@RequestParam("returnPage") String returnPage, @Valid  @ModelAttribute BoardVO boardVO
	 , BindingResult result
	) {
		
		 if (result.hasErrors()) {
		 return "/board/boardWriteFrm/" + returnPage;
		 }
		boardSVC.insertArticle(boardVO);
		return "redirect:/board/boardListFrm";
	}
	
	
	
	//파일 첨부 화면
	@GetMapping("/fileUploadFrm")
	 String toFileUploadFrm() {
		
		
		return "/board/fileUploadFrm";
	}
	
	

	// 게시글열람
	@GetMapping({ "/read/{bnum}/{returnPage}", 
		"/read/{bnum}/{returnPage}/{searchType}/{searchKeyword}" }) 
	// returnPage		열람후	리스트로 이동시 돌아갈reqPage																								
	public String toRead(@PathVariable("bnum") Long bnum
			, @ModelAttribute("returnPage") String returnPage,
			@ModelAttribute SearchCriteria searchCriteria, Model model) {
		
		// svc는 map 타입을 반환값으로 가짐
		Map<String, Object> map = boardSVC.selectArticle(bnum);		
	
		BoardVO boardVO = (BoardVO)map.get("boardVO");

		// 파일 타입은 List<BoardFileVO>	
		List<BoardFileVO> files = (List<BoardFileVO>)  map.get("files");
        
		model.addAttribute("boardVO", boardVO);		
		model.addAttribute("files",files);
		return "/board/boardReadFrm";
	}
	
	//첨부파일 다운로드
	@GetMapping("/file/{fid}")
	public ResponseEntity<byte[]> toGetFile(
			@PathVariable("fid") String fid
			,Model model			
			){
		 ResponseEntity<byte[]> res = null;		 
		 BoardFileVO boardFileVO = boardSVC.selectFileToDwLoad(fid);
		 
		 //응답헤더에 mimetype과 파일 사이즈 정보를 설정
		 final HttpHeaders headers = new HttpHeaders();
		 String[] mimeTypes = boardFileVO.getFtype().split("/");
		 headers.setContentType(new MediaType(mimeTypes[0], mimeTypes[1]));
		 headers.setContentLength(boardFileVO.getFsize());
		 
		 //첨부파일 명이 한글일 경우 깨짐방지
		 String fileName = null;
		 try {
			fileName = new String(boardFileVO.getFname().getBytes("utf-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		 
		 //응답헤더에 파일이 있음을 알려줌
		 headers.setContentDispositionFormData("attachment", fileName);
		 
		 res = new ResponseEntity<byte[]>(boardFileVO.getFdata(), headers, HttpStatus.OK);
				 
		return res;
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
	public String toSaveChanges(@ModelAttribute BoardVO boardVO
			,@RequestParam("returnPage") String returnPage
			, Model model) {
		boardSVC.updateArticle(boardVO);
		return "redirect:/board/read/" + boardVO.getBnum() +"/" + returnPage;
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
	public String toReply(@ModelAttribute BoardVO boardVO,
			@RequestParam("returnPage") String returnPage,
			BindingResult result) {
		
//		if (result.hasErrors()) {
//			return "/board/boardReplyFrm";
//		}
		boardSVC.insertRepliedArticle(boardVO);
		return "redirect:/board/boardListFrm/"+ returnPage;
	}

}
