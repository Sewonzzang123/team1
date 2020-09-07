package com.my.maintest.board.dao;

public interface PagingDAO {


//페이징
		//레코드 총 수량 구하기  blog
	int selectRecQnty_Blog(long catnum, String searchType, String searchKeyword);
	//레코드 총 수량 구하기  album
	int selectRecQnty_Album(long catnum, String searchType, String searchKeyword);
}
