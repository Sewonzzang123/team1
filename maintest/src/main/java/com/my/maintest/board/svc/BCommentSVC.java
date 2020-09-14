package com.my.maintest.board.svc;


import java.util.List;

import com.my.maintest.board.vo.BCoVoteVO;
import com.my.maintest.board.vo.BCommentVO;

public interface BCommentSVC {
	
	//부모 댓글 등록 (inner) 
	int insertBComment(BCommentVO bCommentVO);
	
	//자식 댓글 등록 (inner) 
	int insertReBComment(BCommentVO bCommentVO);

	//댓글 목록 불러오기
	List<BCommentVO> selectBComments( long bnum, long reqPage, long recNumPerPage,long  pagingNumsPerPage);			
	

	//댓글 수정
	int updateBccontent(BCommentVO bCommentVO);
	//댓글 삭제 
	int deleteBComment(long bcnum);
	
	
	//댓글 선호도 투표
	int updateVote(BCoVoteVO bCoVoteVO);
	
}
