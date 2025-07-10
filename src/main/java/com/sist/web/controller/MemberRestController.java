package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.MemberMapper;
import com.sist.web.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

@RestController
public class MemberRestController {
	@Autowired
	private MemberMapper dao;
	
	@PostMapping("/member/login_ok")
	public String member_login(@RequestParam("id") String id,@RequestParam("pwd") String pwd,HttpSession session)
	{
		String result="";
		int count=dao.memberIdCount(id);
		if(count==0)
		{
			result="NOID";
		}
		else {
			MemberVO vo=dao.memberInfoData(id);
			if(vo.getPwd().equals(pwd))
			{
				result="OK";
				session.setAttribute("name", vo.getName());
				session.setAttribute("id", vo.getId());
			}
			else {
				result="NOPWD";
			}
		}
		return result;
	}
}