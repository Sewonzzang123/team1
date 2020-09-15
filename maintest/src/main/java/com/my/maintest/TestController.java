/*
 * package com.my.maintest;
 * 
 * import java.awt.image.BufferedImage; import java.io.ByteArrayOutputStream;
 * import java.io.File; import java.nio.file.Files; import java.util.HashMap;
 * import java.util.Iterator; import java.util.List; import java.util.Locale;
 * import java.util.Map;
 * 
 * import javax.annotation.Resource; import javax.imageio.ImageIO; import
 * javax.inject.Inject; import javax.servlet.http.HttpSession;
 * 
 * import org.apache.commons.codec.binary.Base64; import org.slf4j.Logger;
 * import org.slf4j.LoggerFactory; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.ResponseBody; import
 * org.springframework.web.multipart.MultipartFile; import
 * org.springframework.web.multipart.MultipartHttpServletRequest;
 * 
 * import com.my.maintest.board.dao.BoardDAO; import
 * com.my.maintest.board.svc.BoardSVC; import
 * com.my.maintest.board.vo.BoardFileVO;
 * 
 * import net.coobird.thumbnailator.Thumbnails;
 * 
 *//**
	 * Handles requests for the application home page.
	 */
/*
 * @Controller public class TestController {
 * 
 * private static final Logger logger =
 * LoggerFactory.getLogger(TestController.class);
 * 
 * // 파일업로드 경로 저장 필드
 * 
 * @Resource(name = "uploadPath") private String uploadPath;
 * 
 * @Inject BoardSVC boardSVC;
 * 
 * @Inject BoardDAO boardDAO;
 * 
 *//**
	 * Simply selects the home view to render by returning its name.
	 *//*
		 * @RequestMapping(value = "/upftst", method = RequestMethod.GET) public String
		 * home(Locale locale, Model model) {
		 * 
		 * return "home"; }
		 * 
		 * @PostMapping({ "/upload", "/upload/{files}" }) public String
		 * test(MultipartHttpServletRequest request, HttpSession session, Model model)
		 * throws Exception { Iterator<String> iterator = request.getFileNames();
		 * 
		 * List<MultipartFile> multifiles = request.getFiles(iterator.next());
		 * 
		 * ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		 * 
		 * MultipartFile multipartFile = multifiles.get(0);
		 * 
		 * BufferedImage image = ImageIO.read(multipartFile.getInputStream());
		 * 
		 * Integer width = image.getWidth();
		 * 
		 * Integer height = image.getHeight();
		 * 
		 * logger.info(String.valueOf(image)); logger.info(String.valueOf(width));
		 * logger.info(String.valueOf(height));
		 * 
		 * // System.out.println("multifiles.size()==" + multifiles.size()); // files 출력
		 * // String flxblImgUploadPath = null; // for (int i = 0; i <
		 * multifiles.size(); i++) { // // 1. 유동적 path root 패스
		 * D:\JAVAEDU\Tomcat9\wtpwebapps\maintest\resources // flxblImgUploadPath =
		 * request.getSession().getServletContext().getRealPath("resources"); // // 2.
		 * 파일명 변경하기 --> 중복 방지를 위함 . // // 2-1. 파일 이름 읽어오기 // // // 2-1. 날짜 정보 추가 --> 하위
		 * 폴더 생성 \2020\08\29 // // String ymdPath =
		 * UploadFilesUtils.calcPath(flxblImgUploadPath); // //
		 * D:\java\mygit\team1\maintest\src\main\webapp\WEB-INF\resources\imgUpload\2020
		 * \08\31 // // } // // // calcPath 메소드는 directory 경로에 필요한 폴더를 생성한다. // //
		 * uploadPathD:\java\apache-tomcat-9.0.37\wtpwebapps\maintest\resources\file /
		 * // // yearPath / monthPath / datePath // // uploadPath / yearPath 2020 /
		 * monthPath 08 / datePath 29 + // // String ymdPath =
		 * UploadFilesUtils.calcPath(flxblImgUploadPath); //
		 * logger.info("ymdPath=======" + ymdPath); // 출력값 : \2020\08\29 // // String
		 * _fileName = null; // byte[] _fileData = null; // String fileName = null; //
		 * // System.out.println("files.toString()" + multifiles.toString()); // // if
		 * (multifiles.get(0) != null) { // // jsp로 부터 바인딩된 파일이 있는경우 // File thum =
		 * null; // Thumbnails.of(multifiles.get(0).getName()).size(50,
		 * 50).outputFormat("png").toFile(thum); // _fileName =
		 * multifiles.get(0).getOriginalFilename(); // _fileData =
		 * multifiles.get(0).getBytes(); // fileName =
		 * UploadFilesUtils.fileUpload(flxblImgUploadPath, _fileName, _fileData,
		 * ymdPath); // } else { // fileName = uploadPath + File.separator + "images" +
		 * File.separator + "none.png"; // } // // BoardFileVO boardFileVO = new
		 * BoardFileVO(); // // boardFileVO.setFname(File.separator + "imgUpload" +
		 * ymdPath + File.separator + fileName); // boardFileVO.setFthumbnail( //
		 * File.separator + "resources" + ymdPath + File.separator + "s" +
		 * File.separator + "s_" + fileName); // boardFileVO.setBnum(39); //
		 * boardFileVO.setFdata(_fileData); // // boardDAO.insertFiles(boardFileVO); //
		 * boardFileVO = boardDAO.selectFiles(39).get(0); // //
		 * model.addAttribute("boardFileVO", boardFileVO); //
		 * model.addAttribute("pathPrefix",
		 * request.getSession().getServletContext().getRealPath("resources/imgUpload"));
		 * // model.addAttribute("boardFileVO", boardFileVO);
		 * 
		 * return "home"; }
		 * 
		 * // 썸네일 보기
		 * 
		 * @RequestMapping(value = "/thumb") public String getThumb(Model model) {
		 * List<BoardFileVO> rs = boardDAO.getThumbnail();
		 * 
		 * model.addAttribute("pic", rs.get(0).getFdata());
		 * 
		 * try { int[] x = { 1, 2, 3 }; } catch (Exception e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 * 
		 * // base64 이용 for (BoardFileVO test : rs) { int i = 0; byte[] encoded =
		 * Base64.encodeBase64(test.getFdata()); model.addAttribute("pic", new
		 * String(encoded)); i++; }
		 * 
		 * // if (rs.next()) { // // 바이너리 데이터를 저장하고 있는 컬럼으로부터 데이터를 가져온다 // InputStream
		 * in = rs.getBinaryStream("bfile"); // // BufferedImage를 생성하면 ImageIO를 통해 브라우저에
		 * 출력하기가 쉽다. // BufferedImage bimg = ImageIO.read(in); // in.close(); // }
		 * 
		 * return "thumb";
		 * 
		 * // 사진 저장 }
		 * 
		 * @ResponseBody
		 * 
		 * @RequestMapping(value = "/setphoto", produces =
		 * "application/String;charset=utf8") public String
		 * set_photo(MultipartHttpServletRequest mtf) throws Exception { Map<String,
		 * String> result = new HashMap<>(); logger.info("사진업로드허출"); // 파일 태그 String
		 * fileTag = "file"; // 업로드 파일이 저장될 경로 String filePath =
		 * "C:\\Users\\Administrator\\git\\team1\\maintest\\src\\main\\webapp\\resources\\photo\\";
		 * // 파일 이름 MultipartFile file = mtf.getFile(fileTag); String fileName =
		 * file.getOriginalFilename();
		 * 
		 * try { file.transferTo(new File(filePath + fileName)); } catch (Exception e) {
		 * System.out.println("업로드 오류"); }
		 * 
		 * result.putIfAbsent("url", filePath + fileName); logger.info(filePath +
		 * fileName); return fileName; }
		 * 
		 * @ResponseBody
		 * 
		 * @RequestMapping(value = "/setpost") public Map<String, String>
		 * setpost(@RequestBody String content) throws Exception { Map<String, String>
		 * result = new HashMap<>(); BoardFileVO boardFileVO = new BoardFileVO(); //
		 * String strContent = content; byte[] byteConent = content.getBytes("UTF-8");
		 * // Blob blob = connection.createBlob();//Where connection is the connection
		 * to db object. // blob.setBytes(1, byteContent); logger.info(content);
		 * logger.info(byteConent.toString()); boardFileVO.setFdata(byteConent);
		 * boardDAO.setpost(boardFileVO);
		 * 
		 * return result; }
		 * 
		 * @ResponseBody
		 * 
		 * @RequestMapping(value = "/getphto") public String getphto() throws Exception
		 * { String pathName =
		 * "C:\\Users\\Administrator\\git\\team1\\maintest\\src\\main\\webapp\\resources\\photo\\이미지4.jpg";
		 * File file1 = new File(pathName); logger.info(file1.toString());
		 * 
		 * File thumbnail = new File(
		 * "C:\\Users\\Administrator\\git\\team1\\maintest\\src\\main\\webapp\\resources\\photo\\썸네일test.png"
		 * );
		 * 
		 * // 이미지 파일이 존재하면 if (file1.exists()) { // 썸네일
		 * thumbnail.getParentFile().mkdir();
		 * 
		 * Thumbnails.of(file1).size(300, 300).toFile(thumbnail);
		 * 
		 * }
		 * 
		 * byte[] test1 = Files.readAllBytes(thumbnail.toPath());
		 * logger.info(test1.toString()); BoardFileVO boardFileVO = new BoardFileVO();
		 * boardFileVO.setFdata(test1); boardDAO.setpost(boardFileVO);
		 * 
		 * thumbnail.delete();
		 * 
		 * return null; }
		 * 
		 * @ResponseBody
		 * 
		 * @RequestMapping(value = "/testest") public String testest() throws Exception
		 * {
		 * 
		 * // 썸네일 제작 // 썸네일 사진 String thumb_img_name = null;
		 * 
		 * if (thumb_img_name != null) { String pathName =
		 * "C:\\Users\\Administrator\\git\\team1\\maintest\\src\\main\\webapp\\resources\\photo\\"
		 * + thumb_img_name; // 썸네일로 만들 파일 File thumb_img_file = new File(pathName); //
		 * 썸네일을 담을 파일 File thumbnail = new File(
		 * "C:\\Users\\Administrator\\git\\team1\\maintest\\src\\main\\webapp\\resources\\photo\\썸네일_"
		 * + thumb_img_name);
		 * 
		 * // 대상 파일을 리사징 후 썸네일 파일에 저장 if (thumb_img_file.exists()) { // 썸네일
		 * thumbnail.getParentFile().mkdir();
		 * 
		 * Thumbnails.of(thumb_img_file).size(300, 300).toFile(thumbnail); } } return
		 * null; } }
		 */