package com.my.maintest.member.svc;

import com.my.maintest.member.vo.MemberVO;

public interface MemberSVC {

	// 회원가입
	public int singup(MemberVO memberVO);

	// 로그인
	public int login();

	// 로그아웃
	public int logout();

	// 아이디 찾기
	public int findID();

	// 비밀번호 찾기
	public int findPW();

	// 개별 조회
	public MemberVO listOneMember(String id);
}
