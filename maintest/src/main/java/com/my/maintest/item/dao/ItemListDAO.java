package com.my.maintest.item.dao;

import java.util.List;
import java.util.Map;

import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.item.vo.ListVO;
import com.my.maintest.item.vo.ListingVO;

public interface ItemListDAO {
	//아이템 카테고리 불러오기
	List<ItemCategoryVO> selectAllCategory();
	//아이템 전체 불러오기
	List<ItemVO> selectAllItem();
	//사용자가 리스트불러오기를 선택했을 경우
	List<ItemVO> selectListItem(long lnum);
	//사용자의 리스트 불러오기(이름만, 저장하기용)
	List<ListVO> loadList(String ucode);
	//리스트 이름 생성
	int listNameInsert(ListVO listVO);
	//리스트 불러오기(리스트번호 입력시 아이템들 출력)
	List<ListingVO> loadListing(long lnum);
	
	//리스트 아이템 저장
	//기존에 있던 아이템들 삭제
	int deleteListing(long lnum);
	//아이템 테이블에 있는 사용자 추가 아이템들 삭제
	int deleteNewItem(long lnum);
	//남아있는 아이템이 있는지 확인
	int countListing(long lnum);
	//새로운 아이템 item table에 넣기
	int saveNewItem(ItemVO itemVO);
	//listing table에 item넣기
	int insertListing(ListingVO listingVO);
	
	//get lname
	String getListname(long lnum);
}
