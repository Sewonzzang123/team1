package com.my.maintest.item.svc;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.my.maintest.item.dao.ItemListDAO;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;

@Service
public class ItemListSVCImpl implements ItemListSVC {

	@Inject
	ItemListDAO itemListDAO;
	
	@Override
	public List<ItemCategoryVO> selectAllCategory() {		
		return itemListDAO.selectAllCategory();
	}

	@Override
	public List<ItemVO> selectAllItem() {

		return itemListDAO.selectAllItem();
	}

	@Override
	public List<ItemVO> selectConeItem(String ca_num) {
		return itemListDAO.selectOneItem(ca_num);
	}

}
