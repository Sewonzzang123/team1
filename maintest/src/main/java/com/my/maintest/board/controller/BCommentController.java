package com.my.maintest.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.maintest.board.svc.BCommentSVC;
import com.my.maintest.board.vo.BCommentVO;
import com.my.maintest.member.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/bcomment")
public class BCommentController {
	
	//*  하나의 페이징 넘버 페이지에 보여줄 레코드수 : recNumPerPage
	final long  REC_NUM_PER_PAGE = 10;	
	//한 페이지에 보여줄 페이징 수 
	final long  PAGING_NUM_PER_PAGE = 10;
	
	@Inject
	BCommentSVC bCommentSVC;
	
	
	
	@GetMapping("")
	public String toTest() {		
		return "/board/boardReplyInner";
	}
	
	//부모 댓글등록 (inner)
	@PostMapping(value={"/replyP", "/replyP/{reqPage}"} ,produces="application/json")
	public ResponseEntity<Map>  toReplyInner(
			@Valid @RequestBody BCommentVO bCommentVO,			
			@PathVariable("reqPage") Optional<Integer> reqPage
		,HttpServletRequest request
			) {				
		//1.작성자 UCODE 셋팅
		MemberVO memberVO = (MemberVO)request.getSession(false).getAttribute("member");				
		bCommentVO.setUcode(Integer.parseInt(memberVO.getUcode()));		
		
		//2.등록 요청		
		ResponseEntity<Map> res = null;		
		int result = bCommentSVC.insertBComment(bCommentVO);
	
	 
		Map<String, Object> map = new HashMap<>();	
		if (result == 1) {			
			//등록 성공시 댓글 LIST 정보 읽어서반환 			
			System.out.println(" bCommentVO.getBnum()  == = = = " + bCommentVO.getBnum());
			
			List<BCommentVO> list = bCommentSVC.selectBComments(bCommentVO.getBnum(),reqPage.orElse(1), REC_NUM_PER_PAGE, PAGING_NUM_PER_PAGE);
			
			
			
			System.out.println(list.size());
			
			
			
			map.put("result", "OK");
			map.put("list", list);
			res = new ResponseEntity<Map>(map, HttpStatus.OK);
		} else {			
			map.put("result", "NG");
			res = new ResponseEntity<Map>(map, HttpStatus.OK);
			}				
		return res;		
}
	
	
	//자식댓글 등록 
	@PostMapping(value="/replyC/{reqPage}", produces="application/json")
	public ResponseEntity<Map> toReplyInnerOnCmt(
			@Valid @RequestBody BCommentVO bCommentVO,
			@PathVariable("reqPage") Optional<Integer> reqPage
			,HttpServletRequest request		
			){
		ResponseEntity<Map> res = null;	
		int result = bCommentSVC.insertReBComment(bCommentVO);		
		Map<String, Object> map = new HashMap<>();
		if(result >= 0) {
			
			List<BCommentVO> list = bCommentSVC.selectBComments(bCommentVO.getBnum(),reqPage.orElse(1), REC_NUM_PER_PAGE, PAGING_NUM_PER_PAGE);
			map.put("result", "OK");
			map.put("list", list);			
			res = new ResponseEntity<Map>(map,HttpStatus.OK);			
		}else {
			map.put("result", "NG");
			res = new ResponseEntity<Map>(map,HttpStatus.OK);		
		}
		return res;

}
}