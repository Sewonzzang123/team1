package com.my.maintest.item.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.item.vo.ListingVO;

@Repository
public class PackingListDAOImplXML implements PackingListDAO {

	@Inject
	SqlSession sqlSession;
	//리스트 아이템 저장
	//기존에 있던 아이템들 삭제
	@Override
	public int deleteListing(long lnum) {
		return sqlSession.delete("mappers.ItemListDAO-mapper.deleteListing", lnum);
	}
	@Override
	public int countListing(long lnum) {
		return sqlSession.selectOne("mappers.ItemListDAO-mapper.countListing", lnum);
	}
	//새로운 아이템 item table에 넣기
	@Override
	public int saveNewItem(ItemVO itemVO) {
		return sqlSession.insert("mappers.ItemListDAO-mapper.saveNewItem", itemVO);
	}

	
	//listing table에 item넣기
	@Override
	public int insertListing(ListingVO listingVO) {		
		return sqlSession.insert("mappers.ItemListDAO-mapper.insertListing", listingVO);
	}



}
