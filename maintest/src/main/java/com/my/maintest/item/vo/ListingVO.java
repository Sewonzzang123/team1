package com.my.maintest.item.vo;

import lombok.Data;

@Data
public class ListingVO {
	//리스트에 저장된 아이템 정보
	private long linum;			//	linum number primary key,
	private long lnum;			//	lnum number,
	private long i_num;			//	i_num number,
	private String lname;		//	lname varchar2(50) not null,
	private String checked;	//	checked varchar2(10),
	private int icount;			//	icount number,

}
