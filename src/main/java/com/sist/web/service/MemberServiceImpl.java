package com.sist.web.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.MemberMapper;
import com.sist.web.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	
	@Autowired
	private MemberMapper mapper;

	@Override
	public MemberVO memberLogin(String id, String pwd) {
	    return mapper.memberLogin(id, pwd);
	}
	


}
