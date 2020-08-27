package com.my.maintest.item.svc;

import java.util.List;
import java.util.Map;

import com.my.maintest.item.vo.ListVO;

public interface PackingListSVC {
	//아이템 리스트에 넣기
	int insertListing(ListVO listVO, List<Map<String,String>> listing);
}
