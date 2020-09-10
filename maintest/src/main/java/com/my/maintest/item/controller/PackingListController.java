package com.my.maintest.item.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.maintest.item.vo.ListingVO;


@RestController
@RequestMapping("/packingList")
public class PackingListController {
	private static final Logger logger = LoggerFactory.getLogger(PackingListController.class);

	

	//session에 아이템 정보 추가
	@PutMapping(value="", produces="application/json")
	public ResponseEntity<String> putItem(
			@RequestBody ListingVO listingVO,
			HttpServletRequest request,
			HttpSession session){
		logger.info(listingVO.toString());

		ResponseEntity<String> res = null;
		List<ListingVO> listing = new ArrayList();
		//session에 listing이 있는지 확인
		List<ListingVO> Sessionlisting = (List<ListingVO>)request.getSession(false).getAttribute("listing");
		//없으면 session에 새로 생성 후 추가

		if(Sessionlisting == null) {
			listing.add(listingVO);
			session.setAttribute("listing", listing);	
		}else {
			Sessionlisting.add(listingVO);
			logger.info(Sessionlisting.toString());
		}
		
		res = new ResponseEntity<String>("success",HttpStatus.OK);
		
		return res;
	}
	
	//session에 아이템 정보 제거
	@DeleteMapping(value="", produces="application/json")
	public ResponseEntity<String> deleteItem(
		@RequestBody ListingVO listingVO,
		HttpServletRequest request){
		ResponseEntity<String> res = null;

		
		//session에 listing 불러오기
		List<ListingVO> Sessionlisting = (List<ListingVO>)request.getSession(false).getAttribute("listing");
		
		for(int i=0; i<Sessionlisting.size(); i++) {
			if(listingVO.getI_name().equals(Sessionlisting.get(i).getI_name()) && 
					listingVO.getCa_num()==Sessionlisting.get(i).getCa_num()) {
				((List<ListingVO>)request.getSession(false).getAttribute("listing")).remove(i);
				logger.info(Sessionlisting.toString());
				res = new ResponseEntity<String>("success",HttpStatus.OK);
			}
		}

		return res;
	}
	
	@PutMapping(value="/modifyItem", produces="application/json")
	public ResponseEntity<String> modifyItem(
			@RequestBody ListingVO listingVO,
			HttpServletRequest request){
		ResponseEntity<String> res = null;
		
		
		//session에 listing 불러오기
		List<ListingVO> Sessionlisting = (List<ListingVO>)request.getSession(false).getAttribute("listing");
			logger.info(listingVO.toString());
		for(int i=0; i<Sessionlisting.size(); i++) {
			if(listingVO.getI_name().equals(Sessionlisting.get(i).getI_name()) && 
					listingVO.getCa_num()==Sessionlisting.get(i).getCa_num()) {
				
				if(listingVO.getIcount() == 0) {
					//체크여부변경
					((List<ListingVO>)request.getSession(false).getAttribute("listing")).get(i).setChecked(listingVO.getChecked());
				}else {
					//수량변경
					((List<ListingVO>)request.getSession(false).getAttribute("listing")).get(i).setIcount(listingVO.getIcount());
				}
				logger.info(Sessionlisting.toString());
				res = new ResponseEntity<String>("success",HttpStatus.OK);
			}
		}
		
		return res;
	}
	
	

	
}
