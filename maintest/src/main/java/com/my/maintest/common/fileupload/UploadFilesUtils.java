package com.my.maintest.common.fileupload;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
public class UploadFilesUtils {

	static final int THUMB_WIDTH = 300;
	static final int THUMB_HEIGHT = 300;

	public static String fileUpload(String uploadPath, String fileName, byte[] fileData, String ymdPath)
			throws Exception {

		// flow
		// 폴더 생성
		// 파일 저장
		// 썸네일 생성

		// 랜덤으로 패스명 생성 + 파일이름(green.jpg)
		// 출력값 : fa5b07c9-7b62-4b02-a54f-90ea92aa2137_green.jpg
		UUID uid = UUID.randomUUID();
		String newFileName = uid + "_" + fileName;
		// prefix 경로 :D:\java\apache-tomcat-9.0.37\wtpwebapps\maintest\resources\file\
		// + 날짜 == > 저장경로
		// 출력값 :
		// D:\java\apache-tomcat-9.0.37\wtpwebapps\maintest\resources\file\2020\08\29
		String imgPath = uploadPath + ymdPath;

		log.info("newFileName===" + newFileName);
		log.info("imgPath===" + imgPath);

		// File(매개변수 1개 : pathName or uri)

		// File(매개변수 2개 : parent pathName , child pathName)
		// File(매개변수 2개 : 부모객체로 부터 가져오는 파일객체 ,
		// a new File instance from a parent abstractpathname and a child pathname
		// string. )

		// 새로운 파일 생성 File(저장할 위치, 저장할 파일이름) ;
		// 비어있는 파일
		File target = new File(imgPath, newFileName); // newFileName fa5b07c9-7b62-4b02-a54f-90ea92aa2137_green.jpg
		// 출력값:D:\java\apache-tomcat-9.0.37\wtpwebapps\maintest\resources\file\2020\08\29\349be108-0942-4880-948f-9ca8954491e5_green.jpg
		log.info("new File(imgPath, newFileName) ===" + target);

		// 파일을 복사한다.
		// fileData = boardVO.getFiles().get(0).getBytes() 1번파일의 bytes와
		// 파일 data를 target에 복사
		// copy(file in , file out)
		FileCopyUtils.copy(fileData, target); // 기존에 form에서 가져온 filedata정보를 새로운 파일 target에 복사한다.

		// 썸네일 파일의 이름은 s_ prefix를 붙여 생성
		String thumbFileName = "s_" + newFileName;
		// 본래 크기의 이미지 파일을 생성 to copy
		// 비어있는 파일
		File image = new File(imgPath + File.separator + newFileName);

		// 썸네일 파일을 생성
		// 비어있는 파일
		// 썸네일 저장 위치는 s라는 이름의 폴더로 한층 더 들어가서 저장
		// D:\java\apache-tomcat-9.0.37\wtpwebapps\maintest\resources\file\2020\08\29\ +
		// s 안에 저장
		File thumbnail = new File(imgPath + File.separator + "s" + File.separator + thumbFileName);

		// 이미지 파일이 존재하면
		if (image.exists()) {
			// 썸네일
			thumbnail.getParentFile().mkdir(); // Creates the directory named by this abstract pathname.

			log.info("thumbnail.getParentFile() ===" + thumbnail.getParentFile());
			// 출력값 :
			// D:\java\apache-tomcat-9.0.37\wtpwebapps\maintest\resources\file\2020\08\29\s

			// 출력값 : if and only if directory was created : true otherwise false

			Thumbnails.of(image).size(THUMB_WIDTH, THUMB_HEIGHT).toFile(thumbnail); // Create a thumbnail and writes it
																					// to a File.
			
			// the thumbnail must have been created from a single source.
		}
		return newFileName;
	}

	public static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath, monthPath, datePath);
		makeDir(uploadPath, yearPath, monthPath, datePath + "\s");
		return datePath; // D:\java\mygit\team1\maintest\src\main\webapp\WEB-INF\resources\imgUpload\2020\08\31

	}

	private static void makeDir(String uploadPath, String... paths) {

		// 제일 안쪽의 패스에
		// paths 총길이 3
		// index [paths.length -n ] :
		// yearPath [0]: length - 3
		// monthPath [1] : length - 2
		// datePath[2] : length - 1

		// datePath의 파일이 존재하면 Stop
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}
		// datePath의 파일이 존재하지 않으면
		for (String path : paths) {
			// \2020
			// \2020\08
			// \2020\08\29
			// 차례로 년도 경로 /년월 경로 /년월일 경로 대입해서 파일을 만들어 봄
			File dirPath = new File(uploadPath + path);
			// Creates a new File instance by converting the given pathname string into an
			// abstract pathname

			// 경로의 파일이 존재하지 않으면 폴더를 만든다 .
			if (!dirPath.exists()) {
				dirPath.mkdir(); // Creates the directory named by this abstract pathname.
			}
		}
	}

}