package com.my.maintest.board.dao;

import javax.inject.Inject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.maintest.board.vo.BCommentVO;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
public class BCommentDAOImplTest {
	
	
	@Inject
	BCommentDAO bCommentDAO;
	

	//댓글 등록(inner Comment)
		@Test
		@DisplayName("댓글 등록(inner Comment)")
		void insertbcomment() {
			
			BCommentVO bCommentVO = new BCommentVO();
			
			bCommentVO.setUcode(2);
			bCommentVO.setPucode(4);
			bCommentVO.setBnum(7);
			bCommentVO.setBccontent(" 으아아아아아4 " );
					
			bCommentDAO.insertBComment(bCommentVO);
			
			
			System.out.println(bCommentVO.getPnickname());
			System.out.println(bCommentVO.getNickname());
		}
		
	

}
