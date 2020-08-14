package com.my.maintest.item.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.item.vo.ListVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class ItemListDAOImplXMLTEST {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(ItemListDAOImplXMLTEST.class);
	
	@Inject
	ItemListDAO itemListDAO;
	
	@Test
	@DisplayName("카테고리 불러오기")
	@Disabled
	void selectAllCategory() {
		
		List<ItemCategoryVO> list = itemListDAO.selectAllCategory();
		
		for(ItemCategoryVO itemCategoryVO : list) {
			logger.info(itemCategoryVO.toString());
		}
	}
	
	@Test
	@DisplayName("아이템 불러오기")
	@Disabled
	void selectAllItem() {
		ItemCategoryVO itemCategoryVO = new ItemCategoryVO();
		
		List<ItemVO> list = itemListDAO.selectAllItem();
		for(ItemVO itemVO : list) {
			logger.info(itemVO.toString());
		}

	}
	
	@Test
	@DisplayName("사용자의 리스트 이름 불러오기")
	@Disabled
	void loadList() {
		String ucode="0";
		List<ListVO> list = itemListDAO.loadList(ucode);
		
		for(ListVO listVO: list) {
		logger.info(":"+listVO.toString());
		}
	}
	
	@Test
	@DisplayName("사용자의 리스트 (아이템포함) 불러오기")
	@Disabled
	void loadItemList() {
		String ucode="0";
		List<ListVO> list = itemListDAO.loadItemList(ucode);
		
		for(ListVO listVO: list) {
		logger.info(":"+listVO.toString());
		}
	}
	
	@Test
	@DisplayName("리스트 이름 생성")
	@Disabled
	void listNameInsert() {
		Map<String, String> map = new HashMap<>();
		int result = itemListDAO.listNameInsert("0", "리스트2");
		Assertions.assertEquals(1, result);
		
	}
	
	@Test
	@DisplayName("생성한 리스트 번호")
	void getName() {
		Map<String, String> map = new HashMap<>();
		int result = itemListDAO.getNum("0", "리스트2");
		logger.info(""+result);
	}
}
