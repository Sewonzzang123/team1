package com.my.maintest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.maintest.board.dao.BoardDAO;
import com.my.maintest.board.svc.BoardSVC;
import com.my.maintest.board.vo.BoardFileVO;
import com.my.maintest.board.vo.TemporaryVO;
import com.my.maintest.common.fileupload.UploadFilesUtils;

import net.coobird.thumbnailator.Thumbnails;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TestController {

	
	
	
	static final int THUMB_WIDTH = 150;
	static final int THUMB_HEIGHT = 200;
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	
	//파일업로드 경로 저장  필드
	@Resource(name="uploadPath")
	private String uploadPath;
	
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
			{"/upload", "/upload/{files}"})
	public String test(
			MultipartHttpServletRequest filesRequest, HttpSession session
			,Model model ) throws Exception{	
		
		
		

		
		
		Iterator<String> iterator = filesRequest.getFileNames();	
		List<MultipartFile> multifiles = null;
		
		while(iterator.hasNext()) {
		multifiles = filesRequest.getFiles(iterator.next());
		}
		

	
		
		
		
		
		//패스 설정
		String flxblImgUploadPath = null;		
		//1. 유동적 path  root 패스 D:\JAVAEDU\Tomcat9\wtpwebapps\maintest\resources
			flxblImgUploadPath = filesRequest.getSession().getServletContext().getRealPath("resources");			
			//2.중복방지를 위한 주소에 날짜 추가 
			String ymdPath = UploadFilesUtils.calcPath(flxblImgUploadPath);	
			//D:\java\mygit\team1\maintest\src\main\webapp\WEB-INF\resources\imgUpload\2020\08\31

			
			//가공전 데이터 
		
			String _ftype = null ;
			byte[] _fdata = null;		
			long _fsize = 0; 
			//본래 파일이름
			String _fname = null ;
			
			//가공후 데이터 
			// 파일명 중복 방지용 파일명 접두사  생성
			String prefixFname = null;			
			//원본 파일 이름
			String genFname = null;			
			//썸네일 파일 이름  			
			String thumbfname=null;
			
			
		
			

			String ftype = null ;
			byte[] fdata = null;		
			long fsize = 0; 
			
			
			
			
			
			List<BoardFileVO> files = new ArrayList<BoardFileVO>();
			for(int i = 0 ; i < multifiles.size(); i++) {
				BoardFileVO boardFileVO = new BoardFileVO();
				

				_ftype = multifiles.get(i).getContentType();
				_fdata = multifiles.get(i).getBytes();
				_fsize =	multifiles.get(i).getSize();
								
				//1.원본파일 이름 
				_fname = 	multifiles.get(i).getOriginalFilename();
				//2.중복방지 랜덤한 접두사 생성
				prefixFname = UUID.randomUUID().toString();
				//3.접두사 + 원본파일명 (저장할 원본파일이름)
				genFname = prefixFname + _fname;						
				//4.접두사 + _thumb_+원본파일명(저장할 썸네일 파일이름)
				thumbfname = prefixFname + "_thumb_" + _fname;			
				
				
				//thumbnail 이미지 새로 만들기 
				InputStream is = multifiles.get(i).getInputStream();
				BufferedImage orginalImage = ImageIO.read(is);
				
				BufferedImage thumbnailImg= Thumbnails.of(orginalImage).size(THUMB_WIDTH,THUMB_HEIGHT)
						//.outputQuality(1.0f).outputFormat("png")   //고화질용
						.asBufferedImage();
				
		
				//thumbnail 이미지 파일 데이터 생성하기 
				ByteArrayOutputStream baos = new ByteArrayOutputStream();				
				//write(원본 이미지 , 파일 타입, 타겟 ) 바이트 어래이 OS 객체에 원본이미지 정보를 담아준다. 
				ImageIO.write(thumbnailImg, _ftype, baos);
								
				byte[] thumbfdata = baos.toByteArray(); 
				
				boardFileVO.setThumbfname(thumbfname);			
				boardFileVO.setThumbfdata(thumbfdata);		
				boardFileVO.setThumbfsize(baos.size());
				
				//파일 주소 저장
				boardFileVO.setFname(genFname);				
				boardFileVO.setFtype(_ftype);
				boardFileVO.setFdata(_fdata);
				boardFileVO.setFsize(_fsize);			
				
				boardFileVO.setBnum(104);
				//썸네일 파일 축소 및 주소 가져오기 
				
//				boardFileVO.getFthumbnail();
				
				//원본파일 저장
				System.out.println("boardFileVO =================== " + boardFileVO.toString());				
				boardDAO.insertFiles(boardFileVO);			
				
			}
		
		 long bnum = 104;
		
		 
		 files = boardDAO.selectFiles(bnum);		 
		 TemporaryVO temp = new TemporaryVO();
		 
		 int i = 0; 
		 
		 //for(BoardFileVO file : files) {			 
			 byte[] encodedFdata = Base64.encodeBase64(files.get(0).getFdata());			 
			 temp.setFtype(files.get(0).getFtype());
			 temp.setEncoded(new String(encodedFdata));

			 

			 System.out.println( i++);
	
	model.addAttribute("temp",temp);
	
	
	
	
	
//			model.addAttribute("pathPrefix", filesRequest.getSession().getServletContext().getRealPath("resources"));
//
//			
//	
			
			
		
		
		//calcPath 메소드는 directory 경로에 필요한 폴더를 생성한다. 
		//uploadPathD:\java\apache-tomcat-9.0.37\wtpwebapps\maintest\resources\file / yearPath / monthPath / datePath
		//uploadPath / yearPath 2020 / monthPath 08 / datePath 29 +
		
	

		
		
		System.out.println( "files.toString()" + multifiles.toString());
		String fileName = null;

		if(multifiles.get(0) != null) {
			//jsp로 부터 바인딩된 파일이 있는경우 
			_fname= multifiles.get(0).getOriginalFilename();	
			_fdata =multifiles.get(0).getBytes();			
			fileName = UploadFilesUtils.fileUpload(flxblImgUploadPath, _fname, _fdata, ymdPath);
			}
		
		

//		 BoardVO boardVO = new BoardVO();		 
		
		
		//boardFileVO.setFname(File.separator  + ymdPath + File.separator + fileName);
		//boardFileVO.setFthumbnail(File.separator  + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);


	
		
		
		return "home";
	}	
	
	
	
	
	

		

		
		
	
	

}
