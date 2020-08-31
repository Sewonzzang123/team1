package com.my.maintest;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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
import com.my.maintest.common.fileupload.UploadFilesUtils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TestController {

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
			MultipartHttpServletRequest request, HttpSession session
			,Model model ) throws Exception{	
		Iterator<String> iterator = request.getFileNames();	
		
		List<MultipartFile> multifiles = request.getFiles(iterator.next());
		System.out.println("multifiles.size()==" + multifiles.size()); //files 출력
		String flxblImgUploadPath = null;
		for(int i = 0 ; i < multifiles.size(); i++) {
		//1. 유동적 path  root 패스 D:\JAVAEDU\Tomcat9\wtpwebapps\maintest\resources
			flxblImgUploadPath = request.getSession().getServletContext().getRealPath("resources");
		//2. 파일명 변경하기 --> 중복 방지를 위함 .
			//2-1. 파일 이름 읽어오기 
			
			//2-1. 날짜 정보 추가 --> 하위 폴더 생성 \2020\08\29
			
			String ymdPath = UploadFilesUtils.calcPath(flxblImgUploadPath);	
			//D:\java\mygit\team1\maintest\src\main\webapp\WEB-INF\resources\imgUpload\2020\08\31

			
			
			
			
			
			
			
			
		}
		
		
		
		
		//calcPath 메소드는 directory 경로에 필요한 폴더를 생성한다. 
		//uploadPathD:\java\apache-tomcat-9.0.37\wtpwebapps\maintest\resources\file / yearPath / monthPath / datePath
		//uploadPath / yearPath 2020 / monthPath 08 / datePath 29 +
		
		String ymdPath = UploadFilesUtils.calcPath(flxblImgUploadPath);		
		logger.info("ymdPath=======" + ymdPath); // 출력값 : \2020\08\29
		
		
		String _fileName = null ;
		byte[] _fileData = null;		
		String fileName = null;
		
		
		System.out.println( "files.toString()" + multifiles.toString());
		
		
		if(multifiles.get(0) != null) {
			//jsp로 부터 바인딩된 파일이 있는경우 
			_fileName= multifiles.get(0).getOriginalFilename();	
			_fileData =multifiles.get(0).getBytes();			
			fileName = UploadFilesUtils.fileUpload(flxblImgUploadPath, _fileName, _fileData, ymdPath);
			}else {
			fileName = uploadPath + File.separator+"images" +File.separator +"none.png";
			}
		
		BoardFileVO boardFileVO = new BoardFileVO();
		
		boardFileVO.setFname(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
		boardFileVO.setFthumbnail(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		boardFileVO.setBnum(39);
		boardFileVO.setFdata(_fileData);
		
		
		
		boardDAO.insertFiles(boardFileVO);
		boardFileVO = boardDAO.selectFiles(39).get(0);
		
		model.addAttribute("boardFileVO",boardFileVO);
		model.addAttribute("pathPrefix", request.getSession().getServletContext().getRealPath("resources/imgUpload"));
		model.addAttribute("boardFileVO",boardFileVO);
		
		return "home";
	}	
	
	
	
	
	

		

		
		
	
	

}
