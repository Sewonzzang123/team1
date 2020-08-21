package com.my.maintest.board.svc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.my.maintest.board.dao.BoardDAO;
import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardFileVO;
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;
import com.my.maintest.common.paging.PageCriteria;
import com.my.maintest.common.paging.PagingComponent;
import com.my.maintest.common.paging.RecordCriteria;
import com.my.maintest.common.paging.SearchCriteria;

@Service
public class BoardSVCImpl implements BoardSVC {

	
	@Inject
	BoardDAO boardDAO;
	
	//게시판 카테고리 조회 
	@Override
	public List<BcategoryVO> selectBcategory() {		 
		return boardDAO.selectBcategory();
	}
	//게시판 말머리 조회
	@Override
	public List<HeadIdCategoryVO> selectHeadIdCategory(int catnum) {
		
		return  boardDAO.selectHeadIdCategory((long)catnum);
	}
	
//전체글 조회 (default)
	@Override
	public List<BoardVO> selectArticles() {
		return boardDAO.selectArticles();
	}

	
	
	
	
	@Inject 
	PagingSVC pagingSVC;
	
	
	//전체글 조회 + 페이징
	@Override
	public List<BoardVO> selectArticles(int reqPage, String searchType, String searchKeyword) {		
		PagingComponent	pagingComponent = pagingSVC.getPagingComponent(reqPage,searchType,searchKeyword);		
		
		System.out.println("board SVC 사용안하는 메소드 호출");
		
	return boardDAO.selectArticles(pagingComponent.getRecordCriteria().getRecFrom(), pagingComponent.getRecordCriteria().getRecTo());
	}
	
	
	
	//전체게시글 조회 + 페이징 + 검색어 (검색타입/검색어)
	@Override
	public List<BoardVO> selectArticlesWithKey(int reqPage, String searchType, String searchKeyword) {
		PagingComponent pagingComponent = pagingSVC.getPagingComponent(reqPage,searchType,searchKeyword);		
		
		System.out.println("board SVC  recFrom , recTO SQL범위 설정=== " + pagingComponent.toString());
		System.out.println("board SVC DAO단에서 읽어오는 데이터 개수" + boardDAO.selectArticlesWithKey(pagingComponent.getRecordCriteria().getRecFrom(), pagingComponent.getRecordCriteria().getRecTo(), searchType, searchKeyword).size());
	 
		return boardDAO.selectArticlesWithKey(pagingComponent.getRecordCriteria().getRecFrom(), pagingComponent.getRecordCriteria().getRecTo(), searchType, searchKeyword);
		
	}
	
	

	// 게시글 열람
	@Override
	public Map<String, Object> selectArticle(long bnum) {		
		 Map<String, Object> map = new HashMap<>();
		 List<BoardFileVO> files = null;
		 
		//조회수 업데이트 
		boardDAO.updateBhits(bnum);	
		
		//게시글 가져오기 
		map.put("boardVO",boardDAO.selectArticle(bnum));
		//첨부파일 가져오기 
		files = boardDAO.selectFiles(bnum);
		if(files !=null && files.size() >0 ) {
		map.put("files", files);
		}
		
		return map;
	}

	// 게시글 등록
	@Transactional 	
	@Override
	public int insertArticle(BoardVO boardVO) {		
		int result = 0 ;
		
		//게시글 저장		
		result = boardDAO.insertArticle(boardVO);
		//첨부파일 저장
		//첨부파일 유무 체크 
		
		
		List<MultipartFile> files = boardVO.getFiles();
		if(files !=null && files.size() > 0 ) {			
		
			insertFiles(files, boardVO.getBnum());		
			
		}

		
		
		
		
		return result;
	}
	//첨부파일 등록	
public void insertFiles(List<MultipartFile> files, long bnum) {
	BoardFileVO boardFileVO = null;
	
	for(MultipartFile file : files) {
		boardFileVO = new BoardFileVO();
		
		try {
			System.out.println("file" + file.getOriginalFilename());
			//게시글 번호
			boardFileVO.setBnum(bnum);
			//파일명
			boardFileVO.setFname(file.getOriginalFilename());
			//파일크기
			boardFileVO.setFsize(file.getSize());
			//파일타입
			boardFileVO.setFtype(file.getContentType());
			//파일데이터
			boardFileVO.setFdata(file.getBytes());
			
			
			if(boardFileVO.getFsize() >0 ) {
				boardDAO.insertFiles(boardFileVO);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

		
	}


	// 게시글 수정
	@Transactional
	@Override
	public int updateArticle(BoardVO boardVO) {
		
		
		//게시글 저장
		boardDAO.updateArticle(boardVO);
		
		//첨부파일 변경사항 저장
		List<MultipartFile> files = boardVO.getFiles();
		if(files != null && files.size() >0) {
			insertFiles(files, boardVO.getBnum());
		}
		

		return boardDAO.updateArticle(boardVO);
	}
	//첨부파일 일부 삭제 
		@Override
		public int deleteFile(long fid) {		
			return	boardDAO.deleteFile(fid);
		}

	// 게시글 삭제
	@Override
	public int deleteArticle(long bnum) {
		return boardDAO.deleteArticle(bnum);
	}
	
	//게시글 답글 작성
	@Override
	public int insertRepliedArticle(BoardVO boardVO) {	
		boardDAO.updateBstep(boardVO.getBgroup(), boardVO.getBstep());
		return boardDAO.insertRepliedArticle(boardVO);
	}
	
	
	



	
	
	
	

	
	


}
