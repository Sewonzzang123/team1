package com.my.maintest.board.vo;

/**
 * @author DSY
 *
 */
public class TemporaryVO {
	private String id;
	private String pw;
	private String nickname;
	private String ucode;
	
	
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

	@Override
	public String toString() {
		return "TemporaryVO [id=" + id + ", pw=" + pw + ", nickname=" + nickname + ", ucode=" + ucode + "]";
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
	
	

}
