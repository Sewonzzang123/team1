package com.my.maintest.item.svc;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.my.maintest.item.dao.ItemListDAO;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.item.vo.ListVO;
import com.my.maintest.item.vo.ListingVO;
import com.my.maintest.member.vo.MemberVO;

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
	public int listNameInsert(ListVO listVO) {
		return itemListDAO.listNameInsert(listVO);
	}


	@Override
	public List<ListingVO> loadListing(long lnum) {	
		return itemListDAO.loadListing(lnum);
	}

	@Override
	public int insertListing(ListVO listVO, List<Map<String,String>> listing) {
		long lnum = listVO.getLnum();
		String lname =itemListDAO.getListname(lnum);
			
		//1) 기존에 있던 아이템들 삭제
		itemListDAO.deleteListing(lnum);
		itemListDAO.deleteNewItem(lnum);
		
		//2) 남아있는 아이템이 있는지 확인
		if(itemListDAO.countListing(lnum)!=0) {
			return -1;
		}
		for(Map<String,String> item: listing ) {
			//3-1) 만약 inum이 additem 이라면 item table에 새로 생성
			if(item.get("i_num").equals("newitem") || Integer.parseInt(item.get("i_num"))>1000) {
				ItemVO itemVO = new ItemVO();
				ItemCategoryVO itemCategoryVO = new ItemCategoryVO();
				//아이템 카테고리 번호
				itemCategoryVO.setCa_num(Long.valueOf(item.get("icategory")));
				itemVO.setItemCategoryVO(itemCategoryVO);
				//아이템 이름
				itemVO.setI_name(item.get("i_name"));
				itemVO.setLnum(lnum);
				itemListDAO.saveNewItem(itemVO);
				//selectKey를 사용해서 inum을 새로 받았음.			
				ListingVO listingVO = new ListingVO();
				
				listingVO.setLnum(lnum);
				listingVO.setI_num(String.valueOf(itemVO.getI_num()));
				listingVO.setLname(lname);
				listingVO.setChecked(item.get("checked"));
				listingVO.setIcount(Integer.parseInt(item.get("icount")));
				//listing table에 item넣기
				itemListDAO.insertListing(listingVO);
			}
			else{
				//3-2) inum이 additem이 아니면
				ListingVO listingVO = new ListingVO();
				listingVO.setLnum(lnum);
				listingVO.setI_num(item.get("i_num"));
				listingVO.setLname(lname);
				listingVO.setChecked(item.get("checked"));
				listingVO.setIcount(Integer.parseInt(item.get("icount")));
				//listing table에 item넣기
				itemListDAO.insertListing(listingVO);
			}
		}		
		return 0;
	}
	@Override
	public String getListname(long lnum) {
		return itemListDAO.getListname(lnum);
	}
}
