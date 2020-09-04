package com.my.maintest.common.fileupload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class UploadFilesUtils {

	static final int THUMB_WIDTH = 150;
	static final int THUMB_HEIGHT = 200;
	
	public static BufferedImage makeThumbnail( String filePath, String fileName, String fileExt) throws Exception{
		BufferedImage _thumbnail = null;
		//저장된 원본파일로부터 BufferedImage객체 생성
		BufferedImage srcImg = ImageIO.read(new File(filePath));
		
		//원본이미지의 너비 / 높이 
		int originW = srcImg.getWidth();		
		int originH = srcImg.getHeight();
		
	//원본 기준 썸네일의 crop 지점 x,y계산		
			int capturedW = originW;
			int capturedH = (originW*THUMB_HEIGHT) / THUMB_WIDTH ;
			
			//계산된 높이가 원본보다 높다면 CROP이 안되므로
			//원본 높이를 기준으로 썸네일의 비율로 너비를 계산함.		
			if(capturedH > originH){
				capturedW = (originH * THUMB_WIDTH)/THUMB_HEIGHT;
				capturedH = originH;
						}
			
			//계산된 크기로 원본이미지의 중앙부를 crop합니다. 		
			BufferedImage cropImg = Scalr.crop(srcImg, (originW-capturedW)/2,(originH -capturedH)/2, capturedW,capturedH );
						//crop된 이미지로 썸네일을 생성 반환
			_thumbnail = Scalr.resize(cropImg, THUMB_WIDTH, THUMB_HEIGHT);			
			
		return _thumbnail ;
		}
	
	
	public static BufferedImage makeThumb( List<MultipartFile> multifiles)throws Exception {
		BufferedImage thumbnailImage = null;
		for(int i = 0 ; i < multifiles.size(); i++ ) {			
			InputStream in = multifiles.get(i).getInputStream();
			BufferedImage originalImage = ImageIO.read(in);
			 thumbnailImage = Scalr.resize(originalImage, THUMB_WIDTH, THUMB_HEIGHT);
		
			//이미지 자동 조절 기능 적용
			//가로기준
			BufferedImage thumbnailImageOnWidth = Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, THUMB_WIDTH); 
			//세로기준
			BufferedImage thumbnailImageOnHeight = Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, THUMB_HEIGHT);
			
			in.close();	
		}
		
		return thumbnailImage;
	}
	
}