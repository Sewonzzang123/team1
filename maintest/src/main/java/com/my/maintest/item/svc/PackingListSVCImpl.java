package com.my.maintest.item.svc;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.my.maintest.item.dao.PackingListDAO;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.item.vo.ListVO;
import com.my.maintest.item.vo.ListingVO;

@Service
public class PackingListSVCImpl implements PackingListSVC{

	@Inject
	PackingListDAO packingListDAO;
	
	@Override
	public int insertListing(ListVO listVO, List<Map<String,String>> listing) {
		long lnum = listVO.getLnum();
		String lname =packingListDAO.getListname(lnum);
			
		//1) 기존에 있던 아이템들 삭제
		packingListDAO.deleteListing(lnum);
		//packingListDAO.deleteNewItem(lnum);
		
		//2) 남아있는 아이템이 있는지 확인
		if(packingListDAO.countListing(lnum)!=0) {
			return -1;
		}
		for(Map<String,String> item: listing ) {
			//3-1) 만약 inum이 additem 이라면 item table에 새로 생성
			if(item.get("inum").equals("newitem")) {
				ItemVO itemVO = new ItemVO();
				ItemCategoryVO itemCategoryVO = new ItemCategoryVO();
				//아이템 카테고리 번호
				itemCategoryVO.setCa_num(Long.valueOf(item.get("icategory")));
				itemVO.setItemCategoryVO(itemCategoryVO);
				//아이템 이름
				itemVO.setI_name(item.get("iname"));
				
				packingListDAO.saveNewItem(itemVO);
				//selectKey를 사용해서 inum을 새로 받았음.			
				ListingVO listingVO = new ListingVO();
				
				listingVO.setLnum(lnum);
				listingVO.setI_num(itemVO.getI_num());
				listingVO.setLname(lname);
				listingVO.setChecked(item.get("checked"));
				listingVO.setIcount(Integer.parseInt(item.get("icount")));
				//listing table에 item넣기
				packingListDAO.insertListing(listingVO);
			}else {
				//3-2) inum이 additem이 아니면
				ListingVO listingVO = new ListingVO();
				listingVO.setLnum(lnum);
				listingVO.setI_num(Long.valueOf(item.get("inum")));
				listingVO.setLname(lname);
				listingVO.setChecked(item.get("checked"));
				listingVO.setIcount(Integer.parseInt(item.get("icount")));
				//listing table에 item넣기
				packingListDAO.insertListing(listingVO);
			}
		}		
		return 0;
	}

}
