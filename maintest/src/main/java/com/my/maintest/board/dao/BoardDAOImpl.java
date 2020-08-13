package com.my.maintest.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	SqlSession sqlSession;

	
	//게시판 카테고리 조회 
	@Override
	public List<BcategoryVO> selectBcategory() {
		List<BcategoryVO> list = sqlSession.selectList("mappers.BoardDAO-mapper.selectBcategory");
		return list;
	}
	
	//게시판 말머리 조회
	@Override
	public List<HeadIdCategoryVO> selectHeadIdCategory() {		
		return sqlSession.selectList("mappers.BoardDAO-mapper.selectHeadIdCategory");
	}

//전체 게시글 조회 (default)
	@Override
	public List<BoardVO> selectArticles() {		
		return	sqlSession.selectList("mappers.BoardDAO-mapper.selectArticles");
	}

	// 게시글 등록
	@Override
	public int insertArticle(BoardVO boardVO) {
		int result = 0;
		result = sqlSession.insert("mappers.BoardDAO-mapper.insertArticle", boardVO);
		
		return result;
	}
	
	
// 게시글 열람
@Override
public BoardVO selectArticle(long bnum) {
	return sqlSession.selectOne("mappers.BoardDAO-mapper.selectArticle", bnum);
}
//게시글 조회수 갱신
@Override
public int updateBhits(long bnum) {
	return sqlSession.update("mappers.BoardDAO-mapper.updateBhits",bnum);
}

	// 게시글 수정
	@Override
	public int updateArticle(BoardVO boardVO) {
		
		return sqlSession.update("mappers.BoardDAO-mapper.updateArticle", boardVO);		
	}

		// 게시글 삭제
		@Override
		public int deleteArticle(long bnum) {			
			return sqlSession.delete("mappers.BoardDAO-mapper.deleteArticle", bnum);
		}
		
		
		//게시글 답글 등록
		@Override
		public int insertRepliedArticle(BoardVO boardVO) {	
			return		sqlSession.insert("mappers.BoardDAO-mapper.insertRepliedArticle",boardVO );
		}
		//게시글 bstep +1 처리 (답글 등록 순위)  높을수록 오래된 답글
		@Override
		public int updateBstep(long bgroup, long bstep) {
			Map<String, Object> map = new HashMap<>();
			map.put("bgroup", bgroup);
			map.put("bstep", bstep);					
			return sqlSession.update("mappers.BoardDAO-mapper.updateBstep", map );
		}
		





	}







	




