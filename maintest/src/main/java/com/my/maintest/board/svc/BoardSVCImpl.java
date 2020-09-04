package com.my.maintest.board.svc;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.my.maintest.board.dao.BoardDAO;
import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardFileVO;
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;
import com.my.maintest.common.paging.PagingComponent;

import lombok.extern.slf4j.Slf4j;
@Slf4j
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
	public List<HeadIdCategoryVO> selectHeadIdCategory(String catnum) {
		
		return  boardDAO.selectHeadIdCategory(Long.parseLong( catnum));
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
			
	return boardDAO.selectArticles(pagingComponent.getRecordCriteria().getRecFrom(), pagingComponent.getRecordCriteria().getRecTo());
	}
	
	
//게시판 타입 조회 
	@Override
	public String selectBtype(long catnum) {	
	return boardDAO.selectBtype(catnum);
	}

	
	//전체게시글 조회 + 페이징 + 검색어 (검색타입/검색어)
	@Override
	public  Map<String, Object>  selectArticlesWithKey(long catnum, int reqPage, String searchType, String searchKeyword) {
		
	//페이징 		
			PagingComponent pagingComponent = pagingSVC.getPagingComponent(reqPage,searchType,searchKeyword);
			
			
		Map<String, Object> map = new HashMap<>();
		List<BoardVO> list = null;
		BoardFileVO file = null;
		
		//BoardVO 불러오기
	 list = boardDAO.selectArticlesWithKey(catnum,pagingComponent.getRecordCriteria().getRecFrom(), pagingComponent.getRecordCriteria().getRecTo(), searchType, searchKeyword);
	 log.info(list.toString());
		
	 map.put("articles", list); 
	 map.put("files",file);
		
		
		return map;
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
		System.out.println("board SVC 파일 개수=============================" + files.size());	
		}
		
		return map;
	}

	// 게시글 등록
	@Transactional 	
	@Override
	public long insertArticle(BoardVO boardVO) {		
		long result = 0 ;		
		
		//게시글 저장		
		result = boardDAO.insertArticle(boardVO);
		//첨부파일 저장
		//첨부파일 유무 체크 			
		List<MultipartFile> files = boardVO.getFiles();
		if(files !=null && files.size() > 0 ) {			
			insertFiles(files, boardVO.getBnum(), boardVO.getBcategory().getCatnum());					
		}

		return result;
	}
	
	
	
	//첨부파일 등록	
public void insertFiles(List<MultipartFile> files, long bnum, String catnum)  {	

	//list loop
	
	for(int i = 0 ; i < files.size(); i++ ) {			
		//저장객체 per one file
		
		String btype = boardDAO.selectBtype(Long.parseLong(catnum));
		
		try {			
			//원본 파일 정보 추출
			String originalFileName = files.get(i).getOriginalFilename();
			String ftype= files.get(i).getContentType();
			byte[] fdata = files.get(i).getBytes();
			long fsize =  files.get(i).getSize();
			//---------------------------------------------------------------------------------------------------------------
			//원본파일 정보 가공
			//확장자명
			int index = originalFileName.lastIndexOf(".");
			String fileExt = originalFileName.substring(index+1);						
		//---------------------------------------------------------------------------------------------------------------
			//새로운 이름 (중복방지)
			//파일 이름 중복방지를 위한 랜덤한 접두사 생성
			String prefixFname = UUID.randomUUID().toString();
			//접두사 + 원본파일명 (저장할 원본파일이름)
				String genFname = prefixFname +"_"+ originalFileName;						
				
			//VO 생성
			BoardFileVO boardFileVO = new BoardFileVO();;				
			
			//게시글 번호
			boardFileVO.setBnum(bnum);
			//파일명
			boardFileVO.setFname(genFname);
			//파일크기
			boardFileVO.setFsize(fsize);
			//파일타입
			boardFileVO.setFtype(ftype);
			//파일데이터
			boardFileVO.setFdata(fdata);
			
			
		//첫번째 첨부한 이미지 파일 썸네일생성	
			//원본파일 정보			
			if(btype.equals("album") && i == 0 ) {					
				//접두사 + _thumb_+원본파일명(저장할 썸네일 파일이름)
				String genThumbfname = prefixFname + "-thumb_" + originalFileName;				
					//---------------------------------------------------------------------------------------------------------------
					//썸네일 생성
					InputStream in = files.get(0).getInputStream();
					BufferedImage originalImage = ImageIO.read(in);
					BufferedImage thumbnail = Scalr.resize(originalImage, 257, 257);		
					//---------------------------------------------------------------------------------------------------------------
					// 썸네일 db저장을 위한 byte 데이터 및 사이즈 가져오기
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					baos.flush();		
					ImageIO.write(thumbnail, fileExt, baos);				
					byte[] thumbfdata = baos.toByteArray();		
					long thumbfsize = baos.size();
					baos.close();		
					
					boardFileVO.setThumbfname(genThumbfname);			
					boardFileVO.setThumbfdata(thumbfdata);		
					boardFileVO.setThumbfsize(thumbfsize);
					}
			
			if(boardFileVO.getFsize() > 0  ) {
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
	public long updateArticle(BoardVO boardVO) {
		
		
		//게시글 저장
		boardDAO.updateArticle(boardVO);
		
		//첨부파일 변경사항 저장
		List<MultipartFile> files = boardVO.getFiles();
		if(files != null && files.size() >0) {
			insertFiles(files, boardVO.getBnum(),boardVO.getBcategory().getCatnum());
		}
		

		return boardDAO.updateArticle(boardVO);
	}
	//첨부파일 일부 삭제 
		@Override
		public long deleteFile(long fid) {		
			return	boardDAO.deleteFile(fid);
		}
		
		//첨부파일 다운로드
		@Override
		public BoardFileVO selectFileToDwLoad(String fid) {			
			
			return			boardDAO.selectFileToDwLoad(Long.parseLong(fid));
		}
		

	// 게시글 삭제
	@Override
	public long deleteArticle(long bnum) {
		return boardDAO.deleteArticle(bnum);
	}
	
	//게시글 답글 작성
	@Override
	public long insertRepliedArticle(BoardVO boardVO) {	
		
		//답글 step 갱신
		boardDAO.updateBstep(boardVO.getBgroup(), boardVO.getBstep());
			long result = 0 ;
		
		//게시글 저장		
		result = boardDAO.insertRepliedArticle(boardVO);
		//첨부파일 저장
		//첨부파일 유무 체크 
		
		
		List<MultipartFile> files = boardVO.getFiles();
		if(files !=null && files.size() > 0 ) {					
			insertFiles(files, boardVO.getBnum(),boardVO.getBcategory().getCatnum());		
					}

		return result;	

	}
	
	
	
	
	



	
	
	
	

	
	


}
