package com.my.maintest.common.paging;

import lombok.Data;

/*
 * 현재 페이지에서 보여줄 페이지 범위 계산
 * 전제조건: RecordCriteria 정보, 전체레코드수(totalRec)를 입력값으로 받아
 * 시작페이지,종료페이지,이전,다음,처음,마지막 페이지를 산출한다.
 */
@Data
public class Paging {

	private int pageNumPerPage; // 한페이지에보여줄 페이지수
	private int startPage; // 한페이지에보여줄 시작페이지
	private int endPage; // 한페이지에보여줄 종료페이지

	private int totalRec; // 전체레코드수
	private int finalEndPage; // 최종페이지

	private boolean prev; // 이전페이지
	private boolean next; // 다음페지

	private int prevpage; // 이전페이지
	private int nextpage; // 다음페지

	private int reqPage; // 요청페이지
	private int recNumPerPage; // 한페이지당 보여줄 레코드수

	// 시작레코드 = (요청페이지-1) * 한페이지에 보여줄 레코드수+1
	public int getStr_num() {

		return totalRec - ((reqPage - 1) * recNumPerPage);
	}

	// 종료레코드 = 요청페이지 X 한페이지에보여줄 레코드수
	public int getEnd_num() {
		return totalRec - (reqPage * recNumPerPage) + 1;
	}

	public void calculatefinalEndPage() {

		// 한페이지에 보여줄 종료페이지
		// 올림(요청페이지/한페이지에 보여줄 페이지수) * 한페이지에 보여줄 페이지수
		endPage = (int) Math.ceil(getReqPage() / (double) getPageNumPerPage()) * getPageNumPerPage();

		// 한페이지에보여줄 시작페이지
		// 한페이지에보여줄 종료페이지 - 한페이지에 보여줄 페이지수 + 1
		startPage = getEndPage() - getPageNumPerPage() + 1;

		// 최종(마지막) 페이지계산: 올림(전체레코드수/한페이지에보여줄 레코드수)
		finalEndPage = (int) Math.ceil(getTotalRec() / (double) getRecNumPerPage());

		// 최종페이지가 현재페이지의 종료페이지보다 작으면 최종페이지가 종료페이지가 된다.
		if (finalEndPage < endPage) {
			endPage = finalEndPage;
		}
	}

	// 이전페이지 노출여부 : 요청페이지의 시작페이지가 1이 아닌경우 노출
	public boolean isPrev() {
		return getStartPage() == 1 ? false : true;
	}

	// 다음페이지 노출여부 : 전체 레코드수가 요청페이지의 종료페이지보다 큰경우 노출
	public boolean isNext() {
		return totalRec > getEndPage() * getRecNumPerPage() ? true : false;
	}

	public void napPage() {
		nextpage = ((reqPage / pageNumPerPage) + 1) * pageNumPerPage + 1;
		prevpage = (((reqPage / pageNumPerPage) - 1) * pageNumPerPage) + 1;
	}

}
