package com.my.maintest.member.svc;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.my.maintest.member.dao.MemberDAO;
import com.my.maintest.member.vo.MemberVO;

@Repository
public class MemberSVCImpl implements MemberSVC {

	@Inject
	MemberDAO memberDAO;

	// 회원가입
	@Override
	public int singup(MemberVO memberVO) {

		return memberDAO.singup(memberVO);
	}

	// 로그인
	@Override
	public int login() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int logout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findPW() {
		// TODO Auto-generated method stub
		return 0;
	}

	// 개별 조회
	@Override
	public MemberVO listOneMember(String id) {

		return memberDAO.listOneMember(id);
	}

}
