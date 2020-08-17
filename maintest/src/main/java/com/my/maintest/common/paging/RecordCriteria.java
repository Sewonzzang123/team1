package com.my.maintest.common.paging;

/*
 * 요청페이지를 입력받아 시작레코드 , 종료 레코드 구하기 
 * 한페이지에 보여줄 레코드수 : recNumPerPage
 * 
 * 요청페이지 :reqPage
 * 
 * sql where  between {recFrom} and {recTo}
 * sql문 시작번호 : recFrom
 * sql문 끝번호 :recTo
 * 
 * 총레코스 수 totalRec 
 * 
 * */

public class RecordCriteria {
	private int totalRec;
	private int recNumPerPage; 
	private int recFrom;
	private int recTo;

	public RecordCriteria() {

	}

	// 객체 생성할때 요청페이지 번호 + 한페이지에 보여줄 레코드 수를 인자로 받아서 레코드 범위를 산출
	public RecordCriteria(int recNumPerPage, int reqPage) {
		
		// requestPage 번호 기준으로 sql문 데이터 범위 산출 : sql where between {recFrom} and {recTo}
		// recTo = reqPage *10
		this.recTo = reqPage * recNumPerPage;

		// recTo - (recNumPerPage-1)
		this.recFrom = this.recTo - (recNumPerPage - 1);

	
		this.recNumPerPage = recNumPerPage;
	}

	
	
	
	public int getTotalRec() {
		return totalRec;
	}

	public void setRecFrom(int recFrom) {
		this.recFrom = recFrom;
	}

	public void setRecTo(int recTo) {
		this.recTo = recTo;
	}

	public void setTotalRec(int totalRec) {
		this.totalRec = totalRec;
	}


	public int getRecNumPerPage() {
		return recNumPerPage;
	}

	public void setRecNumPerPage(int recNumPerPage) {
		this.recNumPerPage = recNumPerPage;
	}

	public int getRecTo() {
		return recTo;
	}

	public int getRecFrom() {
		return recFrom;
	}

	@Override
	public String toString() {
		return "RecordCriteria [totalRec=" + totalRec + ", recNumPerPage=" + recNumPerPage + ", recFrom=" + recFrom
				+ ", recTo=" + recTo + "]";
	}


	
	
	
}
