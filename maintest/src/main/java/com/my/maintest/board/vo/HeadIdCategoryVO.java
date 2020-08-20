package com.my.maintest.board.vo;

import lombok.Data;

@Data
public class HeadIdCategoryVO {
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

}
