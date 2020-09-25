package com.my.maintest.board.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.my.maintest.item.vo.ListVO;

import lombok.Data;
@Data
@Entity
public class BoardVO {
private long bnum;																																			  //bnum number primary key, --게시글 번호
@Valid
private BcategoryVO bcategory;         						//catNum catName							  //bcategory number , -- 게시판 코드
@Valid
private HeadIdCategoryVO hidcategory;							//hidNum catNum hidName															  //hidnum number, --말머리 번호

private long ucode;																																						  //ucode number NOT NULL, --사용자 코드 아이디
@NotNull
//(message="최소 2자 이상 기록하셔야 합니다.")
@Size(min=2, max= 40, message="제목은최소 2자,  최대 20자까지 입력가능합니다.")
private String btitle;																																						  //btitle varchar2(60) NOT NULL, --게시글 제목
private byte[]  bcontent;																																  //bcontent varchar2(500) NOT NULL, -- 게시글 내용
@NotNull
//(message="최소 2자 이상 기록하셔야 합니다.")
@Size(min=4, max=1500, message="본문은 최소 4자,  최대 1500자까지 입력가능합니다.")
private String tcontent;
private  long bhits;																																					  //bhits number NOT NULL, --조회수
private Timestamp bcdate;																												  //bCdate timestamp NOT NULL, --생성일
private Timestamp budate;																										  //bUdate  timestamp NOT NULL, --수정일
private  long bgroup;																						  //bGroup  number (5), --답글 그룹번호
private  long bstep;																																				  //bstep number(5), --답글 순서 
private  long bindent;																																		 // bindent number(5), --답글 들여쓰기 
private long dbrownum;   //SQL : board "dbrownum" 게시글 목록 row_number() over(order by bgroup asc, bstep desc) as  dbrownum로 생성
private String bnickname; //SQL : member.nickname as "bnickname"
			

//첨부파일

private String thumbnail; // 썸네일
private List<MultipartFile>  files;
private BoardFileVO boardFileVO;
private ThumbnailVO thumbnailVO; //섬네일

//리스트
private ListVO listVO;
}