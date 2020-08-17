package com.my.maintest.board.vo;

/**
 * @author DSY
 *
 */
public class TemporaryVO {
	private String id;
	private String pw;
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
		return "TemporaryVO [id=" + id + ", pw=" + pw + "]";
	}
	
	

}
