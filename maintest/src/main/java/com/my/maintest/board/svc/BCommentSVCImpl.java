package com.my.maintest.board.svc;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.my.maintest.board.dao.BCommentDAO;
import com.my.maintest.board.vo.BCommentVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BCommentSVCImpl implements BCommentSVC{

	
	
	@Inject
	BCommentDAO bCommentDAO;
	
	

//댓글 등록 (inner) 
@Override
public int insertBComment(BCommentVO bCommentVO) {	
	return bCommentDAO.insertBComment(bCommentVO);
}

}
