package com.my.maintest.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardFileVO;
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;
import com.my.maintest.item.vo.ListingVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	SqlSession sqlSession;

	
	//게시판 카테고리 조회 
	@Override
	public List<BcategoryVO> selectBcategory() {
		List<BcategoryVO> list = sqlSession.selectList("mappers.BoardDAO-mapper.selectBcategory");
		return list;
	}
	
	//게시판 말머리 조회
	@Override
	public List<HeadIdCategoryVO> selectHeadIdCategory(long catnum) {		
		return sqlSession.selectList("mappers.BoardDAO-mapper.selectHeadIdCategory", catnum);
	}

//전체 게시글 조회 (default)
	@Override
	public List<BoardVO> selectArticles() {		
		return	sqlSession.selectList("mappers.BoardDAO-mapper.selectArticles0");
	}
	
	//전체 게시글 조회 + 페이징	
	@Override
	public List<BoardVO> selectArticles(int catnum, long recFrom, long recTo) {
				
		Map<String, Object> map = new HashMap<>();
		
		map.put("recFrom", recFrom);
		map.put("recTo", recTo);
		map.put("catnum", catnum);
		
		return sqlSession.selectList("mappers.BoardDAO-mapper.selectArticles", map);
	}
	
	//게시판 타입 조회 
	@Override
	public BcategoryVO selectBtype(long catnum) {		
		return sqlSession.selectOne("mappers.BoardDAO-mapper.selectBtype", catnum);
	}
	
	
//갤러리게시판(catnum:2)  썸네일 첨부파일 전체 정보 불러오기 
	@Override
	public List<BoardFileVO>selectThumbnailFiles(long catnum) {	
		return sqlSession.selectList("mappers.BoardDAO-mapper.selectThumbnailFiles", catnum)  ;
	}




//전체게시글 조회 + 페이징 + 검색어 (블로그타입게시판 / 레코드 범위 / 검색타입 / 검색어)
	@Override
	public List<BoardVO> selectArticlesWithKey_Blog( long catnum,  long recFrom,long recTo,String searchType, String searchKeyword) {
		Map<String,Object> map = new HashMap<String, Object>();				
		map.put("catnum",catnum);
		map.put("recFrom", recFrom);
		map.put("recTo", recTo);
		map.put("searchType", searchType);
		map.put("searchKeyword", searchKeyword);
		
		
		return sqlSession.selectList("mappers.BoardDAO-mapper.selectArticlesWithKey_Blog", map ) ;
	}

	//전체게시글 조회 + 페이징 + 검색어 (앨범타입게시판 (썸네일 포함) / 레코드 범위 / 검색타입 / 검색어)
	@Override
	public List<BoardVO> selectArticlesWithKey_Album( long catnum,  long recFrom,long recTo,String searchType, String searchKeyword) {
		Map<String,Object> map = new HashMap<String, Object>();				
		map.put("catnum",catnum);
		map.put("recFrom", recFrom);
		map.put("recTo", recTo);
		map.put("searchType", searchType);
		map.put("searchKeyword", searchKeyword);
		
		
		
		return sqlSession.selectList("mappers.BoardDAO-mapper.selectArticlesWithKey_Album", map ) ;
	}
	
	
	
	// 게시글 등록
	@Override
	public int insertArticle(BoardVO boardVO) {

		return sqlSession.insert("mappers.BoardDAO-mapper.insertArticle", boardVO);
	}
	//썸네일 경로저장
	@Override
	public int updateThumbPath(BoardVO boardVO) {
		return sqlSession.update("mappers.BoardDAO-mapper.updateThumbPath", boardVO);
	}
	
//첨부파일 등록
	@Override
	public int insertFiles(BoardFileVO boardFileVO) {		
		return sqlSession.insert("mappers.BoardDAO-mapper.insertFiles", boardFileVO);
	}
	
		
// 게시글 열람
@Override
public BoardVO selectArticle(long bnum) {
	return sqlSession.selectOne("mappers.BoardDAO-mapper.selectArticle", bnum);
}

//게시글 열람 +첨부파일
@Override
public List<BoardFileVO> selectFiles(long bnum) {	
	List<BoardFileVO> files = null;	
	files =(sqlSession.selectList("mappers.BoardDAO-mapper.selectFiles", bnum));
		
	return files;
}

//게시글 조회수 갱신
@Override
public int updateBhits(long bnum) {
	
	System.out.println("조회수 갱신  ============== ");
	
	return sqlSession.update("mappers.BoardDAO-mapper.updateBhits",bnum);
}

	// 게시글 수정
	@Override
	public int updateArticle(BoardVO boardVO) {
		
		return sqlSession.update("mappers.BoardDAO-mapper.updateArticle", boardVO);		
	}
	
	
	

	//첨부파일 일부 삭제 
	@Override
	public int deleteFile(long fid, String isThumb) {				
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fid", fid);
		map.put("isThumb", isThumb);		
		
		
		return sqlSession.delete("mappers.BoardDAO-mapper.deleteFile",map);
	}
//첨부파일 다운로드
	@Override
	public BoardFileVO selectFileToDwLoad(long fid) {
	
		return sqlSession.selectOne("mappers.BoardDAO-mapper.selectFileToDwLoad", fid);
	}

		// 게시글 삭제
		@Override
		public int deleteArticle(long bnum) {			
			return sqlSession.delete("mappers.BoardDAO-mapper.deleteArticle", bnum);
		}
		
		
		//첨부파일 전체 삭제 
		//cascade 외래키 연동으로 자동삭제
		
		
		//게시글 답글 등록
		@Override
		public int insertRepliedArticle(BoardVO boardVO) {	
			
			return		sqlSession.insert("mappers.BoardDAO-mapper.insertRepliedArticle",boardVO );
		}
		//게시글 bstep +1 처리 (답글 등록 순위)  높을수록 오래된 답글
		@Override
		public int updateBstep(long bgroup, long bstep) {
			Map<String, Object> map = new HashMap<>();
			map.put("bgroup", bgroup);
			map.put("bstep", bstep);					
			return sqlSession.update("mappers.BoardDAO-mapper.updateBstep", map );
		}
		
	// 텍스트 이미지 게시판 수정 시작
		// 썸네일 가져오기
		@Override
		public List<BoardFileVO> getThumbnail() {

			return sqlSession.selectList("mappers.BoardDAO-mapper.getThumbnail");
		}
		//게시글 리스트 불러오기
		@Override
		public List<ListingVO> loadListing(long bnum){
			List<ListingVO> list = null;
			list = (sqlSession.selectList("mappers.BoardDAO-mapper.loadListing", bnum));
			return list;
		};
		
		//리스트 등록
		@Override
		public int insertBlisting(ListingVO listingVO) {
			return sqlSession.insert("mappers.BoardDAO-mapper.insertBlisting", listingVO);
		};
		

	}







	




