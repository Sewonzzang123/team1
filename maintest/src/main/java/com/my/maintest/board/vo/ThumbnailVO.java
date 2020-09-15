package com.my.maintest.board.vo;

import java.sql.Timestamp;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class ThumbnailVO {			 
	private String thumbfname;  //thumbnail 이미지 이름	
	private byte[] thumbfdata;//thumbnail 파일 데이터
	private long thumbfsize; 
	private String thumbftype; // 원본 파일의 ftype에서 값을 가져옴
	private String base64encoded;
	// 썸네일 파일
}
