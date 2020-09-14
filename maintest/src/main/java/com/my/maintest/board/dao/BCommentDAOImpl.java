package com.my.maintest.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.my.maintest.board.vo.BCoVoteVO;
import com.my.maintest.board.vo.BCommentVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository
public class BCommentDAOImpl implements BCommentDAO{	
	
	

	@Inject
	SqlSession sqlSession;
	
	//부모 댓글 등록 (inner) 
	@Override
	public int insertBComment(BCommentVO bCommentVO) {
		return sqlSession.insert("mappers.BCommentDAO-mapper.insertBComment", bCommentVO);
	}
	
	//자식 댓글 등록 (inner) 
	// 기 등록된 자식댓글의 BCSTEP +1 처리 
	@Override
	public int updateBcstep(long bcgroup) {		
		return sqlSession.update("mappers.BCommentDAO-mapper.updateBcstep", bcgroup);
	}
	//자식  댓글 등록 처리
	@Override
	public int insertReBComment(BCommentVO bCommentVO) {
		return sqlSession.insert("mappers.BCommentDAO-mapper.insertReBComment", bCommentVO);
	}
	
	
	//댓글 목록 불러오기
	@Override
	public List<BCommentVO> selectBComments( long bnum, int recFrom, int recTo) {
		 Map<String, Object> map = new HashMap<>(); 		
		 log.info(" bnum    "  + String.valueOf(bnum));
		 log.info(" recFrom    "  +String.valueOf(recFrom));
		 log.info(" recTo    "  +String.valueOf(recTo));
		 
		 map.put("bnum", bnum);
		 map.put("recFrom", recFrom);
		 map.put("recTo", recTo);		 
		return sqlSession.selectList("mappers.BCommentDAO-mapper.selectBComments", map);
	}

	
	//댓글 수정
	@Override
	public int updateBccontent(BCommentVO bCommentVO) {
		return sqlSession.update("mappers.BCommentDAO-mapper.updateBccontent", bCommentVO);
	}
	//댓글 삭제 
	@Override
	public int deleteBComment(long bcnum) {
		return sqlSession.delete("mappers.BCommentDAO-mapper.deleteBComment", bcnum);
	}

	//댓글 선호도 투표
	@Override
	public int updateVote(BCoVoteVO bCoVoteVO) {

		return sqlSession.update("mappers.BCommentDAO-mapper.updateVote", bCoVoteVO);
	}
//good 투표수 갱신
	@Override
	public int updateGoodQnty(long bcnum) {
		return sqlSession.update("mappers.BCommentDAO-mapper.updateGoodQnty" , bcnum);
	}
//ad 투표수 갱신
	@Override
	public int updateBadQnty(long bcnum) {
		return sqlSession.update("mappers.BCommentDAO-mapper.updateBadQnty" , bcnum);
	}

	

}
