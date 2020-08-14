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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
public class BoardDAOImplTest {

	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImplTest.class);

	@Inject
	BoardDAO boardDAO;

	@Test
	@DisplayName("게시글 등록")
	@Disabled
	void insertArticle() {
		long catNum = 2;
		long hidNum = 1;
		long uCode = 0;
		String bTitle = "8/13 13:54제목1제목1제목1제목1제목1";
		String bContent = "내용111111";

		BcategoryVO BcategoryVO = new BcategoryVO();
		HeadIdCategoryVO headIdCategoryVO = new HeadIdCategoryVO();

		
		 BoardVO boardVO = new BoardVO();
		 
		 boardVO.setBcategory(BcategoryVO);
		 boardVO.getBcategory().setCatnum(catNum);
		 
		  boardVO.setHidcategory(headIdCategoryVO);
		  boardVO.getHidcategory().setHidnum(hidNum);
		  
		  boardVO.setUcode(uCode); boardVO.setBtitle(bTitle);
		  boardVO.setBcontent(bContent);
		  boardDAO.insertArticle(boardVO);
	
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
	//@Disabled
	void insertRepliedArticle() {
		long catNum = 3;
		long hidNum = 2;
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
		  boardVO.setBcontent(bContent);
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
		boardVO.setBcontent("수정된 내용");
		BcategoryVO bcategory = new BcategoryVO();
		boardVO.setBcategory(bcategory);
		boardVO.getBcategory().setCatnum(1);
		boardVO.setBnickname("관리자");
		boardDAO.updateArticle(boardVO);
	}
	
	
	@Test
	@DisplayName("첨부파일 등록")
	void insertFiles() {
		
		
		BoardFileVO boardFileVO = new BoardFileVO();
		
		boardFileVO.setBnum(210);
		boardFileVO.setFname("파일이름");
		boardFileVO.setFsize(1008);
		boardFileVO.setFtype("jpg");

		
		boardDAO.insertFiles(boardFileVO);
		
		
	}

}
