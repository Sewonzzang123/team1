package com.my.maintest.board.vo;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@Entity
public class BcategoryVO {
	
	@NotNull
	@Positive(message="게시판 분류를 선택해주세요.")
	private String catnum; // CID NUMBER(10,0) No 1 분류코드
	
	private String catname; // CNAME VARCHAR2(60 BYTE) No 2 분류명
	private String bmemo; //게시판 용도 설명
	private String btype; //게시판 종류 blog/album 타입
	
	
	

	public String getCatnum() {
		return catnum;
	}

	public void setCatnum(String catnum) {
		this.catnum = catnum;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public String getBmemo() {
		return bmemo;
	}

	public void setBmemo(String bmemo) {
		this.bmemo = bmemo;
	}

	@Override
	public String toString() {
		return "BcategoryVO [catnum=" + catnum + ", catname=" + catname + ", bmemo=" + bmemo + ", btype=" + btype + "]";
	}



}
