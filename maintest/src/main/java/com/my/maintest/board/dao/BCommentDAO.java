package com.my.maintest.board.dao;

import com.my.maintest.board.vo.BCommentVO;

public interface BCommentDAO {

	
	
	//댓글 등록 (inner) 
	int insertBComment(BCommentVO bCommentVO);		
}
