package com.my.maintest.member.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.my.maintest.member.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;

	// 회원가입
	@Override
	public int singup(MemberVO memberVO) {

		return sqlSession.insert("mappers.MemberDAO-mapper.signup", memberVO);
	}

	// 아이디 찾기
	@Override
	public int findID(String id, String tel) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 비밀번호 찾기
	@Override
	public int findPW() {
		// TODO Auto-generated method stub
		return 0;
	}

	// 개별 조회
	@Override
	public MemberVO listOneMember(String id) {

		return sqlSession.selectOne("mappers.MemberDAO-mapper.listOneMember", id);
	}

}
