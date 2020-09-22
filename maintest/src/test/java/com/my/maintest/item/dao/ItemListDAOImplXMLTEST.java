package com.my.maintest.item.dao;

import java.util.List;

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

import com.my.maintest.item.svc.ItemListSVC;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.item.vo.ListVO;
import com.my.maintest.item.vo.ListingVO;
import com.my.maintest.member.vo.MemberVO;

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
	@Inject
	ItemListSVC itemListSVC;
	
	@Test
	@DisplayName("리스트불러오기에서 선택했을 경우")
//	@Disabled
	void selectListItem() {
		List<ItemVO> list = itemListDAO.selectListItem(2);
		List<ListingVO> listing = null;
		List<ItemVO> itemList = null;
		listing = itemListSVC.loadListing(Long.valueOf(2));
		itemList = itemListSVC.selectListItem(Long.valueOf(2));
		logger.info(listing.toString());
		logger.info(itemList.toString());

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
	@DisplayName("리스트 이름 생성")
	@Disabled
	void listNameInsert() {
		ListVO listVO = new ListVO();
		MemberVO memberVO = new MemberVO();
		memberVO.setUcode("1");
		listVO.setMemberVO(memberVO);
		listVO.setLname("test1");
		int result = itemListDAO.listNameInsert(listVO);
		logger.info(""+listVO.getLnum());
		
	}

	
	@Test
	@DisplayName("리스트 이름 선택 시 아이템 출력")
	@Disabled
	void loadItem() {
		List<ListingVO> list = null;
		list = itemListDAO.loadListing((long)2);
		for(int i=0; i<list.size(); i++) {
			logger.info(""+list.get(i));
		}
		
	}
	
	//list에 아이템 넣기
	@Test
	@DisplayName("기존 list에있는 아이템들 삭제")
	@Disabled
	void deleteListing() {
		long lnum=1;
		itemListDAO.deleteListing(lnum);
		Assertions.assertEquals(0,itemListDAO.countListing(lnum));
		
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
		itemListDAO.saveNewItem(itemVO);
		logger.info(""+itemVO.getI_num());		
	}
	
	@Test
	@DisplayName("listing에 아이템 정보 넣기")
	@Disabled
	void insertListing() {
		ListingVO listingVO = new ListingVO();
		listingVO.setI_num("1000");
		listingVO.setChecked("false");
		listingVO.setIcount(3);
		listingVO.setLname("ㄹㅇㄹㄴㅇㄹㄴㄹㅇ");
		listingVO.setLnum(1);
		
		itemListDAO.insertListing(listingVO);
	}
}
