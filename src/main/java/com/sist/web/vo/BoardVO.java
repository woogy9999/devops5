package com.sist.web.vo;

import java.util.Date;

import lombok.Data;

/*
 * NO      NOT NULL NUMBER         
NAME    NOT NULL VARCHAR2(50)   
SUBJECT NOT NULL VARCHAR2(2000) 
CONTENT NOT NULL VARCHAR2(4000) 
PWD     NOT NULL VARCHAR2(10)   
REGDATE          DATE           
HIT              NUMBER   
 */

@Data
public class BoardVO {
	private int no,hit;
	private String name,subject,content,pwd;
	private String regdate;
}
