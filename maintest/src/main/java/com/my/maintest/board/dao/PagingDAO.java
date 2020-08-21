package com.my.maintest.board.dao;

public interface PagingDAO {


//페이징
		//레코드 총 수량 구하기 
	int selectRecQnty(String searchType, String searchKeyword);
}
