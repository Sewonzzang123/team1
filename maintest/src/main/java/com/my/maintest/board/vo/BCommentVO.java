package com.my.maintest.board.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BCommentVO {
	
private long dbrownum;
private long bcnum;																									//	BCNUM	NUMBER
private long ucode;																										//	UCODE	NUMBER
private String nickname;
private long pucode;
private String pnickname;
private long bnum;																										//	BNUM	NUMBER
private String bccontent;																					//	BCCOMMENT	VARCHAR2(500 BYTE)
private long bcgrp;																									//	BCGROUP	NUMBER
private long bcindent;																								//	BCINDENT	NUMBER
private long bcstep;																										//	BCSTEP	NUMBER
private long bcgood;																									//	BCGOOD	NUMBER
private long bcbad;																											//	BCBAD	NUMBER
private Timestamp cdate;																				//	CDATE	TIMESTAMP(6)
private Timestamp udate;																				//	UDATE	TIMESTAMP(6)
	
}

