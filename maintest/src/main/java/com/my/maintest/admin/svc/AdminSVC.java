package com.my.maintest.admin.svc;

import java.util.HashMap;
import java.util.List;

import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;
import com.my.maintest.common.paging.Paging;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.member.vo.MemberVO;

public interface AdminSVC {

	// 게시판 카테고리 읽기
	public List<BcategoryVO> getCate();

	// 게시판 삭제
	public int delBoard(String catnum);

	// 게시판 생성
	public int createBoard(BcategoryVO bcategoryVO);

	// 게시판 수정
	public int setBoard(BcategoryVO bcategoryVO);

	// 말머리 읽기
	public List<HeadIdCategoryVO> getHead();

	// 말머리 생성
	public int setHead(HeadIdCategoryVO headIdCategoryVO);

	// 말머리 삭제
	public int delHead(String hidnum);

	// 아이템 카테고리 읽기
	public List<ItemCategoryVO> getIcate();

	// 아이템 가져오기
	public List<ItemVO> getItem();

	// 아이템 카테고리 삭제
	public int delIcate(String ca_num);

	// 아이템 삭제
	public int delItem(String i_num);

	// 카테고리 넘버 가져오기
	public String getCa_num();

	// 카테고리 생성
	public int setIcate(HashMap<String, String> map);

	// 카테고리 수정
	public int modifyIcate(HashMap<String, String> map);

	// 아이템 추가
	public int setItem(HashMap<String, String> map);

	// 회원 리스트
	public List<MemberVO> memberlist(int reqPage);

	// 회원 리스트 (체크)
	public List<MemberVO> memberlist(int reqPage, String searchType, String keyword);

	// 페이징
	public Paging paging(int reqPage);

	// 페이징 (체크)
	public Paging paging(int reqPage, String searchType, String keyword);

	// 강제탈퇴
	public int exit_member(String ucode);

}
