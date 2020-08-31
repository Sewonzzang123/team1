package com.my.maintest.board.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.Nullable;

import lombok.Data;

@Entity
public class BoardVO {

	private long bnum; // bnum number primary key, --게시글 번호
	@Valid
	private BcategoryVO bcategory; // catNum catName //bcategory number , -- 게시판 코드
	@Valid
	private HeadIdCategoryVO hidcategory; // hidNum catNum hidName //hidnum number, --말머리 번호

	private long ucode; // ucode number NOT NULL, --사용자 코드 아이디
	@NotNull
	@Size(min = 2, max = 20, message = "제목은최소 2자,  최대 20자까지 입력가능합니다.")
	private String btitle; // btitle varchar2(60) NOT NULL, --게시글 제목
	@NotNull
	@Size(min = 4, max = 1500, message = "본문은 최소 4자,  최대 1500자까지 입력가능합니다.")
	private String bcontent; // bcontent varchar2(500) NOT NULL, -- 게시글 내용
	private long bhits; // bhits number NOT NULL, --조회수
	private Timestamp bcdate; // bCdate timestamp NOT NULL, --생성일
	private Timestamp budate; // bUdate timestamp NOT NULL, --수정일
	private long bgroup; // bGroup number (5), --답글 그룹번호
	private long bstep; // bstep number(5), --답글 순서
	private long bindent; // bindent number(5), --답글 들여쓰기

	private int num; // 아래 dbrownum과 용도가 같은듯????

	private long dbrownum; // SQL : board "dbrownum" 게시글 목록 row_number() over(order by bgroup asc, bstep
							// desc) as dbrownum로 생성
	private String bnickname; // SQL : member.nickname as "bnickname"

//첨부파일

	private List<MultipartFile> files;
	private BoardFileVO boardFileVO;

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

	public long getUcode() {
		return ucode;
	}

	public void setUcode(long ucode) {
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

	public long getBhits() {
		return bhits;
	}

	public void setBhits(long bhits) {
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

	public long getBgroup() {
		return bgroup;
	}

	public long getDbrownum() {
		return dbrownum;
	}

	public void setDbrownum(long dbrownum) {
		this.dbrownum = dbrownum;
	}

	public void setBgroup(long bgroup) {
		this.bgroup = bgroup;
	}

	public long getBstep() {
		return bstep;
	}

	public void setBstep(long bstep) {
		this.bstep = bstep;
	}

	public long getBindent() {
		return bindent;
	}

	public void setBindent(long bindent) {
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

	public BoardFileVO getBoardFileVO() {
		return boardFileVO;
	}

	public void setBoardFileVO(BoardFileVO boardFileVO) {
		this.boardFileVO = boardFileVO;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "BoardVO [bnum=" + bnum + ", bcategory=" + bcategory + ", hidcategory=" + hidcategory + ", ucode="
				+ ucode + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bhits=" + bhits + ", bcdate=" + bcdate
				+ ", budate=" + budate + ", bgroup=" + bgroup + ", bstep=" + bstep + ", bindent=" + bindent + ", num="
				+ num + ", dbrownum=" + dbrownum + ", bnickname=" + bnickname + ", files=" + files + ", boardFileVO="
				+ boardFileVO + "]";
	}

}