package com.my.maintest.mypage.svc;

import java.util.List;

import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.common.paging.Paging;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ListVO;
import com.my.maintest.item.vo.ListingVO;
import com.my.maintest.member.vo.MemberVO;

public interface MypageSVC {
	// 회원 정보 수정
	public int modify(MemberVO memberVO);

	// 비밀번호 변경
	public int changePW(MemberVO memberVO);

	// 회원탈퇴
	public int withdraw(String id);

	// 마이리스트
	public List<ListVO> mylist(int reqPage, String ucode);

	// 마이리스트 페이징
	public Paging mylist_paging(int reqPage, String ucode);

	// 리스트 삭제
	public int del_list(String lnum);

	// 카테고리 불러오기
	public List<ItemCategoryVO> get_category();

	// 아이템 불러오기
	public List<ListingVO> get_listing(String lnum);

	// 아이템 갯수 구하기
	public int item_check(String linum);

	// 체크 아이템 갯수 구하기
	public int item_uncheck(String linum);

	// 마이포스트
	public List<BoardVO> mypost(int reqPage, String ucode);

	//마이 포스트 페이징
	public Paging mypost_paging(int reqPage, String ucode);
	
	// 글 삭제
	public int del_post(String bnum);
}
