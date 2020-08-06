package com.my.maintest.admin.dao;

import java.util.List;

import com.my.maintest.board.vo.AnnotationVO;
import com.my.maintest.board.vo.BcategoryVO;

public interface AdminDAO {

	//게시판 카테고리 읽기
	public List<BcategoryVO> getCate();
	
	//게시판 말머리 읽기
	public List<AnnotationVO> getAnn();
	
	//게시판 삭제
	public int delBoard(int canum);
	
	//게시판 생성
	public int createBoard();
	
}
