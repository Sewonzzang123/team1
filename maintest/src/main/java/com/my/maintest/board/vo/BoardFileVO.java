package com.my.maintest.board.vo;

import java.sql.Timestamp;

import javax.persistence.Entity;

import lombok.Data;
@Data
@Entity
public class BoardFileVO {

	
	private long fid; // FID NUMBER(10,0)
	private long bnum; // BNUM NUMBER(10,0)
	private String fname; // FNAME VARCHAR2(150 BYTE)
	private long fsize; // FSIZE VARCHAR2(45 BYTE)
	private String ftype; // FTYPE VARCHAR2(50 BYTE)
	private byte[] fdata; // FDATA BLOB
	
	
	
	
	
	
	private String thumbfname;  //thumbnail 이미지 이름	
	private byte[] thumbfdata;//thumbnail 파일 데이터
	private long thumbfsize; 
	
	
	
	
	
	private Timestamp cdate; // CDATE TIMESTAMP(6)
	private Timestamp udate; // UDATE TIMESTAMP(6)

	

	

	
}


