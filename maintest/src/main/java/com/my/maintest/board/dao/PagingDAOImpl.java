package com.my.maintest.board.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PagingDAOImpl  implements PagingDAO{

	@Inject
	SqlSession sqlSession;
	
	
	
//페이징
		//레코드 총 수량 구하기 
		@Override
		public int selectRecQnty_Blog(long catnum, String searchType, String searchKeyword) {		
			Map<String, Object> map = new HashMap<>();
			
			
			map.put("catnum", catnum);
			map.put("searchType", searchType);
			map.put("searchKeyword", searchKeyword);
			return sqlSession.selectOne("mappers.PagingDAO-mapper.selectRecQnty_Blog", map);
		}

		
		
		@Override
		public int selectRecQnty_Album(long catnum, String searchType, String searchKeyword) {		
			Map<String, Object> map = new HashMap<>();
			
			
			map.put("catnum", catnum);
			map.put("searchType", searchType);
			map.put("searchKeyword", searchKeyword);
			return sqlSession.selectOne("mappers.PagingDAO-mapper.selectRecQnty_Album", map);
		}
}
