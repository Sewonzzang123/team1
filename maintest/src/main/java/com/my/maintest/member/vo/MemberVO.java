package com.my.maintest.member.vo;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class MemberVO {
	// 회원가입 정보

	@NotEmpty(message = "* 필수 정보입니다.")
	@Email(message = "* 이메일 형식으로 입력해주세요.")
	private String id; // ID VARCHAR2(40 BYTE) No 1 이메일 ex)admin@google.com

	@Length(min = 6, max = 10, message = "* 6~10자리로 입력해주세요.")
	private String pw; // PW VARCHAR2(10 BYTE) No 2 6~10자리, 특수문자 포함

	private String tel; // TEL VARCHAR2(13 BYTE) Yes 3 '-'포함 ex)010-1234-5678

	@NotEmpty(message = "* 필수 정보입니다.")
	private String name;

	@NotEmpty(message = "* 필수 정보입니다.")
	private String nickname; // NICKNAME VARCHAR2(30 BYTE) Yes 4 별칭

	private String tel1; // TEL VARCHAR2(13 BYTE) Yes 3 '-'포함 ex)010-1234-5678
	private String tel2; // TEL VARCHAR2(13 BYTE) Yes 3 '-'포함 ex)010-1234-5678
	private String tel3; // TEL VARCHAR2(13 BYTE) Yes 3 '-'포함 ex)010-1234-5678

	@NotEmpty(message = "* 필수 정보입니다.")
	private String pwc;
	private String ucode;
	private Date cdate;

}
