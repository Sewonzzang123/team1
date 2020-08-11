package com.my.maintest.item.dao;

import java.util.List;

import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;

public interface ItemListDAO {
	//아이템 카테고리 불러오기
	List<ItemCategoryVO> selectAllCategory();
	//카테고리 클릭시 아이템  불러오기
	List<ItemVO> selectOneItem(String ca_num);
	//아이템 전체 불러오기
	List<ItemVO> selectAllItem();
	
}
