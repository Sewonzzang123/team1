package com.my.maintest.board.svc;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.my.maintest.board.dao.BoardDAO;
import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;

@Service
public class BoardSVCImpl implements BoardSVC {

	
	@Inject
	BoardDAO boardDAO;
	
	//게시판 카테고리 조회 
	@Override
	public List<BcategoryVO> selectBcategory() {		 
		return boardDAO.selectBcategory();
	}
	//게시판 말머리 조회
	@Override
	public List<HeadIdCategoryVO> selectHeadIdCategory() {
		
		return  boardDAO.selectHeadIdCategory();
	}
	
//전체글 조회 (default)
	@Override
	public List<BoardVO> selectArticles() {
		return boardDAO.selectArticles();
	}

	// 게시글 열람
	@Override
	public BoardVO selectArticle(long bnum) {		
		boardDAO.updateBhits(bnum);
		return boardDAO.selectArticle(bnum);
	}

	// 게시글 등록
	@Override
	public int insertArticle(BoardVO boardVO) {		
		return boardDAO.insertArticle(boardVO);
	}

	// 게시글 수정
	@Override
	public int updateArticle(BoardVO boardVO) {

		return boardDAO.updateArticle(boardVO);
	}

	// 게시글 삭제
	@Override
	public int deleteArticle(long bnum) {

		return boardDAO.deleteArticle(bnum);
	}
	
	//게시글 답글 작성
	@Override
	public int insertRepliedArticle(BoardVO boardVO) {	
		boardDAO.updateBstep(boardVO.getBgroup(), boardVO.getBstep());
		return boardDAO.insertRepliedArticle(boardVO);
	}


	
	


}
