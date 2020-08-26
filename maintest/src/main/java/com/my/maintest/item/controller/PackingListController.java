package com.my.maintest.item.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.maintest.item.svc.PackingListSVC;
import com.my.maintest.item.vo.ListVO;

@Controller
@RequestMapping("/packingList")
public class PackingListController {
	private static final Logger logger = LoggerFactory.getLogger(PackingListController.class);

	@Inject
	PackingListSVC packingListSVC;

	@PostMapping(value="/saveList")
	public String inum(
			@RequestParam(value = "lnum", required = true) int lnum,
			@RequestParam(value = "inum", required = true) List<String> inum,
			@RequestParam(value = "iname", required = true) List<String> iname,
			@RequestParam(value = "icount", required = true) List<String> icount,
			@RequestParam(value = "icategory", required = true) List<String> icategory,
			@RequestParam(value = "checked", required = true) List<String> checked, Model model) {
		logger.info(""+lnum);
		List<Map<String, String>> listing = new ArrayList<Map<String, String>>();
		if (inum != null) {
			for (int i = 0; i < inum.size(); i++) {
				Map<String, String> itemMap = new HashMap<>();
				itemMap.put("inum", inum.get(i));
				itemMap.put("iname", iname.get(i));
				itemMap.put("icategory", icategory.get(i));
				itemMap.put("icount", icount.get(i));
				itemMap.put("checked", checked.get(i));
				listing.add(i, itemMap);
			}
			logger.info(listing.toString());
		}
		
		ListVO listVO = new ListVO();

		listVO.setLnum(lnum);
		packingListSVC.insertListing(listVO, listing);
		
		//마이페이지에 리스트 화면으로 이동
		return "";
	}

}
