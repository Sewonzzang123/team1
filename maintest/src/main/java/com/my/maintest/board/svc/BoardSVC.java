package com.my.maintest.board.svc;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardFileVO;
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;
import com.my.maintest.item.vo.ListVO;
import com.my.maintest.item.vo.ListingVO;

public interface BoardSVC {
	
	//게시판 카테고리 조회 
	List<BcategoryVO> selectBcategory();
	//게시판 말머리 조회
	List<HeadIdCategoryVO> selectHeadIdCategory(String catnum);	
	// 전체 게시글 조회 (default)
	List<BoardVO> selectArticles() ;	
	// 게시글 열람		
	//전체 게시글 조회 + 페이징 
	List<BoardVO> selectArticles(String btype,int catnum, int reqPage, long recNumPerPage,String searchType, String searchKeyword);	
	//게시판 타입 조회 
	BcategoryVO selectBtype(long catnum);	
	//전체게시글 조회 + 페이징 + 검색어  (게시판타입 / 레코드 범위 / 검색타입 / 검색어)
	Map<String,Object> selectArticlesWithKey(String btype,long catnum, int reqPage,long recNumPerPage, String searchType, String searchKeyword);	
	//갤러리게시판(catnum:2)  썸네일 첨부파일 정보 불러오기 
	 List<BoardFileVO> selectThumbnailFiles(int catnum);	 
	 // 게시글 열람
	Map<String, Object> selectArticle(long bnum);		
	//게시글 등록(게시글 원글 and 답글)
	long insertArticle(BoardVO boardVO);
//게시글 등록(text + img  (썸네일생성 / 원본 파일 저장)
	long insertArticleWithImg(BoardVO boardVO);	
	//썸네일 경로 저장
	int updateThumbPath(BoardVO boardVO);
	//첨부파일 등록 +  썸네일 생성 및 등록	
	void insertFiles(List<MultipartFile> files, long bnum, String catnum);	
	//게시글  수정
	long  updateArticle(BoardVO boardVO);
	//첨부파일 일부 삭제 
	long deleteFile(long fid, String isThumb); 
	//첨부파일 다운로드
	BoardFileVO selectFileToDwLoad(String fid);	
	//게시글 삭제
	long deleteArticle(long  bnum);
	//게시글 답글 작성
	long insertRepliedArticle(BoardVO boardVO);
	//파일 처리 (썸네일 및 원본 사진 저장 디렉토리 생성 및 파일 복사)
	 boolean  handleFiles(long _bnum, String thumbnailName);
	
	//리스트 열람
	List<ListingVO> loadListing(long bnum);
	List<ListVO> myList(String ucode);

	
}
