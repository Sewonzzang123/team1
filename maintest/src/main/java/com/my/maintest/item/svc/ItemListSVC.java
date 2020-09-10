package com.my.maintest.item.svc;

import java.util.List;
import java.util.Map;

import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.item.vo.ListVO;
import com.my.maintest.item.vo.ListingVO;

public interface ItemListSVC {
	//아이템 카테고리 불러오기
	List<ItemCategoryVO> selectAllCategory();
	//카테고리 클릭시 아이템  불러오기
	List<ItemVO> selectAllItem();
	//사용자가 리스트불러오기를 선택했을 경우
	List<ItemVO> selectListItem(long lnum);
	//사용자의 리스트 불러오기(이름)
	List<ListVO> loadList(String ucode);
	//리스트 이름 생성
	int listNameInsert(ListVO listVO);
	//리스트 불러오기(리스트번호 입력시 아이템들 출력)
	List<ListingVO> loadListing(long lnum);
	//아이템 리스트에 넣기
	int insertListing(ListVO listVO, List<Map<String,String>> listing);
}
