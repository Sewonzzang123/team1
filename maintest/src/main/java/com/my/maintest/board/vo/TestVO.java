package com.my.maintest.board.vo;

public class TestVO {
	
	private String btitle;
	private int bnum;
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	@Override
	public String toString() {
		return "testVO [btitle=" + btitle + ", bnum=" + bnum + "]";
	}
	
	
	

}
