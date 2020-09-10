package com.my.maintest.item.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.item.vo.ListVO;
import com.my.maintest.item.vo.ListingVO;

@Repository 
public class ItemListDAOImplXML implements ItemListDAO{

	@Inject
	private SqlSession sqlSession;
	
	//아이템 카테고리 불러오기
	@Override
	public List<ItemCategoryVO> selectAllCategory() {
		return sqlSession.selectList("mappers.ItemListDAO-mapper.selectAllCategory");
	}

	//아이템 전체 불러오기
	@Override
	public List<ItemVO> selectAllItem() {
		List<ItemVO> list = null;
		list = sqlSession.selectList("mappers.ItemListDAO-mapper.selectAllItem");
		return list;
	}
	
//사용자가 리스트불러오기를 선택했을 경우
	@Override
	public List<ItemVO> selectListItem(long lnum) {
		return sqlSession.selectList("mappers.ItemListDAO-mapper.selectListItem",lnum);
	}
	
	//사용자의 리스트 불러오기(이름만, 저장하기용)
	@Override
	public List<ListVO> loadList(String ucode) {
		return sqlSession.selectList("mappers.ItemListDAO-mapper.loadList", ucode);
	}

//리스트 이름 생성
	@Override
	public int listNameInsert(ListVO listVO) {
		return sqlSession.insert("mappers.ItemListDAO-mapper.listNameInsert", listVO);
	}


	@Override
	public List<ListingVO> loadListing(long lnum) {
		return sqlSession.selectList("mappers.ItemListDAO-mapper.loadListing",lnum);
	}

//리스트 아이템 저장
	//기존에 있던 아이템들 삭제
	@Override
	public int deleteListing(long lnum) {
		return sqlSession.delete("mappers.ItemListDAO-mapper.deleteListing", lnum);
	}
	@Override
	public int deleteNewItem(long lnum) {
		return sqlSession.delete("mappers.ItemListDAO-mapper.deleteNewItem", lnum);
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
	public String getListname(long lnum) {		
		return sqlSession.selectOne("mappers.ItemListDAO-mapper.getListname", lnum);
	}
	@Override
	public int insertListing(ListingVO listingVO) {		
		return sqlSession.insert("mappers.ItemListDAO-mapper.insertListing", listingVO);
	}

	

}
