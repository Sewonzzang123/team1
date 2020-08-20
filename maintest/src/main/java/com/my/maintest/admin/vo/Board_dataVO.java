package com.my.maintest.admin.vo;

import java.util.List;

import lombok.Data;

@Data
public class Board_dataVO {

	private String canum;

	private String caname;

	private String bmemo;

	private String btype;

	private String del_sub_list;
	
	private List<String> sub_list;

}
