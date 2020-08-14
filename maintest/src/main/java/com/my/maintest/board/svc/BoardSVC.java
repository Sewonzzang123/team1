package com.my.maintest.board.svc;

import java.util.List;

import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardFileVO;
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;

public interface BoardSVC {
	
	//게시판 카테고리 조회 
	List<BcategoryVO> selectBcategory();
	//게시판 말머리 조회
	List<HeadIdCategoryVO> selectHeadIdCategory();
	
	// 전체 게시글 조회 (default)
	List<BoardVO> selectArticles() ;	
	// 게시글 열람
	BoardVO selectArticle(long bnum);	
	//게시글 등록
	int insertArticle(BoardVO boardVO);		
	//첨부파일 등록
	int insertFiles(BoardFileVO boardFileVO);
	//게시글  수정
	int  updateArticle(BoardVO boardVO);
	//게시글 삭제
	int deleteArticle(long  bnum);
	//게시글 답글 작성
	int insertRepliedArticle(BoardVO boardVO);
	
	
	
	
	//글 검색

	
	  //말머리
	
	  //제목
	//댓글 작성
	//댓글 수정
	//댓글 삭제 
	
	
}
