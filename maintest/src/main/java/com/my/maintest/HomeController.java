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
import com.my.maintest.common.fileupload.UploadFiles;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
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
	public String test(MultipartFile files
			,MultipartHttpServletRequest request, HttpSession session
			,Model model ) throws Exception{
		
		
		Iterator<String> iterator = request.getFileNames();
		
		List<MultipartFile> multifiles = request.getFiles(iterator.next());
		
		for(int i = 0 ; i < multifiles.size(); i++) {
			
			String path = request.getSession().getServletContext().getRealPath("");
			String path2 = request.getSession().getServletContext().getRealPath("resources/imgUpload");
			System.out.println("path ==" + path);
			System.out.println("path2 ==" + path2);
			
			
		}
		
		
		logger.info("upload 호출됨.");
		//위에는 고정 path	
		// uploadPath from servlet-context.xml에 정의해놓음.
		String imgUploadPath = uploadPath + File.separator + "imgUpload";
		
		
		//아래는 유동적 path 
		String flxblImgUploadPath = request.getSession().getServletContext().getRealPath("");
		
	
		// 저장위치 D:\java\apache-tomcat-9.0.37\wtpwebapps\maintest\resources\file + 추가로 덧붙일수 있음.
		
		logger.info("imgUploadPath=======" + imgUploadPath);
		
		//calcPath 메소드는 directory 경로에 필요한 폴더를 생성한다. 
		//uploadPathD:\java\apache-tomcat-9.0.37\wtpwebapps\maintest\resources\file / yearPath / monthPath / datePath
		//uploadPath / yearPath 2020 / monthPath 08 / datePath 29 +
		
		String ymdPath = UploadFiles.calcPath(imgUploadPath);		
		logger.info("ymdPath=======" + ymdPath); // 출력값 : \2020\08\29
		
		
		String _fileName = null ;
		byte[] _fileData = null;
		
		String fileName = null;
		
		if(files != null) {
			//jsp로 부터 바인딩된 파일이 있는경우 
			_fileName= files.getOriginalFilename();
					
			_fileData =files.getBytes();
			
			fileName = UploadFiles.fileUpload(imgUploadPath, _fileName, _fileData, ymdPath);
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
