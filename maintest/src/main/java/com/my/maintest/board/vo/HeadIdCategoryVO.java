package com.my.maintest.board.vo;

import javax.persistence.Entity;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class HeadIdCategoryVO {

	 @PositiveOrZero
	String hidnum;  
	String catnum; 

	String hidname;
	
	
	public HeadIdCategoryVO() {
	};

	public HeadIdCategoryVO(String hidnum, String catnum, String hidname) {
		super();
		this.hidnum = hidnum;
		this.catnum = catnum;
		this.hidname = hidname;
	}

	
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
