package com.my.maintest.admin.vo;

import java.util.List;

import lombok.Data;

@Data
public class Item_dataVO {
	private List<String> del_icate_list;
	private List<String> item_del_list;
	private List<Icate_dataVO> icate_data;
}
