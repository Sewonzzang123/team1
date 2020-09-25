package com.my.maintest.admin.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;
import com.my.maintest.item.vo.ItemCategoryVO;
import com.my.maintest.item.vo.ItemVO;
import com.my.maintest.member.vo.MemberVO;

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

	@Override
	public List<ItemCategoryVO> getIcate() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mappers.AdminDAO-mapper.getIcate");
	}

	@Override
	public List<ItemVO> getItem() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mappers.AdminDAO-mapper.getItem");
	}

	@Override
	public int delIcate(String ca_num) {
		// TODO Auto-generated method stub
		return sqlSession.delete("mappers.AdminDAO-mapper.delIcate", ca_num);
	}

	@Override
	public int delItem(String i_num) {
		// TODO Auto-generated method stub
		return sqlSession.delete("mappers.AdminDAO-mapper.delItem", i_num);
	}

	@Override
	public String getCa_num() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mappers.AdminDAO-mapper.getCa_num");
	}

	@Override
	public int setIcate(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.insert("mappers.AdminDAO-mapper.setIcate", map);
	}

	@Override
	public int modifyIcate(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.insert("mappers.AdminDAO-mapper.modifyIcate", map);
	}

	@Override
	public int setItem(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.insert("mappers.AdminDAO-mapper.setItem", map);
	}

	@Override
	public int total_member() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mappers.AdminDAO-mapper.total_member");
	}

	@Override
	public int total_member(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mappers.AdminDAO-mapper.total_member_ck", map);
	}

	@Override
	public List<MemberVO> memberlist(HashMap<String, String> map) {

		return sqlSession.selectList("mappers.AdminDAO-mapper.memberlist", map);
	}

	@Override
	public List<MemberVO> memberlist_ck(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mappers.AdminDAO-mapper.memberlist_ck", map);
	}

	@Override
	public int exit_member(String ucode) {
		// TODO Auto-generated method stub
		return sqlSession.update("mappers.AdminDAO-mapper.exit_member", ucode);
	}

}
