package com.my.maintest.board.svc;

import java.util.List;

import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;

public interface BoardSVC {
	
	


	//게시판 카테고리 조회 
	List<BcategoryVO> selectBcategory();
	//게시판 말머리 조회
	List<HeadIdCategoryVO> selectHeadIdCategory();
	
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

}
