package com.my.maintest.board.vo;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class AnnotationVO {

	private String annum; // CID NUMBER(10,0) No 1 분류코드
	private String canum; // CNAME VARCHAR2(60 BYTE) No 2 분류명
	private String aname;
}
