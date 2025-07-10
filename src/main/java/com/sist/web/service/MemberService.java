package com.sist.web.service;

import java.util.Map;

import com.sist.web.vo.MemberVO;

public interface MemberService {
	public MemberVO memberLogin(String id, String pwd);
}
