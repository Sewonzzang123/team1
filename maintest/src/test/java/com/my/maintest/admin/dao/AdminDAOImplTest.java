package com.my.maintest.admin.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
public class AdminDAOImplTest {

	private final static Logger logger = LoggerFactory.getLogger(AdminDAOImplTest.class);

	@Inject
	AdminDAO adminDAO;

	@Test
	@Disabled
	public void getCate() {
		List<BcategoryVO> bcategoryVO = adminDAO.getCate();
		logger.info(bcategoryVO.toString());
		logger.info(bcategoryVO.get(0).getCatname());
	}

	@Test
	@Disabled
	public void delBoard() {
		String catnum = "109";
		int x = adminDAO.delBoard(catnum);
	}

	@Test
	@Disabled
	public void createBoard() {
		BcategoryVO bcategoryVO = new BcategoryVO();
		bcategoryVO.setBtype("blog");
		bcategoryVO.setCatname("테스트용");
		bcategoryVO.setCatnum("new");
		bcategoryVO.setBmemo("new");
		int x = adminDAO.createBoard(bcategoryVO);
	}

	@Test
	@Disabled
	public void setBoard() {
		BcategoryVO bcategorVO = new BcategoryVO();
		bcategorVO.setBtype("blog");
		bcategorVO.setCatname("테스트용");
		bcategorVO.setCatnum("34");
		int x = adminDAO.setBoard(bcategorVO);
	}

	@Test
	@Disabled
	public void getHead() {
		List<HeadIdCategoryVO> headIdCategoryVO = adminDAO.getHead();
		logger.info(headIdCategoryVO.toString());
	}

	@Test
	@Disabled
	public void setHead() {
		HeadIdCategoryVO headIdCategoryVO = new HeadIdCategoryVO();
		headIdCategoryVO.setHidname("테스트2");
		headIdCategoryVO.setCatnum("34");

		adminDAO.setHead(headIdCategoryVO);

	}

	@Test
	@Disabled
	public void delHead() {
		String hidnum = "5";
		adminDAO.delHead(hidnum);
	}

	@Test
	@Disabled
	public void getIcate() {
		logger.info(adminDAO.getIcate().toString());
	}

	@Test
	@Disabled
	public void getItem() {
		logger.info(adminDAO.getItem().toString());
	}

	@Test
	@Disabled
	public void delIcate() {
		String ca_num = "5";
		adminDAO.delIcate(ca_num);
		logger.info("완료");
	}

	@Test
	@Disabled
	public void delItem() {
		String i_num = "72";
		adminDAO.delIcate(i_num);
		logger.info("완료");
	}

	@Test
	@Disabled
	public void total_member() {
		logger.info(String.valueOf(adminDAO.total_member()));
	}

	@Test
	@Disabled
	public void total_member_ck() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchType", "name");
		map.put("keyword", "정민");
		logger.info(String.valueOf(adminDAO.total_member(map)));
	}

	@Test
	@Disabled
	public void memberlist() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("end_num", "1");
		map.put("str_num", "5");
		logger.info(adminDAO.memberlist(map).toString());
	}

	@Test
//@Disabled
	public void memberlist_ck() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("end_num", "1");
		map.put("str_num", "5");
		map.put("searchType", "name");
		map.put("keyword", "정민");
		logger.info(adminDAO.memberlist_ck(map).toString());
	}
}
