package com.my.maintest.board.vo;

import java.util.Arrays;

/**
 * @author DSY
 *
 */
public class TemporaryVO {
	private String id;
	private String pw;
	private String nickname;
	private String ucode;
	
	
	
	private String encoded;
	private String ftype;
	
	
	public String getEncoded() {
		return encoded;
	}
	public void setEncoded(String encoded) {
		this.encoded = encoded;
	}
	public String getFtype() {
		return ftype;
	}
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUcode() {
		return ucode;
	}
	public void setUcode(String ucode) {
		this.ucode = ucode;
	}
	@Override
	public String toString() {
		return "TemporaryVO [id=" + id + ", pw=" + pw + ", nickname=" + nickname + ", ucode=" + ucode + ", encoded="
				+ encoded + ", ftype=" + ftype + "]";
	}

	

}
