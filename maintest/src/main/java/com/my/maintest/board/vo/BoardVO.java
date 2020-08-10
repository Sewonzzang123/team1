package com.my.maintest.board.vo;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.Nullable;

import lombok.Data;

public class BoardVO {


private long bnum;																																			  //bnum number primary key, --게시글 번호
private BcategoryVO bcategory;         						//catNum catName							  //bcategory number , -- 게시판 코드
private HeadIdCategoryVO hidcategory;							//hidNum catNum hidName															  //hidnum number, --말머리 번호
private int ucode;																																						  //ucode number NOT NULL, --사용자 코드 아이디
private String btitle;																																						  //btitle varchar2(60) NOT NULL, --게시글 제목
private String bcontent;																																  //bcontent varchar2(500) NOT NULL, -- 게시글 내용    
private  int bhits;																																					  //bhits number NOT NULL, --조회수
private Timestamp bcdate;																												  //bCdate timestamp NOT NULL, --생성일
private Timestamp budate;																										  //bUdate  timestamp NOT NULL, --수정일
private  int bgroup;																						  //bGroup  number (5), --답글 그룹번호
private  int bstep;																																				  //bstep number(5), --답글 순서 
private  int bindent;																																		 // bindent number(5), --답글 들여쓰기 
private String bnickname; //SQL : member.nickname as "bnickname"
																										

//첨부파일
private List<MultipartFile>  files;


public long getBnum() {
	return bnum;
}


public void setBnum(long bnum) {
	this.bnum = bnum;
}


public BcategoryVO getBcategory() {
	return bcategory;
}


public void setBcategory(BcategoryVO bcategory) {
	this.bcategory = bcategory;
}


public HeadIdCategoryVO getHidcategory() {
	return hidcategory;
}


public void setHidcategory(HeadIdCategoryVO hidcategory) {
	this.hidcategory = hidcategory;
}


public int getUcode() {
	return ucode;
}


public void setUcode(int ucode) {
	this.ucode = ucode;
}


public String getBtitle() {
	return btitle;
}


public void setBtitle(String btitle) {
	this.btitle = btitle;
}


public String getBcontent() {
	return bcontent;
}


public void setBcontent(String bcontent) {
	this.bcontent = bcontent;
}


public int getBhits() {
	return bhits;
}


public void setBhits(int bhits) {
	this.bhits = bhits;
}


public Timestamp getBcdate() {
	return bcdate;
}


public void setBcdate(Timestamp bcdate) {
	this.bcdate = bcdate;
}


public Timestamp getBudate() {
	return budate;
}


public void setBudate(Timestamp budate) {
	this.budate = budate;
}


public int getBgroup() {
	return bgroup;
}


public void setBgroup(int bgroup) {
	this.bgroup = bgroup;
}


public int getBstep() {
	return bstep;
}


public void setBstep(int bstep) {
	this.bstep = bstep;
}


public int getBindent() {
	return bindent;
}


public void setBindent(int bindent) {
	this.bindent = bindent;
}


public String getBnickname() {
	return bnickname;
}


public void setBnickname(String bnickname) {
	this.bnickname = bnickname;
}


public List<MultipartFile> getFiles() {
	return files;
}


public void setFiles(List<MultipartFile> files) {
	this.files = files;
}


@Override
public String toString() {
	return "BoardVO [bnum=" + bnum + ", bcategory=" + bcategory + ", hidcategory=" + hidcategory + ", ucode=" + ucode
			+ ", btitle=" + btitle + ", bcontent=" + bcontent + ", bhits=" + bhits + ", bcdate=" + bcdate + ", budate="
			+ budate + ", bgroup=" + bgroup + ", bstep=" + bstep + ", bindent=" + bindent + ", bnickname=" + bnickname
			+ ", files=" + files + "]";
}


}