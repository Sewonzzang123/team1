package com.my.maintest.admin.svc;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.my.maintest.admin.dao.AdminDAO;
import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;
import com.my.maintest.common.paging.Paging;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.member.vo.MemberVO;

@Repository
public class AdminSVCImpl implements AdminSVC {

	@Inject
	AdminDAO adminDAO;

	@Override
	public List<BcategoryVO> getCate() {
		
		return adminDAO.getCate();
	}

	@Override
	public int delBoard(String catnum) {

		return adminDAO.delBoard(catnum);
	}

	@Override
	public int createBoard(BcategoryVO bcategoryVO) {
		
		return adminDAO.createBoard(bcategoryVO);
	}

	@Override
	public int setBoard(BcategoryVO bcategoryVO) {
		
		return adminDAO.setBoard(bcategoryVO);
	}

	@Override
	public List<HeadIdCategoryVO> getHead() {

		return adminDAO.getHead();
	}

	@Override
	public int setHead(HeadIdCategoryVO headIdCategoryVO) {
		
		return adminDAO.setHead(headIdCategoryVO);
	}

	@Override
	public int delHead(String hidnum) {

		return adminDAO.delHead(hidnum);
	}

	@Override
	public List<ItemCategoryVO> getIcate() {
		
		return adminDAO.getIcate();
	}

	@Override
	public List<ItemVO> getItem() {
		
		return adminDAO.getItem();
	}

	@Override
	public int delIcate(String ca_num) {
		
		return adminDAO.delIcate(ca_num);
	}

	@Override
	public int delItem(String i_num) {
		
		return adminDAO.delItem(i_num);
	}

	@Override
	public String getCa_num() {
		
		return adminDAO.getCa_num();
	}

	@Override
	public int setIcate(HashMap<String, String> map) {
		
		return adminDAO.setIcate(map);
	}

	@Override
	public int modifyIcate(HashMap<String, String> map) {
		
		return adminDAO.modifyIcate(map);
	}

	@Override
	public int setItem(HashMap<String, String> map) {
		
		return adminDAO.setItem(map);
	}

	@Override
	public List<MemberVO> memberlist(int reqPage) {
		Paging paging = new Paging();
		paging.setReqPage(reqPage);
		paging.setTotalRec(adminDAO.total_member());
		paging.setRecNumPerPage(15);
		paging.calculatefinalEndPage();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("end_num", String.valueOf(paging.getEnd_num()));
		map.put("str_num", String.valueOf(paging.getStr_num()));
		return adminDAO.memberlist(map);
	}

	@Override
	public List<MemberVO> memberlist(int reqPage, String searchType, String keyword) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);

		Paging paging = new Paging();
		paging.setReqPage(reqPage);
		paging.setTotalRec(adminDAO.total_member(map));
		paging.setRecNumPerPage(15);
		paging.calculatefinalEndPage();

		map.put("end_num", String.valueOf(paging.getEnd_num()));
		map.put("str_num", String.valueOf(paging.getStr_num()));
		return adminDAO.memberlist_ck(map);
	}

	@Override
	public Paging paging(int reqPage) {
		Paging paging = new Paging();
		paging.setReqPage(reqPage);
		paging.setPageNumPerPage(10);
		paging.setRecNumPerPage(15);
		paging.setTotalRec(adminDAO.total_member());
		paging.calculatefinalEndPage();
		paging.setNext(paging.isNext());
		paging.setPrev(paging.isPrev());
		paging.napPage();

		return paging;
	}

	@Override
	public Paging paging(int reqPage, String searchType, String keyword) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);

		Paging paging = new Paging();
		paging.setReqPage(reqPage);
		paging.setPageNumPerPage(10);
		paging.setRecNumPerPage(15);
		paging.setTotalRec(adminDAO.total_member(map));
		paging.calculatefinalEndPage();
		paging.setNext(paging.isNext());
		paging.setPrev(paging.isPrev());
		paging.napPage();

		return paging;
	}

	@Override
	public int exit_member(String ucode) {
		
		return adminDAO.exit_member(ucode);
	}
}
