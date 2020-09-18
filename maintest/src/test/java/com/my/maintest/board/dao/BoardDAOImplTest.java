  
package com.my.maintest.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardFileVO;
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;
import com.my.maintest.common.paging.PageCriteria;
import com.my.maintest.common.paging.RecordCriteria;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
public class BoardDAOImplTest {

	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImplTest.class);

	@Inject
	BoardDAO boardDAO;

	@Test
	@DisplayName("게시글 등록")
	//@Disabled
	void insertArticle() {
		
		for(int i = 0 ; i < 20; i++ ) {
		String catNum = "1";
		String hidNum = "1";
		long uCode = 5;
		String bTitle = "제목1"+i;
		String bContent = "내용"+i;

		BcategoryVO BcategoryVO = new BcategoryVO();
		HeadIdCategoryVO headIdCategoryVO = new HeadIdCategoryVO();

		
		 BoardVO boardVO = new BoardVO();
		 
		 boardVO.setBcategory(BcategoryVO);
		 boardVO.getBcategory().setCatnum(catNum);
		 
		  boardVO.setHidcategory(headIdCategoryVO);
		  boardVO.getHidcategory().setHidnum(hidNum);
		  
		  boardVO.setUcode(uCode); boardVO.setBtitle(bTitle);
		  boardVO.setBcontent(bContent.getBytes());
		  boardDAO.insertArticle(boardVO);
		}
	}
	
	@Test
	@DisplayName("이전답글 bstep+1처리 답글 insert 전 처리 ")
	@Disabled
	void updateBstep() {		
		long	bgroup =185;
		long bstep = 0; //원글에 대한 답글		
		boardDAO.updateBstep(bgroup, bstep);		
	}
	@Test
	@DisplayName("게시글 답글 등록")
	@Disabled
	void insertRepliedArticle() {
		String catNum = "3";
		String hidNum =" 2";
		long uCode = 2;
		String bTitle = "8/1314:54게시글 답글 등록";
		String bContent = "1111게시글 답글 등록2";
		long	bgroup =184;
		long bstep = 0;
		long bindent = 0 ;

		boardDAO.updateBstep(bgroup, bstep);		
		
		BcategoryVO BcategoryVO = new BcategoryVO();
		HeadIdCategoryVO headIdCategoryVO = new HeadIdCategoryVO();

		
		 BoardVO boardVO = new BoardVO();		 
		 boardVO.setBcategory(BcategoryVO);
		 boardVO.getBcategory().setCatnum(catNum);		 
		  boardVO.setHidcategory(headIdCategoryVO);
		  boardVO.getHidcategory().setHidnum(hidNum);
		  
		  boardVO.setUcode(uCode); 
		  boardVO.setBtitle(bTitle);
		  boardVO.setTcontent(bContent);
		  boardVO.setBgroup(bgroup);
		  boardVO.setBstep(bstep);
		  boardVO.setBindent(bindent);
		  
		  boardDAO.insertRepliedArticle(boardVO);		
	}
	


	@Test
	@DisplayName("게시글 전체조회")
	@Disabled
	void selectArticles() {
		List<BoardVO> list = boardDAO.selectArticles();

		list.stream().forEach(System.out::println);
		Assertions.assertEquals(1, 1);
	}

	@Test
	@DisplayName("게시글 열람")
	@Disabled
	void toRead() {
		long bNum = 72;
		System.out.println(boardDAO.selectArticle(bNum));

	}

	@Test
	@DisplayName("게시글 삭제")
	@Disabled
	void toDelete() {
		long bnum = 74;
		int result = boardDAO.deleteArticle(bnum);
		Assertions.assertEquals(1, result);
	}

	@Test
	@DisplayName("조회수 갱신")
	@Disabled
	void updateBhits() {

		long bnum = 84;
		boardDAO.updateBhits(bnum);
	}

	@Test
	@DisplayName("게시글 수정")
	@Disabled
	void updateArticle() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBnum(84);
		boardVO.setBtitle("수정된 제목");
		boardVO.setTcontent("수정된 내용");
		BcategoryVO bcategory = new BcategoryVO();
		boardVO.setBcategory(bcategory);
		boardVO.getBcategory().setCatnum("1");
		boardVO.setBnickname("관리자");
		boardDAO.updateArticle(boardVO);
	}
	
	
	@Test
	@DisplayName("첨부파일 등록")
	@Disabled
	void insertFiles() {
		
		
		BoardFileVO boardFileVO = new BoardFileVO();
		
		boardFileVO.setBnum(210);
		boardFileVO.setFname("파일이름");
		boardFileVO.setFsize(1008);
		boardFileVO.setFtype("jpg");

		
		boardDAO.insertFiles(boardFileVO);
		
		
	}
	
	
	@Test
	@DisplayName("첨부파일 가져오기")
	@Disabled
	void toSelectFiles() {
		long bnum = 15;
		boardDAO.selectFiles(bnum);
			}
	
	@Test
	@DisplayName("리스트 조회 + 페이징")
	@Disabled
	void toSelectArticles1() {
		
		int recNumPerPage	= 10;	
		int reqPage = 1 ;
	RecordCriteria recordCriteria = new RecordCriteria(recNumPerPage, reqPage);
		int pagingNumsPerPage= 10;
	PageCriteria pageCriteria = new PageCriteria(reqPage, recordCriteria.getTotalRec(), pagingNumsPerPage);
	
	
		//(recordCriteria.getRecFrom(), recordCriteria.getRecTo());
				
	}
	

	@Test
	@DisplayName("보드타입 ")
	@Disabled
	void toGetBtype() {
	
		int  catnum = 1;
		
		boardDAO.selectBtype(catnum);
		
		
	}
	
	@Test
	@DisplayName("리스트조회 + 페이징 + 검색어 + 보드 카테고리  + 보드타입 ")
	@Disabled
	void toSearchKeyword() {
		int recNumPerPage	= 10;	
		int reqPage = 1 ;
	RecordCriteria recordCriteria = new RecordCriteria(recNumPerPage, reqPage);
	int pagingNumsPerPage= 10;
	PageCriteria pageCriteria = new PageCriteria(reqPage, recordCriteria.getTotalRec(), pagingNumsPerPage);
	
	
	
	String searchType = null;	
	String searchKeyword ="테스트";	
	String btype = "album";
	int catnum =2;			;
	
	
	
	List<BoardVO> list = boardDAO.selectArticlesWithKey_Blog( catnum,recordCriteria.getRecFrom(),recordCriteria.getRecTo(), searchType, searchKeyword);
	List<BoardVO> list1 = boardDAO.selectArticlesWithKey_Album( catnum,recordCriteria.getRecFrom(),recordCriteria.getRecTo(), searchType, searchKeyword);	

	
	
	System.out.println("list사이즈 " + list.size());
	System.out.println("list1사이즈 " +list1.size());
	
	
	
	//System.out.println("list1 " +list1.toString());
	
	}
	
		@Test
	@DisplayName("말머리 카테고리 조회 based on bcategory ")
		@Disabled
		void selectHId() {
			
			long catnum = 2;
			
			boardDAO.selectHeadIdCategory(catnum);
			
			
		}
		
		
		
}