package com.my.maintest.item.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.maintest.item.dao.ItemListDAO;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class ItemListDAOImplXMLTEST {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(ItemListDAOImplXMLTEST.class);
	
	@Inject
	ItemListDAO itemCategoryDAO;
	
	@Test
	@DisplayName("카테고리 불러오기")
	@Disabled
	void selectAllCategory() {
		
		List<ItemCategoryVO> list = itemCategoryDAO.selectAllCategory();
		
		for(ItemCategoryVO itemCategoryVO : list) {
			logger.info(itemCategoryVO.toString());
		}
	}
	
	@Test
	@DisplayName("아이템 불러오기")
	@Disabled
	void selectAllItem() {
		ItemCategoryVO itemCategoryVO = new ItemCategoryVO();
		
		List<ItemVO> list = itemCategoryDAO.selectAllItem();
		for(ItemVO itemVO : list) {
			logger.info(itemVO.toString());
		}

	}
	
	@Test
	@DisplayName("카테고리별 아이템 불러오기")
	@Disabled
	void selectOneItem() {
		ItemCategoryVO itemCategoryVO = new ItemCategoryVO();
		
		List<ItemVO> list = itemCategoryDAO.selectOneItem("1");
		for(ItemVO itemVO : list) {
			logger.info(itemVO.toString());
		}

	}

}
