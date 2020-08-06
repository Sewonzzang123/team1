package com.my.maintest.admin.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.my.maintest.board.vo.BcategoryVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Inject
	private SqlSession sqlSession;

	@Override
	public List<BcategoryVO> getCate() {

		return sqlSession.selectList("mappers.MypageDAO-mapper.getCate");
	}

	@Override
	public int delBoard(int catnum) {

		return sqlSession.delete("mappers.MypageDAO-mapper.delBoard", catnum);
	}

	@Override
	public int createBoard() {
		// TODO Auto-generated method stub
		return 0;
	}

}
