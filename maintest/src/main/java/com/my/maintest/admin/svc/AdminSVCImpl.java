package com.my.maintest.admin.svc;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.my.maintest.admin.dao.AdminDAO;
import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;

@Repository
public class AdminSVCImpl implements AdminSVC {

	@Inject
	AdminDAO adminDAO;

	@Override
	public List<BcategoryVO> getCate() {
		// TODO Auto-generated method stub
		return adminDAO.getCate();
	}

	@Override
	public int delBoard(String catnum) {

		return adminDAO.delBoard(catnum);
	}

	@Override
	public int createBoard(BcategoryVO bcategoryVO) {
		// TODO Auto-generated method stub
		return adminDAO.createBoard(bcategoryVO);
	}

	@Override
	public int setBoard(BcategoryVO bcategoryVO) {
		// TODO Auto-generated method stub
		return adminDAO.setBoard(bcategoryVO);
	}

	@Override
	public List<HeadIdCategoryVO> getHead() {

		return adminDAO.getHead();
	}

	@Override
	public int setHead(HeadIdCategoryVO headIdCategoryVO) {
		// TODO Auto-generated method stub
		return adminDAO.setHead(headIdCategoryVO);
	}

	@Override
	public int delHead(String hidnum) {

		return adminDAO.delHead(hidnum);
	}
}
