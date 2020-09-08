package com.my.maintest.board.controller;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
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
import com.my.maintest.common.paging.PagingComponent;
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
	
	// 게시판 말머리 조회(ajax)
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



	// 게시글 목록보기  (페이징 + 검색 + 게시판 카테고리)
	@GetMapping({
		""
		,"/{catnum}" // 각 게시판으로 이동
		, "/{catnum}/{reqPage}"
		, "/{catnum}/{reqPage}/{searchType}/{searchKeyword}" })
	public String toSearch(			
			@PathVariable(value="catnum", required = false) Optional<Integer> catnum,
			@PathVariable(value = "reqPage", required = false) Optional<Integer> reqPage,
			@PathVariable(value = "searchType", required = false) String searchType,
			@PathVariable(value = "searchKeyword", required = false) String searchKeyword,
			@ModelAttribute SearchCriteria searchCriteria, Model model) {		
		
		// 게시판 타입 읽어오기 		
		BcategoryVO bcategoryVO = boardSVC.selectBtype(catnum.orElse(0));
		
		
		
		//표시할 게시글 수
		long recNumPerPage = 10;
		
		Map<String,Object> map = boardSVC.selectArticlesWithKey(bcategoryVO.getBtype(), catnum.orElse(0), reqPage.orElse(1), recNumPerPage, searchType, searchKeyword);	
		
		model.addAttribute("boardVO",(List<BoardVO>)map.get("list"));
		model.addAttribute("pagingComponent",(PagingComponent)map.get("pagingComponent"));
		model.addAttribute("bcategoryVO", bcategoryVO);
		return 
					"/board/boardMainFrm";
			}


	
	// 게시글 작성 화면
	@GetMapping("/boardWriteFrm/{catnum}/{returnPage}")
	public String toboardWriteFrm(
			@ModelAttribute BoardVO boardVO, 
			@PathVariable ("catnum")  int catnum		
			, @ModelAttribute("returnPage") String returnPage
			,Model model
			) {
		
	
		
		
	// 게시판 타입 읽어오기 		
			BcategoryVO bcategoryVO = boardSVC.selectBtype(catnum);
			bcategoryVO.setCatname("글쓰기");			
			model.addAttribute("bcategoryVO", bcategoryVO);
		
		return "/board/boardWriteFrm";
	}

	// 게시글 등록
	@PostMapping("/write")
	public String toWrite(
			 HttpServletRequest request
			,@RequestParam("returnPage") String returnPage
			, @Valid  @ModelAttribute BoardVO boardVO
	 , BindingResult result
	 ,Model model
	) {
		
		 if (result.hasErrors()) {
		 return "/board/boardWriteFrm";		 		 
		 }
		boardSVC.insertArticle(boardVO);
		
		return "redirect:/board";
	}
	

	
	

	// 게시글열람
	@GetMapping({
		"/read/{bnum}",
		"/read/{bnum}/{returnPage}", 
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
	
	
	//TODO 게시글 임시 저장 구현
	//TODO 게시글 댓글 구현
	//TODO 각 VO 유효성 검사 구현
	
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
	@GetMapping("/deleteFile/{fid}/{isthumb}")
	@ResponseBody
	public ResponseEntity<String> toDeleteFile(
			@PathVariable("fid") long fid
			,@PathVariable(value="isthumb", required = false) String  isThumb			
			, Model model) {
		ResponseEntity<String> responseEntity = null;
		
		long result = boardSVC.deleteFile(fid,isThumb);

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
