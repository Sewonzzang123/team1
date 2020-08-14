package com.my.maintest.mypage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.my.maintest.member.vo.MemberVO;
import com.my.maintest.mypage.vo.IcategoryVO;
import com.my.maintest.mypage.vo.ListingVO;
import com.my.maintest.mypage.vo.MylistVO;

@Repository
public class MypageDAOImpl implements MypageDAO {

	@Inject
	private SqlSession sqlSession;

	@Override
	public int modify(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return sqlSession.update("mappers.MypageDAO-mapper.modify", memberVO);
	}

	@Override
	public int changePW(MemberVO memberVO) {

		return sqlSession.update("mappers.MypageDAO-mapper.changePW", memberVO);
	}

	@Override
	public int withdraw(String id) {

		return sqlSession.delete("mappers.MypageDAO-mapper.withdraw", id);
	}

	@Override
	public List<MylistVO> mylist(String ucode, int str_num, int end_num) {
		Map<String, String> map = new HashMap();
		map.put("ucode", ucode);
		map.put("str_num", String.valueOf(str_num));
		map.put("end_num", String.valueOf(end_num));

		return sqlSession.selectList("mappers.MypageDAO-mapper.mylist", map);
	}

	@Override
	public int total_list(String ucode) {

		return sqlSession.selectOne("mappers.MypageDAO-mapper.total_list", ucode);
	}

	@Override
	public int del_list(String lnum) {
		// TODO Auto-generated method stub
		return sqlSession.delete("mappers.MypageDAO-mapper.del_list", lnum);
	}

	@Override
	public List<IcategoryVO> get_category() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mappers.MypageDAO-mapper.get_category");
	}

	@Override
	public List<ListingVO> get_listing(String lnum) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mappers.MypageDAO-mapper.get_listing", lnum);
	}

	@Override
	public int total_item(String lnum) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mappers.MypageDAO-mapper.total_item", lnum);
	}

	@Override
	public int checked_item(String lnum) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mappers.MypageDAO-mapper.checked_item", lnum);
	}

}
