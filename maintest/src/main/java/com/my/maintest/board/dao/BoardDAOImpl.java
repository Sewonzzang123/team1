package com.my.maintest.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.my.maintest.board.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	SqlSession sqlSession;

//전체 게시글 조회 (default)
	@Override
	public List<BoardVO> selectArticles() {		
		return	sqlSession.selectList("mappers.BoardDAO-mapper.selectArticles");
	}

	// 게시글 열람
	@Override
	public BoardVO selectArticle(long bNum) {
		return null;
	}

	// 게시글 등록
	@Override
	public int insertArticle(BoardVO boardVO) {
		int result = 0;
		result = sqlSession.insert("mappers.BoardDAO-mapper.insertArticle", boardVO);
		return result;
	}

	// 게시글 수정
	@Override
	public void updateArticle(long bNum) {

	}

	// 게시글 삭제
	@Override
	public void deleteArticle(long bNum) {
		// TODO Auto-generated method stub

	}

}