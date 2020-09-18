package com.my.maintest.board.svc;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.maintest.board.dao.BCommentDAO;
import com.my.maintest.board.dao.BoardDAO;
import com.my.maintest.board.vo.BCoVoteVO;
import com.my.maintest.board.vo.BCommentVO;
import com.my.maintest.common.paging.PagingComponent;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BCommentSVCImpl implements BCommentSVC{

	
	
	@Inject
	BCommentDAO bCommentDAO;
	
	

//부모댓글 등록 (inner) 
@Override
public int insertBComment(BCommentVO bCommentVO) {	
	return bCommentDAO.insertBComment(bCommentVO);
}


//자식댓글 등록
@Transactional
@Override
public int insertReBComment(BCommentVO bCommentVO) {
	// 기 등록된 자식댓글의 BCSTEP +1 처리 
	
	
	System.out.println("bCommentVO.getBcgrp() =================" + bCommentVO.getBcgrp());
	
	
	bCommentDAO.updateBcstep(bCommentVO.getBcgrp());
	//자식댓글 등록
	int result = bCommentDAO.insertReBComment(bCommentVO);	
	return result;
}



//댓글 목록 불러오기
@Override
public List<BCommentVO> selectBComments( long bnum, long reqPage, long recNumPerPage,long  pagingNumsPerPage) {
	 List<BCommentVO> list = null;	 	 
	 PagingComponent pagingComponent = new PagingComponent(reqPage, recNumPerPage, pagingNumsPerPage);

	int RecFrom = 1;
	 	list = bCommentDAO.selectBComments(bnum,RecFrom,pagingComponent.getRecordCriteria().getRecTo());
	 	return list;
}

//댓글 수정
@Override
public int updateBccontent(BCommentVO bCommentVO) {
	return bCommentDAO.updateBccontent(bCommentVO);
}

//댓글 삭제 
@Override
public int deleteBComment(long bcnum) {
	return bCommentDAO.deleteBComment(bcnum);
}

//댓글 선호도 투표
@Transactional
@Override
public int updateVote(BCoVoteVO bCoVoteVO) {	
	int result = bCommentDAO.updateVote(bCoVoteVO);	
  bCommentDAO.updateGoodBadQnty(bCoVoteVO);
	return result ;
}




}
