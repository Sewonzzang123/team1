package com.my.maintest.board.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.Nullable;

import lombok.Data;
@Entity
@Data
public class BoardVO {


private long bnum;																																			  //bnum number primary key, --게시글 번호
@Nullable
private BcategoryVO bcategory;         						//catNum catName							  //bcategory number , -- 게시판 코드
@Nullable
private HeadIdCategoryVO hidcategory;							//hidNum catNum hidName															  //hidnum number, --말머리 번호
private long ucode;																																						  //ucode number NOT NULL, --사용자 코드 아이디
@NotNull
@Size(min=4, max=20, message="4자이상 20자 미만으로 작성해주세요.")
private String btitle;																																						  //btitle varchar2(60) NOT NULL, --게시글 제목
@NotNull
private String bcontent;																																  //bcontent varchar2(500) NOT NULL, -- 게시글 내용    
private long bhits;																																					  //bhits number NOT NULL, --조회수
private Timestamp bcdate;																												  //bCdate timestamp NOT NULL, --생성일
private Timestamp budate;																										  //bUdate  timestamp NOT NULL, --수정일
private long bgroup;																						  //bGroup  number (5), --답글 그룹번호
private long bstep;																																				  //bstep number(5), --답글 순서 
private long bindent;																																		 // bindent number(5), --답글 들여쓰기 
private String bnickname; //SQL : member.nickname as "bnickname"
																										

//첨부파일
private List<MultipartFile>  files;

}
