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

	@Override
	public List<ListVO> loadList(String ucode) {
		return sqlSession.selectList("mappers.ItemListDAO-mapper.loadList", ucode);
	}

	@Override
	public List<ListVO> loadItemList(String ucode) {
		return sqlSession.selectList("mappers.ItemListDAO-mapper.loadItemList", ucode);
	}

	@Override
	public int listNameInsert(String ucode, String lname) {
		Map<String, String> map = new HashMap<>();
		map.put("ucode", ucode);
		map.put("lname", lname);
		return sqlSession.insert("mappers.ItemListDAO-mapper.listNameInsert", map);
	}

	@Override
	public int getNum(String ucode, String lname) {
		Map<String, String> map = new HashMap<>();
		map.put("ucode", ucode);
		map.put("lname", lname);
		return sqlSession.selectOne("mappers.ItemListDAO-mapper.getNum",map);
	}
	

}
