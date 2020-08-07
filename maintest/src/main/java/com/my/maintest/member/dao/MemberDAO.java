package com.my.maintest.member.dao;

import com.my.maintest.member.vo.MemberVO;

public interface MemberDAO {

	// 회원가입
	public int singup(MemberVO memberVO);

	// 아이디 찾기
	public int findID(String id, String tel);

	// 비밀번호 찾기
	public int findPW();

	// 개별 조회
	public MemberVO listOneMember(String id);
}
