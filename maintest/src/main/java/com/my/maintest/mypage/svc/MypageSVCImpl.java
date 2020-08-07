package com.my.maintest.mypage.svc;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.my.maintest.member.vo.MemberVO;
import com.my.maintest.mypage.dao.MypageDAO;

@Repository
public class MypageSVCImpl implements MypageSVC {

	@Inject
	MypageDAO mypageDAO;

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

}
