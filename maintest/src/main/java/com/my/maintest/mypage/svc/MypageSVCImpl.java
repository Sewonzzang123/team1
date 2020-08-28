package com.my.maintest.mypage.svc;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.common.paging.Paging;
import com.my.maintest.member.vo.MemberVO;
import com.my.maintest.mypage.dao.MypageDAO;
import com.my.maintest.mypage.vo.IcategoryVO;
import com.my.maintest.mypage.vo.ListingVO;
import com.my.maintest.mypage.vo.MylistVO;

@Repository
public class MypageSVCImpl implements MypageSVC {
	private static final Logger logger = LoggerFactory.getLogger(MypageSVCImpl.class);

	@Inject
	MypageDAO mypageDAO;

//	@Inject
//	Paging paging;

	@Override
	public int modify(MemberVO memberVO) {

		return mypageDAO.modify(memberVO);
	}

	@Override
	public int changePW(MemberVO memberVO) {

		return mypageDAO.changePW(memberVO);
	}

	@Override
	public int withdraw(String id) {

		return mypageDAO.withdraw(id);
	}

	@Override
	public List<MylistVO> mylist(int reqPage, String ucode) {
		Paging paging = new Paging();
		paging.setTotalRec(mypageDAO.total_list(ucode));
		paging.setReqPage(reqPage);
		paging.setRecNumPerPage(10);
		logger.info(String.valueOf(paging.getStr_num()));
		logger.info(String.valueOf(paging.getEnd_num()));

		return mypageDAO.mylist(ucode, paging.getStr_num(), paging.getEnd_num());
	}

	@Override
	public Paging mylist_paging(int reqPage, String ucode) {

		Paging paging = new Paging();
		paging.setReqPage(reqPage);
		paging.setPageNumPerPage(10);
		paging.setRecNumPerPage(10);
		paging.setTotalRec(mypageDAO.total_list(ucode));
		paging.calculatefinalEndPage();
		paging.setNext(paging.isNext());
		paging.setPrev(paging.isPrev());
		paging.napPage();

		return paging;
	}

	@Override
	public int del_list(String lnum) {

		return mypageDAO.del_list(lnum);
	}

	@Override
	public List<IcategoryVO> get_category() {
		// TODO Auto-generated method stub
		return mypageDAO.get_category();
	}

	@Override
	public List<ListingVO> get_listing(String lnum) {
		// TODO Auto-generated method stub
		return mypageDAO.get_listing(lnum);
	}

	@Override
	public int item_check(String linum) {
		// TODO Auto-generated method stub
		return mypageDAO.item_check(linum);
	}

	@Override
	public int item_uncheck(String linum) {
		// TODO Auto-generated method stub
		return mypageDAO.item_uncheck(linum);
	}

	@Override
	// 마이포스트
	public List<BoardVO> mypost(int reqPage, String ucode) {
		Paging paging = new Paging();
		paging.setTotalRec(mypageDAO.total_post(ucode));
		paging.setReqPage(reqPage);
		paging.setRecNumPerPage(10);
		logger.info(String.valueOf(paging.getStr_num()));
		logger.info(String.valueOf(paging.getEnd_num()));

		return mypageDAO.mypost(ucode, paging.getStr_num(), paging.getEnd_num());
	}

	@Override
	// 마이 포스트 페이징
	public Paging mypost_paging(int reqPage, String ucode) {

		Paging paging = new Paging();
		paging.setReqPage(reqPage);
		paging.setPageNumPerPage(10);
		paging.setRecNumPerPage(10);
		paging.setTotalRec(mypageDAO.total_post(ucode));
		paging.calculatefinalEndPage();
		paging.setNext(paging.isNext());
		paging.setPrev(paging.isPrev());
		paging.napPage();

		return paging;
	}

	@Override
	public int del_post(String bnum) {
		return mypageDAO.del_post(bnum);
	}
}
