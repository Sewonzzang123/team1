package com.my.maintest.mypage.dao;

import com.my.maintest.member.vo.MemberVO;

public interface MypageDAO {

	// 회원 정보 수정
	public int modify(MemberVO memberVO);

	// 비밀번호 변경
	public int changePW(MemberVO memberVO);

	// 회원탈퇴
	public int withdraw(String id);
}
