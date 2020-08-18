package com.my.maintest.member.dao;

import java.util.Map;

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
	public String findID(Map<String, String> map) {

		return sqlSession.selectOne("mappers.MemberDAO-mapper.findID", map);
	}

	// 비밀번호 찾기
	@Override
	public String findPW(Map<String,String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mappers.MemberDAO-mapper.findPW", map);
	}

	// 개별 조회
	@Override
	public MemberVO listOneMember(String id) {

		return sqlSession.selectOne("mappers.MemberDAO-mapper.listOneMember", id);
	}

}
