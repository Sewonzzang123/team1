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
	@DisplayName("寃뚯떆湲� �벑濡�")
	@Disabled
	void insertArticle() {
		
		for(int i = 1 ; i < 100; i++ ) {
		String catNum = "2";
		String hidNum = "1";
		long uCode = 0;
		String bTitle = "제목"+i;
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
	}
	
	@Test
	@DisplayName("�씠�쟾�떟湲� bstep+1泥섎━ �떟湲� insert �쟾 泥섎━ ")
	@Disabled
	void updateBstep() {		
		long	bgroup =185;
		long bstep = 0; //�썝湲��뿉 ���븳 �떟湲�		
		boardDAO.updateBstep(bgroup, bstep);		
	}
	@Test
	@DisplayName("寃뚯떆湲� �떟湲� �벑濡�")
	@Disabled
	void insertRepliedArticle() {
		String catNum = "3";
		String hidNum = "2";
		long uCode = 2;
		String bTitle = "8/1314:54寃뚯떆湲� �떟湲� �벑濡�";
		String bContent = "1111寃뚯떆湲� �떟湲� �벑濡�2";
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
	@DisplayName("寃뚯떆湲� �쟾泥댁“�쉶")
	@Disabled
	void selectArticles() {
		List<BoardVO> list = boardDAO.selectArticles();

		list.stream().forEach(System.out::println);
		Assertions.assertEquals(1, 1);
	}

	@Test
	@DisplayName("寃뚯떆湲� �뿴�엺")
	@Disabled
	void toRead() {
		long bNum = 72;
		System.out.println(boardDAO.selectArticle(bNum));

	}

	@Test
	@DisplayName("寃뚯떆湲� �궘�젣")
	@Disabled
	void toDelete() {
		long bnum = 74;
		int result = boardDAO.deleteArticle(bnum);
		Assertions.assertEquals(1, result);
	}

	@Test
	@DisplayName("議고쉶�닔 媛깆떊")
	@Disabled
	void updateBhits() {

		long bnum = 84;
		boardDAO.updateBhits(bnum);
	}

	@Test
	@DisplayName("寃뚯떆湲� �닔�젙")
	@Disabled
	void updateArticle() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBnum(84);
		boardVO.setBtitle("�닔�젙�맂 �젣紐�");
		boardVO.setBcontent("�닔�젙�맂 �궡�슜");
		BcategoryVO bcategory = new BcategoryVO();
		boardVO.setBcategory(bcategory);
		boardVO.getBcategory().setCatnum("1");
		boardVO.setBnickname("愿�由ъ옄");
		boardDAO.updateArticle(boardVO);
	}
	
	
	@Test
	@DisplayName("泥⑤��뙆�씪 �벑濡�")
	@Disabled
	void insertFiles() {
		
		
		BoardFileVO boardFileVO = new BoardFileVO();
		
		boardFileVO.setBnum(210);
		boardFileVO.setFname("�뙆�씪�씠由�");
		boardFileVO.setFsize(1008);
		boardFileVO.setFtype("jpg");

		
		boardDAO.insertFiles(boardFileVO);
		
		
	}
	
	
	@Test
	@DisplayName("泥⑤��뙆�씪 媛��졇�삤湲�")
	@Disabled
	void toSelectFiles() {
		long bnum = 15;
		boardDAO.selectFiles(bnum);
			}
	
	@Test
	@DisplayName("由ъ뒪�듃 議고쉶 + �럹�씠吏�")
	@Disabled
	void toSelectArticles1() {
		
		int recNumPerPage	= 10;	
		int reqPage = 1 ;
	RecordCriteria recordCriteria = new RecordCriteria(recNumPerPage, reqPage);
		int pagingNumsPerPage= 10;
	PageCriteria pageCriteria = new PageCriteria(reqPage, recordCriteria.getTotalRec(), pagingNumsPerPage);
	
	
		boardDAO.selectArticles(recordCriteria.getRecFrom(), recordCriteria.getRecTo());
				
	}
	
	@Test
	@DisplayName("由ъ뒪�듃議고쉶 + �럹�씠吏� + 寃��깋�뼱")
	void toSearchKeyword() {
		
		int recNumPerPage	= 10;	
		int reqPage = 1 ;
	RecordCriteria recordCriteria = new RecordCriteria(recNumPerPage, reqPage);
		int pagingNumsPerPage= 10;
	PageCriteria pageCriteria = new PageCriteria(reqPage, recordCriteria.getTotalRec(), pagingNumsPerPage);
	String searchType = "T";	
	String searchKeyword ="179";
	boardDAO.selectArticlesWithKey(recordCriteria.getRecFrom(),recordCriteria.getRecTo(), searchType, searchKeyword);
		
	}
	
	
		@Test
	@DisplayName("留먮㉧由� 移댄뀒怨좊━ 議고쉶 based on bcategory ")
		void selectHId() {
			
			long catnum = 2;
			
			boardDAO.selectHeadIdCategory(catnum);
			
			
		}
		
		
		
}
