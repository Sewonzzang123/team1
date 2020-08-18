package com.my.maintest.mypage.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class MylistVO {
	private String lnum;
	private String lname;
	private Date cdate;
	private int num;
	private String checked_item;
	private String total_item;
	
}
