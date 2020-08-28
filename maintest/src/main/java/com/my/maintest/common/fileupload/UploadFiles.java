package com.my.maintest.common.fileupload;

import java.io.File;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class UploadFiles {

	static final int THUMB_WIDTH = 300;
	static final int THUMB_HEIGHT = 300;
	
	public static String fileUpload(String uploadPath, String fileName, byte[] fileData, String ymdPath) throws Exception {
		
		UUID uid = UUID.randomUUID();
		String newFileName = uid + "_" +fileName;
		String imgPath = uploadPath + ymdPath;
		
		log.info("newFileName" +newFileName);
		log.info("imgPath" + imgPath);
		
		
		File target = new File(imgPath, newFileName);
		FileCopyUtils.copy(fileData, target);
		
		String thumbFileName = "s_" + newFileName;
 
		
		
		
		
		return "";
	}
}
