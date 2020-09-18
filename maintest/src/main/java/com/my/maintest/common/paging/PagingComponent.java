package com.my.maintest.common.paging;

import lombok.Data;

@Data
public class PagingComponent {

	
	private RecordCriteria recordCriteria;
	private PageCriteria pageCriteria;
private SearchCriteria searchCriteria;
	
	
	
	public PagingComponent() {

		
	}	
	public PagingComponent( long reqPage, long recNumPerPage,long  pagingNumsPerPage) {		
		RecordCriteria recordCriteria = new RecordCriteria(recNumPerPage, reqPage);
		PageCriteria pageCriteria = new PageCriteria(reqPage, recordCriteria.getTotalRec(),pagingNumsPerPage);				
		this.recordCriteria = recordCriteria;
		this.pageCriteria = pageCriteria;
	}
	
	public PagingComponent( long reqPage, long recNumPerPage,long  pagingNumsPerPage,String searchType,String searchKeyword) {
		RecordCriteria recordCriteria = new RecordCriteria(recNumPerPage, reqPage);
		PageCriteria pageCriteria = new PageCriteria(reqPage, recordCriteria.getTotalRec(),pagingNumsPerPage);
		SearchCriteria searchCreCriteria = new SearchCriteria(searchType, searchKeyword);		
		this.recordCriteria = recordCriteria;
		this.pageCriteria = pageCriteria;
		this.searchCriteria = searchCreCriteria;
	}
	
	
	


	
	
	
	
	
}
