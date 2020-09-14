package com.my.maintest.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.maintest.board.vo.BCoVoteVO;
import com.my.maintest.board.vo.BCommentVO;
import com.my.maintest.common.paging.PagingComponent;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
public class BCommentDAOImplTest {
	
	
	@Inject
	BCommentDAO bCommentDAO;
	

	//부모 댓글 등록(inner Comment)
		@Test
		@DisplayName("댓글 등록(inner Comment)")
		@Disabled
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
		
		
		
		//자식 댓글 등록
		
		@Test
		@DisplayName("자식댓글 등록(inner Comment)")
		@Disabled
		void insertbcomment1() {
			
			
	BCommentVO bCommentVO = new BCommentVO();			
			bCommentVO.setUcode(2);
			bCommentVO.setPucode(0);
			bCommentVO.setBnum(6);
			bCommentVO.setBcgrp(284);
			bCommentVO.setBccontent(" 원글 6 / 부모댓글 284에 대한 자식댓글 등록테스트 2" );
					
			bCommentDAO.updateBcstep(bCommentVO.getBcgrp());
			
			
			bCommentDAO.insertReBComment(bCommentVO);
			
			
			
		}
		
		
		//댓글 목록 불러오기 
		
		@Test 
		@DisplayName("댓글 목록불러오기")
		@Disabled
		void toGetComments() {
			
			
			 
			
			PagingComponent paging = new PagingComponent(1, 10, 10);
			
			
			List<BCommentVO> list = bCommentDAO.selectBComments(4, paging.getRecordCriteria().getRecFrom(), paging.getRecordCriteria().getRecTo());
			
			System.out.println("리스트 사이즈 " + list.size());
			
			for(BCommentVO vo: list) {
				
				
			System.out.println(vo.toString());
			}
			
		}
		
		
		//댓글 수정
		@Test 
		@DisplayName("댓글 수정")
		@Disabled
		void toMod() {
			
			BCommentVO bCommentVO = new BCommentVO();
			bCommentVO.setBccontent("유대석");
			bCommentVO.setBcnum(7);
			
			bCommentDAO.updateBccontent(bCommentVO);
			
			
			
			
		}
		
		
		
		@Test 
		@DisplayName("댓글 삭제")
		@Disabled
		void todel() {
			long bcnum = 13;
						bCommentDAO.deleteBComment(bcnum);
			
					}
		
		
		@Test
		@DisplayName("선호도 투표")
		void toVote() {
			
																			
		 long				bnum = 7;																//	bnum number not null,
		long				bcnum = 73;														//	bcnum number not null,
			 long				ucode = 4;															//	ucode number not null unique,
			 String 		voted = "good";																//	voted varchar(10) check(voted = 'good' or voted='bad'),
				
			 BCoVoteVO bCoVoteVO = new BCoVoteVO();
			 bCoVoteVO.setBnum(bnum);
			 bCoVoteVO.setBcnum(bcnum);
			 bCoVoteVO.setUcode(ucode);
			 bCoVoteVO.setVoted(voted);
			bCommentDAO.updateVote(bCoVoteVO);
			
			 
			 
		}
	

}
