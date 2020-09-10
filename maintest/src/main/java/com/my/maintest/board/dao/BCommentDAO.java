package com.my.maintest.board.dao;

import java.util.List;

import com.my.maintest.board.vo.BCommentVO;

public interface BCommentDAO {

	
	
	
	//부모 댓글 등록 (inner) 
	int insertBComment(BCommentVO bCommentVO);
	
	//자식 댓글 등록 (inner) 
	// 기 등록된 자식댓글의 BCSTEP +1 처리 
	int updateBcstep(long bcgroup);
	//등록처리
	int insertReBComment(BCommentVO bCommentVO);
	
	
//댓글 목록 불러오기
	List<BCommentVO> selectBComments(long bnum , int recFrom, int recTo);		
	
	
}
