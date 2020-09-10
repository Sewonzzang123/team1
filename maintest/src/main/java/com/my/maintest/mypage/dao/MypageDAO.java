package com.my.maintest.mypage.dao;

import java.util.List;

import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ListVO;
import com.my.maintest.item.vo.ListingVO;
import com.my.maintest.member.vo.MemberVO;

public interface MypageDAO {

	// 회원 정보 수정
	public int modify(MemberVO memberVO);

	// 비밀번호 변경
	public int changePW(MemberVO memberVO);

	// 회원탈퇴
	public int withdraw(String id);

	// 마이리스트
	public List<ListVO> mylist(String ucode, int str_num, int end_num);

	// 총 리스트 수 구하기
	public int total_list(String ucode);

	// 리스트 삭제
	public int del_list(String lnum);

	// 아이템 카테고리 불러오기
	public List<ItemCategoryVO> get_category();

	// 아이템 불러오기
	public List<ListingVO> get_listing(String lnum);

	// 아이템 체크
	public int item_check(String linum);

	// 아이템 체크 해제
	public int item_uncheck(String linum);

	// 총 게시글 수 구하기
	public int total_post(String ucode);

	// 마이포스트
	public List<BoardVO> mypost(String ucode, int str_num, int end_num);

	// 글 삭제
	public int del_post(String bnum);
}
