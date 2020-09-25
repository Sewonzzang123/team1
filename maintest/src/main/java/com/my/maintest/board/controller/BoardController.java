package com.my.maintest.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.maintest.admin.svc.AdminSVC;
import com.my.maintest.board.svc.BCommentSVC;
import com.my.maintest.board.svc.BoardSVC;
import com.my.maintest.board.svc.PagingSVC;
import com.my.maintest.board.vo.BCommentVO;
import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardFileVO;
import com.my.maintest.board.vo.BoardVO;
//import com.my.maintest.board.vo.TestVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;
import com.my.maintest.common.paging.PagingComponent;
import com.my.maintest.common.paging.SearchCriteria;
import com.my.maintest.item.svc.ItemListSVC;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ListVO;
import com.my.maintest.item.vo.ListingVO;
import com.my.maintest.member.vo.MemberVO;
import com.my.maintest.mypage.svc.MypageSVC;

import lombok.extern.slf4j.Slf4j;

// TODO 관리자 페이지 게시판 추가 구현
// TODO 내가쓴글 클릭시 게시글 열람 가능하게 구현


@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	// * 하나의 페이징 넘버 페이지에 보여줄 레코드수 : recNumPerPage
	final long REC_NUM_PER_PAGE = 10;
	// 한 페이지에 보여줄 페이징 수
	final long PAGING_NUM_PER_PAGE = 10;

	Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	BoardSVC boardSVC;

	@Inject
	AdminSVC adminSVC;

	@Inject
	PagingSVC pagingSVC;

	@Inject
	BCommentSVC bCommentSVC;

	@Inject
	ItemListSVC itemListSVC;

	@Inject
	MypageSVC mypageSVC;

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
	public ResponseEntity<Map<String, Object>> getHeadIdCategory(@RequestBody HeadIdCategoryVO headIdCategoryVO) {
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

	// 사진등록(ajax)
	@RequestMapping(value = "/setphoto", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String set_photo(MultipartHttpServletRequest mtf) throws Exception {
		log.info("사진등록 ajax 호출 ");
		logger.info("mtf" + mtf.getFile("file").getOriginalFilename());
		// 임시저장위치
		File filePath = new File("C:\\tmpServerRepo\\photo\\tmp\\");
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		// 파일 태그
		String fileTag = "file";
		System.out.println("filePath.getPath() == " + filePath.getPath());
		StringBuilder sb = new StringBuilder(filePath.getPath());
		// 파일 이름
		MultipartFile file = mtf.getFile(fileTag);
		String fileName = file.getOriginalFilename();
		sb.append("\\" + fileName);
		FileOutputStream fos = null;
		File newFile = new File(sb.toString());
		fos = new FileOutputStream(newFile);
		fos.write(mtf.getFile(fileTag).getBytes());
		fos.close();
		return fileName;
	}

	// 게시글 목록보기 (페이징 + 검색 + 게시판 카테고리)
	@GetMapping({ "", "/{catnum}" // 각 게시판으로 이동
			, "/{catnum}/{reqPage}", "/{catnum}/{reqPage}/{searchType}/{searchKeyword}" })
	public String toSearch(@PathVariable(value = "catnum", required = false) Optional<Integer> catnum,
			@PathVariable(value = "reqPage", required = false) Optional<Integer> reqPage,
			@PathVariable(value = "searchType", required = false) String searchType,
			@PathVariable(value = "searchKeyword", required = false) String searchKeyword,
			@ModelAttribute SearchCriteria searchCriteria, Model model) {
		// 게시판 타입 읽어오기
		BcategoryVO bcategoryVO = boardSVC.selectBtype(catnum.orElse(0));
		// 표시할 게시글 수
		long recNumPerPage = 10;
		Map<String, Object> map = boardSVC.selectArticlesWithKey(bcategoryVO.getBtype(), catnum.orElse(0),
				reqPage.orElse(1), recNumPerPage, searchType, searchKeyword);
		model.addAttribute("boardVO", map.get("list"));
		model.addAttribute("pagingComponent", (PagingComponent) map.get("pagingComponent"));
		model.addAttribute("bcategoryVO", bcategoryVO);
		model.addAttribute("bcatelist", adminSVC.getCate());
		return "/board/boardMainFrm";
	}

	// 게시글 작성 화면
	@GetMapping({ "/boardWriteFrm/{catnum}/{returnPage}", "/boardWriteFrm/{catnum}/{returnPage}/{lnum}" })
	public String toboardWriteFrm(@ModelAttribute BoardVO boardVO, @PathVariable("catnum") int catnum,
			@ModelAttribute("returnPage") String returnPage, @PathVariable(value = "lnum", required = false) String lnum,
			HttpServletRequest request, Model model) {
		// 게시판 타입 읽어오기
		BcategoryVO bcategoryVO = boardSVC.selectBtype(catnum);
		bcategoryVO.setCatname("글쓰기");
		model.addAttribute("bcategoryVO", bcategoryVO);
		model.addAttribute("catnum", catnum);
		if (lnum != null) {
			model.addAttribute("lname", itemListSVC.getListname(Long.valueOf(lnum)));
			model.addAttribute("lnum", lnum);
		}
		return "/board/boardWriteFrm";
	}

	// 게시글 등록
	@PostMapping(value = { "/write", "/write/{catnum}" })
	public String toWrite(@RequestParam(value = "catnum") Optional<Integer> catnum,
			@Valid @ModelAttribute BoardVO boardVO, BindingResult result, HttpServletRequest request, Model model) {
		String error = null;
		if (boardVO.getTcontent().trim().length() == 0) {
			error = "최소 한글자 이상 입력해주세요.";
			model.addAttribute("error", error);
			return "/board/boardWriteFrm";
		}	;
		if (result.hasErrors()) {
			return "/board/boardWriteFrm";
		}
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
		boardVO.setUcode(Long.parseLong(memberVO.getUcode()));
		boardSVC.insertArticleWithImg(boardVO);
		return "redirect:/board/read/" + boardVO.getBcategory().getCatnum() + "/" + boardVO.getBnum();
	}

//리스트 첨부하기 클릭
	@GetMapping("/loadListForm/{catnum}/{returnPage}")
	public String loadListForm(@PathVariable(value = "catnum", required = false) Integer catnum,
			@PathVariable(value = "returnPage", required = false) Integer returnPage, Model model, HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		if (session != null) {
			session.removeAttribute("returnPage");
			session.removeAttribute("catnum");
		}
		session.setAttribute("returnPage", returnPage);
		session.setAttribute("catnum", catnum);
		String ucode = memberVO.getUcode();
		List<ListVO> listVO = null;
		List<ListingVO> listing = null;
		listVO = boardSVC.myList(ucode);
		long lnum = listVO.get(0).getLnum();
		listing = itemListSVC.loadListing(lnum);
		List<ItemCategoryVO> icategory = itemListSVC.selectAllCategory();
		model.addAttribute("lnum", lnum);
		model.addAttribute("icategory", icategory);
		model.addAttribute("listVO", listVO);
		model.addAttribute("listingVO", listing);
		return "/board/loadListForm";
	}

	@GetMapping("/loadListForm/{lnum}")
	public String getListing(@PathVariable("lnum") long lnum, HttpSession session, Model model) {
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String ucode = memberVO.getUcode();
		List<ListVO> listVO = null;
		List<ListingVO> listing = null;
		listVO = itemListSVC.loadList(ucode);
		listing = itemListSVC.loadListing(lnum);
		List<ItemCategoryVO> icategory = itemListSVC.selectAllCategory();
		model.addAttribute("lnum", lnum);
		model.addAttribute("icategory", icategory);
		model.addAttribute("listVO", listVO);
		model.addAttribute("listingVO", listing);
		return "/board/loadListForm";
	}

	// 게시글열람
	@GetMapping({ "/read/{catnum}/{bnum}", "/read/{catnum}/{bnum}/{returnPage}",
			"/read/{catnum}/{bnum}/{returnPage}/{searchType}/{searchKeyword}" })
	// returnPage 열람후 리스트로 이동시 돌아갈reqPage
	public String toRead(@PathVariable("bnum") Long bnum, @PathVariable("catnum") int catnum,
			@ModelAttribute("returnPage") String returnPage, @ModelAttribute SearchCriteria searchCriteria, Model model)
			throws UnsupportedEncodingException {
		// 게시판 타입 읽어오기
		BcategoryVO bcategoryVO = boardSVC.selectBtype(catnum);
		// 조회수 업데이트필요 여부
		boolean toUphits = true;
		// svc는 map 타입을 반환값으로 가짐
		Map<String, Object> map = boardSVC.selectArticle(toUphits, bnum);
		BoardVO boardVO = (BoardVO) map.get("boardVO");		
		if (boardVO.getBcontent() != null) {
			boardVO.setTcontent(new String(boardVO.getBcontent(), "UTF-8"));// 정민
		}		
		// 파일 타입은 List<BoardFileVO>
		List<BoardFileVO> files = ((List<BoardFileVO>) map.get("files"));
		// inner댓글 리스트 불러오기
		List<BCommentVO> list = bCommentSVC.selectBComments(bnum, 1, REC_NUM_PER_PAGE, PAGING_NUM_PER_PAGE);
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("files", files);
		model.addAttribute("innerList", list);
		model.addAttribute("bcategoryVO", bcategoryVO);
		return "/board/boardReadFrm";
	}

	@GetMapping("/downloadListForm/{bnum}")
	public String downloadListForm(@PathVariable("bnum") Long bnum, Model model) {
		List<ItemCategoryVO> itemCategoryVO = itemListSVC.selectAllCategory();
		List<ListingVO> listingVO = boardSVC.loadListing(bnum);
		System.out.println(listingVO.toString());
		model.addAttribute("listing", listingVO);
		model.addAttribute("category", itemCategoryVO);
		model.addAttribute("bnum", bnum);
		return "board/downloadListForm";
	}

	@PostMapping(value = "/saveList/{lname}")
	public String inum(@RequestParam(value = "bnum", required = true) Long bnum,
			@RequestParam(value = "checked", required = false) List<String> checked,
			@PathVariable(value = "lname") String lname, HttpSession session, Model model) {
		List<Map<String, String>> listing = new ArrayList<Map<String, String>>();
		List<ListingVO> getListing = boardSVC.loadListing(bnum);
		for (int i = 0; i < getListing.size(); i++) {
			Map<String, String> itemMap = new HashMap<>();
			itemMap.put("i_num", getListing.get(i).getI_num());
			itemMap.put("i_name", getListing.get(i).getI_name());
			itemMap.put("icount", String.valueOf(getListing.get(i).getIcount()));
			itemMap.put("icategory", String.valueOf(getListing.get(i).getCa_num()));
			itemMap.put("checked", checked.get(i).toString());
			listing.add(i, itemMap);
		}
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String ucode = memberVO.getUcode();
		ListVO listVO = new ListVO();
		listVO.setLname(lname);
		listVO.setMemberVO(memberVO);
		itemListSVC.listNameInsert(listVO);
		itemListSVC.insertListing(listVO, listing);
		model.addAttribute("mylist", mypageSVC.mylist(1, ucode));
		model.addAttribute("paging", mypageSVC.mylist_paging(1, ucode));
		// 마이페이지에 리스트 화면으로 이동
		return "/mypage/mylist";

	}

	// 첨부파일 다운로드
	@GetMapping("/file/{fid}")
	public ResponseEntity<byte[]> toGetFile(@PathVariable("fid") String fid, Model model) {
		ResponseEntity<byte[]> res = null;
		BoardFileVO boardFileVO = boardSVC.selectFileToDwLoad(fid);
		// 응답헤더에 mimetype과 파일 사이즈 정보를 설정
		final HttpHeaders headers = new HttpHeaders();
		String[] mimeTypes = boardFileVO.getFtype().split("/");
		headers.setContentType(new MediaType(mimeTypes[0], mimeTypes[1]));
		headers.setContentLength(boardFileVO.getFsize());
		// 첨부파일 명이 한글일 경우 깨짐방지
		String fileName = null;
		try {
			fileName = new String(boardFileVO.getFname().getBytes("utf-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 응답헤더에 파일이 있음을 알려줌
		headers.setContentDispositionFormData("attachment", fileName);
		res = new ResponseEntity<byte[]>(boardFileVO.getFdata(), headers, HttpStatus.OK);
		return res;
	}

	// 첨부파일 일부 삭제
	@GetMapping("/deleteFile/{fid}/{isthumb}")
	@ResponseBody
	public ResponseEntity<String> toDeleteFile(@PathVariable("fid") long fid,
			@PathVariable(value = "isthumb", required = false) String isThumb, Model model) {
		ResponseEntity<String> responseEntity = null;
		long result = boardSVC.deleteFile(fid, isThumb);
		if (result == 1) {
			responseEntity = new ResponseEntity<>("success", HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>("fail", HttpStatus.OK);
		}
		return responseEntity;
	}

	// 게시글수정 화면
	@GetMapping("/boardModifyFrm/{catnum}/{bnum}")
	public String toModifyFrm(@PathVariable("catnum") int catnum, @PathVariable("bnum") int bnum, Model model)
			throws UnsupportedEncodingException {
		BcategoryVO bcategoryVO = boardSVC.selectBtype(catnum);
		// 조회수 업데이트필요 여부
		boolean toUphits = false;
		Map<String, Object> map = boardSVC.selectArticle(toUphits, bnum);
		BoardVO boardVO = (BoardVO) map.get("boardVO");
		// 파일 타입은 List<BoardFileVO>
		List<BoardFileVO> files = ((List<BoardFileVO>) map.get("files"));
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("files", files);
		logger.info(boardVO.toString());
		return "board/boardModifyFrm";
	}

//게시글 수정처리
	@PostMapping("/modify")
	public String toModify(@RequestParam(value = "thumbnail") String thumb_img_name,
			@Valid @ModelAttribute BoardVO boardVO, BindingResult result) throws IOException {
		logger.info("수정호출");
		logger.info(boardVO.toString());
		if (result.hasErrors()) {
			return "/board/boardModifyFrm";
		}
		boardSVC.updateArticle(boardVO);
		return "redirect:/board/read/" + boardVO.getBcategory().getCatnum() + "/" + boardVO.getBnum();
	}
	// 게시글삭제
	@GetMapping("/delete/{bnum}")
	public String toDeleteArticle(@PathVariable("bnum") int bnum, Model model) {
		boardSVC.deleteArticle(bnum);
		return "redirect:/board";
	}

	// 답글 작성 화면
	@GetMapping(value = { "/boardReplyFrm/{bnum}", "/boardReplyFrm/{bnum}/{returnPage}" })
	public String toboardReplyFrm(@PathVariable("bnum") long bnum, @ModelAttribute("returnPage") String returnPage,
			Model model) {
		Map<String, Object> map = null;
		boolean toUphits = false;
		// svc는 map 타입을 반환값으로 가짐
		map = boardSVC.selectArticle(toUphits, bnum);
		// 맵안에서 게시글데이터를 가져와 가공한다.
		BoardVO boardVO = (BoardVO) map.get("boardVO");
		boardVO.setBtitle("[답글]" + boardVO.getBtitle());
		boardVO.setTcontent("[원글]" + boardVO.getBtitle());
		model.addAttribute("boardVO", boardVO);
		return "/board/boardReplyFrm";
	}

	// 답글 등록
	@PostMapping("/reply")
	public String toReply(@Valid @ModelAttribute BoardVO boardVO, BindingResult result,
			 @ModelAttribute("returnPage") String returnPage,
			@RequestParam("bcategory.catnum") String catnum, HttpServletRequest request) {
		
		logger.info(boardVO.toString());
		
		
		if (result.hasErrors()) {
			
			logger.info(""+	result.getAllErrors());
		
			return "/board/boardReplyFrm";
		}
		
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
		boardVO.setUcode(Long.parseLong(memberVO.getUcode()));
		boardSVC.insertRepliedArticle(boardVO);
		
		return "redirect:/board/" + catnum;
	}

}