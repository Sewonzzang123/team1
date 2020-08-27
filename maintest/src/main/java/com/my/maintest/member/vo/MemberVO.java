package com.my.maintest.member.vo;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class MemberVO {
	// �쉶�썝媛��엯 �젙蹂�

	@NotEmpty(message = "* �븘�닔 �젙蹂댁엯�땲�떎.")
	@Email(message = "* �씠硫붿씪 �삎�떇�쑝濡� �엯�젰�빐二쇱꽭�슂.")
	private String id; // ID VARCHAR2(40 BYTE) No 1 �씠硫붿씪 ex)admin@google.com

	@NotEmpty(message = "* �븘�닔 �젙蹂댁엯�땲�떎.")
	@Length(min = 6, max = 10, message = "* 6~10�옄由щ줈 �엯�젰�빐二쇱꽭�슂.")
	private String pw; // PW VARCHAR2(10 BYTE) No 2 6~10�옄由�, �듅�닔臾몄옄 �룷�븿

	private String tel; // TEL VARCHAR2(13 BYTE) Yes 3 '-'�룷�븿 ex)010-1234-5678

	@NotEmpty(message = "* �븘�닔 �젙蹂댁엯�땲�떎.")
	private String name;

	@NotEmpty(message = "* �븘�닔 �젙蹂댁엯�땲�떎.")
	private String nickname; // NICKNAME VARCHAR2(30 BYTE) Yes 4 蹂꾩묶

	private String tel1; // TEL VARCHAR2(13 BYTE) Yes 3 '-'�룷�븿 ex)010-1234-5678
	private String tel2; // TEL VARCHAR2(13 BYTE) Yes 3 '-'�룷�븿 ex)010-1234-5678
	private String tel3; // TEL VARCHAR2(13 BYTE) Yes 3 '-'�룷�븿 ex)010-1234-5678

	@NotEmpty(message = "* �븘�닔 �젙蹂댁엯�땲�떎.")
	private String pwc;

	private String ucode;

	private Date cdate;

}