package com.my.maintest.board.vo;

import lombok.Data;


public class HeadIdCategoryVO {
	long hidnum;  
	long catnum; 
	String hidname;
	
	
	
	public long getHidnum() {
		return hidnum;
	}
	public void setHidnum(long hidnum) {
		this.hidnum = hidnum;
	}

	public String getHidname() {
		return hidname;
	}
	public void setHidname(String hidname) {
		this.hidname = hidname;
	}
	public long getCatnum() {
		return catnum;
	}
	public void setCatnum(long catnum) {
		this.catnum = catnum;
	}
	@Override
	public String toString() {
		return "HeadIdCategoryVO [hidnum=" + hidnum + ", catnum=" + catnum + ", hidname=" + hidname + "]";
	}

	
	

	
		
}
