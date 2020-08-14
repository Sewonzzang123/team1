package com.my.maintest.mypage.svc;

import java.util.List;

import com.my.maintest.common.paging.Paging;
import com.my.maintest.member.vo.MemberVO;
import com.my.maintest.mypage.vo.IcategoryVO;
import com.my.maintest.mypage.vo.ListingVO;
import com.my.maintest.mypage.vo.MylistVO;

public interface MypageSVC {
	// 회원 정보 수정
	public int modify(MemberVO memberVO);

	// 비밀번호 변경
	public int changePW(MemberVO memberVO);

	// 회원탈퇴
	public int withdraw(String id);

	// 마이리스트
	public List<MylistVO> mylist(int reqPage, String ucode);

	// 페이징
	public Paging paging(int reqPage, String ucode);

	// 리스트 삭제
	public int del_list(String lnum);

	// 카테고리 불러오기
	public List<IcategoryVO> get_category();

	// 아이템 불러오기
	public List<ListingVO> get_listing(String lnum);

	// 아이템 갯수 구하기
	public int total_item(String lnum);

	// 체크 아이템 갯수 구하기
	public int checked_item(String lnum);
}
