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
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class BoardDAOImplTest {

	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImplTest.class);
	
	@Inject 
	BoardDAO boardDAO;
	
	
	@Test
	@DisplayName("게시글 등록")
	@Disabled
	void insertArticle() {	
		long catNum = 4;
		long hidNum = 1;
		long uCode = 0;
		String bTitle ="제목1";
		String bContent ="내용111111";
		
		BcategoryVO BcategoryVO = new BcategoryVO();
		HeadIdCategoryVO headIdCategoryVO = new HeadIdCategoryVO();
		
		/*
		 * BoardVO boardVO = new BoardVO(); boardVO.setbCategory(BcategoryVO);
		 * boardVO.getbCategory().setCatNum(catNum);
		 * 
		 * boardVO.setHidCategory(headIdCategoryVO);
		 * boardVO.getHidCategory().setHidNum(hidNum);
		 * 
		 * boardVO.setuCode(uCode); boardVO.setbTitle(bTitle);
		 * boardVO.setbContent(bContent); boardDAO.insertArticle(boardVO);
		 */
	}
	
	
	@Test
	@DisplayName("게시글 전체조회")
	@Disabled
void selectArticles() {
		List<BoardVO> list =	boardDAO.selectArticles();		

		
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
	void updateBhits () {
		
		long bnum = 84;
		boardDAO.updateBhits(bnum);
	}
	
	@Test
	@DisplayName("게시글 수정")
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
	
}
