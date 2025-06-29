package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.CampVO;

public interface CampService {

	public List<CampVO> campListData(Map map);
	
	public int campTotalPage (); 
	
	public CampVO campMainList1();
	public List<CampVO> campMainList2();
}
 