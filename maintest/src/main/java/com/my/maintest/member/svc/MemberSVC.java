package com.my.maintest.member.svc;

import java.sql.Date;

import com.my.maintest.member.vo.MemberVO;

public interface MemberSVC {

	// 회원가입
	public int singup(MemberVO memberVO);

	// 로그인
	public int login();

	// 로그아웃
	public int logout();

	// 아이디 찾기
	public String findID(String name, String tel);

	// 비밀번호 찾기
	public String findPW(String id, String tel);

	// 개별 조회
	public MemberVO listOneMember(String id);

	// 메일 전송
	public void sendMail(String to, String subject, String body);

	// 자동 로그인 등록
	public int keepLogin(String id, String sessionId, Date age);

	// 자동 로그인 확인
	public MemberVO checkUserWithSessionKey(String sessionId);

}
