package com.my.maintest.board.dao;

import java.util.List;

import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;

public interface BoardDAO {

	
	//게시판 카테고리 조회 
	List<BcategoryVO> selectBcategory();
	//게시판 말머리 조회
	List<HeadIdCategoryVO> selectHeadIdCategory();
	
	// 전체 게시글 조회 (default)
	List<BoardVO> selectArticles() ;	
	
	// 게시글 열람
	BoardVO selectArticle(long bNum);	
	
	//게시글 등록
	int insertArticle(BoardVO boardVO);
	
	//게시글  수정
	void updateArticle(long  bNum);
	
	//게시글 삭제
	void deleteArticle(long  bNum);


	
	
	//글 검색

	
	  //말머리
	
	  //제목
	//댓글 작성
	//댓글 수정
	//댓글 삭제 
	
	
	//답글 작성
	//답글 수정
	//답글 삭제 
	
	
	
}
