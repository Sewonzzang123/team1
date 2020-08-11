package com.my.maintest.item.svc;

import java.util.List;

import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;

public interface ItemListSVC {
	//아이템 카테고리 불러오기
	List<ItemCategoryVO> selectAllCategory();
	//카테고리 클릭시 아이템  불러오기
	List<ItemVO> selectAllItem();
	//
	List<ItemVO> selectConeItem(String ca_num);
}
