package com.my.maintest.board.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.my.maintest.board.vo.BCommentVO;
@Repository
public class BCommentDAOImpl implements BCommentDAO{	
	
	@Inject
	SqlSession sqlSession;
	
	//댓글 등록(inner Comment)
	@Override
	public int insertBComment(BCommentVO bCommentVO) {
		return sqlSession.insert("mappers.BCommentDAO-mapper.insertBComment", bCommentVO);
	}
	
	

}
