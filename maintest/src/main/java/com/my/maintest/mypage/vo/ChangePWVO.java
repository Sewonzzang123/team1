package com.my.maintest.mypage.vo;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ChangePWVO {

	@NotEmpty(message = " * 필수 정보입니다.")
	private String culpw;

	@Length(min = 6, max = 10, message = " * 6~10자리로 입력해주세요.")
	private String newpw;
	private String newpwc;
}
