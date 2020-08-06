package com.my.maintest.board.dao;

import java.util.List;

import com.my.maintest.board.vo.BoardVO;

public interface BoardDAO {

	// 전체글 조회 (default)
	List<BoardVO> selectArticles() ;	
	// 글 열람
	BoardVO selectArticle(long bNum);	
	//글 등록
	int insertArticle(BoardVO boardVO);	
	//글 수정
	void updateArticle(long  bNum);
	//글 삭제
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
