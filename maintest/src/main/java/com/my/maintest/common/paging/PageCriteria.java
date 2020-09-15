package com.my.maintest.common.paging;

import lombok.Data;

/*
 * 
 * 한페이지에 보여줄 페이징 번호 수 : pagingNumsPerPage : default
 * 총페이지 수 totalPagingNums = RecordCriteria.getTotalRec / pagingNumsPerPage
 * 페이징 시작번호 : pagingNumFrom  = pagingNumTo - (pagingNumsPerPage -1)
 * 페이징 끝번호 : pagingNumTo = (reqPage/pagingNumsPerPage)*pagingNumsPerPage 
 * 마지막 페이지 : endPagingNum = totalPagingNums
 * 
 * */

@Data
public class PageCriteria {

	private long currReqPage;

	private long pagingNumsPerPage;
	private long totalPagingNums;
	private long pagingNumFrom;
	private long pagingNumTo;
	private long endPagingNum;

	private boolean prevPage;
	private boolean nextPage;

	public PageCriteria() {

	}

	// 총레코드 수 와 페이징으로 나타날 번호 수 를 인자로 받아 총페이지수 / 페이징 끝번호 / 페이징 시작번호를 산출
	public PageCriteria(long reqPage, int totalRec, long pagingNumsPerPage) {
		// 요청한 페이지를 저장 --> 현재 페이지를 나타냄
		this.currReqPage = reqPage;

		this.pagingNumsPerPage = pagingNumsPerPage;

		// 총페이지 수 totalPagingNums = RecordCriteria.getTotalRec / pagingNums
		this.totalPagingNums = (int) Math.ceil(totalRec / (double) pagingNumsPerPage);

		// 마지막 페이지 : endPagingNum = totalPagingNums
		this.endPagingNum = totalPagingNums;

		// 페이징 끝번호 : pagingNumTo = (reqPage/pagingNums)*pagingNums
		this.pagingNumTo = (int) Math.ceil(reqPage / (double) pagingNumsPerPage) * pagingNumsPerPage;

		// 페이징 시작번호 : pagingNumFrom = pagingNumTo - (pagingNums -1)
		this.pagingNumFrom = pagingNumTo <= pagingNumsPerPage ? 1 : pagingNumTo - (pagingNumsPerPage - 1);

		// 마지막 페이지 페이징 번호 설정
		if (this.pagingNumTo > endPagingNum) {
			this.pagingNumTo = endPagingNum;
		}

		// 이전페이지 true :
		this.prevPage = reqPage <= 10 ? false : true;

		// 다음페이지 true:
		this.nextPage = this.pagingNumTo < endPagingNum ? true : false;

	}

}
