package com.my.maintest.board.vo;

public class BcategoryVO {

	private long catnum; // CID NUMBER(10,0) No 1 분류코드
	private String catname; // CNAME VARCHAR2(60 BYTE) No 2 분류명
	private String btype;

	public long getCatnum() {
		return catnum;
	}

	public void setCatnum(long catnum) {
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

	@Override
	public String toString() {
		return "BcategoryVO [catnum=" + catnum + ", catname=" + catname + ", btype="
	 + btype
				+ "]";
	}

}
