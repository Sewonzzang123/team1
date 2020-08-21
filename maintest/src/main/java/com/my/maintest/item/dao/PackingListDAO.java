package com.my.maintest.item.dao;

import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.item.vo.ListingVO;

public interface PackingListDAO {
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
