package com.my.maintest.item.svc;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.my.maintest.item.dao.ItemListDAO;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.item.vo.ListVO;

@Service
public class ItemListSVCImpl implements ItemListSVC {

	@Inject
	ItemListDAO itemListDAO;

//아이템 카테고리 불러오기	
	@Override
	public List<ItemCategoryVO> selectAllCategory() {
		return itemListDAO.selectAllCategory();
	}

	// 카테고리 클릭시 아이템 불러오기
	@Override
	public List<ItemVO> selectAllItem() {
		return itemListDAO.selectAllItem();
	}
	//사용자가 리스트불러오기를 선택했을 경우
	@Override
	public List<ItemVO> selectListItem(long lnum) {	
		return itemListDAO.selectListItem(lnum);
	}
	// 사용자의 리스트 불러오기(이름)
	@Override
	public List<ListVO> loadList(String ucode) {
		return itemListDAO.loadList(ucode);
	}

//리스트 이름 생성
	@Override
	public int listNameInsert(String ucode, String lname) {
		return itemListDAO.listNameInsert(ucode, lname);
	}

	@Override
	public int getNum(String ucode, String lname) {
		return itemListDAO.getNum(ucode, lname);
	}

	@Override
	public List<Map<String, String>> loadListing(long lnum) {	
		return itemListDAO.loadListing(lnum);
	}



}
