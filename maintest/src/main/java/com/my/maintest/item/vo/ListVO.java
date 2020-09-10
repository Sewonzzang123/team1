package com.my.maintest.item.vo;

import java.sql.Date;

import com.my.maintest.member.vo.MemberVO;

import lombok.Data;

@Data
public class ListVO {
	private long lnum; //리스트 번호
	private MemberVO memberVO; //사용자 정보
	private String lname; //리스트 이름
	private Date cdate; //생성일자

	private int num;
	private String checked_item;
	private String total_item;
}
