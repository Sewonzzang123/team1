package com.my.maintest.board.svc;


import java.util.List;

import com.my.maintest.board.vo.BCommentVO;

public interface BCommentSVC {
	
	//부모 댓글 등록 (inner) 
	int insertBComment(BCommentVO bCommentVO);
	
	//자식 댓글 등록 (inner) 
	int insertReBComment(BCommentVO bCommentVO);

	//댓글 목록 불러오기
	List<BCommentVO> selectBComments( long bnum, long reqPage, long recNumPerPage,long  pagingNumsPerPage);			
	
	
	
	
}
