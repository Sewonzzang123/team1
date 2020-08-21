package com.my.maintest.item.dao;

import java.util.List;
import java.util.Map;

import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.item.vo.ListVO;

public interface ItemListDAO {
	//아이템 카테고리 불러오기
	List<ItemCategoryVO> selectAllCategory();
	//아이템 전체 불러오기
	List<ItemVO> selectAllItem();
	//사용자의 리스트 불러오기(이름만, 저장하기용)
	List<ListVO> loadList(String ucode);
	//사용자의 리스트 불러오기(아이템 리스트까지 전체로)
	List<ListVO> loadItemList(String ucode);
	//리스트 이름 생성
	int listNameInsert(String ucode, String lname);
	//생성한 이름의 리스트번호 가져오기
	int getNum(String ucode, String lname);
}
