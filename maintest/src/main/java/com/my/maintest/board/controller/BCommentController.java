package com.my.maintest.board.controller;

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

@Controller
@RequestMapping("/bcomment")
public class BCommentController {
	
	

	
	@Inject
	BCommentSVC bCommentSVC;
	
	
	
	@GetMapping("")
	public String toTest() {
		
		return "/board/boardReplyInner";
	}
	
	//댓글등록 (inner)
	@PostMapping(value={"/replyInner/{bnum}"},produces="application/json")
	public ResponseEntity<String>  toReplyInner(
			@Valid @RequestBody BCommentVO bCommentVO,
			@PathVariable("reqPage") Optional<Integer> reqPage
		,HttpServletRequest request
			) {		
		
		
		MemberVO memberVO = (MemberVO)request.getSession(false).getAttribute("member");		
		System.out.println(memberVO.getUcode());
		System.out.println(memberVO.getNickname());
		
		bCommentVO.setUcode(Integer.parseInt(memberVO.getUcode()));
		
		ResponseEntity<String> res = null;		
		int result = bCommentSVC.insertBComment(bCommentVO);

		if (result == 1) {
			res = new ResponseEntity<>("success", HttpStatus.OK);
		} else {
			res = new ResponseEntity<>("fail", HttpStatus.OK);
		}
				
		return res;
		
		


}

}
