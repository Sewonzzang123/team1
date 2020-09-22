package com.my.maintest.item.vo;

import lombok.Data;

@Data
public class ListingVO {
	private long linum;			//	linum number primary key,
	private long lnum;			//	lnum number,
	private String i_num;			//	i_num number,
	private String i_name;
	private String lname;
	private String checked;	//	checked varchar2(10),
	private int icount;			//	icount number,
	private int ca_num;
	
	private long bnum;
}
