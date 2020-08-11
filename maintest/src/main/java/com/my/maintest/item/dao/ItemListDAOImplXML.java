package com.my.maintest.item.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;

@Repository
public class ItemListDAOImplXML implements ItemListDAO{

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<ItemCategoryVO> selectAllCategory() {
		return sqlSession.selectList("mappers.ItemListDAO-mapper.selectAllCategory");
	}

	@Override
	public List<ItemVO> selectOneItem(String ca_num) {
		List<ItemVO> list = null;
		list =	sqlSession.selectList("mappers.ItemListDAO-mapper.selectOneItem", ca_num);
		return list;
	
	}

	@Override
	public List<ItemVO> selectAllItem() {
		List<ItemVO> list = null;
		list = sqlSession.selectList("mappers.ItemListDAO-mapper.selectAllItem");
		return list;
	}
	

}
