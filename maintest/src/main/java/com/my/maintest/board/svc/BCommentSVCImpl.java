package com.my.maintest.board.svc;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.my.maintest.board.dao.BCommentDAO;
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
@Override
public int insertReBComment(BCommentVO bCommentVO) {
	// 기 등록된 자식댓글의 BCSTEP +1 처리 
	bCommentDAO.updateBcstep(bCommentVO.getBcgroup());
	//자식댓글 등록
	int result = bCommentDAO.insertReBComment(bCommentVO);	
	return result;
}



//댓글 목록 불러오기
@Override
public List<BCommentVO> selectBComments( long bnum, long reqPage, long recNumPerPage,long  pagingNumsPerPage) {
	 List<BCommentVO> list = null;	 	 
	 PagingComponent pagingComponent = new PagingComponent(reqPage, recNumPerPage, pagingNumsPerPage);
	 System.out.println("reqPage==============" + reqPage);
	 System.out.println("bnum==============" + bnum);
	 System.out.println("pagingComponent.getRecordCriteria().getRecFrom()==============" + pagingComponent.getRecordCriteria().getRecFrom());
	 System.out.println("pagingComponent.getRecordCriteria().getRecTo()==============" + pagingComponent.getRecordCriteria().getRecTo());
	 
	 
	 	list = bCommentDAO.selectBComments(bnum, pagingComponent.getRecordCriteria().getRecFrom(),pagingComponent.getRecordCriteria().getRecTo());
	 	return list;
}


}
