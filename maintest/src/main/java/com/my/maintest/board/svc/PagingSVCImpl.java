package com.my.maintest.board.svc;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.my.maintest.board.dao.PagingDAO;
import com.my.maintest.common.paging.PageCriteria;
import com.my.maintest.common.paging.PagingComponent;
import com.my.maintest.common.paging.RecordCriteria;
import com.my.maintest.common.paging.SearchCriteria;

@Service
public class PagingSVCImpl implements PagingSVC{
	
	@Inject 
	PagingDAO pagingDAO;
	
	//페이징 _  게시글 한페이지에 표현될 게시글 수 산출 + 검색어 
	@Override
	public RecordCriteria getRecCriteria(int reqPage,String searchType, String searchKeyword ) {
	//한페이지에 보여줄 게시글 수 
	long recNumPerPage = 10; 
	RecordCriteria recordCriteria = new RecordCriteria(recNumPerPage, reqPage);
	//게시글 총수량 
	recordCriteria.setTotalRec(pagingDAO.selectRecQnty(searchType,searchKeyword));
	return recordCriteria;
	}
	
	//페이징 _ 한페이지에 표현될 페이징 번호 산출
	@Override
	public PageCriteria getPageCriteria(int reqPage, RecordCriteria recordCriteria) {
		//한페이지에 보여줄 페이징넘버의 수 : 이전페이지 {1,2,3,....,10} 다음페이지
		int pagingNumsPerPage = 10;					
		PageCriteria pageCriteria = new PageCriteria(reqPage, recordCriteria.getTotalRec(), pagingNumsPerPage);
		return pageCriteria;
	}
	
	
	
//페이징 설정
//페이징 wrapper _   (레코드 / 페이징 / 검색어) 요소 wrapping 
	@Override
	public PagingComponent getPagingComponent(int reqPage,String searchType, String searchKeyword) {
		PagingComponent pagingComponent = new PagingComponent();
		
		//각 criteria 필드멤버 변수 설정
		RecordCriteria recordCriteria = getRecCriteria(reqPage,searchType,searchKeyword);
		PageCriteria pageCriteria = getPageCriteria(reqPage, recordCriteria);	
		SearchCriteria searchCriteria = new SearchCriteria(searchType, searchKeyword);
		
		// pagingComponent에 반영
		pagingComponent.setRecordCriteria(recordCriteria);
		pagingComponent.setPageCriteria(pageCriteria);
		pagingComponent.setSearchCriteria(searchCriteria);
		
		return pagingComponent;
	}

}
