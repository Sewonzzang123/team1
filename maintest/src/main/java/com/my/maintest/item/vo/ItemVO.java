package com.my.maintest.item.vo;

import lombok.Data;

@Data
public class ItemVO {
	private ItemCategoryVO itemCategoryVO;
	private long i_num;
	private String i_name;
	private String i_description;
	private long lnum;
}
