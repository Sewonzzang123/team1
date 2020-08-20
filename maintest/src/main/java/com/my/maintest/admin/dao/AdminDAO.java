package com.my.maintest.admin.dao;

import java.util.List;

import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;

public interface AdminDAO {

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
}
