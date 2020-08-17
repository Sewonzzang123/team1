package com.my.maintest.common.paging;

/*
 * 
 * 한페이지에 보여줄 페이징 번호 수 : pagingNumsPerPage : default
 * 총페이지 수 totalPagingNums = RecordCriteria.getTotalRec / pagingNumsPerPage
 * 페이징 시작번호 : pagingNumFrom  = pagingNumTo - (pagingNumsPerPage -1)
 * 페이징 끝번호 : pagingNumTo = (reqPage/pagingNumsPerPage)*pagingNumsPerPage 
 * 마지막 페이지 : endPagingNum = totalPagingNums
 * 
 * */

/**
 * @author DSY
 *
 */
public class PageCriteria {

	private int currReqPage;
	
	private int pagingNumsPerPage;
	private int totalPagingNums;
	private int pagingNumFrom;
	private int pagingNumTo;
	private int endPagingNum;
	
	private boolean prevPage;
	private boolean nextPage;
	
	
	public PageCriteria() {

	}

	// 총레코드 수 와 페이징으로 나타날 번호 수 를 인자로 받아 총페이지수 / 페이징 끝번호 / 페이징 시작번호를 산출
	public PageCriteria(int reqPage, int totalRec, int pagingNumsPerPage) {
		//요청한 페이지를 저장 --> 현재 페이지를 나타냄
		this.currReqPage = reqPage;
		
		this.pagingNumsPerPage = pagingNumsPerPage;
		
		// 총페이지 수 totalPagingNums = RecordCriteria.getTotalRec / pagingNums
		this.totalPagingNums = (int) Math.ceil(totalRec /(double) pagingNumsPerPage);
		
		// 마지막 페이지 : endPagingNum = totalPagingNums
		this.endPagingNum = totalPagingNums;
	
		// 페이징 끝번호 : pagingNumTo = (reqPage/pagingNums)*pagingNums
		
				if(reqPage <= 10) {
					this.pagingNumTo = endPagingNum;
				}else {
					this.pagingNumTo = (int)(reqPage/(double)pagingNumsPerPage)*pagingNumsPerPage;
				}
		

		// 페이징 시작번호 : pagingNumFrom = pagingNumTo - (pagingNums -1)
		
		this.pagingNumFrom = pagingNumTo <= 10 ? 1 : pagingNumTo - (pagingNumsPerPage - 1);
		
		//이전페이지  true :
		this.prevPage = this.pagingNumFrom == 1 ? false: true;
		
		//다음페이지 true:
		this.nextPage = this.pagingNumTo < endPagingNum ? true : false;
			
	}

	
	

	public int getPagingNumsPerPage() {
		return pagingNumsPerPage;
	}

	public void setPagingNumsPerPage(int pagingNumsPerPage) {
		this.pagingNumsPerPage = pagingNumsPerPage;
	}

	public int getTotalPagingNums() {
		return totalPagingNums;
	}

	public void setTotalPagingNums(int pagingNums, int totalRec) {

	}

	public int getPagingNumFrom() {
		return pagingNumFrom;
	}

	public void setPagingNumFrom(int pagingNumFrom) {
		this.pagingNumFrom = pagingNumFrom;
	}

	public int getPagingNumTo() {
		return pagingNumTo;
	}

	public void setTotalPagingNums(int totalPagingNums) {
		this.totalPagingNums = totalPagingNums;
	}

	public void setPagingNumTo(int pagingNumTo) {
		//
		this.pagingNumTo = pagingNumTo;
	}

	public int getEndPagingNum() {
		return endPagingNum;
	}

	public void setEndPagingNum(int endPagingNum) {
		this.endPagingNum = endPagingNum;
	}

	public int getCurrReqPage() {
		return currReqPage;
	}

	public void setCurrReqPage(int currReqPage) {
		this.currReqPage = currReqPage;
	}

	public boolean isPrevPage() {
		return prevPage;
	}

	public void setPrevPage(boolean prevPage) {
		this.prevPage = prevPage;
	}

	public boolean isNextPage() {
		return nextPage;
	}

	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public String toString() {
		return "PageCriteria [currReqPage=" + currReqPage + ", pagingNumsPerPage=" + pagingNumsPerPage
				+ ", totalPagingNums=" + totalPagingNums + ", pagingNumFrom=" + pagingNumFrom + ", pagingNumTo="
				+ pagingNumTo + ", endPagingNum=" + endPagingNum + ", prevPage=" + prevPage + ", nextPage=" + nextPage
				+ "]";
	}



	
	
}
