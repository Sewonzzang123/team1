package com.my.maintest.board.svc;

import com.my.maintest.common.paging.PageCriteria;
import com.my.maintest.common.paging.PagingComponent;
import com.my.maintest.common.paging.RecordCriteria;


public interface PagingSVC {
	
	
	//페이징 _  게시글 한페이지에 표현될 게시글 수 산출 + 검색어 
	RecordCriteria getRecCriteria(String btype, long catnum, int reqPage,long recNumPerPage, String searchType, String searchKeyword);
	//페이징 _ 한페이지에 표현될 페이징 번호 산출
	PageCriteria getPageCriteria(long reqPage, RecordCriteria recordCriteria);	
//페이징 wrapper _   (레코드 / 페이징 / 검색어) 요소 wrapping 
	PagingComponent getPagingComponent(String btype, long catnum, int reqPage, long recNumPerPage, String searchType, String searchKeyword);

	
}	
