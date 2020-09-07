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
	public RecordCriteria getRecCriteria(String btype, long catnum, int reqPage, long recNumPerPage, String searchType, String searchKeyword ) {
	//한페이지에 보여줄 게시글 수 는 인자로 받는다	
	RecordCriteria recordCriteria = new RecordCriteria(recNumPerPage, reqPage);
	
	//게시글 총수량 	
	if(btype.equals("album")) {	
	recordCriteria.setTotalRec(pagingDAO.selectRecQnty_Album(catnum, searchType,searchKeyword));	
	}else {
	recordCriteria.setTotalRec(pagingDAO.selectRecQnty_Blog(catnum, searchType,searchKeyword));	
	}
	System.out.println("ㅁ※※※※※※※※※※※※※※※※※※※※※※※※※※ recordCriteria.getTotalRec()     =   " + recordCriteria.getTotalRec());
	return recordCriteria;
	}
	
	//페이징 _ 한페이지에 표현될 페이징 번호 산출
	@Override
	public PageCriteria getPageCriteria(long reqPage, RecordCriteria recordCriteria) {
		//한페이지에 보여줄 페이징넘버의 수 : 이전페이지 {1,2,3,....,10} 다음페이지
		int pagingNumsPerPage = 10;					
		PageCriteria pageCriteria = new PageCriteria(reqPage, recordCriteria.getTotalRec(), pagingNumsPerPage);
		return pageCriteria;
	}
	
	
	
//페이징 설정
//페이징 wrapper _   (레코드 / 페이징 / 검색어) 요소 wrapping 
	@Override
	public PagingComponent getPagingComponent(String btype,long catnum, int reqPage,long recNumPerPage,String searchType, String searchKeyword) {
		PagingComponent pagingComponent = new PagingComponent();
		
		//각 criteria 필드멤버 변수 설정
		RecordCriteria recordCriteria = getRecCriteria(btype,catnum, reqPage,recNumPerPage,searchType,searchKeyword);
		
		PageCriteria pageCriteria = getPageCriteria(reqPage, recordCriteria);	
		SearchCriteria searchCriteria = new SearchCriteria(searchType, searchKeyword);
		
		// pagingComponent에 반영
		pagingComponent.setRecordCriteria(recordCriteria);
		pagingComponent.setPageCriteria(pageCriteria);
		pagingComponent.setSearchCriteria(searchCriteria);
		
		return pagingComponent;
	}

}
