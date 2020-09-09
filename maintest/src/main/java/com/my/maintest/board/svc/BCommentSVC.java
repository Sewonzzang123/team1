package com.my.maintest.board.svc;


import com.my.maintest.board.vo.BCommentVO;

public interface BCommentSVC {
	
	//댓글 등록 (inner) 
	int insertBComment(BCommentVO bCommentVO);			
	
	
}
