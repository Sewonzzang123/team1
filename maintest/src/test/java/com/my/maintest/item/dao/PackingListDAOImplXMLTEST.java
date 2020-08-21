package com.my.maintest.item.dao;

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
import com.my.maintest.item.vo.ListingVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class PackingListDAOImplXMLTEST {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(ItemListDAOImplXMLTEST.class);
	
	@Inject
	PackingListDAO packingListDAO;
	
	//list에 아이템 넣기
	@Test
	@DisplayName("기존 list에있는 아이템들 삭제")
	@Disabled
	void deleteListing() {
		long lnum=1;
		packingListDAO.deleteListing(lnum);
		Assertions.assertEquals(0,packingListDAO.countListing(lnum));
		
	}
	
	@Test
	@DisplayName("newitem이면 item테이블에 추가")
	@Disabled
	void saveNewItem() {
		ItemVO itemVO = new ItemVO();
		ItemCategoryVO itemCategoryVO = new ItemCategoryVO();
		itemCategoryVO.setCa_num(1);
		itemVO.setItemCategoryVO(itemCategoryVO);
		itemVO.setI_name("test1");
		logger.info(""+itemVO.toString());
		packingListDAO.saveNewItem(itemVO);
		logger.info(""+itemVO.getI_num());		
	}
	
	@Test
	@DisplayName("listing에 아이템 정보 넣기")
//	@Disabled
	void insertListing() {
		ListingVO listingVO = new ListingVO();
//		listingVO.setI_num(i_num);
//		listingVO.setChecked(checked);
		
		packingListDAO.insertListing(listingVO);
	}
}
