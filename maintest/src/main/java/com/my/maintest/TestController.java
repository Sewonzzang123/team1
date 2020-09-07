package com.my.maintest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.tomcat.util.codec.binary.Base64;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.my.maintest.board.dao.BoardDAO;
import com.my.maintest.board.svc.BoardSVC;
import com.my.maintest.board.vo.BoardFileVO;
import com.my.maintest.common.fileupload.UploadFilesUtils;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

/**
 * Handles requests for the application home page.
 */

@Slf4j
@Controller
@RequestMapping("/file")
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Inject
	BoardSVC boardSVC;
	
	@Inject
	BoardDAO boardDAO;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	
	
	@RequestMapping(value = "/upftst", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
			return "home";
	}
	

	

	@PostMapping(
			{"/upload1", "/upload1/{files}"})
	public String test1(
			@RequestParam("files") List<MultipartFile> multifiles, 
			HttpSession session,HttpServletRequest request,
			Model model ) throws Exception{			
		
	//원본파일 정보
		String originalFileName = multifiles.get(0).getOriginalFilename();
		String ftype= multifiles.get(0).getContentType();
		byte[] fdata = multifiles.get(0).getBytes();
		long fsize =  multifiles.get(0).getSize();
		//---------------------------------------------------------------------------------------------------------------
		//원본파일 정보 가공
		int index = originalFileName.lastIndexOf(".");
		//파일 이름 without 확장자
		String fileName = originalFileName.substring(0, index);
		//확장자명
		String fileExt = originalFileName.substring(index+1);			
		
		//---------------------------------------------------------------------------------------------------------------
		//썸네일 생성
		InputStream in = multifiles.get(0).getInputStream();
		BufferedImage originalImage = ImageIO.read(in);
		BufferedImage thumbnail = Scalr.resize(originalImage, 257, 257);		
		
		
		//---------------------------------------------------------------------------------------------------------------
		//새로운 이름 (중복방지)
		 //1.중복방지 랜덤한 접두사 생성
			String prefixFname = UUID.randomUUID().toString();
			//3.접두사 + 원본파일명 (저장할 원본파일이름)
			String genFname = prefixFname +"_"+ originalFileName;						
			//4.접두사 + _thumb_+원본파일명(저장할 썸네일 파일이름)
			String genThumbfname = prefixFname + "-thumb_" + originalFileName;			
			
			
			//---------------------------------------------------------------------------------------------------------------
			// 썸네일 db저장을 위한 byte 데이터 및 사이즈 가져오기
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.flush();		
		ImageIO.write(thumbnail, fileExt, baos);				
		byte[] thumbfdata = baos.toByteArray();		
		long thumbfsize = baos.size();
		baos.close();		
		
		//---------------------------------------------------------------------------------------------------------------
		// VO 생성
		BoardFileVO boardFileVO = new BoardFileVO();		
//		boardFileVO.setThumbfname(genThumbfname);			
//		boardFileVO.setThumbfdata(thumbfdata);		
//		boardFileVO.setThumbfsize(thumbfsize);
		
		//원본 파일 정보 저장
		boardFileVO.setFname(genFname);				
		boardFileVO.setFtype(ftype);
		boardFileVO.setFdata(fdata);
		boardFileVO.setFsize(fsize);			
		
		boardFileVO.setBnum(4);
		//썸네일 파일 축소 및 주소 가져오기 
		
//		boardFileVO.getFthumbnail();
		
		//원본파일 저장
		boardDAO.insertFiles(boardFileVO);			
		
		//---------------------------------------------------------------------------------------------------------------

		 List<BoardFileVO> files = new ArrayList<BoardFileVO>();
		 
		long bnum = 141;
		 
		 files = boardDAO.selectFiles(bnum);		  

			 byte[] encodedOriginFdata = Base64.encodeBase64(files.get(0).getFdata());
//			 byte[] encodedThumbFdata = Base64.encodeBase64(files.get(0).getThumbfdata());
			 model.addAttribute("ftype", files.get(0).getFtype());
			 model.addAttribute("originFdata",new String(encodedOriginFdata));			 
//			 model.addAttribute("thumbFdata",new String(encodedThumbFdata));			 
		
		return "home";
		
	}
	
	
	@PostMapping(
			{"/upload", "/upload/{files}"})
	public String test(
			@RequestParam("files") List<MultipartFile> multifiles, 
			HttpSession session,HttpServletRequest request,
			Model model ) throws Exception{			
		
		for(int i = 0 ; i < multifiles.size(); i++ ) {			
			String originalFileName = multifiles.get(i).getOriginalFilename();
			String ftype= multifiles.get(i).getContentType();
			byte[] fdata = multifiles.get(i).getBytes();
			long fsize =  multifiles.get(i).getSize();
			
			
			String path = request.getSession().getServletContext().getRealPath("resources");
			
			logger.info("path================================" + path);
			
			
			int index = originalFileName.lastIndexOf(".");
			//파일 이름 without 확장자
			String fileName = originalFileName.substring(0, index);
			//확장자명
			String fileExt = originalFileName.substring(index+1);			

		 //1.중복방지 랜덤한 접두사 생성
			String prefixFname = UUID.randomUUID().toString();
			//3.접두사 + 원본파일명 (저장할 원본파일이름)
			String genFname = prefixFname +"_"+ originalFileName;						
			//4.접두사 + _thumb_+원본파일명(저장할 썸네일 파일이름)
			String genThumbfname = prefixFname + "-thumb_" + originalFileName;			
			
			
			
			//MultipartFile로부터 파일이름과 저장패스를 
			File file = new File(genFname);

		System.out.println(file.getParentFile());
		
		multifiles.get(i).transferTo(file);
		
BufferedImage _thumbFile = 	UploadFilesUtils.makeThumbnail(file.getAbsolutePath(), genFname, fileExt);
//BufferedImage _thumbFile1 = 	UploadFilesUtils.makeThumb(multifiles);
			
	//썸네일을 저장 이미지 이름앞에 THUMB_을 표시
		
		String thumbName =  genThumbfname;
		File  thumbFile = new File(thumbName);
		
		//1.이미지 파일 생성		
		ImageIO.write(_thumbFile,fileExt , thumbFile);
		
		//2.thumbnail 이미지 파일 byte 데이터 생성하기 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();				
		//write(원본 이미지 , 파일 타입, 타겟 ) 바이트 어래이 OS 객체에 원본이미지 정보를 담아준다. 
		baos.flush();
		ImageIO.write(_thumbFile,fileExt , baos);
		baos.close();		
		byte[] thumbfdata = baos.toByteArray(); 					

	//	BoardFileVO boardFileVO = new BoardFileVO();		
		//boardFileVO.setThumbfname(genThumbfname);			
		//boardFileVO.setThumbfdata(thumbfdata);		
		//boardFileVO.setThumbfsize(baos.size());
		
		//원본 파일 정보 저장
//		boardFileVO.setFname(genFname);				
//		boardFileVO.setFtype(ftype);
//		boardFileVO.setFdata(fdata);
//		boardFileVO.setFsize(fsize);			
//		
//		boardFileVO.setBnum(104);
		//썸네일 파일 축소 및 주소 가져오기 
		
//		boardFileVO.getFthumbnail();
		
		//원본파일 저장
//		boardDAO.insertFiles(boardFileVO);			
		


 long bnum = 104;
 List<BoardFileVO> files = new ArrayList<BoardFileVO>();
 
 
 files = boardDAO.selectFiles(bnum);		  

	 byte[] encodedOriginFdata = Base64.encodeBase64(files.get(0).getFdata());
//	 byte[] encodedThumbFdata = Base64.encodeBase64(files.get(0).getThumbfdata());
	 model.addAttribute("ftype", files.get(0).getFtype());
	 model.addAttribute("originFdata",new String(encodedOriginFdata));			 
//	 model.addAttribute("thumbFdata",new String(encodedThumbFdata));			 
			
		}
		
//		==========================================================================================
//		==========================================================================================
//		==========================================================================================
//		==========================================================================================
//		==========================================================================================
//		==========================================================================================
//		==========================================================================================
//		==========================================================================================
		
		//Iterator<String> iterator = filesRequest.getFileNames();	
//		while(iterator.hasNext()) {
//		multifiles = filesRequest.getFiles(iterator.next());
//		}
		
				
		//패스 설정
//		String flxblImgUploadPath = null;		
//		//1. 유동적 path  root 패스 D:\JAVAEDU\Tomcat9\wtpwebapps\maintest\resources
//			flxblImgUploadPath = request.getSession().getServletContext().getRealPath("resources");			
//			//2.중복방지를 위한 주소에 날짜 추가 
//			//String ymdPath = UploadFilesUtils.calcPath(flxblImgUploadPath);	
//			//D:\java\mygit\team1\maintest\src\main\webapp\WEB-INF\resources\imgUpload\2020\08\31
//
//			
//			//첨부파일 데이터(가공전)  		
//			String _ftype = null ;
//			byte[] _fdata = null;		
//			long _fsize = 0; 
//			//본래 파일이름
//			String _fname = null ;
//			
//			//첨부파일 데이터 (가공후)
//			// 파일명 중복 방지용 파일명 접두사  생성
//			String prefixFname = null;			
//			//원본 파일 이름
//			String genFname = null;			
//			//썸네일 파일 이름  			
//			String thumbfname=null;
//			
//			String ftype = null ;
//			byte[] fdata = null;		
//			long fsize = 0; 
//			
//			List<BoardFileVO> files = new ArrayList<BoardFileVO>();
//
//			for(int i = 0 ; i < multifiles.size(); i++) {
//				BoardFileVO boardFileVO = new BoardFileVO();
//				_ftype = multifiles.get(i).getContentType();
//				_fdata = multifiles.get(i).getBytes();
//				_fsize =	multifiles.get(i).getSize();
//			 _fname = 	multifiles.get(i).getOriginalFilename();
//		
//
//
//
//				
//	
//				//thumbnail 이미지 새로 만들기 
//				InputStream is = multifiles.get(i).getInputStream();
//				BufferedImage orginalImage = ImageIO.read(is);				
//				// 고화질 BufferedImage thumbnailImg= Thumbnails.of(orginalImage).size(THUMB_WIDTH,THUMB_HEIGHT).outputQuality(1.0f).outputFormat("png").asBufferedImage();
//				BufferedImage thumbnailImg= Thumbnails.of(orginalImage).size(THUMB_WIDTH,THUMB_HEIGHT).asBufferedImage();
//				


	
	
	
	
//			model.addAttribute("pathPrefix", filesRequest.getSession().getServletContext().getRealPath("resources"));
//
//			
//	
			
			
		
		
		//calcPath 메소드는 directory 경로에 필요한 폴더를 생성한다. 
		//uploadPathD:\java\apache-tomcat-9.0.37\wtpwebapps\maintest\resources\file / yearPath / monthPath / datePath
		//uploadPath / yearPath 2020 / monthPath 08 / datePath 29 +
		
	

//		
//		
//		System.out.println( "files.toString()" + multifiles.toString());
//		String fileName = null;
//
//		if(multifiles.get(0) != null) {
//			//jsp로 부터 바인딩된 파일이 있는경우 
//			_fname= multifiles.get(0).getOriginalFilename();	
//			_fdata =multifiles.get(0).getBytes();			
//			fileName = UploadFilesUtils.fileUpload(flxblImgUploadPath, _fname, _fdata, ymdPath);
//			}
//		
		

//		 BoardVO boardVO = new BoardVO();		 
		
		
//		boardFileVO.setFname(File.separator  + ymdPath + File.separator + fileName);
//		boardFileVO.setFthumbnail(File.separator  + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
//

	
		
		
		return "home";
	}	
	
	
	
	
	

		

		
		
	
	

}
