package com.my.maintest.admin.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Inject
	private SqlSession sqlSession;

	@Override
	public List<BcategoryVO> getCate() {

		return sqlSession.selectList("mappers.AdminDAO-mapper.getCate");
	}

	@Override
	public int delBoard(String catnum) {

		return sqlSession.delete("mappers.AdminDAO-mapper.delBoard", catnum);
	}

	@Override
	public int createBoard(BcategoryVO bcategoryVO) {
		return sqlSession.insert("mappers.AdminDAO-mapper.createBoard", bcategoryVO);
	}

	@Override
	public int setBoard(BcategoryVO bcategoryVO) {
		return sqlSession.update("mappers.AdminDAO-mapper.setBoard", bcategoryVO);
	}

	@Override
	public List<HeadIdCategoryVO> getHead() {

		return sqlSession.selectList("mappers.AdminDAO-mapper.getHead");
	}

	@Override
	public int setHead(HeadIdCategoryVO headIdCategoryVO) {
		// TODO Auto-generated method stub
		return sqlSession.insert("mappers.AdminDAO-mapper.setHead", headIdCategoryVO);
	}

	@Override
	public int delHead(String hidnum) {

		return sqlSession.delete("mappers.AdminDAO-mapper.delHead", hidnum);
	}

}
