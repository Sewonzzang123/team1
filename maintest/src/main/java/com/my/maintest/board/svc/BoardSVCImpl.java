package com.my.maintest.board.svc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.tomcat.util.codec.binary.Base64;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.my.maintest.board.dao.BoardDAO;
import com.my.maintest.board.vo.BcategoryVO;
import com.my.maintest.board.vo.BoardFileVO;
import com.my.maintest.board.vo.BoardVO;
import com.my.maintest.board.vo.HeadIdCategoryVO;
import com.my.maintest.board.vo.ThumbnailVO;
import com.my.maintest.common.paging.PagingComponent;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
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
	public List<BoardVO> selectArticles(String btype, int catnum, int reqPage,long recNumPerPage, String searchType, String searchKeyword) {		
		PagingComponent	pagingComponent = pagingSVC.getPagingComponent(btype,catnum , reqPage, recNumPerPage,searchType,searchKeyword);		
			
	return boardDAO.selectArticles(catnum, pagingComponent.getRecordCriteria().getRecFrom(), pagingComponent.getRecordCriteria().getRecTo());
	}
	
	
//게시판 타입 조회 
	@Override
	public BcategoryVO selectBtype(long catnum) {	
	return boardDAO.selectBtype(catnum);
	}

	
	//전체게시글 조회 + 페이징 + 검색어 (검색타입/검색어) 게시판타입 분기 : 앨범/블로그
	@Override
	public  Map<String,Object> selectArticlesWithKey(String btype, long catnum, int reqPage,long recNumPerPage, String searchType, String searchKeyword) {
			if(btype.equals("album")) {
			recNumPerPage = 8;
		}
		//페이징 		
	PagingComponent pagingComponent = pagingSVC.getPagingComponent(btype,catnum, reqPage, recNumPerPage, searchType,searchKeyword);
		List<BoardVO> list = null;	
	if(btype.equals("album")) {		
		list = boardDAO.selectArticlesWithKey_Album(catnum,pagingComponent.getRecordCriteria().getRecFrom(), pagingComponent.getRecordCriteria().getRecTo(), searchType, searchKeyword);
		// base64 
		/*
		 * for(int i = 0 ; i < list.size(); i++) {
		 * log.info(list.get(i).getThumbnailVO().getThumbfname()); //fdata 인코딩 byte[]
		 * encoded = Base64.encodeBase64(list.get(i).getThumbnailVO().getThumbfdata());
		 * //file 타입 추출 image/jpg --> jpg String thumbftype =
		 * (list.get(i).getThumbnailVO().getThumbftype()).split("/")[1];
		 * list.get(i).getThumbnailVO().setThumbftype(thumbftype);
		 * list.get(i).getThumbnailVO().setBase64encoded(new String(encoded)); // 주의
		 * base64 인코딩 후 String 타입으로 반환 해야함. }
		 */
	}else {
		
		list = boardDAO.selectArticlesWithKey_Blog(catnum,pagingComponent.getRecordCriteria().getRecFrom(), pagingComponent.getRecordCriteria().getRecTo(), searchType, searchKeyword);
	}
	log.info("list.size()    =   " +list.size() );
	for(BoardVO vo : list) {
	System.out.println("list.get(0).getBtitle() = "+vo.getBtitle());
	}
	Map<String,Object> map = new HashMap<>();
	map.put("pagingComponent", pagingComponent);
	map.put("list", list);
	return map;
	}
	
	 //갤러리 게시판 썸네일 목록 호출 : 게시판 타입이 album일 때 호출
	public List<BoardFileVO> selectThumbnailFiles(int catnum){		
	return	boardDAO.selectThumbnailFiles(catnum);		
	}
	// 게시글 열람
	@Override
	public Map<String, Object> selectArticle(long bnum) {		
		 Map<String, Object> map = new HashMap<>();
		 List<BoardFileVO> files = null;		 
		//조회수 업데이트 
		boardDAO.updateBhits(bnum);			
		//게시글 가져오기 
		
		BoardVO boardVO = boardDAO.selectArticle(bnum);
		if(boardVO.getBcontent() != null) {
		try {
			boardVO.setTcontent(new String(boardVO.getBcontent(), "UTF-8"));
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		map.put("boardVO",boardVO);
//		//첨부파일 가져오기 
//		files = boardDAO.selectFiles(bnum);	
//		if(files !=null && files.size() >0 ) {
//		map.put("files", files);
//		
//		}		
		return map;
	}

	
	
	//게시글 등록(게시글 원글 and 답글)
	@Transactional 	
	@Override
	public long insertArticle(BoardVO boardVO) {		
		long result = 0 ;		
		//게시글 저장		
		result = boardDAO.insertArticle(boardVO);
		//첨부파일 저장
		///첨부파일 유무 체크 			
		List<MultipartFile> files = boardVO.getFiles();
		if(files !=null && files.size() > 0 ) {			
			insertFiles(files, boardVO.getBnum(), boardVO.getBcategory().getCatnum());					
		}
		return result;
	}
	
	//썸네일 경로 저장
	@Override
	public int updateThumbPath(BoardVO boardVO) {
		return  boardDAO.updateThumbPath(boardVO);
	}
	
	//첨부파일 등록	
public void insertFiles(List<MultipartFile> files, long bnum, String catnum)  {	
	//list loop
	
	for(int i = 0 ; i < files.size(); i++ ) {			
		//저장객체 per one file		
		BcategoryVO bcategoryVO = boardDAO.selectBtype(Long.parseLong(catnum));
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
			if(bcategoryVO.getBtype().equals("album") && i == 0 ) {					
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
					ThumbnailVO thumbnailVO = new ThumbnailVO();
					boardFileVO.setThumbnailVO(thumbnailVO);					
					boardFileVO.getThumbnailVO().setThumbfname(genThumbfname);
					boardFileVO.getThumbnailVO().setThumbfdata(thumbfdata);		
					boardFileVO.getThumbnailVO().setThumbfsize(thumbfsize);
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
//		
//		//게시글 저장
//		boardDAO.updateArticle(boardVO);
		
		//첨부파일 변경사항 저장
//		List<MultipartFile> files = boardVO.getFiles();
//		if(files != null && files.size() >0) {
//			insertFiles(files, boardVO.getBnum(),boardVO.getBcategory().getCatnum());
//		}		

		return boardDAO.updateArticle(boardVO);
	}
	//첨부파일 일부 삭제 
		@Override
		public long deleteFile(long fid, String isThumb) {		
			return	boardDAO.deleteFile(fid, isThumb);			
			//썸네일 파일 남겨 놓아야함. 
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
	System.out.println("SVC 단 답글 ==" + boardVO.toString());
		
		//답글 step 갱신
		boardDAO.updateBstep(boardVO.getBgroup(), boardVO.getBstep());
			long _result = 0 ;
		
			_result = boardDAO.insertRepliedArticle(boardVO);
			//게시글 등록후 bnum 반환			
			//등록후 생성된 bnum으로 저장 경로 재설정 
				long _bnum = boardVO.getBnum();	
				String thumbnailName = boardVO.getThumbnail();	
				String _tcontent = null;
			if (!thumbnailName.equals("null")) {
		 boolean result = 	handleFiles(_bnum, thumbnailName);
			if(result == true ) {
			//db에 썸네일 저장 경로 저장			
			boardVO.setThumbnail("photo\\" + _bnum+"\\s\\thumb_" + thumbnailName);
			//원본파일 경로 수정 \\tmpphoto\\ ==>  \\ photo\\bnum\\origin
			 _tcontent = boardVO.getTcontent().replace("tmpphoto", "photo/" +_bnum+"/origin/");
			}
			}else {			
				_tcontent = boardVO.getTcontent();	
				}
				try {
					boardVO.setBcontent(_tcontent.getBytes("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}				

			return boardDAO.updateThumbPath(boardVO);
		
		//첨부파일 저장
		//첨부파일 유무 체크 		
//		List<MultipartFile> files = boardVO.getFiles();
//		if(files !=null && files.size() > 0 ) {					
//			insertFiles(files, boardVO.getBnum(),boardVO.getBcategory().getCatnum());		
//					}

	}
	
	
//게시글 등록(text + img  (썸네일생성 / 원본 파일 저장)
	
	@Override
	public long insertArticleWithImg(BoardVO boardVO) {		
	
		//게시글 등록후 bnum 반환
		 boardDAO.insertArticle(boardVO);		 
		//등록후 생성된 bnum으로 저장 경로 재설정 
			long _bnum = boardVO.getBnum();	
			
			
			String thumbnailName = boardVO.getThumbnail();	
			String _tcontent = null;
		if (!thumbnailName.equals("null")) {
	 boolean result = 	handleFiles(_bnum, thumbnailName);
		if(result == true ) {
		//db에 썸네일 저장 경로 저장			
		boardVO.setThumbnail("photo\\" + _bnum+"\\s\\thumb_" + thumbnailName);
		//원본파일 경로 수정 \\tmpphoto\\ ==>  \\ photo\\bnum\\origin
		 _tcontent = boardVO.getTcontent().replace("tmpphoto", "photo/" +_bnum+"/origin/");
		}
		}else {			
			_tcontent = boardVO.getTcontent();	
			}
			try {
				boardVO.setBcontent(_tcontent.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}				
	

		return boardDAO.updateThumbPath(boardVO);
	}
	
	//파일 처리 메소드 
	@Override
	public boolean  handleFiles(long _bnum, String thumbnailName) {
	boolean result = true;
	
	//게시글 번호별 폴더 생성
	File bnumPath = new File("C:\\tmpServerRepo\\photo\\" +_bnum + "\\" );		
	if(!bnumPath.exists()) {			
		bnumPath.mkdir();
	}		
	// 썸네일 등록
	if (!thumbnailName.equals("null")) {
		String originFilePath ="C:\\tmpServerRepo\\photo\\tmp\\" +thumbnailName;
		// 썸네일로 만들 원본파일객체 생성
		File originFile = new File(originFilePath);
		// 썸네일을 담을 파일
		//원본파일을 임시폴더에서 게시글 번호 폴더에 새로이 생성
		File originImgFile = new File( "C:\\tmpServerRepo\\photo\\"+_bnum+"\\origin\\"	+ thumbnailName);
		File thumbnail	 = new File( "C:\\tmpServerRepo\\photo\\"+_bnum+"\\s\\thumb_"	+ thumbnailName);
		// 대상 파일을 리사징 후 썸네일 파일에 저장
		if (originFile.exists()) {
			// 썸네일 저장 디렉토리 및 파일 생성			
			originImgFile.getParentFile().mkdirs();
			thumbnail.getParentFile().mkdirs();	
			try {
				Thumbnails.of(originFile).size(257, 257).toFile(thumbnail);
		
			//copy data				
			FileInputStream fis = null;
			FileOutputStream fos = null;
			File[] files = originFile.getParentFile().listFiles();			
			for(File file : files) {							  
				//tmp폴더에 들어있는 원본파일의 객체생성
				 fis = new FileInputStream(file);
				 //target이 되는 파일을 생성 (실제 저장될 루트)
				 File targetFile =   new File(  "C:\\tmpServerRepo\\photo\\"+_bnum+"\\origin\\" +  file.getName());
				 //실제 저장될 파일을 생성 
				 fos = new FileOutputStream(targetFile);
				 
				 //복사 
				 byte[] buffer = new byte[512];
					int readCnt = 0;
					while ((readCnt = 	fis.read(buffer)) != -1) {
					fos.write(buffer,0,readCnt);							
					}						
					fis.close();
					fos.close();	
					//tmp폴더 원본 삭제
					file.delete();				
			}			
			} catch (IOException e) {
				e.printStackTrace();
			}
			result = true;
		}
	} else {		
			result = false;
	}			
	return  result;
	
}

	
	
	
	
	



	
	
	
	

	
	


}
