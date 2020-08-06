package com.my.maintest.board.vo;

import javax.persistence.Entity;
import javax.validation.constraints.Positive;

import lombok.Data;

@Entity
@Data
public class BcategoryVO {

	@Positive(message = "분류를 선택하세요")
	private String catnum; // CID NUMBER(10,0) No 1 분류코드
	private String catname; // CNAME VARCHAR2(60 BYTE) No 2 분류명
	private String btype;
}
