package com.my.maintest.admin.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.maintest.board.vo.BcategoryVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
public class AdminDAOImplTest {

	private final static Logger logger = LoggerFactory.getLogger(AdminDAOImplTest.class);

	@Inject
	@Qualifier("adminDAOImpl")
	AdminDAO adminDAO;

	@Test
//	@Disabled
	public void getCate() {
		List<BcategoryVO> bcategoryVO = adminDAO.getCate();
		logger.info(bcategoryVO.toString());
		logger.info(bcategoryVO.get(0).getCatname());
	}

	@Test
	@Disabled
	public void delBoard() {
		String catnum = "4";
		int x = adminDAO.delBoard(catnum);
	}

	@Test
	@Disabled
	public void createBoard() {
		int x = adminDAO.createBoard();
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
}
