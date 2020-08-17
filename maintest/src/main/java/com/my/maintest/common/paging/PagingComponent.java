package com.my.maintest.common.paging;

public class PagingComponent {

	
	private RecordCriteria recordCriteria;
	private PageCriteria pageCriteria;

	
	public PagingComponent() {

	}
	
	public RecordCriteria getRecordCriteria() {
		return recordCriteria;
	}

	public void setRecordCriteria(RecordCriteria recordCriteria) {
		this.recordCriteria = recordCriteria;
	}

	public PageCriteria getPageCriteria() {
		return pageCriteria;
	}

	public void setPageCriteria(PageCriteria pageCriteria) {
		this.pageCriteria = pageCriteria;
	}

	@Override
	public String toString() {
		return "PagingComponent [recordCriteria=" + recordCriteria + ", pageCriteria=" + pageCriteria + "]";
	}
	
	
	
	
	
	
}
