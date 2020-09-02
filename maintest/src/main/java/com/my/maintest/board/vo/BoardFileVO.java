package com.my.maintest.board.vo;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Entity;
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

	
	
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
	}
	public long getBnum() {
		return bnum;
	}
	public void setBnum(long bnum) {
		this.bnum = bnum;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public long getFsize() {
		return fsize;
	}
	public void setFsize(long fsize) {
		this.fsize = fsize;
	}
	public String getFtype() {
		return ftype;
	}
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	public byte[] getFdata() {
		return fdata;
	}
	public void setFdata(byte[] fdata) {
		this.fdata = fdata;
	}
	public Timestamp getCdate() {
		return cdate;
	}
	public void setCdate(Timestamp cdate) {
		this.cdate = cdate;
	}
	public Timestamp getUdate() {
		return udate;
	}
	public void setUdate(Timestamp udate) {
		this.udate = udate;
	}
	public String getThumbfname() {
		return thumbfname;
	}
	public void setThumbfname(String thumbfname) {
		this.thumbfname = thumbfname;
	}
	public byte[] getThumbfdata() {
		return thumbfdata;
	}
	public long getThumbfsize() {
		return thumbfsize;
	}
	public void setThumbfsize(long thumbfsize) {
		this.thumbfsize = thumbfsize;
	}
	public void setThumbfdata(byte[] thumbfdata) {
		this.thumbfdata = thumbfdata;
	}
	@Override
	public String toString() {
		return "BoardFileVO [fid=" + fid + ", bnum=" + bnum + ", fname=" + fname + ", fsize=" + fsize + ", ftype=" + ftype
				+ ", fdata=" + Arrays.toString(fdata) + ", thumbfname=" + thumbfname + ", thumbfdata="
				+ Arrays.toString(thumbfdata) + ", thumbfsize=" + thumbfsize + ", cdate=" + cdate + ", udate=" + udate + "]";
	}

	
	
	
	

	
}


