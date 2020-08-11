package com.my.maintest.mypage.svc;

import java.util.List;

import com.my.maintest.member.vo.MemberVO;
import com.my.maintest.mypage.vo.MylistVO;

public interface MypageSVC {
	// 회원 정보 수정
	public int modify(MemberVO memberVO);

	// 비밀번호 변경
	public int changePW(MemberVO memberVO);

	// 회원탈퇴
	public int withdraw(String id);

	//마이리스트
	public List<MylistVO> mylist(String ucode);
}
