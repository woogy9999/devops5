package com.sist.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.*;
import com.sist.web.vo.*;

@Mapper
public interface CampMapper {
/*
 * <select id="campListData" resultType="CampVO" parameterType="hashmap">
 		SELECT cno,title,poster,num 
 		FROM (SELECT cno,title,poster,rownum as num 
 		FROM (SELECT cno,title,poster 
 		FROM CAMP ORDER BY cno ASC))
 		WHERE num BETWEEN #{start} AND #{end}
 	</select>
 */
	public List<CampVO> campListData(Map map);
	
	/*
	 * <select id="campTotalPage" resultType="int">
 		SELECT CEIL(COUNT(*)/12.0) FROM CAMP
 	</select>
	 */
	public int campTotalPage ();
	
	
	@Select("SELECT cno, title, poster,intro FROM "
			+ "(SELECT cno, title, poster,intro FROM CAMP ORDER BY cno ASC) "
			+ "WHERE cno = 1237")
	public CampVO campMainList1();
	
	@Select("SELECT cno, title, poster,addr,intro FROM "
			+ "(SELECT cno, title, poster,addr,intro FROM CAMP ORDER BY cno ASC) "
			+ "WHERE ROWNUM <= 4")
	public List<CampVO> campMainList2();
}
