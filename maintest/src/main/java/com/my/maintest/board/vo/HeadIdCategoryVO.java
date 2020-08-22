package com.my.maintest.board.vo;

public class HeadIdCategoryVO {
	String hidnum;  
	String catnum; 
	String hidname;
	public String getHidnum() {
		return hidnum;
	}
	public void setHidnum(String hidnum) {
		this.hidnum = hidnum;
	}
	public String getCatnum() {
		return catnum;
	}
	public void setCatnum(String catnum) {
		this.catnum = catnum;
	}
	public String getHidname() {
		return hidname;
	}
	public void setHidname(String hidname) {
		this.hidname = hidname;
	}
	@Override
	public String toString() {
		return "HeadIdCategoryVO [hidnum=" + hidnum + ", catnum=" + catnum + ", hidname=" + hidname + "]";
	}
}
