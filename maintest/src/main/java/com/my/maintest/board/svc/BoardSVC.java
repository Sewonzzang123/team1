package com.my.maintest.board.svc;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardFileVO;
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;
import com.my.maintest.common.paging.PageCriteria;
import com.my.maintest.common.paging.PagingComponent;
import com.my.maintest.common.paging.RecordCriteria;

public interface BoardSVC {
	
	//게시판 카테고리 조회 
	List<BcategoryVO> selectBcategory();
	//게시판 말머리 조회
	List<HeadIdCategoryVO> selectHeadIdCategory(String catnum);
	
	// 전체 게시글 조회 (default)
	List<BoardVO> selectArticles() ;	
	// 게시글 열람
	
	
	//전체 게시글 조회 + 페이징 
	List<BoardVO> selectArticles(int reqPage, String searchType, String searchKeyword);
	
	
	//전체게시글 조회 + 페이징 + 검색어 (검색타입/검색어)
	List<BoardVO> selectArticlesWithKey(int reqPage, String searchType, String searchKeyword);
	
	// 게시글 열람
	Map<String, Object> selectArticle(long bnum);	
	
	//게시글 등록
	int insertArticle(BoardVO boardVO);		
	//첨부파일 등록
	void insertFiles(List<MultipartFile> files, long bnum);
	//게시글  수정
	int  updateArticle(BoardVO boardVO);
	//첨부파일 일부 삭제 
	int deleteFile(long fid); 
	//첨부파일 다운로드
	BoardFileVO selectFileToDwLoad(String fid);
	
	//게시글 삭제
	int deleteArticle(long  bnum);
	//게시글 답글 작성
	int insertRepliedArticle(BoardVO boardVO);
	

	

	
	


	
	  //말머리
	
	  //제목
	//댓글 작성
	//댓글 수정
	//댓글 삭제 
	
	
}
