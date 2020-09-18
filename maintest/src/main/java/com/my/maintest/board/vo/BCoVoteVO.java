package com.my.maintest.board.vo;

import javax.persistence.Entity;

import lombok.Data;
@Entity
@Data
public class BCoVoteVO {
private long 				bcgbnum;																				//	bcgbnum number primary key,
private long				bnum;																//	bnum number not null,
private long				bcnum;														//	bcnum number not null,
private long				ucode;															//	ucode number not null unique,
private VoteGoodBad 		voted;																//	voted varchar(10) check(voted = 'good' or voted='bad'),
	
}
