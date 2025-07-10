package com.sist.web.vo;

import lombok.Data;
/*
 * ID       NOT NULL VARCHAR2(20)  
PWD      NOT NULL VARCHAR2(200) 
NAME     NOT NULL VARCHAR2(52)  
NICKNAME          VARCHAR2(200) 
SEX      NOT NULL VARCHAR2(20)  
BIRTHDAY NOT NULL DATE          
EMAIL    NOT NULL VARCHAR2(100) 
PHONE    NOT NULL VARCHAR2(13)  
POST              VARCHAR2(10)  
ADDR1             VARCHAR2(200) 
ADDR2             VARCHAR2(200) 
CONTENT           CLOB          
ENABLE            NUMBER        
 */
@Data
public class MemberVO {
	private String id,pwd,name;
}
