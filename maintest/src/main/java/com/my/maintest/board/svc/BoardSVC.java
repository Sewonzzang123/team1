package com.my.maintest.board.svc;

import java.util.List;

import com.my.maintest.board.vo.BoardVO;

public interface BoardSVC {
	
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
