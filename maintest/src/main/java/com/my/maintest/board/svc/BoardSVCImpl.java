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
	public List<HeadIdCategoryVO> selectHeadIdCategory(String catnum) {
		
		return  boardDAO.selectHeadIdCategory(catnum);
	}
	
//전체글 조회 (default)
	@Override
	public List<BoardVO> selectArticles() {
		return boardDAO.selectArticles();
	}

	// 글 열람
	@Override
	public BoardVO selectArticle(long bNum) {
		return null;
	}

	// 게시글 등록
	@Override
	public int insertArticle(BoardVO boardVO) {		
		return boardDAO.insertArticle(boardVO);
	}

	// 글 수정
	@Override
	public void updateArticle(long bNum) {

	}

	// 글 삭제
	@Override
	public void deleteArticle(long bNum) {

	}


	
	


}
