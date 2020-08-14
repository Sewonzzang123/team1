package com.my.maintest.mypage.svc;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.my.maintest.common.paging.Paging;
import com.my.maintest.member.vo.MemberVO;
import com.my.maintest.mypage.dao.MypageDAO;
import com.my.maintest.mypage.vo.IcategoryVO;
import com.my.maintest.mypage.vo.ListingVO;
import com.my.maintest.mypage.vo.MylistVO;

@Repository
public class MypageSVCImpl implements MypageSVC {

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
		paging.setReqPage(reqPage);
		paging.setRecNumPerPage(10);

		return mypageDAO.mylist(ucode, paging.getStr_num(), paging.getEnd_num());
	}

	@Override
	public Paging paging(int reqPage, String ucode) {

		Paging paging = new Paging();
		paging.setReqPage(reqPage);
		paging.setPageNumPerPage(10);
		paging.setRecNumPerPage(10);
		paging.setTotalRec(mypageDAO.total_list(ucode));
		paging.calculatefinalEndPage();
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
	public int total_item(String lnum) {
		// TODO Auto-generated method stub
		return mypageDAO.total_item(lnum);
	}

	@Override
	public int checked_item(String lnum) {
		// TODO Auto-generated method stub
		return mypageDAO.checked_item(lnum);
	}

}
