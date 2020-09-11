package com.my.maintest.board.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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

import com.my.maintest.board.dao.BoardDAO;
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
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	BoardSVC boardSVC;

	@Inject
	PagingSVC pagingSVC;

	@Inject
	BoardDAO boardDAO;

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
	public ResponseEntity<Map<String, Object>> getHeadIdCategory(@RequestBody HeadIdCategoryVO headIdCategoryVO) {
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

	// 게시글 전체보기 (페이징 + 검색)
	@GetMapping({ "/{catnum}" // 각 게시판으로 이동
			, "/boardListFrm", "/boardListFrm/{reqPage}", "/boardListFrm/{reqPage}/{searchType}/{searchKeyword}" })
	public String toSearch(

			@PathVariable(value = "catnum", required = false) Optional<Integer> catnum,
			@PathVariable(value = "reqPage", required = false) Optional<Integer> reqPage,
			@PathVariable(value = "searchType", required = false) String searchType,
			@PathVariable(value = "searchKeyword", required = false) String searchKeyword,
			@ModelAttribute SearchCriteria searchCriteria, Model model) {

		// 게시판 타입 읽어오기
		String btype = boardSVC.selectBtype(catnum.orElse(0));
		Map<String, Object> map = boardSVC.selectArticlesWithKey(catnum.orElse(0), reqPage.orElse(1), searchType,
				searchKeyword);

		model.addAttribute("pagingComponent",
				pagingSVC.getPagingComponent(reqPage.orElse(1), searchType, searchKeyword));
		model.addAttribute("articles", map.get("articles"));
		model.addAttribute("files", map.get("files"));

		if (btype.equals("album")) {
			return "/board/boardGalleryListFrm";
		}
		return "/board/boardListFrm";
	}

	// 게시글 작성 화면
	@GetMapping("/boardWriteFrm")
	public String toboardWriteFrm(@ModelAttribute BoardVO boardVO) {

		logger.info("함수 호출");
		return "/board/boardWriteFrm";
	}

	// 게시글 등록
	@PostMapping("/write")
	public String toWrite(@RequestParam String bcontent_area, @RequestParam(value = "thumbnail") String thumb_img_name,
			@ModelAttribute BoardVO boardVO) throws Exception {

		boardVO.setBcontent(bcontent_area.getBytes("UTF-8"));

		// 썸네일 등록
		if (!thumb_img_name.equals("null")) {

			String pathName = "C:\\Users\\Administrator\\git\\team1\\maintest\\src\\main\\webapp\\resources\\photo\\"
					+ thumb_img_name;
			// 썸네일로 만들 파일
			File thumb_img_file = new File(pathName);
			// 썸네일을 담을 파일
			File thumbnail = new File(
					"C:\\Users\\Administrator\\git\\team1\\maintest\\src\\main\\webapp\\resources\\photo\\썸네일_"
							+ thumb_img_name);

			// 대상 파일을 리사징 후 썸네일 파일에 저장
			if (thumb_img_file.exists()) {
				// 썸네일
				thumbnail.getParentFile().mkdir();

				Thumbnails.of(thumb_img_file).size(300, 300).toFile(thumbnail);

				boardVO.setThumbnail(Files.readAllBytes(thumbnail.toPath()));
			}
		} else {
			boardVO.setThumbnail(null);
		}

		boardSVC.insertArticle(boardVO);
		String bnum = String.valueOf(boardVO.getBnum());

		return "redirect:/board/read/" + bnum;
	}

	// 파일 첨부 화면
	@GetMapping("/fileUploadFrm")
	String toFileUploadFrm() {

		return "/board/fileUploadFrm";
	}

	// 게시글열람
	@GetMapping({ "/read/{bnum}", "/read/{bnum}/{returnPage}",
			"/read/{bnum}/{returnPage}/{searchType}/{searchKeyword}" })
	// returnPage 열람후 리스트로 이동시 돌아갈reqPage
	public String toRead(@PathVariable("bnum") Long bnum, @ModelAttribute("returnPage") String returnPage,
			@ModelAttribute SearchCriteria searchCriteria, Model model) throws UnsupportedEncodingException {

		// svc는 map 타입을 반환값으로 가짐
		Map<String, Object> map = boardSVC.selectArticle(bnum);

		BoardVO boardVO = (BoardVO) map.get("boardVO");
//		logger.info(boardVO.toString());
//		String text = ;// Convert
//		logger.info(boardVO.getBcontent().toString());
//		String test = new String(boardVO.getBcontent());
//		logger.info(test);
		boardVO.setTcontent(new String(boardVO.getBcontent(), "UTF-8"));
//		logger.info(boardVO.getTcontent());
		// 파일 타입은 List<BoardFileVO>
		List<BoardFileVO> files = (List<BoardFileVO>) map.get("files");

		model.addAttribute("boardVO", boardVO);
		model.addAttribute("files", files);
		return "/board/boardReadFrm";
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
	public String toSaveChanges(@ModelAttribute BoardVO boardVO, @RequestParam("returnPage") String returnPage,
			Model model) {
		boardSVC.updateArticle(boardVO);
		return "redirect:/board/read/" + boardVO.getBnum() + "/" + returnPage;
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
//		boardVO.setBcontent("[원글]" + boardVO.getBcontent());

		model.addAttribute("boardVO", boardVO);

		return "/board/boardReplyFrm";
	}

	// 답글 등록
	@PostMapping("/reply")
	public String toReply(@ModelAttribute BoardVO boardVO, @RequestParam("returnPage") String returnPage,
			BindingResult result) {

//		if (result.hasErrors()) {
//			return "/board/boardReplyFrm";
//		}
		boardSVC.insertRepliedArticle(boardVO);
		return "redirect:/board/boardListFrm/" + returnPage;
	}

	@ResponseBody
	@RequestMapping(value = "/setphoto", produces = "application/String;charset=utf8")
	public String set_photo(MultipartHttpServletRequest mtf) throws Exception {
		Map<String, String> result = new HashMap<>();
		logger.info("사진업로드허출");
		// 파일 태그
		String fileTag = "file";
		// 업로드 파일이 저장될 경로
		String filePath = "C:\\Users\\Administrator\\git\\team1\\maintest\\src\\main\\webapp\\resources\\photo\\";
		// 파일 이름
		MultipartFile file = mtf.getFile(fileTag);
		String fileName = file.getOriginalFilename();

		try {
			file.transferTo(new File(filePath + fileName));
		} catch (Exception e) {
			System.out.println("업로드 오류");
		}

		result.putIfAbsent("url", filePath + fileName);
		logger.info(filePath + fileName);
		return fileName;
	}

	@ResponseBody
	@RequestMapping(value = "/setpost")
	public Map<String, String> setpost(@RequestBody String content) throws Exception {
		Map<String, String> result = new HashMap<>();
		BoardFileVO boardFileVO = new BoardFileVO();
//		String strContent = content;
		byte[] byteConent = content.getBytes("UTF-8");
//	    Blob blob = connection.createBlob();//Where connection is the connection to db object. 
//	    blob.setBytes(1, byteContent);
		logger.info(content);
		logger.info(byteConent.toString());
		boardFileVO.setFdata(byteConent);
		boardDAO.setpost(boardFileVO);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/getphto")
	public String getphto() throws Exception {
		String pathName = "C:\\Users\\Administrator\\git\\team1\\maintest\\src\\main\\webapp\\resources\\photo\\이미지4.jpg";
		File file1 = new File(pathName);
		logger.info(file1.toString());

		File thumbnail = new File(
				"C:\\Users\\Administrator\\git\\team1\\maintest\\src\\main\\webapp\\resources\\photo\\썸네일test.png");

		// 이미지 파일이 존재하면
		if (file1.exists()) {
			// 썸네일
			thumbnail.getParentFile().mkdir();

			Thumbnails.of(file1).size(300, 300).toFile(thumbnail);

		}

		byte[] test1 = Files.readAllBytes(thumbnail.toPath());
		logger.info(test1.toString());
		BoardFileVO boardFileVO = new BoardFileVO();
		boardFileVO.setFdata(test1);
		boardDAO.setpost(boardFileVO);

		thumbnail.delete();

		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/testest")
	public String testest() throws Exception {

		// 썸네일 제작
		// 썸네일 사진
		String thumb_img_name = null;

		if (thumb_img_name != null) {
			String pathName = "C:\\Users\\Administrator\\git\\team1\\maintest\\src\\main\\webapp\\resources\\photo\\"
					+ thumb_img_name;
			// 썸네일로 만들 파일
			File thumb_img_file = new File(pathName);
			// 썸네일을 담을 파일
			File thumbnail = new File(
					"C:\\Users\\Administrator\\git\\team1\\maintest\\src\\main\\webapp\\resources\\photo\\썸네일_"
							+ thumb_img_name);

			// 대상 파일을 리사징 후 썸네일 파일에 저장
			if (thumb_img_file.exists()) {
				// 썸네일
				thumbnail.getParentFile().mkdir();

				Thumbnails.of(thumb_img_file).size(300, 300).toFile(thumbnail);
			}
		}
		return null;
	}

}
